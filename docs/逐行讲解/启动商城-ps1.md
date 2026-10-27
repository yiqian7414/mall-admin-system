# 启动商城.ps1

源文件：[`启动商城.ps1`](../../商城管理系统/启动商城.ps1)。以下行号以当前文件为准，空行也计入。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>param(</code> | 声明脚本入口参数；调用脚本时可以用 -参数名 传入值，未传入时使用后面的默认值。 |
| 2 | <code>    [ValidateSet(&#x27;mysql&#x27;, &#x27;h2&#x27;)][string]$Database = &#x27;mysql&#x27;,</code> | 把数据库参数限制为 mysql/h2，默认使用 mysql；其他值会在脚本开始前被拒绝。 |
| 3 | <code>    [switch]$SkipBuild,</code> | 声明开关参数；传入该开关时为 true，否则为 false。 |
| 4 | <code>    [int]$FrontendPort = 5174</code> | 声明整数参数并设置默认值，用来选择本项目的前端端口。 |
| 5 | <code>)</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 6 | <code>$ErrorActionPreference = &#x27;Stop&#x27;</code> | 把 PowerShell 可终止错误提升为 Stop，发生错误时中断当前流程，避免失败后继续启动。 |
| 7 | <code>$projectRoot = $PSScriptRoot</code> | 给脚本变量 $projectRoot 赋值 `$PSScriptRoot`；后续命令引用这个运行状态/路径。 |
| 8 | <code>. (Join-Path $projectRoot &#x27;scripts/环境.ps1&#x27;)</code> | 用点加载方式执行公共环境脚本，把其中函数导入当前脚本作用域。 |
| 9 | <code>$tools = Get-MallToolchain</code> | 给脚本变量 $tools 赋值 `Get-MallToolchain`；后续命令引用这个运行状态/路径。 |
| 10 | <code>$env:JAVA_HOME = $tools.JavaHome</code> | 给当前脚本启动的 Maven/Java 指定 JDK；只影响当前脚本及其子进程。 |
| 11 | <code>$runtimeDir = Join-Path $projectRoot &#x27;.runtime&#x27;</code> | 通过 Join-Path 组合当前项目/工具目录与明确的子路径，保存到变量 $runtimeDir，避免手写路径分隔符。 |
| 12 | <code>New-Item -ItemType Directory -Path $runtimeDir -Force &#124; Out-Null</code> | 创建本项目所需目录，-Force 允许目录已存在；Out-Null 隐藏创建结果，不清空已有内容。 |
| 13 | <code>$backendDir = Join-Path $projectRoot &#x27;backend&#x27;</code> | 通过 Join-Path 组合当前项目/工具目录与明确的子路径，保存到变量 $backendDir，避免手写路径分隔符。 |
| 14 | <code>$frontendDir = Join-Path $projectRoot &#x27;frontend-vue3&#x27;</code> | 通过 Join-Path 组合当前项目/工具目录与明确的子路径，保存到变量 $frontendDir，避免手写路径分隔符。 |
| 15 | <code>$jarFile = Join-Path $backendDir &#x27;target/mall-backend-1.0.0.jar&#x27;</code> | 通过 Join-Path 组合当前项目/工具目录与明确的子路径，保存到变量 $jarFile，避免手写路径分隔符。 |
| 16 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 17 | <code>if (Test-MallPort 8081) { throw &#x27;8081 端口已被占用。若商城已运行，请直接访问页面；重新启动前先运行 停止商城.ps1。&#x27; }</code> | 判断条件 `Test-MallPort 8081`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 18 | <code>if (Test-MallPort $FrontendPort) { throw &quot;前端端口 $FrontendPort 已被占用，请停止已有商城或用 -FrontendPort 指定其他端口。&quot; }</code> | 判断条件 `Test-MallPort $FrontendPort`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 19 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 20 | <code>if ($Database -eq &#x27;mysql&#x27;) {</code> | 判断条件 `$Database -eq 'mysql'`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 21 | <code>    &amp; (Join-Path $projectRoot &#x27;scripts/准备MySQL.ps1&#x27;)</code> | 调用指定可执行程序/子脚本并传入参数：`& (Join-Path $projectRoot 'scripts/准备MySQL.ps1')`；& 是 PowerShell 调用运算符。 |
| 22 | <code>    $db = Get-Content -LiteralPath (Join-Path $runtimeDir &#x27;mysql.local.json&#x27;) -Raw &#124; ConvertFrom-Json</code> | 读取本项目 JSON 配置/进程记录并还原为 PowerShell 对象，后续按属性访问。 |
| 23 | <code>    $env:SPRING_PROFILES_ACTIVE = &#x27;mysql&#x27;</code> | 选择 Spring 数据库 profile；只影响当前脚本及其子进程。 |
| 24 | <code>    $env:MALL_DB_URL = &quot;jdbc:mysql://127.0.0.1:$($db.port)/mall_admin?useUnicode=true&amp;characterEncoding=UTF-8&amp;serverTimezone=Asia/Shanghai&amp;useSSL=false&amp;allowPublicKeyRetrieval=true&quot;</code> | 提供商城专用 MySQL JDBC 连接串；只影响当前脚本及其子进程。 |
| 25 | <code>    $env:MALL_DB_USER = $db.username</code> | 提供商城专用数据库用户名；只影响当前脚本及其子进程。 |
| 26 | <code>    $env:MALL_DB_PASSWORD = $db.password</code> | 提供本机配置中的随机数据库密码，避免硬编码到源码；只影响当前脚本及其子进程。 |
| 27 | <code>} else {</code> | 前面 if 条件不成立时进入这个备用分支。 |
| 28 | <code>    $env:SPRING_PROFILES_ACTIVE = &#x27;h2&#x27;</code> | 选择 Spring 数据库 profile；只影响当前脚本及其子进程。 |
| 29 | <code>    Remove-Item Env:MALL_DB_URL -ErrorAction SilentlyContinue</code> | 清除当前脚本进程里 MySQL 连接环境变量，使 H2 备用配置不受上次 MySQL 参数影响；不删除磁盘文件。 |
| 30 | <code>    Remove-Item Env:MALL_DB_USER -ErrorAction SilentlyContinue</code> | 清除当前脚本进程里 MySQL 连接环境变量，使 H2 备用配置不受上次 MySQL 参数影响；不删除磁盘文件。 |
| 31 | <code>    Remove-Item Env:MALL_DB_PASSWORD -ErrorAction SilentlyContinue</code> | 清除当前脚本进程里 MySQL 连接环境变量，使 H2 备用配置不受上次 MySQL 参数影响；不删除磁盘文件。 |
| 32 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 33 | <code>if (!$SkipBuild -or !(Test-Path -LiteralPath $jarFile)) {</code> | 判断条件 `!$SkipBuild -or !(Test-Path -LiteralPath $jarFile)`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 34 | <code>    Push-Location $backendDir</code> | 临时切换工作目录，确保 Maven/JDBC/MySQL 相对路径在预期项目目录中解析。 |
| 35 | <code>    try {</code> | 开始尝试执行可能失败的操作；抛出的异常交给后续 catch 统一处理。 |
| 36 | <code>        &amp; $tools.Maven -B &#x27;-Dmaven.repo.local=.m2/repository&#x27; -DskipTests package</code> | 调用检测到的 Maven，使用 backend/.m2/repository 本地缓存打包后端；-DskipTests 此启动步骤跳过测试，测试由验收阶段单独执行。 |
| 37 | <code>        if ($LASTEXITCODE -ne 0) { throw &#x27;后端构建失败，请根据上方错误修复。&#x27; }</code> | 判断条件 `$LASTEXITCODE -ne 0`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 38 | <code>    } finally { Pop-Location }</code> | 在 finally 中恢复原工作目录，无论前面操作成功或失败都执行。 |
| 39 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 40 | <code>if (!(Test-Path -LiteralPath (Join-Path $frontendDir &#x27;node_modules&#x27;))) {</code> | 判断条件 `!(Test-Path -LiteralPath (Join-Path $frontendDir 'node_modules'))`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 41 | <code>    Push-Location $frontendDir</code> | 临时切换工作目录，确保 Maven/JDBC/MySQL 相对路径在预期项目目录中解析。 |
| 42 | <code>    try {</code> | 开始尝试执行可能失败的操作；抛出的异常交给后续 catch 统一处理。 |
| 43 | <code>        &amp; $tools.Npm ci --no-audit --no-fund</code> | 按 package-lock.json 精确安装前端依赖，关闭非必要审计/资助输出；仅缺少 node_modules 时执行。 |
| 44 | <code>        if ($LASTEXITCODE -ne 0) { throw &#x27;前端依赖安装失败。&#x27; }</code> | 判断条件 `$LASTEXITCODE -ne 0`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 45 | <code>    } finally { Pop-Location }</code> | 在 finally 中恢复原工作目录，无论前面操作成功或失败都执行。 |
| 46 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 47 | <code>$backendProcess = Start-Process -FilePath $tools.Java -ArgumentList @(&#x27;-jar&#x27;, (&#x27;&quot;&#x27; + $jarFile + &#x27;&quot;&#x27;)) -WorkingDirectory $backendDir -WindowStyle Hidden -RedirectStandardOutput (Join-Path $runtimeDir &#x27;backend.log&#x27;) -RedirectStandardError (Join-Path $runtimeDir &#x27;backend-error.log&#x27;) -PassThru</code> | 后台启动本项目进程，指定工作目录和 Hidden 窗口；日志重定向到 .runtime，PassThru 返回进程对象。 |
| 48 | <code>Save-MallProcess $backendProcess &#x27;backend&#x27; $runtimeDir</code> | 记录本项目子进程的 PID 与启动时间到 .runtime，供停止脚本准确识别。 |
| 49 | <code>$backendReady = $false</code> | 给脚本变量 $backendReady 赋值 `$false`；后续命令引用这个运行状态/路径。 |
| 50 | <code>for ($attempt = 0; $attempt -lt 80; $attempt++) {</code> | 按 `$attempt = 0; $attempt -lt 80; $attempt++` 逐项/逐次循环，执行花括号中的处理。 |
| 51 | <code>    Start-Sleep -Milliseconds 500</code> | 短暂等待 500 毫秒，为后台进程启动/监听端口预留时间后继续检测。 |
| 52 | <code>    try {</code> | 开始尝试执行可能失败的操作；抛出的异常交给后续 catch 统一处理。 |
| 53 | <code>        $health = Invoke-RestMethod -Uri &#x27;http://127.0.0.1:8081/api/health&#x27; -TimeoutSec 2</code> | 请求本项目 /api/health 并读取 JSON，确认 8081 上运行的是商城后端而非仅端口被占用。 |
| 54 | <code>        if ($health.application -eq &#x27;mall-admin&#x27;) { $backendReady = $true; break }</code> | 判断条件 `$health.application -eq 'mall-admin'`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 55 | <code>    } catch { }</code> | 健康检测期间暂时忽略连接失败，交给外层有限重试循环继续等待后端；超时或进程退出会明确报错。 |
| 56 | <code>    if ($backendProcess.HasExited) { throw &#x27;后端启动失败，请查看 .runtime/backend.log 和 backend-error.log。&#x27; }</code> | 判断条件 `$backendProcess.HasExited`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 57 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 58 | <code>if (!$backendReady) { throw &#x27;后端启动超时，请查看 .runtime/backend.log。&#x27; }</code> | 判断条件 `!$backendReady`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 59 | <code>$viteFile = Join-Path $frontendDir &#x27;node_modules/vite/bin/vite.js&#x27;</code> | 通过 Join-Path 组合当前项目/工具目录与明确的子路径，保存到变量 $viteFile，避免手写路径分隔符。 |
| 60 | <code>$frontendProcess = Start-Process -FilePath $tools.Node -ArgumentList @((&#x27;&quot;&#x27; + $viteFile + &#x27;&quot;&#x27;), &#x27;--host&#x27;, &#x27;127.0.0.1&#x27;, &#x27;--port&#x27;, $FrontendPort, &#x27;--strictPort&#x27;) -WorkingDirectory $frontendDir -WindowStyle Hidden -RedirectStandardOutput (Join-Path $runtimeDir &#x27;frontend.log&#x27;) -RedirectStandardError (Join-Path $runtimeDir &#x27;frontend-error.log&#x27;) -PassThru</code> | 后台启动本项目进程，指定工作目录和 Hidden 窗口；日志重定向到 .runtime，PassThru 返回进程对象。 |
| 61 | <code>Save-MallProcess $frontendProcess &#x27;frontend&#x27; $runtimeDir</code> | 记录本项目子进程的 PID 与启动时间到 .runtime，供停止脚本准确识别。 |
| 62 | <code>$frontendReady = $false</code> | 给脚本变量 $frontendReady 赋值 `$false`；后续命令引用这个运行状态/路径。 |
| 63 | <code>for ($attempt = 0; $attempt -lt 40; $attempt++) {</code> | 按 `$attempt = 0; $attempt -lt 40; $attempt++` 逐项/逐次循环，执行花括号中的处理。 |
| 64 | <code>    Start-Sleep -Milliseconds 250</code> | 短暂等待 250 毫秒，为后台进程启动/监听端口预留时间后继续检测。 |
| 65 | <code>    if (Test-MallPort $FrontendPort) { $frontendReady = $true; break }</code> | 判断条件 `Test-MallPort $FrontendPort`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 66 | <code>    if ($frontendProcess.HasExited) { throw &#x27;前端启动失败，请查看 .runtime/frontend-error.log。&#x27; }</code> | 判断条件 `$frontendProcess.HasExited`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 67 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 68 | <code>if (!$frontendReady) { throw &#x27;前端启动超时，请查看 .runtime/frontend.log。&#x27; }</code> | 判断条件 `!$frontendReady`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 69 | <code>Write-Host &quot;商城已启动：http://127.0.0.1:$FrontendPort/&quot;</code> | 向终端显示当前操作结果/启动地址：`Write-Host "商城已启动：http://127.0.0.1:$FrontendPort/"`；数据库随机密码不会在这里输出。 |
| 70 | <code>Write-Host &#x27;学习账号：admin / Admin@123456；员工：staff / Staff@123456。&#x27;</code> | 向终端显示当前操作结果/启动地址：`Write-Host '学习账号：admin / Admin@123456；员工：staff / Staff@123456。'`；数据库随机密码不会在这里输出。 |
| 71 | <code>Write-Host &quot;当前数据库：$Database；停止服务请运行 .\停止商城.ps1。&quot;</code> | 向终端显示当前操作结果/启动地址：`Write-Host "当前数据库：$Database；停止服务请运行 .\停止商城.ps1。"`；数据库随机密码不会在这里输出。 |
| 72 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
