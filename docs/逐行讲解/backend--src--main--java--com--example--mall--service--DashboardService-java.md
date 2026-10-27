# backend/src/main/java/com/example/mall/service/DashboardService.java

源文件：[`backend/src/main/java/com/example/mall/service/DashboardService.java`](../../商城管理系统/backend/src/main/java/com/example/mall/service/DashboardService.java)。以下行号以当前文件为准，空行也计入。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>package com.example.mall.service;</code> | 声明 Java 包名 `com.example.mall.service`，用于组织类并区分同名类型。 |
| 2 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 3 | <code>import com.example.mall.model.Models.Dashboard;</code> | 导入 `com.example.mall.model.Models.Dashboard`：让当前类可以直接使用该类型。 |
| 4 | <code>import com.example.mall.repository.OrderRepository;</code> | 导入 `com.example.mall.repository.OrderRepository`：让当前类可以直接使用该类型。 |
| 5 | <code>import com.example.mall.repository.ProductRepository;</code> | 导入 `com.example.mall.repository.ProductRepository`：让当前类可以直接使用该类型。 |
| 6 | <code>import java.math.BigDecimal;</code> | 导入 `java.math.BigDecimal`：使用十进制数处理金额，避免二进制浮点误差。 |
| 7 | <code>import org.springframework.jdbc.core.JdbcTemplate;</code> | 导入 `org.springframework.jdbc.core.JdbcTemplate`：执行参数化 SQL 并把结果映射为 Java 值。 |
| 8 | <code>import org.springframework.stereotype.Service;</code> | 导入 `org.springframework.stereotype.Service`：将业务类注册为 Spring 服务。 |
| 9 | <code>import org.springframework.transaction.annotation.Transactional;</code> | 导入 `org.springframework.transaction.annotation.Transactional`：声明方法或类的数据库事务边界。 |
| 10 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 11 | <code>@Service</code> | 把此类注册为业务服务，供构造器注入使用。 |
| 12 | <code>public class DashboardService {</code> | 声明 类 `DashboardService`，后续内容定义它的字段或行为。 |
| 13 | <code>    private final JdbcTemplate jdbc;</code> | 声明类型为 `JdbcTemplate` 的 常量/依赖字段 `jdbc`；通过构造器或框架完成赋值。 |
| 14 | <code>    private final OrderRepository orders;</code> | 声明类型为 `OrderRepository` 的 常量/依赖字段 `orders`；通过构造器或框架完成赋值。 |
| 15 | <code>    private final ProductRepository products;</code> | 声明类型为 `ProductRepository` 的 常量/依赖字段 `products`；通过构造器或框架完成赋值。 |
| 16 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 17 | <code>    public DashboardService(JdbcTemplate jdbc, OrderRepository orders, ProductRepository products) {</code> | 声明 `DashboardService` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 18 | <code>        this.jdbc = jdbc;</code> | 把构造器传入的依赖/字段保存到当前对象；this.jdbc = jdbc;，后续实例方法通过 this 的成员使用它。 |
| 19 | <code>        this.orders = orders;</code> | 把构造器传入的依赖/字段保存到当前对象；this.orders = orders;，后续实例方法通过 this 的成员使用它。 |
| 20 | <code>        this.products = products;</code> | 把构造器传入的依赖/字段保存到当前对象；this.products = products;，后续实例方法通过 this 的成员使用它。 |
| 21 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 22 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 23 | <code>    @Transactional(readOnly = true)</code> | 为下方业务操作开启事务，运行时异常时回滚数据库修改。括号内参数为 `readOnly = true`。 |
| 24 | <code>    public Dashboard overview() {</code> | 声明 `overview` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 25 | <code>        return new Dashboard(count(&quot;SELECT COUNT(*) FROM product&quot;), count(&quot;SELECT COUNT(*) FROM category&quot;),</code> | 把 `new Dashboard(count("SELECT COUNT(*) FROM product"), count("SELECT COUNT(*) FROM category"),` 返回给调用方，并结束当前函数/方法。 |
| 26 | <code>                count(&quot;SELECT COUNT(*) FROM mall_order&quot;), count(&quot;SELECT COUNT(*) FROM product WHERE stock &lt;= 5&quot;),</code> | 统计全部订单数与库存不超过 5 的商品数，分别作为概览指标。 |
| 27 | <code>                jdbc.queryForObject(</code> | 执行参数化查询并映射结果：`jdbc.queryForObject(`。? 占位符对应后续传入参数。 |
| 28 | <code>                        &quot;SELECT COALESCE(SUM(total_amount), 0) FROM mall_order WHERE status IN (&#x27;PAID&#x27;, &#x27;SHIPPED&#x27;, &#x27;COMPLETED&#x27;)&quot;,</code> | 提供 SQL 文本片段 `"SELECT COALESCE(SUM(total_amount), 0) FROM mall_order WHERE status IN ('PAID', 'SHIPPED', 'COMPLETED')",`；? 由 JDBC 参数绑定，FOR UPDATE 用于事务行锁，LIMIT/OFFSET 用于分页（出现时生效）。 |
| 29 | <code>                        BigDecimal.class),</code> | 让销售额聚合查询按 BigDecimal 返回，保持金额十进制精度。 |
| 30 | <code>                count(&quot;SELECT COUNT(*) FROM mall_order WHERE status = &#x27;PENDING&#x27;&quot;), orders.recentOrders(),</code> | 统计待支付订单，并取得最近六笔订单，供工作台待办与列表使用。 |
| 31 | <code>                products.lowStockProducts());</code> | 读取低库存商品用于概览提醒；本行传入 `products.lowStockProducts());`。 |
| 32 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 33 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 34 | <code>    private long count(String sql) {</code> | 声明 `count` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 35 | <code>        return jdbc.queryForObject(sql, Long.class);</code> | 把 `jdbc.queryForObject(sql, Long.class)` 返回给调用方，并结束当前函数/方法。 |
| 36 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 37 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
