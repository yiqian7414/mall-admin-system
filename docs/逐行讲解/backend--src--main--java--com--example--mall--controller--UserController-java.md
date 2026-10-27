# backend/src/main/java/com/example/mall/controller/UserController.java

源文件：[`backend/src/main/java/com/example/mall/controller/UserController.java`](../../商城管理系统/backend/src/main/java/com/example/mall/controller/UserController.java)。以下行号以当前文件为准，空行也计入。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>package com.example.mall.controller;</code> | 声明 Java 包名 `com.example.mall.controller`，用于组织类并区分同名类型。 |
| 2 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 3 | <code>import com.example.mall.config.MallPrincipal;</code> | 导入 `com.example.mall.config.MallPrincipal`：让当前类可以直接使用该类型。 |
| 4 | <code>import com.example.mall.model.Models.User;</code> | 导入 `com.example.mall.model.Models.User`：让当前类可以直接使用该类型。 |
| 5 | <code>import com.example.mall.model.Requests.CreateUserInput;</code> | 导入 `com.example.mall.model.Requests.CreateUserInput`：让当前类可以直接使用该类型。 |
| 6 | <code>import com.example.mall.model.Requests.UpdateUserInput;</code> | 导入 `com.example.mall.model.Requests.UpdateUserInput`：让当前类可以直接使用该类型。 |
| 7 | <code>import com.example.mall.service.UserService;</code> | 导入 `com.example.mall.service.UserService`：让当前类可以直接使用该类型。 |
| 8 | <code>import jakarta.validation.Valid;</code> | 导入 `jakarta.validation.Valid`：触发参数对象中的 Bean Validation 约束。 |
| 9 | <code>import java.util.List;</code> | 导入 `java.util.List`：使用有序集合。 |
| 10 | <code>import org.springframework.http.HttpStatus;</code> | 导入 `org.springframework.http.HttpStatus`：使用标准 HTTP 状态码。 |
| 11 | <code>import org.springframework.security.core.annotation.AuthenticationPrincipal;</code> | 导入 `org.springframework.security.core.annotation.AuthenticationPrincipal`：让当前类可以直接使用该类型。 |
| 12 | <code>import org.springframework.web.bind.annotation.*;</code> | 导入 `org.springframework.web.bind.annotation.*`：让当前类可以直接使用该类型。 |
| 13 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 14 | <code>@RestController</code> | 把此类注册为 JSON 接口控制器。 |
| 15 | <code>@RequestMapping(&quot;/api/users&quot;)</code> | 声明 公共 请求路径：/api/users；请求匹配后由下方控制器方法处理。 |
| 16 | <code>public class UserController {</code> | 声明 类 `UserController`，后续内容定义它的字段或行为。 |
| 17 | <code>    private final UserService users;</code> | 声明类型为 `UserService` 的 常量/依赖字段 `users`；通过构造器或框架完成赋值。 |
| 18 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 19 | <code>    public UserController(UserService users) {</code> | 声明 `UserController` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 20 | <code>        this.users = users;</code> | 把构造器传入的依赖/字段保存到当前对象；this.users = users;，后续实例方法通过 this 的成员使用它。 |
| 21 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 22 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 23 | <code>    @GetMapping</code> | 声明 GET 查询 请求路径：；请求匹配后由下方控制器方法处理。 |
| 24 | <code>    public List&lt;User&gt; list() {</code> | 声明 `list` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 25 | <code>        return users.findAll();</code> | 把 `users.findAll()` 返回给调用方，并结束当前函数/方法。 |
| 26 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 27 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 28 | <code>    @PostMapping</code> | 声明 POST 创建/动作 请求路径：；请求匹配后由下方控制器方法处理。 |
| 29 | <code>    @ResponseStatus(HttpStatus.CREATED)</code> | 为下方处理结果指定 HTTP 状态。括号内参数为 `HttpStatus.CREATED`。 |
| 30 | <code>    public User create(@Valid @RequestBody CreateUserInput input) {</code> | 声明 `create` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 31 | <code>        return users.create(input);</code> | 把 `users.create(input)` 返回给调用方，并结束当前函数/方法。 |
| 32 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 33 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 34 | <code>    @PutMapping(&quot;/{id}&quot;)</code> | 声明 PUT 更新 请求路径：/{id}；请求匹配后由下方控制器方法处理。 |
| 35 | <code>    public User update(@PathVariable long id, @Valid @RequestBody UpdateUserInput input,</code> | 声明返回类型为 `User` 的方法 `update`；按主键更新已有记录。本行开始参数声明，后续可能继续分行。 |
| 36 | <code>            @AuthenticationPrincipal MallPrincipal principal) {</code> | 应用 `AuthenticationPrincipal MallPrincipal principal) {` 注解为下方声明附加框架规则。 |
| 37 | <code>        return users.update(id, input, principal.user().id());</code> | 把 `users.update(id, input, principal.user().id())` 返回给调用方，并结束当前函数/方法。 |
| 38 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 39 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 40 | <code>    @DeleteMapping(&quot;/{id}&quot;)</code> | 声明 DELETE 删除 请求路径：/{id}；请求匹配后由下方控制器方法处理。 |
| 41 | <code>    @ResponseStatus(HttpStatus.NO_CONTENT)</code> | 为下方处理结果指定 HTTP 状态。括号内参数为 `HttpStatus.NO_CONTENT`。 |
| 42 | <code>    public void disable(@PathVariable long id, @AuthenticationPrincipal MallPrincipal principal) {</code> | 声明 `disable` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 43 | <code>        users.disable(id, principal.user().id());</code> | 将账号设为停用而保留数据库记录；本行传入 `users.disable(id, principal.user().id());`。 |
| 44 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 45 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
