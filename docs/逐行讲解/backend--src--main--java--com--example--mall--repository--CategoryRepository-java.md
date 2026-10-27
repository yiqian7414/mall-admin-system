# backend/src/main/java/com/example/mall/repository/CategoryRepository.java

源文件：[`backend/src/main/java/com/example/mall/repository/CategoryRepository.java`](../../商城管理系统/backend/src/main/java/com/example/mall/repository/CategoryRepository.java)。以下行号以当前文件为准，空行也计入。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>package com.example.mall.repository;</code> | 声明 Java 包名 `com.example.mall.repository`，用于组织类并区分同名类型。 |
| 2 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 3 | <code>import com.example.mall.model.Models.Category;</code> | 导入 `com.example.mall.model.Models.Category`：让当前类可以直接使用该类型。 |
| 4 | <code>import java.sql.PreparedStatement;</code> | 导入 `java.sql.PreparedStatement`：让当前类可以直接使用该类型。 |
| 5 | <code>import java.util.List;</code> | 导入 `java.util.List`：使用有序集合。 |
| 6 | <code>import java.util.Optional;</code> | 导入 `java.util.Optional`：明确表达结果可能不存在。 |
| 7 | <code>import org.springframework.jdbc.core.JdbcTemplate;</code> | 导入 `org.springframework.jdbc.core.JdbcTemplate`：执行参数化 SQL 并把结果映射为 Java 值。 |
| 8 | <code>import org.springframework.jdbc.core.RowMapper;</code> | 导入 `org.springframework.jdbc.core.RowMapper`：定义一行数据库记录如何转为 Java 对象。 |
| 9 | <code>import org.springframework.jdbc.support.GeneratedKeyHolder;</code> | 导入 `org.springframework.jdbc.support.GeneratedKeyHolder`：让当前类可以直接使用该类型。 |
| 10 | <code>import org.springframework.stereotype.Repository;</code> | 导入 `org.springframework.stereotype.Repository`：将数据访问类注册为 Spring 仓储组件。 |
| 11 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 12 | <code>@Repository</code> | 把此类注册为数据访问组件。 |
| 13 | <code>public class CategoryRepository {</code> | 声明 类 `CategoryRepository`，后续内容定义它的字段或行为。 |
| 14 | <code>    private final JdbcTemplate jdbc;</code> | 保存 Spring 注入的 JdbcTemplate，它复用数据源连接并执行参数化 SQL；本类不拼接用户值到 SQL。 |
| 15 | <code>    private static final RowMapper&lt;Category&gt; MAPPER = (rs, row) -&gt; new Category(rs.getLong(&quot;id&quot;), rs.getString(&quot;name&quot;),</code> | 定义可复用的数据库行映射 Lambda：rs 是当前 ResultSet 行，row 是行号，后续把列转换为对应返回对象。 |
| 16 | <code>            rs.getString(&quot;description&quot;));</code> | 读取本行数据库列 `description`，按构造器顺序填入当前行映射对象。 |
| 17 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 18 | <code>    public CategoryRepository(JdbcTemplate jdbc) {</code> | 声明 `CategoryRepository` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 19 | <code>        this.jdbc = jdbc;</code> | 把构造器传入的依赖/字段保存到当前对象；this.jdbc = jdbc;，后续实例方法通过 this 的成员使用它。 |
| 20 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 21 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 22 | <code>    public List&lt;Category&gt; findAll() {</code> | 声明 `findAll` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 23 | <code>        return jdbc.query(&quot;SELECT * FROM category ORDER BY id&quot;, MAPPER);</code> | 按 ID 读取全部分类/账号记录，并通过对应 RowMapper 转换为列表。 |
| 24 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 25 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 26 | <code>    public Optional&lt;Category&gt; findById(long id) {</code> | 声明 `findById` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 27 | <code>        return jdbc.query(&quot;SELECT * FROM category WHERE id = ?&quot;, MAPPER, id).stream().findFirst();</code> | 按指定 id 参数精确查询一条记录；RowMapper 转对象，stream().findFirst() 用 Optional 表示未找到。 |
| 28 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 29 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 30 | <code>    public long insert(String name, String description) {</code> | 声明 `insert` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 31 | <code>        var keys = new GeneratedKeyHolder();</code> | 创建主键收集器，保存 INSERT 后数据库生成的 id，避免自行猜测主键。 |
| 32 | <code>        jdbc.update(connection -&gt; {</code> | 交给 JdbcTemplate 在当前数据源/事务连接上创建 PreparedStatement，并执行插入；回调随后返回语句对象。 |
| 33 | <code>            PreparedStatement statement = connection</code> | 通过 JDBC Connection 创建预编译语句；下一行给出固定 INSERT 与返回主键列。 |
| 34 | <code>                    .prepareStatement(&quot;INSERT INTO category(name, description) VALUES (?, ?)&quot;, new String[] { &quot;id&quot; });</code> | 为分类 INSERT 创建带两个 ? 的预编译语句，并只请求返回 id 生成主键，兼容 H2/MySQL。 |
| 35 | <code>            statement.setString(1, name);</code> | 给预编译 SQL 的第 1 个 ? 占位符绑定参数 `name`；按类型传值而不拼接用户输入。 |
| 36 | <code>            statement.setString(2, description);</code> | 给预编译 SQL 的第 2 个 ? 占位符绑定参数 `description`；按类型传值而不拼接用户输入。 |
| 37 | <code>            return statement;</code> | 把绑定好全部 ? 参数的 PreparedStatement 交还 JdbcTemplate 执行。 |
| 38 | <code>        }, keys);</code> | 结束创建 PreparedStatement 的回调，并把自动生成的主键写入 keys。 |
| 39 | <code>        return keys.getKey().longValue();</code> | 读取刚插入记录的唯一 id 主键，并转换为 long 返回服务层。 |
| 40 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 41 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 42 | <code>    public void update(long id, String name, String description) {</code> | 声明 `update` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 43 | <code>        jdbc.update(&quot;UPDATE category SET name = ?, description = ? WHERE id = ?&quot;, name, description, id);</code> | 执行修改 SQL，返回影响行数：`jdbc.update("UPDATE category SET name = ?, description = ? WHERE id = ?", name, description, id);`。? 占位符对应后续传入参数。 |
| 44 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 45 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 46 | <code>    public long countProducts(long id) {</code> | 声明 `countProducts` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 47 | <code>        return jdbc.queryForObject(&quot;SELECT COUNT(*) FROM product WHERE category_id = ?&quot;, Long.class, id);</code> | 执行 COUNT(*) 并映射为 Long：统计分类下的商品数，用于删除保护。 |
| 48 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 49 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 50 | <code>    public void delete(long id) {</code> | 声明 `delete` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 51 | <code>        jdbc.update(&quot;DELETE FROM category WHERE id = ?&quot;, id);</code> | 按指定主键执行物理删除；业务服务事先检查引用，数据库外键再次保护关联记录。 |
| 52 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 53 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
