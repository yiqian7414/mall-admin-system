# backend/src/main/java/com/example/mall/service/InventoryService.java

源文件：[`backend/src/main/java/com/example/mall/service/InventoryService.java`](../../商城管理系统/backend/src/main/java/com/example/mall/service/InventoryService.java)。空行计入行号。

统一库存业务：加减库存与登记流水在同一事务中完成。

库存与图片的业务示例、整体流程见 [新功能说明](../库存管理与商品图片.md)。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>package com.example.mall.service;</code> | 声明 Java 包，按控制器、业务、数据访问等职责组织类。 |
| 2 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 3 | <code>import com.example.mall.common.BusinessException;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 4 | <code>import com.example.mall.model.Models.Page;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 5 | <code>import com.example.mall.model.Models.StockMovement;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 6 | <code>import com.example.mall.model.Requests.StockInput;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 7 | <code>import com.example.mall.repository.ProductRepository;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 8 | <code>import com.example.mall.repository.StockRepository;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 9 | <code>import java.time.LocalDate;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 10 | <code>import java.util.Set;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 11 | <code>import org.springframework.security.core.context.SecurityContextHolder;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 12 | <code>import org.springframework.stereotype.Service;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 13 | <code>import org.springframework.transaction.annotation.Transactional;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 14 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 15 | <code>/** 所有运行期库存变更统一经过这里：锁商品、校验、修改数量、写流水，同一事务提交。 */</code> | 代码注释，说明设计意图，不直接执行。 |
| 16 | <code>@Service</code> | 向 Spring 或 Java 声明本行对应类/方法的用途，结合后面的定义生效。 |
| 17 | <code>public class InventoryService {</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 18 | <code>    private final ProductRepository products;</code> | 声明构造后不再替换的依赖字段，由构造器接收 Spring 管理的对象。 |
| 19 | <code>    private final StockRepository movements;</code> | 声明构造后不再替换的依赖字段，由构造器接收 Spring 管理的对象。 |
| 20 | <code>    public InventoryService(ProductRepository products, StockRepository movements) {</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 21 | <code>        this.products = products; this.movements = movements;</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 22 | <code>    }</code> | 结束当前作用域、参数、回调或集合定义。 |
| 23 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 24 | <code>    public Page&lt;StockMovement&gt; findPage(String keyword, String type, LocalDate start, LocalDate end, int page, int size) {</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 25 | <code>        CatalogService.checkPage(page, size);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 26 | <code>        if (type != null &amp;&amp; !type.isBlank() &amp;&amp; !Set.of(&quot;INBOUND&quot;, &quot;OUTBOUND&quot;, &quot;ORDER_DEDUCT&quot;, &quot;ORDER_RETURN&quot;, &quot;OPENING&quot;).contains(type))</code> | 判断本行条件，只有成立时才进入对应分支；结合下一行看成功、拒绝或提前返回的处理。 |
| 27 | <code>            throw BusinessException.badRequest(&quot;库存业务类型无效&quot;);</code> | 拒绝不满足业务规则的操作，返回中文原因；处于事务内的数据库变更会回滚。 |
| 28 | <code>        if ((start != null &amp;&amp; start.getYear() &lt; 1900) &#124;&#124; (end != null &amp;&amp; (end.getYear() &lt; 1900 &#124;&#124; end.getYear() &gt; 9998))</code> | 判断本行条件，只有成立时才进入对应分支；结合下一行看成功、拒绝或提前返回的处理。 |
| 29 | <code>                &#124;&#124; (start != null &amp;&amp; end != null &amp;&amp; start.isAfter(end)))</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 30 | <code>            throw BusinessException.badRequest(&quot;日期范围无效&quot;);</code> | 拒绝不满足业务规则的操作，返回中文原因；处于事务内的数据库变更会回滚。 |
| 31 | <code>        return movements.findPage(keyword, type, start, end, page, size);</code> | 核对相同商品、业务类型和关联编号是否已处理，避免同一请求重试造成重复加减。 |
| 32 | <code>    }</code> | 结束当前作用域、参数、回调或集合定义。 |
| 33 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 34 | <code>    @Transactional</code> | 声明事务边界：库存、订单和流水相关数据库操作要么一起提交，要么失败回滚。 |
| 35 | <code>    public StockMovement manual(StockInput input) {</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 36 | <code>        var reasons = &quot;INBOUND&quot;.equals(input.type()) ? Set.of(&quot;采购补货&quot;, &quot;其他入库&quot;) : Set.of(&quot;商品报损&quot;, &quot;内部领用&quot;, &quot;其他出库&quot;);</code> | 声明本地变量或状态引用，保存后续代码要使用的值。 |
| 37 | <code>        if (!reasons.contains(input.reason())) throw BusinessException.badRequest(&quot;出入库原因无效&quot;);</code> | 拒绝不满足业务规则的操作，返回中文原因；处于事务内的数据库变更会回滚。 |
| 38 | <code>        return move(input.productId(), &quot;INBOUND&quot;.equals(input.type()) ? input.quantity() : -input.quantity(),</code> | 返回本行表达式的结果，交给调用方或作为接口响应。 |
| 39 | <code>                input.type(), (&quot;INBOUND&quot;.equals(input.type()) ? &quot;RK-&quot; : &quot;CK-&quot;) + input.requestId(), input.reason(),</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 40 | <code>                input.remark() == null ? &quot;&quot; : input.remark().trim());</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 41 | <code>    }</code> | 结束当前作用域、参数、回调或集合定义。 |
| 42 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 43 | <code>    @Transactional</code> | 声明事务边界：库存、订单和流水相关数据库操作要么一起提交，要么失败回滚。 |
| 44 | <code>    public StockMovement move(long productId, int delta, String type, String reference, String reason, String remark) {</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 45 | <code>        var product = products.lockById(productId).orElseThrow(() -&gt; BusinessException.notFound(&quot;商品不存在&quot;));</code> | 在事务中锁定目标商品，其他写操作要等待，随后按数据库中的最新库存校验。 |
| 46 | <code>        var previous = movements.find(productId, type, reference);</code> | 核对相同商品、业务类型和关联编号是否已处理，避免同一请求重试造成重复加减。 |
| 47 | <code>        if (previous.isPresent()) {</code> | 判断本行条件，只有成立时才进入对应分支；结合下一行看成功、拒绝或提前返回的处理。 |
| 48 | <code>            var old = previous.get();</code> | 声明本地变量或状态引用，保存后续代码要使用的值。 |
| 49 | <code>            if (old.delta() != delta &#124;&#124; !old.reason().equals(reason) &#124;&#124; !old.remark().equals(remark))</code> | 判断本行条件，只有成立时才进入对应分支；结合下一行看成功、拒绝或提前返回的处理。 |
| 50 | <code>                throw BusinessException.conflict(&quot;同一操作编号不能提交不同内容，请重新打开表单&quot;);</code> | 拒绝不满足业务规则的操作，返回中文原因；处于事务内的数据库变更会回滚。 |
| 51 | <code>            return old; // 同一个手动提交重试，不会再改一次库存。</code> | 同一请求且内容一致时返回原流水，库存不会再变动一次。 |
| 52 | <code>        }</code> | 结束当前作用域、参数、回调或集合定义。 |
| 53 | <code>        long after = (long) product.stock() + delta;</code> | 用原库存加带正负号的变动量计算新库存；比如 20 + (-3) = 17。 |
| 54 | <code>        if (delta == 0 &#124;&#124; after &lt; 0) throw BusinessException.conflict(&quot;库存不足，无法完成出库&quot;);</code> | 拒绝不满足业务规则的操作，返回中文原因；处于事务内的数据库变更会回滚。 |
| 55 | <code>        if (after &gt; 1000000000) throw BusinessException.conflict(&quot;变更后库存不能超过 10 亿&quot;);</code> | 拒绝不满足业务规则的操作，返回中文原因；处于事务内的数据库变更会回滚。 |
| 56 | <code>        if (products.adjustStock(productId, delta) != 1) throw BusinessException.conflict(&quot;库存已变化，请刷新后重试&quot;);</code> | 执行带数量范围条件的库存更新；返回值是影响的商品行数，不是剩余库存。 |
| 57 | <code>        var authentication = SecurityContextHolder.getContext().getAuthentication();</code> | 声明本地变量或状态引用，保存后续代码要使用的值。 |
| 58 | <code>        String operator = authentication == null ? &quot;system&quot; : authentication.getName();</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 59 | <code>        return movements.insert(product, delta, type, reference, reason, remark, operator);</code> | 保存商品名称/SKU 快照、变动前后数量、原因、关联编号和实际操作人。 |
| 60 | <code>    }</code> | 结束当前作用域、参数、回调或集合定义。 |
| 61 | <code>}</code> | 结束当前作用域、参数、回调或集合定义。 |
