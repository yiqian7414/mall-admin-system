# frontend-vue3/src/feedback.js

源文件：[`frontend-vue3/src/feedback.js`](../../商城管理系统/frontend-vue3/src/feedback.js)。以下行号以当前文件为准，空行也计入。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>import { reactive } from &quot;vue&quot;;</code> | 从 `vue` 导入 `{ reactive }`。reactive：把对象变成响应式对象。 |
| 2 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 3 | <code>export const notices = reactive([]);</code> | 导出下方声明，允许其他模块通过 import 使用；const notices = reactive([]);。 |
| 4 | <code>let nextId = 1;</code> | 声明 可以重新赋值的变量 `nextId`，用右侧 `1` 初始化。 |
| 5 | <code>export function notify(message, type = &quot;success&quot;) {</code> | 导出下方声明，允许其他模块通过 import 使用；function notify(message, type = "success") {。 |
| 6 | <code>  const id = nextId++;</code> | 声明 不能重新赋值绑定的常量 `id`，用右侧 `nextId++` 初始化。 |
| 7 | <code>  notices.push({ id, message, type });</code> | 把后面的对象/值追加到数组末尾；响应式数组变化会触发 Vue 更新，原生数组则需要手动重新渲染。 |
| 8 | <code>  setTimeout(() =&gt; dismissNotice(id), 4500);</code> | 安排或取消延迟操作，用于通知自动消失或相关界面清理；时间单位为毫秒。 |
| 9 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 10 | <code>export function dismissNotice(id) {</code> | 导出下方声明，允许其他模块通过 import 使用；function dismissNotice(id) {。 |
| 11 | <code>  const index = notices.findIndex((item) =&gt; item.id === id);</code> | 声明 不能重新赋值绑定的常量 `index`，用右侧 `notices.findIndex((item) => item.id === id)` 初始化。 |
| 12 | <code>  if (index &gt;= 0) notices.splice(index, 1);</code> | 判断条件 `index >= 0) notices.splice(index, 1`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 13 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 14 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 15 | <code>export const confirmation = reactive({</code> | 导出下方声明，允许其他模块通过 import 使用；const confirmation = reactive({。 |
| 16 | <code>  open: false,</code> | 定义对象成员 `open` 的值为 `false`。`open`：弹窗是否打开 |
| 17 | <code>  title: &quot;&quot;,</code> | 定义对象成员 `title` 的值为 `""`。 |
| 18 | <code>  message: &quot;&quot;,</code> | 定义对象成员 `message` 的值为 `""`。 |
| 19 | <code>  danger: false,</code> | 定义对象成员 `danger` 的值为 `false`。 |
| 20 | <code>  resolve: null,</code> | 定义对象成员 `resolve` 的值为 `null`。 |
| 21 | <code>});</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 22 | <code>export function confirmAction(title, message, danger = false) {</code> | 导出下方声明，允许其他模块通过 import 使用；function confirmAction(title, message, danger = false) {。 |
| 23 | <code>  return new Promise((resolve) =&gt; {</code> | 把 `new Promise((resolve) => {` 返回给调用方，并结束当前函数/方法。 |
| 24 | <code>    Object.assign(confirmation, {</code> | 把源对象字段复制到目标响应式对象，保留原对象引用与 Vue 绑定：`Object.assign(confirmation, {`。 |
| 25 | <code>      open: true,</code> | 定义对象成员 `open` 的值为 `true`。`open`：弹窗是否打开 |
| 26 | <code>      title,</code> | 使用变量/函数 `title` 的简写名称，作为当前 import 列表或对象同名成员。 |
| 27 | <code>      message,</code> | 使用变量/函数 `message` 的简写名称，作为当前 import 列表或对象同名成员。 |
| 28 | <code>      danger,</code> | 使用变量/函数 `danger` 的简写名称，作为当前 import 列表或对象同名成员。 |
| 29 | <code>      resolve,</code> | 使用变量/函数 `resolve` 的简写名称，作为当前 import 列表或对象同名成员。 |
| 30 | <code>    });</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 31 | <code>  });</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 32 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 33 | <code>export function resolveConfirmation(accepted) {</code> | 导出下方声明，允许其他模块通过 import 使用；function resolveConfirmation(accepted) {。 |
| 34 | <code>  confirmation.resolve?.(accepted);</code> | 调用确认弹窗保存的 Promise resolve，把 accepted 布尔值传回等待中的业务操作；?. 避免无待确认时调用空值。 |
| 35 | <code>  confirmation.open = false;</code> | 更新 `confirmation.open` 为 `false`。`open`：弹窗是否打开 |
| 36 | <code>  confirmation.resolve = null;</code> | 调用确认弹窗保存的 Promise resolve，把 accepted 布尔值传回等待中的业务操作；?. 避免无待确认时调用空值。 |
| 37 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
