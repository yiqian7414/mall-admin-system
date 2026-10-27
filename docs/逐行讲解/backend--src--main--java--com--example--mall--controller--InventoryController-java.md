# backend/src/main/java/com/example/mall/controller/InventoryController.java

源文件：[`backend/src/main/java/com/example/mall/controller/InventoryController.java`](../../商城管理系统/backend/src/main/java/com/example/mall/controller/InventoryController.java)。空行计入行号。

把库存 HTTP 请求交给库存业务服务，写请求由安全过滤器检查登录和 CSRF。

库存与图片的业务示例、整体流程见 [新功能说明](../库存管理与商品图片.md)。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>package com.example.mall.controller;</code> | 声明 Java 包，按控制器、业务、数据访问等职责组织类。 |
| 2 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 3 | <code>import com.example.mall.model.Models.Page;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 4 | <code>import com.example.mall.model.Models.StockMovement;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 5 | <code>import com.example.mall.model.Requests.StockInput;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 6 | <code>import com.example.mall.service.InventoryService;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 7 | <code>import jakarta.validation.Valid;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 8 | <code>import java.time.LocalDate;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 9 | <code>import org.springframework.format.annotation.DateTimeFormat;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 10 | <code>import org.springframework.http.HttpStatus;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 11 | <code>import org.springframework.web.bind.annotation.*;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 12 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 13 | <code>@RestController</code> | 向 Spring 或 Java 声明本行对应类/方法的用途，结合后面的定义生效。 |
| 14 | <code>@RequestMapping(&quot;/api/inventory&quot;)</code> | 向 Spring 或 Java 声明本行对应类/方法的用途，结合后面的定义生效。 |
| 15 | <code>public class InventoryController {</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 16 | <code>    private final InventoryService inventory;</code> | 声明构造后不再替换的依赖字段，由构造器接收 Spring 管理的对象。 |
| 17 | <code>    public InventoryController(InventoryService inventory) { this.inventory = inventory; }</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 18 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 19 | <code>    @GetMapping(&quot;/movements&quot;)</code> | 注册读取接口；这里的路径与控制器类上的路径组合成完整 API 地址。 |
| 20 | <code>    public Page&lt;StockMovement&gt; list(@RequestParam(required = false) String keyword,</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 21 | <code>            @RequestParam(required = false) String type,</code> | 从查询参数或 multipart 字段绑定值，按声明进行类型转换。 |
| 22 | <code>            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,</code> | 从查询参数或 multipart 字段绑定值，按声明进行类型转换。 |
| 23 | <code>            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,</code> | 从查询参数或 multipart 字段绑定值，按声明进行类型转换。 |
| 24 | <code>            @RequestParam(defaultValue = &quot;1&quot;) int page, @RequestParam(defaultValue = &quot;10&quot;) int size) {</code> | 从查询参数或 multipart 字段绑定值，按声明进行类型转换。 |
| 25 | <code>        return inventory.findPage(keyword, type, start, end, page, size);</code> | 返回本行表达式的结果，交给调用方或作为接口响应。 |
| 26 | <code>    }</code> | 结束当前作用域、参数、回调或集合定义。 |
| 27 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 28 | <code>    @PostMapping(&quot;/movements&quot;)</code> | 注册新增或业务提交接口；项目安全过滤器会检查登录与 CSRF。 |
| 29 | <code>    @ResponseStatus(HttpStatus.CREATED)</code> | 向 Spring 或 Java 声明本行对应类/方法的用途，结合后面的定义生效。 |
| 30 | <code>    public StockMovement create(@Valid @RequestBody StockInput input) { return inventory.manual(input); }</code> | 声明或触发请求校验；缺失、类型错误或超范围的参数不能进入正常业务处理。 |
| 31 | <code>}</code> | 结束当前作用域、参数、回调或集合定义。 |
