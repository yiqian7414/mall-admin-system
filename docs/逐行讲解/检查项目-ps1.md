# 检查项目.ps1

源文件：[`检查项目.ps1`](../../商城管理系统/检查项目.ps1)。以下行号以当前文件为准，空行也计入。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>$ErrorActionPreference = &#x27;Stop&#x27;</code> | 把 PowerShell 可终止错误提升为 Stop，发生错误时中断当前流程，避免失败后继续启动。 |
| 2 | <code>$projectRoot = $PSScriptRoot</code> | 给脚本变量 $projectRoot 赋值 `$PSScriptRoot`；后续命令引用这个运行状态/路径。 |
| 3 | <code>. (Join-Path $projectRoot &#x27;scripts/环境.ps1&#x27;)</code> | 用点加载方式执行公共环境脚本，把其中函数导入当前脚本作用域。 |
| 4 | <code>$tools = Get-MallToolchain</code> | 给脚本变量 $tools 赋值 `Get-MallToolchain`；后续命令引用这个运行状态/路径。 |
| 5 | <code>$env:JAVA_HOME = $tools.JavaHome</code> | 给当前脚本启动的 Maven/Java 指定 JDK；只影响当前脚本及其子进程。 |
| 6 | <code># 测试类明确使用独立的 H2 内存库，不读写正在运行的 MySQL 商城数据。</code> | 注释：测试类明确使用独立的 H2 内存库，不读写正在运行的 MySQL 商城数据。；不作为配置值或命令执行。 |
| 7 | <code>Push-Location (Join-Path $projectRoot &#x27;backend&#x27;)</code> | 临时切换工作目录，确保 Maven/JDBC/MySQL 相对路径在预期项目目录中解析。 |
| 8 | <code>try {</code> | 开始尝试执行可能失败的操作；抛出的异常交给后续 catch 统一处理。 |
| 9 | <code>    &amp; $tools.Maven -B &#x27;-Dmaven.repo.local=.m2/repository&#x27; test</code> | 实际调用 Maven 执行后端集成测试，使用项目内 .m2 缓存；测试类指定独立 H2 内存库，不读写当前商城 MySQL。 |
| 10 | <code>    if ($LASTEXITCODE -ne 0) { throw &#x27;后端集成测试失败。&#x27; }</code> | 判断条件 `$LASTEXITCODE -ne 0`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 11 | <code>} finally { Pop-Location }</code> | 在 finally 中恢复原工作目录，无论前面操作成功或失败都执行。 |
| 12 | <code>Push-Location (Join-Path $projectRoot &#x27;frontend-vue3&#x27;)</code> | 临时切换工作目录，确保 Maven/JDBC/MySQL 相对路径在预期项目目录中解析。 |
| 13 | <code>try {</code> | 开始尝试执行可能失败的操作；抛出的异常交给后续 catch 统一处理。 |
| 14 | <code>    &amp; $tools.Npm run build</code> | 调用前端生产构建命令，把手写模块编译到 dist；构建失败时返回非零退出码，下面立即报错。 |
| 15 | <code>    if ($LASTEXITCODE -ne 0) { throw &#x27;前端构建失败。&#x27; }</code> | 判断条件 `$LASTEXITCODE -ne 0`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 16 | <code>} finally { Pop-Location }</code> | 在 finally 中恢复原工作目录，无论前面操作成功或失败都执行。 |
| 17 | <code>Write-Host &#x27;后端集成测试与前端构建均通过。&#x27;</code> | 向终端显示当前操作结果/启动地址：`Write-Host '后端集成测试与前端构建均通过。'`；数据库随机密码不会在这里输出。 |
