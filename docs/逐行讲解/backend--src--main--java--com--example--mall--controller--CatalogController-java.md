# backend/src/main/java/com/example/mall/controller/CatalogController.java

源文件：[`backend/src/main/java/com/example/mall/controller/CatalogController.java`](../../商城管理系统/backend/src/main/java/com/example/mall/controller/CatalogController.java)。以下行号以当前文件为准，空行也计入。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>package com.example.mall.controller;</code> | 声明 Java 包名 `com.example.mall.controller`，用于组织类并区分同名类型。 |
| 2 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 3 | <code>import com.example.mall.model.Models.Category;</code> | 导入 `com.example.mall.model.Models.Category`：让当前类可以直接使用该类型。 |
| 4 | <code>import com.example.mall.model.Models.Page;</code> | 导入 `com.example.mall.model.Models.Page`：让当前类可以直接使用该类型。 |
| 5 | <code>import com.example.mall.model.Models.Product;</code> | 导入 `com.example.mall.model.Models.Product`：让当前类可以直接使用该类型。 |
| 6 | <code>import com.example.mall.model.Requests.CategoryInput;</code> | 导入 `com.example.mall.model.Requests.CategoryInput`：让当前类可以直接使用该类型。 |
| 7 | <code>import com.example.mall.model.Requests.ProductInput;</code> | 导入 `com.example.mall.model.Requests.ProductInput`：让当前类可以直接使用该类型。 |
| 8 | <code>import com.example.mall.model.Requests.ProductStatusInput;</code> | 导入 `com.example.mall.model.Requests.ProductStatusInput`：让当前类可以直接使用该类型。 |
| 9 | <code>import com.example.mall.service.CatalogService;</code> | 导入 `com.example.mall.service.CatalogService`：让当前类可以直接使用该类型。 |
| 10 | <code>import jakarta.validation.Valid;</code> | 导入 `jakarta.validation.Valid`：触发参数对象中的 Bean Validation 约束。 |
| 11 | <code>import java.util.List;</code> | 导入 `java.util.List`：使用有序集合。 |
| 12 | <code>import org.springframework.http.HttpStatus;</code> | 导入 `org.springframework.http.HttpStatus`：使用标准 HTTP 状态码。 |
| 13 | <code>import org.springframework.web.bind.annotation.*;</code> | 导入 `org.springframework.web.bind.annotation.*`：让当前类可以直接使用该类型。 |
| 14 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 15 | <code>@RestController</code> | 把此类注册为 JSON 接口控制器。 |
| 16 | <code>@RequestMapping(&quot;/api&quot;)</code> | 声明 公共 请求路径：/api；请求匹配后由下方控制器方法处理。 |
| 17 | <code>public class CatalogController {</code> | 声明 类 `CatalogController`，后续内容定义它的字段或行为。 |
| 18 | <code>    private final CatalogService catalog;</code> | 声明类型为 `CatalogService` 的 常量/依赖字段 `catalog`；通过构造器或框架完成赋值。 |
| 19 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 20 | <code>    public CatalogController(CatalogService catalog) {</code> | 声明 `CatalogController` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 21 | <code>        this.catalog = catalog;</code> | 把构造器传入的依赖/字段保存到当前对象；this.catalog = catalog;，后续实例方法通过 this 的成员使用它。 |
| 22 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 23 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 24 | <code>    @GetMapping(&quot;/categories&quot;)</code> | 声明 GET 查询 请求路径：/categories；请求匹配后由下方控制器方法处理。 |
| 25 | <code>    public List&lt;Category&gt; categories() {</code> | 声明 `categories` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 26 | <code>        return catalog.categories();</code> | 把 `catalog.categories()` 返回给调用方，并结束当前函数/方法。 |
| 27 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 28 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 29 | <code>    @PostMapping(&quot;/categories&quot;)</code> | 声明 POST 创建/动作 请求路径：/categories；请求匹配后由下方控制器方法处理。 |
| 30 | <code>    @ResponseStatus(HttpStatus.CREATED)</code> | 为下方处理结果指定 HTTP 状态。括号内参数为 `HttpStatus.CREATED`。 |
| 31 | <code>    public Category createCategory(@Valid @RequestBody CategoryInput input) {</code> | 声明 `createCategory` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 32 | <code>        return catalog.createCategory(input);</code> | 把 `catalog.createCategory(input)` 返回给调用方，并结束当前函数/方法。 |
| 33 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 34 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 35 | <code>    @PutMapping(&quot;/categories/{id}&quot;)</code> | 声明 PUT 更新 请求路径：/categories/{id}；请求匹配后由下方控制器方法处理。 |
| 36 | <code>    public Category updateCategory(@PathVariable long id, @Valid @RequestBody CategoryInput input) {</code> | 声明 `updateCategory` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 37 | <code>        return catalog.updateCategory(id, input);</code> | 把 `catalog.updateCategory(id, input)` 返回给调用方，并结束当前函数/方法。 |
| 38 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 39 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 40 | <code>    @DeleteMapping(&quot;/categories/{id}&quot;)</code> | 声明 DELETE 删除 请求路径：/categories/{id}；请求匹配后由下方控制器方法处理。 |
| 41 | <code>    @ResponseStatus(HttpStatus.NO_CONTENT)</code> | 为下方处理结果指定 HTTP 状态。括号内参数为 `HttpStatus.NO_CONTENT`。 |
| 42 | <code>    public void deleteCategory(@PathVariable long id) {</code> | 声明 `deleteCategory` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 43 | <code>        catalog.deleteCategory(id);</code> | 调用商品分类业务服务执行 `catalog.deleteCategory(id);`，控制器负责接收请求，规则与数据库操作在服务中完成。 |
| 44 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 45 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 46 | <code>    @GetMapping(&quot;/products&quot;)</code> | 声明 GET 查询 请求路径：/products；请求匹配后由下方控制器方法处理。 |
| 47 | <code>    public Page&lt;Product&gt; products(@RequestParam(required = false) String keyword,</code> | 声明返回类型为 `Page<Product>` 的方法 `products`；用于处理 `products` 对应的请求/错误。本行开始参数声明，后续可能继续分行。 |
| 48 | <code>            @RequestParam(required = false) Long categoryId, @RequestParam(required = false) String status,</code> | 应用 `RequestParam` 注解为下方声明附加框架规则。括号内参数为 `required = false) Long categoryId, @RequestParam(required = false`。 |
| 49 | <code>            @RequestParam(defaultValue = &quot;1&quot;) int page, @RequestParam(defaultValue = &quot;10&quot;) int size) {</code> | 应用 `RequestParam` 注解为下方声明附加框架规则。括号内参数为 `defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size`。 |
| 50 | <code>        return catalog.products(keyword, categoryId, status, page, size);</code> | 把 `catalog.products(keyword, categoryId, status, page, size)` 返回给调用方，并结束当前函数/方法。 |
| 51 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 52 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 53 | <code>    @PostMapping(&quot;/products&quot;)</code> | 声明 POST 创建/动作 请求路径：/products；请求匹配后由下方控制器方法处理。 |
| 54 | <code>    @ResponseStatus(HttpStatus.CREATED)</code> | 为下方处理结果指定 HTTP 状态。括号内参数为 `HttpStatus.CREATED`。 |
| 55 | <code>    public Product createProduct(@Valid @RequestBody ProductInput input) {</code> | 声明 `createProduct` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 56 | <code>        return catalog.createProduct(input);</code> | 把 `catalog.createProduct(input)` 返回给调用方，并结束当前函数/方法。 |
| 57 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 58 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 59 | <code>    @PutMapping(&quot;/products/{id}&quot;)</code> | 声明 PUT 更新 请求路径：/products/{id}；请求匹配后由下方控制器方法处理。 |
| 60 | <code>    public Product updateProduct(@PathVariable long id, @Valid @RequestBody ProductInput input) {</code> | 声明 `updateProduct` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 61 | <code>        return catalog.updateProduct(id, input);</code> | 把 `catalog.updateProduct(id, input)` 返回给调用方，并结束当前函数/方法。 |
| 62 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 63 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 64 | <code>    @DeleteMapping(&quot;/products/{id}&quot;)</code> | 声明 DELETE 删除 请求路径：/products/{id}；请求匹配后由下方控制器方法处理。 |
| 65 | <code>    @ResponseStatus(HttpStatus.NO_CONTENT)</code> | 为下方处理结果指定 HTTP 状态。括号内参数为 `HttpStatus.NO_CONTENT`。 |
| 66 | <code>    public void deleteProduct(@PathVariable long id) {</code> | 声明 `deleteProduct` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 67 | <code>        catalog.deleteProduct(id);</code> | 调用商品分类业务服务执行 `catalog.deleteProduct(id);`，控制器负责接收请求，规则与数据库操作在服务中完成。 |
| 68 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 69 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 70 | <code>    @PutMapping(&quot;/products/{id}/status&quot;)</code> | 声明 PUT 更新 请求路径：/products/{id}/status；请求匹配后由下方控制器方法处理。 |
| 71 | <code>    public Product changeStatus(@PathVariable long id, @Valid @RequestBody ProductStatusInput input) {</code> | 声明 `changeStatus` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 72 | <code>        return catalog.changeProductStatus(id, input.status());</code> | 把 `catalog.changeProductStatus(id, input.status())` 返回给调用方，并结束当前函数/方法。 |
| 73 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 74 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
