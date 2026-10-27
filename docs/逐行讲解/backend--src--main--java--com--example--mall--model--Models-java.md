# backend/src/main/java/com/example/mall/model/Models.java

源文件：[`backend/src/main/java/com/example/mall/model/Models.java`](../../商城管理系统/backend/src/main/java/com/example/mall/model/Models.java)。空行计入行号。

本文件按当前源码重新校对行号；未改变代码的已有讲解予以保留。

库存与图片的业务示例、整体流程见 [新功能说明](../库存管理与商品图片.md)。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>package com.example.mall.model;</code> | 声明 Java 包名 `com.example.mall.model`，用于组织类并区分同名类型。 |
| 2 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 3 | <code>import com.fasterxml.jackson.annotation.JsonIgnore;</code> | 导入 `com.fasterxml.jackson.annotation.JsonIgnore`：让当前类可以直接使用该类型。 |
| 4 | <code>import java.math.BigDecimal;</code> | 导入 `java.math.BigDecimal`：使用十进制数处理金额，避免二进制浮点误差。 |
| 5 | <code>import java.time.LocalDateTime;</code> | 导入 `java.time.LocalDateTime`：表示不含时区的日期时间。 |
| 6 | <code>import java.util.List;</code> | 导入 `java.util.List`：使用有序集合。 |
| 7 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 8 | <code>/** 返回给前端的数据结构；密码摘要绝不进入 API 响应。 */</code> | 块注释中的说明：返回给前端的数据结构；密码摘要绝不进入 API 响应。；不参与运行。 |
| 9 | <code>public final class Models {</code> | 声明 类 `Models`，后续内容定义它的字段或行为。 |
| 10 | <code>    private Models() {</code> | 声明 `Models` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 11 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 12 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 13 | <code>    public record User(long id, String username, String displayName, String role, boolean enabled) {</code> | 声明 不可变数据载体 record `User`，后续内容定义它的字段或行为。 |
| 14 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 15 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 16 | <code>    public record UserAccount(User user, @JsonIgnore String passwordHash) {</code> | 声明 不可变数据载体 record `UserAccount`，后续内容定义它的字段或行为。 |
| 17 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 18 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 19 | <code>    public record Category(long id, String name, String description) {</code> | 声明 不可变数据载体 record `Category`，后续内容定义它的字段或行为。 |
| 20 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 21 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 22 | <code>    public record Product(long id, String name, String sku, long categoryId, String categoryName, BigDecimal price,</code> | 声明 不可变数据载体 record `Product`，后续内容定义它的字段或行为。 |
| 23 | <code>            int stock, String status, String description, LocalDateTime createdAt, String imageUrl) {</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 24 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 25 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 26 | <code>    public record StockMovement(long id, long productId, String productName, String sku, String type, int delta,</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 27 | <code>            int beforeStock, int afterStock, String reference, String reason, String remark, String operator,</code> | 计算或展示表单预估的变更后库存；后端仍会重新读取真实数量，不能只信页面预估。 |
| 28 | <code>            LocalDateTime createdAt) {</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 29 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 30 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 31 | <code>    public record OrderItem(long productId, String productName, BigDecimal unitPrice, int quantity,</code> | 声明 不可变数据载体 record `OrderItem`，后续内容定义它的字段或行为。 |
| 32 | <code>            BigDecimal lineTotal) {</code> | 继续声明参数/record 字段：`BigDecimal lineTotal) {`。`lineTotal`：该商品行金额；这些类型决定 JSON/方法参数能保存什么数据。 |
| 33 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 34 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 35 | <code>    public record Order(long id, String orderNo, String customerName, String phone, String address, String status,</code> | 声明 不可变数据载体 record `Order`，后续内容定义它的字段或行为。 |
| 36 | <code>            BigDecimal totalAmount, LocalDateTime createdAt, List&lt;OrderItem&gt; items) {</code> | 继续声明参数/record 字段：`BigDecimal totalAmount, LocalDateTime createdAt, List<OrderItem> items) {`。`totalAmount`：订单总额；`createdAt`：创建时间；`items`：列表条目或订单明细；这些类型决定 JSON/方法参数能保存什么数据。 |
| 37 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 38 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 39 | <code>    public record Page&lt;T&gt;(List&lt;T&gt; items, long total, int page, int size) {</code> | 声明 不可变数据载体 record `Page`，后续内容定义它的字段或行为。 |
| 40 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 41 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 42 | <code>    public record Dashboard(long productCount, long categoryCount, long orderCount, long lowStockCount,</code> | 声明 不可变数据载体 record `Dashboard`，后续内容定义它的字段或行为。 |
| 43 | <code>            BigDecimal totalSales, long pendingOrderCount, List&lt;Order&gt; recentOrders, List&lt;Product&gt; lowStockProducts) {</code> | 继续声明参数/record 字段：`BigDecimal totalSales, long pendingOrderCount, List<Order> recentOrders, List<Product> lowStockProducts) {`。`totalSales`：有效销售金额；`pendingOrderCount`：待支付订单数；`recentOrders`：最近订单列表；`lowStockProducts`：低库存商品列表；这些类型决定 JSON/方法参数能保存什么数据。 |
| 44 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 45 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
