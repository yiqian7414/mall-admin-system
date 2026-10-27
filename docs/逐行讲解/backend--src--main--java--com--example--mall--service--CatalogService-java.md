# backend/src/main/java/com/example/mall/service/CatalogService.java

源文件：[`backend/src/main/java/com/example/mall/service/CatalogService.java`](../../商城管理系统/backend/src/main/java/com/example/mall/service/CatalogService.java)。空行计入行号。

本文件按当前源码重新校对行号；未改变代码的已有讲解予以保留。

库存与图片的业务示例、整体流程见 [新功能说明](../库存管理与商品图片.md)。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>package com.example.mall.service;</code> | 声明 Java 包名 `com.example.mall.service`，用于组织类并区分同名类型。 |
| 2 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 3 | <code>import com.example.mall.common.BusinessException;</code> | 导入 `com.example.mall.common.BusinessException`：让当前类可以直接使用该类型。 |
| 4 | <code>import com.example.mall.model.Models.Category;</code> | 导入 `com.example.mall.model.Models.Category`：让当前类可以直接使用该类型。 |
| 5 | <code>import com.example.mall.model.Models.Page;</code> | 导入 `com.example.mall.model.Models.Page`：让当前类可以直接使用该类型。 |
| 6 | <code>import com.example.mall.model.Models.Product;</code> | 导入 `com.example.mall.model.Models.Product`：让当前类可以直接使用该类型。 |
| 7 | <code>import com.example.mall.model.Requests.CategoryInput;</code> | 导入 `com.example.mall.model.Requests.CategoryInput`：让当前类可以直接使用该类型。 |
| 8 | <code>import com.example.mall.model.Requests.ProductInput;</code> | 导入 `com.example.mall.model.Requests.ProductInput`：让当前类可以直接使用该类型。 |
| 9 | <code>import com.example.mall.repository.CategoryRepository;</code> | 导入 `com.example.mall.repository.CategoryRepository`：让当前类可以直接使用该类型。 |
| 10 | <code>import com.example.mall.repository.ProductRepository;</code> | 导入 `com.example.mall.repository.ProductRepository`：让当前类可以直接使用该类型。 |
| 11 | <code>import java.util.List;</code> | 导入 `java.util.List`：使用有序集合。 |
| 12 | <code>import org.springframework.stereotype.Service;</code> | 导入 `org.springframework.stereotype.Service`：将业务类注册为 Spring 服务。 |
| 13 | <code>import org.springframework.transaction.annotation.Transactional;</code> | 导入 `org.springframework.transaction.annotation.Transactional`：声明方法或类的数据库事务边界。 |
| 14 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 15 | <code>@Service</code> | 把此类注册为业务服务，供构造器注入使用。 |
| 16 | <code>public class CatalogService {</code> | 声明 类 `CatalogService`，后续内容定义它的字段或行为。 |
| 17 | <code>    private final CategoryRepository categories;</code> | 声明类型为 `CategoryRepository` 的 常量/依赖字段 `categories`；通过构造器或框架完成赋值。 |
| 18 | <code>    private final ProductRepository products;</code> | 声明类型为 `ProductRepository` 的 常量/依赖字段 `products`；通过构造器或框架完成赋值。 |
| 19 | <code>    private final com.example.mall.repository.StockRepository movements;</code> | 声明构造后不再替换的依赖字段，由构造器接收 Spring 管理的对象。 |
| 20 | <code>    private final ProductImageService images;</code> | 声明构造后不再替换的依赖字段，由构造器接收 Spring 管理的对象。 |
| 21 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 22 | <code>    public CatalogService(CategoryRepository categories, ProductRepository products,</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 23 | <code>            com.example.mall.repository.StockRepository movements, ProductImageService images) {</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 24 | <code>        this.categories = categories;</code> | 把构造器传入的依赖/字段保存到当前对象；this.categories = categories;，后续实例方法通过 this 的成员使用它。 |
| 25 | <code>        this.products = products;</code> | 把构造器传入的依赖/字段保存到当前对象；this.products = products;，后续实例方法通过 this 的成员使用它。 |
| 26 | <code>        this.movements = movements;</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 27 | <code>        this.images = images;</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 28 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 29 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 30 | <code>    public List&lt;Category&gt; categories() {</code> | 声明 `categories` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 31 | <code>        return categories.findAll();</code> | 把 `categories.findAll()` 返回给调用方，并结束当前函数/方法。 |
| 32 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 33 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 34 | <code>    @Transactional</code> | 为下方业务操作开启事务，运行时异常时回滚数据库修改。 |
| 35 | <code>    public Category createCategory(CategoryInput input) {</code> | 声明 `createCategory` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 36 | <code>        long id = categories.insert(input.name().trim(), text(input.description()));</code> | 声明 `id`，类型 `long`，保存 `categories.insert(input.name().trim(), text(input.description()))` 的结果。`id`：稳定记录主键 |
| 37 | <code>        return category(id);</code> | 把 `category(id)` 返回给调用方，并结束当前函数/方法。 |
| 38 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 39 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 40 | <code>    @Transactional</code> | 为下方业务操作开启事务，运行时异常时回滚数据库修改。 |
| 41 | <code>    public Category updateCategory(long id, CategoryInput input) {</code> | 声明 `updateCategory` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 42 | <code>        category(id);</code> | 读取分类或以 404 结束请求；本次参数为 `id`。 |
| 43 | <code>        categories.update(id, input.name().trim(), text(input.description()));</code> | 按主键更新已有记录；本行传入 `categories.update(id, input.name().trim(), text(input.description()));`。 |
| 44 | <code>        return category(id);</code> | 把 `category(id)` 返回给调用方，并结束当前函数/方法。 |
| 45 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 46 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 47 | <code>    @Transactional</code> | 为下方业务操作开启事务，运行时异常时回滚数据库修改。 |
| 48 | <code>    public void deleteCategory(long id) {</code> | 声明 `deleteCategory` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 49 | <code>        category(id);</code> | 读取分类或以 404 结束请求；本次参数为 `id`。 |
| 50 | <code>        if (categories.countProducts(id) &gt; 0)</code> | 判断条件 `categories.countProducts(id) > 0`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 51 | <code>            throw BusinessException.conflict(&quot;分类下仍有商品，请先移动或删除这些商品&quot;);</code> | 抛出错误 `BusinessException.conflict("分类下仍有商品，请先移动或删除这些商品")`，终止当前路径，并由异常处理器转换为失败响应或提示。 |
| 52 | <code>        categories.delete(id);</code> | 删除指定主键的记录；本行传入 `categories.delete(id);`。 |
| 53 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 54 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 55 | <code>    public Page&lt;Product&gt; products(String keyword, Long categoryId, String status, int page, int size) {</code> | 声明 `products` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 56 | <code>        checkPage(page, size);</code> | 检查页码和每页数量范围；本次参数为 `page, size`。 |
| 57 | <code>        if (categoryId != null &amp;&amp; categoryId &lt;= 0)</code> | 判断条件 `categoryId != null && categoryId <= 0`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 58 | <code>            throw BusinessException.badRequest(&quot;分类编号无效&quot;);</code> | 抛出错误 `BusinessException.badRequest("分类编号无效")`，终止当前路径，并由异常处理器转换为失败响应或提示。 |
| 59 | <code>        if (status != null &amp;&amp; !status.isBlank() &amp;&amp; !List.of(&quot;ON_SALE&quot;, &quot;OFF_SALE&quot;).contains(status)) {</code> | 判断条件 `status != null && !status.isBlank() && !List.of("ON_SALE", "OFF_SALE").contains(status)`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 60 | <code>            throw BusinessException.badRequest(&quot;商品状态无效&quot;);</code> | 抛出错误 `BusinessException.badRequest("商品状态无效")`，终止当前路径，并由异常处理器转换为失败响应或提示。 |
| 61 | <code>        }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 62 | <code>        return products.findPage(keyword, categoryId, status, page, size);</code> | 把 `products.findPage(keyword, categoryId, status, page, size)` 返回给调用方，并结束当前函数/方法。 |
| 63 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 64 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 65 | <code>    @Transactional</code> | 为下方业务操作开启事务，运行时异常时回滚数据库修改。 |
| 66 | <code>    public Product createProduct(ProductInput input) {</code> | 声明 `createProduct` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 67 | <code>        category(input.categoryId());</code> | 读取分类或以 404 结束请求；本次参数为 `input.categoryId()`。 |
| 68 | <code>        images.validateReference(input.imageUrl());</code> | 读取商品图片地址；后端只接受已上传且实际存在的图片，不能借此提交任意文件路径。 |
| 69 | <code>        return product(products.insert(input));</code> | 把 `product(products.insert(input))` 返回给调用方，并结束当前函数/方法。 |
| 70 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 71 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 72 | <code>    @Transactional</code> | 为下方业务操作开启事务，运行时异常时回滚数据库修改。 |
| 73 | <code>    public Product updateProduct(long id, ProductInput input) {</code> | 声明 `updateProduct` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 74 | <code>        products.lockById(id).orElseThrow(() -&gt; BusinessException.notFound(&quot;商品不存在&quot;));</code> | 在事务中锁定目标商品，其他写操作要等待，随后按数据库中的最新库存校验。 |
| 75 | <code>        category(input.categoryId());</code> | 读取分类或以 404 结束请求；本次参数为 `input.categoryId()`。 |
| 76 | <code>        images.validateReference(input.imageUrl());</code> | 读取商品图片地址；后端只接受已上传且实际存在的图片，不能借此提交任意文件路径。 |
| 77 | <code>        products.update(id, input);</code> | 按主键更新已有记录；本行传入 `products.update(id, input);`。 |
| 78 | <code>        return product(id);</code> | 把 `product(id)` 返回给调用方，并结束当前函数/方法。 |
| 79 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 80 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 81 | <code>    @Transactional</code> | 为下方业务操作开启事务，运行时异常时回滚数据库修改。 |
| 82 | <code>    public void deleteProduct(long id) {</code> | 声明 `deleteProduct` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 83 | <code>        var product = products.lockById(id).orElseThrow(() -&gt; BusinessException.notFound(&quot;商品不存在&quot;));</code> | 在事务中锁定目标商品，其他写操作要等待，随后按数据库中的最新库存校验。 |
| 84 | <code>        if (product.stock() != 0 &#124;&#124; movements.countByProduct(id) &gt; 0)</code> | 判断本行条件，只有成立时才进入对应分支；结合下一行看成功、拒绝或提前返回的处理。 |
| 85 | <code>            throw BusinessException.conflict(&quot;商品仍有库存或库存流水，请下架以保留业务记录&quot;);</code> | 拒绝不满足业务规则的操作，返回中文原因；处于事务内的数据库变更会回滚。 |
| 86 | <code>        if (products.countOrderReferences(id) &gt; 0) {</code> | 判断条件 `products.countOrderReferences(id) > 0`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 87 | <code>            throw BusinessException.conflict(&quot;商品已被订单引用，请将商品下架以保留订单记录&quot;);</code> | 抛出错误 `BusinessException.conflict("商品已被订单引用，请将商品下架以保留订单记录")`，终止当前路径，并由异常处理器转换为失败响应或提示。 |
| 88 | <code>        }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 89 | <code>        products.delete(id);</code> | 删除指定主键的记录；本行传入 `products.delete(id);`。 |
| 90 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 91 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 92 | <code>    @Transactional</code> | 为下方业务操作开启事务，运行时异常时回滚数据库修改。 |
| 93 | <code>    public Product changeProductStatus(long id, String status) {</code> | 声明 `changeProductStatus` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 94 | <code>        products.lockById(id).orElseThrow(() -&gt; BusinessException.notFound(&quot;商品不存在&quot;));</code> | 在事务中锁定目标商品，其他写操作要等待，随后按数据库中的最新库存校验。 |
| 95 | <code>        products.updateStatus(id, status);</code> | 更新订单状态字段；本行传入 `products.updateStatus(id, status);`。 |
| 96 | <code>        return product(id);</code> | 把 `product(id)` 返回给调用方，并结束当前函数/方法。 |
| 97 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 98 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 99 | <code>    private Category category(long id) {</code> | 声明 `category` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 100 | <code>        return categories.findById(id).orElseThrow(() -&gt; BusinessException.notFound(&quot;分类不存在&quot;));</code> | 把 `categories.findById(id).orElseThrow(() -> BusinessException.notFound("分类不存在"))` 返回给调用方，并结束当前函数/方法。 |
| 101 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 102 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 103 | <code>    private Product product(long id) {</code> | 声明 `product` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 104 | <code>        return products.findById(id).orElseThrow(() -&gt; BusinessException.notFound(&quot;商品不存在&quot;));</code> | 把 `products.findById(id).orElseThrow(() -> BusinessException.notFound("商品不存在"))` 返回给调用方，并结束当前函数/方法。 |
| 105 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 106 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 107 | <code>    public static void checkPage(int page, int size) {</code> | 声明 `checkPage` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 108 | <code>        if (page &lt; 1 &#124;&#124; size &lt; 1 &#124;&#124; size &gt; 100)</code> | 判断条件 `page < 1 &#124;&#124; size < 1 &#124;&#124; size > 100`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 109 | <code>            throw BusinessException.badRequest(&quot;页码至少为 1，每页数量为 1 至 100&quot;);</code> | 抛出错误 `BusinessException.badRequest("页码至少为 1，每页数量为 1 至 100")`，终止当前路径，并由异常处理器转换为失败响应或提示。 |
| 110 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 111 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 112 | <code>    private String text(String value) {</code> | 声明 `text` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 113 | <code>        return value == null ? &quot;&quot; : value.trim();</code> | 把 `value == null ? "" : value.trim()` 返回给调用方，并结束当前函数/方法。 |
| 114 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 115 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
