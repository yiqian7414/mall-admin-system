package com.example.mall.repository;

import com.example.mall.model.Models.Order;
import com.example.mall.model.Models.OrderItem;
import com.example.mall.model.Models.Page;
import com.example.mall.model.Requests.OrderInput;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {
    private final JdbcTemplate jdbc;
    private static final RowMapper<Order> MAPPER = (rs, row) -> new Order(rs.getLong("id"), rs.getString("order_no"),
            rs.getString("customer_name"), rs.getString("phone"), rs.getString("address"), rs.getString("status"),
            rs.getBigDecimal("total_amount"), rs.getTimestamp("created_at").toLocalDateTime(), List.of());
    private static final RowMapper<OrderItem> ITEM_MAPPER = (rs, row) -> new OrderItem(rs.getLong("product_id"),
            rs.getString("product_name"), rs.getBigDecimal("unit_price"), rs.getInt("quantity"),
            rs.getBigDecimal("line_total"));

    public OrderRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Optional<Order> findById(long id) {
        return jdbc.query("SELECT * FROM mall_order WHERE id = ?", MAPPER, id).stream().findFirst()
                .map(this::withItems);
    }

    public Optional<Order> lockById(long id) {
        return jdbc.query("SELECT * FROM mall_order WHERE id = ? FOR UPDATE", MAPPER, id).stream().findFirst()
                .map(this::withItems);
    }

    public Page<Order> findPage(String keyword, String status, int page, int size) {
        var where = new StringBuilder(" WHERE 1 = 1");
        var parameters = new ArrayList<Object>();
        if (keyword != null && !keyword.isBlank()) {
            where.append(
                    " AND (LOWER(order_no) LIKE ? ESCAPE '!' OR LOWER(customer_name) LIKE ? ESCAPE '!' OR phone LIKE ? ESCAPE '!')");
            String pattern = "%" + ProductRepository.escapeLike(keyword.trim().toLowerCase(java.util.Locale.ROOT))
                    + "%";
            parameters.add(pattern);
            parameters.add(pattern);
            parameters.add(pattern);
        }
        if (status != null && !status.isBlank()) {
            where.append(" AND status = ?");
            parameters.add(status);
        }
        long total = jdbc.queryForObject("SELECT COUNT(*) FROM mall_order" + where, Long.class, parameters.toArray());
        parameters.add(size);
        parameters.add((long) (page - 1) * size);
        var orders = jdbc.query("SELECT * FROM mall_order" + where + " ORDER BY id DESC LIMIT ? OFFSET ?", MAPPER,
                parameters.toArray());
        return new Page<>(orders.stream().map(this::withItems).toList(), total, page, size);
    }

    public long insert(String orderNo, OrderInput input, BigDecimal total) {
        var keys = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO mall_order(order_no, customer_name, phone, address, status, total_amount) VALUES (?, ?, ?, ?, 'PENDING', ?)",
                    new String[] { "id" });
            statement.setString(1, orderNo);
            statement.setString(2, input.customerName().trim());
            statement.setString(3, input.phone().trim());
            statement.setString(4, input.address().trim());
            statement.setBigDecimal(5, total);
            return statement;
        }, keys);
        return keys.getKey().longValue();
    }

    public void insertItem(long orderId, OrderItem item) {
        jdbc.update(
                "INSERT INTO order_item(order_id, product_id, product_name, unit_price, quantity, line_total) VALUES (?, ?, ?, ?, ?, ?)",
                orderId, item.productId(), item.productName(), item.unitPrice(), item.quantity(), item.lineTotal());
    }

    public void updateStatus(long id, String status) {
        jdbc.update("UPDATE mall_order SET status = ? WHERE id = ?", status, id);
    }

    public List<Order> recentOrders() {
        return jdbc.query("SELECT * FROM mall_order ORDER BY id DESC LIMIT 6", MAPPER).stream().map(this::withItems)
                .toList();
    }

    private Order withItems(Order order) {
        List<OrderItem> items = jdbc.query("SELECT * FROM order_item WHERE order_id = ? ORDER BY id", ITEM_MAPPER,
                order.id());
        return new Order(order.id(), order.orderNo(), order.customerName(), order.phone(), order.address(),
                order.status(), order.totalAmount(), order.createdAt(), items);
    }
}
