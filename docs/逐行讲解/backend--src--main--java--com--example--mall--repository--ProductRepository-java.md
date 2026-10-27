# backend/src/main/java/com/example/mall/repository/ProductRepository.java

源文件：[`backend/src/main/java/com/example/mall/repository/ProductRepository.java`](../../商城管理系统/backend/src/main/java/com/example/mall/repository/ProductRepository.java)。空行计入行号。

本文件按当前源码重新校对行号；未改变代码的已有讲解予以保留。

库存与图片的业务示例、整体流程见 [新功能说明](../库存管理与商品图片.md)。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>package com.example.mall.repository;</code> | 声明 Java 包名 `com.example.mall.repository`，用于组织类并区分同名类型。 |
| 2 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 3 | <code>import com.example.mall.model.Models.Page;</code> | 导入 `com.example.mall.model.Models.Page`：让当前类可以直接使用该类型。 |
| 4 | <code>import com.example.mall.model.Models.Product;</code> | 导入 `com.example.mall.model.Models.Product`：让当前类可以直接使用该类型。 |
| 5 | <code>import com.example.mall.model.Requests.ProductInput;</code> | 导入 `com.example.mall.model.Requests.ProductInput`：让当前类可以直接使用该类型。 |
| 6 | <code>import java.sql.PreparedStatement;</code> | 导入 `java.sql.PreparedStatement`：让当前类可以直接使用该类型。 |
| 7 | <code>import java.util.ArrayList;</code> | 导入 `java.util.ArrayList`：创建可增长的列表。 |
| 8 | <code>import java.util.List;</code> | 导入 `java.util.List`：使用有序集合。 |
| 9 | <code>import java.util.Optional;</code> | 导入 `java.util.Optional`：明确表达结果可能不存在。 |
| 10 | <code>import org.springframework.jdbc.core.JdbcTemplate;</code> | 导入 `org.springframework.jdbc.core.JdbcTemplate`：执行参数化 SQL 并把结果映射为 Java 值。 |
| 11 | <code>import org.springframework.jdbc.core.RowMapper;</code> | 导入 `org.springframework.jdbc.core.RowMapper`：定义一行数据库记录如何转为 Java 对象。 |
| 12 | <code>import org.springframework.jdbc.support.GeneratedKeyHolder;</code> | 导入 `org.springframework.jdbc.support.GeneratedKeyHolder`：让当前类可以直接使用该类型。 |
| 13 | <code>import org.springframework.stereotype.Repository;</code> | 导入 `org.springframework.stereotype.Repository`：将数据访问类注册为 Spring 仓储组件。 |
| 14 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 15 | <code>@Repository</code> | 把此类注册为数据访问组件。 |
| 16 | <code>public class ProductRepository {</code> | 声明 类 `ProductRepository`，后续内容定义它的字段或行为。 |
| 17 | <code>    private final JdbcTemplate jdbc;</code> | 保存 Spring 注入的 JdbcTemplate，它复用数据源连接并执行参数化 SQL；本类不拼接用户值到 SQL。 |
| 18 | <code>    private static final String SELECT = &quot;SELECT p.*, c.name AS category_name FROM product p JOIN category c ON c.id = p.category_id&quot;;</code> | 定义商品基础联表查询：product 别名 p，category 别名 c，通过分类 ID JOIN，并把分类名起别名 category_name 供映射器读取。 |
| 19 | <code>    private static final RowMapper&lt;Product&gt; MAPPER = (rs, row) -&gt; new Product(rs.getLong(&quot;id&quot;), rs.getString(&quot;name&quot;),</code> | 定义可复用的数据库行映射 Lambda：rs 是当前 ResultSet 行，row 是行号，后续把列转换为对应返回对象。 |
| 20 | <code>            rs.getString(&quot;sku&quot;), rs.getLong(&quot;category_id&quot;), rs.getString(&quot;category_name&quot;), rs.getBigDecimal(&quot;price&quot;),</code> | 读取本行数据库列 `sku`、`category_id`、`category_name`、`price`，按构造器顺序填入当前行映射对象。getBigDecimal 保留十进制金额精度。 |
| 21 | <code>            rs.getInt(&quot;stock&quot;), rs.getString(&quot;status&quot;), rs.getString(&quot;description&quot;),</code> | 读取本行数据库列 `stock`、`status`、`description`，按构造器顺序填入当前行映射对象。 |
| 22 | <code>            rs.getTimestamp(&quot;created_at&quot;).toLocalDateTime(), rs.getString(&quot;image_url&quot;));</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 23 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 24 | <code>    public ProductRepository(JdbcTemplate jdbc) {</code> | 声明 `ProductRepository` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 25 | <code>        this.jdbc = jdbc;</code> | 把构造器传入的依赖/字段保存到当前对象；this.jdbc = jdbc;，后续实例方法通过 this 的成员使用它。 |
| 26 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 27 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 28 | <code>    public Optional&lt;Product&gt; findById(long id) {</code> | 声明 `findById` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 29 | <code>        return jdbc.query(SELECT + &quot; WHERE p.id = ?&quot;, MAPPER, id).stream().findFirst();</code> | 按指定 id 参数精确查询一条记录；RowMapper 转对象，stream().findFirst() 用 Optional 表示未找到。 |
| 30 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 31 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 32 | <code>    /** 事务里锁定商品行。按 id 顺序调用，避免多商品订单相互逆序等待。 */</code> | 块注释中的说明：事务里锁定商品行。按 id 顺序调用，避免多商品订单相互逆序等待。；不参与运行。 |
| 33 | <code>    public Optional&lt;Product&gt; lockById(long id) {</code> | 声明 `lockById` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 34 | <code>        return jdbc.query(SELECT + &quot; WHERE p.id = ? FOR UPDATE&quot;, MAPPER, id).stream().findFirst();</code> | 执行 SELECT ... FOR UPDATE，在当前事务中锁定匹配行；用户管理员查询按 ID 排序锁定，商品/订单查询按指定主键锁定。 |
| 35 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 36 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 37 | <code>    public Page&lt;Product&gt; findPage(String keyword, Long categoryId, String status, int page, int size) {</code> | 声明 `findPage` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 38 | <code>        var where = new StringBuilder(&quot; WHERE 1 = 1&quot;);</code> | 创建可追加的固定查询条件；WHERE 1=1 恒成立，后续每个可选筛选都可统一追加 AND。 |
| 39 | <code>        var parameters = new ArrayList&lt;Object&gt;();</code> | 创建按 SQL ? 顺序保存查询参数的列表；参数与 SQL 结构分开，防止注入。 |
| 40 | <code>        if (keyword != null &amp;&amp; !keyword.isBlank()) {</code> | 只有用户填写了非空关键词才追加模糊搜索；空关键词表示不按文字筛选。 |
| 41 | <code>            where.append(&quot; AND (LOWER(p.name) LIKE ? ESCAPE &#x27;!&#x27; OR LOWER(p.sku) LIKE ? ESCAPE &#x27;!&#x27;)&quot;);</code> | 追加商品名称或 SKU 的小写 LIKE 搜索，ESCAPE ! 规定感叹号转义通配符，两个 ? 使用同一搜索内容。 |
| 42 | <code>            String pattern = &quot;%&quot; + escapeLike(keyword.trim().toLowerCase(java.util.Locale.ROOT)) + &quot;%&quot;;</code> | 把关键词去空白、转小写并转义 !/%/_，再加两侧 % 做包含搜索；使用 Locale.ROOT 保证大小写行为不依赖机器语言。 |
| 43 | <code>            parameters.add(pattern);</code> | 按当前 LIKE 占位符顺序加入转义后的同一关键词；同一行重复调用对应名称/SKU或订单号/姓名/电话的不同 ?。 |
| 44 | <code>            parameters.add(pattern);</code> | 按当前 LIKE 占位符顺序加入转义后的同一关键词；同一行重复调用对应名称/SKU或订单号/姓名/电话的不同 ?。 |
| 45 | <code>        }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 46 | <code>        if (categoryId != null) {</code> | 只有传入分类 ID 时才增加分类筛选；不传则查询全部分类中的商品。 |
| 47 | <code>            where.append(&quot; AND p.category_id = ?&quot;);</code> | 追加 p.category_id = ? 固定条件，下一行把分类 ID 作为参数传入。 |
| 48 | <code>            parameters.add(categoryId);</code> | 绑定前面的分类 ID 占位符，分类编号作为值传入而不是 SQL 文本。 |
| 49 | <code>        }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 50 | <code>        if (status != null &amp;&amp; !status.isBlank()) {</code> | 只有传入非空状态时才追加状态筛选，状态是否合法已由业务层检查。 |
| 51 | <code>            where.append(&quot; AND p.status = ?&quot;);</code> | 追加 status = ? 固定条件，下一行传入上架/订单状态。 |
| 52 | <code>            parameters.add(status);</code> | 绑定前面的状态占位符。 |
| 53 | <code>        }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 54 | <code>        long total = jdbc.queryForObject(&quot;SELECT COUNT(*) FROM product p&quot; + where, Long.class, parameters.toArray());</code> | 先用完全相同筛选条件 COUNT(*) 取得结果总条数；此时尚未加入 LIMIT/OFFSET，所以 total 是全部匹配数量而非当前页长度。 |
| 55 | <code>        parameters.add(size);</code> | 将每页条数追加为 LIMIT ? 参数。 |
| 56 | <code>        parameters.add((long) (page - 1) * size);</code> | 计算 OFFSET=(页码-1)×每页条数；先转 long 避免 int 乘法溢出，page=1 时偏移为0。 |
| 57 | <code>        var items = jdbc.query(SELECT + where + &quot; ORDER BY p.id DESC LIMIT ? OFFSET ?&quot;, MAPPER, parameters.toArray());</code> | 使用同一筛选条件按 ID 倒序取当前页，LIMIT/OFFSET 使用最后两个参数；RowMapper 把数据库行映射为对象。 |
| 58 | <code>        return new Page&lt;&gt;(items, total, page, size);</code> | 组装分页响应 items/total/page/size；items 是当前页商品列表；total 表示全部筛选结果数。 |
| 59 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 60 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 61 | <code>    public long insert(ProductInput input) {</code> | 声明 `insert` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 62 | <code>        var keys = new GeneratedKeyHolder();</code> | 创建主键收集器，保存 INSERT 后数据库生成的 id，避免自行猜测主键。 |
| 63 | <code>        jdbc.update(connection -&gt; {</code> | 交给 JdbcTemplate 在当前数据源/事务连接上创建 PreparedStatement，并执行插入；回调随后返回语句对象。 |
| 64 | <code>            PreparedStatement statement = connection.prepareStatement(</code> | 通过 JDBC Connection 创建预编译语句；下一行给出固定 INSERT 与返回主键列。 |
| 65 | <code>                    &quot;INSERT INTO product(name, sku, category_id, price, stock, status, description, image_url) VALUES (?, ?, ?, ?, 0, ?, ?, ?)&quot;,</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 66 | <code>                    new String[] { &quot;id&quot; });</code> | 明确要求 JDBC 只返回名为 id 的生成主键列，保证 H2/MySQL 的主键读取一致。 |
| 67 | <code>            statement.setString(1, input.name().trim());</code> | 给预编译 SQL 的第 1 个 ? 占位符绑定参数 `input.name().trim()`；按类型传值而不拼接用户输入。 |
| 68 | <code>            statement.setString(2, input.sku());</code> | 给预编译 SQL 的第 2 个 ? 占位符绑定参数 `input.sku()`；按类型传值而不拼接用户输入。 |
| 69 | <code>            statement.setLong(3, input.categoryId());</code> | 给预编译 SQL 的第 3 个 ? 占位符绑定参数 `input.categoryId()`；按类型传值而不拼接用户输入。 |
| 70 | <code>            statement.setBigDecimal(4, input.price());</code> | 给预编译 SQL 的第 4 个 ? 占位符绑定参数 `input.price()`；按类型传值而不拼接用户输入。 |
| 71 | <code>            statement.setString(5, input.status());</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 72 | <code>            statement.setString(6, input.description() == null ? &quot;&quot; : input.description().trim());</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 73 | <code>            statement.setString(7, input.imageUrl() == null ? &quot;&quot; : input.imageUrl());</code> | 读取商品图片地址；后端只接受已上传且实际存在的图片，不能借此提交任意文件路径。 |
| 74 | <code>            return statement;</code> | 把绑定好全部 ? 参数的 PreparedStatement 交还 JdbcTemplate 执行。 |
| 75 | <code>        }, keys);</code> | 结束创建 PreparedStatement 的回调，并把自动生成的主键写入 keys。 |
| 76 | <code>        return keys.getKey().longValue();</code> | 读取刚插入记录的唯一 id 主键，并转换为 long 返回服务层。 |
| 77 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 78 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 79 | <code>    public void update(long id, ProductInput input) {</code> | 声明 `update` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 80 | <code>        jdbc.update(</code> | 执行修改 SQL，返回影响行数：`jdbc.update(`。? 占位符对应后续传入参数。 |
| 81 | <code>                &quot;UPDATE product SET name = ?, sku = ?, category_id = ?, price = ?, status = ?, description = ?, image_url = ? WHERE id = ?&quot;,</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 82 | <code>                input.name().trim(), input.sku(), input.categoryId(), input.price(), input.status(),</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 83 | <code>                input.description() == null ? &quot;&quot; : input.description().trim(), input.imageUrl() == null ? &quot;&quot; : input.imageUrl(), id);</code> | 读取商品图片地址；后端只接受已上传且实际存在的图片，不能借此提交任意文件路径。 |
| 84 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 85 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 86 | <code>    public int adjustStock(long id, int delta) {</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 87 | <code>        return jdbc.update(&quot;UPDATE product SET stock = stock + ? WHERE id = ? AND stock + ? BETWEEN 0 AND 1000000000&quot;,</code> | 数据库原子加减库存，同时限制变更后的数量在 0 到 10 亿之间；修改成功还必须写入流水。 |
| 88 | <code>                delta, id, delta);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 89 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 90 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 91 | <code>    public void updateStatus(long id, String status) {</code> | 声明 `updateStatus` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 92 | <code>        jdbc.update(&quot;UPDATE product SET status = ? WHERE id = ?&quot;, status, id);</code> | 只更新商品 status 列，保留数据库当前库存和其他字段，避免旧列表数据覆盖并发订单扣库。 |
| 93 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 94 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 95 | <code>    public long countOrderReferences(long id) {</code> | 声明 `countOrderReferences` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 96 | <code>        return jdbc.queryForObject(&quot;SELECT COUNT(*) FROM order_item WHERE product_id = ?&quot;, Long.class, id);</code> | 执行 COUNT(*) 并映射为 Long：统计目标商品已被订单明细引用的次数，用于禁止删除历史关联商品。 |
| 97 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 98 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 99 | <code>    public void delete(long id) {</code> | 声明 `delete` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 100 | <code>        jdbc.update(&quot;DELETE FROM product WHERE id = ?&quot;, id);</code> | 按指定主键执行物理删除；业务服务事先检查引用，数据库外键再次保护关联记录。 |
| 101 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 102 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 103 | <code>    public List&lt;Product&gt; lowStockProducts() {</code> | 声明 `lowStockProducts` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 104 | <code>        return jdbc.query(SELECT + &quot; WHERE p.stock &lt;= 5 ORDER BY p.stock, p.id LIMIT 8&quot;, MAPPER);</code> | 读取库存不超过5的商品，按库存从少到多、再按ID排序，最多8件，用于工作台低库存提醒。 |
| 105 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 106 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 107 | <code>    static String escapeLike(String value) {</code> | 声明返回类型为 `String` 的方法 `escapeLike`；转义 LIKE 搜索的特殊字符，按用户字面内容查询。本行开始参数声明，后续可能继续分行。 |
| 108 | <code>        return value.replace(&quot;!&quot;, &quot;!!&quot;).replace(&quot;%&quot;, &quot;!%&quot;).replace(&quot;_&quot;, &quot;!_&quot;);</code> | 先把转义符 ! 变成 !!，再把 %/_ 变成 !%/!_；配合 SQL ESCAPE !，用户关键词中的这些字符按字面匹配。 |
| 109 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 110 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
