package com.example.mall.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/** 返回给前端的数据结构；密码摘要绝不进入 API 响应。 */
public final class Models {
    private Models() {
    }

    public record User(long id, String username, String displayName, String role, boolean enabled) {
    }

    public record UserAccount(User user, @JsonIgnore String passwordHash) {
    }

    public record Category(long id, String name, String description) {
    }

    public record Product(long id, String name, String sku, long categoryId, String categoryName, BigDecimal price,
            int stock, String status, String description, LocalDateTime createdAt) {
    }

    public record OrderItem(long productId, String productName, BigDecimal unitPrice, int quantity,
            BigDecimal lineTotal) {
    }

    public record Order(long id, String orderNo, String customerName, String phone, String address, String status,
            BigDecimal totalAmount, LocalDateTime createdAt, List<OrderItem> items) {
    }

    public record Page<T>(List<T> items, long total, int page, int size) {
    }

    public record Dashboard(long productCount, long categoryCount, long orderCount, long lowStockCount,
            BigDecimal totalSales, long pendingOrderCount, List<Order> recentOrders, List<Product> lowStockProducts) {
    }
}
