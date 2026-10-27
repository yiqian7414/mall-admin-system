package com.example.mall;

import com.example.mall.config.DataInitializer;
import com.example.mall.model.Requests.OrderInput;
import com.example.mall.model.Requests.OrderLineInput;
import com.example.mall.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/** 使用真实 Security 过滤器和测试 SQL 数据库，不使用伪造 Repository。 */
@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:malltests;MODE=MySQL;DATABASE_TO_LOWER=TRUE;DB_CLOSE_DELAY=-1;LOCK_TIMEOUT=10000",
        "spring.datasource.driver-class-name=org.h2.Driver", "spring.datasource.username=sa",
        "spring.datasource.password=", "mall.upload-directory=./target/test-uploads" })
@AutoConfigureMockMvc
@TestExecutionListeners(listeners = { ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class }, mergeMode = TestExecutionListeners.MergeMode.REPLACE_DEFAULTS)
class MallIntegrationTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    JdbcTemplate jdbc;
    @Autowired
    OrderService orders;
    @Autowired
    DataInitializer initializer;
    @Autowired
    com.example.mall.service.InventoryService inventory;
    @Autowired
    com.example.mall.config.OpeningInventory openingInventory;

    @Test
    void authenticationRequiresCsrfAndRotatesTokenAfterLoginAndLogout() throws Exception {
        mvc.perform(get("/api/health")).andExpect(status().isOk())
                .andExpect(jsonPath("$.application").value("mall-admin"));
        mvc.perform(get("/api/auth/me")).andExpect(status().isUnauthorized());
        mvc.perform(post("/api/auth/login").param("username", "admin").param("password", "Admin@123456"))
                .andExpect(status().isForbidden()).andExpect(jsonPath("$.message").isString());

        MvcResult tokenResult = mvc.perform(get("/api/auth/csrf")).andExpect(status().isOk()).andReturn();
        var session = (MockHttpSession) tokenResult.getRequest().getSession(false);
        String oldToken = json(tokenResult).get("token").asText();
        mvc.perform(post("/api/auth/login").session(session).header("X-CSRF-TOKEN", oldToken).param("username", "admin")
                .param("password", "wrong-password")).andExpect(status().isUnauthorized());
        mvc.perform(post("/api/auth/login").session(session).header("X-CSRF-TOKEN", csrf(session))
                .param("username", "admin").param("password", "Admin@123456")).andExpect(status().isOk())
                .andExpect(jsonPath("$.role").value("ADMIN")).andExpect(jsonPath("$.passwordHash").doesNotExist());
        String newToken = csrf(session);
        assertThat(newToken).isNotEqualTo(oldToken);
        mvc.perform(post("/api/categories").session(session).header("X-CSRF-TOKEN", oldToken)
                .contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"无效令牌分类\"}"))
                .andExpect(status().isForbidden());
        mvc.perform(post("/api/auth/logout").session(session).header("X-CSRF-TOKEN", newToken))
                .andExpect(status().isOk());
        mvc.perform(get("/api/auth/me")).andExpect(status().isUnauthorized());
    }

    @Test
    @Transactional
    void staffCanManageBusinessButCannotManageUsers() throws Exception {
        var session = login("staff", "Staff@123456");
        mvc.perform(get("/api/users").session(session)).andExpect(status().isForbidden());
        var category = create(session, "/api/categories", Map.of("name", "员工分类", "description", "员工可维护"), 201);
        long id = category.get("id").asLong();
        mvc.perform(put("/api/categories/" + id).session(session).header("X-CSRF-TOKEN", csrf(session))
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(Map.of("name", "员工分类修改"))))
                .andExpect(status().isOk()).andExpect(jsonPath("$.name").value("员工分类修改"));
        mvc.perform(delete("/api/categories/" + id).session(session).header("X-CSRF-TOKEN", csrf(session)))
                .andExpect(status().isNoContent());
    }

    @Test
    @Transactional
    void productValidationSearchPaginationAndReferenceRules() throws Exception {
        var session = login("admin", "Admin@123456");
        long categoryId = firstCategory();
        for (int number = 1; number <= 3; number++) {
            create(session, "/api/products",
                    productInput("分页商品" + number, "PAGE-" + number, categoryId, "8.80", number), 201);
        }
        mvc.perform(
                get("/api/products").session(session).param("keyword", "分页商品").param("size", "2").param("page", "2"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.total").value(3))
                .andExpect(jsonPath("$.page").value(2)).andExpect(jsonPath("$.items.length()").value(1));
        mvc.perform(get("/api/products").session(session).param("size", "101")).andExpect(status().isBadRequest());
        mvc.perform(get("/api/products").session(session).param("status", "HACKED")).andExpect(status().isBadRequest());
        mvc.perform(get("/api/products").session(session).param("keyword", "' OR 1=1 --")).andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(0));
        create(session, "/api/products", productInput("无效小数", "BAD-PRICE", categoryId, "1.001", 1), 400);
        create(session, "/api/products", Map.of("name", "不能设置库存", "sku", "BAD-STOCK", "categoryId", categoryId,
                "price", 1, "stock", 5, "status", "ON_SALE"), 400);
        create(session, "/api/products", Map.of("name", "小数库存", "sku", "FRACTION-STOCK", "categoryId", categoryId,
                "price", 1, "stock", 0.1, "status", "ON_SALE"), 400);
        create(session, "/api/products", productInput("零元赠品", "ZERO-GIFT", categoryId, "0.00", 1), 201);
        create(session, "/api/products", productInput("重复编号", "ZERO-GIFT", categoryId, "1.00", 1), 409);
        mvc.perform(delete("/api/categories/" + categoryId).session(session).header("X-CSRF-TOKEN", csrf(session)))
                .andExpect(status().isConflict());
    }

    @Test
    void failedOrderRollsBackEveryStockDeduction() throws Exception {
        var session = login("admin", "Admin@123456");
        long first = create(session, "/api/products", productInput("有货商品", "ROLLBACK-A", firstCategory(), "5.00", 5),
                201).get("id").asLong();
        long second = create(session, "/api/products", productInput("缺货商品", "ROLLBACK-B", firstCategory(), "7.00", 0),
                201).get("id").asLong();
        try {
            long count = jdbc.queryForObject("SELECT COUNT(*) FROM mall_order", Long.class);
            long movementCount = jdbc.queryForObject("SELECT COUNT(*) FROM stock_movement", Long.class);
            create(session, "/api/orders", orderInput(
                    List.of(Map.of("productId", first, "quantity", 2), Map.of("productId", second, "quantity", 1))),
                    409);
            assertThat(stock(first)).isEqualTo(5);
            assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM stock_movement", Long.class)).isEqualTo(movementCount);
            assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM mall_order", Long.class)).isEqualTo(count);
            create(session, "/api/orders", orderInput(
                    List.of(Map.of("productId", first, "quantity", 1), Map.of("productId", first, "quantity", 1))),
                    400);
            create(session, "/api/orders", orderInput(List.of(Map.of("productId", first, "quantity", 0))), 400);
            create(session, "/api/orders", orderInput(List.of(Map.of("productId", first, "quantity", 0.1))), 400);
        } finally {
            jdbc.update("DELETE FROM stock_movement WHERE product_id IN (?, ?)", first, second);
            jdbc.update("DELETE FROM product WHERE id IN (?, ?)", first, second);
        }
    }

    @Test
    @Transactional
    void cancellationRestoresStockOnlyOnceAndOrderKeepsPriceSnapshot() throws Exception {
        var session = login("admin", "Admin@123456");
        long productId = create(session, "/api/products", productInput("快照商品", "SNAPSHOT", firstCategory(), "0.10", 10),
                201).get("id").asLong();
        var order = create(session, "/api/orders", orderInput(List.of(Map.of("productId", productId, "quantity", 3))),
                201);
        long orderId = order.get("id").asLong();
        assertThat(new BigDecimal(order.get("totalAmount").asText())).isEqualByComparingTo("0.30");
        assertThat(stock(productId)).isEqualTo(7);
        mvc.perform(put("/api/products/" + productId + "/status").session(session).header("X-CSRF-TOKEN", csrf(session))
                .contentType(MediaType.APPLICATION_JSON).content("{\"status\":\"OFF_SALE\"}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.stock").value(7));
        assertThat(stock(productId)).isEqualTo(7);
        jdbc.update("UPDATE product SET name = '改名商品', price = 99.00 WHERE id = ?", productId);
        mvc.perform(get("/api/orders/" + orderId).session(session)).andExpect(status().isOk())
                .andExpect(jsonPath("$.items[0].productName").value("快照商品"))
                .andExpect(jsonPath("$.items[0].unitPrice").value(0.1));
        mvc.perform(delete("/api/products/" + productId).session(session).header("X-CSRF-TOKEN", csrf(session)))
                .andExpect(status().isConflict());
        changeStatus(session, orderId, "PAID", 200);
        changeStatus(session, orderId, "CANCELLED", 200);
        assertThat(stock(productId)).isEqualTo(10);
        changeStatus(session, orderId, "CANCELLED", 409);
        assertThat(stock(productId)).isEqualTo(10);
    }

    @Test
    @Transactional
    void orderWorkflowAndSalesExcludeUnpaidAndCancelledOrders() throws Exception {
        var session = login("admin", "Admin@123456");
        long productId = create(session, "/api/products", productInput("销售商品", "SALES", firstCategory(), "12.30", 10),
                201).get("id").asLong();
        long id = create(session, "/api/orders", orderInput(List.of(Map.of("productId", productId, "quantity", 2))),
                201).get("id").asLong();
        changeStatus(session, id, "SHIPPED", 409);
        assertThat(dashboardSales(session)).isEqualByComparingTo("0.00");
        changeStatus(session, id, "PAID", 200);
        assertThat(dashboardSales(session)).isEqualByComparingTo("24.60");
        changeStatus(session, id, "SHIPPED", 200);
        changeStatus(session, id, "CANCELLED", 409);
        changeStatus(session, id, "COMPLETED", 200);
        changeStatus(session, id, "PAID", 409);
        mvc.perform(get("/api/orders").session(session).param("status", "COMPLETED").param("keyword", "测试客户"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.total").value(1));
    }

    @Test
    @Transactional
    void disabledAccountsAndChangedPasswordsInvalidateExistingSessions() throws Exception {
        var admin = login("admin", "Admin@123456");
        var created = create(admin, "/api/users", Map.of("username", "test_staff", "displayName", "测试员工", "password",
                "Original123!", "role", "STAFF", "enabled", true), 201);
        long id = created.get("id").asLong();
        var staff = login("test_staff", "Original123!");
        mvc.perform(put("/api/users/" + id).session(admin).header("X-CSRF-TOKEN", csrf(admin))
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(
                        Map.of("displayName", "测试员工", "role", "STAFF", "enabled", true, "password", "Changed123!"))))
                .andExpect(status().isOk());
        mvc.perform(get("/api/auth/me").session(staff)).andExpect(status().isUnauthorized());
        var updatedSession = login("test_staff", "Changed123!");
        mvc.perform(delete("/api/users/" + id).session(admin).header("X-CSRF-TOKEN", csrf(admin)))
                .andExpect(status().isNoContent());
        mvc.perform(get("/api/auth/me").session(updatedSession)).andExpect(status().isUnauthorized());
        long adminId = jdbc.queryForObject("SELECT id FROM mall_user WHERE username = 'admin'", Long.class);
        mvc.perform(delete("/api/users/" + adminId).session(admin).header("X-CSRF-TOKEN", csrf(admin)))
                .andExpect(status().isConflict());
        mvc.perform(put("/api/users/" + adminId).session(admin).header("X-CSRF-TOKEN", csrf(admin))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"displayName\":\"管理员\",\"role\":\"STAFF\",\"enabled\":true}"))
                .andExpect(status().isConflict());
    }

    @Test
    @Transactional
    void rerunningBootstrapDoesNotOverwriteExistingBusinessData() throws Exception {
        jdbc.update("UPDATE product SET stock = 17, name = '保留我的修改' WHERE sku = 'MOUSE-001'");
        jdbc.update("UPDATE mall_user SET display_name = '自定义管理员' WHERE username = 'admin'");
        initializer.run(new DefaultApplicationArguments(new String[0]));
        assertThat(jdbc.queryForObject("SELECT stock FROM product WHERE sku = 'MOUSE-001'", Integer.class))
                .isEqualTo(17);
        assertThat(jdbc.queryForObject("SELECT display_name FROM mall_user WHERE username = 'admin'", String.class))
                .isEqualTo("自定义管理员");
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM mall_user WHERE username = 'admin'", Long.class))
                .isEqualTo(1);
    }

    @Test
    void concurrentOrdersCannotOversellTheLastUnit() throws Exception {
        long categoryId = firstCategory();
        jdbc.update(
                "INSERT INTO product(name, sku, category_id, price, stock, status, description) VALUES ('并发商品', 'CONCURRENT', ?, 5.00, 1, 'ON_SALE', '')",
                categoryId);
        long productId = jdbc.queryForObject("SELECT id FROM product WHERE sku = 'CONCURRENT'", Long.class);
        var executor = Executors.newFixedThreadPool(2);
        var ready = new CountDownLatch(2);
        var start = new CountDownLatch(1);
        try {
            java.util.concurrent.Callable<Boolean> purchase = () -> {
                ready.countDown();
                if (!start.await(5, TimeUnit.SECONDS))
                    throw new IllegalStateException("并发测试未同时启动");
                try {
                    orders.create(
                            new OrderInput("并发客户", "13800138000", "测试地址", List.of(new OrderLineInput(productId, 1))));
                    return true;
                } catch (com.example.mall.common.BusinessException expected) {
                    return false;
                }
            };
            var first = executor.submit(purchase);
            var second = executor.submit(purchase);
            assertThat(ready.await(5, TimeUnit.SECONDS)).isTrue();
            start.countDown();
            int successes = (first.get(15, TimeUnit.SECONDS) ? 1 : 0) + (second.get(15, TimeUnit.SECONDS) ? 1 : 0);
            assertThat(successes).isEqualTo(1);
            assertThat(stock(productId)).isZero();
            assertThat(
                    jdbc.queryForObject("SELECT COUNT(*) FROM order_item WHERE product_id = ?", Long.class, productId))
                    .isEqualTo(1);
        } finally {
            executor.shutdownNow();
            var ids = jdbc.queryForList("SELECT order_id FROM order_item WHERE product_id = ?", Long.class, productId);
            jdbc.update("DELETE FROM order_item WHERE product_id = ?", productId);
            for (long id : ids)
                jdbc.update("DELETE FROM mall_order WHERE id = ?", id);
            jdbc.update("DELETE FROM stock_movement WHERE product_id = ?", productId);
            jdbc.update("DELETE FROM product WHERE id = ?", productId);
        }
    }

    @Test
    @Transactional
    void stockCanOnlyChangeThroughInventoryAndRetryDoesNotDuplicateMovement() throws Exception {
        var session = login("staff", "Staff@123456");
        var product = create(session, "/api/products", productInput("库存业务商品", "INVENTORY-ONLY", firstCategory(), "9.90", 0), 201);
        long id = product.get("id").asLong();
        assertThat(product.get("stock").asInt()).isZero();
        var inbound = Map.of("productId", id, "type", "INBOUND", "quantity", 20, "reason", "采购补货",
                "requestId", java.util.UUID.randomUUID().toString());
        create(session, "/api/inventory/movements", inbound, 201);
        create(session, "/api/inventory/movements", inbound, 201);
        assertThat(stock(id)).isEqualTo(20);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM stock_movement WHERE product_id = ?", Long.class, id)).isEqualTo(1);
        var changedRetry = new java.util.HashMap<>(inbound); changedRetry.put("quantity", 21);
        create(session, "/api/inventory/movements", changedRetry, 409);
        create(session, "/api/inventory/movements", Map.of("productId", id, "type", "OUTBOUND", "quantity", 3,
                "reason", "商品报损", "requestId", java.util.UUID.randomUUID().toString()), 201);
        create(session, "/api/inventory/movements", Map.of("productId", id, "type", "OUTBOUND", "quantity", 18,
                "reason", "商品报损", "requestId", java.util.UUID.randomUUID().toString()), 409);
        assertThat(stock(id)).isEqualTo(17);
        var update = new java.util.HashMap<String, Object>(Map.of("name", "改名不改库存", "sku", "INVENTORY-ONLY",
                "categoryId", firstCategory(), "price", 8, "status", "ON_SALE"));
        mvc.perform(put("/api/products/" + id).session(session).header("X-CSRF-TOKEN", csrf(session))
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(update)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.stock").value(17));
        update.put("stock", 999);
        mvc.perform(put("/api/products/" + id).session(session).header("X-CSRF-TOKEN", csrf(session))
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(update))).andExpect(status().isBadRequest());
        assertThat(stock(id)).isEqualTo(17);
        mvc.perform(get("/api/inventory/movements").session(session).param("keyword", "INVENTORY-ONLY").param("type", "OUTBOUND"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.total").value(1))
                .andExpect(jsonPath("$.items[0].beforeStock").value(20)).andExpect(jsonPath("$.items[0].afterStock").value(17))
                .andExpect(jsonPath("$.items[0].operator").value("staff"));
        mvc.perform(get("/api/inventory/movements")).andExpect(status().isUnauthorized());
        mvc.perform(post("/api/inventory/movements").session(session).contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(inbound))).andExpect(status().isForbidden());
        mvc.perform(get("/api/inventory/movements").session(session).param("start", "2026-10-30").param("end", "2026-10-01"))
                .andExpect(status().isBadRequest());
        mvc.perform(get("/api/inventory/movements").session(session).param("type", "INVALID"))
                .andExpect(status().isBadRequest());
        create(session, "/api/inventory/movements", Map.of("productId", id, "type", "INBOUND", "quantity", 0,
                "reason", "采购补货", "requestId", java.util.UUID.randomUUID().toString()), 400);
        create(session, "/api/inventory/movements", Map.of("productId", id, "type", "INBOUND", "quantity", 1,
                "reason", "订单扣减", "requestId", java.util.UUID.randomUUID().toString()), 400);
        mvc.perform(delete("/api/products/" + id).session(session).header("X-CSRF-TOKEN", csrf(session))).andExpect(status().isConflict());
    }

    @Test
    @Transactional
    void ordersRecordBothDeductionAndReturnAndNeverDeductOnShipping() throws Exception {
        var session = login("admin", "Admin@123456");
        long id = create(session, "/api/products", productInput("订单库存流水", "ORDER-LEDGER", firstCategory(), "5", 10), 201).get("id").asLong();
        var first = create(session, "/api/orders", orderInput(List.of(Map.of("productId", id, "quantity", 3))), 201);
        var second = create(session, "/api/orders", orderInput(List.of(Map.of("productId", id, "quantity", 2))), 201);
        changeStatus(session, first.get("id").asLong(), "PAID", 200);
        changeStatus(session, first.get("id").asLong(), "SHIPPED", 200);
        assertThat(stock(id)).isEqualTo(5);
        changeStatus(session, second.get("id").asLong(), "CANCELLED", 200);
        changeStatus(session, second.get("id").asLong(), "CANCELLED", 409);
        assertThat(stock(id)).isEqualTo(7);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM stock_movement WHERE product_id = ? AND type = 'ORDER_DEDUCT'", Long.class, id)).isEqualTo(2);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM stock_movement WHERE product_id = ? AND type = 'ORDER_RETURN'", Long.class, id)).isEqualTo(1);
    }

    @Test
    @Transactional
    void imageUploadValidatesContentAndPersistsReferenceWithoutChangingStock() throws Exception {
        var session = login("admin", "Admin@123456");
        var image = new java.awt.image.BufferedImage(8, 8, java.awt.image.BufferedImage.TYPE_INT_RGB);
        var buffer = new java.io.ByteArrayOutputStream();
        javax.imageio.ImageIO.write(image, "png", buffer);
        var file = new org.springframework.mock.web.MockMultipartFile("file", "photo.png", "image/png", buffer.toByteArray());
        mvc.perform(multipart("/api/product-images").file(file).session(session)).andExpect(status().isForbidden());
        var uploaded = json(mvc.perform(multipart("/api/product-images").file(file).session(session)
                .header("X-CSRF-TOKEN", csrf(session))).andExpect(status().isCreated()).andReturn());
        String url = uploaded.get("imageUrl").asText();
        mvc.perform(get(url)).andExpect(status().isUnauthorized());
        mvc.perform(get(url).session(session)).andExpect(status().isOk()).andExpect(content().contentType("image/png"));
        var input = new java.util.HashMap<String, Object>(Map.of("name", "带图片商品", "sku", "IMAGE-TEST",
                "categoryId", firstCategory(), "price", 9, "status", "OFF_SALE", "imageUrl", url));
        var product = create(session, "/api/products", input, 201);
        assertThat(product.get("stock").asInt()).isZero();
        assertThat(product.get("imageUrl").asText()).isEqualTo(url);
        mvc.perform(get("/api/products").session(session).param("keyword", "IMAGE-TEST"))
                .andExpect(jsonPath("$.items[0].imageUrl").value(url));
        input.put("imageUrl", "");
        mvc.perform(put("/api/products/" + product.get("id").asLong()).session(session).header("X-CSRF-TOKEN", csrf(session))
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(input)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.imageUrl").value(""));
        var fake = new org.springframework.mock.web.MockMultipartFile("file", "fake.png", "image/png", "<script>bad</script>".getBytes());
        mvc.perform(multipart("/api/product-images").file(fake).session(session).header("X-CSRF-TOKEN", csrf(session)))
                .andExpect(status().isBadRequest());
        for (String format : List.of("jpg", "webp")) {
            byte[] bytes;
            try (var resource = getClass().getResourceAsStream("/images/sample." + format)) { bytes = resource.readAllBytes(); }
            var sample = new org.springframework.mock.web.MockMultipartFile("file", "sample." + format, "application/octet-stream", bytes);
            var normalized = json(mvc.perform(multipart("/api/product-images").file(sample).session(session)
                    .header("X-CSRF-TOKEN", csrf(session))).andExpect(status().isCreated()).andReturn());
            mvc.perform(get(normalized.get("imageUrl").asText()).session(session)).andExpect(status().isOk())
                    .andExpect(content().contentType("image/png"));
        }
        mvc.perform(multipart("/api/product-images").session(session).header("X-CSRF-TOKEN", csrf(session)))
                .andExpect(status().isBadRequest());
        var large = new org.springframework.mock.web.MockMultipartFile("file", "large.png", "image/png", new byte[5 * 1024 * 1024 + 1]);
        mvc.perform(multipart("/api/product-images").file(large).session(session).header("X-CSRF-TOKEN", csrf(session)))
                .andExpect(status().isBadRequest());
        input.put("sku", "IMAGE-BAD"); input.put("imageUrl", "https://example.invalid/image.png");
        create(session, "/api/products", input, 400);
    }

    @Test
    void imageColumnMigrationIsRepeatableAndPreservesOldStock() {
        var source = new org.springframework.jdbc.datasource.DriverManagerDataSource("jdbc:h2:mem:oldstock;MODE=MySQL;DATABASE_TO_LOWER=TRUE;DB_CLOSE_DELAY=-1", "sa", "");
        var old = new JdbcTemplate(source);
        old.execute("CREATE TABLE product(id BIGINT PRIMARY KEY, stock INT)");
        old.update("INSERT INTO product(id, stock) VALUES (1, 37)");
        var migration = new com.example.mall.config.InventoryMigration(old);
        migration.run(new DefaultApplicationArguments(new String[0]));
        migration.run(new DefaultApplicationArguments(new String[0]));
        assertThat(old.queryForObject("SELECT stock FROM product WHERE id = 1", Integer.class)).isEqualTo(37);
        assertThat(old.queryForObject("SELECT image_url FROM product WHERE id = 1", String.class)).isEmpty();
    }

    @Test
    @Transactional
    void openingInventoryRecordsOldBalanceOnceWithoutChangingIt() {
        jdbc.update("INSERT INTO product(name, sku, category_id, price, stock, status, description) VALUES ('升级旧商品', 'OLD-BALANCE', ?, 1, 37, 'ON_SALE', '')", firstCategory());
        long id = jdbc.queryForObject("SELECT id FROM product WHERE sku = 'OLD-BALANCE'", Long.class);
        jdbc.update("DELETE FROM app_setting WHERE setting_key = 'stock_ledger_v1'");
        openingInventory.run(new DefaultApplicationArguments(new String[0]));
        openingInventory.run(new DefaultApplicationArguments(new String[0]));
        assertThat(stock(id)).isEqualTo(37);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM stock_movement WHERE product_id = ? AND type = 'OPENING'", Long.class, id)).isEqualTo(1);
    }

    @Test
    void concurrentManualOutboundCannotDeductTheSameLastUnitTwice() throws Exception {
        var session = login("admin", "Admin@123456");
        long id = create(session, "/api/products", productInput("并发出库", "CONCURRENT-OUT", firstCategory(), "1", 1), 201).get("id").asLong();
        var executor = Executors.newFixedThreadPool(2);
        var ready = new CountDownLatch(2);
        var start = new CountDownLatch(1);
        try {
            java.util.concurrent.Callable<Boolean> outbound = () -> {
                ready.countDown();
                if (!start.await(5, TimeUnit.SECONDS)) throw new IllegalStateException("并发出库未同时启动");
                try {
                    inventory.manual(new com.example.mall.model.Requests.StockInput(id, "OUTBOUND", 1, "商品报损", "", java.util.UUID.randomUUID().toString()));
                    return true;
                } catch (com.example.mall.common.BusinessException expected) { return false; }
            };
            var a = executor.submit(outbound); var b = executor.submit(outbound);
            assertThat(ready.await(5, TimeUnit.SECONDS)).isTrue(); start.countDown();
            assertThat((a.get(15, TimeUnit.SECONDS) ? 1 : 0) + (b.get(15, TimeUnit.SECONDS) ? 1 : 0)).isEqualTo(1);
            assertThat(stock(id)).isZero();
            assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM stock_movement WHERE product_id = ? AND type = 'OUTBOUND'", Long.class, id)).isEqualTo(1);
        } finally {
            executor.shutdownNow();
            jdbc.update("DELETE FROM stock_movement WHERE product_id = ?", id);
            jdbc.update("DELETE FROM product WHERE id = ?", id);
        }
    }

    private MockHttpSession login(String username, String password) throws Exception {
        var result = mvc.perform(get("/api/auth/csrf")).andReturn();
        var session = (MockHttpSession) result.getRequest().getSession(false);
        mvc.perform(post("/api/auth/login").session(session).header("X-CSRF-TOKEN", json(result).get("token").asText())
                .param("username", username).param("password", password)).andExpect(status().isOk());
        return session;
    }

    private String csrf(MockHttpSession session) throws Exception {
        return json(mvc.perform(get("/api/auth/csrf").session(session)).andExpect(status().isOk()).andReturn())
                .get("token").asText();
    }

    private JsonNode create(MockHttpSession session, String path, Object body, int expectedStatus) throws Exception {
        int initialStock = 0;
        if ("/api/products".equals(path) && body instanceof Map<?, ?> map && map.containsKey("testInitialStock")) {
            var input = new java.util.HashMap<>(map);
            initialStock = ((Number) input.remove("testInitialStock")).intValue();
            body = input;
        }
        var result = mvc
                .perform(post(path).session(session).header("X-CSRF-TOKEN", csrf(session))
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(body)))
                .andExpect(status().is(expectedStatus)).andReturn();
        JsonNode created = json(result);
        if (expectedStatus == 201 && "/api/products".equals(path) && initialStock > 0) {
            create(session, "/api/inventory/movements", Map.of("productId", created.get("id").asLong(), "type", "INBOUND",
                    "quantity", initialStock, "reason", "采购补货", "requestId", java.util.UUID.randomUUID().toString()), 201);
        }
        return created;
    }

    private void changeStatus(MockHttpSession session, long id, String status, int expectedStatus) throws Exception {
        mvc.perform(put("/api/orders/" + id + "/status").session(session).header("X-CSRF-TOKEN", csrf(session))
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(Map.of("status", status))))
                .andExpect(status().is(expectedStatus));
    }

    private JsonNode json(MvcResult result) throws Exception {
        return mapper.readTree(result.getResponse().getContentAsByteArray());
    }

    private long firstCategory() {
        return jdbc.queryForObject("SELECT MIN(id) FROM category", Long.class);
    }

    private int stock(long id) {
        return jdbc.queryForObject("SELECT stock FROM product WHERE id = ?", Integer.class, id);
    }

    private BigDecimal dashboardSales(MockHttpSession session) throws Exception {
        return new BigDecimal(
                json(mvc.perform(get("/api/dashboard").session(session)).andReturn()).get("totalSales").asText());
    }

    private Map<String, Object> productInput(String name, String sku, long category, String price, int stock) {
        return Map.of("name", name, "sku", sku, "categoryId", category, "price", new BigDecimal(price), "testInitialStock", stock,
                "status", "ON_SALE", "description", "测试商品");
    }

    private Map<String, Object> orderInput(List<Map<String, Object>> items) {
        return Map.of("customerName", "测试客户", "phone", "13800138000", "address", "测试收货地址", "items", items);
    }
}
