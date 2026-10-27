# backend/src/main/java/com/example/mall/controller/HealthController.java

源文件：[`backend/src/main/java/com/example/mall/controller/HealthController.java`](../../商城管理系统/backend/src/main/java/com/example/mall/controller/HealthController.java)。以下行号以当前文件为准，空行也计入。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>package com.example.mall.controller;</code> | 声明 Java 包名 `com.example.mall.controller`，用于组织类并区分同名类型。 |
| 2 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 3 | <code>import java.util.Map;</code> | 导入 `java.util.Map`：使用键值映射。 |
| 4 | <code>import org.springframework.web.bind.annotation.GetMapping;</code> | 导入 `org.springframework.web.bind.annotation.GetMapping`：映射 GET 查询请求。 |
| 5 | <code>import org.springframework.web.bind.annotation.RestController;</code> | 导入 `org.springframework.web.bind.annotation.RestController`：让控制器方法以 JSON 等响应体形式返回数据。 |
| 6 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 7 | <code>@RestController</code> | 把此类注册为 JSON 接口控制器。 |
| 8 | <code>public class HealthController {</code> | 声明 类 `HealthController`，后续内容定义它的字段或行为。 |
| 9 | <code>    @GetMapping(&quot;/api/health&quot;)</code> | 声明 GET 查询 请求路径：/api/health；请求匹配后由下方控制器方法处理。 |
| 10 | <code>    public Map&lt;String, String&gt; health() {</code> | 声明 `health` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 11 | <code>        return Map.of(&quot;application&quot;, &quot;mall-admin&quot;, &quot;status&quot;, &quot;UP&quot;);</code> | 把 `Map.of("application", "mall-admin", "status", "UP")` 返回给调用方，并结束当前函数/方法。 |
| 12 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 13 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
