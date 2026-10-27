package com.example.mall.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** 旧库存只登记升级时基线，不伪造历史入库，也不改变现存数量。 */
@Component
@Order(100)
public class OpeningInventory implements ApplicationRunner {
    private final JdbcTemplate jdbc;
    public OpeningInventory(JdbcTemplate jdbc) { this.jdbc = jdbc; }
    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        if (jdbc.queryForObject("SELECT COUNT(*) FROM app_setting WHERE setting_key = 'stock_ledger_v1'", Long.class) > 0) return;
        jdbc.update("INSERT INTO stock_movement(product_id, product_name, sku, type, delta, before_stock, after_stock, reference, reason, remark, operator) SELECT p.id, p.name, p.sku, 'OPENING', p.stock, 0, p.stock, 'MIGRATION-V1', '升级期初库存', '仅记录升级时数量，不代表历史采购入库', 'system' FROM product p WHERE p.stock <> 0 AND NOT EXISTS (SELECT 1 FROM stock_movement m WHERE m.product_id = p.id)");
        jdbc.update("INSERT INTO app_setting(setting_key, setting_value) VALUES ('stock_ledger_v1', 'complete')");
    }
}
