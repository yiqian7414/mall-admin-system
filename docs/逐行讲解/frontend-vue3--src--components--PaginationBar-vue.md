# frontend-vue3/src/components/PaginationBar.vue

源文件：[`frontend-vue3/src/components/PaginationBar.vue`](../../商城管理系统/frontend-vue3/src/components/PaginationBar.vue)。以下行号以当前文件为准，空行也计入。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>&lt;script setup&gt;</code> | 开始 Vue 组件的 JavaScript 区域；setup 写法中顶层声明可直接供本组件模板使用。 |
| 2 | <code>import { computed } from &quot;vue&quot;;</code> | 从 `vue` 导入 `{ computed }`。computed：根据已有响应式数据计算并缓存派生值。 |
| 3 | <code>const props = defineProps({</code> | 声明 不能重新赋值绑定的常量 `props`，用右侧 `defineProps({` 初始化。 |
| 4 | <code>  page: Number,</code> | 定义对象成员 `page` 的值为 `Number`。`page`：从 1 开始的当前页码 |
| 5 | <code>  size: Number,</code> | 定义对象成员 `size` 的值为 `Number`。`size`：每页条数 |
| 6 | <code>  total: Number,</code> | 定义对象成员 `total` 的值为 `Number`。`total`：筛选结果总条数 |
| 7 | <code>  loading: Boolean,</code> | 定义对象成员 `loading` 的值为 `Boolean`。`loading`：查询是否进行中 |
| 8 | <code>});</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 9 | <code>const emit = defineEmits([&quot;change&quot;]);</code> | 声明 不能重新赋值绑定的常量 `emit`，用右侧 `defineEmits(["change"])` 初始化。 |
| 10 | <code>const pages = computed(() =&gt; Math.max(1, Math.ceil(props.total / props.size)));</code> | 定义计算属性 `pages`；根据回调使用的响应式数据自动重新计算，并缓存结果。 |
| 11 | <code>&lt;/script&gt;</code> | 结束 Vue 组件的 JavaScript 区域。 |
| 12 | <code>&lt;template&gt;</code> | 开始 Vue 模板：声明当前组件要渲染的页面结构和数据绑定。 |
| 13 | <code>  &lt;div class=&quot;pagination&quot;&gt;</code> | 声明通用布局容器；class 连接下方/公共 CSS 样式。 |
| 14 | <code>    &lt;span&gt;共 {{ total }} 条，每页 {{ size }} 条&lt;/span&gt;</code> | 声明行内文字容器；以普通文字显示表达式 `total` 的结果；以普通文字显示表达式 `size` 的结果。 |
| 15 | <code>    &lt;div&gt;</code> | 声明通用布局容器。 |
| 16 | <code>      &lt;button</code> | 声明操作按钮。 |
| 17 | <code>        class=&quot;button secondary small&quot;</code> | 继续为上一行打开的元素设置属性 `class="button secondary small"`，它与该元素属于同一个开始标签。 |
| 18 | <code>        :disabled=&quot;page &lt;= 1 &#124;&#124; loading&quot;</code> | 继续为上一行打开的元素设置属性 `:disabled="page <= 1 &#124;&#124; loading"`，它与该元素属于同一个开始标签。 |
| 19 | <code>        @click=&quot;emit(&#x27;change&#x27;, page - 1)&quot;</code> | 继续为上一行打开的元素设置属性 `@click="emit('change', page - 1)"`，它与该元素属于同一个开始标签。 |
| 20 | <code>      &gt;</code> | 结束上一行打开的开始标签，随后显示文字/嵌套元素 ``。 |
| 21 | <code>        上一页&lt;/button</code> | 显示标签前的文字，并结束对应元素：`上一页</button`。 |
| 22 | <code>      &gt;&lt;span&gt;{{ page }} / {{ pages }}&lt;/span</code> | 插值 `><span>{{ page }} / {{ pages }}</span`：计算双花括号中的表达式并作为普通文字显示，避免把商品名等输入当作 HTML 执行。 |
| 23 | <code>      &gt;&lt;button</code> | 结束上一个标签后，继续声明后面的元素：`><button`。这种换行保持相邻行内元素之间不额外插入空格。 |
| 24 | <code>        class=&quot;button secondary small&quot;</code> | 继续为上一行打开的元素设置属性 `class="button secondary small"`，它与该元素属于同一个开始标签。 |
| 25 | <code>        :disabled=&quot;page &gt;= pages &#124;&#124; loading&quot;</code> | 继续为上一行打开的元素设置属性 `:disabled="page >= pages &#124;&#124; loading"`，它与该元素属于同一个开始标签。 |
| 26 | <code>        @click=&quot;emit(&#x27;change&#x27;, page + 1)&quot;</code> | 继续为上一行打开的元素设置属性 `@click="emit('change', page + 1)"`，它与该元素属于同一个开始标签。 |
| 27 | <code>      &gt;</code> | 结束上一行打开的开始标签，随后显示文字/嵌套元素 ``。 |
| 28 | <code>        下一页</code> | 向用户显示文字 `下一页`，作为按钮名称、提示或页面说明。 |
| 29 | <code>      &lt;/button&gt;</code> | 关闭 `button` 元素，结束对应区域的包含关系。 |
| 30 | <code>    &lt;/div&gt;</code> | 关闭 `div` 元素，结束对应区域的包含关系。 |
| 31 | <code>  &lt;/div&gt;</code> | 关闭 `div` 元素，结束对应区域的包含关系。 |
| 32 | <code>&lt;/template&gt;</code> | 结束 Vue 模板区域。 |
