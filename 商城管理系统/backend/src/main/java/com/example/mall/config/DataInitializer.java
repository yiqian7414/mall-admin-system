package com.example.mall.config;

import com.example.mall.model.Requests.ProductInput;
import com.example.mall.repository.CategoryRepository;
import com.example.mall.repository.ProductRepository;
import com.example.mall.repository.UserRepository;
import java.math.BigDecimal;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** 只在全新项目数据库中执行一次；标记保存在数据库中，重启不会覆盖用户修改。 */
@Component
public class DataInitializer implements ApplicationRunner {
    private final JdbcTemplate jdbc;
    private final UserRepository users;
    private final CategoryRepository categories;
    private final ProductRepository products;
    private final PasswordEncoder encoder;

    public DataInitializer(JdbcTemplate jdbc, UserRepository users, CategoryRepository categories,
            ProductRepository products, PasswordEncoder encoder) {
        this.jdbc = jdbc;
        this.users = users;
        this.categories = categories;
        this.products = products;
        this.encoder = encoder;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        Long initialized = jdbc.queryForObject("SELECT COUNT(*) FROM app_setting WHERE setting_key = 'bootstrap_v1'",
                Long.class);
        if (initialized != null && initialized > 0)
            return;
        users.insert("admin", "商城管理员", encoder.encode("Admin@123456"), "ADMIN", true);
        users.insert("staff", "商城员工", encoder.encode("Staff@123456"), "STAFF", true);
        long digital = categories.insert("数码配件", "键盘、鼠标等电脑周边");
        long daily = categories.insert("生活用品", "日常生活好物");
        products.insert(
                new ProductInput("无线鼠标", "MOUSE-001", digital, new BigDecimal("59.00"), 50, "ON_SALE", "轻巧便携，适合办公"));
        products.insert(
                new ProductInput("机械键盘", "KEYBOARD-001", digital, new BigDecimal("199.00"), 20, "ON_SALE", "清晰按键反馈"));
        products.insert(
                new ProductInput("保温杯", "CUP-001", daily, new BigDecimal("89.00"), 4, "ON_SALE", "500ml 不锈钢保温杯"));
        jdbc.update("INSERT INTO app_setting(setting_key, setting_value) VALUES ('bootstrap_v1', 'complete')");
    }
}
