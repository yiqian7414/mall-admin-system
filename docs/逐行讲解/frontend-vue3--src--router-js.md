# frontend-vue3/src/router.js

源文件：[`frontend-vue3/src/router.js`](../../商城管理系统/frontend-vue3/src/router.js)。空行计入行号。

本文件按当前源码重新校对行号；未改变代码的已有讲解予以保留。

库存与图片的业务示例、整体流程见 [新功能说明](../库存管理与商品图片.md)。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>import { createRouter, createWebHistory } from &quot;vue-router&quot;;</code> | 从 `vue-router` 导入 `{ createRouter, createWebHistory }`。createRouter：创建前端路由器；createWebHistory：使用浏览器 History API 管理地址。 |
| 2 | <code>import { session, restoreSession } from &quot;./session&quot;;</code> | 导入共享会话和恢复函数，路由守卫根据后端确认的用户判断是否进入页面。 |
| 3 | <code>import AdminLayout from &quot;./components/AdminLayout.vue&quot;;</code> | 导入后台布局，业务子页面会显示在它内部的 RouterView 中。 |
| 4 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 5 | <code>const router = createRouter({</code> | 声明 不能重新赋值绑定的常量 `router`，用右侧 `createRouter({` 初始化。 |
| 6 | <code>  history: createWebHistory(),</code> | 使用浏览器 History API 维护前端地址；点击路由链接无需整页重新加载。 |
| 7 | <code>  routes: [</code> | 开始路由配置数组，每个对象声明一个 URL 对应的页面组件与元信息。 |
| 8 | <code>    {</code> | 继续声明数组元素或对象字面量：`{`；字段/元素按当前结构保存。 |
| 9 | <code>      path: &quot;/login&quot;,</code> | 定义对象成员 `path` 的值为 `"/login"`。 |
| 10 | <code>      component: () =&gt; import(&quot;./views/LoginView.vue&quot;),</code> | 懒加载登录页面组件，只有进入该路由时才加载对应模块。 |
| 11 | <code>      meta: { title: &quot;登录&quot;, public: true },</code> | 自定义 meta：登录页标题为登录，public=true 表示守卫允许未登录访问。 |
| 12 | <code>    },</code> | 结束前面打开的代码块/对象，并保留逗号供后续成员继续声明；括号与前面的起始符号配对。 |
| 13 | <code>    {</code> | 继续声明数组元素或对象字面量：`{`；字段/元素按当前结构保存。 |
| 14 | <code>      path: &quot;/&quot;,</code> | 定义对象成员 `path` 的值为 `"/"`。 |
| 15 | <code>      component: AdminLayout,</code> | 定义对象成员 `component` 的值为 `AdminLayout`。 |
| 16 | <code>      children: [</code> | 开始后台子路由列表，下面的业务页面显示在 AdminLayout 内部。 |
| 17 | <code>        {</code> | 继续声明数组元素或对象字面量：`{`；字段/元素按当前结构保存。 |
| 18 | <code>          path: &quot;&quot;,</code> | 空子路径对应父路径 /，所以工作台是后台默认页面。 |
| 19 | <code>          component: () =&gt; import(&quot;./views/DashboardView.vue&quot;),</code> | 懒加载概览页面，进入工作台时才加载组件。 |
| 20 | <code>          meta: {</code> | 定义对象成员 `meta` 的值为 `{`。 |
| 21 | <code>            title: &quot;工作台&quot;,</code> | 给浏览器页签与后台页头提供工作台标题。 |
| 22 | <code>            subtitle: &quot;了解商城经营情况，及时处理待办事项&quot;,</code> | 给后台页头提供当前页面说明文字。 |
| 23 | <code>          },</code> | 结束前面打开的代码块/对象，并保留逗号供后续成员继续声明；括号与前面的起始符号配对。 |
| 24 | <code>        },</code> | 结束前面打开的代码块/对象，并保留逗号供后续成员继续声明；括号与前面的起始符号配对。 |
| 25 | <code>        {</code> | 继续声明数组元素或对象字面量：`{`；字段/元素按当前结构保存。 |
| 26 | <code>          path: &quot;products&quot;,</code> | 相对父路径 / 拼成 /products，进入商品管理。 |
| 27 | <code>          component: () =&gt; import(&quot;./views/ProductsView.vue&quot;),</code> | 懒加载商品管理页面。 |
| 28 | <code>          meta: {</code> | 定义对象成员 `meta` 的值为 `{`。 |
| 29 | <code>            title: &quot;商品管理&quot;,</code> | 定义对象成员 `title` 的值为 `"商品管理"`。 |
| 30 | <code>            subtitle: &quot;管理商品资料、图片与上下架状态，库存通过出入库变更&quot;,</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 31 | <code>          },</code> | 结束前面打开的代码块/对象，并保留逗号供后续成员继续声明；括号与前面的起始符号配对。 |
| 32 | <code>        },</code> | 结束前面打开的代码块/对象，并保留逗号供后续成员继续声明；括号与前面的起始符号配对。 |
| 33 | <code>        {</code> | 继续声明数组元素或对象字面量：`{`；字段/元素按当前结构保存。 |
| 34 | <code>          path: &quot;categories&quot;,</code> | 相对父路径拼成 /categories，进入分类管理。 |
| 35 | <code>          component: () =&gt; import(&quot;./views/CategoriesView.vue&quot;),</code> | 懒加载分类管理页面。 |
| 36 | <code>          meta: {</code> | 定义对象成员 `meta` 的值为 `{`。 |
| 37 | <code>            title: &quot;商品分类&quot;,</code> | 定义对象成员 `title` 的值为 `"商品分类"`。 |
| 38 | <code>            subtitle: &quot;建立清晰的商品分类，方便日常管理&quot;,</code> | 定义对象成员 `subtitle` 的值为 `"建立清晰的商品分类，方便日常管理"`。 |
| 39 | <code>          },</code> | 结束前面打开的代码块/对象，并保留逗号供后续成员继续声明；括号与前面的起始符号配对。 |
| 40 | <code>        },</code> | 结束前面打开的代码块/对象，并保留逗号供后续成员继续声明；括号与前面的起始符号配对。 |
| 41 | <code>        {</code> | 继续声明数组元素或对象字面量：`{`；字段/元素按当前结构保存。 |
| 42 | <code>          path: &quot;inventory&quot;,</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 43 | <code>          component: () =&gt; import(&quot;./views/InventoryView.vue&quot;),</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 44 | <code>          meta: {</code> | 定义对象成员 `meta` 的值为 `{`。 |
| 45 | <code>            title: &quot;库存管理&quot;,</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 46 | <code>            subtitle: &quot;办理商品出入库，追踪每一笔库存变化&quot;,</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 47 | <code>          },</code> | 结束前面打开的代码块/对象，并保留逗号供后续成员继续声明；括号与前面的起始符号配对。 |
| 48 | <code>        },</code> | 结束前面打开的代码块/对象，并保留逗号供后续成员继续声明；括号与前面的起始符号配对。 |
| 49 | <code>        {</code> | 继续声明数组元素或对象字面量：`{`；字段/元素按当前结构保存。 |
| 50 | <code>          path: &quot;orders&quot;,</code> | 相对父路径拼成 /orders，进入订单管理。 |
| 51 | <code>          component: () =&gt; import(&quot;./views/OrdersView.vue&quot;),</code> | 懒加载订单管理页面。 |
| 52 | <code>          meta: {</code> | 定义对象成员 `meta` 的值为 `{`。 |
| 53 | <code>            title: &quot;订单管理&quot;,</code> | 定义对象成员 `title` 的值为 `"订单管理"`。 |
| 54 | <code>            subtitle: &quot;创建订单并跟踪支付、发货与完成情况&quot;,</code> | 定义对象成员 `subtitle` 的值为 `"创建订单并跟踪支付、发货与完成情况"`。 |
| 55 | <code>          },</code> | 结束前面打开的代码块/对象，并保留逗号供后续成员继续声明；括号与前面的起始符号配对。 |
| 56 | <code>        },</code> | 结束前面打开的代码块/对象，并保留逗号供后续成员继续声明；括号与前面的起始符号配对。 |
| 57 | <code>        {</code> | 继续声明数组元素或对象字面量：`{`；字段/元素按当前结构保存。 |
| 58 | <code>          path: &quot;users&quot;,</code> | 相对父路径拼成 /users，进入账号管理。 |
| 59 | <code>          component: () =&gt; import(&quot;./views/UsersView.vue&quot;),</code> | 懒加载账号管理页面。 |
| 60 | <code>          meta: {</code> | 定义对象成员 `meta` 的值为 `{`。 |
| 61 | <code>            title: &quot;账号管理&quot;,</code> | 定义对象成员 `title` 的值为 `"账号管理"`。 |
| 62 | <code>            subtitle: &quot;为管理员与员工分配后台访问权限&quot;,</code> | 定义对象成员 `subtitle` 的值为 `"为管理员与员工分配后台访问权限"`。 |
| 63 | <code>            admin: true,</code> | admin=true 是前端自定义权限标记，导航守卫据此只允许 ADMIN 进入此页；后端仍独立鉴权。 |
| 64 | <code>          },</code> | 结束前面打开的代码块/对象，并保留逗号供后续成员继续声明；括号与前面的起始符号配对。 |
| 65 | <code>        },</code> | 结束前面打开的代码块/对象，并保留逗号供后续成员继续声明；括号与前面的起始符号配对。 |
| 66 | <code>      ],</code> | 结束前面打开的代码块/对象，并保留逗号供后续成员继续声明；括号与前面的起始符号配对。 |
| 67 | <code>    },</code> | 结束前面打开的代码块/对象，并保留逗号供后续成员继续声明；括号与前面的起始符号配对。 |
| 68 | <code>    { path: &quot;/:pathMatch(.*)*&quot;, redirect: &quot;/&quot; },</code> | 所有未匹配的前端地址跳回工作台，避免空白页面；这不是后端 API 的 404 处理。 |
| 69 | <code>  ],</code> | 结束前面打开的代码块/对象，并保留逗号供后续成员继续声明；括号与前面的起始符号配对。 |
| 70 | <code>});</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 71 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 72 | <code>router.beforeEach(async (to) =&gt; {</code> | 注册异步导航前守卫，to 是即将进入的目标路由。 |
| 73 | <code>  try {</code> | 开始尝试执行可能失败的操作；抛出的异常交给后续 catch 统一处理。 |
| 74 | <code>    await restoreSession();</code> | 先等待 Cookie 对应的身份恢复完成，再做登录判断。 |
| 75 | <code>  } catch {</code> | 恢复身份的连接/服务错误在此被容忍，使登录页仍能打开；下方仍继续检查是否有用户。 |
| 76 | <code>    /* 登录页允许在后台恢复后重试登录 */</code> | 块注释中的说明：登录页允许在后台恢复后重试登录；不参与运行。 |
| 77 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 78 | <code>  if (!to.meta.public &amp;&amp; !session.user)</code> | 非公开路由且没有用户时要求登录。 |
| 79 | <code>    return { path: &quot;/login&quot;, query: { redirect: to.fullPath } };</code> | 返回新的路由位置触发重定向，并保存完整目标地址，便于登录成功后回跳。 |
| 80 | <code>  if (to.path === &quot;/login&quot; &amp;&amp; session.user) return &quot;/&quot;;</code> | 已经登录时访问 /login，直接回工作台，避免重复登录界面。 |
| 81 | <code>  if (to.meta.admin &amp;&amp; session.user?.role !== &quot;ADMIN&quot;) return &quot;/&quot;;</code> | 进入管理员页面但角色不是 ADMIN 时回工作台；这只是界面导航检查，接口权限由后端执行。 |
| 82 | <code>  document.title = `${to.meta.title &#124;&#124; &quot;后台&quot;} · 我的商城管理系统`;</code> | 生成当前浏览器页签标题，meta.title 缺失时使用后台作为默认名称。 |
| 83 | <code>});</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 84 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 85 | <code>export default router;</code> | 默认导出路由器，供 main.js 通过 app.use(router) 注册。 |
