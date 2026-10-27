# backend/src/main/java/com/example/mall/config/InventoryMigration.java

源文件：[`backend/src/main/java/com/example/mall/config/InventoryMigration.java`](../../商城管理系统/backend/src/main/java/com/example/mall/config/InventoryMigration.java)。空行计入行号。

兼容旧数据库，只补缺失的图片列，不重建商品表。

库存与图片的业务示例、整体流程见 [新功能说明](../库存管理与商品图片.md)。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>package com.example.mall.config;</code> | 声明 Java 包，按控制器、业务、数据访问等职责组织类。 |
| 2 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 3 | <code>import org.springframework.boot.ApplicationArguments;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 4 | <code>import org.springframework.boot.ApplicationRunner;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 5 | <code>import org.springframework.core.annotation.Order;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 6 | <code>import org.springframework.jdbc.core.JdbcTemplate;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 7 | <code>import org.springframework.jdbc.core.ConnectionCallback;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 8 | <code>import org.springframework.stereotype.Component;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 9 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 10 | <code>/** 非破坏性升级：只补缺失的图片列，已有商品、库存和订单不重建。 */</code> | 代码注释，说明设计意图，不直接执行。 |
| 11 | <code>@Component</code> | 向 Spring 或 Java 声明本行对应类/方法的用途，结合后面的定义生效。 |
| 12 | <code>@Order(-100)</code> | 设置启动任务执行顺序，确保先补图片列，再初始化演示数据，最后登记旧库存基线。 |
| 13 | <code>public class InventoryMigration implements ApplicationRunner {</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 14 | <code>    private final JdbcTemplate jdbc;</code> | 声明构造后不再替换的依赖字段，由构造器接收 Spring 管理的对象。 |
| 15 | <code>    public InventoryMigration(JdbcTemplate jdbc) { this.jdbc = jdbc; }</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 16 | <code>    @Override</code> | 向 Spring 或 Java 声明本行对应类/方法的用途，结合后面的定义生效。 |
| 17 | <code>    public void run(ApplicationArguments args) {</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 18 | <code>        boolean exists = Boolean.TRUE.equals(jdbc.execute((ConnectionCallback&lt;Boolean&gt;) connection -&gt; {</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 19 | <code>            try (var columns = connection.getMetaData().getColumns(connection.getCatalog(), null, &quot;product&quot;, &quot;image_url&quot;)) {</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 20 | <code>                return columns.next();</code> | 返回本行表达式的结果，交给调用方或作为接口响应。 |
| 21 | <code>            }</code> | 结束当前作用域、参数、回调或集合定义。 |
| 22 | <code>        }));</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 23 | <code>        if (!exists) jdbc.execute(&quot;ALTER TABLE product ADD COLUMN image_url VARCHAR(255) NOT NULL DEFAULT &#x27;&#x27;&quot;);</code> | 为商品增加可选图片地址字段，空字符串代表没有上传主图。 |
| 24 | <code>    }</code> | 结束当前作用域、参数、回调或集合定义。 |
| 25 | <code>}</code> | 结束当前作用域、参数、回调或集合定义。 |
