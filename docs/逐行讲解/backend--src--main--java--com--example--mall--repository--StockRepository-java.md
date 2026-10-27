# backend/src/main/java/com/example/mall/repository/StockRepository.java

源文件：[`backend/src/main/java/com/example/mall/repository/StockRepository.java`](../../商城管理系统/backend/src/main/java/com/example/mall/repository/StockRepository.java)。空行计入行号。

库存流水查询与写入，所有用户输入作为 SQL 参数传递。

库存与图片的业务示例、整体流程见 [新功能说明](../库存管理与商品图片.md)。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>package com.example.mall.repository;</code> | 声明 Java 包，按控制器、业务、数据访问等职责组织类。 |
| 2 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 3 | <code>import com.example.mall.model.Models.Page;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 4 | <code>import com.example.mall.model.Models.Product;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 5 | <code>import com.example.mall.model.Models.StockMovement;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 6 | <code>import java.time.LocalDate;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 7 | <code>import java.util.ArrayList;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 8 | <code>import java.util.Optional;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 9 | <code>import org.springframework.jdbc.core.JdbcTemplate;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 10 | <code>import org.springframework.jdbc.core.RowMapper;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 11 | <code>import org.springframework.stereotype.Repository;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 12 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 13 | <code>@Repository</code> | 向 Spring 或 Java 声明本行对应类/方法的用途，结合后面的定义生效。 |
| 14 | <code>public class StockRepository {</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 15 | <code>    private final JdbcTemplate jdbc;</code> | 声明构造后不再替换的依赖字段，由构造器接收 Spring 管理的对象。 |
| 16 | <code>    private static final RowMapper&lt;StockMovement&gt; MAPPER = (rs, row) -&gt; new StockMovement(rs.getLong(&quot;id&quot;),</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 17 | <code>            rs.getLong(&quot;product_id&quot;), rs.getString(&quot;product_name&quot;), rs.getString(&quot;sku&quot;), rs.getString(&quot;type&quot;),</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 18 | <code>            rs.getInt(&quot;delta&quot;), rs.getInt(&quot;before_stock&quot;), rs.getInt(&quot;after_stock&quot;), rs.getString(&quot;reference&quot;),</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 19 | <code>            rs.getString(&quot;reason&quot;), rs.getString(&quot;remark&quot;), rs.getString(&quot;operator&quot;),</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 20 | <code>            rs.getTimestamp(&quot;created_at&quot;).toLocalDateTime());</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 21 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 22 | <code>    public StockRepository(JdbcTemplate jdbc) { this.jdbc = jdbc; }</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 23 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 24 | <code>    public Optional&lt;StockMovement&gt; find(long productId, String type, String reference) {</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 25 | <code>        return jdbc.query(&quot;SELECT * FROM stock_movement WHERE product_id = ? AND type = ? AND reference = ?&quot;,</code> | 查询数据库并映射为返回对象；查询参数与 SQL 结构分开传递。 |
| 26 | <code>                MAPPER, productId, type, reference).stream().findFirst();</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 27 | <code>    }</code> | 结束当前作用域、参数、回调或集合定义。 |
| 28 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 29 | <code>    public StockMovement insert(Product product, int delta, String type, String reference, String reason,</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 30 | <code>            String remark, String operator) {</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 31 | <code>        jdbc.update(&quot;INSERT INTO stock_movement(product_id, product_name, sku, type, delta, before_stock, after_stock, reference, reason, remark, operator) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)&quot;,</code> | 执行参数化数据库写入，后面的参数按问号顺序绑定；不把用户输入直接拼成 SQL。 |
| 32 | <code>                product.id(), product.name(), product.sku(), type, delta, product.stock(), product.stock() + delta,</code> | 用原库存加带正负号的变动量计算新库存；比如 20 + (-3) = 17。 |
| 33 | <code>                reference, reason, remark, operator);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 34 | <code>        return find(product.id(), type, reference).orElseThrow();</code> | 返回本行表达式的结果，交给调用方或作为接口响应。 |
| 35 | <code>    }</code> | 结束当前作用域、参数、回调或集合定义。 |
| 36 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 37 | <code>    public long countByProduct(long id) {</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 38 | <code>        return jdbc.queryForObject(&quot;SELECT COUNT(*) FROM stock_movement WHERE product_id = ?&quot;, Long.class, id);</code> | 查询数据库并映射为返回对象；查询参数与 SQL 结构分开传递。 |
| 39 | <code>    }</code> | 结束当前作用域、参数、回调或集合定义。 |
| 40 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 41 | <code>    public Page&lt;StockMovement&gt; findPage(String keyword, String type, LocalDate start, LocalDate end, int page, int size) {</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 42 | <code>        var where = new StringBuilder(&quot; WHERE 1 = 1&quot;);</code> | 声明本地变量或状态引用，保存后续代码要使用的值。 |
| 43 | <code>        var args = new ArrayList&lt;Object&gt;();</code> | 声明本地变量或状态引用，保存后续代码要使用的值。 |
| 44 | <code>        if (keyword != null &amp;&amp; !keyword.isBlank()) {</code> | 判断本行条件，只有成立时才进入对应分支；结合下一行看成功、拒绝或提前返回的处理。 |
| 45 | <code>            where.append(&quot; AND (LOWER(product_name) LIKE ? ESCAPE &#x27;!&#x27; OR LOWER(sku) LIKE ? ESCAPE &#x27;!&#x27; OR LOWER(reference) LIKE ? ESCAPE &#x27;!&#x27;)&quot;);</code> | 按当前筛选条件追加 SQL 结构，具体用户输入放进参数列表。 |
| 46 | <code>            String pattern = &quot;%&quot; + ProductRepository.escapeLike(keyword.trim().toLowerCase(java.util.Locale.ROOT)) + &quot;%&quot;;</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 47 | <code>            args.add(pattern); args.add(pattern); args.add(pattern);</code> | 把查询值加入参数列表，顺序必须与 SQL 中问号的位置一致。 |
| 48 | <code>        }</code> | 结束当前作用域、参数、回调或集合定义。 |
| 49 | <code>        if (type != null &amp;&amp; !type.isBlank()) { where.append(&quot; AND type = ?&quot;); args.add(type); }</code> | 按当前筛选条件追加 SQL 结构，具体用户输入放进参数列表。 |
| 50 | <code>        if (start != null) { where.append(&quot; AND created_at &gt;= ?&quot;); args.add(start.atStartOfDay()); }</code> | 按当前筛选条件追加 SQL 结构，具体用户输入放进参数列表。 |
| 51 | <code>        if (end != null) { where.append(&quot; AND created_at &lt; ?&quot;); args.add(end.plusDays(1).atStartOfDay()); }</code> | 按当前筛选条件追加 SQL 结构，具体用户输入放进参数列表。 |
| 52 | <code>        long total = jdbc.queryForObject(&quot;SELECT COUNT(*) FROM stock_movement&quot; + where, Long.class, args.toArray());</code> | 查询数据库并映射为返回对象；查询参数与 SQL 结构分开传递。 |
| 53 | <code>        args.add(size); args.add((long) (page - 1) * size);</code> | 把查询值加入参数列表，顺序必须与 SQL 中问号的位置一致。 |
| 54 | <code>        return new Page&lt;&gt;(jdbc.query(&quot;SELECT * FROM stock_movement&quot; + where + &quot; ORDER BY id DESC LIMIT ? OFFSET ?&quot;,</code> | 查询数据库并映射为返回对象；查询参数与 SQL 结构分开传递。 |
| 55 | <code>                MAPPER, args.toArray()), total, page, size);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 56 | <code>    }</code> | 结束当前作用域、参数、回调或集合定义。 |
| 57 | <code>}</code> | 结束当前作用域、参数、回调或集合定义。 |
