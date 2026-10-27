# frontend-vue3/src/format.js

源文件：[`frontend-vue3/src/format.js`](../../商城管理系统/frontend-vue3/src/format.js)。以下行号以当前文件为准，空行也计入。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>export const money = (value) =&gt;</code> | 导出下方声明，允许其他模块通过 import 使用；const money = (value) =>。 |
| 2 | <code>  new Intl.NumberFormat(&quot;zh-CN&quot;, { style: &quot;currency&quot;, currency: &quot;CNY&quot; }).format(</code> | 创建中文人民币格式化器，currency=CNY 显示人民币符号并按货币规则保留小数。 |
| 3 | <code>    Number(value &#124;&#124; 0),</code> | 把传入金额转为数字；空值使用 0 作为显示默认值。 |
| 4 | <code>  );</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 5 | <code>export const dateTime = (value) =&gt;</code> | 导出下方声明，允许其他模块通过 import 使用；const dateTime = (value) =>。 |
| 6 | <code>  value ? new Date(value).toLocaleString(&quot;zh-CN&quot;, { hour12: false }) : &quot;—&quot;;</code> | 有时间值时转为 Date 并用中文 24 小时格式显示；空值显示破折号。 |
| 7 | <code>export const orderLabels = {</code> | 导出下方声明，允许其他模块通过 import 使用；const orderLabels = {。 |
| 8 | <code>  PENDING: &quot;待付款&quot;,</code> | 定义对象成员 `PENDING` 的值为 `"待付款"`。 |
| 9 | <code>  PAID: &quot;待发货&quot;,</code> | 定义对象成员 `PAID` 的值为 `"待发货"`。 |
| 10 | <code>  SHIPPED: &quot;已发货&quot;,</code> | 定义对象成员 `SHIPPED` 的值为 `"已发货"`。 |
| 11 | <code>  COMPLETED: &quot;已完成&quot;,</code> | 定义对象成员 `COMPLETED` 的值为 `"已完成"`。 |
| 12 | <code>  CANCELLED: &quot;已取消&quot;,</code> | 定义对象成员 `CANCELLED` 的值为 `"已取消"`。 |
| 13 | <code>};</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 14 | <code>export const orderTransitions = {</code> | 导出下方声明，允许其他模块通过 import 使用；const orderTransitions = {。 |
| 15 | <code>  PENDING: [&quot;PAID&quot;, &quot;CANCELLED&quot;],</code> | 定义对象成员 `PENDING` 的值为 `["PAID", "CANCELLED"]`。 |
| 16 | <code>  PAID: [&quot;SHIPPED&quot;, &quot;CANCELLED&quot;],</code> | 定义对象成员 `PAID` 的值为 `["SHIPPED", "CANCELLED"]`。 |
| 17 | <code>  SHIPPED: [&quot;COMPLETED&quot;],</code> | 定义对象成员 `SHIPPED` 的值为 `["COMPLETED"]`。 |
| 18 | <code>  COMPLETED: [],</code> | 定义对象成员 `COMPLETED` 的值为 `[]`。 |
| 19 | <code>  CANCELLED: [],</code> | 定义对象成员 `CANCELLED` 的值为 `[]`。 |
| 20 | <code>};</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 21 | <code>export const transitionLabels = {</code> | 导出下方声明，允许其他模块通过 import 使用；const transitionLabels = {。 |
| 22 | <code>  PAID: &quot;标记已付款&quot;,</code> | 定义对象成员 `PAID` 的值为 `"标记已付款"`。 |
| 23 | <code>  SHIPPED: &quot;确认发货&quot;,</code> | 定义对象成员 `SHIPPED` 的值为 `"确认发货"`。 |
| 24 | <code>  COMPLETED: &quot;确认完成&quot;,</code> | 定义对象成员 `COMPLETED` 的值为 `"确认完成"`。 |
| 25 | <code>  CANCELLED: &quot;取消订单&quot;,</code> | 定义对象成员 `CANCELLED` 的值为 `"取消订单"`。 |
| 26 | <code>};</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
