package com.example.mall.service;

import com.example.mall.model.Models.Dashboard;
import com.example.mall.repository.OrderRepository;
import com.example.mall.repository.ProductRepository;
import java.math.BigDecimal;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DashboardService {
    private final JdbcTemplate jdbc;
    private final OrderRepository orders;
    private final ProductRepository products;

    public DashboardService(JdbcTemplate jdbc, OrderRepository orders, ProductRepository products) {
        this.jdbc = jdbc;
        this.orders = orders;
        this.products = products;
    }

    @Transactional(readOnly = true)
    public Dashboard overview() {
        return new Dashboard(count("SELECT COUNT(*) FROM product"), count("SELECT COUNT(*) FROM category"),
                count("SELECT COUNT(*) FROM mall_order"), count("SELECT COUNT(*) FROM product WHERE stock <= 5"),
                jdbc.queryForObject(
                        "SELECT COALESCE(SUM(total_amount), 0) FROM mall_order WHERE status IN ('PAID', 'SHIPPED', 'COMPLETED')",
                        BigDecimal.class),
                count("SELECT COUNT(*) FROM mall_order WHERE status = 'PENDING'"), orders.recentOrders(),
                products.lowStockProducts());
    }

    private long count(String sql) {
        return jdbc.queryForObject(sql, Long.class);
    }
}
