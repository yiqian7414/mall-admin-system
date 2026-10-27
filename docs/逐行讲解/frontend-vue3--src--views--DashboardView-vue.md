# frontend-vue3/src/views/DashboardView.vue

源文件：[`frontend-vue3/src/views/DashboardView.vue`](../../商城管理系统/frontend-vue3/src/views/DashboardView.vue)。以下行号以当前文件为准，空行也计入。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>&lt;script setup&gt;</code> | 开始 Vue 组件的 JavaScript 区域；setup 写法中顶层声明可直接供本组件模板使用。 |
| 2 | <code>import { ref, computed, onMounted } from &quot;vue&quot;;</code> | 从 `vue` 导入 `{ ref, computed, onMounted }`。ref：创建带 .value 的响应式容器；computed：根据已有响应式数据计算并缓存派生值；onMounted：注册组件挂载后执行的回调。 |
| 3 | <code>import { api } from &quot;../api&quot;;</code> | 从 `../api` 导入 `{ api }`。导入后可以在本文件中使用这些组件或工具。 |
| 4 | <code>import { money, dateTime, orderLabels } from &quot;../format&quot;;</code> | 从 `../format` 导入 `{ money, dateTime, orderLabels }`。导入后可以在本文件中使用这些组件或工具。 |
| 5 | <code>const data = ref(null),</code> | 创建响应式变量 `data`，初始值由 ref(...) 指定；脚本中用 .value 读写，模板会自动解包。 |
| 6 | <code>  loading = ref(false),</code> | 继续同一条 const 声明，创建响应式变量 `loading`，初始内容为 `ref(false),`；这不是修改已有变量。`loading`：查询是否进行中 |
| 7 | <code>  error = ref(&quot;&quot;);</code> | 继续同一条 const 声明，创建响应式变量 `error`，初始内容为 `ref("");`；这不是修改已有变量。`error`：查询失败提示 |
| 8 | <code>const cards = computed(() =&gt;</code> | 定义计算属性 `cards`；根据回调使用的响应式数据自动重新计算，并缓存结果。 |
| 9 | <code>  data.value</code> | 读取概览接口的响应式数据，作为后面三元表达式条件；数据尚未加载时返回空统计列表。 |
| 10 | <code>    ? [</code> | 三元表达式的条件成立分支，使用 `[` 作为结果；对应另一分支在后续 : 后。 |
| 11 | <code>        {</code> | 继续声明数组元素或对象字面量：`{`；字段/元素按当前结构保存。 |
| 12 | <code>          label: &quot;商品总数&quot;,</code> | 定义对象成员 `label` 的值为 `"商品总数"`。 |
| 13 | <code>          value: data.value.productCount,</code> | 定义对象成员 `value` 的值为 `data.value.productCount`。 |
| 14 | <code>          hint: `${data.value.categoryCount} 个商品分类`,</code> | 定义对象成员 `hint` 的值为 ``${data.value.categoryCount} 个商品分类``。 |
| 15 | <code>          mark: &quot;▦&quot;,</code> | 定义对象成员 `mark` 的值为 `"▦"`。 |
| 16 | <code>          color: &quot;blue&quot;,</code> | 定义对象成员 `color` 的值为 `"blue"`。 |
| 17 | <code>        },</code> | 结束前面打开的代码块/对象，并保留逗号供后续成员继续声明；括号与前面的起始符号配对。 |
| 18 | <code>        {</code> | 继续声明数组元素或对象字面量：`{`；字段/元素按当前结构保存。 |
| 19 | <code>          label: &quot;订单总数&quot;,</code> | 定义对象成员 `label` 的值为 `"订单总数"`。 |
| 20 | <code>          value: data.value.orderCount,</code> | 定义对象成员 `value` 的值为 `data.value.orderCount`。 |
| 21 | <code>          hint: `${data.value.pendingOrderCount} 笔订单待付款`,</code> | 定义对象成员 `hint` 的值为 ``${data.value.pendingOrderCount} 笔订单待付款``。 |
| 22 | <code>          mark: &quot;▣&quot;,</code> | 定义对象成员 `mark` 的值为 `"▣"`。 |
| 23 | <code>          color: &quot;purple&quot;,</code> | 定义对象成员 `color` 的值为 `"purple"`。 |
| 24 | <code>        },</code> | 结束前面打开的代码块/对象，并保留逗号供后续成员继续声明；括号与前面的起始符号配对。 |
| 25 | <code>        {</code> | 继续声明数组元素或对象字面量：`{`；字段/元素按当前结构保存。 |
| 26 | <code>          label: &quot;累计销售额&quot;,</code> | 定义对象成员 `label` 的值为 `"累计销售额"`。 |
| 27 | <code>          value: money(data.value.totalSales),</code> | 定义对象成员 `value` 的值为 `money(data.value.totalSales)`。 |
| 28 | <code>          hint: &quot;已付款且未取消的订单金额&quot;,</code> | 定义对象成员 `hint` 的值为 `"已付款且未取消的订单金额"`。 |
| 29 | <code>          mark: &quot;¥&quot;,</code> | 定义对象成员 `mark` 的值为 `"¥"`。 |
| 30 | <code>          color: &quot;green&quot;,</code> | 定义对象成员 `color` 的值为 `"green"`。 |
| 31 | <code>        },</code> | 结束前面打开的代码块/对象，并保留逗号供后续成员继续声明；括号与前面的起始符号配对。 |
| 32 | <code>        {</code> | 继续声明数组元素或对象字面量：`{`；字段/元素按当前结构保存。 |
| 33 | <code>          label: &quot;库存预警&quot;,</code> | 定义对象成员 `label` 的值为 `"库存预警"`。 |
| 34 | <code>          value: data.value.lowStockCount,</code> | 定义对象成员 `value` 的值为 `data.value.lowStockCount`。 |
| 35 | <code>          hint: &quot;库存不超过 5 件的商品&quot;,</code> | 定义对象成员 `hint` 的值为 `"库存不超过 5 件的商品"`。 |
| 36 | <code>          mark: &quot;!&quot;,</code> | 定义对象成员 `mark` 的值为 `"!"`。 |
| 37 | <code>          color: &quot;orange&quot;,</code> | 定义对象成员 `color` 的值为 `"orange"`。 |
| 38 | <code>        },</code> | 结束前面打开的代码块/对象，并保留逗号供后续成员继续声明；括号与前面的起始符号配对。 |
| 39 | <code>      ]</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 40 | <code>    : [],</code> | 继续为上一行打开的元素设置属性 `: [],`，它与该元素属于同一个开始标签。 |
| 41 | <code>);</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 42 | <code>async function load() {</code> | 定义异步函数 `load`，接收参数 `无`；被事件或其他函数调用时执行内部逻辑。 |
| 43 | <code>  loading.value = true;</code> | 更新 `loading.value` 为 `true`。`loading`：查询是否进行中；.value 写入会通知依赖该 ref 的 Vue 模板更新。 |
| 44 | <code>  error.value = &quot;&quot;;</code> | 更新 `error.value` 为 `""`。`error`：查询失败提示；.value 写入会通知依赖该 ref 的 Vue 模板更新。 |
| 45 | <code>  try {</code> | 开始尝试执行可能失败的操作；抛出的异常交给后续 catch 统一处理。 |
| 46 | <code>    data.value = await api(&quot;/dashboard&quot;);</code> | 更新 `data.value` 为 `await api("/dashboard")`。；.value 写入会通知依赖该 ref 的 Vue 模板更新。 |
| 47 | <code>  } catch (cause) {</code> | 捕获前面 try 中抛出的错误 cause，进入对应的失败处理。 |
| 48 | <code>    error.value = cause.message;</code> | 更新 `error.value` 为 `cause.message`。`error`：查询失败提示；.value 写入会通知依赖该 ref 的 Vue 模板更新。 |
| 49 | <code>  } finally {</code> | 无论前面的操作成功还是失败，都执行此处的清理/状态恢复代码。 |
| 50 | <code>    loading.value = false;</code> | 更新 `loading.value` 为 `false`。`loading`：查询是否进行中；.value 写入会通知依赖该 ref 的 Vue 模板更新。 |
| 51 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 52 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 53 | <code>onMounted(load);</code> | 组件挂载到页面后启动初始数据请求。 |
| 54 | <code>&lt;/script&gt;</code> | 结束 Vue 组件的 JavaScript 区域。 |
| 55 | <code>&lt;template&gt;</code> | 开始 Vue 模板：声明当前组件要渲染的页面结构和数据绑定。 |
| 56 | <code>  &lt;div v-if=&quot;error&quot; class=&quot;error-banner&quot; role=&quot;alert&quot;&gt;</code> | 声明通用布局容器；条件成立才创建此区域：`error`；class 连接下方/公共 CSS 样式。 |
| 57 | <code>    {{ error }}&lt;button @click=&quot;load&quot;&gt;重试&lt;/button&gt;</code> | 插值 `{{ error }}<button @click="load">重试</button>`：计算双花括号中的表达式并作为普通文字显示，避免把商品名等输入当作 HTML 执行。 |
| 58 | <code>  &lt;/div&gt;</code> | 关闭 `div` 元素，结束对应区域的包含关系。 |
| 59 | <code>  &lt;div v-if=&quot;loading &amp;&amp; !data&quot; class=&quot;loading-state&quot;&gt;正在读取经营数据…&lt;/div&gt;</code> | 声明通用布局容器；条件成立才创建此区域：`loading && !data`；class 连接下方/公共 CSS 样式。 |
| 60 | <code>  &lt;template v-if=&quot;data&quot;</code> | 声明`template` 组件；条件成立才创建此区域：`data`。 |
| 61 | <code>    &gt;&lt;section class=&quot;stats-grid&quot;&gt;</code> | 结束上一个标签后，继续声明后面的元素：`><section class="stats-grid">`。这种换行保持相邻行内元素之间不额外插入空格。 |
| 62 | <code>      &lt;article v-for=&quot;card in cards&quot; :key=&quot;card.label&quot; class=&quot;stat-card&quot;&gt;</code> | 声明可独立阅读的内容；逐项生成此元素：`card in cards`；动态绑定 `key` 为 `card.label`，帮助 Vue 按稳定 ID 识别列表项；class 连接下方/公共 CSS 样式。 |
| 63 | <code>        &lt;div&gt;</code> | 声明通用布局容器。 |
| 64 | <code>          &lt;p&gt;{{ card.label }}&lt;/p&gt;</code> | 声明文字段落；以普通文字显示表达式 `card.label` 的结果。 |
| 65 | <code>          &lt;strong&gt;{{ card.value }}&lt;/strong</code> | 声明强调文字；以普通文字显示表达式 `card.value` 的结果。 |
| 66 | <code>          &gt;&lt;small&gt;{{ card.hint }}&lt;/small&gt;</code> | 插值 `><small>{{ card.hint }}</small>`：计算双花括号中的表达式并作为普通文字显示，避免把商品名等输入当作 HTML 执行。 |
| 67 | <code>        &lt;/div&gt;</code> | 关闭 `div` 元素，结束对应区域的包含关系。 |
| 68 | <code>        &lt;span class=&quot;stat-symbol&quot; :class=&quot;card.color&quot;&gt;{{ card.mark }}&lt;/span&gt;</code> | 声明行内文字容器；动态绑定 `class` 为 `card.color`；以普通文字显示表达式 `card.mark` 的结果；class 连接下方/公共 CSS 样式。 |
| 69 | <code>      &lt;/article&gt;</code> | 关闭 `article` 元素，结束对应区域的包含关系。 |
| 70 | <code>    &lt;/section&gt;</code> | 关闭 `section` 元素，结束对应区域的包含关系。 |
| 71 | <code>    &lt;section class=&quot;welcome-banner&quot;&gt;</code> | 声明独立内容分区；class 连接下方/公共 CSS 样式。 |
| 72 | <code>      &lt;div&gt;</code> | 声明通用布局容器。 |
| 73 | <code>        &lt;p class=&quot;eyebrow&quot;&gt;TODAY&#x27;S WORKSPACE&lt;/p&gt;</code> | 声明文字段落；class 连接下方/公共 CSS 样式。 |
| 74 | <code>        &lt;h2&gt;每一笔订单，都从有序管理开始。&lt;/h2&gt;</code> | 声明二级标题。 |
| 75 | <code>        &lt;p&gt;查看商品库存，处理待办订单，让经营保持顺畅。&lt;/p&gt;</code> | 声明文字段落。 |
| 76 | <code>      &lt;/div&gt;</code> | 关闭 `div` 元素，结束对应区域的包含关系。 |
| 77 | <code>      &lt;RouterLink to=&quot;/orders&quot; class=&quot;button&quot;</code> | 声明`RouterLink` 组件；class 连接下方/公共 CSS 样式。 |
| 78 | <code>        &gt;管理订单 &lt;span&gt;→&lt;/span&gt;&lt;/RouterLink</code> | 结束上一行打开的开始标签，随后显示文字/嵌套元素 `管理订单 <span>→</span></RouterLink`。 |
| 79 | <code>      &gt;</code> | 结束上一行打开的开始标签，随后显示文字/嵌套元素 ``。 |
| 80 | <code>    &lt;/section&gt;</code> | 关闭 `section` 元素，结束对应区域的包含关系。 |
| 81 | <code>    &lt;div class=&quot;dashboard-grid&quot;&gt;</code> | 声明通用布局容器；class 连接下方/公共 CSS 样式。 |
| 82 | <code>      &lt;section class=&quot;panel&quot;&gt;</code> | 声明独立内容分区；class 连接下方/公共 CSS 样式。 |
| 83 | <code>        &lt;header class=&quot;panel-header&quot;&gt;</code> | 声明顶部区域；class 连接下方/公共 CSS 样式。 |
| 84 | <code>          &lt;div&gt;</code> | 声明通用布局容器。 |
| 85 | <code>            &lt;h2&gt;最近订单&lt;/h2&gt;</code> | 声明二级标题。 |
| 86 | <code>            &lt;p&gt;最新创建的订单与处理状态&lt;/p&gt;</code> | 声明文字段落。 |
| 87 | <code>          &lt;/div&gt;</code> | 关闭 `div` 元素，结束对应区域的包含关系。 |
| 88 | <code>          &lt;RouterLink to=&quot;/orders&quot; class=&quot;text-link&quot;&gt;全部订单 →&lt;/RouterLink&gt;</code> | 声明`RouterLink` 组件；class 连接下方/公共 CSS 样式。 |
| 89 | <code>        &lt;/header&gt;</code> | 关闭 `header` 元素，结束对应区域的包含关系。 |
| 90 | <code>        &lt;div class=&quot;table-wrap&quot;&gt;</code> | 声明通用布局容器；class 连接下方/公共 CSS 样式。 |
| 91 | <code>          &lt;table&gt;</code> | 声明表格。 |
| 92 | <code>            &lt;thead&gt;</code> | 声明表头区域。 |
| 93 | <code>              &lt;tr&gt;</code> | 声明表格行。 |
| 94 | <code>                &lt;th&gt;订单 / 客户&lt;/th&gt;</code> | 声明表头单元格。 |
| 95 | <code>                &lt;th&gt;金额&lt;/th&gt;</code> | 声明表头单元格。 |
| 96 | <code>                &lt;th&gt;状态&lt;/th&gt;</code> | 声明表头单元格。 |
| 97 | <code>              &lt;/tr&gt;</code> | 关闭 `tr` 元素，结束对应区域的包含关系。 |
| 98 | <code>            &lt;/thead&gt;</code> | 关闭 `thead` 元素，结束对应区域的包含关系。 |
| 99 | <code>            &lt;tbody&gt;</code> | 声明表格数据区域。 |
| 100 | <code>              &lt;tr v-for=&quot;order in data.recentOrders&quot; :key=&quot;order.id&quot;&gt;</code> | 声明表格行；逐项生成此元素：`order in data.recentOrders`；动态绑定 `key` 为 `order.id`，帮助 Vue 按稳定 ID 识别列表项。 |
| 101 | <code>                &lt;td&gt;</code> | 声明数据单元格。 |
| 102 | <code>                  &lt;RouterLink</code> | 声明`RouterLink` 组件。 |
| 103 | <code>                    :to=&quot;{ path: &#x27;/orders&#x27;, query: { orderId: order.id } }&quot;</code> | 继续为上一行打开的元素设置属性 `:to="{ path: '/orders', query: { orderId: order.id } }"`，它与该元素属于同一个开始标签。 |
| 104 | <code>                    class=&quot;table-title&quot;</code> | 继续为上一行打开的元素设置属性 `class="table-title"`，它与该元素属于同一个开始标签。 |
| 105 | <code>                    &gt;{{ order.orderNo }}&lt;/RouterLink</code> | 插值 `>{{ order.orderNo }}</RouterLink`：计算双花括号中的表达式并作为普通文字显示，避免把商品名等输入当作 HTML 执行。 |
| 106 | <code>                  &gt;&lt;small class=&quot;cell-note&quot;</code> | 结束上一个标签后，继续声明后面的元素：`><small class="cell-note"`。这种换行保持相邻行内元素之间不额外插入空格。 |
| 107 | <code>                    &gt;{{ order.customerName }} ·</code> | 插值 `>{{ order.customerName }} ·`：计算双花括号中的表达式并作为普通文字显示，避免把商品名等输入当作 HTML 执行。 |
| 108 | <code>                    {{ dateTime(order.createdAt) }}&lt;/small</code> | 插值 `{{ dateTime(order.createdAt) }}</small`：计算双花括号中的表达式并作为普通文字显示，避免把商品名等输入当作 HTML 执行。 |
| 109 | <code>                  &gt;</code> | 结束上一行打开的开始标签，随后显示文字/嵌套元素 ``。 |
| 110 | <code>                &lt;/td&gt;</code> | 关闭 `td` 元素，结束对应区域的包含关系。 |
| 111 | <code>                &lt;td class=&quot;money-cell&quot;&gt;{{ money(order.totalAmount) }}&lt;/td&gt;</code> | 声明数据单元格；以普通文字显示表达式 `money(order.totalAmount)` 的结果；class 连接下方/公共 CSS 样式。 |
| 112 | <code>                &lt;td&gt;</code> | 声明数据单元格。 |
| 113 | <code>                  &lt;span class=&quot;badge&quot; :class=&quot;order.status.toLowerCase()&quot;&gt;{{</code> | 声明行内文字容器；动态绑定 `class` 为 `order.status.toLowerCase()`；class 连接下方/公共 CSS 样式。 |
| 114 | <code>                    orderLabels[order.status]</code> | 继续前面打开的插值表达式：`orderLabels[order.status]`，计算结果作为文字显示。 |
| 115 | <code>                  }}&lt;/span&gt;</code> | 结束前面跨行的 Vue 插值表达式，并关闭/继续当前标签；表达式结果按普通文字显示。 |
| 116 | <code>                &lt;/td&gt;</code> | 关闭 `td` 元素，结束对应区域的包含关系。 |
| 117 | <code>              &lt;/tr&gt;</code> | 关闭 `tr` 元素，结束对应区域的包含关系。 |
| 118 | <code>              &lt;tr v-if=&quot;!data.recentOrders.length&quot;&gt;</code> | 声明表格行；条件成立才创建此区域：`!data.recentOrders.length`。 |
| 119 | <code>                &lt;td colspan=&quot;3&quot; class=&quot;empty-cell&quot;&gt;</code> | 声明数据单元格；class 连接下方/公共 CSS 样式。 |
| 120 | <code>                  还没有订单，去创建第一笔订单吧。</code> | 向用户显示文字 `还没有订单，去创建第一笔订单吧。`，作为按钮名称、提示或页面说明。 |
| 121 | <code>                &lt;/td&gt;</code> | 关闭 `td` 元素，结束对应区域的包含关系。 |
| 122 | <code>              &lt;/tr&gt;</code> | 关闭 `tr` 元素，结束对应区域的包含关系。 |
| 123 | <code>            &lt;/tbody&gt;</code> | 关闭 `tbody` 元素，结束对应区域的包含关系。 |
| 124 | <code>          &lt;/table&gt;</code> | 关闭 `table` 元素，结束对应区域的包含关系。 |
| 125 | <code>        &lt;/div&gt;</code> | 关闭 `div` 元素，结束对应区域的包含关系。 |
| 126 | <code>      &lt;/section&gt;</code> | 关闭 `section` 元素，结束对应区域的包含关系。 |
| 127 | <code>      &lt;section class=&quot;panel&quot;&gt;</code> | 声明独立内容分区；class 连接下方/公共 CSS 样式。 |
| 128 | <code>        &lt;header class=&quot;panel-header&quot;&gt;</code> | 声明顶部区域；class 连接下方/公共 CSS 样式。 |
| 129 | <code>          &lt;div&gt;</code> | 声明通用布局容器。 |
| 130 | <code>            &lt;h2&gt;库存关注&lt;/h2&gt;</code> | 声明二级标题。 |
| 131 | <code>            &lt;p&gt;及时补充低库存商品&lt;/p&gt;</code> | 声明文字段落。 |
| 132 | <code>          &lt;/div&gt;</code> | 关闭 `div` 元素，结束对应区域的包含关系。 |
| 133 | <code>          &lt;RouterLink to=&quot;/products&quot; class=&quot;text-link&quot;&gt;管理商品 →&lt;/RouterLink&gt;</code> | 声明`RouterLink` 组件；class 连接下方/公共 CSS 样式。 |
| 134 | <code>        &lt;/header&gt;</code> | 关闭 `header` 元素，结束对应区域的包含关系。 |
| 135 | <code>        &lt;div class=&quot;stock-list&quot;&gt;</code> | 声明通用布局容器；class 连接下方/公共 CSS 样式。 |
| 136 | <code>          &lt;div</code> | 声明通用布局容器。 |
| 137 | <code>            v-for=&quot;product in data.lowStockProducts&quot;</code> | 继续为上一行打开的元素设置属性 `v-for="product in data.lowStockProducts"`，它与该元素属于同一个开始标签。 |
| 138 | <code>            :key=&quot;product.id&quot;</code> | 继续为上一行打开的元素设置属性 `:key="product.id"`，它与该元素属于同一个开始标签。 |
| 139 | <code>            class=&quot;stock-item&quot;</code> | 继续为上一行打开的元素设置属性 `class="stock-item"`，它与该元素属于同一个开始标签。 |
| 140 | <code>          &gt;</code> | 结束上一行打开的开始标签，随后显示文字/嵌套元素 ``。 |
| 141 | <code>            &lt;span class=&quot;product-thumbnail&quot;&gt;{{</code> | 声明行内文字容器；class 连接下方/公共 CSS 样式。 |
| 142 | <code>              product.name.slice(0, 1)</code> | 继续前面打开的插值表达式：`product.name.slice(0, 1)`，计算结果作为文字显示。 |
| 143 | <code>            }}&lt;/span&gt;</code> | 结束前面跨行的 Vue 插值表达式，并关闭/继续当前标签；表达式结果按普通文字显示。 |
| 144 | <code>            &lt;div&gt;</code> | 声明通用布局容器。 |
| 145 | <code>              &lt;strong&gt;{{ product.name }}&lt;/strong</code> | 声明强调文字；以普通文字显示表达式 `product.name` 的结果。 |
| 146 | <code>              &gt;&lt;small&gt;{{ product.sku }}&lt;/small&gt;</code> | 插值 `><small>{{ product.sku }}</small>`：计算双花括号中的表达式并作为普通文字显示，避免把商品名等输入当作 HTML 执行。 |
| 147 | <code>            &lt;/div&gt;</code> | 关闭 `div` 元素，结束对应区域的包含关系。 |
| 148 | <code>            &lt;span class=&quot;stock-count&quot;&gt;剩余 {{ product.stock }} 件&lt;/span&gt;</code> | 声明行内文字容器；以普通文字显示表达式 `product.stock` 的结果；class 连接下方/公共 CSS 样式。 |
| 149 | <code>          &lt;/div&gt;</code> | 关闭 `div` 元素，结束对应区域的包含关系。 |
| 150 | <code>          &lt;p v-if=&quot;!data.lowStockProducts.length&quot; class=&quot;empty-cell&quot;&gt;</code> | 声明文字段落；条件成立才创建此区域：`!data.lowStockProducts.length`；class 连接下方/公共 CSS 样式。 |
| 151 | <code>            当前没有低库存商品。</code> | 向用户显示文字 `当前没有低库存商品。`，作为按钮名称、提示或页面说明。 |
| 152 | <code>          &lt;/p&gt;</code> | 关闭 `p` 元素，结束对应区域的包含关系。 |
| 153 | <code>        &lt;/div&gt;</code> | 关闭 `div` 元素，结束对应区域的包含关系。 |
| 154 | <code>      &lt;/section&gt;</code> | 关闭 `section` 元素，结束对应区域的包含关系。 |
| 155 | <code>    &lt;/div&gt;&lt;/template</code> | 关闭 `div` 元素，结束对应区域的包含关系。 |
| 156 | <code>  &gt;</code> | 结束上一行打开的开始标签，随后显示文字/嵌套元素 ``。 |
| 157 | <code>&lt;/template&gt;</code> | 结束 Vue 模板区域。 |
