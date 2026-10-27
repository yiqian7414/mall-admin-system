# backend/src/main/java/com/example/mall/model/Requests.java

源文件：[`backend/src/main/java/com/example/mall/model/Requests.java`](../../商城管理系统/backend/src/main/java/com/example/mall/model/Requests.java)。空行计入行号。

本文件按当前源码重新校对行号；未改变代码的已有讲解予以保留。

库存与图片的业务示例、整体流程见 [新功能说明](../库存管理与商品图片.md)。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>package com.example.mall.model;</code> | 声明 Java 包名 `com.example.mall.model`，用于组织类并区分同名类型。 |
| 2 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 3 | <code>import jakarta.validation.Valid;</code> | 导入 `jakarta.validation.Valid`：触发参数对象中的 Bean Validation 约束。 |
| 4 | <code>import jakarta.validation.constraints.*;</code> | 导入 `jakarta.validation.constraints.*`：让当前类可以直接使用该类型。 |
| 5 | <code>import java.math.BigDecimal;</code> | 导入 `java.math.BigDecimal`：使用十进制数处理金额，避免二进制浮点误差。 |
| 6 | <code>import java.util.List;</code> | 导入 `java.util.List`：使用有序集合。 |
| 7 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 8 | <code>/** 请求与响应分开，防止前端写入 id、库存流水、订单金额等服务器管理的字段。 */</code> | 块注释中的说明：请求与响应分开，防止前端写入 id、库存流水、订单金额等服务器管理的字段。；不参与运行。 |
| 9 | <code>public final class Requests {</code> | 声明 类 `Requests`，后续内容定义它的字段或行为。 |
| 10 | <code>    private Requests() {</code> | 声明 `Requests` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 11 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 12 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 13 | <code>    public record CategoryInput(@NotBlank(message = &quot;请填写分类名称&quot;) @Size(max = 60, message = &quot;分类名称最多 60 个字符&quot;) String name,</code> | 定义请求字段及后端校验：字符串必填且不能全是空白；Size限制长度，min/max取值见本行。message是校验失败返回的中文原因；校验在Controller的@Valid触发。 |
| 14 | <code>            @Size(max = 500, message = &quot;分类说明最多 500 个字符&quot;) String description) {</code> | 定义请求字段及后端校验：Size限制长度，min/max取值见本行。message是校验失败返回的中文原因；校验在Controller的@Valid触发。 |
| 15 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 16 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 17 | <code>    public record ProductInput(@NotBlank(message = &quot;请填写商品名称&quot;) @Size(max = 100, message = &quot;商品名称最多 100 个字符&quot;) String name,</code> | 定义请求字段及后端校验：字符串必填且不能全是空白；Size限制长度，min/max取值见本行。message是校验失败返回的中文原因；校验在Controller的@Valid触发。 |
| 18 | <code>            @NotBlank(message = &quot;请填写商品编号&quot;) @Pattern(regexp = &quot;[A-Za-z0-9_-]{1,50}&quot;, message = &quot;商品编号仅支持 1 至 50 位字母、数字、下划线和短横线&quot;) String sku,</code> | 定义请求字段及后端校验：字符串必填且不能全是空白；Pattern规定可用字符/状态，regexp是具体正则规则。message是校验失败返回的中文原因；校验在Controller的@Valid触发。 |
| 19 | <code>            @NotNull(message = &quot;请选择分类&quot;) @Positive(message = &quot;分类编号无效&quot;) Long categoryId,</code> | 定义请求字段及后端校验：值不能为null；编号必须大于0。message是校验失败返回的中文原因；校验在Controller的@Valid触发。 |
| 20 | <code>            @NotNull(message = &quot;请填写商品价格&quot;) @DecimalMin(value = &quot;0.00&quot;, message = &quot;商品价格不能小于 0&quot;) @Digits(integer = 10, fraction = 2, message = &quot;价格最多 10 位整数和 2 位小数&quot;) BigDecimal price,</code> | 定义请求字段及后端校验：值不能为null；金额不能小于0.00；金额最多10位整数与2位小数。message是校验失败返回的中文原因；校验在Controller的@Valid触发。 |
| 21 | <code>            @Pattern(regexp = &quot;^$&#124;/api/product-images/[a-f0-9-]{36}\\.png&quot;, message = &quot;请先上传有效的商品图片&quot;) String imageUrl,</code> | 声明或触发请求校验；缺失、类型错误或超范围的参数不能进入正常业务处理。 |
| 22 | <code>            @NotNull(message = &quot;请选择商品状态&quot;) @Pattern(regexp = &quot;ON_SALE&#124;OFF_SALE&quot;, message = &quot;商品状态无效&quot;) String status,</code> | 定义请求字段及后端校验：值不能为null；Pattern规定可用字符/状态，regexp是具体正则规则。message是校验失败返回的中文原因；校验在Controller的@Valid触发。 |
| 23 | <code>            @Size(max = 1000, message = &quot;商品说明最多 1000 个字符&quot;) String description) {</code> | 定义请求字段及后端校验：Size限制长度，min/max取值见本行。message是校验失败返回的中文原因；校验在Controller的@Valid触发。 |
| 24 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 25 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 26 | <code>    public record StockInput(</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 27 | <code>            @NotNull(message = &quot;请选择商品&quot;) @Positive Long productId,</code> | 声明或触发请求校验；缺失、类型错误或超范围的参数不能进入正常业务处理。 |
| 28 | <code>            @NotNull(message = &quot;请选择出入库类型&quot;) @Pattern(regexp = &quot;INBOUND&#124;OUTBOUND&quot;) String type,</code> | 声明或触发请求校验；缺失、类型错误或超范围的参数不能进入正常业务处理。 |
| 29 | <code>            @NotNull(message = &quot;请填写数量&quot;) @Min(1) @Max(1000000000) Integer quantity,</code> | 声明或触发请求校验；缺失、类型错误或超范围的参数不能进入正常业务处理。 |
| 30 | <code>            @NotBlank(message = &quot;请选择出入库原因&quot;) @Size(max = 80) String reason,</code> | 声明或触发请求校验；缺失、类型错误或超范围的参数不能进入正常业务处理。 |
| 31 | <code>            @Size(max = 200, message = &quot;备注最多 200 字&quot;) String remark,</code> | 声明或触发请求校验；缺失、类型错误或超范围的参数不能进入正常业务处理。 |
| 32 | <code>            @NotBlank @Pattern(regexp = &quot;[a-f0-9-]{36}&quot;) String requestId) {</code> | 声明或触发请求校验；缺失、类型错误或超范围的参数不能进入正常业务处理。 |
| 33 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 34 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 35 | <code>    public record OrderLineInput(@NotNull(message = &quot;请选择商品&quot;) @Positive(message = &quot;商品编号无效&quot;) Long productId,</code> | 定义请求字段及后端校验：值不能为null；编号必须大于0。message是校验失败返回的中文原因；校验在Controller的@Valid触发。 |
| 36 | <code>            @NotNull(message = &quot;请填写购买数量&quot;) @Min(value = 1, message = &quot;购买数量至少为 1&quot;) @Max(value = 1000000, message = &quot;单项购买数量不能超过 100 万&quot;) Integer quantity) {</code> | 定义请求字段及后端校验：值不能为null；Min限制数值下限；Max限制数值上限。message是校验失败返回的中文原因；校验在Controller的@Valid触发。 |
| 37 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 38 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 39 | <code>    public record OrderInput(</code> | 声明 不可变数据载体 record `OrderInput`，后续内容定义它的字段或行为。 |
| 40 | <code>            @NotBlank(message = &quot;请填写客户姓名&quot;) @Size(max = 100, message = &quot;客户姓名最多 100 个字符&quot;) String customerName,</code> | 定义请求字段及后端校验：字符串必填且不能全是空白；Size限制长度，min/max取值见本行。message是校验失败返回的中文原因；校验在Controller的@Valid触发。 |
| 41 | <code>            @NotBlank(message = &quot;请填写联系电话&quot;) @Pattern(regexp = &quot;[0-9+()\\-\\s]{5,30}&quot;, message = &quot;联系电话格式无效&quot;) String phone,</code> | 定义请求字段及后端校验：字符串必填且不能全是空白；Pattern规定可用字符/状态，regexp是具体正则规则。message是校验失败返回的中文原因；校验在Controller的@Valid触发。 |
| 42 | <code>            @NotBlank(message = &quot;请填写收货地址&quot;) @Size(max = 300, message = &quot;收货地址最多 300 个字符&quot;) String address,</code> | 定义请求字段及后端校验：字符串必填且不能全是空白；Size限制长度，min/max取值见本行。message是校验失败返回的中文原因；校验在Controller的@Valid触发。 |
| 43 | <code>            @NotEmpty(message = &quot;订单至少包含一件商品&quot;) @Size(max = 100, message = &quot;订单最多包含 100 种商品&quot;) List&lt;@NotNull(message = &quot;订单商品不能为空&quot;) @Valid OrderLineInput&gt; items) {</code> | 定义请求字段及后端校验：值不能为null；集合至少包含一项；Size限制长度，min/max取值见本行；逐项继续校验OrderLineInput内部字段。message是校验失败返回的中文原因；校验在Controller的@Valid触发。 |
| 44 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 45 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 46 | <code>    public record StatusInput(</code> | 声明 不可变数据载体 record `StatusInput`，后续内容定义它的字段或行为。 |
| 47 | <code>            @NotNull(message = &quot;请选择订单状态&quot;) @Pattern(regexp = &quot;PENDING&#124;PAID&#124;SHIPPED&#124;COMPLETED&#124;CANCELLED&quot;, message = &quot;订单状态无效&quot;) String status) {</code> | 定义请求字段及后端校验：值不能为null；Pattern规定可用字符/状态，regexp是具体正则规则。message是校验失败返回的中文原因；校验在Controller的@Valid触发。 |
| 48 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 49 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 50 | <code>    public record ProductStatusInput(</code> | 声明 不可变数据载体 record `ProductStatusInput`，后续内容定义它的字段或行为。 |
| 51 | <code>            @NotNull(message = &quot;请选择商品状态&quot;) @Pattern(regexp = &quot;ON_SALE&#124;OFF_SALE&quot;, message = &quot;商品状态无效&quot;) String status) {</code> | 定义请求字段及后端校验：值不能为null；Pattern规定可用字符/状态，regexp是具体正则规则。message是校验失败返回的中文原因；校验在Controller的@Valid触发。 |
| 52 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 53 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 54 | <code>    public record CreateUserInput(</code> | 声明 不可变数据载体 record `CreateUserInput`，后续内容定义它的字段或行为。 |
| 55 | <code>            @NotBlank(message = &quot;请填写用户名&quot;) @Pattern(regexp = &quot;[A-Za-z0-9_]{3,30}&quot;, message = &quot;用户名为 3 至 30 位字母、数字或下划线&quot;) String username,</code> | 定义请求字段及后端校验：字符串必填且不能全是空白；Pattern规定可用字符/状态，regexp是具体正则规则。message是校验失败返回的中文原因；校验在Controller的@Valid触发。 |
| 56 | <code>            @NotBlank(message = &quot;请填写姓名&quot;) @Size(max = 60, message = &quot;姓名最多 60 个字符&quot;) String displayName,</code> | 定义请求字段及后端校验：字符串必填且不能全是空白；Size限制长度，min/max取值见本行。message是校验失败返回的中文原因；校验在Controller的@Valid触发。 |
| 57 | <code>            @NotBlank(message = &quot;请填写密码&quot;) @Size(min = 8, max = 64, message = &quot;密码长度为 8 至 64 个字符&quot;) String password,</code> | 定义请求字段及后端校验：字符串必填且不能全是空白；Size限制长度，min/max取值见本行。message是校验失败返回的中文原因；校验在Controller的@Valid触发。 |
| 58 | <code>            @NotNull(message = &quot;请选择角色&quot;) @Pattern(regexp = &quot;ADMIN&#124;STAFF&quot;, message = &quot;角色无效&quot;) String role,</code> | 定义请求字段及后端校验：值不能为null；Pattern规定可用字符/状态，regexp是具体正则规则。message是校验失败返回的中文原因；校验在Controller的@Valid触发。 |
| 59 | <code>            @NotNull(message = &quot;请选择账号状态&quot;) Boolean enabled) {</code> | 定义请求字段及后端校验：值不能为null。message是校验失败返回的中文原因；校验在Controller的@Valid触发。 |
| 60 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 61 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 62 | <code>    public record UpdateUserInput(</code> | 声明 不可变数据载体 record `UpdateUserInput`，后续内容定义它的字段或行为。 |
| 63 | <code>            @NotBlank(message = &quot;请填写姓名&quot;) @Size(max = 60, message = &quot;姓名最多 60 个字符&quot;) String displayName,</code> | 定义请求字段及后端校验：字符串必填且不能全是空白；Size限制长度，min/max取值见本行。message是校验失败返回的中文原因；校验在Controller的@Valid触发。 |
| 64 | <code>            @NotNull(message = &quot;请选择角色&quot;) @Pattern(regexp = &quot;ADMIN&#124;STAFF&quot;, message = &quot;角色无效&quot;) String role,</code> | 定义请求字段及后端校验：值不能为null；Pattern规定可用字符/状态，regexp是具体正则规则。message是校验失败返回的中文原因；校验在Controller的@Valid触发。 |
| 65 | <code>            @NotNull(message = &quot;请选择账号状态&quot;) Boolean enabled, @Size(max = 64, message = &quot;密码最多 64 个字符&quot;) String password) {</code> | 定义请求字段及后端校验：值不能为null；Size限制长度，min/max取值见本行。message是校验失败返回的中文原因；校验在Controller的@Valid触发。 |
| 66 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 67 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
