package com.example.mall.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.stereotype.Component;

/** 非破坏性升级：只补缺失的图片列，已有商品、库存和订单不重建。 */
@Component
@Order(-100)
public class InventoryMigration implements ApplicationRunner {
    private final JdbcTemplate jdbc;
    public InventoryMigration(JdbcTemplate jdbc) { this.jdbc = jdbc; }
    @Override
    public void run(ApplicationArguments args) {
        boolean exists = Boolean.TRUE.equals(jdbc.execute((ConnectionCallback<Boolean>) connection -> {
            try (var columns = connection.getMetaData().getColumns(connection.getCatalog(), null, "product", "image_url")) {
                return columns.next();
            }
        }));
        if (!exists) jdbc.execute("ALTER TABLE product ADD COLUMN image_url VARCHAR(255) NOT NULL DEFAULT ''");
    }
}
