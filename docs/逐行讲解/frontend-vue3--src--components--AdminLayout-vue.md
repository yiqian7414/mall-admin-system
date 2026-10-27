# frontend-vue3/src/components/AdminLayout.vue

源文件：[`frontend-vue3/src/components/AdminLayout.vue`](../../商城管理系统/frontend-vue3/src/components/AdminLayout.vue)。空行计入行号。

本文件按当前源码重新校对行号；未改变代码的已有讲解予以保留。

库存与图片的业务示例、整体流程见 [新功能说明](../库存管理与商品图片.md)。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>&lt;script setup&gt;</code> | 开始 Vue 组件的 JavaScript 区域；setup 写法中顶层声明可直接供本组件模板使用。 |
| 2 | <code>import { ref, computed } from &quot;vue&quot;;</code> | 从 `vue` 导入 `{ ref, computed }`。ref：创建带 .value 的响应式容器；computed：根据已有响应式数据计算并缓存派生值。 |
| 3 | <code>import { useRoute, useRouter } from &quot;vue-router&quot;;</code> | 从 `vue-router` 导入 `{ useRoute, useRouter }`。导入后可以在本文件中使用这些组件或工具。 |
| 4 | <code>import { session, logout } from &quot;../session&quot;;</code> | 从 `../session` 导入 `{ session, logout }`。导入后可以在本文件中使用这些组件或工具。 |
| 5 | <code>import { confirmAction, notify } from &quot;../feedback&quot;;</code> | 从 `../feedback` 导入 `{ confirmAction, notify }`。导入后可以在本文件中使用这些组件或工具。 |
| 6 | <code>const route = useRoute(),</code> | 声明 不能重新赋值绑定的常量 `route`，用右侧 `useRoute(),` 初始化。 |
| 7 | <code>  router = useRouter();</code> | 更新 `router` 为 `useRouter()`。 |
| 8 | <code>const sidebarOpen = ref(false),</code> | 创建响应式变量 `sidebarOpen`，初始值由 ref(...) 指定；脚本中用 .value 读写，模板会自动解包。 |
| 9 | <code>  signingOut = ref(false);</code> | 继续同一条 const 声明，创建响应式变量 `signingOut`，初始内容为 `ref(false);`；这不是修改已有变量。 |
| 10 | <code>const links = computed(() =&gt; [</code> | 定义计算属性 `links`；根据回调使用的响应式数据自动重新计算，并缓存结果。 |
| 11 | <code>  { path: &quot;/&quot;, label: &quot;工作台&quot;, mark: &quot;⌂&quot; },</code> | 继续声明数组元素或对象字面量：`{ path: "/", label: "工作台", mark: "⌂" },`；字段/元素按当前结构保存。 |
| 12 | <code>  { path: &quot;/products&quot;, label: &quot;商品管理&quot;, mark: &quot;▦&quot; },</code> | 继续声明数组元素或对象字面量：`{ path: "/products", label: "商品管理", mark: "▦" },`；字段/元素按当前结构保存。 |
| 13 | <code>  { path: &quot;/categories&quot;, label: &quot;商品分类&quot;, mark: &quot;▤&quot; },</code> | 继续声明数组元素或对象字面量：`{ path: "/categories", label: "商品分类", mark: "▤" },`；字段/元素按当前结构保存。 |
| 14 | <code>  { path: &quot;/inventory&quot;, label: &quot;库存管理&quot;, mark: &quot;▧&quot; },</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 15 | <code>  { path: &quot;/orders&quot;, label: &quot;订单管理&quot;, mark: &quot;▣&quot; },</code> | 继续声明数组元素或对象字面量：`{ path: "/orders", label: "订单管理", mark: "▣" },`；字段/元素按当前结构保存。 |
| 16 | <code>  ...(session.user?.role === &quot;ADMIN&quot;</code> | 展开 `(session.user?.role === "ADMIN"` 的对象属性/数组元素，合并到当前结构中；不额外嵌套一层。 |
| 17 | <code>    ? [{ path: &quot;/users&quot;, label: &quot;账号管理&quot;, mark: &quot;♙&quot; }]</code> | 三元表达式的条件成立分支，使用 `[{ path: "/users", label: "账号管理", mark: "♙" }]` 作为结果；对应另一分支在后续 : 后。 |
| 18 | <code>    : []),</code> | 继续为上一行打开的元素设置属性 `: []),`，它与该元素属于同一个开始标签。 |
| 19 | <code>]);</code> | 结束前面打开的对象、数组、回调或函数调用；括号顺序与起始结构配对。 |
| 20 | <code>async function signOut() {</code> | 定义异步函数 `signOut`，接收参数 `无`；被事件或其他函数调用时执行内部逻辑。 |
| 21 | <code>  if (!(await confirmAction(&quot;退出登录&quot;, &quot;确认退出当前账号？&quot;))) return;</code> | 判断条件 `!(await confirmAction("退出登录", "确认退出当前账号？"))`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 22 | <code>  signingOut.value = true;</code> | 更新 `signingOut.value` 为 `true`。；.value 写入会通知依赖该 ref 的 Vue 模板更新。 |
| 23 | <code>  try {</code> | 开始尝试执行可能失败的操作；抛出的异常交给后续 catch 统一处理。 |
| 24 | <code>    await logout();</code> | 暂停当前异步函数，等待 `logout()` 完成；不会阻塞浏览器主线程。 |
| 25 | <code>    router.push(&quot;/login&quot;);</code> | 把后面的对象/值追加到数组末尾；响应式数组变化会触发 Vue 更新，原生数组则需要手动重新渲染。 |
| 26 | <code>  } catch (error) {</code> | 捕获前面 try 中抛出的错误 error，进入对应的失败处理。 |
| 27 | <code>    notify(error.message, &quot;error&quot;);</code> | 向通知列表添加一条有类型与文字的消息；本次调用参数为 `error.message, "error"`。 |
| 28 | <code>  } finally {</code> | 无论前面的操作成功还是失败，都执行此处的清理/状态恢复代码。 |
| 29 | <code>    signingOut.value = false;</code> | 更新 `signingOut.value` 为 `false`。；.value 写入会通知依赖该 ref 的 Vue 模板更新。 |
| 30 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 31 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 32 | <code>&lt;/script&gt;</code> | 结束 Vue 组件的 JavaScript 区域。 |
| 33 | <code>&lt;template&gt;</code> | 开始 Vue 模板：声明当前组件要渲染的页面结构和数据绑定。 |
| 34 | <code>  &lt;div class=&quot;admin-shell&quot;&gt;</code> | 声明通用布局容器；class 连接下方/公共 CSS 样式。 |
| 35 | <code>    &lt;button</code> | 声明操作按钮。 |
| 36 | <code>      v-if=&quot;sidebarOpen&quot;</code> | 继续为上一行打开的元素设置属性 `v-if="sidebarOpen"`，它与该元素属于同一个开始标签。 |
| 37 | <code>      class=&quot;sidebar-shade&quot;</code> | 继续为上一行打开的元素设置属性 `class="sidebar-shade"`，它与该元素属于同一个开始标签。 |
| 38 | <code>      aria-label=&quot;收起导航&quot;</code> | 继续为上一行打开的元素设置属性 `aria-label="收起导航"`，它与该元素属于同一个开始标签。 |
| 39 | <code>      @click=&quot;sidebarOpen = false&quot;</code> | 继续为上一行打开的元素设置属性 `@click="sidebarOpen = false"`，它与该元素属于同一个开始标签。 |
| 40 | <code>    &gt;&lt;/button&gt;</code> | 结束上一个标签后，继续声明后面的元素：`></button>`。这种换行保持相邻行内元素之间不额外插入空格。 |
| 41 | <code>    &lt;aside class=&quot;sidebar&quot; :class=&quot;{ open: sidebarOpen }&quot;&gt;</code> | 声明侧边区域；动态绑定 `class` 为 `{ open: sidebarOpen }`；class 连接下方/公共 CSS 样式。 |
| 42 | <code>      &lt;RouterLink class=&quot;brand&quot; to=&quot;/&quot; @click=&quot;sidebarOpen = false&quot;</code> | 声明`RouterLink` 组件；绑定 `click` 事件执行 `sidebarOpen = false`；class 连接下方/公共 CSS 样式。 |
| 43 | <code>        &gt;&lt;span class=&quot;brand-symbol&quot;&gt;M&lt;/span</code> | 结束上一个标签后，继续声明后面的元素：`><span class="brand-symbol">M</span`。这种换行保持相邻行内元素之间不额外插入空格。 |
| 44 | <code>        &gt;&lt;span&gt;我的商城&lt;small&gt;管理工作空间&lt;/small&gt;&lt;/span&gt;&lt;/RouterLink</code> | 结束上一个标签后，继续声明后面的元素：`><span>我的商城<small>管理工作空间</small></span></RouterLink`。这种换行保持相邻行内元素之间不额外插入空格。 |
| 45 | <code>      &gt;</code> | 结束上一行打开的开始标签，随后显示文字/嵌套元素 ``。 |
| 46 | <code>      &lt;p class=&quot;nav-caption&quot;&gt;商城管理&lt;/p&gt;</code> | 声明文字段落；class 连接下方/公共 CSS 样式。 |
| 47 | <code>      &lt;nav aria-label=&quot;主导航&quot;&gt;</code> | 声明导航区域。 |
| 48 | <code>        &lt;RouterLink</code> | 声明`RouterLink` 组件。 |
| 49 | <code>          v-for=&quot;link in links&quot;</code> | 继续为上一行打开的元素设置属性 `v-for="link in links"`，它与该元素属于同一个开始标签。 |
| 50 | <code>          :key=&quot;link.path&quot;</code> | 继续为上一行打开的元素设置属性 `:key="link.path"`，它与该元素属于同一个开始标签。 |
| 51 | <code>          :to=&quot;link.path&quot;</code> | 继续为上一行打开的元素设置属性 `:to="link.path"`，它与该元素属于同一个开始标签。 |
| 52 | <code>          :class=&quot;{ active: route.path === link.path }&quot;</code> | 继续为上一行打开的元素设置属性 `:class="{ active: route.path === link.path }"`，它与该元素属于同一个开始标签。 |
| 53 | <code>          @click=&quot;sidebarOpen = false&quot;</code> | 继续为上一行打开的元素设置属性 `@click="sidebarOpen = false"`，它与该元素属于同一个开始标签。 |
| 54 | <code>          &gt;&lt;span class=&quot;nav-symbol&quot;&gt;{{ link.mark }}&lt;/span</code> | 插值 `><span class="nav-symbol">{{ link.mark }}</span`：计算双花括号中的表达式并作为普通文字显示，避免把商品名等输入当作 HTML 执行。 |
| 55 | <code>          &gt;{{ link.label }}&lt;/RouterLink</code> | 插值 `>{{ link.label }}</RouterLink`：计算双花括号中的表达式并作为普通文字显示，避免把商品名等输入当作 HTML 执行。 |
| 56 | <code>        &gt;</code> | 结束上一行打开的开始标签，随后显示文字/嵌套元素 ``。 |
| 57 | <code>      &lt;/nav&gt;</code> | 关闭 `nav` 元素，结束对应区域的包含关系。 |
| 58 | <code>      &lt;div class=&quot;sidebar-footer&quot;&gt;</code> | 声明通用布局容器；class 连接下方/公共 CSS 样式。 |
| 59 | <code>        &lt;span class=&quot;online-dot&quot;&gt;&lt;/span&gt; Vue 3 · Spring Boot&lt;small</code> | 声明行内文字容器；class 连接下方/公共 CSS 样式。 |
| 60 | <code>          &gt;独立商城管理系统&lt;/small</code> | 结束上一行打开的开始标签，随后显示文字/嵌套元素 `独立商城管理系统</small`。 |
| 61 | <code>        &gt;</code> | 结束上一行打开的开始标签，随后显示文字/嵌套元素 ``。 |
| 62 | <code>      &lt;/div&gt;</code> | 关闭 `div` 元素，结束对应区域的包含关系。 |
| 63 | <code>    &lt;/aside&gt;</code> | 关闭 `aside` 元素，结束对应区域的包含关系。 |
| 64 | <code>    &lt;div class=&quot;workspace&quot;&gt;</code> | 声明通用布局容器；class 连接下方/公共 CSS 样式。 |
| 65 | <code>      &lt;header class=&quot;topbar&quot;&gt;</code> | 声明顶部区域；class 连接下方/公共 CSS 样式。 |
| 66 | <code>        &lt;div class=&quot;breadcrumb&quot;&gt;</code> | 声明通用布局容器；class 连接下方/公共 CSS 样式。 |
| 67 | <code>          &lt;button</code> | 声明操作按钮。 |
| 68 | <code>            class=&quot;icon-button mobile-menu&quot;</code> | 继续为上一行打开的元素设置属性 `class="icon-button mobile-menu"`，它与该元素属于同一个开始标签。 |
| 69 | <code>            aria-label=&quot;展开导航&quot;</code> | 继续为上一行打开的元素设置属性 `aria-label="展开导航"`，它与该元素属于同一个开始标签。 |
| 70 | <code>            @click=&quot;sidebarOpen = true&quot;</code> | 继续为上一行打开的元素设置属性 `@click="sidebarOpen = true"`，它与该元素属于同一个开始标签。 |
| 71 | <code>          &gt;</code> | 结束上一行打开的开始标签，随后显示文字/嵌套元素 ``。 |
| 72 | <code>            ☰&lt;/button</code> | 显示标签前的文字，并结束对应元素：`☰</button`。 |
| 73 | <code>          &gt;&lt;span&gt;商城后台&lt;/span&gt;&lt;span class=&quot;crumb-divider&quot;&gt;/&lt;/span</code> | 结束上一个标签后，继续声明后面的元素：`><span>商城后台</span><span class="crumb-divider">/</span`。这种换行保持相邻行内元素之间不额外插入空格。 |
| 74 | <code>          &gt;&lt;strong&gt;{{ route.meta.title }}&lt;/strong&gt;</code> | 插值 `><strong>{{ route.meta.title }}</strong>`：计算双花括号中的表达式并作为普通文字显示，避免把商品名等输入当作 HTML 执行。 |
| 75 | <code>        &lt;/div&gt;</code> | 关闭 `div` 元素，结束对应区域的包含关系。 |
| 76 | <code>        &lt;div class=&quot;account&quot;&gt;</code> | 声明通用布局容器；class 连接下方/公共 CSS 样式。 |
| 77 | <code>          &lt;span class=&quot;avatar&quot;&gt;{{</code> | 声明行内文字容器；class 连接下方/公共 CSS 样式。 |
| 78 | <code>            session.user?.displayName?.slice(0, 1) &#124;&#124; &quot;用&quot;</code> | 继续前面打开的插值表达式：`session.user?.displayName?.slice(0, 1) &#124;&#124; "用"`，计算结果作为文字显示。 |
| 79 | <code>          }}&lt;/span&gt;</code> | 结束前面跨行的 Vue 插值表达式，并关闭/继续当前标签；表达式结果按普通文字显示。 |
| 80 | <code>          &lt;div&gt;</code> | 声明通用布局容器。 |
| 81 | <code>            &lt;strong&gt;{{ session.user?.displayName }}&lt;/strong</code> | 声明强调文字；以普通文字显示表达式 `session.user?.displayName` 的结果。 |
| 82 | <code>            &gt;&lt;small&gt;{{</code> | 插值 `><small>{{`：计算双花括号中的表达式并作为普通文字显示，避免把商品名等输入当作 HTML 执行。 |
| 83 | <code>              session.user?.role === &quot;ADMIN&quot; ? &quot;管理员&quot; : &quot;员工&quot;</code> | 根据当前 User.role 显示角色文字，?. 允许退出过程中 user 暂时为空而不报错。 |
| 84 | <code>            }}&lt;/small&gt;</code> | 结束前面跨行的 Vue 插值表达式，并关闭/继续当前标签；表达式结果按普通文字显示。 |
| 85 | <code>          &lt;/div&gt;</code> | 关闭 `div` 元素，结束对应区域的包含关系。 |
| 86 | <code>          &lt;button class=&quot;logout-button&quot; :disabled=&quot;signingOut&quot; @click=&quot;signOut&quot;&gt;</code> | 声明操作按钮；绑定 `click` 事件执行 `signOut`；动态绑定 `disabled` 为 `signingOut`；class 连接下方/公共 CSS 样式。 |
| 87 | <code>            {{ signingOut ? &quot;退出中…&quot; : &quot;退出&quot; }}</code> | 插值 `{{ signingOut ? "退出中…" : "退出" }}`：计算双花括号中的表达式并作为普通文字显示，避免把商品名等输入当作 HTML 执行。 |
| 88 | <code>          &lt;/button&gt;</code> | 关闭 `button` 元素，结束对应区域的包含关系。 |
| 89 | <code>        &lt;/div&gt;</code> | 关闭 `div` 元素，结束对应区域的包含关系。 |
| 90 | <code>      &lt;/header&gt;</code> | 关闭 `header` 元素，结束对应区域的包含关系。 |
| 91 | <code>      &lt;main class=&quot;page-content&quot;&gt;</code> | 声明主要内容区域；class 连接下方/公共 CSS 样式。 |
| 92 | <code>        &lt;div class=&quot;page-heading&quot;&gt;</code> | 声明通用布局容器；class 连接下方/公共 CSS 样式。 |
| 93 | <code>          &lt;div&gt;</code> | 声明通用布局容器。 |
| 94 | <code>            &lt;h1&gt;{{ route.meta.title }}&lt;/h1&gt;</code> | 声明一级标题；以普通文字显示表达式 `route.meta.title` 的结果。 |
| 95 | <code>            &lt;p&gt;{{ route.meta.subtitle }}&lt;/p&gt;</code> | 声明文字段落；以普通文字显示表达式 `route.meta.subtitle` 的结果。 |
| 96 | <code>          &lt;/div&gt;</code> | 关闭 `div` 元素，结束对应区域的包含关系。 |
| 97 | <code>          &lt;span class=&quot;environment-tag&quot;&gt;商城管理&lt;/span&gt;</code> | 声明行内文字容器；class 连接下方/公共 CSS 样式。 |
| 98 | <code>        &lt;/div&gt;</code> | 关闭 `div` 元素，结束对应区域的包含关系。 |
| 99 | <code>        &lt;RouterView /&gt;</code> | 声明`RouterView` 组件。 |
| 100 | <code>      &lt;/main&gt;</code> | 关闭 `main` 元素，结束对应区域的包含关系。 |
| 101 | <code>      &lt;footer class=&quot;workspace-footer&quot;&gt;我的商城管理系统 · 学习与实践&lt;/footer&gt;</code> | 声明底部区域；class 连接下方/公共 CSS 样式。 |
| 102 | <code>    &lt;/div&gt;</code> | 关闭 `div` 元素，结束对应区域的包含关系。 |
| 103 | <code>  &lt;/div&gt;</code> | 关闭 `div` 元素，结束对应区域的包含关系。 |
| 104 | <code>&lt;/template&gt;</code> | 结束 Vue 模板区域。 |
