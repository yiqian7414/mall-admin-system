# backend/src/main/java/com/example/mall/service/OrderService.java

源文件：[`backend/src/main/java/com/example/mall/service/OrderService.java`](../../商城管理系统/backend/src/main/java/com/example/mall/service/OrderService.java)。空行计入行号。

本文件按当前源码重新校对行号；未改变代码的已有讲解予以保留。

库存与图片的业务示例、整体流程见 [新功能说明](../库存管理与商品图片.md)。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>package com.example.mall.service;</code> | 声明 Java 包名 `com.example.mall.service`，用于组织类并区分同名类型。 |
| 2 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 3 | <code>import com.example.mall.common.BusinessException;</code> | 导入 `com.example.mall.common.BusinessException`：让当前类可以直接使用该类型。 |
| 4 | <code>import com.example.mall.model.Models.Order;</code> | 导入 `com.example.mall.model.Models.Order`：让当前类可以直接使用该类型。 |
| 5 | <code>import com.example.mall.model.Models.OrderItem;</code> | 导入 `com.example.mall.model.Models.OrderItem`：让当前类可以直接使用该类型。 |
| 6 | <code>import com.example.mall.model.Models.Page;</code> | 导入 `com.example.mall.model.Models.Page`：让当前类可以直接使用该类型。 |
| 7 | <code>import com.example.mall.model.Requests.OrderInput;</code> | 导入 `com.example.mall.model.Requests.OrderInput`：让当前类可以直接使用该类型。 |
| 8 | <code>import com.example.mall.model.Requests.OrderLineInput;</code> | 导入 `com.example.mall.model.Requests.OrderLineInput`：让当前类可以直接使用该类型。 |
| 9 | <code>import com.example.mall.repository.OrderRepository;</code> | 导入 `com.example.mall.repository.OrderRepository`：让当前类可以直接使用该类型。 |
| 10 | <code>import com.example.mall.repository.ProductRepository;</code> | 导入 `com.example.mall.repository.ProductRepository`：让当前类可以直接使用该类型。 |
| 11 | <code>import java.math.BigDecimal;</code> | 导入 `java.math.BigDecimal`：使用十进制数处理金额，避免二进制浮点误差。 |
| 12 | <code>import java.time.LocalDateTime;</code> | 导入 `java.time.LocalDateTime`：表示不含时区的日期时间。 |
| 13 | <code>import java.time.format.DateTimeFormatter;</code> | 导入 `java.time.format.DateTimeFormatter`：让当前类可以直接使用该类型。 |
| 14 | <code>import java.util.ArrayList;</code> | 导入 `java.util.ArrayList`：创建可增长的列表。 |
| 15 | <code>import java.util.Comparator;</code> | 导入 `java.util.Comparator`：让当前类可以直接使用该类型。 |
| 16 | <code>import java.util.HashSet;</code> | 导入 `java.util.HashSet`：让当前类可以直接使用该类型。 |
| 17 | <code>import java.util.List;</code> | 导入 `java.util.List`：使用有序集合。 |
| 18 | <code>import java.util.Map;</code> | 导入 `java.util.Map`：使用键值映射。 |
| 19 | <code>import java.util.UUID;</code> | 导入 `java.util.UUID`：生成难以预测的唯一标识。 |
| 20 | <code>import org.springframework.stereotype.Service;</code> | 导入 `org.springframework.stereotype.Service`：将业务类注册为 Spring 服务。 |
| 21 | <code>import org.springframework.transaction.annotation.Transactional;</code> | 导入 `org.springframework.transaction.annotation.Transactional`：声明方法或类的数据库事务边界。 |
| 22 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 23 | <code>@Service</code> | 把此类注册为业务服务，供构造器注入使用。 |
| 24 | <code>public class OrderService {</code> | 声明 类 `OrderService`，后续内容定义它的字段或行为。 |
| 25 | <code>    private static final Map&lt;String, List&lt;String&gt;&gt; TRANSITIONS = Map.of(&quot;PENDING&quot;, List.of(&quot;PAID&quot;, &quot;CANCELLED&quot;), &quot;PAID&quot;,</code> | 定义只读状态流转表：待支付只能变为已支付或已取消；下一行继续已支付及其他状态的去向。 |
| 26 | <code>            List.of(&quot;SHIPPED&quot;, &quot;CANCELLED&quot;), &quot;SHIPPED&quot;, List.of(&quot;COMPLETED&quot;), &quot;COMPLETED&quot;, List.of(), &quot;CANCELLED&quot;,</code> | 已支付可发货或取消；已发货只可完成；已完成无后续状态；下一行定义已取消的终点规则。 |
| 27 | <code>            List.of());</code> | 已取消对应空的允许目标列表，阻止重复取消和重复归还库存，并结束状态表。 |
| 28 | <code>    private final OrderRepository orders;</code> | 保存订单仓储依赖，负责订单与明细 SQL；final 表示构造器赋值后不更换依赖。 |
| 29 | <code>    private final ProductRepository products;</code> | 保存商品仓储依赖，订单业务通过它加锁、检查并扣减/归还库存。 |
| 30 | <code>    private final InventoryService inventory;</code> | 声明构造后不再替换的依赖字段，由构造器接收 Spring 管理的对象。 |
| 31 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 32 | <code>    public OrderService(OrderRepository orders, ProductRepository products, InventoryService inventory) {</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 33 | <code>        this.orders = orders;</code> | 把构造器传入的依赖/字段保存到当前对象；this.orders = orders;，后续实例方法通过 this 的成员使用它。 |
| 34 | <code>        this.products = products;</code> | 把构造器传入的依赖/字段保存到当前对象；this.products = products;，后续实例方法通过 this 的成员使用它。 |
| 35 | <code>        this.inventory = inventory;</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 36 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 37 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 38 | <code>    public Page&lt;Order&gt; findPage(String keyword, String status, int page, int size) {</code> | 声明订单搜索分页方法；keyword/status 是筛选条件，page/size 是分页，返回 Page<Order>。 |
| 39 | <code>        CatalogService.checkPage(page, size);</code> | 复用分页参数检查：page 至少 1，size 为 1 至 100，之后才执行数据库分页查询。 |
| 40 | <code>        if (status != null &amp;&amp; !status.isBlank() &amp;&amp; !TRANSITIONS.containsKey(status)) {</code> | 只有用户传入非空状态时才检查它是否存在于状态表；未知值立即拒绝，避免无意义的数据库筛选。 |
| 41 | <code>            throw BusinessException.badRequest(&quot;订单状态无效&quot;);</code> | 抛出错误 `BusinessException.badRequest("订单状态无效")`，终止当前路径，并由异常处理器转换为失败响应或提示。 |
| 42 | <code>        }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 43 | <code>        return orders.findPage(keyword, status, page, size);</code> | 把已校验的筛选/分页交给仓储查询，并原样返回包含条目、总数、页码和每页数量的结果。 |
| 44 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 45 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 46 | <code>    public Order get(long id) {</code> | 声明按 ID 读取订单详情的方法。 |
| 47 | <code>        return orders.findById(id).orElseThrow(() -&gt; BusinessException.notFound(&quot;订单不存在&quot;));</code> | 仓储会读取订单及明细；Optional 为空则抛出 404，存在则返回完整 Order。 |
| 48 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 49 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 50 | <code>    /** 所有扣库存和订单明细写入必须在同一事务内成功，否则全部回滚。 */</code> | 块注释中的说明：所有扣库存和订单明细写入必须在同一事务内成功，否则全部回滚。；不参与运行。 |
| 51 | <code>    @Transactional</code> | 为下方业务操作开启事务，运行时异常时回滚数据库修改。 |
| 52 | <code>    public Order create(OrderInput input) {</code> | 开始创建订单业务；调用者已完成 DTO 字段约束，此处继续处理重复商品、上架、库存与金额规则。 |
| 53 | <code>        var uniqueIds = new HashSet&lt;Long&gt;();</code> | 创建不允许重复的商品 ID 集合，用来检查请求是否把同一商品拆成多行。 |
| 54 | <code>        for (OrderLineInput line : input.items()) {</code> | 逐条遍历请求中的订单商品行，只检查 ID 重复；尚未修改库存。 |
| 55 | <code>            if (!uniqueIds.add(line.productId()))</code> | HashSet.add 遇到已有 ID 返回 false；取反后进入重复商品的拒绝分支。 |
| 56 | <code>                throw BusinessException.badRequest(&quot;同一商品请合并为一行后提交&quot;);</code> | 抛出错误 `BusinessException.badRequest("同一商品请合并为一行后提交")`，终止当前路径，并由异常处理器转换为失败响应或提示。 |
| 57 | <code>        }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 58 | <code>        var lines = input.items().stream().sorted(Comparator.comparing(OrderLineInput::productId)).toList();</code> | 按商品 ID 升序整理订单行；并发订单采用同样顺序加锁，可降低交叉锁定带来的死锁机会。 |
| 59 | <code>        var snapshots = new ArrayList&lt;OrderItem&gt;();</code> | 创建订单明细快照列表，保存此刻数据库中的商品名称、价格、数量与行金额。 |
| 60 | <code>        BigDecimal total = new BigDecimal(&quot;0.00&quot;);</code> | 用字符串构造精确十进制零作为总额起点，避免通过 double 引入误差。 |
| 61 | <code>        String orderNo = &quot;M&quot; + LocalDateTime.now().format(DateTimeFormatter.ofPattern(&quot;yyyyMMddHHmmssSSS&quot;))</code> | 生成订单号前缀 M 加年月日时分秒毫秒；下一行再加入随机片段降低同毫秒冲突。 |
| 62 | <code>                + UUID.randomUUID().toString().substring(0, 8).toUpperCase(java.util.Locale.ROOT);</code> | 在时间前缀后拼接随机 UUID 的 8 位片段，并用固定 Locale 转大写，降低订单号冲突概率。 |
| 63 | <code>        for (OrderLineInput line : lines) {</code> | 按已排序的商品 ID 逐条处理订单行；这一循环中的扣库存与后面订单 INSERT 属于同一事务。 |
| 64 | <code>            var product = products.lockById(line.productId())</code> | 在事务中锁定目标商品，其他写操作要等待，随后按数据库中的最新库存校验。 |
| 65 | <code>                    .orElseThrow(() -&gt; BusinessException.notFound(&quot;订单中的商品不存在&quot;));</code> | 如果商品不存在，抛出 404 并回滚整笔创建；不能生成指向不存在商品的订单明细。 |
| 66 | <code>            if (!product.status().equals(&quot;ON_SALE&quot;))</code> | 只允许上架商品下单；下架商品即使仍有库存也不能加入新订单。 |
| 67 | <code>                throw BusinessException.conflict(product.name() + &quot;已下架&quot;);</code> | 抛出错误 `BusinessException.conflict(product.name() + "已下架")`，终止当前路径，并由异常处理器转换为失败响应或提示。 |
| 68 | <code>            if (product.stock() &lt; line.quantity())</code> | 比较数据库当前库存与请求数量，库存不足时拒绝整笔订单。 |
| 69 | <code>                throw BusinessException.conflict(product.name() + &quot;库存不足&quot;);</code> | 抛出错误 `BusinessException.conflict(product.name() + "库存不足")`，终止当前路径，并由异常处理器转换为失败响应或提示。 |
| 70 | <code>            inventory.move(product.id(), -line.quantity(), &quot;ORDER_DEDUCT&quot;, orderNo, &quot;创建订单扣减&quot;, &quot;&quot;);</code> | 调用统一库存业务，在当前事务中修改库存并写流水；订单扣减与取消归还也走这个入口。 |
| 71 | <code>            BigDecimal lineTotal = product.price().multiply(BigDecimal.valueOf(line.quantity()));</code> | 后端使用数据库单价乘购买数量算行金额，不接受前端自行提交的价格或金额。 |
| 72 | <code>            snapshots.add(new OrderItem(product.id(), product.name(), product.price(), line.quantity(), lineTotal));</code> | 记录下单时名称、单价与金额快照；以后修改商品价格，不会改变已有订单。 |
| 73 | <code>            total = total.add(lineTotal);</code> | 把行金额加进订单总额。BigDecimal 是不可变对象，add 返回新值，必须重新赋给 total。 |
| 74 | <code>        }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 75 | <code>        if (total.compareTo(new BigDecimal(&quot;9999999999999999.99&quot;)) &gt; 0) {</code> | 把总额与数据库 DECIMAL(18,2) 上限比较，超限拒绝并回滚；compareTo 大于 0 表示左侧更大。 |
| 76 | <code>            throw BusinessException.badRequest(&quot;订单总金额超出允许范围，请拆分订单&quot;);</code> | 抛出错误 `BusinessException.badRequest("订单总金额超出允许范围，请拆分订单")`，终止当前路径，并由异常处理器转换为失败响应或提示。 |
| 77 | <code>        }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 78 | <code>        long id = orders.insert(orderNo, input, total);</code> | 库存检查/扣减全部成功后插入 PENDING 订单主表，得到数据库自动生成的主键。 |
| 79 | <code>        for (OrderItem item : snapshots)</code> | 遍历前面已经算好的订单明细快照；没有花括号时循环仅作用于紧接着的一条语句。 |
| 80 | <code>            orders.insertItem(id, item);</code> | 把本行快照写入 order_item，并关联刚创建的订单 ID；失败会连同订单和库存一起回滚。 |
| 81 | <code>        return get(id);</code> | 重新读取完整订单及明细返回前端；事务完成提交后，此次库存/订单变更才一起持久化。 |
| 82 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 83 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 84 | <code>    @Transactional</code> | 为下方业务操作开启事务，运行时异常时回滚数据库修改。 |
| 85 | <code>    public Order changeStatus(long id, String targetStatus) {</code> | 开始订单状态更新；参数是订单主键和请求目标状态，方法整体处于事务中。 |
| 86 | <code>        var order = orders.lockById(id).orElseThrow(() -&gt; BusinessException.notFound(&quot;订单不存在&quot;));</code> | 先 SELECT ... FOR UPDATE 锁订单，再读取其当前状态；并发取消请求会串行检查，不能重复归还库存。 |
| 87 | <code>        if (!TRANSITIONS.get(order.status()).contains(targetStatus)) {</code> | 查状态流转表，目标必须是当前状态允许的下一步；已取消/已完成对应空列表，所以不能重复操作。 |
| 88 | <code>            throw BusinessException.conflict(&quot;订单不能从 &quot; + order.status() + &quot; 变为 &quot; + targetStatus);</code> | 抛出错误 `BusinessException.conflict("订单不能从 " + order.status() + " 变为 " + targetStatus)`，终止当前路径，并由异常处理器转换为失败响应或提示。 |
| 89 | <code>        }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 90 | <code>        if (targetStatus.equals(&quot;CANCELLED&quot;)) {</code> | 只有合法取消操作需要归还库存；支付、发货、完成均不再次扣减或归还。 |
| 91 | <code>            for (OrderItem item : order.items().stream().sorted(Comparator.comparing(OrderItem::productId)).toList()) {</code> | 按商品 ID 排序遍历订单明细，和下单保持一致锁顺序，减少并发死锁。 |
| 92 | <code>                products.lockById(item.productId()).orElseThrow(() -&gt; BusinessException.conflict(&quot;订单商品不存在，无法归还库存&quot;));</code> | 在事务中锁定目标商品，其他写操作要等待，随后按数据库中的最新库存校验。 |
| 93 | <code>                inventory.move(item.productId(), item.quantity(), &quot;ORDER_RETURN&quot;, order.orderNo(), &quot;取消订单归还&quot;, &quot;&quot;);</code> | 调用统一库存业务，在当前事务中修改库存并写流水；订单扣减与取消归还也走这个入口。 |
| 94 | <code>            }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 95 | <code>        }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 96 | <code>        orders.updateStatus(id, targetStatus);</code> | 库存处理全部成功后保存目标状态；订单状态与库存归还在同一事务里共同提交。 |
| 97 | <code>        return get(id);</code> | 重新读取完整订单及明细返回前端；事务完成提交后，此次库存/订单变更才一起持久化。 |
| 98 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 99 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
