# frontend-vue3/vite.config.js

源文件：[`frontend-vue3/vite.config.js`](../../商城管理系统/frontend-vue3/vite.config.js)。空行计入行号。

本文件按当前源码重新校对行号；未改变代码的已有讲解予以保留。

库存与图片的业务示例、整体流程见 [新功能说明](../库存管理与商品图片.md)。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>import { defineConfig } from &quot;vite&quot;;</code> | 从 `vite` 导入 `{ defineConfig }`。defineConfig：以 Vite 能识别的结构声明配置。 |
| 2 | <code>import vue from &quot;@vitejs/plugin-vue&quot;;</code> | 从 `@vitejs/plugin-vue` 导入 `vue`。vue：让 Vite 编译 .vue 单文件组件。 |
| 3 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 4 | <code>// Vue 插件让 Vite 能处理 .vue 单文件组件</code> | 注释：Vue 插件让 Vite 能处理 .vue 单文件组件。此行用于解释代码，不会执行。 |
| 5 | <code>export default defineConfig({</code> | 把后面的对象/配置作为此模块的默认导出，供入口或 Vite 导入使用。 |
| 6 | <code>  plugins: [vue()],</code> | 启用 Vue 插件，让 Vite 能编译 .vue 文件中的脚本、模板和样式。 |
| 7 | <code>  server: {</code> | 定义对象成员 `server` 的值为 `{`。 |
| 8 | <code>    proxy: {</code> | 定义对象成员 `proxy` 的值为 `{`。 |
| 9 | <code>      &quot;/api&quot;: process.env.MALL_API_TARGET &#124;&#124; &quot;http://127.0.0.1:8081&quot;,</code> | 默认仍代理到正式后端 8081；独立验收可用进程环境变量指定 8181。 |
| 10 | <code>    },</code> | 结束前面打开的代码块/对象，并保留逗号供后续成员继续声明；括号与前面的起始符号配对。 |
| 11 | <code>  },</code> | 结束前面打开的代码块/对象，并保留逗号供后续成员继续声明；括号与前面的起始符号配对。 |
| 12 | <code>});</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
