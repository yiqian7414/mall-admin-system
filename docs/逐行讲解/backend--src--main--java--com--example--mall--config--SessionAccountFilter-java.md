# backend/src/main/java/com/example/mall/config/SessionAccountFilter.java

源文件：[`backend/src/main/java/com/example/mall/config/SessionAccountFilter.java`](../../商城管理系统/backend/src/main/java/com/example/mall/config/SessionAccountFilter.java)。以下行号以当前文件为准，空行也计入。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>package com.example.mall.config;</code> | 声明 Java 包名 `com.example.mall.config`，用于组织类并区分同名类型。 |
| 2 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 3 | <code>import com.example.mall.repository.UserRepository;</code> | 导入 `com.example.mall.repository.UserRepository`：让当前类可以直接使用该类型。 |
| 4 | <code>import com.fasterxml.jackson.databind.ObjectMapper;</code> | 导入 `com.fasterxml.jackson.databind.ObjectMapper`：在 Java 对象和 JSON 之间转换。 |
| 5 | <code>import jakarta.servlet.FilterChain;</code> | 导入 `jakarta.servlet.FilterChain`：让当前类可以直接使用该类型。 |
| 6 | <code>import jakarta.servlet.ServletException;</code> | 导入 `jakarta.servlet.ServletException`：让当前类可以直接使用该类型。 |
| 7 | <code>import jakarta.servlet.http.HttpServletRequest;</code> | 导入 `jakarta.servlet.http.HttpServletRequest`：读取 HTTP 请求信息及会话。 |
| 8 | <code>import jakarta.servlet.http.HttpServletResponse;</code> | 导入 `jakarta.servlet.http.HttpServletResponse`：设置 HTTP 响应内容。 |
| 9 | <code>import java.io.IOException;</code> | 导入 `java.io.IOException`：让当前类可以直接使用该类型。 |
| 10 | <code>import java.util.Map;</code> | 导入 `java.util.Map`：使用键值映射。 |
| 11 | <code>import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;</code> | 导入 `org.springframework.security.authentication.UsernamePasswordAuthenticationToken`：表示用户名密码认证或已认证身份。 |
| 12 | <code>import org.springframework.security.core.context.SecurityContextHolder;</code> | 导入 `org.springframework.security.core.context.SecurityContextHolder`：读取或设置当前线程的安全上下文。 |
| 13 | <code>import org.springframework.web.filter.OncePerRequestFilter;</code> | 导入 `org.springframework.web.filter.OncePerRequestFilter`：让当前类可以直接使用该类型。 |
| 14 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 15 | <code>/** 每次请求重新确认账号状态，使停用、角色变更和改密码对已有会话同样生效。 */</code> | 块注释中的说明：每次请求重新确认账号状态，使停用、角色变更和改密码对已有会话同样生效。；不参与运行。 |
| 16 | <code>public class SessionAccountFilter extends OncePerRequestFilter {</code> | 声明 类 `SessionAccountFilter`，后续内容定义它的字段或行为。 |
| 17 | <code>    private final UserRepository users;</code> | 声明类型为 `UserRepository` 的 常量/依赖字段 `users`；通过构造器或框架完成赋值。 |
| 18 | <code>    private final ObjectMapper mapper;</code> | 声明类型为 `ObjectMapper` 的 常量/依赖字段 `mapper`；通过构造器或框架完成赋值。 |
| 19 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 20 | <code>    public SessionAccountFilter(UserRepository users, ObjectMapper mapper) {</code> | 声明 `SessionAccountFilter` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 21 | <code>        this.users = users;</code> | 把构造器传入的依赖/字段保存到当前对象；this.users = users;，后续实例方法通过 this 的成员使用它。 |
| 22 | <code>        this.mapper = mapper;</code> | 把构造器传入的依赖/字段保存到当前对象；this.mapper = mapper;，后续实例方法通过 this 的成员使用它。 |
| 23 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 24 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 25 | <code>    @Override</code> | 表明方法重写了父类或接口方法，由编译器检查签名。 |
| 26 | <code>    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)</code> | 声明 `doFilterInternal` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 27 | <code>            throws ServletException, IOException {</code> | 声明方法可能抛出的受检异常：`throws ServletException, IOException`；调用者或框架需要处理这些异常。 |
| 28 | <code>        var authentication = SecurityContextHolder.getContext().getAuthentication();</code> | 让 Java 编译器根据右侧推断局部变量 `authentication` 的类型，值来自 `SecurityContextHolder.getContext().getAuthentication()`。 |
| 29 | <code>        if (authentication != null &amp;&amp; authentication.getPrincipal() instanceof MallPrincipal principal) {</code> | 判断条件 `authentication != null && authentication.getPrincipal() instanceof MallPrincipal principal`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 30 | <code>            var latest = users.findById(principal.user().id());</code> | 让 Java 编译器根据右侧推断局部变量 `latest` 的类型，值来自 `users.findById(principal.user().id())`。 |
| 31 | <code>            if (latest.isEmpty() &#124;&#124; !latest.get().user().enabled()</code> | 判断条件 `latest.isEmpty() &#124;&#124; !latest.get().user().enabled(`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 32 | <code>                    &#124;&#124; !latest.get().passwordHash().equals(principal.getPassword())) {</code> | 继续前一行 OR 条件：若数据库密码哈希与登录时哈希不同，则密码已经修改，旧会话应失效。 |
| 33 | <code>                SecurityContextHolder.clearContext();</code> | 清除当前请求的认证信息，避免无效账号继续被当作已登录。 |
| 34 | <code>                var session = request.getSession(false);</code> | 让 Java 编译器根据右侧推断局部变量 `session` 的类型，值来自 `request.getSession(false)`。 |
| 35 | <code>                if (session != null)</code> | 判断条件 `session != null`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 36 | <code>                    session.invalidate();</code> | 销毁服务器中的旧会话，防止它继续关联已失效的登录身份。 |
| 37 | <code>                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);</code> | 设置 HTTP 响应状态码：`response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);`，浏览器据此判断认证/业务成功或失败。 |
| 38 | <code>                response.setContentType(&quot;application/json;charset=UTF-8&quot;);</code> | 声明响应为 UTF-8 JSON，确保中文消息按正确编码读取。 |
| 39 | <code>                mapper.writeValue(response.getOutputStream(), Map.of(&quot;message&quot;, &quot;账号状态或密码已变化，请重新登录&quot;));</code> | 把 Java 对象序列化为 JSON 并写入 HTTP 响应输出流。 |
| 40 | <code>                return;</code> | 立即结束当前函数/方法，不再执行后面的新增、保存或状态更新。 |
| 41 | <code>            }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 42 | <code>            var refreshed = new MallPrincipal(latest.get());</code> | 让 Java 编译器根据右侧推断局部变量 `refreshed` 的类型，值来自 `new MallPrincipal(latest.get())`。 |
| 43 | <code>            var token = UsernamePasswordAuthenticationToken.authenticated(refreshed, null, refreshed.getAuthorities());</code> | 根据最新账号构造服务器端已认证 Authentication 对象：principal 为 refreshed，credentials 为 null（不保存明文密码），authorities 为最新角色；它不是浏览器 CSRF 令牌/JWT。 |
| 44 | <code>            token.setDetails(authentication.getDetails());</code> | 保留原认证对象的请求/会话附加信息。 |
| 45 | <code>            SecurityContextHolder.getContext().setAuthentication(token);</code> | 用刚从数据库加载的账号角色更新当前请求认证信息，使权限变化立即生效。 |
| 46 | <code>        }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 47 | <code>        chain.doFilter(request, response);</code> | 把请求交给后续安全过滤器/控制器继续处理；前面 return 的无效会话不会到这里。 |
| 48 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 49 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
