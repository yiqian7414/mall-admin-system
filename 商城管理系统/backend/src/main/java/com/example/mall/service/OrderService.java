package com.example.mall.service;

import com.example.mall.common.BusinessException;
import com.example.mall.model.Models.Order;
import com.example.mall.model.Models.OrderItem;
import com.example.mall.model.Models.Page;
import com.example.mall.model.Requests.OrderInput;
import com.example.mall.model.Requests.OrderLineInput;
import com.example.mall.repository.OrderRepository;
import com.example.mall.repository.ProductRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    private static final Map<String, List<String>> TRANSITIONS = Map.of("PENDING", List.of("PAID", "CANCELLED"), "PAID",
            List.of("SHIPPED", "CANCELLED"), "SHIPPED", List.of("COMPLETED"), "COMPLETED", List.of(), "CANCELLED",
            List.of());
    private final OrderRepository orders;
    private final ProductRepository products;
    private final InventoryService inventory;

    public OrderService(OrderRepository orders, ProductRepository products, InventoryService inventory) {
        this.orders = orders;
        this.products = products;
        this.inventory = inventory;
    }

    public Page<Order> findPage(String keyword, String status, int page, int size) {
        CatalogService.checkPage(page, size);
        if (status != null && !status.isBlank() && !TRANSITIONS.containsKey(status)) {
            throw BusinessException.badRequest("订单状态无效");
        }
        return orders.findPage(keyword, status, page, size);
    }

    public Order get(long id) {
        return orders.findById(id).orElseThrow(() -> BusinessException.notFound("订单不存在"));
    }

    /** 所有扣库存和订单明细写入必须在同一事务内成功，否则全部回滚。 */
    @Transactional
    public Order create(OrderInput input) {
        var uniqueIds = new HashSet<Long>();
        for (OrderLineInput line : input.items()) {
            if (!uniqueIds.add(line.productId()))
                throw BusinessException.badRequest("同一商品请合并为一行后提交");
        }
        var lines = input.items().stream().sorted(Comparator.comparing(OrderLineInput::productId)).toList();
        var snapshots = new ArrayList<OrderItem>();
        BigDecimal total = new BigDecimal("0.00");
        String orderNo = "M" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"))
                + UUID.randomUUID().toString().substring(0, 8).toUpperCase(java.util.Locale.ROOT);
        for (OrderLineInput line : lines) {
            var product = products.lockById(line.productId())
                    .orElseThrow(() -> BusinessException.notFound("订单中的商品不存在"));
            if (!product.status().equals("ON_SALE"))
                throw BusinessException.conflict(product.name() + "已下架");
            if (product.stock() < line.quantity())
                throw BusinessException.conflict(product.name() + "库存不足");
            inventory.move(product.id(), -line.quantity(), "ORDER_DEDUCT", orderNo, "创建订单扣减", "");
            BigDecimal lineTotal = product.price().multiply(BigDecimal.valueOf(line.quantity()));
            snapshots.add(new OrderItem(product.id(), product.name(), product.price(), line.quantity(), lineTotal));
            total = total.add(lineTotal);
        }
        if (total.compareTo(new BigDecimal("9999999999999999.99")) > 0) {
            throw BusinessException.badRequest("订单总金额超出允许范围，请拆分订单");
        }
        long id = orders.insert(orderNo, input, total);
        for (OrderItem item : snapshots)
            orders.insertItem(id, item);
        return get(id);
    }

    @Transactional
    public Order changeStatus(long id, String targetStatus) {
        var order = orders.lockById(id).orElseThrow(() -> BusinessException.notFound("订单不存在"));
        if (!TRANSITIONS.get(order.status()).contains(targetStatus)) {
            throw BusinessException.conflict("订单不能从 " + order.status() + " 变为 " + targetStatus);
        }
        if (targetStatus.equals("CANCELLED")) {
            for (OrderItem item : order.items().stream().sorted(Comparator.comparing(OrderItem::productId)).toList()) {
                products.lockById(item.productId()).orElseThrow(() -> BusinessException.conflict("订单商品不存在，无法归还库存"));
                inventory.move(item.productId(), item.quantity(), "ORDER_RETURN", order.orderNo(), "取消订单归还", "");
            }
        }
        orders.updateStatus(id, targetStatus);
        return get(id);
    }
}
