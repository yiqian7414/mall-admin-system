# frontend-vue3/src/components/BaseModal.vue

源文件：[`frontend-vue3/src/components/BaseModal.vue`](../../商城管理系统/frontend-vue3/src/components/BaseModal.vue)。以下行号以当前文件为准，空行也计入。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>&lt;script setup&gt;</code> | 开始 Vue 组件的 JavaScript 区域；setup 写法中顶层声明可直接供本组件模板使用。 |
| 2 | <code>import { ref, watch, nextTick, onBeforeUnmount } from &quot;vue&quot;;</code> | 从 `vue` 导入 `{ ref, watch, nextTick, onBeforeUnmount }`。ref：创建带 .value 的响应式容器；watch：监听数据变化并执行回调；nextTick：等待 Vue 完成本轮 DOM 更新。 |
| 3 | <code>const props = defineProps({</code> | 声明 不能重新赋值绑定的常量 `props`，用右侧 `defineProps({` 初始化。 |
| 4 | <code>  open: Boolean,</code> | 定义对象成员 `open` 的值为 `Boolean`。`open`：弹窗是否打开 |
| 5 | <code>  title: String,</code> | 定义对象成员 `title` 的值为 `String`。 |
| 6 | <code>  wide: Boolean,</code> | 定义对象成员 `wide` 的值为 `Boolean`。 |
| 7 | <code>  busy: Boolean,</code> | 定义对象成员 `busy` 的值为 `Boolean`。 |
| 8 | <code>  elevated: Boolean,</code> | 定义对象成员 `elevated` 的值为 `Boolean`。 |
| 9 | <code>});</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 10 | <code>const emit = defineEmits([&quot;close&quot;]);</code> | 声明 不能重新赋值绑定的常量 `emit`，用右侧 `defineEmits(["close"])` 初始化。 |
| 11 | <code>const panel = ref(null);</code> | 创建响应式变量 `panel`，初始值由 ref(...) 指定；脚本中用 .value 读写，模板会自动解包。 |
| 12 | <code>let previousFocus;</code> | 声明可在后续分支中赋值的变量 `previousFocus`，先不指定初值。 |
| 13 | <code>function keydown(event) {</code> | 定义函数 `keydown`，接收参数 `event`；被事件或其他函数调用时执行内部逻辑。 |
| 14 | <code>  if (event.key === &quot;Escape&quot; &amp;&amp; !props.busy) emit(&quot;close&quot;);</code> | 判断条件 `event.key === "Escape" && !props.busy) emit("close"`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 15 | <code>  if (event.key !== &quot;Tab&quot;) return;</code> | 判断条件 `event.key !== "Tab"`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 16 | <code>  const nodes = [</code> | 声明 不能重新赋值绑定的常量 `nodes`，用右侧 `[` 初始化。 |
| 17 | <code>    ...panel.value.querySelectorAll(</code> | 展开 `panel.value.querySelectorAll(` 的对象属性/数组元素，合并到当前结构中；不额外嵌套一层。 |
| 18 | <code>      &#x27;button:not(:disabled), input:not(:disabled), select:not(:disabled), textarea:not(:disabled), [tabindex=&quot;0&quot;]&#x27;,</code> | 提供当前调用/对象使用的字符串或模板字符串：`'button:not(:disabled), input:not(:disabled), select:not(:disabled), textarea:not(:disabled), [tabindex="0"]',`；${...}（出现时）会替换为表达式结果。 |
| 19 | <code>    ),</code> | 结束前面打开的代码块/对象，并保留逗号供后续成员继续声明；括号与前面的起始符号配对。 |
| 20 | <code>  ].filter((node) =&gt; node.offsetParent !== null);</code> | 只保留当前可见的可聚焦控件，焦点循环不会落到隐藏元素。 |
| 21 | <code>  if (!nodes.length) {</code> | 判断条件 `!nodes.length`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 22 | <code>    event.preventDefault();</code> | 阻止本次事件默认行为；弹窗键盘处理用它拦截 Tab 的默认跳出。 |
| 23 | <code>    return;</code> | 立即结束当前函数/方法，不再执行后面的新增、保存或状态更新。 |
| 24 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 25 | <code>  const first = nodes[0],</code> | 声明 不能重新赋值绑定的常量 `first`，用右侧 `nodes[0],` 初始化。 |
| 26 | <code>    last = nodes[nodes.length - 1];</code> | 更新 `last` 为 `nodes[nodes.length - 1]`。 |
| 27 | <code>  if (event.shiftKey &amp;&amp; document.activeElement === first) {</code> | 判断条件 `event.shiftKey && document.activeElement === first`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 28 | <code>    event.preventDefault();</code> | 阻止本次事件默认行为；弹窗键盘处理用它拦截 Tab 的默认跳出。 |
| 29 | <code>    last.focus();</code> | 把键盘输入焦点移到该输入框，便于继续录入。 |
| 30 | <code>  } else if (!event.shiftKey &amp;&amp; document.activeElement === last) {</code> | 前一条件不成立时，继续判断 `!event.shiftKey && document.activeElement === last`。 |
| 31 | <code>    event.preventDefault();</code> | 阻止本次事件默认行为；弹窗键盘处理用它拦截 Tab 的默认跳出。 |
| 32 | <code>    first.focus();</code> | 把键盘输入焦点移到该输入框，便于继续录入。 |
| 33 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 34 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 35 | <code>watch(</code> | 监听指定响应式值变化并执行回调。 |
| 36 | <code>  () =&gt; props.open,</code> | 定义箭头回调：`() => props.open,`；左侧是每次接收的参数，右侧给出筛选/查找/金额汇总结果。 |
| 37 | <code>  async (open) =&gt; {</code> | watch 的异步回调接收新的弹窗 open 值；打开后等待 DOM 更新再处理焦点。 |
| 38 | <code>    if (open) {</code> | 判断条件 `open`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 39 | <code>      previousFocus = document.activeElement;</code> | 更新 `previousFocus` 为 `document.activeElement`。 |
| 40 | <code>      await nextTick();</code> | 暂停当前异步函数，等待 `nextTick()` 完成；不会阻塞浏览器主线程。 |
| 41 | <code>      panel.value?.querySelector(&quot;input, select, textarea, button&quot;)?.focus();</code> | 把键盘输入焦点移到该输入框，便于继续录入。 |
| 42 | <code>    } else previousFocus?.focus();</code> | 前一分支不成立时执行后面的备用分支。 |
| 43 | <code>  },</code> | 结束前面打开的代码块/对象，并保留逗号供后续成员继续声明；括号与前面的起始符号配对。 |
| 44 | <code>);</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 45 | <code>onBeforeUnmount(() =&gt; previousFocus?.focus());</code> | 把键盘输入焦点移到该输入框，便于继续录入。 |
| 46 | <code>&lt;/script&gt;</code> | 结束 Vue 组件的 JavaScript 区域。 |
| 47 | <code>&lt;template&gt;</code> | 开始 Vue 模板：声明当前组件要渲染的页面结构和数据绑定。 |
| 48 | <code>  &lt;Teleport to=&quot;body&quot;&gt;</code> | 声明`Teleport` 组件。 |
| 49 | <code>    &lt;div</code> | 声明通用布局容器。 |
| 50 | <code>      v-if=&quot;open&quot;</code> | 继续为上一行打开的元素设置属性 `v-if="open"`，它与该元素属于同一个开始标签。 |
| 51 | <code>      class=&quot;modal-backdrop&quot;</code> | 继续为上一行打开的元素设置属性 `class="modal-backdrop"`，它与该元素属于同一个开始标签。 |
| 52 | <code>      :class=&quot;{ elevated }&quot;</code> | 继续为上一行打开的元素设置属性 `:class="{ elevated }"`，它与该元素属于同一个开始标签。 |
| 53 | <code>      @click.self=&quot;!busy &amp;&amp; emit(&#x27;close&#x27;)&quot;</code> | 继续为上一行打开的元素设置属性 `@click.self="!busy && emit('close')"`，它与该元素属于同一个开始标签。 |
| 54 | <code>    &gt;</code> | 结束上一行打开的开始标签，随后显示文字/嵌套元素 ``。 |
| 55 | <code>      &lt;section</code> | 声明独立内容分区。 |
| 56 | <code>        ref=&quot;panel&quot;</code> | 更新 `ref` 为 `"panel"`。 |
| 57 | <code>        role=&quot;dialog&quot;</code> | 继续为上一行打开的元素设置属性 `role="dialog"`，它与该元素属于同一个开始标签。 |
| 58 | <code>        aria-modal=&quot;true&quot;</code> | 继续为上一行打开的元素设置属性 `aria-modal="true"`，它与该元素属于同一个开始标签。 |
| 59 | <code>        :aria-label=&quot;title&quot;</code> | 继续为上一行打开的元素设置属性 `:aria-label="title"`，它与该元素属于同一个开始标签。 |
| 60 | <code>        class=&quot;modal&quot;</code> | 继续为上一行打开的元素设置属性 `class="modal"`，它与该元素属于同一个开始标签。 |
| 61 | <code>        :class=&quot;{ wide }&quot;</code> | 继续为上一行打开的元素设置属性 `:class="{ wide }"`，它与该元素属于同一个开始标签。 |
| 62 | <code>        @keydown=&quot;keydown&quot;</code> | 继续为上一行打开的元素设置属性 `@keydown="keydown"`，它与该元素属于同一个开始标签。 |
| 63 | <code>      &gt;</code> | 结束上一行打开的开始标签，随后显示文字/嵌套元素 ``。 |
| 64 | <code>        &lt;header class=&quot;modal-header&quot;&gt;</code> | 声明顶部区域；class 连接下方/公共 CSS 样式。 |
| 65 | <code>          &lt;h2&gt;{{ title }}&lt;/h2&gt;</code> | 声明二级标题；以普通文字显示表达式 `title` 的结果。 |
| 66 | <code>          &lt;button</code> | 声明操作按钮。 |
| 67 | <code>            class=&quot;icon-button&quot;</code> | 继续为上一行打开的元素设置属性 `class="icon-button"`，它与该元素属于同一个开始标签。 |
| 68 | <code>            :disabled=&quot;busy&quot;</code> | 继续为上一行打开的元素设置属性 `:disabled="busy"`，它与该元素属于同一个开始标签。 |
| 69 | <code>            aria-label=&quot;关闭弹窗&quot;</code> | 继续为上一行打开的元素设置属性 `aria-label="关闭弹窗"`，它与该元素属于同一个开始标签。 |
| 70 | <code>            @click=&quot;emit(&#x27;close&#x27;)&quot;</code> | 继续为上一行打开的元素设置属性 `@click="emit('close')"`，它与该元素属于同一个开始标签。 |
| 71 | <code>          &gt;</code> | 结束上一行打开的开始标签，随后显示文字/嵌套元素 ``。 |
| 72 | <code>            ×</code> | 向用户显示文字 `×`，作为按钮名称、提示或页面说明。 |
| 73 | <code>          &lt;/button&gt;</code> | 关闭 `button` 元素，结束对应区域的包含关系。 |
| 74 | <code>        &lt;/header&gt;</code> | 关闭 `header` 元素，结束对应区域的包含关系。 |
| 75 | <code>        &lt;div class=&quot;modal-body&quot;&gt;&lt;slot /&gt;&lt;/div&gt;</code> | 声明通用布局容器；class 连接下方/公共 CSS 样式。 |
| 76 | <code>      &lt;/section&gt;</code> | 关闭 `section` 元素，结束对应区域的包含关系。 |
| 77 | <code>    &lt;/div&gt;</code> | 关闭 `div` 元素，结束对应区域的包含关系。 |
| 78 | <code>  &lt;/Teleport&gt;</code> | 关闭 `Teleport` 元素，结束对应区域的包含关系。 |
| 79 | <code>&lt;/template&gt;</code> | 结束 Vue 模板区域。 |
