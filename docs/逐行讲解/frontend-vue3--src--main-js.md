# frontend-vue3/src/main.js

源文件：[`frontend-vue3/src/main.js`](../../商城管理系统/frontend-vue3/src/main.js)。以下行号以当前文件为准，空行也计入。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>// 创建 Vue 应用，将根组件挂载到 index.html 的 #app 容器</code> | 注释：创建 Vue 应用，将根组件挂载到 index.html 的 #app 容器。此行用于解释代码，不会执行。 |
| 2 | <code>import { createApp } from &quot;vue&quot;;</code> | 从 `vue` 导入 `{ createApp }`。createApp：创建 Vue 应用。 |
| 3 | <code>import App from &quot;./App.vue&quot;;</code> | 从 `./App.vue` 导入 `App`。导入后可以在本文件中使用这些组件或工具。 |
| 4 | <code>import router from &quot;./router&quot;;</code> | 从 `./router` 导入 `router`。导入后可以在本文件中使用这些组件或工具。 |
| 5 | <code>import &quot;./style.css&quot;;</code> | 从 `./style.css` 导入 `"./style.css";`。导入后可以在本文件中使用这些组件或工具。 |
| 6 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 7 | <code>createApp(App).use(router).mount(&quot;#app&quot;);</code> | 创建 Vue 应用，注册路由/全局能力，再挂载到 index.html 的 #app 容器。 |
