# frontend-vue3/src/views/CategoriesView.vue

源文件：[`frontend-vue3/src/views/CategoriesView.vue`](../../商城管理系统/frontend-vue3/src/views/CategoriesView.vue)。以下行号以当前文件为准，空行也计入。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>&lt;script setup&gt;</code> | 开始 Vue 组件的 JavaScript 区域；setup 写法中顶层声明可直接供本组件模板使用。 |
| 2 | <code>import { ref, reactive, onMounted } from &quot;vue&quot;;</code> | 从 `vue` 导入 `{ ref, reactive, onMounted }`。ref：创建带 .value 的响应式容器；reactive：把对象变成响应式对象；onMounted：注册组件挂载后执行的回调。 |
| 3 | <code>import { api } from &quot;../api&quot;;</code> | 从 `../api` 导入 `{ api }`。导入后可以在本文件中使用这些组件或工具。 |
| 4 | <code>import { notify, confirmAction } from &quot;../feedback&quot;;</code> | 从 `../feedback` 导入 `{ notify, confirmAction }`。导入后可以在本文件中使用这些组件或工具。 |
| 5 | <code>import BaseModal from &quot;../components/BaseModal.vue&quot;;</code> | 从 `../components/BaseModal.vue` 导入 `BaseModal`。导入后可以在本文件中使用这些组件或工具。 |
| 6 | <code>const categories = ref([]),</code> | 创建响应式变量 `categories`，初始值由 ref(...) 指定；脚本中用 .value 读写，模板会自动解包。 |
| 7 | <code>  loading = ref(false),</code> | 继续同一条 const 声明，创建响应式变量 `loading`，初始内容为 `ref(false),`；这不是修改已有变量。`loading`：查询是否进行中 |
| 8 | <code>  error = ref(&quot;&quot;),</code> | 继续同一条 const 声明，创建响应式变量 `error`，初始内容为 `ref(""),`；这不是修改已有变量。`error`：查询失败提示 |
| 9 | <code>  open = ref(false),</code> | 继续同一条 const 声明，创建响应式变量 `open`，初始内容为 `ref(false),`；这不是修改已有变量。`open`：弹窗是否打开 |
| 10 | <code>  saving = ref(false),</code> | 继续同一条 const 声明，创建响应式变量 `saving`，初始内容为 `ref(false),`；这不是修改已有变量。`saving`：保存是否进行中，用来阻止重复提交 |
| 11 | <code>  formError = ref(&quot;&quot;);</code> | 继续同一条 const 声明，创建响应式变量 `formError`，初始内容为 `ref("");`；这不是修改已有变量。`formError`：表单保存失败提示 |
| 12 | <code>const draft = reactive({ id: null, name: &quot;&quot;, description: &quot;&quot; });</code> | 创建响应式对象 `draft`；直接修改它的属性会触发依赖它的模板更新，无需 .value。 |
| 13 | <code>async function load() {</code> | 定义异步函数 `load`，接收参数 `无`；被事件或其他函数调用时执行内部逻辑。 |
| 14 | <code>  loading.value = true;</code> | 更新 `loading.value` 为 `true`。`loading`：查询是否进行中；.value 写入会通知依赖该 ref 的 Vue 模板更新。 |
| 15 | <code>  error.value = &quot;&quot;;</code> | 更新 `error.value` 为 `""`。`error`：查询失败提示；.value 写入会通知依赖该 ref 的 Vue 模板更新。 |
| 16 | <code>  try {</code> | 开始尝试执行可能失败的操作；抛出的异常交给后续 catch 统一处理。 |
| 17 | <code>    categories.value = await api(&quot;/categories&quot;);</code> | 调用业务/仓储对象；本行传入 `categories.value = await api("/categories");`。 |
| 18 | <code>  } catch (cause) {</code> | 捕获前面 try 中抛出的错误 cause，进入对应的失败处理。 |
| 19 | <code>    error.value = cause.message;</code> | 更新 `error.value` 为 `cause.message`。`error`：查询失败提示；.value 写入会通知依赖该 ref 的 Vue 模板更新。 |
| 20 | <code>  } finally {</code> | 无论前面的操作成功还是失败，都执行此处的清理/状态恢复代码。 |
| 21 | <code>    loading.value = false;</code> | 更新 `loading.value` 为 `false`。`loading`：查询是否进行中；.value 写入会通知依赖该 ref 的 Vue 模板更新。 |
| 22 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 23 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 24 | <code>function edit(category = null) {</code> | 定义函数 `edit`，接收参数 `category = null`；被事件或其他函数调用时执行内部逻辑。 |
| 25 | <code>  Object.assign(draft, {</code> | 把源对象字段复制到目标响应式对象，保留原对象引用与 Vue 绑定：`Object.assign(draft, {`。 |
| 26 | <code>    id: category?.id ?? null,</code> | 定义对象成员 `id` 的值为 `category?.id ?? null`。`id`：稳定记录主键 |
| 27 | <code>    name: category?.name ?? &quot;&quot;,</code> | 定义对象成员 `name` 的值为 `category?.name ?? ""`。`name`：名称 |
| 28 | <code>    description: category?.description ?? &quot;&quot;,</code> | 定义对象成员 `description` 的值为 `category?.description ?? ""`。`description`：补充说明 |
| 29 | <code>  });</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 30 | <code>  formError.value = &quot;&quot;;</code> | 更新 `formError.value` 为 `""`。`formError`：表单保存失败提示；.value 写入会通知依赖该 ref 的 Vue 模板更新。 |
| 31 | <code>  open.value = true;</code> | 更新 `open.value` 为 `true`。`open`：弹窗是否打开；.value 写入会通知依赖该 ref 的 Vue 模板更新。 |
| 32 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 33 | <code>async function save() {</code> | 定义异步函数 `save`，接收参数 `无`；被事件或其他函数调用时执行内部逻辑。 |
| 34 | <code>  if (saving.value) return;</code> | 判断条件 `saving.value`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 35 | <code>  if (!draft.name.trim()) {</code> | 判断条件 `!draft.name.trim()`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 36 | <code>    formError.value = &quot;请输入分类名称。&quot;;</code> | 更新 `formError.value` 为 `"请输入分类名称。"`。`formError`：表单保存失败提示；.value 写入会通知依赖该 ref 的 Vue 模板更新。 |
| 37 | <code>    return;</code> | 立即结束当前函数/方法，不再执行后面的新增、保存或状态更新。 |
| 38 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 39 | <code>  saving.value = true;</code> | 更新 `saving.value` 为 `true`。`saving`：保存是否进行中，用来阻止重复提交；.value 写入会通知依赖该 ref 的 Vue 模板更新。 |
| 40 | <code>  formError.value = &quot;&quot;;</code> | 更新 `formError.value` 为 `""`。`formError`：表单保存失败提示；.value 写入会通知依赖该 ref 的 Vue 模板更新。 |
| 41 | <code>  try {</code> | 开始尝试执行可能失败的操作；抛出的异常交给后续 catch 统一处理。 |
| 42 | <code>    await api(draft.id ? `/categories/${draft.id}` : &quot;/categories&quot;, {</code> | 暂停当前异步函数，等待 `api(draft.id ? `/categories/${draft.id}` : "/categories", {` 完成；不会阻塞浏览器主线程。 |
| 43 | <code>      method: draft.id ? &quot;PUT&quot; : &quot;POST&quot;,</code> | 定义对象成员 `method` 的值为 `draft.id ? "PUT" : "POST"`。 |
| 44 | <code>      body: { name: draft.name.trim(), description: draft.description.trim() },</code> | 定义对象成员 `body` 的值为 `{ name: draft.name.trim(), description: draft.description.trim() }`。 |
| 45 | <code>    });</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 46 | <code>    open.value = false;</code> | 更新 `open.value` 为 `false`。`open`：弹窗是否打开；.value 写入会通知依赖该 ref 的 Vue 模板更新。 |
| 47 | <code>    notify(draft.id ? &quot;分类已更新。&quot; : &quot;分类已创建。&quot;);</code> | 向通知列表添加一条有类型与文字的消息；本次调用参数为 `draft.id ? "分类已更新。" : "分类已创建。"`。 |
| 48 | <code>    await load();</code> | 暂停当前异步函数，等待 `load()` 完成；不会阻塞浏览器主线程。 |
| 49 | <code>  } catch (cause) {</code> | 捕获前面 try 中抛出的错误 cause，进入对应的失败处理。 |
| 50 | <code>    formError.value = cause.message;</code> | 更新 `formError.value` 为 `cause.message`。`formError`：表单保存失败提示；.value 写入会通知依赖该 ref 的 Vue 模板更新。 |
| 51 | <code>  } finally {</code> | 无论前面的操作成功还是失败，都执行此处的清理/状态恢复代码。 |
| 52 | <code>    saving.value = false;</code> | 更新 `saving.value` 为 `false`。`saving`：保存是否进行中，用来阻止重复提交；.value 写入会通知依赖该 ref 的 Vue 模板更新。 |
| 53 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 54 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 55 | <code>async function remove(category) {</code> | 定义异步函数 `remove`，接收参数 `category`；被事件或其他函数调用时执行内部逻辑。 |
| 56 | <code>  if (</code> | 判断条件 ``；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 57 | <code>    !(await confirmAction(</code> | 等待用户在确认弹窗中的选择，并取反；取消时条件成立，随后的 return 终止这次操作。 |
| 58 | <code>      &quot;删除分类&quot;,</code> | 提供当前调用/对象使用的字符串或模板字符串：`"删除分类",`；${...}（出现时）会替换为表达式结果。 |
| 59 | <code>      `确认删除“${category.name}”？有关联商品的分类不能删除。`,</code> | 提供当前调用/对象使用的字符串或模板字符串：``确认删除“${category.name}”？有关联商品的分类不能删除。`,`；${...}（出现时）会替换为表达式结果。 |
| 60 | <code>      true,</code> | 使用变量/函数 `true` 的简写名称，作为当前 import 列表或对象同名成员。 |
| 61 | <code>    ))</code> | 结束前面打开的对象、数组、回调或函数调用；括号顺序与起始结构配对。 |
| 62 | <code>  )</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 63 | <code>    return;</code> | 立即结束当前函数/方法，不再执行后面的新增、保存或状态更新。 |
| 64 | <code>  try {</code> | 开始尝试执行可能失败的操作；抛出的异常交给后续 catch 统一处理。 |
| 65 | <code>    await api(`/categories/${category.id}`, { method: &quot;DELETE&quot; });</code> | 暂停当前异步函数，等待 `api(`/categories/${category.id}`, { method: "DELETE" })` 完成；不会阻塞浏览器主线程。 |
| 66 | <code>    notify(&quot;分类已删除。&quot;);</code> | 向通知列表添加一条有类型与文字的消息；本次调用参数为 `"分类已删除。"`。 |
| 67 | <code>    await load();</code> | 暂停当前异步函数，等待 `load()` 完成；不会阻塞浏览器主线程。 |
| 68 | <code>  } catch (cause) {</code> | 捕获前面 try 中抛出的错误 cause，进入对应的失败处理。 |
| 69 | <code>    notify(cause.message, &quot;error&quot;);</code> | 向通知列表添加一条有类型与文字的消息；本次调用参数为 `cause.message, "error"`。 |
| 70 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 71 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 72 | <code>onMounted(load);</code> | 组件挂载到页面后启动初始数据请求。 |
| 73 | <code>&lt;/script&gt;</code> | 结束 Vue 组件的 JavaScript 区域。 |
| 74 | <code>&lt;template&gt;</code> | 开始 Vue 模板：声明当前组件要渲染的页面结构和数据绑定。 |
| 75 | <code>  &lt;section class=&quot;panel&quot;&gt;</code> | 声明独立内容分区；class 连接下方/公共 CSS 样式。 |
| 76 | <code>    &lt;div class=&quot;toolbar&quot;&gt;</code> | 声明通用布局容器；class 连接下方/公共 CSS 样式。 |
| 77 | <code>      &lt;div&gt;</code> | 声明通用布局容器。 |
| 78 | <code>        &lt;h2&gt;</code> | 声明二级标题。 |
| 79 | <code>          分类列表 &lt;span class=&quot;count-pill&quot;&gt;{{ categories.length }}&lt;/span&gt;</code> | 插值 `分类列表 <span class="count-pill">{{ categories.length }}</span>`：计算双花括号中的表达式并作为普通文字显示，避免把商品名等输入当作 HTML 执行。 |
| 80 | <code>        &lt;/h2&gt;</code> | 关闭 `h2` 元素，结束对应区域的包含关系。 |
| 81 | <code>        &lt;p class=&quot;muted&quot;&gt;使用分类组织商城商品&lt;/p&gt;</code> | 声明文字段落；class 连接下方/公共 CSS 样式。 |
| 82 | <code>      &lt;/div&gt;</code> | 关闭 `div` 元素，结束对应区域的包含关系。 |
| 83 | <code>      &lt;button class=&quot;button&quot; @click=&quot;edit()&quot;&gt;＋ 新增分类&lt;/button&gt;</code> | 声明操作按钮；绑定 `click` 事件执行 `edit()`；class 连接下方/公共 CSS 样式。 |
| 84 | <code>    &lt;/div&gt;</code> | 关闭 `div` 元素，结束对应区域的包含关系。 |
| 85 | <code>    &lt;div v-if=&quot;error&quot; class=&quot;error-banner&quot; role=&quot;alert&quot;&gt;</code> | 声明通用布局容器；条件成立才创建此区域：`error`；class 连接下方/公共 CSS 样式。 |
| 86 | <code>      {{ error }}&lt;button @click=&quot;load&quot;&gt;重试&lt;/button&gt;</code> | 插值 `{{ error }}<button @click="load">重试</button>`：计算双花括号中的表达式并作为普通文字显示，避免把商品名等输入当作 HTML 执行。 |
| 87 | <code>    &lt;/div&gt;</code> | 关闭 `div` 元素，结束对应区域的包含关系。 |
| 88 | <code>    &lt;div class=&quot;table-wrap&quot;&gt;</code> | 声明通用布局容器；class 连接下方/公共 CSS 样式。 |
| 89 | <code>      &lt;table&gt;</code> | 声明表格。 |
| 90 | <code>        &lt;thead&gt;</code> | 声明表头区域。 |
| 91 | <code>          &lt;tr&gt;</code> | 声明表格行。 |
| 92 | <code>            &lt;th class=&quot;id-column&quot;&gt;编号&lt;/th&gt;</code> | 声明表头单元格；class 连接下方/公共 CSS 样式。 |
| 93 | <code>            &lt;th&gt;分类名称&lt;/th&gt;</code> | 声明表头单元格。 |
| 94 | <code>            &lt;th&gt;分类说明&lt;/th&gt;</code> | 声明表头单元格。 |
| 95 | <code>            &lt;th class=&quot;actions-column&quot;&gt;操作&lt;/th&gt;</code> | 声明表头单元格；class 连接下方/公共 CSS 样式。 |
| 96 | <code>          &lt;/tr&gt;</code> | 关闭 `tr` 元素，结束对应区域的包含关系。 |
| 97 | <code>        &lt;/thead&gt;</code> | 关闭 `thead` 元素，结束对应区域的包含关系。 |
| 98 | <code>        &lt;tbody&gt;</code> | 声明表格数据区域。 |
| 99 | <code>          &lt;tr v-for=&quot;category in categories&quot; :key=&quot;category.id&quot;&gt;</code> | 声明表格行；逐项生成此元素：`category in categories`；动态绑定 `key` 为 `category.id`，帮助 Vue 按稳定 ID 识别列表项。 |
| 100 | <code>            &lt;td class=&quot;muted&quot;&gt;#{{ category.id }}&lt;/td&gt;</code> | 声明数据单元格；以普通文字显示表达式 `category.id` 的结果；class 连接下方/公共 CSS 样式。 |
| 101 | <code>            &lt;td class=&quot;table-title&quot;&gt;{{ category.name }}&lt;/td&gt;</code> | 声明数据单元格；以普通文字显示表达式 `category.name` 的结果；class 连接下方/公共 CSS 样式。 |
| 102 | <code>            &lt;td class=&quot;description-cell&quot;&gt;{{ category.description &#124;&#124; &quot;—&quot; }}&lt;/td&gt;</code> | 声明数据单元格；以普通文字显示表达式 `category.description &#124;&#124; "—"` 的结果；class 连接下方/公共 CSS 样式。 |
| 103 | <code>            &lt;td&gt;</code> | 声明数据单元格。 |
| 104 | <code>              &lt;div class=&quot;row-actions&quot;&gt;</code> | 声明通用布局容器；class 连接下方/公共 CSS 样式。 |
| 105 | <code>                &lt;button @click=&quot;edit(category)&quot;&gt;编辑&lt;/button</code> | 声明操作按钮；绑定 `click` 事件执行 `edit(category)`。 |
| 106 | <code>                &gt;&lt;button class=&quot;danger-text&quot; @click=&quot;remove(category)&quot;&gt;</code> | 结束上一个标签后，继续声明后面的元素：`><button class="danger-text" @click="remove(category)">`。绑定输入/事件/显示值的属性与该元素属于同一个标签。 |
| 107 | <code>                  删除</code> | 向用户显示文字 `删除`，作为按钮名称、提示或页面说明。 |
| 108 | <code>                &lt;/button&gt;</code> | 关闭 `button` 元素，结束对应区域的包含关系。 |
| 109 | <code>              &lt;/div&gt;</code> | 关闭 `div` 元素，结束对应区域的包含关系。 |
| 110 | <code>            &lt;/td&gt;</code> | 关闭 `td` 元素，结束对应区域的包含关系。 |
| 111 | <code>          &lt;/tr&gt;</code> | 关闭 `tr` 元素，结束对应区域的包含关系。 |
| 112 | <code>          &lt;tr v-if=&quot;loading&quot;&gt;</code> | 声明表格行；条件成立才创建此区域：`loading`。 |
| 113 | <code>            &lt;td colspan=&quot;4&quot; class=&quot;empty-cell&quot;&gt;正在加载分类…&lt;/td&gt;</code> | 声明数据单元格；class 连接下方/公共 CSS 样式。 |
| 114 | <code>          &lt;/tr&gt;</code> | 关闭 `tr` 元素，结束对应区域的包含关系。 |
| 115 | <code>          &lt;tr v-else-if=&quot;!categories.length&quot;&gt;</code> | 声明表格行；前一条件不成立且此条件成立才创建此区域：`!categories.length`。 |
| 116 | <code>            &lt;td colspan=&quot;4&quot; class=&quot;empty-cell&quot;&gt;</code> | 声明数据单元格；class 连接下方/公共 CSS 样式。 |
| 117 | <code>              暂无分类，点击“新增分类”开始创建。</code> | 向用户显示文字 `暂无分类，点击“新增分类”开始创建。`，作为按钮名称、提示或页面说明。 |
| 118 | <code>            &lt;/td&gt;</code> | 关闭 `td` 元素，结束对应区域的包含关系。 |
| 119 | <code>          &lt;/tr&gt;</code> | 关闭 `tr` 元素，结束对应区域的包含关系。 |
| 120 | <code>        &lt;/tbody&gt;</code> | 关闭 `tbody` 元素，结束对应区域的包含关系。 |
| 121 | <code>      &lt;/table&gt;</code> | 关闭 `table` 元素，结束对应区域的包含关系。 |
| 122 | <code>    &lt;/div&gt;</code> | 关闭 `div` 元素，结束对应区域的包含关系。 |
| 123 | <code>  &lt;/section&gt;</code> | 关闭 `section` 元素，结束对应区域的包含关系。 |
| 124 | <code>  &lt;BaseModal</code> | 声明`BaseModal` 组件。 |
| 125 | <code>    :open=&quot;open&quot;</code> | 继续为上一行打开的元素设置属性 `:open="open"`，它与该元素属于同一个开始标签。 |
| 126 | <code>    :title=&quot;draft.id ? &#x27;编辑分类&#x27; : &#x27;新增分类&#x27;&quot;</code> | 继续为上一行打开的元素设置属性 `:title="draft.id ? '编辑分类' : '新增分类'"`，它与该元素属于同一个开始标签。 |
| 127 | <code>    :busy=&quot;saving&quot;</code> | 继续为上一行打开的元素设置属性 `:busy="saving"`，它与该元素属于同一个开始标签。 |
| 128 | <code>    @close=&quot;open = false&quot;</code> | 继续为上一行打开的元素设置属性 `@close="open = false"`，它与该元素属于同一个开始标签。 |
| 129 | <code>    &gt;&lt;form @submit.prevent=&quot;save&quot;&gt;</code> | 结束上一个标签后，继续声明后面的元素：`><form @submit.prevent="save">`。绑定输入/事件/显示值的属性与该元素属于同一个标签。 |
| 130 | <code>      &lt;div class=&quot;field&quot;&gt;</code> | 声明通用布局容器；class 连接下方/公共 CSS 样式。 |
| 131 | <code>        &lt;label for=&quot;category-name&quot;&gt;分类名称 &lt;span&gt;*&lt;/span&gt;&lt;/label</code> | 声明输入控件的文字标签。 |
| 132 | <code>        &gt;&lt;input</code> | 结束上一个标签后，继续声明后面的元素：`><input`。这种换行保持相邻行内元素之间不额外插入空格。 |
| 133 | <code>          id=&quot;category-name&quot;</code> | 继续为上一行打开的元素设置属性 `id="category-name"`，它与该元素属于同一个开始标签。 |
| 134 | <code>          v-model=&quot;draft.name&quot;</code> | 继续为上一行打开的元素设置属性 `v-model="draft.name"`，它与该元素属于同一个开始标签。 |
| 135 | <code>          maxlength=&quot;60&quot;</code> | 继续为上一行打开的元素设置属性 `maxlength="60"`，它与该元素属于同一个开始标签。 |
| 136 | <code>          required</code> | 继续为上一行打开的元素设置属性 `required`，它与该元素属于同一个开始标签。 |
| 137 | <code>          placeholder=&quot;例如：数码配件&quot;</code> | 继续为上一行打开的元素设置属性 `placeholder="例如：数码配件"`，它与该元素属于同一个开始标签。 |
| 138 | <code>        /&gt;</code> | 结束上一行开始的 HTML/组件标签；/> 表示该标签在此自闭合。 |
| 139 | <code>      &lt;/div&gt;</code> | 关闭 `div` 元素，结束对应区域的包含关系。 |
| 140 | <code>      &lt;div class=&quot;field&quot;&gt;</code> | 声明通用布局容器；class 连接下方/公共 CSS 样式。 |
| 141 | <code>        &lt;label for=&quot;category-description&quot;&gt;分类说明&lt;/label</code> | 声明输入控件的文字标签。 |
| 142 | <code>        &gt;&lt;textarea</code> | 结束上一个标签后，继续声明后面的元素：`><textarea`。这种换行保持相邻行内元素之间不额外插入空格。 |
| 143 | <code>          id=&quot;category-description&quot;</code> | 继续为上一行打开的元素设置属性 `id="category-description"`，它与该元素属于同一个开始标签。 |
| 144 | <code>          v-model=&quot;draft.description&quot;</code> | 继续为上一行打开的元素设置属性 `v-model="draft.description"`，它与该元素属于同一个开始标签。 |
| 145 | <code>          maxlength=&quot;500&quot;</code> | 继续为上一行打开的元素设置属性 `maxlength="500"`，它与该元素属于同一个开始标签。 |
| 146 | <code>          rows=&quot;3&quot;</code> | 继续为上一行打开的元素设置属性 `rows="3"`，它与该元素属于同一个开始标签。 |
| 147 | <code>          placeholder=&quot;说明这一分类包含的商品&quot;</code> | 继续为上一行打开的元素设置属性 `placeholder="说明这一分类包含的商品"`，它与该元素属于同一个开始标签。 |
| 148 | <code>        &gt;&lt;/textarea&gt;</code> | 结束上一个标签后，继续声明后面的元素：`></textarea>`。这种换行保持相邻行内元素之间不额外插入空格。 |
| 149 | <code>      &lt;/div&gt;</code> | 关闭 `div` 元素，结束对应区域的包含关系。 |
| 150 | <code>      &lt;p v-if=&quot;formError&quot; class=&quot;inline-error&quot; role=&quot;alert&quot;&gt;{{ formError }}&lt;/p&gt;</code> | 声明文字段落；条件成立才创建此区域：`formError`；以普通文字显示表达式 `formError` 的结果；class 连接下方/公共 CSS 样式。 |
| 151 | <code>      &lt;div class=&quot;form-actions&quot;&gt;</code> | 声明通用布局容器；class 连接下方/公共 CSS 样式。 |
| 152 | <code>        &lt;button</code> | 声明操作按钮。 |
| 153 | <code>          type=&quot;button&quot;</code> | 继续为上一行打开的元素设置属性 `type="button"`，它与该元素属于同一个开始标签。 |
| 154 | <code>          class=&quot;button secondary&quot;</code> | 继续为上一行打开的元素设置属性 `class="button secondary"`，它与该元素属于同一个开始标签。 |
| 155 | <code>          :disabled=&quot;saving&quot;</code> | 继续为上一行打开的元素设置属性 `:disabled="saving"`，它与该元素属于同一个开始标签。 |
| 156 | <code>          @click=&quot;open = false&quot;</code> | 继续为上一行打开的元素设置属性 `@click="open = false"`，它与该元素属于同一个开始标签。 |
| 157 | <code>        &gt;</code> | 结束上一行打开的开始标签，随后显示文字/嵌套元素 ``。 |
| 158 | <code>          取消&lt;/button</code> | 显示标签前的文字，并结束对应元素：`取消</button`。 |
| 159 | <code>        &gt;&lt;button class=&quot;button&quot; :disabled=&quot;saving&quot;&gt;</code> | 结束上一个标签后，继续声明后面的元素：`><button class="button" :disabled="saving">`。绑定输入/事件/显示值的属性与该元素属于同一个标签。 |
| 160 | <code>          {{ saving ? &quot;保存中…&quot; : &quot;保存分类&quot; }}</code> | 插值 `{{ saving ? "保存中…" : "保存分类" }}`：计算双花括号中的表达式并作为普通文字显示，避免把商品名等输入当作 HTML 执行。 |
| 161 | <code>        &lt;/button&gt;</code> | 关闭 `button` 元素，结束对应区域的包含关系。 |
| 162 | <code>      &lt;/div&gt;</code> | 关闭 `div` 元素，结束对应区域的包含关系。 |
| 163 | <code>    &lt;/form&gt;&lt;/BaseModal</code> | 关闭 `form` 元素，结束对应区域的包含关系。 |
| 164 | <code>  &gt;</code> | 结束上一行打开的开始标签，随后显示文字/嵌套元素 ``。 |
| 165 | <code>&lt;/template&gt;</code> | 结束 Vue 模板区域。 |
