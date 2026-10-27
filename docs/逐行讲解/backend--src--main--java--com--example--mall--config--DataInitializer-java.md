# backend/src/main/java/com/example/mall/config/DataInitializer.java

源文件：[`backend/src/main/java/com/example/mall/config/DataInitializer.java`](../../商城管理系统/backend/src/main/java/com/example/mall/config/DataInitializer.java)。空行计入行号。

本文件按当前源码重新校对行号；未改变代码的已有讲解予以保留。

库存与图片的业务示例、整体流程见 [新功能说明](../库存管理与商品图片.md)。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>package com.example.mall.config;</code> | 声明 Java 包名 `com.example.mall.config`，用于组织类并区分同名类型。 |
| 2 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 3 | <code>import com.example.mall.model.Requests.ProductInput;</code> | 导入 `com.example.mall.model.Requests.ProductInput`：让当前类可以直接使用该类型。 |
| 4 | <code>import com.example.mall.repository.CategoryRepository;</code> | 导入 `com.example.mall.repository.CategoryRepository`：让当前类可以直接使用该类型。 |
| 5 | <code>import com.example.mall.repository.ProductRepository;</code> | 导入 `com.example.mall.repository.ProductRepository`：让当前类可以直接使用该类型。 |
| 6 | <code>import com.example.mall.repository.UserRepository;</code> | 导入 `com.example.mall.repository.UserRepository`：让当前类可以直接使用该类型。 |
| 7 | <code>import java.math.BigDecimal;</code> | 导入 `java.math.BigDecimal`：使用十进制数处理金额，避免二进制浮点误差。 |
| 8 | <code>import org.springframework.boot.ApplicationArguments;</code> | 导入 `org.springframework.boot.ApplicationArguments`：让当前类可以直接使用该类型。 |
| 9 | <code>import org.springframework.boot.ApplicationRunner;</code> | 导入 `org.springframework.boot.ApplicationRunner`：让当前类可以直接使用该类型。 |
| 10 | <code>import org.springframework.jdbc.core.JdbcTemplate;</code> | 导入 `org.springframework.jdbc.core.JdbcTemplate`：执行参数化 SQL 并把结果映射为 Java 值。 |
| 11 | <code>import org.springframework.security.crypto.password.PasswordEncoder;</code> | 导入 `org.springframework.security.crypto.password.PasswordEncoder`：通过单向哈希校验和保存密码。 |
| 12 | <code>import org.springframework.stereotype.Component;</code> | 导入 `org.springframework.stereotype.Component`：将通用组件注册到 Spring 容器。 |
| 13 | <code>import org.springframework.transaction.annotation.Transactional;</code> | 导入 `org.springframework.transaction.annotation.Transactional`：声明方法或类的数据库事务边界。 |
| 14 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 15 | <code>/** 只在全新项目数据库中执行一次；标记保存在数据库中，重启不会覆盖用户修改。 */</code> | 块注释中的说明：只在全新项目数据库中执行一次；标记保存在数据库中，重启不会覆盖用户修改。；不参与运行。 |
| 16 | <code>@Component</code> | 把此类注册为通用组件。 |
| 17 | <code>@org.springframework.core.annotation.Order(0)</code> | 向 Spring 或 Java 声明本行对应类/方法的用途，结合后面的定义生效。 |
| 18 | <code>public class DataInitializer implements ApplicationRunner {</code> | 声明 类 `DataInitializer`，后续内容定义它的字段或行为。 |
| 19 | <code>    private final JdbcTemplate jdbc;</code> | 声明类型为 `JdbcTemplate` 的 常量/依赖字段 `jdbc`；通过构造器或框架完成赋值。 |
| 20 | <code>    private final UserRepository users;</code> | 声明类型为 `UserRepository` 的 常量/依赖字段 `users`；通过构造器或框架完成赋值。 |
| 21 | <code>    private final CategoryRepository categories;</code> | 声明类型为 `CategoryRepository` 的 常量/依赖字段 `categories`；通过构造器或框架完成赋值。 |
| 22 | <code>    private final ProductRepository products;</code> | 声明类型为 `ProductRepository` 的 常量/依赖字段 `products`；通过构造器或框架完成赋值。 |
| 23 | <code>    private final PasswordEncoder encoder;</code> | 声明类型为 `PasswordEncoder` 的 常量/依赖字段 `encoder`；通过构造器或框架完成赋值。 |
| 24 | <code>    private final com.example.mall.service.InventoryService inventory;</code> | 声明构造后不再替换的依赖字段，由构造器接收 Spring 管理的对象。 |
| 25 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 26 | <code>    public DataInitializer(JdbcTemplate jdbc, UserRepository users, CategoryRepository categories,</code> | 开始 DataInitializer 构造器，接收数据库与用户/分类仓储；下一行继续其余依赖参数。 |
| 27 | <code>            ProductRepository products, PasswordEncoder encoder, com.example.mall.service.InventoryService inventory) {</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 28 | <code>        this.jdbc = jdbc;</code> | 把构造器传入的依赖/字段保存到当前对象；this.jdbc = jdbc;，后续实例方法通过 this 的成员使用它。 |
| 29 | <code>        this.users = users;</code> | 把构造器传入的依赖/字段保存到当前对象；this.users = users;，后续实例方法通过 this 的成员使用它。 |
| 30 | <code>        this.categories = categories;</code> | 把构造器传入的依赖/字段保存到当前对象；this.categories = categories;，后续实例方法通过 this 的成员使用它。 |
| 31 | <code>        this.products = products;</code> | 把构造器传入的依赖/字段保存到当前对象；this.products = products;，后续实例方法通过 this 的成员使用它。 |
| 32 | <code>        this.encoder = encoder;</code> | 把构造器传入的依赖/字段保存到当前对象；this.encoder = encoder;，后续实例方法通过 this 的成员使用它。 |
| 33 | <code>        this.inventory = inventory;</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 34 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 35 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 36 | <code>    @Override</code> | 表明方法重写了父类或接口方法，由编译器检查签名。 |
| 37 | <code>    @Transactional</code> | 为下方业务操作开启事务，运行时异常时回滚数据库修改。 |
| 38 | <code>    public void run(ApplicationArguments args) {</code> | 声明 `run` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 39 | <code>        Long initialized = jdbc.queryForObject(&quot;SELECT COUNT(*) FROM app_setting WHERE setting_key = &#x27;bootstrap_v1&#x27;&quot;,</code> | 声明 `initialized`，类型 `Long`，保存 `jdbc.queryForObject("SELECT COUNT(*) FROM app_setting WHERE setting_key = 'bootstrap_v1'",` 的结果。 |
| 40 | <code>                Long.class);</code> | 指定查询结果映射为 Long，结束前面的 COUNT 查询调用。 |
| 41 | <code>        if (initialized != null &amp;&amp; initialized &gt; 0)</code> | 判断条件 `initialized != null && initialized > 0`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 42 | <code>            return;</code> | 立即结束当前函数/方法，不再执行后面的新增、保存或状态更新。 |
| 43 | <code>        users.insert(&quot;admin&quot;, &quot;商城管理员&quot;, encoder.encode(&quot;Admin@123456&quot;), &quot;ADMIN&quot;, true);</code> | 写入新记录并返回数据库生成的主键；本行传入 `users.insert("admin", "商城管理员", encoder.encode("Admin@123456"), "ADMIN", true);`。 |
| 44 | <code>        users.insert(&quot;staff&quot;, &quot;商城员工&quot;, encoder.encode(&quot;Staff@123456&quot;), &quot;STAFF&quot;, true);</code> | 写入新记录并返回数据库生成的主键；本行传入 `users.insert("staff", "商城员工", encoder.encode("Staff@123456"), "STAFF", true);`。 |
| 45 | <code>        long digital = categories.insert(&quot;数码配件&quot;, &quot;键盘、鼠标等电脑周边&quot;);</code> | 声明 `digital`，类型 `long`，保存 `categories.insert("数码配件", "键盘、鼠标等电脑周边")` 的结果。 |
| 46 | <code>        long daily = categories.insert(&quot;生活用品&quot;, &quot;日常生活好物&quot;);</code> | 声明 `daily`，类型 `long`，保存 `categories.insert("生活用品", "日常生活好物")` 的结果。 |
| 47 | <code>        seed(new ProductInput(&quot;无线鼠标&quot;, &quot;MOUSE-001&quot;, digital, new BigDecimal(&quot;59.00&quot;), &quot;&quot;, &quot;ON_SALE&quot;, &quot;轻巧便携，适合办公&quot;), 50);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 48 | <code>        seed(new ProductInput(&quot;机械键盘&quot;, &quot;KEYBOARD-001&quot;, digital, new BigDecimal(&quot;199.00&quot;), &quot;&quot;, &quot;ON_SALE&quot;, &quot;清晰按键反馈&quot;), 20);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 49 | <code>        seed(new ProductInput(&quot;保温杯&quot;, &quot;CUP-001&quot;, daily, new BigDecimal(&quot;89.00&quot;), &quot;&quot;, &quot;ON_SALE&quot;, &quot;500ml 不锈钢保温杯&quot;), 4);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 50 | <code>        jdbc.update(&quot;INSERT INTO app_setting(setting_key, setting_value) VALUES (&#x27;bootstrap_v1&#x27;, &#x27;complete&#x27;)&quot;);</code> | 执行修改 SQL，返回影响行数：`jdbc.update("INSERT INTO app_setting(setting_key, setting_value) VALUES ('bootstrap_v1', 'complete')");`。? 占位符对应后续传入参数。 |
| 51 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 52 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 53 | <code>    private void seed(ProductInput input, int quantity) {</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 54 | <code>        long id = products.insert(input);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 55 | <code>        inventory.move(id, quantity, &quot;INBOUND&quot;, &quot;BOOTSTRAP-&quot; + input.sku(), &quot;初始演示入库&quot;, &quot;&quot;);</code> | 调用统一库存业务，在当前事务中修改库存并写流水；订单扣减与取消归还也走这个入口。 |
| 56 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 57 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
