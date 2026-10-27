# backend/src/main/java/com/example/mall/config/OpeningInventory.java

源文件：[`backend/src/main/java/com/example/mall/config/OpeningInventory.java`](../../商城管理系统/backend/src/main/java/com/example/mall/config/OpeningInventory.java)。空行计入行号。

为旧商品登记升级时的库存基线，不修改现存库存。

库存与图片的业务示例、整体流程见 [新功能说明](../库存管理与商品图片.md)。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>package com.example.mall.config;</code> | 声明 Java 包，按控制器、业务、数据访问等职责组织类。 |
| 2 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 3 | <code>import org.springframework.boot.ApplicationArguments;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 4 | <code>import org.springframework.boot.ApplicationRunner;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 5 | <code>import org.springframework.core.annotation.Order;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 6 | <code>import org.springframework.jdbc.core.JdbcTemplate;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 7 | <code>import org.springframework.stereotype.Component;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 8 | <code>import org.springframework.transaction.annotation.Transactional;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 9 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 10 | <code>/** 旧库存只登记升级时基线，不伪造历史入库，也不改变现存数量。 */</code> | 代码注释，说明设计意图，不直接执行。 |
| 11 | <code>@Component</code> | 向 Spring 或 Java 声明本行对应类/方法的用途，结合后面的定义生效。 |
| 12 | <code>@Order(100)</code> | 设置启动任务执行顺序，确保先补图片列，再初始化演示数据，最后登记旧库存基线。 |
| 13 | <code>public class OpeningInventory implements ApplicationRunner {</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 14 | <code>    private final JdbcTemplate jdbc;</code> | 声明构造后不再替换的依赖字段，由构造器接收 Spring 管理的对象。 |
| 15 | <code>    public OpeningInventory(JdbcTemplate jdbc) { this.jdbc = jdbc; }</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 16 | <code>    @Override</code> | 向 Spring 或 Java 声明本行对应类/方法的用途，结合后面的定义生效。 |
| 17 | <code>    @Transactional</code> | 声明事务边界：库存、订单和流水相关数据库操作要么一起提交，要么失败回滚。 |
| 18 | <code>    public void run(ApplicationArguments args) {</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 19 | <code>        if (jdbc.queryForObject(&quot;SELECT COUNT(*) FROM app_setting WHERE setting_key = &#x27;stock_ledger_v1&#x27;&quot;, Long.class) &gt; 0) return;</code> | 使用数据库版本标记控制期初流水只登记一次，重启不重复执行。 |
| 20 | <code>        jdbc.update(&quot;INSERT INTO stock_movement(product_id, product_name, sku, type, delta, before_stock, after_stock, reference, reason, remark, operator) SELECT p.id, p.name, p.sku, &#x27;OPENING&#x27;, p.stock, 0, p.stock, &#x27;MIGRATION-V1&#x27;, &#x27;升级期初库存&#x27;, &#x27;仅记录升级时数量，不代表历史采购入库&#x27;, &#x27;system&#x27; FROM product p WHERE p.stock &lt;&gt; 0 AND NOT EXISTS (SELECT 1 FROM stock_movement m WHERE m.product_id = p.id)&quot;);</code> | 给旧商品写升级基线记录；只补记录，实际库存保持原值。 |
| 21 | <code>        jdbc.update(&quot;INSERT INTO app_setting(setting_key, setting_value) VALUES (&#x27;stock_ledger_v1&#x27;, &#x27;complete&#x27;)&quot;);</code> | 使用数据库版本标记控制期初流水只登记一次，重启不重复执行。 |
| 22 | <code>    }</code> | 结束当前作用域、参数、回调或集合定义。 |
| 23 | <code>}</code> | 结束当前作用域、参数、回调或集合定义。 |
