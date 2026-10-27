# backend/src/test/java/com/example/mall/MallIntegrationTest.java

源文件：[`backend/src/test/java/com/example/mall/MallIntegrationTest.java`](../../商城管理系统/backend/src/test/java/com/example/mall/MallIntegrationTest.java)。空行计入行号。

本文件按当前源码重新校对行号；未改变代码的已有讲解予以保留。

库存与图片的业务示例、整体流程见 [新功能说明](../库存管理与商品图片.md)。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>package com.example.mall;</code> | 声明 Java 包名 `com.example.mall`，用于组织类并区分同名类型。 |
| 2 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 3 | <code>import com.example.mall.config.DataInitializer;</code> | 导入 `com.example.mall.config.DataInitializer`：让当前类可以直接使用该类型。 |
| 4 | <code>import com.example.mall.model.Requests.OrderInput;</code> | 导入 `com.example.mall.model.Requests.OrderInput`：让当前类可以直接使用该类型。 |
| 5 | <code>import com.example.mall.model.Requests.OrderLineInput;</code> | 导入 `com.example.mall.model.Requests.OrderLineInput`：让当前类可以直接使用该类型。 |
| 6 | <code>import com.example.mall.service.OrderService;</code> | 导入 `com.example.mall.service.OrderService`：让当前类可以直接使用该类型。 |
| 7 | <code>import com.fasterxml.jackson.databind.JsonNode;</code> | 导入 `com.fasterxml.jackson.databind.JsonNode`：让当前类可以直接使用该类型。 |
| 8 | <code>import com.fasterxml.jackson.databind.ObjectMapper;</code> | 导入 `com.fasterxml.jackson.databind.ObjectMapper`：在 Java 对象和 JSON 之间转换。 |
| 9 | <code>import java.math.BigDecimal;</code> | 导入 `java.math.BigDecimal`：使用十进制数处理金额，避免二进制浮点误差。 |
| 10 | <code>import java.util.List;</code> | 导入 `java.util.List`：使用有序集合。 |
| 11 | <code>import java.util.Map;</code> | 导入 `java.util.Map`：使用键值映射。 |
| 12 | <code>import java.util.concurrent.CountDownLatch;</code> | 导入 `java.util.concurrent.CountDownLatch`：让当前类可以直接使用该类型。 |
| 13 | <code>import java.util.concurrent.Executors;</code> | 导入 `java.util.concurrent.Executors`：让当前类可以直接使用该类型。 |
| 14 | <code>import java.util.concurrent.TimeUnit;</code> | 导入 `java.util.concurrent.TimeUnit`：让当前类可以直接使用该类型。 |
| 15 | <code>import org.junit.jupiter.api.Test;</code> | 导入 `org.junit.jupiter.api.Test`：让当前类可以直接使用该类型。 |
| 16 | <code>import org.springframework.beans.factory.annotation.Autowired;</code> | 导入 `org.springframework.beans.factory.annotation.Autowired`：让当前类可以直接使用该类型。 |
| 17 | <code>import org.springframework.boot.DefaultApplicationArguments;</code> | 导入 `org.springframework.boot.DefaultApplicationArguments`：让当前类可以直接使用该类型。 |
| 18 | <code>import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;</code> | 导入 `org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc`：让当前类可以直接使用该类型。 |
| 19 | <code>import org.springframework.boot.test.context.SpringBootTest;</code> | 导入 `org.springframework.boot.test.context.SpringBootTest`：让当前类可以直接使用该类型。 |
| 20 | <code>import org.springframework.http.MediaType;</code> | 导入 `org.springframework.http.MediaType`：让当前类可以直接使用该类型。 |
| 21 | <code>import org.springframework.jdbc.core.JdbcTemplate;</code> | 导入 `org.springframework.jdbc.core.JdbcTemplate`：执行参数化 SQL 并把结果映射为 Java 值。 |
| 22 | <code>import org.springframework.mock.web.MockHttpSession;</code> | 导入 `org.springframework.mock.web.MockHttpSession`：让当前类可以直接使用该类型。 |
| 23 | <code>import org.springframework.test.web.servlet.MockMvc;</code> | 导入 `org.springframework.test.web.servlet.MockMvc`：让当前类可以直接使用该类型。 |
| 24 | <code>import org.springframework.test.web.servlet.MvcResult;</code> | 导入 `org.springframework.test.web.servlet.MvcResult`：让当前类可以直接使用该类型。 |
| 25 | <code>import org.springframework.test.context.TestExecutionListeners;</code> | 导入 `org.springframework.test.context.TestExecutionListeners`：让当前类可以直接使用该类型。 |
| 26 | <code>import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;</code> | 导入 `org.springframework.test.context.support.DependencyInjectionTestExecutionListener`：让当前类可以直接使用该类型。 |
| 27 | <code>import org.springframework.test.context.transaction.TransactionalTestExecutionListener;</code> | 导入 `org.springframework.test.context.transaction.TransactionalTestExecutionListener`：让当前类可以直接使用该类型。 |
| 28 | <code>import org.springframework.test.context.web.ServletTestExecutionListener;</code> | 导入 `org.springframework.test.context.web.ServletTestExecutionListener`：让当前类可以直接使用该类型。 |
| 29 | <code>import org.springframework.transaction.annotation.Transactional;</code> | 导入 `org.springframework.transaction.annotation.Transactional`：声明方法或类的数据库事务边界。 |
| 30 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 31 | <code>import static org.assertj.core.api.Assertions.assertThat;</code> | 导入 `org.assertj.core.api.Assertions.assertThat`：让当前类可以直接使用该类型的静态成员。 |
| 32 | <code>import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;</code> | 导入 `org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*`：让当前类可以直接使用该类型的静态成员。 |
| 33 | <code>import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;</code> | 导入 `org.springframework.test.web.servlet.result.MockMvcResultMatchers.*`：让当前类可以直接使用该类型的静态成员。 |
| 34 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 35 | <code>/** 使用真实 Security 过滤器和测试 SQL 数据库，不使用伪造 Repository。 */</code> | 块注释中的说明：使用真实 Security 过滤器和测试 SQL 数据库，不使用伪造 Repository。；不参与运行。 |
| 36 | <code>@SpringBootTest(properties = {</code> | 应用 `SpringBootTest` 注解为下方声明附加框架规则。括号内参数为 `properties = `。 |
| 37 | <code>        &quot;spring.datasource.url=jdbc:h2:mem:malltests;MODE=MySQL;DATABASE_TO_LOWER=TRUE;DB_CLOSE_DELAY=-1;LOCK_TIMEOUT=10000&quot;,</code> | 为集成测试覆盖 Spring 配置，使用独立内存 H2 测试库，不读取或修改正式 MySQL 数据。 |
| 38 | <code>        &quot;spring.datasource.driver-class-name=org.h2.Driver&quot;, &quot;spring.datasource.username=sa&quot;,</code> | 为集成测试覆盖 Spring 配置，使用独立内存 H2 测试库，不读取或修改正式 MySQL 数据。 |
| 39 | <code>        &quot;spring.datasource.password=&quot;, &quot;mall.upload-directory=./target/test-uploads&quot; })</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 40 | <code>@AutoConfigureMockMvc</code> | 应用 `AutoConfigureMockMvc` 注解为下方声明附加框架规则。 |
| 41 | <code>@TestExecutionListeners(listeners = { ServletTestExecutionListener.class,</code> | 应用 `TestExecutionListeners` 注解为下方声明附加框架规则。括号内参数为 `listeners = { ServletTestExecutionListener.class`。 |
| 42 | <code>        DependencyInjectionTestExecutionListener.class,</code> | 为集成测试启用 Spring 依赖注入监听器，把真实 MVC/数据库/业务服务注入测试字段。 |
| 43 | <code>        TransactionalTestExecutionListener.class }, mergeMode = TestExecutionListeners.MergeMode.REPLACE_DEFAULTS)</code> | 启用测试事务监听器，并替换默认监听器集合；本项目不用 mock，避免无关 Mockito 动态附加。 |
| 44 | <code>class MallIntegrationTest {</code> | 声明 类 `MallIntegrationTest`，后续内容定义它的字段或行为。 |
| 45 | <code>    @Autowired</code> | 让 Spring 注入匹配依赖。 |
| 46 | <code>    MockMvc mvc;</code> | 声明测试依赖字段，Spring 测试容器通过 @Autowired 注入实际对象。 |
| 47 | <code>    @Autowired</code> | 让 Spring 注入匹配依赖。 |
| 48 | <code>    ObjectMapper mapper;</code> | 声明测试依赖字段，Spring 测试容器通过 @Autowired 注入实际对象。 |
| 49 | <code>    @Autowired</code> | 让 Spring 注入匹配依赖。 |
| 50 | <code>    JdbcTemplate jdbc;</code> | 声明测试依赖字段，Spring 测试容器通过 @Autowired 注入实际对象。 |
| 51 | <code>    @Autowired</code> | 让 Spring 注入匹配依赖。 |
| 52 | <code>    OrderService orders;</code> | 声明测试依赖字段，Spring 测试容器通过 @Autowired 注入实际对象。 |
| 53 | <code>    @Autowired</code> | 让 Spring 注入匹配依赖。 |
| 54 | <code>    DataInitializer initializer;</code> | 声明测试依赖字段，Spring 测试容器通过 @Autowired 注入实际对象。 |
| 55 | <code>    @Autowired</code> | 让 Spring 注入匹配依赖。 |
| 56 | <code>    com.example.mall.service.InventoryService inventory;</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 57 | <code>    @Autowired</code> | 让 Spring 注入匹配依赖。 |
| 58 | <code>    com.example.mall.config.OpeningInventory openingInventory;</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 59 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 60 | <code>    @Test</code> | 应用 `Test` 注解为下方声明附加框架规则。 |
| 61 | <code>    void authenticationRequiresCsrfAndRotatesTokenAfterLoginAndLogout() throws Exception {</code> | 声明一个自动化测试用例 `authenticationRequiresCsrfAndRotatesTokenAfterLoginAndLogout`；JUnit 在测试阶段调用它。 |
| 62 | <code>        mvc.perform(get(&quot;/api/health&quot;)).andExpect(status().isOk())</code> | 通过 MockMvc 模拟 HTTP 请求 `mvc.perform(get("/api/health")).andExpect(status().isOk())`，经过真实 Spring MVC/安全过滤链，后续断言检查响应。 |
| 63 | <code>                .andExpect(jsonPath(&quot;$.application&quot;).value(&quot;mall-admin&quot;));</code> | 断言请求响应满足 `jsonPath("$.application").value("mall-admin"));；status 检查状态码，jsonPath 检查 JSON 字段。 |
| 64 | <code>        mvc.perform(get(&quot;/api/auth/me&quot;)).andExpect(status().isUnauthorized());</code> | 通过 MockMvc 模拟 HTTP 请求 `mvc.perform(get("/api/auth/me")).andExpect(status().isUnauthorized());`，经过真实 Spring MVC/安全过滤链，后续断言检查响应。 |
| 65 | <code>        mvc.perform(post(&quot;/api/auth/login&quot;).param(&quot;username&quot;, &quot;admin&quot;).param(&quot;password&quot;, &quot;Admin@123456&quot;))</code> | 通过 MockMvc 模拟 HTTP 请求 `mvc.perform(post("/api/auth/login").param("username", "admin").param("password", "Admin@123456"))`，经过真实 Spring MVC/安全过滤链，后续断言检查响应。 |
| 66 | <code>                .andExpect(status().isForbidden()).andExpect(jsonPath(&quot;$.message&quot;).isString());</code> | 断言请求响应满足 `status().isForbidden()).andExpect(jsonPath("$.message").isString());；status 检查状态码，jsonPath 检查 JSON 字段。 |
| 67 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 68 | <code>        MvcResult tokenResult = mvc.perform(get(&quot;/api/auth/csrf&quot;)).andExpect(status().isOk()).andReturn();</code> | 执行测试模拟请求并取得响应，供后续读取会话、JSON 或业务 ID 使用。 |
| 69 | <code>        var session = (MockHttpSession) tokenResult.getRequest().getSession(false);</code> | 让 Java 编译器根据右侧推断局部变量 `session` 的类型，值来自 `(MockHttpSession) tokenResult.getRequest().getSession(false)`。 |
| 70 | <code>        String oldToken = json(tokenResult).get(&quot;token&quot;).asText();</code> | 声明 `oldToken`，类型 `String`，保存 `json(tokenResult).get("token").asText()` 的结果。 |
| 71 | <code>        mvc.perform(post(&quot;/api/auth/login&quot;).session(session).header(&quot;X-CSRF-TOKEN&quot;, oldToken).param(&quot;username&quot;, &quot;admin&quot;)</code> | 通过 MockMvc 模拟 HTTP 请求 `mvc.perform(post("/api/auth/login").session(session).header("X-CSRF-TOKEN", oldToken).param("username", "admin")`，经过真实 Spring MVC/安全过滤链，后续断言检查响应。 |
| 72 | <code>                .param(&quot;password&quot;, &quot;wrong-password&quot;)).andExpect(status().isUnauthorized());</code> | 为模拟请求添加表单参数；登录使用 username/password，随后检查认证响应。 |
| 73 | <code>        mvc.perform(post(&quot;/api/auth/login&quot;).session(session).header(&quot;X-CSRF-TOKEN&quot;, csrf(session))</code> | 通过 MockMvc 模拟 HTTP 请求 `mvc.perform(post("/api/auth/login").session(session).header("X-CSRF-TOKEN", csrf(session))`，经过真实 Spring MVC/安全过滤链，后续断言检查响应。 |
| 74 | <code>                .param(&quot;username&quot;, &quot;admin&quot;).param(&quot;password&quot;, &quot;Admin@123456&quot;)).andExpect(status().isOk())</code> | 为模拟请求添加表单参数；登录使用 username/password，随后检查认证响应。 |
| 75 | <code>                .andExpect(jsonPath(&quot;$.role&quot;).value(&quot;ADMIN&quot;)).andExpect(jsonPath(&quot;$.passwordHash&quot;).doesNotExist());</code> | 断言请求响应满足 `jsonPath("$.role").value("ADMIN")).andExpect(jsonPath("$.passwordHash").doesNotExist());；status 检查状态码，jsonPath 检查 JSON 字段。 |
| 76 | <code>        String newToken = csrf(session);</code> | 声明 `newToken`，类型 `String`，保存 `csrf(session)` 的结果。 |
| 77 | <code>        assertThat(newToken).isNotEqualTo(oldToken);</code> | 对实际结果执行 AssertJ 断言：`assertThat(newToken).isNotEqualTo(oldToken);`；不符合期望时该测试失败。 |
| 78 | <code>        mvc.perform(post(&quot;/api/categories&quot;).session(session).header(&quot;X-CSRF-TOKEN&quot;, oldToken)</code> | 通过 MockMvc 模拟 HTTP 请求 `mvc.perform(post("/api/categories").session(session).header("X-CSRF-TOKEN", oldToken)`，经过真实 Spring MVC/安全过滤链，后续断言检查响应。 |
| 79 | <code>                .contentType(MediaType.APPLICATION_JSON).content(&quot;{\&quot;name\&quot;:\&quot;无效令牌分类\&quot;}&quot;))</code> | 声明测试请求正文为 JSON，并传入待测业务数据；后续断言检查成功/拒绝行为。 |
| 80 | <code>                .andExpect(status().isForbidden());</code> | 断言请求响应满足 `status().isForbidden());；status 检查状态码，jsonPath 检查 JSON 字段。 |
| 81 | <code>        mvc.perform(post(&quot;/api/auth/logout&quot;).session(session).header(&quot;X-CSRF-TOKEN&quot;, newToken))</code> | 通过 MockMvc 模拟 HTTP 请求 `mvc.perform(post("/api/auth/logout").session(session).header("X-CSRF-TOKEN", newToken))`，经过真实 Spring MVC/安全过滤链，后续断言检查响应。 |
| 82 | <code>                .andExpect(status().isOk());</code> | 断言请求响应满足 `status().isOk());；status 检查状态码，jsonPath 检查 JSON 字段。 |
| 83 | <code>        mvc.perform(get(&quot;/api/auth/me&quot;)).andExpect(status().isUnauthorized());</code> | 通过 MockMvc 模拟 HTTP 请求 `mvc.perform(get("/api/auth/me")).andExpect(status().isUnauthorized());`，经过真实 Spring MVC/安全过滤链，后续断言检查响应。 |
| 84 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 85 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 86 | <code>    @Test</code> | 应用 `Test` 注解为下方声明附加框架规则。 |
| 87 | <code>    @Transactional</code> | 为下方业务操作开启事务，运行时异常时回滚数据库修改。 |
| 88 | <code>    void staffCanManageBusinessButCannotManageUsers() throws Exception {</code> | 声明一个自动化测试用例 `staffCanManageBusinessButCannotManageUsers`；JUnit 在测试阶段调用它。 |
| 89 | <code>        var session = login(&quot;staff&quot;, &quot;Staff@123456&quot;);</code> | 让 Java 编译器根据右侧推断局部变量 `session` 的类型，值来自 `login("staff", "Staff@123456")`。 |
| 90 | <code>        mvc.perform(get(&quot;/api/users&quot;).session(session)).andExpect(status().isForbidden());</code> | 通过 MockMvc 模拟 HTTP 请求 `mvc.perform(get("/api/users").session(session)).andExpect(status().isForbidden());`，经过真实 Spring MVC/安全过滤链，后续断言检查响应。 |
| 91 | <code>        var category = create(session, &quot;/api/categories&quot;, Map.of(&quot;name&quot;, &quot;员工分类&quot;, &quot;description&quot;, &quot;员工可维护&quot;), 201);</code> | 让 Java 编译器根据右侧推断局部变量 `category` 的类型，值来自 `create(session, "/api/categories", Map.of("name", "员工分类", "description", "员工可维护"), 201)`。 |
| 92 | <code>        long id = category.get(&quot;id&quot;).asLong();</code> | 声明 `id`，类型 `long`，保存 `category.get("id").asLong()` 的结果。`id`：稳定记录主键 |
| 93 | <code>        mvc.perform(put(&quot;/api/categories/&quot; + id).session(session).header(&quot;X-CSRF-TOKEN&quot;, csrf(session))</code> | 通过 MockMvc 模拟 HTTP 请求 `mvc.perform(put("/api/categories/" + id).session(session).header("X-CSRF-TOKEN", csrf(session))`，经过真实 Spring MVC/安全过滤链，后续断言检查响应。 |
| 94 | <code>                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(Map.of(&quot;name&quot;, &quot;员工分类修改&quot;))))</code> | 声明测试请求正文为 JSON，并传入待测业务数据；后续断言检查成功/拒绝行为。 |
| 95 | <code>                .andExpect(status().isOk()).andExpect(jsonPath(&quot;$.name&quot;).value(&quot;员工分类修改&quot;));</code> | 断言请求响应满足 `status().isOk()).andExpect(jsonPath("$.name").value("员工分类修改"));；status 检查状态码，jsonPath 检查 JSON 字段。 |
| 96 | <code>        mvc.perform(delete(&quot;/api/categories/&quot; + id).session(session).header(&quot;X-CSRF-TOKEN&quot;, csrf(session)))</code> | 通过 MockMvc 模拟 HTTP 请求 `mvc.perform(delete("/api/categories/" + id).session(session).header("X-CSRF-TOKEN", csrf(session)))`，经过真实 Spring MVC/安全过滤链，后续断言检查响应。 |
| 97 | <code>                .andExpect(status().isNoContent());</code> | 断言请求响应满足 `status().isNoContent());；status 检查状态码，jsonPath 检查 JSON 字段。 |
| 98 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 99 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 100 | <code>    @Test</code> | 应用 `Test` 注解为下方声明附加框架规则。 |
| 101 | <code>    @Transactional</code> | 为下方业务操作开启事务，运行时异常时回滚数据库修改。 |
| 102 | <code>    void productValidationSearchPaginationAndReferenceRules() throws Exception {</code> | 声明一个自动化测试用例 `productValidationSearchPaginationAndReferenceRules`；JUnit 在测试阶段调用它。 |
| 103 | <code>        var session = login(&quot;admin&quot;, &quot;Admin@123456&quot;);</code> | 让 Java 编译器根据右侧推断局部变量 `session` 的类型，值来自 `login("admin", "Admin@123456")`。 |
| 104 | <code>        long categoryId = firstCategory();</code> | 声明 `categoryId`，类型 `long`，保存 `firstCategory()` 的结果。`categoryId`：关联分类主键 |
| 105 | <code>        for (int number = 1; number &lt;= 3; number++) {</code> | 按 `int number = 1; number <= 3; number++` 逐项/逐次循环，执行花括号中的处理。 |
| 106 | <code>            create(session, &quot;/api/products&quot;,</code> | 调用测试辅助方法创建记录/发送无效输入，并核对本次期望状态码：`create(session, "/api/products",`。 |
| 107 | <code>                    productInput(&quot;分页商品&quot; + number, &quot;PAGE-&quot; + number, categoryId, &quot;8.80&quot;, number), 201);</code> | 构造商品测试请求，依次指定名称、SKU、分类、单价和库存；末尾状态码说明预期创建/拒绝结果。 |
| 108 | <code>        }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 109 | <code>        mvc.perform(</code> | 通过 MockMvc 模拟 HTTP 请求 `mvc.perform(`，经过真实 Spring MVC/安全过滤链，后续断言检查响应。 |
| 110 | <code>                get(&quot;/api/products&quot;).session(session).param(&quot;keyword&quot;, &quot;分页商品&quot;).param(&quot;size&quot;, &quot;2&quot;).param(&quot;page&quot;, &quot;2&quot;))</code> | 继续构造 GET 测试请求，附加会话与查询参数供分页/搜索断言检查。 |
| 111 | <code>                .andExpect(status().isOk()).andExpect(jsonPath(&quot;$.total&quot;).value(3))</code> | 断言请求响应满足 `status().isOk()).andExpect(jsonPath("$.total").value(3))；status 检查状态码，jsonPath 检查 JSON 字段。 |
| 112 | <code>                .andExpect(jsonPath(&quot;$.page&quot;).value(2)).andExpect(jsonPath(&quot;$.items.length()&quot;).value(1));</code> | 断言请求响应满足 `jsonPath("$.page").value(2)).andExpect(jsonPath("$.items.length()").value(1));；status 检查状态码，jsonPath 检查 JSON 字段。 |
| 113 | <code>        mvc.perform(get(&quot;/api/products&quot;).session(session).param(&quot;size&quot;, &quot;101&quot;)).andExpect(status().isBadRequest());</code> | 通过 MockMvc 模拟 HTTP 请求 `mvc.perform(get("/api/products").session(session).param("size", "101")).andExpect(status().isBadRequest());`，经过真实 Spring MVC/安全过滤链，后续断言检查响应。 |
| 114 | <code>        mvc.perform(get(&quot;/api/products&quot;).session(session).param(&quot;status&quot;, &quot;HACKED&quot;)).andExpect(status().isBadRequest());</code> | 通过 MockMvc 模拟 HTTP 请求 `mvc.perform(get("/api/products").session(session).param("status", "HACKED")).andExpect(status().isBadRequest());`，经过真实 Spring MVC/安全过滤链，后续断言检查响应。 |
| 115 | <code>        mvc.perform(get(&quot;/api/products&quot;).session(session).param(&quot;keyword&quot;, &quot;&#x27; OR 1=1 --&quot;)).andExpect(status().isOk())</code> | 通过 MockMvc 模拟 HTTP 请求 `mvc.perform(get("/api/products").session(session).param("keyword", "' OR 1=1 --")).andExpect(status().isOk())`，经过真实 Spring MVC/安全过滤链，后续断言检查响应。 |
| 116 | <code>                .andExpect(jsonPath(&quot;$.total&quot;).value(0));</code> | 断言请求响应满足 `jsonPath("$.total").value(0));；status 检查状态码，jsonPath 检查 JSON 字段。 |
| 117 | <code>        create(session, &quot;/api/products&quot;, productInput(&quot;无效小数&quot;, &quot;BAD-PRICE&quot;, categoryId, &quot;1.001&quot;, 1), 400);</code> | 调用测试辅助方法创建记录/发送无效输入，并核对本次期望状态码：`create(session, "/api/products", productInput("无效小数", "BAD-PRICE", categoryId, "1.001", 1), 400);`。 |
| 118 | <code>        create(session, &quot;/api/products&quot;, Map.of(&quot;name&quot;, &quot;不能设置库存&quot;, &quot;sku&quot;, &quot;BAD-STOCK&quot;, &quot;categoryId&quot;, categoryId,</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 119 | <code>                &quot;price&quot;, 1, &quot;stock&quot;, 5, &quot;status&quot;, &quot;ON_SALE&quot;), 400);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 120 | <code>        create(session, &quot;/api/products&quot;, Map.of(&quot;name&quot;, &quot;小数库存&quot;, &quot;sku&quot;, &quot;FRACTION-STOCK&quot;, &quot;categoryId&quot;, categoryId,</code> | 调用测试辅助方法创建记录/发送无效输入，并核对本次期望状态码：`create(session, "/api/products", Map.of("name", "小数库存", "sku", "FRACTION-STOCK", "categoryId", categoryId,`。 |
| 121 | <code>                &quot;price&quot;, 1, &quot;stock&quot;, 0.1, &quot;status&quot;, &quot;ON_SALE&quot;), 400);</code> | 继续构造本次测试的键值请求数据：`"price", 1, "stock", 0.1, "status", "ON_SALE"), 400);`，供模拟接口调用使用。 |
| 122 | <code>        create(session, &quot;/api/products&quot;, productInput(&quot;零元赠品&quot;, &quot;ZERO-GIFT&quot;, categoryId, &quot;0.00&quot;, 1), 201);</code> | 调用测试辅助方法创建记录/发送无效输入，并核对本次期望状态码：`create(session, "/api/products", productInput("零元赠品", "ZERO-GIFT", categoryId, "0.00", 1), 201);`。 |
| 123 | <code>        create(session, &quot;/api/products&quot;, productInput(&quot;重复编号&quot;, &quot;ZERO-GIFT&quot;, categoryId, &quot;1.00&quot;, 1), 409);</code> | 调用测试辅助方法创建记录/发送无效输入，并核对本次期望状态码：`create(session, "/api/products", productInput("重复编号", "ZERO-GIFT", categoryId, "1.00", 1), 409);`。 |
| 124 | <code>        mvc.perform(delete(&quot;/api/categories/&quot; + categoryId).session(session).header(&quot;X-CSRF-TOKEN&quot;, csrf(session)))</code> | 通过 MockMvc 模拟 HTTP 请求 `mvc.perform(delete("/api/categories/" + categoryId).session(session).header("X-CSRF-TOKEN", csrf(session)))`，经过真实 Spring MVC/安全过滤链，后续断言检查响应。 |
| 125 | <code>                .andExpect(status().isConflict());</code> | 断言请求响应满足 `status().isConflict());；status 检查状态码，jsonPath 检查 JSON 字段。 |
| 126 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 127 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 128 | <code>    @Test</code> | 应用 `Test` 注解为下方声明附加框架规则。 |
| 129 | <code>    void failedOrderRollsBackEveryStockDeduction() throws Exception {</code> | 声明一个自动化测试用例 `failedOrderRollsBackEveryStockDeduction`；JUnit 在测试阶段调用它。 |
| 130 | <code>        var session = login(&quot;admin&quot;, &quot;Admin@123456&quot;);</code> | 让 Java 编译器根据右侧推断局部变量 `session` 的类型，值来自 `login("admin", "Admin@123456")`。 |
| 131 | <code>        long first = create(session, &quot;/api/products&quot;, productInput(&quot;有货商品&quot;, &quot;ROLLBACK-A&quot;, firstCategory(), &quot;5.00&quot;, 5),</code> | 声明 `first`，类型 `long`，保存 `create(session, "/api/products", productInput("有货商品", "ROLLBACK-A", firstCategory(), "5.00", 5),` 的结果。 |
| 132 | <code>                201).get(&quot;id&quot;).asLong();</code> | 结束测试辅助调用并指定预期 HTTP 状态码；201 表示创建成功，其返回 JSON 的 id 用作后续测试主键。 |
| 133 | <code>        long second = create(session, &quot;/api/products&quot;, productInput(&quot;缺货商品&quot;, &quot;ROLLBACK-B&quot;, firstCategory(), &quot;7.00&quot;, 0),</code> | 声明 `second`，类型 `long`，保存 `create(session, "/api/products", productInput("缺货商品", "ROLLBACK-B", firstCategory(), "7.00", 0),` 的结果。 |
| 134 | <code>                201).get(&quot;id&quot;).asLong();</code> | 结束测试辅助调用并指定预期 HTTP 状态码；201 表示创建成功，其返回 JSON 的 id 用作后续测试主键。 |
| 135 | <code>        try {</code> | 开始尝试执行可能失败的操作；抛出的异常交给后续 catch 统一处理。 |
| 136 | <code>            long count = jdbc.queryForObject(&quot;SELECT COUNT(*) FROM mall_order&quot;, Long.class);</code> | 声明 `count`，类型 `long`，保存 `jdbc.queryForObject("SELECT COUNT(*) FROM mall_order", Long.class)` 的结果。 |
| 137 | <code>            long movementCount = jdbc.queryForObject(&quot;SELECT COUNT(*) FROM stock_movement&quot;, Long.class);</code> | 查询数据库并映射为返回对象；查询参数与 SQL 结构分开传递。 |
| 138 | <code>            create(session, &quot;/api/orders&quot;, orderInput(</code> | 调用测试辅助方法创建记录/发送无效输入，并核对本次期望状态码：`create(session, "/api/orders", orderInput(`。 |
| 139 | <code>                    List.of(Map.of(&quot;productId&quot;, first, &quot;quantity&quot;, 2), Map.of(&quot;productId&quot;, second, &quot;quantity&quot;, 1))),</code> | 构造订单商品行测试列表，明确每行 productId 与 quantity，用于检验重复商品、库存不足或正常下单。 |
| 140 | <code>                    409);</code> | 结束测试辅助调用并指定预期 HTTP 状态码；201 表示创建成功，其返回 JSON 的 id 用作后续测试主键。 |
| 141 | <code>            assertThat(stock(first)).isEqualTo(5);</code> | 对实际结果执行 AssertJ 断言：`assertThat(stock(first)).isEqualTo(5);`；不符合期望时该测试失败。 |
| 142 | <code>            assertThat(jdbc.queryForObject(&quot;SELECT COUNT(*) FROM stock_movement&quot;, Long.class)).isEqualTo(movementCount);</code> | 查询数据库并映射为返回对象；查询参数与 SQL 结构分开传递。 |
| 143 | <code>            assertThat(jdbc.queryForObject(&quot;SELECT COUNT(*) FROM mall_order&quot;, Long.class)).isEqualTo(count);</code> | 对实际结果执行 AssertJ 断言：`assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM mall_order", Long.class)).isEqualTo(count);`；不符合期望时该测试失败。 |
| 144 | <code>            create(session, &quot;/api/orders&quot;, orderInput(</code> | 调用测试辅助方法创建记录/发送无效输入，并核对本次期望状态码：`create(session, "/api/orders", orderInput(`。 |
| 145 | <code>                    List.of(Map.of(&quot;productId&quot;, first, &quot;quantity&quot;, 1), Map.of(&quot;productId&quot;, first, &quot;quantity&quot;, 1))),</code> | 构造订单商品行测试列表，明确每行 productId 与 quantity，用于检验重复商品、库存不足或正常下单。 |
| 146 | <code>                    400);</code> | 结束测试辅助调用并指定预期 HTTP 状态码；201 表示创建成功，其返回 JSON 的 id 用作后续测试主键。 |
| 147 | <code>            create(session, &quot;/api/orders&quot;, orderInput(List.of(Map.of(&quot;productId&quot;, first, &quot;quantity&quot;, 0))), 400);</code> | 调用测试辅助方法创建记录/发送无效输入，并核对本次期望状态码：`create(session, "/api/orders", orderInput(List.of(Map.of("productId", first, "quantity", 0))), 400);`。 |
| 148 | <code>            create(session, &quot;/api/orders&quot;, orderInput(List.of(Map.of(&quot;productId&quot;, first, &quot;quantity&quot;, 0.1))), 400);</code> | 调用测试辅助方法创建记录/发送无效输入，并核对本次期望状态码：`create(session, "/api/orders", orderInput(List.of(Map.of("productId", first, "quantity", 0.1))), 400);`。 |
| 149 | <code>        } finally {</code> | 无论前面的操作成功还是失败，都执行此处的清理/状态恢复代码。 |
| 150 | <code>            jdbc.update(&quot;DELETE FROM stock_movement WHERE product_id IN (?, ?)&quot;, first, second);</code> | 执行参数化数据库写入，后面的参数按问号顺序绑定；不把用户输入直接拼成 SQL。 |
| 151 | <code>            jdbc.update(&quot;DELETE FROM product WHERE id IN (?, ?)&quot;, first, second);</code> | 执行修改 SQL，返回影响行数：`jdbc.update("DELETE FROM product WHERE id IN (?, ?)", first, second);`。? 占位符对应后续传入参数。 |
| 152 | <code>        }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 153 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 154 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 155 | <code>    @Test</code> | 应用 `Test` 注解为下方声明附加框架规则。 |
| 156 | <code>    @Transactional</code> | 为下方业务操作开启事务，运行时异常时回滚数据库修改。 |
| 157 | <code>    void cancellationRestoresStockOnlyOnceAndOrderKeepsPriceSnapshot() throws Exception {</code> | 声明一个自动化测试用例 `cancellationRestoresStockOnlyOnceAndOrderKeepsPriceSnapshot`；JUnit 在测试阶段调用它。 |
| 158 | <code>        var session = login(&quot;admin&quot;, &quot;Admin@123456&quot;);</code> | 让 Java 编译器根据右侧推断局部变量 `session` 的类型，值来自 `login("admin", "Admin@123456")`。 |
| 159 | <code>        long productId = create(session, &quot;/api/products&quot;, productInput(&quot;快照商品&quot;, &quot;SNAPSHOT&quot;, firstCategory(), &quot;0.10&quot;, 10),</code> | 声明 `productId`，类型 `long`，保存 `create(session, "/api/products", productInput("快照商品", "SNAPSHOT", firstCategory(), "0.10", 10),` 的结果。`productId`：关联商品主键 |
| 160 | <code>                201).get(&quot;id&quot;).asLong();</code> | 结束测试辅助调用并指定预期 HTTP 状态码；201 表示创建成功，其返回 JSON 的 id 用作后续测试主键。 |
| 161 | <code>        var order = create(session, &quot;/api/orders&quot;, orderInput(List.of(Map.of(&quot;productId&quot;, productId, &quot;quantity&quot;, 3))),</code> | 让 Java 编译器根据右侧推断局部变量 `order` 的类型，值来自 `create(session, "/api/orders", orderInput(List.of(Map.of("productId", productId, "quantity", 3))),`。 |
| 162 | <code>                201);</code> | 结束测试辅助调用并指定预期 HTTP 状态码；201 表示创建成功，其返回 JSON 的 id 用作后续测试主键。 |
| 163 | <code>        long orderId = order.get(&quot;id&quot;).asLong();</code> | 声明 `orderId`，类型 `long`，保存 `order.get("id").asLong()` 的结果。 |
| 164 | <code>        assertThat(new BigDecimal(order.get(&quot;totalAmount&quot;).asText())).isEqualByComparingTo(&quot;0.30&quot;);</code> | 对实际结果执行 AssertJ 断言：`assertThat(new BigDecimal(order.get("totalAmount").asText())).isEqualByComparingTo("0.30");`；不符合期望时该测试失败。 |
| 165 | <code>        assertThat(stock(productId)).isEqualTo(7);</code> | 对实际结果执行 AssertJ 断言：`assertThat(stock(productId)).isEqualTo(7);`；不符合期望时该测试失败。 |
| 166 | <code>        mvc.perform(put(&quot;/api/products/&quot; + productId + &quot;/status&quot;).session(session).header(&quot;X-CSRF-TOKEN&quot;, csrf(session))</code> | 通过 MockMvc 模拟 HTTP 请求 `mvc.perform(put("/api/products/" + productId + "/status").session(session).header("X-CSRF-TOKEN", csrf(session))`，经过真实 Spring MVC/安全过滤链，后续断言检查响应。 |
| 167 | <code>                .contentType(MediaType.APPLICATION_JSON).content(&quot;{\&quot;status\&quot;:\&quot;OFF_SALE\&quot;}&quot;))</code> | 声明测试请求正文为 JSON，并传入待测业务数据；后续断言检查成功/拒绝行为。 |
| 168 | <code>                .andExpect(status().isOk()).andExpect(jsonPath(&quot;$.stock&quot;).value(7));</code> | 断言请求响应满足 `status().isOk()).andExpect(jsonPath("$.stock").value(7));；status 检查状态码，jsonPath 检查 JSON 字段。 |
| 169 | <code>        assertThat(stock(productId)).isEqualTo(7);</code> | 对实际结果执行 AssertJ 断言：`assertThat(stock(productId)).isEqualTo(7);`；不符合期望时该测试失败。 |
| 170 | <code>        jdbc.update(&quot;UPDATE product SET name = &#x27;改名商品&#x27;, price = 99.00 WHERE id = ?&quot;, productId);</code> | 执行修改 SQL，返回影响行数：`jdbc.update("UPDATE product SET name = '改名商品', price = 99.00 WHERE id = ?", productId);`。? 占位符对应后续传入参数。 |
| 171 | <code>        mvc.perform(get(&quot;/api/orders/&quot; + orderId).session(session)).andExpect(status().isOk())</code> | 通过 MockMvc 模拟 HTTP 请求 `mvc.perform(get("/api/orders/" + orderId).session(session)).andExpect(status().isOk())`，经过真实 Spring MVC/安全过滤链，后续断言检查响应。 |
| 172 | <code>                .andExpect(jsonPath(&quot;$.items[0].productName&quot;).value(&quot;快照商品&quot;))</code> | 断言请求响应满足 `jsonPath("$.items[0].productName").value("快照商品"))；status 检查状态码，jsonPath 检查 JSON 字段。 |
| 173 | <code>                .andExpect(jsonPath(&quot;$.items[0].unitPrice&quot;).value(0.1));</code> | 断言请求响应满足 `jsonPath("$.items[0].unitPrice").value(0.1));；status 检查状态码，jsonPath 检查 JSON 字段。 |
| 174 | <code>        mvc.perform(delete(&quot;/api/products/&quot; + productId).session(session).header(&quot;X-CSRF-TOKEN&quot;, csrf(session)))</code> | 通过 MockMvc 模拟 HTTP 请求 `mvc.perform(delete("/api/products/" + productId).session(session).header("X-CSRF-TOKEN", csrf(session)))`，经过真实 Spring MVC/安全过滤链，后续断言检查响应。 |
| 175 | <code>                .andExpect(status().isConflict());</code> | 断言请求响应满足 `status().isConflict());；status 检查状态码，jsonPath 检查 JSON 字段。 |
| 176 | <code>        changeStatus(session, orderId, &quot;PAID&quot;, 200);</code> | 模拟订单状态更新并核对期望 HTTP 状态：`changeStatus(session, orderId, "PAID", 200);`；200 为允许，409 为业务拒绝。 |
| 177 | <code>        changeStatus(session, orderId, &quot;CANCELLED&quot;, 200);</code> | 模拟订单状态更新并核对期望 HTTP 状态：`changeStatus(session, orderId, "CANCELLED", 200);`；200 为允许，409 为业务拒绝。 |
| 178 | <code>        assertThat(stock(productId)).isEqualTo(10);</code> | 对实际结果执行 AssertJ 断言：`assertThat(stock(productId)).isEqualTo(10);`；不符合期望时该测试失败。 |
| 179 | <code>        changeStatus(session, orderId, &quot;CANCELLED&quot;, 409);</code> | 模拟订单状态更新并核对期望 HTTP 状态：`changeStatus(session, orderId, "CANCELLED", 409);`；200 为允许，409 为业务拒绝。 |
| 180 | <code>        assertThat(stock(productId)).isEqualTo(10);</code> | 对实际结果执行 AssertJ 断言：`assertThat(stock(productId)).isEqualTo(10);`；不符合期望时该测试失败。 |
| 181 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 182 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 183 | <code>    @Test</code> | 应用 `Test` 注解为下方声明附加框架规则。 |
| 184 | <code>    @Transactional</code> | 为下方业务操作开启事务，运行时异常时回滚数据库修改。 |
| 185 | <code>    void orderWorkflowAndSalesExcludeUnpaidAndCancelledOrders() throws Exception {</code> | 声明一个自动化测试用例 `orderWorkflowAndSalesExcludeUnpaidAndCancelledOrders`；JUnit 在测试阶段调用它。 |
| 186 | <code>        var session = login(&quot;admin&quot;, &quot;Admin@123456&quot;);</code> | 让 Java 编译器根据右侧推断局部变量 `session` 的类型，值来自 `login("admin", "Admin@123456")`。 |
| 187 | <code>        long productId = create(session, &quot;/api/products&quot;, productInput(&quot;销售商品&quot;, &quot;SALES&quot;, firstCategory(), &quot;12.30&quot;, 10),</code> | 声明 `productId`，类型 `long`，保存 `create(session, "/api/products", productInput("销售商品", "SALES", firstCategory(), "12.30", 10),` 的结果。`productId`：关联商品主键 |
| 188 | <code>                201).get(&quot;id&quot;).asLong();</code> | 结束测试辅助调用并指定预期 HTTP 状态码；201 表示创建成功，其返回 JSON 的 id 用作后续测试主键。 |
| 189 | <code>        long id = create(session, &quot;/api/orders&quot;, orderInput(List.of(Map.of(&quot;productId&quot;, productId, &quot;quantity&quot;, 2))),</code> | 声明 `id`，类型 `long`，保存 `create(session, "/api/orders", orderInput(List.of(Map.of("productId", productId, "quantity", 2))),` 的结果。`id`：稳定记录主键 |
| 190 | <code>                201).get(&quot;id&quot;).asLong();</code> | 结束测试辅助调用并指定预期 HTTP 状态码；201 表示创建成功，其返回 JSON 的 id 用作后续测试主键。 |
| 191 | <code>        changeStatus(session, id, &quot;SHIPPED&quot;, 409);</code> | 模拟订单状态更新并核对期望 HTTP 状态：`changeStatus(session, id, "SHIPPED", 409);`；200 为允许，409 为业务拒绝。 |
| 192 | <code>        assertThat(dashboardSales(session)).isEqualByComparingTo(&quot;0.00&quot;);</code> | 对实际结果执行 AssertJ 断言：`assertThat(dashboardSales(session)).isEqualByComparingTo("0.00");`；不符合期望时该测试失败。 |
| 193 | <code>        changeStatus(session, id, &quot;PAID&quot;, 200);</code> | 模拟订单状态更新并核对期望 HTTP 状态：`changeStatus(session, id, "PAID", 200);`；200 为允许，409 为业务拒绝。 |
| 194 | <code>        assertThat(dashboardSales(session)).isEqualByComparingTo(&quot;24.60&quot;);</code> | 对实际结果执行 AssertJ 断言：`assertThat(dashboardSales(session)).isEqualByComparingTo("24.60");`；不符合期望时该测试失败。 |
| 195 | <code>        changeStatus(session, id, &quot;SHIPPED&quot;, 200);</code> | 模拟订单状态更新并核对期望 HTTP 状态：`changeStatus(session, id, "SHIPPED", 200);`；200 为允许，409 为业务拒绝。 |
| 196 | <code>        changeStatus(session, id, &quot;CANCELLED&quot;, 409);</code> | 模拟订单状态更新并核对期望 HTTP 状态：`changeStatus(session, id, "CANCELLED", 409);`；200 为允许，409 为业务拒绝。 |
| 197 | <code>        changeStatus(session, id, &quot;COMPLETED&quot;, 200);</code> | 模拟订单状态更新并核对期望 HTTP 状态：`changeStatus(session, id, "COMPLETED", 200);`；200 为允许，409 为业务拒绝。 |
| 198 | <code>        changeStatus(session, id, &quot;PAID&quot;, 409);</code> | 模拟订单状态更新并核对期望 HTTP 状态：`changeStatus(session, id, "PAID", 409);`；200 为允许，409 为业务拒绝。 |
| 199 | <code>        mvc.perform(get(&quot;/api/orders&quot;).session(session).param(&quot;status&quot;, &quot;COMPLETED&quot;).param(&quot;keyword&quot;, &quot;测试客户&quot;))</code> | 通过 MockMvc 模拟 HTTP 请求 `mvc.perform(get("/api/orders").session(session).param("status", "COMPLETED").param("keyword", "测试客户"))`，经过真实 Spring MVC/安全过滤链，后续断言检查响应。 |
| 200 | <code>                .andExpect(status().isOk()).andExpect(jsonPath(&quot;$.total&quot;).value(1));</code> | 断言请求响应满足 `status().isOk()).andExpect(jsonPath("$.total").value(1));；status 检查状态码，jsonPath 检查 JSON 字段。 |
| 201 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 202 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 203 | <code>    @Test</code> | 应用 `Test` 注解为下方声明附加框架规则。 |
| 204 | <code>    @Transactional</code> | 为下方业务操作开启事务，运行时异常时回滚数据库修改。 |
| 205 | <code>    void disabledAccountsAndChangedPasswordsInvalidateExistingSessions() throws Exception {</code> | 声明一个自动化测试用例 `disabledAccountsAndChangedPasswordsInvalidateExistingSessions`；JUnit 在测试阶段调用它。 |
| 206 | <code>        var admin = login(&quot;admin&quot;, &quot;Admin@123456&quot;);</code> | 让 Java 编译器根据右侧推断局部变量 `admin` 的类型，值来自 `login("admin", "Admin@123456")`。 |
| 207 | <code>        var created = create(admin, &quot;/api/users&quot;, Map.of(&quot;username&quot;, &quot;test_staff&quot;, &quot;displayName&quot;, &quot;测试员工&quot;, &quot;password&quot;,</code> | 让 Java 编译器根据右侧推断局部变量 `created` 的类型，值来自 `create(admin, "/api/users", Map.of("username", "test_staff", "displayName", "测试员工", "password",`。 |
| 208 | <code>                &quot;Original123!&quot;, &quot;role&quot;, &quot;STAFF&quot;, &quot;enabled&quot;, true), 201);</code> | 继续构造本次测试的键值请求数据：`"Original123!", "role", "STAFF", "enabled", true), 201);`，供模拟接口调用使用。 |
| 209 | <code>        long id = created.get(&quot;id&quot;).asLong();</code> | 声明 `id`，类型 `long`，保存 `created.get("id").asLong()` 的结果。`id`：稳定记录主键 |
| 210 | <code>        var staff = login(&quot;test_staff&quot;, &quot;Original123!&quot;);</code> | 让 Java 编译器根据右侧推断局部变量 `staff` 的类型，值来自 `login("test_staff", "Original123!")`。 |
| 211 | <code>        mvc.perform(put(&quot;/api/users/&quot; + id).session(admin).header(&quot;X-CSRF-TOKEN&quot;, csrf(admin))</code> | 通过 MockMvc 模拟 HTTP 请求 `mvc.perform(put("/api/users/" + id).session(admin).header("X-CSRF-TOKEN", csrf(admin))`，经过真实 Spring MVC/安全过滤链，后续断言检查响应。 |
| 212 | <code>                .contentType(MediaType.APPLICATION_JSON)</code> | 声明测试请求正文为 JSON，并传入待测业务数据；后续断言检查成功/拒绝行为。 |
| 213 | <code>                .content(mapper.writeValueAsString(</code> | 为模拟请求设置 JSON 正文，覆盖指定账号/业务字段，后续验证后端处理结果。 |
| 214 | <code>                        Map.of(&quot;displayName&quot;, &quot;测试员工&quot;, &quot;role&quot;, &quot;STAFF&quot;, &quot;enabled&quot;, true, &quot;password&quot;, &quot;Changed123!&quot;))))</code> | 继续构造本次测试的键值请求数据：`Map.of("displayName", "测试员工", "role", "STAFF", "enabled", true, "password", "Changed123!"))))`，供模拟接口调用使用。 |
| 215 | <code>                .andExpect(status().isOk());</code> | 断言请求响应满足 `status().isOk());；status 检查状态码，jsonPath 检查 JSON 字段。 |
| 216 | <code>        mvc.perform(get(&quot;/api/auth/me&quot;).session(staff)).andExpect(status().isUnauthorized());</code> | 通过 MockMvc 模拟 HTTP 请求 `mvc.perform(get("/api/auth/me").session(staff)).andExpect(status().isUnauthorized());`，经过真实 Spring MVC/安全过滤链，后续断言检查响应。 |
| 217 | <code>        var updatedSession = login(&quot;test_staff&quot;, &quot;Changed123!&quot;);</code> | 让 Java 编译器根据右侧推断局部变量 `updatedSession` 的类型，值来自 `login("test_staff", "Changed123!")`。 |
| 218 | <code>        mvc.perform(delete(&quot;/api/users/&quot; + id).session(admin).header(&quot;X-CSRF-TOKEN&quot;, csrf(admin)))</code> | 通过 MockMvc 模拟 HTTP 请求 `mvc.perform(delete("/api/users/" + id).session(admin).header("X-CSRF-TOKEN", csrf(admin)))`，经过真实 Spring MVC/安全过滤链，后续断言检查响应。 |
| 219 | <code>                .andExpect(status().isNoContent());</code> | 断言请求响应满足 `status().isNoContent());；status 检查状态码，jsonPath 检查 JSON 字段。 |
| 220 | <code>        mvc.perform(get(&quot;/api/auth/me&quot;).session(updatedSession)).andExpect(status().isUnauthorized());</code> | 通过 MockMvc 模拟 HTTP 请求 `mvc.perform(get("/api/auth/me").session(updatedSession)).andExpect(status().isUnauthorized());`，经过真实 Spring MVC/安全过滤链，后续断言检查响应。 |
| 221 | <code>        long adminId = jdbc.queryForObject(&quot;SELECT id FROM mall_user WHERE username = &#x27;admin&#x27;&quot;, Long.class);</code> | 声明 `adminId`，类型 `long`，保存 `jdbc.queryForObject("SELECT id FROM mall_user WHERE username = 'admin'", Long.class)` 的结果。 |
| 222 | <code>        mvc.perform(delete(&quot;/api/users/&quot; + adminId).session(admin).header(&quot;X-CSRF-TOKEN&quot;, csrf(admin)))</code> | 通过 MockMvc 模拟 HTTP 请求 `mvc.perform(delete("/api/users/" + adminId).session(admin).header("X-CSRF-TOKEN", csrf(admin)))`，经过真实 Spring MVC/安全过滤链，后续断言检查响应。 |
| 223 | <code>                .andExpect(status().isConflict());</code> | 断言请求响应满足 `status().isConflict());；status 检查状态码，jsonPath 检查 JSON 字段。 |
| 224 | <code>        mvc.perform(put(&quot;/api/users/&quot; + adminId).session(admin).header(&quot;X-CSRF-TOKEN&quot;, csrf(admin))</code> | 通过 MockMvc 模拟 HTTP 请求 `mvc.perform(put("/api/users/" + adminId).session(admin).header("X-CSRF-TOKEN", csrf(admin))`，经过真实 Spring MVC/安全过滤链，后续断言检查响应。 |
| 225 | <code>                .contentType(MediaType.APPLICATION_JSON)</code> | 声明测试请求正文为 JSON，并传入待测业务数据；后续断言检查成功/拒绝行为。 |
| 226 | <code>                .content(&quot;{\&quot;displayName\&quot;:\&quot;管理员\&quot;,\&quot;role\&quot;:\&quot;STAFF\&quot;,\&quot;enabled\&quot;:true}&quot;))</code> | 为模拟请求设置 JSON 正文，覆盖指定账号/业务字段，后续验证后端处理结果。 |
| 227 | <code>                .andExpect(status().isConflict());</code> | 断言请求响应满足 `status().isConflict());；status 检查状态码，jsonPath 检查 JSON 字段。 |
| 228 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 229 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 230 | <code>    @Test</code> | 应用 `Test` 注解为下方声明附加框架规则。 |
| 231 | <code>    @Transactional</code> | 为下方业务操作开启事务，运行时异常时回滚数据库修改。 |
| 232 | <code>    void rerunningBootstrapDoesNotOverwriteExistingBusinessData() throws Exception {</code> | 声明一个自动化测试用例 `rerunningBootstrapDoesNotOverwriteExistingBusinessData`；JUnit 在测试阶段调用它。 |
| 233 | <code>        jdbc.update(&quot;UPDATE product SET stock = 17, name = &#x27;保留我的修改&#x27; WHERE sku = &#x27;MOUSE-001&#x27;&quot;);</code> | 执行修改 SQL，返回影响行数：`jdbc.update("UPDATE product SET stock = 17, name = '保留我的修改' WHERE sku = 'MOUSE-001'");`。? 占位符对应后续传入参数。 |
| 234 | <code>        jdbc.update(&quot;UPDATE mall_user SET display_name = &#x27;自定义管理员&#x27; WHERE username = &#x27;admin&#x27;&quot;);</code> | 执行修改 SQL，返回影响行数：`jdbc.update("UPDATE mall_user SET display_name = '自定义管理员' WHERE username = 'admin'");`。? 占位符对应后续传入参数。 |
| 235 | <code>        initializer.run(new DefaultApplicationArguments(new String[0]));</code> | 再次执行初始化器，验证已有账号、密码和数据不会因服务重启而被重置。 |
| 236 | <code>        assertThat(jdbc.queryForObject(&quot;SELECT stock FROM product WHERE sku = &#x27;MOUSE-001&#x27;&quot;, Integer.class))</code> | 对实际结果执行 AssertJ 断言：`assertThat(jdbc.queryForObject("SELECT stock FROM product WHERE sku = 'MOUSE-001'", Integer.class))`；不符合期望时该测试失败。 |
| 237 | <code>                .isEqualTo(17);</code> | 继续前一条 AssertJ 断言链：`.isEqualTo(17);`，验证值、集合、异常或字段是否满足预期。 |
| 238 | <code>        assertThat(jdbc.queryForObject(&quot;SELECT display_name FROM mall_user WHERE username = &#x27;admin&#x27;&quot;, String.class))</code> | 对实际结果执行 AssertJ 断言：`assertThat(jdbc.queryForObject("SELECT display_name FROM mall_user WHERE username = 'admin'", String.class))`；不符合期望时该测试失败。 |
| 239 | <code>                .isEqualTo(&quot;自定义管理员&quot;);</code> | 继续前一条 AssertJ 断言链：`.isEqualTo("自定义管理员");`，验证值、集合、异常或字段是否满足预期。 |
| 240 | <code>        assertThat(jdbc.queryForObject(&quot;SELECT COUNT(*) FROM mall_user WHERE username = &#x27;admin&#x27;&quot;, Long.class))</code> | 对实际结果执行 AssertJ 断言：`assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM mall_user WHERE username = 'admin'", Long.class))`；不符合期望时该测试失败。 |
| 241 | <code>                .isEqualTo(1);</code> | 继续前一条 AssertJ 断言链：`.isEqualTo(1);`，验证值、集合、异常或字段是否满足预期。 |
| 242 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 243 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 244 | <code>    @Test</code> | 应用 `Test` 注解为下方声明附加框架规则。 |
| 245 | <code>    void concurrentOrdersCannotOversellTheLastUnit() throws Exception {</code> | 声明一个自动化测试用例 `concurrentOrdersCannotOversellTheLastUnit`；JUnit 在测试阶段调用它。 |
| 246 | <code>        long categoryId = firstCategory();</code> | 声明 `categoryId`，类型 `long`，保存 `firstCategory()` 的结果。`categoryId`：关联分类主键 |
| 247 | <code>        jdbc.update(</code> | 执行修改 SQL，返回影响行数：`jdbc.update(`。? 占位符对应后续传入参数。 |
| 248 | <code>                &quot;INSERT INTO product(name, sku, category_id, price, stock, status, description) VALUES (&#x27;并发商品&#x27;, &#x27;CONCURRENT&#x27;, ?, 5.00, 1, &#x27;ON_SALE&#x27;, &#x27;&#x27;)&quot;,</code> | 继续构造本次测试的键值请求数据：`"INSERT INTO product(name, sku, category_id, price, stock, status, description) VALUES ('并发商品', 'CONCURRENT', ?, 5.00, 1, 'ON_SALE', '')",`，供模拟接口调用使用。 |
| 249 | <code>                categoryId);</code> | 结束前面的测试数据/调用参数，并传入 `categoryId`。 |
| 250 | <code>        long productId = jdbc.queryForObject(&quot;SELECT id FROM product WHERE sku = &#x27;CONCURRENT&#x27;&quot;, Long.class);</code> | 声明 `productId`，类型 `long`，保存 `jdbc.queryForObject("SELECT id FROM product WHERE sku = 'CONCURRENT'", Long.class)` 的结果。`productId`：关联商品主键 |
| 251 | <code>        var executor = Executors.newFixedThreadPool(2);</code> | 让 Java 编译器根据右侧推断局部变量 `executor` 的类型，值来自 `Executors.newFixedThreadPool(2)`。 |
| 252 | <code>        var ready = new CountDownLatch(2);</code> | 让 Java 编译器根据右侧推断局部变量 `ready` 的类型，值来自 `new CountDownLatch(2)`。 |
| 253 | <code>        var start = new CountDownLatch(1);</code> | 让 Java 编译器根据右侧推断局部变量 `start` 的类型，值来自 `new CountDownLatch(1)`。 |
| 254 | <code>        try {</code> | 开始尝试执行可能失败的操作；抛出的异常交给后续 catch 统一处理。 |
| 255 | <code>            java.util.concurrent.Callable&lt;Boolean&gt; purchase = () -&gt; {</code> | 定义 Java Lambda 回调 `java.util.concurrent.Callable<Boolean> purchase = () -> {`；框架或集合处理调用它时，把左侧参数传入右侧操作。 |
| 256 | <code>                ready.countDown();</code> | 释放 CountDownLatch 的一个计数，用于让并发测试线程在可控时间一起开始或通知已准备。 |
| 257 | <code>                if (!start.await(5, TimeUnit.SECONDS))</code> | 判断条件 `!start.await(5, TimeUnit.SECONDS)`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 258 | <code>                    throw new IllegalStateException(&quot;并发测试未同时启动&quot;);</code> | 抛出错误 `new IllegalStateException("并发测试未同时启动")`，终止当前路径，并由异常处理器转换为失败响应或提示。 |
| 259 | <code>                try {</code> | 开始尝试执行可能失败的操作；抛出的异常交给后续 catch 统一处理。 |
| 260 | <code>                    orders.create(</code> | 调用 `create` 完成对应数据操作；本行传入 `orders.create(`。 |
| 261 | <code>                            new OrderInput(&quot;并发客户&quot;, &quot;13800138000&quot;, &quot;测试地址&quot;, List.of(new OrderLineInput(productId, 1))));</code> | 构造并发测试订单：同一个目标商品、数量 1，由订单服务执行真实事务和行锁。 |
| 262 | <code>                    return true;</code> | 把 `true` 返回给调用方，并结束当前函数/方法。 |
| 263 | <code>                } catch (com.example.mall.common.BusinessException expected) {</code> | 捕获前面 try 中抛出的错误 com.example.mall.common.BusinessException expected，进入对应的失败处理。 |
| 264 | <code>                    return false;</code> | 把 `false` 返回给调用方，并结束当前函数/方法。 |
| 265 | <code>                }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 266 | <code>            };</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 267 | <code>            var first = executor.submit(purchase);</code> | 让 Java 编译器根据右侧推断局部变量 `first` 的类型，值来自 `executor.submit(purchase)`。 |
| 268 | <code>            var second = executor.submit(purchase);</code> | 让 Java 编译器根据右侧推断局部变量 `second` 的类型，值来自 `executor.submit(purchase)`。 |
| 269 | <code>            assertThat(ready.await(5, TimeUnit.SECONDS)).isTrue();</code> | 对实际结果执行 AssertJ 断言：`assertThat(ready.await(5, TimeUnit.SECONDS)).isTrue();`；不符合期望时该测试失败。 |
| 270 | <code>            start.countDown();</code> | 释放 CountDownLatch 的一个计数，用于让并发测试线程在可控时间一起开始或通知已准备。 |
| 271 | <code>            int successes = (first.get(15, TimeUnit.SECONDS) ? 1 : 0) + (second.get(15, TimeUnit.SECONDS) ? 1 : 0);</code> | 声明 `successes`，类型 `int`，保存 `(first.get(15, TimeUnit.SECONDS) ? 1 : 0) + (second.get(15, TimeUnit.SECONDS) ? 1 : 0)` 的结果。 |
| 272 | <code>            assertThat(successes).isEqualTo(1);</code> | 对实际结果执行 AssertJ 断言：`assertThat(successes).isEqualTo(1);`；不符合期望时该测试失败。 |
| 273 | <code>            assertThat(stock(productId)).isZero();</code> | 对实际结果执行 AssertJ 断言：`assertThat(stock(productId)).isZero();`；不符合期望时该测试失败。 |
| 274 | <code>            assertThat(</code> | 对实际结果执行 AssertJ 断言：`assertThat(`；不符合期望时该测试失败。 |
| 275 | <code>                    jdbc.queryForObject(&quot;SELECT COUNT(*) FROM order_item WHERE product_id = ?&quot;, Long.class, productId))</code> | 执行参数化查询并映射结果：`jdbc.queryForObject("SELECT COUNT(*) FROM order_item WHERE product_id = ?", Long.class, productId))`。? 占位符对应后续传入参数。 |
| 276 | <code>                    .isEqualTo(1);</code> | 继续前一条 AssertJ 断言链：`.isEqualTo(1);`，验证值、集合、异常或字段是否满足预期。 |
| 277 | <code>        } finally {</code> | 无论前面的操作成功还是失败，都执行此处的清理/状态恢复代码。 |
| 278 | <code>            executor.shutdownNow();</code> | 在测试清理阶段关闭线程池，防止工作线程泄漏到其他测试。 |
| 279 | <code>            var ids = jdbc.queryForList(&quot;SELECT order_id FROM order_item WHERE product_id = ?&quot;, Long.class, productId);</code> | 让 Java 编译器根据右侧推断局部变量 `ids` 的类型，值来自 `jdbc.queryForList("SELECT order_id FROM order_item WHERE product_id = ?", Long.class, productId)`。 |
| 280 | <code>            jdbc.update(&quot;DELETE FROM order_item WHERE product_id = ?&quot;, productId);</code> | 执行修改 SQL，返回影响行数：`jdbc.update("DELETE FROM order_item WHERE product_id = ?", productId);`。? 占位符对应后续传入参数。 |
| 281 | <code>            for (long id : ids)</code> | 按 `long id : ids` 逐项/逐次循环，执行花括号中的处理。 |
| 282 | <code>                jdbc.update(&quot;DELETE FROM mall_order WHERE id = ?&quot;, id);</code> | 执行修改 SQL，返回影响行数：`jdbc.update("DELETE FROM mall_order WHERE id = ?", id);`。? 占位符对应后续传入参数。 |
| 283 | <code>            jdbc.update(&quot;DELETE FROM stock_movement WHERE product_id = ?&quot;, productId);</code> | 执行参数化数据库写入，后面的参数按问号顺序绑定；不把用户输入直接拼成 SQL。 |
| 284 | <code>            jdbc.update(&quot;DELETE FROM product WHERE id = ?&quot;, productId);</code> | 执行修改 SQL，返回影响行数：`jdbc.update("DELETE FROM product WHERE id = ?", productId);`。? 占位符对应后续传入参数。 |
| 285 | <code>        }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 286 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 287 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 288 | <code>    @Test</code> | 应用 `Test` 注解为下方声明附加框架规则。 |
| 289 | <code>    @Transactional</code> | 为下方业务操作开启事务，运行时异常时回滚数据库修改。 |
| 290 | <code>    void stockCanOnlyChangeThroughInventoryAndRetryDoesNotDuplicateMovement() throws Exception {</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 291 | <code>        var session = login(&quot;staff&quot;, &quot;Staff@123456&quot;);</code> | 让 Java 编译器根据右侧推断局部变量 `session` 的类型，值来自 `login("staff", "Staff@123456")`。 |
| 292 | <code>        var product = create(session, &quot;/api/products&quot;, productInput(&quot;库存业务商品&quot;, &quot;INVENTORY-ONLY&quot;, firstCategory(), &quot;9.90&quot;, 0), 201);</code> | 声明本地变量或状态引用，保存后续代码要使用的值。 |
| 293 | <code>        long id = product.get(&quot;id&quot;).asLong();</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 294 | <code>        assertThat(product.get(&quot;stock&quot;).asInt()).isZero();</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 295 | <code>        var inbound = Map.of(&quot;productId&quot;, id, &quot;type&quot;, &quot;INBOUND&quot;, &quot;quantity&quot;, 20, &quot;reason&quot;, &quot;采购补货&quot;,</code> | 声明本地变量或状态引用，保存后续代码要使用的值。 |
| 296 | <code>                &quot;requestId&quot;, java.util.UUID.randomUUID().toString());</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 297 | <code>        create(session, &quot;/api/inventory/movements&quot;, inbound, 201);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 298 | <code>        create(session, &quot;/api/inventory/movements&quot;, inbound, 201);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 299 | <code>        assertThat(stock(id)).isEqualTo(20);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 300 | <code>        assertThat(jdbc.queryForObject(&quot;SELECT COUNT(*) FROM stock_movement WHERE product_id = ?&quot;, Long.class, id)).isEqualTo(1);</code> | 查询数据库并映射为返回对象；查询参数与 SQL 结构分开传递。 |
| 301 | <code>        var changedRetry = new java.util.HashMap&lt;&gt;(inbound); changedRetry.put(&quot;quantity&quot;, 21);</code> | 声明本地变量或状态引用，保存后续代码要使用的值。 |
| 302 | <code>        create(session, &quot;/api/inventory/movements&quot;, changedRetry, 409);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 303 | <code>        create(session, &quot;/api/inventory/movements&quot;, Map.of(&quot;productId&quot;, id, &quot;type&quot;, &quot;OUTBOUND&quot;, &quot;quantity&quot;, 3,</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 304 | <code>                &quot;reason&quot;, &quot;商品报损&quot;, &quot;requestId&quot;, java.util.UUID.randomUUID().toString()), 201);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 305 | <code>        create(session, &quot;/api/inventory/movements&quot;, Map.of(&quot;productId&quot;, id, &quot;type&quot;, &quot;OUTBOUND&quot;, &quot;quantity&quot;, 18,</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 306 | <code>                &quot;reason&quot;, &quot;商品报损&quot;, &quot;requestId&quot;, java.util.UUID.randomUUID().toString()), 409);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 307 | <code>        assertThat(stock(id)).isEqualTo(17);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 308 | <code>        var update = new java.util.HashMap&lt;String, Object&gt;(Map.of(&quot;name&quot;, &quot;改名不改库存&quot;, &quot;sku&quot;, &quot;INVENTORY-ONLY&quot;,</code> | 声明本地变量或状态引用，保存后续代码要使用的值。 |
| 309 | <code>                &quot;categoryId&quot;, firstCategory(), &quot;price&quot;, 8, &quot;status&quot;, &quot;ON_SALE&quot;));</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 310 | <code>        mvc.perform(put(&quot;/api/products/&quot; + id).session(session).header(&quot;X-CSRF-TOKEN&quot;, csrf(session))</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 311 | <code>                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(update)))</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 312 | <code>                .andExpect(status().isOk()).andExpect(jsonPath(&quot;$.stock&quot;).value(17));</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 313 | <code>        update.put(&quot;stock&quot;, 999);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 314 | <code>        mvc.perform(put(&quot;/api/products/&quot; + id).session(session).header(&quot;X-CSRF-TOKEN&quot;, csrf(session))</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 315 | <code>                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(update))).andExpect(status().isBadRequest());</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 316 | <code>        assertThat(stock(id)).isEqualTo(17);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 317 | <code>        mvc.perform(get(&quot;/api/inventory/movements&quot;).session(session).param(&quot;keyword&quot;, &quot;INVENTORY-ONLY&quot;).param(&quot;type&quot;, &quot;OUTBOUND&quot;))</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 318 | <code>                .andExpect(status().isOk()).andExpect(jsonPath(&quot;$.total&quot;).value(1))</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 319 | <code>                .andExpect(jsonPath(&quot;$.items[0].beforeStock&quot;).value(20)).andExpect(jsonPath(&quot;$.items[0].afterStock&quot;).value(17))</code> | 计算或展示表单预估的变更后库存；后端仍会重新读取真实数量，不能只信页面预估。 |
| 320 | <code>                .andExpect(jsonPath(&quot;$.items[0].operator&quot;).value(&quot;staff&quot;));</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 321 | <code>        mvc.perform(get(&quot;/api/inventory/movements&quot;)).andExpect(status().isUnauthorized());</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 322 | <code>        mvc.perform(post(&quot;/api/inventory/movements&quot;).session(session).contentType(MediaType.APPLICATION_JSON)</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 323 | <code>                .content(mapper.writeValueAsString(inbound))).andExpect(status().isForbidden());</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 324 | <code>        mvc.perform(get(&quot;/api/inventory/movements&quot;).session(session).param(&quot;start&quot;, &quot;2026-10-30&quot;).param(&quot;end&quot;, &quot;2026-10-01&quot;))</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 325 | <code>                .andExpect(status().isBadRequest());</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 326 | <code>        mvc.perform(get(&quot;/api/inventory/movements&quot;).session(session).param(&quot;type&quot;, &quot;INVALID&quot;))</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 327 | <code>                .andExpect(status().isBadRequest());</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 328 | <code>        create(session, &quot;/api/inventory/movements&quot;, Map.of(&quot;productId&quot;, id, &quot;type&quot;, &quot;INBOUND&quot;, &quot;quantity&quot;, 0,</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 329 | <code>                &quot;reason&quot;, &quot;采购补货&quot;, &quot;requestId&quot;, java.util.UUID.randomUUID().toString()), 400);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 330 | <code>        create(session, &quot;/api/inventory/movements&quot;, Map.of(&quot;productId&quot;, id, &quot;type&quot;, &quot;INBOUND&quot;, &quot;quantity&quot;, 1,</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 331 | <code>                &quot;reason&quot;, &quot;订单扣减&quot;, &quot;requestId&quot;, java.util.UUID.randomUUID().toString()), 400);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 332 | <code>        mvc.perform(delete(&quot;/api/products/&quot; + id).session(session).header(&quot;X-CSRF-TOKEN&quot;, csrf(session))).andExpect(status().isConflict());</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 333 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 334 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 335 | <code>    @Test</code> | 应用 `Test` 注解为下方声明附加框架规则。 |
| 336 | <code>    @Transactional</code> | 为下方业务操作开启事务，运行时异常时回滚数据库修改。 |
| 337 | <code>    void ordersRecordBothDeductionAndReturnAndNeverDeductOnShipping() throws Exception {</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 338 | <code>        var session = login(&quot;admin&quot;, &quot;Admin@123456&quot;);</code> | 让 Java 编译器根据右侧推断局部变量 `session` 的类型，值来自 `login("admin", "Admin@123456")`。 |
| 339 | <code>        long id = create(session, &quot;/api/products&quot;, productInput(&quot;订单库存流水&quot;, &quot;ORDER-LEDGER&quot;, firstCategory(), &quot;5&quot;, 10), 201).get(&quot;id&quot;).asLong();</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 340 | <code>        var first = create(session, &quot;/api/orders&quot;, orderInput(List.of(Map.of(&quot;productId&quot;, id, &quot;quantity&quot;, 3))), 201);</code> | 声明本地变量或状态引用，保存后续代码要使用的值。 |
| 341 | <code>        var second = create(session, &quot;/api/orders&quot;, orderInput(List.of(Map.of(&quot;productId&quot;, id, &quot;quantity&quot;, 2))), 201);</code> | 声明本地变量或状态引用，保存后续代码要使用的值。 |
| 342 | <code>        changeStatus(session, first.get(&quot;id&quot;).asLong(), &quot;PAID&quot;, 200);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 343 | <code>        changeStatus(session, first.get(&quot;id&quot;).asLong(), &quot;SHIPPED&quot;, 200);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 344 | <code>        assertThat(stock(id)).isEqualTo(5);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 345 | <code>        changeStatus(session, second.get(&quot;id&quot;).asLong(), &quot;CANCELLED&quot;, 200);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 346 | <code>        changeStatus(session, second.get(&quot;id&quot;).asLong(), &quot;CANCELLED&quot;, 409);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 347 | <code>        assertThat(stock(id)).isEqualTo(7);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 348 | <code>        assertThat(jdbc.queryForObject(&quot;SELECT COUNT(*) FROM stock_movement WHERE product_id = ? AND type = &#x27;ORDER_DEDUCT&#x27;&quot;, Long.class, id)).isEqualTo(2);</code> | 查询数据库并映射为返回对象；查询参数与 SQL 结构分开传递。 |
| 349 | <code>        assertThat(jdbc.queryForObject(&quot;SELECT COUNT(*) FROM stock_movement WHERE product_id = ? AND type = &#x27;ORDER_RETURN&#x27;&quot;, Long.class, id)).isEqualTo(1);</code> | 查询数据库并映射为返回对象；查询参数与 SQL 结构分开传递。 |
| 350 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 351 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 352 | <code>    @Test</code> | 应用 `Test` 注解为下方声明附加框架规则。 |
| 353 | <code>    @Transactional</code> | 为下方业务操作开启事务，运行时异常时回滚数据库修改。 |
| 354 | <code>    void imageUploadValidatesContentAndPersistsReferenceWithoutChangingStock() throws Exception {</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 355 | <code>        var session = login(&quot;admin&quot;, &quot;Admin@123456&quot;);</code> | 让 Java 编译器根据右侧推断局部变量 `session` 的类型，值来自 `login("admin", "Admin@123456")`。 |
| 356 | <code>        var image = new java.awt.image.BufferedImage(8, 8, java.awt.image.BufferedImage.TYPE_INT_RGB);</code> | 声明本地变量或状态引用，保存后续代码要使用的值。 |
| 357 | <code>        var buffer = new java.io.ByteArrayOutputStream();</code> | 声明本地变量或状态引用，保存后续代码要使用的值。 |
| 358 | <code>        javax.imageio.ImageIO.write(image, &quot;png&quot;, buffer);</code> | 把解码得到的像素重新编码成 PNG，统一输出格式，不直接保存不可信原文件。 |
| 359 | <code>        var file = new org.springframework.mock.web.MockMultipartFile(&quot;file&quot;, &quot;photo.png&quot;, &quot;image/png&quot;, buffer.toByteArray());</code> | 声明本地变量或状态引用，保存后续代码要使用的值。 |
| 360 | <code>        mvc.perform(multipart(&quot;/api/product-images&quot;).file(file).session(session)).andExpect(status().isForbidden());</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 361 | <code>        var uploaded = json(mvc.perform(multipart(&quot;/api/product-images&quot;).file(file).session(session)</code> | 声明本地变量或状态引用，保存后续代码要使用的值。 |
| 362 | <code>                .header(&quot;X-CSRF-TOKEN&quot;, csrf(session))).andExpect(status().isCreated()).andReturn());</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 363 | <code>        String url = uploaded.get(&quot;imageUrl&quot;).asText();</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 364 | <code>        mvc.perform(get(url)).andExpect(status().isUnauthorized());</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 365 | <code>        mvc.perform(get(url).session(session)).andExpect(status().isOk()).andExpect(content().contentType(&quot;image/png&quot;));</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 366 | <code>        var input = new java.util.HashMap&lt;String, Object&gt;(Map.of(&quot;name&quot;, &quot;带图片商品&quot;, &quot;sku&quot;, &quot;IMAGE-TEST&quot;,</code> | 声明本地变量或状态引用，保存后续代码要使用的值。 |
| 367 | <code>                &quot;categoryId&quot;, firstCategory(), &quot;price&quot;, 9, &quot;status&quot;, &quot;OFF_SALE&quot;, &quot;imageUrl&quot;, url));</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 368 | <code>        var product = create(session, &quot;/api/products&quot;, input, 201);</code> | 声明本地变量或状态引用，保存后续代码要使用的值。 |
| 369 | <code>        assertThat(product.get(&quot;stock&quot;).asInt()).isZero();</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 370 | <code>        assertThat(product.get(&quot;imageUrl&quot;).asText()).isEqualTo(url);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 371 | <code>        mvc.perform(get(&quot;/api/products&quot;).session(session).param(&quot;keyword&quot;, &quot;IMAGE-TEST&quot;))</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 372 | <code>                .andExpect(jsonPath(&quot;$.items[0].imageUrl&quot;).value(url));</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 373 | <code>        input.put(&quot;imageUrl&quot;, &quot;&quot;);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 374 | <code>        mvc.perform(put(&quot;/api/products/&quot; + product.get(&quot;id&quot;).asLong()).session(session).header(&quot;X-CSRF-TOKEN&quot;, csrf(session))</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 375 | <code>                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(input)))</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 376 | <code>                .andExpect(status().isOk()).andExpect(jsonPath(&quot;$.imageUrl&quot;).value(&quot;&quot;));</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 377 | <code>        var fake = new org.springframework.mock.web.MockMultipartFile(&quot;file&quot;, &quot;fake.png&quot;, &quot;image/png&quot;, &quot;&lt;script&gt;bad&lt;/script&gt;&quot;.getBytes());</code> | 声明本地变量或状态引用，保存后续代码要使用的值。 |
| 378 | <code>        mvc.perform(multipart(&quot;/api/product-images&quot;).file(fake).session(session).header(&quot;X-CSRF-TOKEN&quot;, csrf(session)))</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 379 | <code>                .andExpect(status().isBadRequest());</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 380 | <code>        for (String format : List.of(&quot;jpg&quot;, &quot;webp&quot;)) {</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 381 | <code>            byte[] bytes;</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 382 | <code>            try (var resource = getClass().getResourceAsStream(&quot;/images/sample.&quot; + format)) { bytes = resource.readAllBytes(); }</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 383 | <code>            var sample = new org.springframework.mock.web.MockMultipartFile(&quot;file&quot;, &quot;sample.&quot; + format, &quot;application/octet-stream&quot;, bytes);</code> | 声明本地变量或状态引用，保存后续代码要使用的值。 |
| 384 | <code>            var normalized = json(mvc.perform(multipart(&quot;/api/product-images&quot;).file(sample).session(session)</code> | 声明本地变量或状态引用，保存后续代码要使用的值。 |
| 385 | <code>                    .header(&quot;X-CSRF-TOKEN&quot;, csrf(session))).andExpect(status().isCreated()).andReturn());</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 386 | <code>            mvc.perform(get(normalized.get(&quot;imageUrl&quot;).asText()).session(session)).andExpect(status().isOk())</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 387 | <code>                    .andExpect(content().contentType(&quot;image/png&quot;));</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 388 | <code>        }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 389 | <code>        mvc.perform(multipart(&quot;/api/product-images&quot;).session(session).header(&quot;X-CSRF-TOKEN&quot;, csrf(session)))</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 390 | <code>                .andExpect(status().isBadRequest());</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 391 | <code>        var large = new org.springframework.mock.web.MockMultipartFile(&quot;file&quot;, &quot;large.png&quot;, &quot;image/png&quot;, new byte[5 * 1024 * 1024 + 1]);</code> | 声明本地变量或状态引用，保存后续代码要使用的值。 |
| 392 | <code>        mvc.perform(multipart(&quot;/api/product-images&quot;).file(large).session(session).header(&quot;X-CSRF-TOKEN&quot;, csrf(session)))</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 393 | <code>                .andExpect(status().isBadRequest());</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 394 | <code>        input.put(&quot;sku&quot;, &quot;IMAGE-BAD&quot;); input.put(&quot;imageUrl&quot;, &quot;https://example.invalid/image.png&quot;);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 395 | <code>        create(session, &quot;/api/products&quot;, input, 400);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 396 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 397 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 398 | <code>    @Test</code> | 应用 `Test` 注解为下方声明附加框架规则。 |
| 399 | <code>    void imageColumnMigrationIsRepeatableAndPreservesOldStock() {</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 400 | <code>        var source = new org.springframework.jdbc.datasource.DriverManagerDataSource(&quot;jdbc:h2:mem:oldstock;MODE=MySQL;DATABASE_TO_LOWER=TRUE;DB_CLOSE_DELAY=-1&quot;, &quot;sa&quot;, &quot;&quot;);</code> | 声明本地变量或状态引用，保存后续代码要使用的值。 |
| 401 | <code>        var old = new JdbcTemplate(source);</code> | 声明本地变量或状态引用，保存后续代码要使用的值。 |
| 402 | <code>        old.execute(&quot;CREATE TABLE product(id BIGINT PRIMARY KEY, stock INT)&quot;);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 403 | <code>        old.update(&quot;INSERT INTO product(id, stock) VALUES (1, 37)&quot;);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 404 | <code>        var migration = new com.example.mall.config.InventoryMigration(old);</code> | 声明本地变量或状态引用，保存后续代码要使用的值。 |
| 405 | <code>        migration.run(new DefaultApplicationArguments(new String[0]));</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 406 | <code>        migration.run(new DefaultApplicationArguments(new String[0]));</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 407 | <code>        assertThat(old.queryForObject(&quot;SELECT stock FROM product WHERE id = 1&quot;, Integer.class)).isEqualTo(37);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 408 | <code>        assertThat(old.queryForObject(&quot;SELECT image_url FROM product WHERE id = 1&quot;, String.class)).isEmpty();</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 409 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 410 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 411 | <code>    @Test</code> | 应用 `Test` 注解为下方声明附加框架规则。 |
| 412 | <code>    @Transactional</code> | 为下方业务操作开启事务，运行时异常时回滚数据库修改。 |
| 413 | <code>    void openingInventoryRecordsOldBalanceOnceWithoutChangingIt() {</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 414 | <code>        jdbc.update(&quot;INSERT INTO product(name, sku, category_id, price, stock, status, description) VALUES (&#x27;升级旧商品&#x27;, &#x27;OLD-BALANCE&#x27;, ?, 1, 37, &#x27;ON_SALE&#x27;, &#x27;&#x27;)&quot;, firstCategory());</code> | 执行参数化数据库写入，后面的参数按问号顺序绑定；不把用户输入直接拼成 SQL。 |
| 415 | <code>        long id = jdbc.queryForObject(&quot;SELECT id FROM product WHERE sku = &#x27;OLD-BALANCE&#x27;&quot;, Long.class);</code> | 查询数据库并映射为返回对象；查询参数与 SQL 结构分开传递。 |
| 416 | <code>        jdbc.update(&quot;DELETE FROM app_setting WHERE setting_key = &#x27;stock_ledger_v1&#x27;&quot;);</code> | 使用数据库版本标记控制期初流水只登记一次，重启不重复执行。 |
| 417 | <code>        openingInventory.run(new DefaultApplicationArguments(new String[0]));</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 418 | <code>        openingInventory.run(new DefaultApplicationArguments(new String[0]));</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 419 | <code>        assertThat(stock(id)).isEqualTo(37);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 420 | <code>        assertThat(jdbc.queryForObject(&quot;SELECT COUNT(*) FROM stock_movement WHERE product_id = ? AND type = &#x27;OPENING&#x27;&quot;, Long.class, id)).isEqualTo(1);</code> | 查询数据库并映射为返回对象；查询参数与 SQL 结构分开传递。 |
| 421 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 422 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 423 | <code>    @Test</code> | 应用 `Test` 注解为下方声明附加框架规则。 |
| 424 | <code>    void concurrentManualOutboundCannotDeductTheSameLastUnitTwice() throws Exception {</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 425 | <code>        var session = login(&quot;admin&quot;, &quot;Admin@123456&quot;);</code> | 让 Java 编译器根据右侧推断局部变量 `session` 的类型，值来自 `login("admin", "Admin@123456")`。 |
| 426 | <code>        long id = create(session, &quot;/api/products&quot;, productInput(&quot;并发出库&quot;, &quot;CONCURRENT-OUT&quot;, firstCategory(), &quot;1&quot;, 1), 201).get(&quot;id&quot;).asLong();</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 427 | <code>        var executor = Executors.newFixedThreadPool(2);</code> | 让 Java 编译器根据右侧推断局部变量 `executor` 的类型，值来自 `Executors.newFixedThreadPool(2)`。 |
| 428 | <code>        var ready = new CountDownLatch(2);</code> | 让 Java 编译器根据右侧推断局部变量 `ready` 的类型，值来自 `new CountDownLatch(2)`。 |
| 429 | <code>        var start = new CountDownLatch(1);</code> | 让 Java 编译器根据右侧推断局部变量 `start` 的类型，值来自 `new CountDownLatch(1)`。 |
| 430 | <code>        try {</code> | 开始尝试执行可能失败的操作；抛出的异常交给后续 catch 统一处理。 |
| 431 | <code>            java.util.concurrent.Callable&lt;Boolean&gt; outbound = () -&gt; {</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 432 | <code>                ready.countDown();</code> | 释放 CountDownLatch 的一个计数，用于让并发测试线程在可控时间一起开始或通知已准备。 |
| 433 | <code>                if (!start.await(5, TimeUnit.SECONDS)) throw new IllegalStateException(&quot;并发出库未同时启动&quot;);</code> | 判断本行条件，只有成立时才进入对应分支；结合下一行看成功、拒绝或提前返回的处理。 |
| 434 | <code>                try {</code> | 开始尝试执行可能失败的操作；抛出的异常交给后续 catch 统一处理。 |
| 435 | <code>                    inventory.manual(new com.example.mall.model.Requests.StockInput(id, &quot;OUTBOUND&quot;, 1, &quot;商品报损&quot;, &quot;&quot;, java.util.UUID.randomUUID().toString()));</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 436 | <code>                    return true;</code> | 把 `true` 返回给调用方，并结束当前函数/方法。 |
| 437 | <code>                } catch (com.example.mall.common.BusinessException expected) { return false; }</code> | 捕获对应异常，把失败消息显示给用户或转换成接口错误。 |
| 438 | <code>            };</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 439 | <code>            var a = executor.submit(outbound); var b = executor.submit(outbound);</code> | 声明本地变量或状态引用，保存后续代码要使用的值。 |
| 440 | <code>            assertThat(ready.await(5, TimeUnit.SECONDS)).isTrue(); start.countDown();</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 441 | <code>            assertThat((a.get(15, TimeUnit.SECONDS) ? 1 : 0) + (b.get(15, TimeUnit.SECONDS) ? 1 : 0)).isEqualTo(1);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 442 | <code>            assertThat(stock(id)).isZero();</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 443 | <code>            assertThat(jdbc.queryForObject(&quot;SELECT COUNT(*) FROM stock_movement WHERE product_id = ? AND type = &#x27;OUTBOUND&#x27;&quot;, Long.class, id)).isEqualTo(1);</code> | 查询数据库并映射为返回对象；查询参数与 SQL 结构分开传递。 |
| 444 | <code>        } finally {</code> | 无论前面的操作成功还是失败，都执行此处的清理/状态恢复代码。 |
| 445 | <code>            executor.shutdownNow();</code> | 在测试清理阶段关闭线程池，防止工作线程泄漏到其他测试。 |
| 446 | <code>            jdbc.update(&quot;DELETE FROM stock_movement WHERE product_id = ?&quot;, id);</code> | 执行参数化数据库写入，后面的参数按问号顺序绑定；不把用户输入直接拼成 SQL。 |
| 447 | <code>            jdbc.update(&quot;DELETE FROM product WHERE id = ?&quot;, id);</code> | 执行参数化数据库写入，后面的参数按问号顺序绑定；不把用户输入直接拼成 SQL。 |
| 448 | <code>        }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 449 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 450 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 451 | <code>    private MockHttpSession login(String username, String password) throws Exception {</code> | 声明 `login` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 452 | <code>        var result = mvc.perform(get(&quot;/api/auth/csrf&quot;)).andReturn();</code> | 执行测试模拟请求并取得响应，供后续读取会话、JSON 或业务 ID 使用。 |
| 453 | <code>        var session = (MockHttpSession) result.getRequest().getSession(false);</code> | 让 Java 编译器根据右侧推断局部变量 `session` 的类型，值来自 `(MockHttpSession) result.getRequest().getSession(false)`。 |
| 454 | <code>        mvc.perform(post(&quot;/api/auth/login&quot;).session(session).header(&quot;X-CSRF-TOKEN&quot;, json(result).get(&quot;token&quot;).asText())</code> | 通过 MockMvc 模拟 HTTP 请求 `mvc.perform(post("/api/auth/login").session(session).header("X-CSRF-TOKEN", json(result).get("token").asText())`，经过真实 Spring MVC/安全过滤链，后续断言检查响应。 |
| 455 | <code>                .param(&quot;username&quot;, username).param(&quot;password&quot;, password)).andExpect(status().isOk());</code> | 为模拟请求添加表单参数；登录使用 username/password，随后检查认证响应。 |
| 456 | <code>        return session;</code> | 把 `session` 返回给调用方，并结束当前函数/方法。 |
| 457 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 458 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 459 | <code>    private String csrf(MockHttpSession session) throws Exception {</code> | 声明 `csrf` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 460 | <code>        return json(mvc.perform(get(&quot;/api/auth/csrf&quot;).session(session)).andExpect(status().isOk()).andReturn())</code> | 把 `json(mvc.perform(get("/api/auth/csrf").session(session)).andExpect(status().isOk()).andReturn())` 返回给调用方，并结束当前函数/方法。 |
| 461 | <code>                .get(&quot;token&quot;).asText();</code> | 从 CSRF 响应 JSON 读取 token 并转成字符串，作为下一次写请求的请求头。 |
| 462 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 463 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 464 | <code>    private JsonNode create(MockHttpSession session, String path, Object body, int expectedStatus) throws Exception {</code> | 声明 `create` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 465 | <code>        int initialStock = 0;</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 466 | <code>        if (&quot;/api/products&quot;.equals(path) &amp;&amp; body instanceof Map&lt;?, ?&gt; map &amp;&amp; map.containsKey(&quot;testInitialStock&quot;)) {</code> | 判断本行条件，只有成立时才进入对应分支；结合下一行看成功、拒绝或提前返回的处理。 |
| 467 | <code>            var input = new java.util.HashMap&lt;&gt;(map);</code> | 声明本地变量或状态引用，保存后续代码要使用的值。 |
| 468 | <code>            initialStock = ((Number) input.remove(&quot;testInitialStock&quot;)).intValue();</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 469 | <code>            body = input;</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 470 | <code>        }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 471 | <code>        var result = mvc</code> | 让 Java 编译器根据右侧推断局部变量 `result` 的类型，值来自 `mvc`。 |
| 472 | <code>                .perform(post(path).session(session).header(&quot;X-CSRF-TOKEN&quot;, csrf(session))</code> | 执行测试模拟请求并取得响应，供后续读取会话、JSON 或业务 ID 使用。 |
| 473 | <code>                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(body)))</code> | 声明测试请求正文为 JSON，并传入待测业务数据；后续断言检查成功/拒绝行为。 |
| 474 | <code>                .andExpect(status().is(expectedStatus)).andReturn();</code> | 断言请求响应满足 `status().is(expectedStatus)).andReturn();；status 检查状态码，jsonPath 检查 JSON 字段。 |
| 475 | <code>        JsonNode created = json(result);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 476 | <code>        if (expectedStatus == 201 &amp;&amp; &quot;/api/products&quot;.equals(path) &amp;&amp; initialStock &gt; 0) {</code> | 判断本行条件，只有成立时才进入对应分支；结合下一行看成功、拒绝或提前返回的处理。 |
| 477 | <code>            create(session, &quot;/api/inventory/movements&quot;, Map.of(&quot;productId&quot;, created.get(&quot;id&quot;).asLong(), &quot;type&quot;, &quot;INBOUND&quot;,</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 478 | <code>                    &quot;quantity&quot;, initialStock, &quot;reason&quot;, &quot;采购补货&quot;, &quot;requestId&quot;, java.util.UUID.randomUUID().toString()), 201);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 479 | <code>        }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 480 | <code>        return created;</code> | 返回本行表达式的结果，交给调用方或作为接口响应。 |
| 481 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 482 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 483 | <code>    private void changeStatus(MockHttpSession session, long id, String status, int expectedStatus) throws Exception {</code> | 声明 `changeStatus` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 484 | <code>        mvc.perform(put(&quot;/api/orders/&quot; + id + &quot;/status&quot;).session(session).header(&quot;X-CSRF-TOKEN&quot;, csrf(session))</code> | 通过 MockMvc 模拟 HTTP 请求 `mvc.perform(put("/api/orders/" + id + "/status").session(session).header("X-CSRF-TOKEN", csrf(session))`，经过真实 Spring MVC/安全过滤链，后续断言检查响应。 |
| 485 | <code>                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(Map.of(&quot;status&quot;, status))))</code> | 声明测试请求正文为 JSON，并传入待测业务数据；后续断言检查成功/拒绝行为。 |
| 486 | <code>                .andExpect(status().is(expectedStatus));</code> | 断言请求响应满足 `status().is(expectedStatus));；status 检查状态码，jsonPath 检查 JSON 字段。 |
| 487 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 488 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 489 | <code>    private JsonNode json(MvcResult result) throws Exception {</code> | 声明 `json` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 490 | <code>        return mapper.readTree(result.getResponse().getContentAsByteArray());</code> | 把 `mapper.readTree(result.getResponse().getContentAsByteArray())` 返回给调用方，并结束当前函数/方法。 |
| 491 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 492 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 493 | <code>    private long firstCategory() {</code> | 声明 `firstCategory` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 494 | <code>        return jdbc.queryForObject(&quot;SELECT MIN(id) FROM category&quot;, Long.class);</code> | 把 `jdbc.queryForObject("SELECT MIN(id) FROM category", Long.class)` 返回给调用方，并结束当前函数/方法。 |
| 495 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 496 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 497 | <code>    private int stock(long id) {</code> | 声明 `stock` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 498 | <code>        return jdbc.queryForObject(&quot;SELECT stock FROM product WHERE id = ?&quot;, Integer.class, id);</code> | 把 `jdbc.queryForObject("SELECT stock FROM product WHERE id = ?", Integer.class, id)` 返回给调用方，并结束当前函数/方法。 |
| 499 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 500 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 501 | <code>    private BigDecimal dashboardSales(MockHttpSession session) throws Exception {</code> | 声明 `dashboardSales` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 502 | <code>        return new BigDecimal(</code> | 把 `new BigDecimal(` 返回给调用方，并结束当前函数/方法。 |
| 503 | <code>                json(mvc.perform(get(&quot;/api/dashboard&quot;).session(session)).andReturn()).get(&quot;totalSales&quot;).asText());</code> | 执行测试模拟请求并取得响应，供后续读取会话、JSON 或业务 ID 使用。 |
| 504 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 505 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 506 | <code>    private Map&lt;String, Object&gt; productInput(String name, String sku, long category, String price, int stock) {</code> | 声明 `productInput` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 507 | <code>        return Map.of(&quot;name&quot;, name, &quot;sku&quot;, sku, &quot;categoryId&quot;, category, &quot;price&quot;, new BigDecimal(price), &quot;testInitialStock&quot;, stock,</code> | 返回本行表达式的结果，交给调用方或作为接口响应。 |
| 508 | <code>                &quot;status&quot;, &quot;ON_SALE&quot;, &quot;description&quot;, &quot;测试商品&quot;);</code> | 继续构造本次测试的键值请求数据：`"status", "ON_SALE", "description", "测试商品");`，供模拟接口调用使用。 |
| 509 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 510 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 511 | <code>    private Map&lt;String, Object&gt; orderInput(List&lt;Map&lt;String, Object&gt;&gt; items) {</code> | 声明 `orderInput` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 512 | <code>        return Map.of(&quot;customerName&quot;, &quot;测试客户&quot;, &quot;phone&quot;, &quot;13800138000&quot;, &quot;address&quot;, &quot;测试收货地址&quot;, &quot;items&quot;, items);</code> | 把 `Map.of("customerName", "测试客户", "phone", "13800138000", "address", "测试收货地址", "items", items)` 返回给调用方，并结束当前函数/方法。 |
| 513 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 514 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
