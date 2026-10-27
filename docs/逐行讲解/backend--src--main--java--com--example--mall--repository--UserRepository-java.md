# backend/src/main/java/com/example/mall/repository/UserRepository.java

源文件：[`backend/src/main/java/com/example/mall/repository/UserRepository.java`](../../商城管理系统/backend/src/main/java/com/example/mall/repository/UserRepository.java)。以下行号以当前文件为准，空行也计入。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>package com.example.mall.repository;</code> | 声明 Java 包名 `com.example.mall.repository`，用于组织类并区分同名类型。 |
| 2 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 3 | <code>import com.example.mall.model.Models.User;</code> | 导入 `com.example.mall.model.Models.User`：让当前类可以直接使用该类型。 |
| 4 | <code>import com.example.mall.model.Models.UserAccount;</code> | 导入 `com.example.mall.model.Models.UserAccount`：让当前类可以直接使用该类型。 |
| 5 | <code>import java.sql.PreparedStatement;</code> | 导入 `java.sql.PreparedStatement`：让当前类可以直接使用该类型。 |
| 6 | <code>import java.util.List;</code> | 导入 `java.util.List`：使用有序集合。 |
| 7 | <code>import java.util.Optional;</code> | 导入 `java.util.Optional`：明确表达结果可能不存在。 |
| 8 | <code>import org.springframework.jdbc.core.JdbcTemplate;</code> | 导入 `org.springframework.jdbc.core.JdbcTemplate`：执行参数化 SQL 并把结果映射为 Java 值。 |
| 9 | <code>import org.springframework.jdbc.core.RowMapper;</code> | 导入 `org.springframework.jdbc.core.RowMapper`：定义一行数据库记录如何转为 Java 对象。 |
| 10 | <code>import org.springframework.jdbc.support.GeneratedKeyHolder;</code> | 导入 `org.springframework.jdbc.support.GeneratedKeyHolder`：让当前类可以直接使用该类型。 |
| 11 | <code>import org.springframework.stereotype.Repository;</code> | 导入 `org.springframework.stereotype.Repository`：将数据访问类注册为 Spring 仓储组件。 |
| 12 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 13 | <code>@Repository</code> | 把此类注册为数据访问组件。 |
| 14 | <code>public class UserRepository {</code> | 声明 类 `UserRepository`，后续内容定义它的字段或行为。 |
| 15 | <code>    private final JdbcTemplate jdbc;</code> | 保存 Spring 注入的 JdbcTemplate，它复用数据源连接并执行参数化 SQL；本类不拼接用户值到 SQL。 |
| 16 | <code>    private static final RowMapper&lt;UserAccount&gt; MAPPER = (rs, row) -&gt; new UserAccount(new User(rs.getLong(&quot;id&quot;),</code> | 定义可复用的数据库行映射 Lambda：rs 是当前 ResultSet 行，row 是行号，后续把列转换为对应返回对象。 |
| 17 | <code>            rs.getString(&quot;username&quot;), rs.getString(&quot;display_name&quot;), rs.getString(&quot;role&quot;), rs.getBoolean(&quot;enabled&quot;)),</code> | 读取本行数据库列 `username`、`display_name`、`role`、`enabled`，按构造器顺序填入当前行映射对象。 |
| 18 | <code>            rs.getString(&quot;password_hash&quot;));</code> | 读取本行数据库列 `password_hash`，按构造器顺序填入当前行映射对象。密码哈希只放在服务器认证使用的 UserAccount 中，不进入公开 User。 |
| 19 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 20 | <code>    public UserRepository(JdbcTemplate jdbc) {</code> | 声明 `UserRepository` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 21 | <code>        this.jdbc = jdbc;</code> | 把构造器传入的依赖/字段保存到当前对象；this.jdbc = jdbc;，后续实例方法通过 this 的成员使用它。 |
| 22 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 23 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 24 | <code>    public Optional&lt;UserAccount&gt; findByUsername(String username) {</code> | 声明 `findByUsername` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 25 | <code>        return jdbc.query(&quot;SELECT * FROM mall_user WHERE username = ?&quot;, MAPPER, username).stream().findFirst();</code> | 按 username 精确查询认证账号，参数绑定到 ?；取首条并以 Optional 表示存在与否。 |
| 26 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 27 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 28 | <code>    public Optional&lt;UserAccount&gt; findById(long id) {</code> | 声明 `findById` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 29 | <code>        return jdbc.query(&quot;SELECT * FROM mall_user WHERE id = ?&quot;, MAPPER, id).stream().findFirst();</code> | 按指定 id 参数精确查询一条记录；RowMapper 转对象，stream().findFirst() 用 Optional 表示未找到。 |
| 30 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 31 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 32 | <code>    public List&lt;User&gt; findAll() {</code> | 声明 `findAll` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 33 | <code>        return jdbc.query(&quot;SELECT * FROM mall_user ORDER BY id&quot;, MAPPER).stream().map(UserAccount::user).toList();</code> | 按 ID 读取账号并只取公开 User 部分返回，过滤密码哈希，避免用户管理接口泄露认证数据。 |
| 34 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 35 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 36 | <code>    public long insert(String username, String displayName, String hash, String role, boolean enabled) {</code> | 声明 `insert` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 37 | <code>        var keys = new GeneratedKeyHolder();</code> | 创建主键收集器，保存 INSERT 后数据库生成的 id，避免自行猜测主键。 |
| 38 | <code>        jdbc.update(connection -&gt; {</code> | 交给 JdbcTemplate 在当前数据源/事务连接上创建 PreparedStatement，并执行插入；回调随后返回语句对象。 |
| 39 | <code>            PreparedStatement statement = connection.prepareStatement(</code> | 通过 JDBC Connection 创建预编译语句；下一行给出固定 INSERT 与返回主键列。 |
| 40 | <code>                    &quot;INSERT INTO mall_user(username, display_name, password_hash, role, enabled) VALUES (?, ?, ?, ?, ?)&quot;,</code> | 提供 SQL 文本片段 `"INSERT INTO mall_user(username, display_name, password_hash, role, enabled) VALUES (?, ?, ?, ?, ?)",`；? 由 JDBC 参数绑定，FOR UPDATE 用于事务行锁，LIMIT/OFFSET 用于分页（出现时生效）。 |
| 41 | <code>                    new String[] { &quot;id&quot; });</code> | 明确要求 JDBC 只返回名为 id 的生成主键列，保证 H2/MySQL 的主键读取一致。 |
| 42 | <code>            statement.setString(1, username);</code> | 给预编译 SQL 的第 1 个 ? 占位符绑定参数 `username`；按类型传值而不拼接用户输入。 |
| 43 | <code>            statement.setString(2, displayName);</code> | 给预编译 SQL 的第 2 个 ? 占位符绑定参数 `displayName`；按类型传值而不拼接用户输入。 |
| 44 | <code>            statement.setString(3, hash);</code> | 给预编译 SQL 的第 3 个 ? 占位符绑定参数 `hash`；按类型传值而不拼接用户输入。 |
| 45 | <code>            statement.setString(4, role);</code> | 给预编译 SQL 的第 4 个 ? 占位符绑定参数 `role`；按类型传值而不拼接用户输入。 |
| 46 | <code>            statement.setBoolean(5, enabled);</code> | 给预编译 SQL 的第 5 个 ? 占位符绑定参数 `enabled`；按类型传值而不拼接用户输入。 |
| 47 | <code>            return statement;</code> | 把绑定好全部 ? 参数的 PreparedStatement 交还 JdbcTemplate 执行。 |
| 48 | <code>        }, keys);</code> | 结束创建 PreparedStatement 的回调，并把自动生成的主键写入 keys。 |
| 49 | <code>        return keys.getKey().longValue();</code> | 读取刚插入记录的唯一 id 主键，并转换为 long 返回服务层。 |
| 50 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 51 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 52 | <code>    public void update(long id, String displayName, String role, boolean enabled, String hash) {</code> | 声明 `update` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 53 | <code>        jdbc.update(&quot;UPDATE mall_user SET display_name = ?, role = ?, enabled = ?, password_hash = ? WHERE id = ?&quot;,</code> | 执行修改 SQL，返回影响行数：`jdbc.update("UPDATE mall_user SET display_name = ?, role = ?, enabled = ?, password_hash = ? WHERE id = ?",`。? 占位符对应后续传入参数。 |
| 54 | <code>                displayName, role, enabled, hash, id);</code> | 依次绑定用户显示姓名、角色、启用状态、密码哈希及目标账号 ID；不保存明文密码。 |
| 55 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 56 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 57 | <code>    public void disable(long id) {</code> | 声明 `disable` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 58 | <code>        jdbc.update(&quot;UPDATE mall_user SET enabled = FALSE WHERE id = ?&quot;, id);</code> | 只把 enabled 设为 FALSE 停用账号，不物理删除用户记录；已有会话在下次请求复查时失效。 |
| 59 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 60 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 61 | <code>    public List&lt;Long&gt; lockEnabledAdministrators() {</code> | 声明 `lockEnabledAdministrators` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 62 | <code>        return jdbc.query(&quot;SELECT id FROM mall_user WHERE role = &#x27;ADMIN&#x27; AND enabled = TRUE ORDER BY id FOR UPDATE&quot;,</code> | 执行 SELECT ... FOR UPDATE，在当前事务中锁定匹配行；用户管理员查询按 ID 排序锁定，商品/订单查询按指定主键锁定。 |
| 63 | <code>                (rs, row) -&gt; rs.getLong(1));</code> | 定义 Java Lambda 回调 `(rs, row) -> rs.getLong(1));`；框架或集合处理调用它时，把左侧参数传入右侧操作。 |
| 64 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 65 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
