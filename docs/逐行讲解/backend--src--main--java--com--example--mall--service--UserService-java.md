# backend/src/main/java/com/example/mall/service/UserService.java

源文件：[`backend/src/main/java/com/example/mall/service/UserService.java`](../../商城管理系统/backend/src/main/java/com/example/mall/service/UserService.java)。以下行号以当前文件为准，空行也计入。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>package com.example.mall.service;</code> | 声明 Java 包名 `com.example.mall.service`，用于组织类并区分同名类型。 |
| 2 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 3 | <code>import com.example.mall.common.BusinessException;</code> | 导入 `com.example.mall.common.BusinessException`：让当前类可以直接使用该类型。 |
| 4 | <code>import com.example.mall.model.Models.User;</code> | 导入 `com.example.mall.model.Models.User`：让当前类可以直接使用该类型。 |
| 5 | <code>import com.example.mall.model.Requests.CreateUserInput;</code> | 导入 `com.example.mall.model.Requests.CreateUserInput`：让当前类可以直接使用该类型。 |
| 6 | <code>import com.example.mall.model.Requests.UpdateUserInput;</code> | 导入 `com.example.mall.model.Requests.UpdateUserInput`：让当前类可以直接使用该类型。 |
| 7 | <code>import com.example.mall.repository.UserRepository;</code> | 导入 `com.example.mall.repository.UserRepository`：让当前类可以直接使用该类型。 |
| 8 | <code>import java.nio.charset.StandardCharsets;</code> | 导入 `java.nio.charset.StandardCharsets`：让当前类可以直接使用该类型。 |
| 9 | <code>import java.util.List;</code> | 导入 `java.util.List`：使用有序集合。 |
| 10 | <code>import org.springframework.security.crypto.password.PasswordEncoder;</code> | 导入 `org.springframework.security.crypto.password.PasswordEncoder`：通过单向哈希校验和保存密码。 |
| 11 | <code>import org.springframework.stereotype.Service;</code> | 导入 `org.springframework.stereotype.Service`：将业务类注册为 Spring 服务。 |
| 12 | <code>import org.springframework.transaction.annotation.Transactional;</code> | 导入 `org.springframework.transaction.annotation.Transactional`：声明方法或类的数据库事务边界。 |
| 13 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 14 | <code>@Service</code> | 把此类注册为业务服务，供构造器注入使用。 |
| 15 | <code>public class UserService {</code> | 声明 类 `UserService`，后续内容定义它的字段或行为。 |
| 16 | <code>    private final UserRepository users;</code> | 声明类型为 `UserRepository` 的 常量/依赖字段 `users`；通过构造器或框架完成赋值。 |
| 17 | <code>    private final PasswordEncoder encoder;</code> | 声明类型为 `PasswordEncoder` 的 常量/依赖字段 `encoder`；通过构造器或框架完成赋值。 |
| 18 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 19 | <code>    public UserService(UserRepository users, PasswordEncoder encoder) {</code> | 声明 `UserService` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 20 | <code>        this.users = users;</code> | 把构造器传入的依赖/字段保存到当前对象；this.users = users;，后续实例方法通过 this 的成员使用它。 |
| 21 | <code>        this.encoder = encoder;</code> | 把构造器传入的依赖/字段保存到当前对象；this.encoder = encoder;，后续实例方法通过 this 的成员使用它。 |
| 22 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 23 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 24 | <code>    public List&lt;User&gt; findAll() {</code> | 声明 `findAll` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 25 | <code>        return users.findAll();</code> | 把 `users.findAll()` 返回给调用方，并结束当前函数/方法。 |
| 26 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 27 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 28 | <code>    @Transactional</code> | 为下方业务操作开启事务，运行时异常时回滚数据库修改。 |
| 29 | <code>    public User create(CreateUserInput input) {</code> | 声明 `create` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 30 | <code>        checkPassword(input.password());</code> | 检查字符长度与 BCrypt 的 UTF-8 字节长度边界；本次参数为 `input.password()`。 |
| 31 | <code>        long id = users.insert(input.username(), input.displayName().trim(), encoder.encode(input.password()),</code> | 声明 `id`，类型 `long`，保存 `users.insert(input.username(), input.displayName().trim(), encoder.encode(input.password()),` 的结果。`id`：稳定记录主键 |
| 32 | <code>                input.role(), input.enabled());</code> | 把请求中的角色和启用状态继续传入创建用户操作，结束调用参数列表。 |
| 33 | <code>        return users.findById(id).orElseThrow().user();</code> | 把 `users.findById(id).orElseThrow().user()` 返回给调用方，并结束当前函数/方法。 |
| 34 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 35 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 36 | <code>    @Transactional</code> | 为下方业务操作开启事务，运行时异常时回滚数据库修改。 |
| 37 | <code>    public User update(long id, UpdateUserInput input, long currentUserId) {</code> | 声明 `update` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 38 | <code>        var administrators = users.lockEnabledAdministrators();</code> | 让 Java 编译器根据右侧推断局部变量 `administrators` 的类型，值来自 `users.lockEnabledAdministrators()`。 |
| 39 | <code>        var account = users.findById(id).orElseThrow(() -&gt; BusinessException.notFound(&quot;用户不存在&quot;));</code> | 让 Java 编译器根据右侧推断局部变量 `account` 的类型，值来自 `users.findById(id).orElseThrow(() -> BusinessException.notFound("用户不存在"))`。 |
| 40 | <code>        boolean removesAdmin = account.user().role().equals(&quot;ADMIN&quot;) &amp;&amp; account.user().enabled()</code> | 声明 `removesAdmin`，类型 `boolean`，保存 `account.user().role().equals("ADMIN") && account.user().enabled()` 的结果。 |
| 41 | <code>                &amp;&amp; (!input.role().equals(&quot;ADMIN&quot;) &#124;&#124; !input.enabled());</code> | 继续判断该编辑是否移除一个有效管理员：修改为非 ADMIN 或停用都属于移除。 |
| 42 | <code>        if (removesAdmin &amp;&amp; administrators.size() &lt;= 1)</code> | 判断条件 `removesAdmin && administrators.size() <= 1`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 43 | <code>            throw BusinessException.conflict(&quot;不能停用或降级最后一个启用的管理员&quot;);</code> | 抛出错误 `BusinessException.conflict("不能停用或降级最后一个启用的管理员")`，终止当前路径，并由异常处理器转换为失败响应或提示。 |
| 44 | <code>        if (id == currentUserId &amp;&amp; (!input.enabled() &#124;&#124; !input.role().equals(&quot;ADMIN&quot;))) {</code> | 判断条件 `id == currentUserId && (!input.enabled() &#124;&#124; !input.role().equals("ADMIN"))`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 45 | <code>            throw BusinessException.conflict(&quot;不能停用或降级当前登录账号&quot;);</code> | 抛出错误 `BusinessException.conflict("不能停用或降级当前登录账号")`，终止当前路径，并由异常处理器转换为失败响应或提示。 |
| 46 | <code>        }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 47 | <code>        String hash = account.passwordHash();</code> | 声明 `hash`，类型 `String`，保存 `account.passwordHash()` 的结果。 |
| 48 | <code>        if (input.password() != null &amp;&amp; !input.password().isEmpty()) {</code> | 判断条件 `input.password() != null && !input.password().isEmpty()`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 49 | <code>            checkPassword(input.password());</code> | 检查字符长度与 BCrypt 的 UTF-8 字节长度边界；本次参数为 `input.password()`。 |
| 50 | <code>            hash = encoder.encode(input.password());</code> | 只有用户明确填写了新密码才重新生成 BCrypt 哈希；未填写时保留旧哈希。 |
| 51 | <code>        }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 52 | <code>        users.update(id, input.displayName().trim(), input.role(), input.enabled(), hash);</code> | 按主键更新已有记录；本行传入 `users.update(id, input.displayName().trim(), input.role(), input.enabled(), hash);`。 |
| 53 | <code>        return users.findById(id).orElseThrow().user();</code> | 把 `users.findById(id).orElseThrow().user()` 返回给调用方，并结束当前函数/方法。 |
| 54 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 55 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 56 | <code>    @Transactional</code> | 为下方业务操作开启事务，运行时异常时回滚数据库修改。 |
| 57 | <code>    public void disable(long id, long currentUserId) {</code> | 声明 `disable` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 58 | <code>        var administrators = users.lockEnabledAdministrators();</code> | 让 Java 编译器根据右侧推断局部变量 `administrators` 的类型，值来自 `users.lockEnabledAdministrators()`。 |
| 59 | <code>        var account = users.findById(id).orElseThrow(() -&gt; BusinessException.notFound(&quot;用户不存在&quot;));</code> | 让 Java 编译器根据右侧推断局部变量 `account` 的类型，值来自 `users.findById(id).orElseThrow(() -> BusinessException.notFound("用户不存在"))`。 |
| 60 | <code>        if (id == currentUserId)</code> | 判断条件 `id == currentUserId`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 61 | <code>            throw BusinessException.conflict(&quot;不能停用当前登录账号&quot;);</code> | 抛出错误 `BusinessException.conflict("不能停用当前登录账号")`，终止当前路径，并由异常处理器转换为失败响应或提示。 |
| 62 | <code>        if (account.user().role().equals(&quot;ADMIN&quot;) &amp;&amp; account.user().enabled() &amp;&amp; administrators.size() &lt;= 1) {</code> | 判断条件 `account.user().role().equals("ADMIN") && account.user().enabled() && administrators.size() <= 1`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 63 | <code>            throw BusinessException.conflict(&quot;不能停用最后一个启用的管理员&quot;);</code> | 抛出错误 `BusinessException.conflict("不能停用最后一个启用的管理员")`，终止当前路径，并由异常处理器转换为失败响应或提示。 |
| 64 | <code>        }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 65 | <code>        users.disable(id);</code> | 将账号设为停用而保留数据库记录；本行传入 `users.disable(id);`。 |
| 66 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 67 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 68 | <code>    private void checkPassword(String password) {</code> | 声明 `checkPassword` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 69 | <code>        if (password.length() &lt; 8 &#124;&#124; password.length() &gt; 64 &#124;&#124; password.getBytes(StandardCharsets.UTF_8).length &gt; 72) {</code> | 判断条件 `password.length() < 8 &#124;&#124; password.length() > 64 &#124;&#124; password.getBytes(StandardCharsets.UTF_8).length > 72`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 70 | <code>            throw BusinessException.badRequest(&quot;密码须为 8 至 64 个字符，UTF-8 编码不能超过 72 字节&quot;);</code> | 抛出错误 `BusinessException.badRequest("密码须为 8 至 64 个字符，UTF-8 编码不能超过 72 字节")`，终止当前路径，并由异常处理器转换为失败响应或提示。 |
| 71 | <code>        }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 72 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 73 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
