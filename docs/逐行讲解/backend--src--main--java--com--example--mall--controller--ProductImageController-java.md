# backend/src/main/java/com/example/mall/controller/ProductImageController.java

源文件：[`backend/src/main/java/com/example/mall/controller/ProductImageController.java`](../../商城管理系统/backend/src/main/java/com/example/mall/controller/ProductImageController.java)。空行计入行号。

商品图片上传与登录后的读取入口。

库存与图片的业务示例、整体流程见 [新功能说明](../库存管理与商品图片.md)。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>package com.example.mall.controller;</code> | 声明 Java 包，按控制器、业务、数据访问等职责组织类。 |
| 2 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 3 | <code>import com.example.mall.service.ProductImageService;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 4 | <code>import java.io.IOException;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 5 | <code>import java.util.Map;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 6 | <code>import org.springframework.core.io.Resource;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 7 | <code>import org.springframework.http.CacheControl;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 8 | <code>import org.springframework.http.MediaType;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 9 | <code>import org.springframework.http.ResponseEntity;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 10 | <code>import org.springframework.http.HttpStatus;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 11 | <code>import org.springframework.web.bind.annotation.*;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 12 | <code>import org.springframework.web.multipart.MultipartFile;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 13 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 14 | <code>@RestController</code> | 向 Spring 或 Java 声明本行对应类/方法的用途，结合后面的定义生效。 |
| 15 | <code>@RequestMapping(&quot;/api/product-images&quot;)</code> | 向 Spring 或 Java 声明本行对应类/方法的用途，结合后面的定义生效。 |
| 16 | <code>public class ProductImageController {</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 17 | <code>    private final ProductImageService images;</code> | 声明构造后不再替换的依赖字段，由构造器接收 Spring 管理的对象。 |
| 18 | <code>    public ProductImageController(ProductImageService images) { this.images = images; }</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 19 | <code>    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)</code> | 注册新增或业务提交接口；项目安全过滤器会检查登录与 CSRF。 |
| 20 | <code>    @ResponseStatus(HttpStatus.CREATED)</code> | 向 Spring 或 Java 声明本行对应类/方法的用途，结合后面的定义生效。 |
| 21 | <code>    public Map&lt;String, String&gt; upload(@RequestParam(&quot;file&quot;) MultipartFile file) throws IOException {</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 22 | <code>        return Map.of(&quot;imageUrl&quot;, images.upload(file));</code> | 返回本行表达式的结果，交给调用方或作为接口响应。 |
| 23 | <code>    }</code> | 结束当前作用域、参数、回调或集合定义。 |
| 24 | <code>    @GetMapping(&quot;/{filename}&quot;)</code> | 注册读取接口；这里的路径与控制器类上的路径组合成完整 API 地址。 |
| 25 | <code>    public ResponseEntity&lt;Resource&gt; read(@PathVariable String filename) {</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 26 | <code>        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).cacheControl(CacheControl.noCache().cachePrivate())</code> | 返回本行表达式的结果，交给调用方或作为接口响应。 |
| 27 | <code>                .header(&quot;X-Content-Type-Options&quot;, &quot;nosniff&quot;).body(images.read(filename));</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 28 | <code>    }</code> | 结束当前作用域、参数、回调或集合定义。 |
| 29 | <code>}</code> | 结束当前作用域、参数、回调或集合定义。 |
