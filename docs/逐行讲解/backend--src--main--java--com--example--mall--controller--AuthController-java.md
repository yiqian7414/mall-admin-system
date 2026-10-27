# backend/src/main/java/com/example/mall/controller/AuthController.java

源文件：[`backend/src/main/java/com/example/mall/controller/AuthController.java`](../../商城管理系统/backend/src/main/java/com/example/mall/controller/AuthController.java)。以下行号以当前文件为准，空行也计入。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>package com.example.mall.controller;</code> | 声明 Java 包名 `com.example.mall.controller`，用于组织类并区分同名类型。 |
| 2 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 3 | <code>import com.example.mall.config.MallPrincipal;</code> | 导入 `com.example.mall.config.MallPrincipal`：让当前类可以直接使用该类型。 |
| 4 | <code>import com.example.mall.model.Models.User;</code> | 导入 `com.example.mall.model.Models.User`：让当前类可以直接使用该类型。 |
| 5 | <code>import java.util.Map;</code> | 导入 `java.util.Map`：使用键值映射。 |
| 6 | <code>import org.springframework.security.core.annotation.AuthenticationPrincipal;</code> | 导入 `org.springframework.security.core.annotation.AuthenticationPrincipal`：让当前类可以直接使用该类型。 |
| 7 | <code>import org.springframework.security.web.csrf.CsrfToken;</code> | 导入 `org.springframework.security.web.csrf.CsrfToken`：访问当前请求的防跨站请求伪造令牌。 |
| 8 | <code>import org.springframework.web.bind.annotation.GetMapping;</code> | 导入 `org.springframework.web.bind.annotation.GetMapping`：映射 GET 查询请求。 |
| 9 | <code>import org.springframework.web.bind.annotation.RequestMapping;</code> | 导入 `org.springframework.web.bind.annotation.RequestMapping`：为控制器或方法声明请求路径。 |
| 10 | <code>import org.springframework.web.bind.annotation.RestController;</code> | 导入 `org.springframework.web.bind.annotation.RestController`：让控制器方法以 JSON 等响应体形式返回数据。 |
| 11 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 12 | <code>@RestController</code> | 把此类注册为 JSON 接口控制器。 |
| 13 | <code>@RequestMapping(&quot;/api/auth&quot;)</code> | 声明 公共 请求路径：/api/auth；请求匹配后由下方控制器方法处理。 |
| 14 | <code>public class AuthController {</code> | 声明 类 `AuthController`，后续内容定义它的字段或行为。 |
| 15 | <code>    @GetMapping(&quot;/csrf&quot;)</code> | 声明 GET 查询 请求路径：/csrf；请求匹配后由下方控制器方法处理。 |
| 16 | <code>    public Map&lt;String, String&gt; csrf(CsrfToken token) {</code> | 声明 `csrf` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 17 | <code>        return Map.of(&quot;token&quot;, token.getToken());</code> | 把 `Map.of("token", token.getToken())` 返回给调用方，并结束当前函数/方法。 |
| 18 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 19 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 20 | <code>    @GetMapping(&quot;/me&quot;)</code> | 声明 GET 查询 请求路径：/me；请求匹配后由下方控制器方法处理。 |
| 21 | <code>    public User me(@AuthenticationPrincipal MallPrincipal principal) {</code> | 声明 `me` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 22 | <code>        return principal.user();</code> | 把 `principal.user()` 返回给调用方，并结束当前函数/方法。 |
| 23 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 24 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
