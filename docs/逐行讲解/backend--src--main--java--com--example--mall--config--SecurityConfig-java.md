# backend/src/main/java/com/example/mall/config/SecurityConfig.java

源文件：[`backend/src/main/java/com/example/mall/config/SecurityConfig.java`](../../商城管理系统/backend/src/main/java/com/example/mall/config/SecurityConfig.java)。以下行号以当前文件为准，空行也计入。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>package com.example.mall.config;</code> | 声明 Java 包名 `com.example.mall.config`，用于组织类并区分同名类型。 |
| 2 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 3 | <code>import com.example.mall.repository.UserRepository;</code> | 导入 `com.example.mall.repository.UserRepository`：让当前类可以直接使用该类型。 |
| 4 | <code>import com.fasterxml.jackson.databind.ObjectMapper;</code> | 导入 `com.fasterxml.jackson.databind.ObjectMapper`：在 Java 对象和 JSON 之间转换。 |
| 5 | <code>import jakarta.servlet.http.HttpServletResponse;</code> | 导入 `jakarta.servlet.http.HttpServletResponse`：设置 HTTP 响应内容。 |
| 6 | <code>import java.io.IOException;</code> | 导入 `java.io.IOException`：让当前类可以直接使用该类型。 |
| 7 | <code>import java.util.Map;</code> | 导入 `java.util.Map`：使用键值映射。 |
| 8 | <code>import org.springframework.context.annotation.Bean;</code> | 导入 `org.springframework.context.annotation.Bean`：将方法返回对象注册到 Spring 容器。 |
| 9 | <code>import org.springframework.context.annotation.Configuration;</code> | 导入 `org.springframework.context.annotation.Configuration`：声明 Spring 配置类。 |
| 10 | <code>import org.springframework.security.config.annotation.web.builders.HttpSecurity;</code> | 导入 `org.springframework.security.config.annotation.web.builders.HttpSecurity`：构建请求授权、会话、CSRF 等安全规则。 |
| 11 | <code>import org.springframework.security.core.userdetails.UserDetailsService;</code> | 导入 `org.springframework.security.core.userdetails.UserDetailsService`：按用户名加载认证所需的用户信息。 |
| 12 | <code>import org.springframework.security.core.userdetails.UsernameNotFoundException;</code> | 导入 `org.springframework.security.core.userdetails.UsernameNotFoundException`：让当前类可以直接使用该类型。 |
| 13 | <code>import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;</code> | 导入 `org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder`：使用带盐的 BCrypt 密码哈希。 |
| 14 | <code>import org.springframework.security.crypto.password.PasswordEncoder;</code> | 导入 `org.springframework.security.crypto.password.PasswordEncoder`：通过单向哈希校验和保存密码。 |
| 15 | <code>import org.springframework.security.web.SecurityFilterChain;</code> | 导入 `org.springframework.security.web.SecurityFilterChain`：配置 Spring Security HTTP 过滤器链。 |
| 16 | <code>import org.springframework.security.web.access.intercept.AuthorizationFilter;</code> | 导入 `org.springframework.security.web.access.intercept.AuthorizationFilter`：让当前类可以直接使用该类型。 |
| 17 | <code>import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;</code> | 导入 `org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler`：让当前类可以直接使用该类型。 |
| 18 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 19 | <code>@Configuration</code> | 把此类标记为配置类。 |
| 20 | <code>public class SecurityConfig {</code> | 声明 类 `SecurityConfig`，后续内容定义它的字段或行为。 |
| 21 | <code>    @Bean</code> | 把下方方法的返回对象注册为 Spring Bean。 |
| 22 | <code>    PasswordEncoder passwordEncoder() {</code> | 声明返回类型为 `PasswordEncoder` 的方法 `passwordEncoder`；提供 BCrypt 密码哈希工具。本行开始参数声明，后续可能继续分行。 |
| 23 | <code>        return new BCryptPasswordEncoder();</code> | 把 `new BCryptPasswordEncoder()` 返回给调用方，并结束当前函数/方法。 |
| 24 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 25 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 26 | <code>    @Bean</code> | 把下方方法的返回对象注册为 Spring Bean。 |
| 27 | <code>    UserDetailsService userDetailsService(UserRepository users) {</code> | 声明返回类型为 `UserDetailsService` 的方法 `userDetailsService`；按用户名从数据库加载认证对象。本行开始参数声明，后续可能继续分行。 |
| 28 | <code>        return username -&gt; users.findByUsername(username).map(MallPrincipal::new)</code> | 返回 UserDetailsService 的按用户名加载回调：Spring Security 登录时传入 username，仓储找到 UserAccount 后通过构造器引用包装成 MallPrincipal。 |
| 29 | <code>                .orElseThrow(() -&gt; new UsernameNotFoundException(&quot;用户名或密码错误&quot;));</code> | 上一步 Optional 为空时抛出 UsernameNotFoundException，交给认证失败处理器；有账号时返回 MallPrincipal 给 Spring Security 校验密码和启用状态。 |
| 30 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 31 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 32 | <code>    @Bean</code> | 把下方方法的返回对象注册为 Spring Bean。 |
| 33 | <code>    SecurityFilterChain securityFilterChain(HttpSecurity http, UserRepository users, ObjectMapper mapper)</code> | 声明返回类型为 `SecurityFilterChain` 的方法 `securityFilterChain`；配置登录、会话、CSRF、角色与错误响应。本行开始参数声明，后续可能继续分行。 |
| 34 | <code>            throws Exception {</code> | 声明方法可能抛出的受检异常：`throws Exception`；调用者或框架需要处理这些异常。 |
| 35 | <code>        // API 直接返回原始会话 CSRF token；前端通过 X-CSRF-TOKEN 请求头回传。</code> | 注释：API 直接返回原始会话 CSRF token；前端通过 X-CSRF-TOKEN 请求头回传。。此行用于解释代码，不会执行。 |
| 36 | <code>        var csrfHandler = new CsrfTokenRequestAttributeHandler();</code> | 让 Java 编译器根据右侧推断局部变量 `csrfHandler` 的类型，值来自 `new CsrfTokenRequestAttributeHandler()`。 |
| 37 | <code>        http.csrf(csrf -&gt; csrf.csrfTokenRequestHandler(csrfHandler))</code> | 定义 Java Lambda 回调 `http.csrf(csrf -> csrf.csrfTokenRequestHandler(csrfHandler))`；框架或集合处理调用它时，把左侧参数传入右侧操作。 |
| 38 | <code>                .authorizeHttpRequests(auth -&gt; auth</code> | 定义 Java Lambda 回调 `.authorizeHttpRequests(auth -> auth`；框架或集合处理调用它时，把左侧参数传入右侧操作。 |
| 39 | <code>                        .requestMatchers(&quot;/api/health&quot;, &quot;/api/auth/csrf&quot;, &quot;/api/auth/login&quot;, &quot;/error&quot;).permitAll()</code> | 这些 URL 不要求已登录：启动健康检查、获取 CSRF、登录和错误响应。permitAll 不会关闭写请求的 CSRF 检查。 |
| 40 | <code>                        .requestMatchers(&quot;/api/users&quot;, &quot;/api/users/**&quot;).hasRole(&quot;ADMIN&quot;).anyRequest().authenticated())</code> | 用户管理路径必须具有 ROLE_ADMIN；其余请求必须经过认证。权限在后端执行，不能靠隐藏菜单绕过。 |
| 41 | <code>                .formLogin(login -&gt; login.loginProcessingUrl(&quot;/api/auth/login&quot;)</code> | 定义 Java Lambda 回调 `.formLogin(login -> login.loginProcessingUrl("/api/auth/login")`；框架或集合处理调用它时，把左侧参数传入右侧操作。 |
| 42 | <code>                        .successHandler((request, response, authentication) -&gt; write(mapper, response, 200,</code> | 定义 Java Lambda 回调 `.successHandler((request, response, authentication) -> write(mapper, response, 200,`；框架或集合处理调用它时，把左侧参数传入右侧操作。 |
| 43 | <code>                                ((MallPrincipal) authentication.getPrincipal()).user()))</code> | 登录成功后从认证对象中取出公开 User 字段写为 JSON，密码哈希不会返回。 |
| 44 | <code>                        .failureHandler((request, response, exception) -&gt; write(mapper, response, 401,</code> | 定义 Java Lambda 回调 `.failureHandler((request, response, exception) -> write(mapper, response, 401,`；框架或集合处理调用它时，把左侧参数传入右侧操作。 |
| 45 | <code>                                Map.of(&quot;message&quot;, &quot;用户名或密码错误，或账号已停用&quot;))))</code> | 构造登录失败的 JSON 提示；统一原因避免通过错误消息判断账号是否存在。 |
| 46 | <code>                .logout(logout -&gt; logout.logoutUrl(&quot;/api/auth/logout&quot;).invalidateHttpSession(true)</code> | 定义 Java Lambda 回调 `.logout(logout -> logout.logoutUrl("/api/auth/logout").invalidateHttpSession(true)`；框架或集合处理调用它时，把左侧参数传入右侧操作。 |
| 47 | <code>                        .deleteCookies(&quot;JSESSIONID&quot;)</code> | 退出登录时让浏览器删除用于关联服务器会话的 JSESSIONID Cookie。 |
| 48 | <code>                        .logoutSuccessHandler((request, response, authentication) -&gt; write(mapper, response, 200,</code> | 定义 Java Lambda 回调 `.logoutSuccessHandler((request, response, authentication) -> write(mapper, response, 200,`；框架或集合处理调用它时，把左侧参数传入右侧操作。 |
| 49 | <code>                                Map.of(&quot;message&quot;, &quot;已退出登录&quot;))))</code> | 退出成功返回含 message 的 JSON；此时原服务器会话已经失效。 |
| 50 | <code>                .exceptionHandling(errors -&gt; errors.authenticationEntryPoint(</code> | 定义 Java Lambda 回调 `.exceptionHandling(errors -> errors.authenticationEntryPoint(`；框架或集合处理调用它时，把左侧参数传入右侧操作。 |
| 51 | <code>                        (request, response, exception) -&gt; write(mapper, response, 401, Map.of(&quot;message&quot;, &quot;请先登录&quot;)))</code> | 定义 Java Lambda 回调 `(request, response, exception) -> write(mapper, response, 401, Map.of("message", "请先登录")))`；框架或集合处理调用它时，把左侧参数传入右侧操作。 |
| 52 | <code>                        .accessDeniedHandler((request, response, exception) -&gt; write(mapper, response, 403,</code> | 定义 Java Lambda 回调 `.accessDeniedHandler((request, response, exception) -> write(mapper, response, 403,`；框架或集合处理调用它时，把左侧参数传入右侧操作。 |
| 53 | <code>                                Map.of(&quot;message&quot;, &quot;权限不足或安全令牌已失效，请刷新后重试&quot;))))</code> | 权限/CSRF 不通过时返回统一 403 错误消息。 |
| 54 | <code>                .requestCache(cache -&gt; cache.disable())</code> | 定义 Java Lambda 回调 `.requestCache(cache -> cache.disable())`；框架或集合处理调用它时，把左侧参数传入右侧操作。 |
| 55 | <code>                .addFilterBefore(new SessionAccountFilter(users, mapper), AuthorizationFilter.class);</code> | 把账号复查过滤器放在最终授权过滤器之前，保证账号停用、改密与角色变化影响已有会话。 |
| 56 | <code>        return http.build();</code> | 把 `http.build()` 返回给调用方，并结束当前函数/方法。 |
| 57 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 58 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 59 | <code>    private static void write(ObjectMapper mapper, HttpServletResponse response, int status, Object body)</code> | 声明 `write` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 60 | <code>            throws IOException {</code> | 声明方法可能抛出的受检异常：`throws IOException`；调用者或框架需要处理这些异常。 |
| 61 | <code>        response.setStatus(status);</code> | 设置 HTTP 响应状态码：`response.setStatus(status);`，浏览器据此判断认证/业务成功或失败。 |
| 62 | <code>        response.setContentType(&quot;application/json;charset=UTF-8&quot;);</code> | 声明响应为 UTF-8 JSON，确保中文消息按正确编码读取。 |
| 63 | <code>        mapper.writeValue(response.getOutputStream(), body);</code> | 把 Java 对象序列化为 JSON 并写入 HTTP 响应输出流。 |
| 64 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 65 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
