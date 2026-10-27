package com.example.mall.service;

import com.example.mall.common.BusinessException;
import com.example.mall.model.Models.Page;
import com.example.mall.model.Models.StockMovement;
import com.example.mall.model.Requests.StockInput;
import com.example.mall.repository.ProductRepository;
import com.example.mall.repository.StockRepository;
import java.time.LocalDate;
import java.util.Set;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** 所有运行期库存变更统一经过这里：锁商品、校验、修改数量、写流水，同一事务提交。 */
@Service
public class InventoryService {
    private final ProductRepository products;
    private final StockRepository movements;
    public InventoryService(ProductRepository products, StockRepository movements) {
        this.products = products; this.movements = movements;
    }

    public Page<StockMovement> findPage(String keyword, String type, LocalDate start, LocalDate end, int page, int size) {
        CatalogService.checkPage(page, size);
        if (type != null && !type.isBlank() && !Set.of("INBOUND", "OUTBOUND", "ORDER_DEDUCT", "ORDER_RETURN", "OPENING").contains(type))
            throw BusinessException.badRequest("库存业务类型无效");
        if ((start != null && start.getYear() < 1900) || (end != null && (end.getYear() < 1900 || end.getYear() > 9998))
                || (start != null && end != null && start.isAfter(end)))
            throw BusinessException.badRequest("日期范围无效");
        return movements.findPage(keyword, type, start, end, page, size);
    }

    @Transactional
    public StockMovement manual(StockInput input) {
        var reasons = "INBOUND".equals(input.type()) ? Set.of("采购补货", "其他入库") : Set.of("商品报损", "内部领用", "其他出库");
        if (!reasons.contains(input.reason())) throw BusinessException.badRequest("出入库原因无效");
        return move(input.productId(), "INBOUND".equals(input.type()) ? input.quantity() : -input.quantity(),
                input.type(), ("INBOUND".equals(input.type()) ? "RK-" : "CK-") + input.requestId(), input.reason(),
                input.remark() == null ? "" : input.remark().trim());
    }

    @Transactional
    public StockMovement move(long productId, int delta, String type, String reference, String reason, String remark) {
        var product = products.lockById(productId).orElseThrow(() -> BusinessException.notFound("商品不存在"));
        var previous = movements.find(productId, type, reference);
        if (previous.isPresent()) {
            var old = previous.get();
            if (old.delta() != delta || !old.reason().equals(reason) || !old.remark().equals(remark))
                throw BusinessException.conflict("同一操作编号不能提交不同内容，请重新打开表单");
            return old; // 同一个手动提交重试，不会再改一次库存。
        }
        long after = (long) product.stock() + delta;
        if (delta == 0 || after < 0) throw BusinessException.conflict("库存不足，无法完成出库");
        if (after > 1000000000) throw BusinessException.conflict("变更后库存不能超过 10 亿");
        if (products.adjustStock(productId, delta) != 1) throw BusinessException.conflict("库存已变化，请刷新后重试");
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String operator = authentication == null ? "system" : authentication.getName();
        return movements.insert(product, delta, type, reference, reason, remark, operator);
    }
}
