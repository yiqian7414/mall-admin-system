# backend/src/main/java/com/example/mall/repository/OrderRepository.java

源文件：[`backend/src/main/java/com/example/mall/repository/OrderRepository.java`](../../商城管理系统/backend/src/main/java/com/example/mall/repository/OrderRepository.java)。以下行号以当前文件为准，空行也计入。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>package com.example.mall.repository;</code> | 声明 Java 包名 `com.example.mall.repository`，用于组织类并区分同名类型。 |
| 2 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 3 | <code>import com.example.mall.model.Models.Order;</code> | 导入 `com.example.mall.model.Models.Order`：让当前类可以直接使用该类型。 |
| 4 | <code>import com.example.mall.model.Models.OrderItem;</code> | 导入 `com.example.mall.model.Models.OrderItem`：让当前类可以直接使用该类型。 |
| 5 | <code>import com.example.mall.model.Models.Page;</code> | 导入 `com.example.mall.model.Models.Page`：让当前类可以直接使用该类型。 |
| 6 | <code>import com.example.mall.model.Requests.OrderInput;</code> | 导入 `com.example.mall.model.Requests.OrderInput`：让当前类可以直接使用该类型。 |
| 7 | <code>import java.math.BigDecimal;</code> | 导入 `java.math.BigDecimal`：使用十进制数处理金额，避免二进制浮点误差。 |
| 8 | <code>import java.sql.PreparedStatement;</code> | 导入 `java.sql.PreparedStatement`：让当前类可以直接使用该类型。 |
| 9 | <code>import java.util.ArrayList;</code> | 导入 `java.util.ArrayList`：创建可增长的列表。 |
| 10 | <code>import java.util.List;</code> | 导入 `java.util.List`：使用有序集合。 |
| 11 | <code>import java.util.Optional;</code> | 导入 `java.util.Optional`：明确表达结果可能不存在。 |
| 12 | <code>import org.springframework.jdbc.core.JdbcTemplate;</code> | 导入 `org.springframework.jdbc.core.JdbcTemplate`：执行参数化 SQL 并把结果映射为 Java 值。 |
| 13 | <code>import org.springframework.jdbc.core.RowMapper;</code> | 导入 `org.springframework.jdbc.core.RowMapper`：定义一行数据库记录如何转为 Java 对象。 |
| 14 | <code>import org.springframework.jdbc.support.GeneratedKeyHolder;</code> | 导入 `org.springframework.jdbc.support.GeneratedKeyHolder`：让当前类可以直接使用该类型。 |
| 15 | <code>import org.springframework.stereotype.Repository;</code> | 导入 `org.springframework.stereotype.Repository`：将数据访问类注册为 Spring 仓储组件。 |
| 16 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 17 | <code>@Repository</code> | 把此类注册为数据访问组件。 |
| 18 | <code>public class OrderRepository {</code> | 声明 类 `OrderRepository`，后续内容定义它的字段或行为。 |
| 19 | <code>    private final JdbcTemplate jdbc;</code> | 保存 Spring 注入的 JdbcTemplate，它复用数据源连接并执行参数化 SQL；本类不拼接用户值到 SQL。 |
| 20 | <code>    private static final RowMapper&lt;Order&gt; MAPPER = (rs, row) -&gt; new Order(rs.getLong(&quot;id&quot;), rs.getString(&quot;order_no&quot;),</code> | 定义可复用的数据库行映射 Lambda：rs 是当前 ResultSet 行，row 是行号，后续把列转换为对应返回对象。 |
| 21 | <code>            rs.getString(&quot;customer_name&quot;), rs.getString(&quot;phone&quot;), rs.getString(&quot;address&quot;), rs.getString(&quot;status&quot;),</code> | 读取本行数据库列 `customer_name`、`phone`、`address`、`status`，按构造器顺序填入当前行映射对象。 |
| 22 | <code>            rs.getBigDecimal(&quot;total_amount&quot;), rs.getTimestamp(&quot;created_at&quot;).toLocalDateTime(), List.of());</code> | 读取本行数据库列 `total_amount`、`created_at`，按构造器顺序填入当前行映射对象。getBigDecimal 保留十进制金额精度。把 created_at 的 SQL Timestamp 转为 LocalDateTime。此时明细先为空列表，withItems 后续另查订单明细。 |
| 23 | <code>    private static final RowMapper&lt;OrderItem&gt; ITEM_MAPPER = (rs, row) -&gt; new OrderItem(rs.getLong(&quot;product_id&quot;),</code> | 定义可复用的数据库行映射 Lambda：rs 是当前 ResultSet 行，row 是行号，后续把列转换为对应返回对象。 |
| 24 | <code>            rs.getString(&quot;product_name&quot;), rs.getBigDecimal(&quot;unit_price&quot;), rs.getInt(&quot;quantity&quot;),</code> | 读取本行数据库列 `product_name`、`unit_price`、`quantity`，按构造器顺序填入当前行映射对象。getBigDecimal 保留十进制金额精度。 |
| 25 | <code>            rs.getBigDecimal(&quot;line_total&quot;));</code> | 读取本行数据库列 `line_total`，按构造器顺序填入当前行映射对象。getBigDecimal 保留十进制金额精度。 |
| 26 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 27 | <code>    public OrderRepository(JdbcTemplate jdbc) {</code> | 声明 `OrderRepository` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 28 | <code>        this.jdbc = jdbc;</code> | 把构造器传入的依赖/字段保存到当前对象；this.jdbc = jdbc;，后续实例方法通过 this 的成员使用它。 |
| 29 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 30 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 31 | <code>    public Optional&lt;Order&gt; findById(long id) {</code> | 声明 `findById` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 32 | <code>        return jdbc.query(&quot;SELECT * FROM mall_order WHERE id = ?&quot;, MAPPER, id).stream().findFirst()</code> | 按指定 id 参数精确查询一条记录；RowMapper 转对象，stream().findFirst() 用 Optional 表示未找到。 |
| 33 | <code>                .map(this::withItems);</code> | 找到订单时调用 withItems 查询并补齐明细；不存在时 Optional 仍为空。 |
| 34 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 35 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 36 | <code>    public Optional&lt;Order&gt; lockById(long id) {</code> | 声明 `lockById` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 37 | <code>        return jdbc.query(&quot;SELECT * FROM mall_order WHERE id = ? FOR UPDATE&quot;, MAPPER, id).stream().findFirst()</code> | 执行 SELECT ... FOR UPDATE，在当前事务中锁定匹配行；用户管理员查询按 ID 排序锁定，商品/订单查询按指定主键锁定。 |
| 38 | <code>                .map(this::withItems);</code> | 找到订单时调用 withItems 查询并补齐明细；不存在时 Optional 仍为空。 |
| 39 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 40 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 41 | <code>    public Page&lt;Order&gt; findPage(String keyword, String status, int page, int size) {</code> | 声明 `findPage` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 42 | <code>        var where = new StringBuilder(&quot; WHERE 1 = 1&quot;);</code> | 创建可追加的固定查询条件；WHERE 1=1 恒成立，后续每个可选筛选都可统一追加 AND。 |
| 43 | <code>        var parameters = new ArrayList&lt;Object&gt;();</code> | 创建按 SQL ? 顺序保存查询参数的列表；参数与 SQL 结构分开，防止注入。 |
| 44 | <code>        if (keyword != null &amp;&amp; !keyword.isBlank()) {</code> | 只有用户填写了非空关键词才追加模糊搜索；空关键词表示不按文字筛选。 |
| 45 | <code>            where.append(</code> | 开始追加订单关键词 SQL，下一行同时匹配订单号、收货人和电话。 |
| 46 | <code>                    &quot; AND (LOWER(order_no) LIKE ? ESCAPE &#x27;!&#x27; OR LOWER(customer_name) LIKE ? ESCAPE &#x27;!&#x27; OR phone LIKE ? ESCAPE &#x27;!&#x27;)&quot;);</code> | 固定 SQL：订单号/收货人按小写匹配，电话按原文匹配，任意一个 LIKE 条件成立即可；每个 ? 都绑定同一转义关键词。 |
| 47 | <code>            String pattern = &quot;%&quot; + ProductRepository.escapeLike(keyword.trim().toLowerCase(java.util.Locale.ROOT))</code> | 把关键词去空白、转小写并转义 !/%/_，再加两侧 % 做包含搜索；使用 Locale.ROOT 保证大小写行为不依赖机器语言。 |
| 48 | <code>                    + &quot;%&quot;;</code> | 在转义后的关键词末尾添加 LIKE 通配符，与前面的 % 一起实现包含匹配。 |
| 49 | <code>            parameters.add(pattern);</code> | 按当前 LIKE 占位符顺序加入转义后的同一关键词；同一行重复调用对应名称/SKU或订单号/姓名/电话的不同 ?。 |
| 50 | <code>            parameters.add(pattern);</code> | 按当前 LIKE 占位符顺序加入转义后的同一关键词；同一行重复调用对应名称/SKU或订单号/姓名/电话的不同 ?。 |
| 51 | <code>            parameters.add(pattern);</code> | 按当前 LIKE 占位符顺序加入转义后的同一关键词；同一行重复调用对应名称/SKU或订单号/姓名/电话的不同 ?。 |
| 52 | <code>        }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 53 | <code>        if (status != null &amp;&amp; !status.isBlank()) {</code> | 只有传入非空状态时才追加状态筛选，状态是否合法已由业务层检查。 |
| 54 | <code>            where.append(&quot; AND status = ?&quot;);</code> | 追加 status = ? 固定条件，下一行传入上架/订单状态。 |
| 55 | <code>            parameters.add(status);</code> | 绑定前面的状态占位符。 |
| 56 | <code>        }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 57 | <code>        long total = jdbc.queryForObject(&quot;SELECT COUNT(*) FROM mall_order&quot; + where, Long.class, parameters.toArray());</code> | 先用完全相同筛选条件 COUNT(*) 取得结果总条数；此时尚未加入 LIMIT/OFFSET，所以 total 是全部匹配数量而非当前页长度。 |
| 58 | <code>        parameters.add(size);</code> | 将每页条数追加为 LIMIT ? 参数。 |
| 59 | <code>        parameters.add((long) (page - 1) * size);</code> | 计算 OFFSET=(页码-1)×每页条数；先转 long 避免 int 乘法溢出，page=1 时偏移为0。 |
| 60 | <code>        var orders = jdbc.query(&quot;SELECT * FROM mall_order&quot; + where + &quot; ORDER BY id DESC LIMIT ? OFFSET ?&quot;, MAPPER,</code> | 使用同一筛选条件按 ID 倒序取当前页，LIMIT/OFFSET 使用最后两个参数；RowMapper 把数据库行映射为对象。 |
| 61 | <code>                parameters.toArray());</code> | 把按占位符顺序保存的参数转换为数组，结束 JdbcTemplate 查询调用。 |
| 62 | <code>        return new Page&lt;&gt;(orders.stream().map(this::withItems).toList(), total, page, size);</code> | 组装分页响应 items/total/page/size；返回前调用 withItems 补齐每笔订单明细；total 表示全部筛选结果数。 |
| 63 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 64 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 65 | <code>    public long insert(String orderNo, OrderInput input, BigDecimal total) {</code> | 声明 `insert` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 66 | <code>        var keys = new GeneratedKeyHolder();</code> | 创建主键收集器，保存 INSERT 后数据库生成的 id，避免自行猜测主键。 |
| 67 | <code>        jdbc.update(connection -&gt; {</code> | 交给 JdbcTemplate 在当前数据源/事务连接上创建 PreparedStatement，并执行插入；回调随后返回语句对象。 |
| 68 | <code>            PreparedStatement statement = connection.prepareStatement(</code> | 通过 JDBC Connection 创建预编译语句；下一行给出固定 INSERT 与返回主键列。 |
| 69 | <code>                    &quot;INSERT INTO mall_order(order_no, customer_name, phone, address, status, total_amount) VALUES (?, ?, ?, ?, &#x27;PENDING&#x27;, ?)&quot;,</code> | 提供 SQL 文本片段 `"INSERT INTO mall_order(order_no, customer_name, phone, address, status, total_amount) VALUES (?, ?, ?, ?, 'PENDING', ?)",`；? 由 JDBC 参数绑定，FOR UPDATE 用于事务行锁，LIMIT/OFFSET 用于分页（出现时生效）。 |
| 70 | <code>                    new String[] { &quot;id&quot; });</code> | 明确要求 JDBC 只返回名为 id 的生成主键列，保证 H2/MySQL 的主键读取一致。 |
| 71 | <code>            statement.setString(1, orderNo);</code> | 给预编译 SQL 的第 1 个 ? 占位符绑定参数 `orderNo`；按类型传值而不拼接用户输入。 |
| 72 | <code>            statement.setString(2, input.customerName().trim());</code> | 给预编译 SQL 的第 2 个 ? 占位符绑定参数 `input.customerName().trim()`；按类型传值而不拼接用户输入。 |
| 73 | <code>            statement.setString(3, input.phone().trim());</code> | 给预编译 SQL 的第 3 个 ? 占位符绑定参数 `input.phone().trim()`；按类型传值而不拼接用户输入。 |
| 74 | <code>            statement.setString(4, input.address().trim());</code> | 给预编译 SQL 的第 4 个 ? 占位符绑定参数 `input.address().trim()`；按类型传值而不拼接用户输入。 |
| 75 | <code>            statement.setBigDecimal(5, total);</code> | 给预编译 SQL 的第 5 个 ? 占位符绑定参数 `total`；按类型传值而不拼接用户输入。 |
| 76 | <code>            return statement;</code> | 把绑定好全部 ? 参数的 PreparedStatement 交还 JdbcTemplate 执行。 |
| 77 | <code>        }, keys);</code> | 结束创建 PreparedStatement 的回调，并把自动生成的主键写入 keys。 |
| 78 | <code>        return keys.getKey().longValue();</code> | 读取刚插入记录的唯一 id 主键，并转换为 long 返回服务层。 |
| 79 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 80 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 81 | <code>    public void insertItem(long orderId, OrderItem item) {</code> | 声明 `insertItem` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 82 | <code>        jdbc.update(</code> | 执行修改 SQL，返回影响行数：`jdbc.update(`。? 占位符对应后续传入参数。 |
| 83 | <code>                &quot;INSERT INTO order_item(order_id, product_id, product_name, unit_price, quantity, line_total) VALUES (?, ?, ?, ?, ?, ?)&quot;,</code> | 提供 SQL 文本片段 `"INSERT INTO order_item(order_id, product_id, product_name, unit_price, quantity, line_total) VALUES (?, ?, ?, ?, ?, ?)",`；? 由 JDBC 参数绑定，FOR UPDATE 用于事务行锁，LIMIT/OFFSET 用于分页（出现时生效）。 |
| 84 | <code>                orderId, item.productId(), item.productName(), item.unitPrice(), item.quantity(), item.lineTotal());</code> | 依次绑定订单 ID、商品 ID、下单名称快照、单价快照、数量、行金额，对应 INSERT 的六个 ?。 |
| 85 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 86 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 87 | <code>    public void updateStatus(long id, String status) {</code> | 声明 `updateStatus` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 88 | <code>        jdbc.update(&quot;UPDATE mall_order SET status = ? WHERE id = ?&quot;, status, id);</code> | 按订单 ID 保存经过业务层状态流转检查后的新状态，与取消归还库存处于同一事务。 |
| 89 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 90 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 91 | <code>    public List&lt;Order&gt; recentOrders() {</code> | 声明 `recentOrders` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 92 | <code>        return jdbc.query(&quot;SELECT * FROM mall_order ORDER BY id DESC LIMIT 6&quot;, MAPPER).stream().map(this::withItems)</code> | 按订单 ID 倒序读取最新6笔订单，再映射补齐每笔订单明细。 |
| 93 | <code>                .toList();</code> | 把 Stream 的映射/排序结果收集为列表，供后续 JSON 返回或遍历。 |
| 94 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 95 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 96 | <code>    private Order withItems(Order order) {</code> | 声明 `withItems` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 97 | <code>        List&lt;OrderItem&gt; items = jdbc.query(&quot;SELECT * FROM order_item WHERE order_id = ? ORDER BY id&quot;, ITEM_MAPPER,</code> | 按 order_id 查询该订单全部明细，按明细ID排序，并通过 ITEM_MAPPER 读取名称/单价快照与数量。 |
| 98 | <code>                order.id());</code> | 把订单主键绑定到 order_id = ?，查询当前订单的明细。 |
| 99 | <code>        return new Order(order.id(), order.orderNo(), order.customerName(), order.phone(), order.address(),</code> | 把 `new Order(order.id(), order.orderNo(), order.customerName(), order.phone(), order.address(),` 返回给调用方，并结束当前函数/方法。 |
| 100 | <code>                order.status(), order.totalAmount(), order.createdAt(), items);</code> | 保留订单原状态、金额与时间，并把刚查出的明细 items 填入新的完整 Order 对象。 |
| 101 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 102 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
