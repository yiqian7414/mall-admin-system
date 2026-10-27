# frontend-vue3/.gitignore

源文件：[`frontend-vue3/.gitignore`](../../商城管理系统/frontend-vue3/.gitignore)。以下行号以当前文件为准，空行也计入。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code># Logs</code> | 忽略规则注释：Logs。 |
| 2 | <code>logs</code> | Git 忽略模式 `logs`：避免把匹配的依赖、构建结果、日志、运行配置或数据库文件提交到仓库；不删除本地文件。 |
| 3 | <code>*.log</code> | 块注释中的说明：.log；不参与运行。 |
| 4 | <code>npm-debug.log*</code> | Git 忽略模式 `npm-debug.log*`：避免把匹配的依赖、构建结果、日志、运行配置或数据库文件提交到仓库；不删除本地文件。 |
| 5 | <code>yarn-debug.log*</code> | Git 忽略模式 `yarn-debug.log*`：避免把匹配的依赖、构建结果、日志、运行配置或数据库文件提交到仓库；不删除本地文件。 |
| 6 | <code>yarn-error.log*</code> | Git 忽略模式 `yarn-error.log*`：避免把匹配的依赖、构建结果、日志、运行配置或数据库文件提交到仓库；不删除本地文件。 |
| 7 | <code>pnpm-debug.log*</code> | Git 忽略模式 `pnpm-debug.log*`：避免把匹配的依赖、构建结果、日志、运行配置或数据库文件提交到仓库；不删除本地文件。 |
| 8 | <code>lerna-debug.log*</code> | Git 忽略模式 `lerna-debug.log*`：避免把匹配的依赖、构建结果、日志、运行配置或数据库文件提交到仓库；不删除本地文件。 |
| 9 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 10 | <code>node_modules</code> | Git 忽略模式 `node_modules`：避免把匹配的依赖、构建结果、日志、运行配置或数据库文件提交到仓库；不删除本地文件。 |
| 11 | <code>.DS_Store</code> | Git 忽略模式 `.DS_Store`：避免把匹配的依赖、构建结果、日志、运行配置或数据库文件提交到仓库；不删除本地文件。 |
| 12 | <code>dist</code> | Git 忽略模式 `dist`：避免把匹配的依赖、构建结果、日志、运行配置或数据库文件提交到仓库；不删除本地文件。 |
| 13 | <code>dist-ssr</code> | Git 忽略模式 `dist-ssr`：避免把匹配的依赖、构建结果、日志、运行配置或数据库文件提交到仓库；不删除本地文件。 |
| 14 | <code>coverage</code> | Git 忽略模式 `coverage`：避免把匹配的依赖、构建结果、日志、运行配置或数据库文件提交到仓库；不删除本地文件。 |
| 15 | <code>*.local</code> | 块注释中的说明：.local；不参与运行。 |
| 16 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 17 | <code># Editor directories and files</code> | 忽略规则注释：Editor directories and files。 |
| 18 | <code>.vscode/*</code> | Git 忽略模式 `.vscode/*`：避免把匹配的依赖、构建结果、日志、运行配置或数据库文件提交到仓库；不删除本地文件。 |
| 19 | <code>!.vscode/extensions.json</code> | Git 忽略模式 `!.vscode/extensions.json`：避免把匹配的依赖、构建结果、日志、运行配置或数据库文件提交到仓库；不删除本地文件。 |
| 20 | <code>.idea</code> | Git 忽略模式 `.idea`：避免把匹配的依赖、构建结果、日志、运行配置或数据库文件提交到仓库；不删除本地文件。 |
| 21 | <code>*.suo</code> | 块注释中的说明：.suo；不参与运行。 |
| 22 | <code>*.ntvs*</code> | 块注释中的说明：.ntvs；不参与运行。 |
| 23 | <code>*.njsproj</code> | 块注释中的说明：.njsproj；不参与运行。 |
| 24 | <code>*.sln</code> | 块注释中的说明：.sln；不参与运行。 |
| 25 | <code>*.sw?</code> | 块注释中的说明：.sw?；不参与运行。 |
| 26 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 27 | <code>*.tsbuildinfo</code> | 块注释中的说明：.tsbuildinfo；不参与运行。 |
| 28 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 29 | <code>.eslintcache</code> | Git 忽略模式 `.eslintcache`：避免把匹配的依赖、构建结果、日志、运行配置或数据库文件提交到仓库；不删除本地文件。 |
| 30 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 31 | <code># Cypress</code> | 忽略规则注释：Cypress。 |
| 32 | <code>/cypress/videos/</code> | Git 忽略模式 `/cypress/videos/`：避免把匹配的依赖、构建结果、日志、运行配置或数据库文件提交到仓库；不删除本地文件。 |
| 33 | <code>/cypress/screenshots/</code> | Git 忽略模式 `/cypress/screenshots/`：避免把匹配的依赖、构建结果、日志、运行配置或数据库文件提交到仓库；不删除本地文件。 |
| 34 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 35 | <code># Vitest</code> | 忽略规则注释：Vitest。 |
| 36 | <code>__screenshots__/</code> | Git 忽略模式 `__screenshots__/`：避免把匹配的依赖、构建结果、日志、运行配置或数据库文件提交到仓库；不删除本地文件。 |
| 37 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 38 | <code># Vite</code> | 忽略规则注释：Vite。 |
| 39 | <code>*.timestamp-*-*.mjs</code> | 块注释中的说明：.timestamp-*-*.mjs；不参与运行。 |
