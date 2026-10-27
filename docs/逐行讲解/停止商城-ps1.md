# 停止商城.ps1

源文件：[`停止商城.ps1`](../../商城管理系统/停止商城.ps1)。以下行号以当前文件为准，空行也计入。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>param([switch]$KeepDatabase)</code> | 声明脚本入口参数；调用脚本时可以用 -参数名 传入值，未传入时使用后面的默认值。 |
| 2 | <code>$ErrorActionPreference = &#39;Stop&#39;</code> | 把 PowerShell 可终止错误提升为 Stop，发生错误时中断当前流程，避免失败后继续启动。 |
| 3 | <code>$projectRoot = $PSScriptRoot</code> | 给脚本变量 $projectRoot 赋值 `$PSScriptRoot`；后续命令引用这个运行状态/路径。 |
| 4 | <code>$runtimeDir = Join-Path $projectRoot &#39;.runtime&#39;</code> | 通过 Join-Path 组合当前项目/工具目录与明确的子路径，保存到变量 $runtimeDir，避免手写路径分隔符。 |
| 5 | <code>foreach ($name in @(&#39;frontend&#39;, &#39;backend&#39;)) {</code> | 依次遍历 frontend/backend 名称，只读取本项目记录的进程信息进行停止。 |
| 6 | <code>    $recordFile = Join-Path $runtimeDir ($name + &#39;.process.json&#39;)</code> | 通过 Join-Path 组合当前项目/工具目录与明确的子路径，保存到变量 $recordFile，避免手写路径分隔符。 |
| 7 | <code>    if (!(Test-Path -LiteralPath $recordFile)) { continue }</code> | 判断条件 `!(Test-Path -LiteralPath $recordFile)`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 8 | <code>    $record = Get-Content -LiteralPath $recordFile -Raw &#124; ConvertFrom-Json</code> | 读取本项目 JSON 配置/进程记录并还原为 PowerShell 对象，后续按属性访问。 |
| 9 | <code>    $process = Get-Process -Id $record.pid -ErrorAction SilentlyContinue</code> | 只按先前记录的 PID 查找进程；不存在时返回空值，不扫描或结束其他程序。 |
| 10 | <code>    # 同时核对 PID 和启动时间，避免 PID 被其他程序复用时误停进程。</code> | 注释：同时核对 PID 和启动时间，避免 PID 被其他程序复用时误停进程。；不作为配置值或命令执行。 |
| 11 | <code>    if ($process -and $process.StartTime.ToUniversalTime().Ticks -eq $record.startedAt) {</code> | 判断条件 `$process -and $process.StartTime.ToUniversalTime().Ticks -eq $record.startedAt`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 12 | <code>        Stop-Process -Id $process.Id</code> | 停止前面已经核对 PID 与启动时间的本项目进程，不按通用进程名批量停止。 |
| 13 | <code>        $process.WaitForExit(5000) &#124; Out-Null</code> | 最多等待 5 秒让已停止的本项目进程退出，不删除任何数据。 |
| 14 | <code>        Write-Host &quot;已停止商城 $name。&quot;</code> | 向终端显示当前操作结果/启动地址：`Write-Host "已停止商城 $name。"`；数据库随机密码不会在这里输出。 |
| 15 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 16 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 17 | <code>if (!$KeepDatabase) {</code> | 判断条件 `!$KeepDatabase`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 18 | <code>    $recordFile = Join-Path $runtimeDir &#39;mysql.process.json&#39;</code> | 通过 Join-Path 组合当前项目/工具目录与明确的子路径，保存到变量 $recordFile，避免手写路径分隔符。 |
| 19 | <code>    $configFile = Join-Path $runtimeDir &#39;mysql.local.json&#39;</code> | 通过 Join-Path 组合当前项目/工具目录与明确的子路径，保存到变量 $configFile，避免手写路径分隔符。 |
| 20 | <code>    if ((Test-Path -LiteralPath $recordFile) -and (Test-Path -LiteralPath $configFile)) {</code> | 判断条件 `(Test-Path -LiteralPath $recordFile) -and (Test-Path -LiteralPath $configFile)`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 21 | <code>        $record = Get-Content -LiteralPath $recordFile -Raw &#124; ConvertFrom-Json</code> | 读取本项目 JSON 配置/进程记录并还原为 PowerShell 对象，后续按属性访问。 |
| 22 | <code>        $process = Get-Process -Id $record.pid -ErrorAction SilentlyContinue</code> | 只按先前记录的 PID 查找进程；不存在时返回空值，不扫描或结束其他程序。 |
| 23 | <code>        if ($process -and $process.StartTime.ToUniversalTime().Ticks -eq $record.startedAt) {</code> | 判断条件 `$process -and $process.StartTime.ToUniversalTime().Ticks -eq $record.startedAt`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 24 | <code>            $db = Get-Content -LiteralPath $configFile -Raw &#124; ConvertFrom-Json</code> | 读取本项目 JSON 配置/进程记录并还原为 PowerShell 对象，后续按属性访问。 |
| 25 | <code>            # 使用选项文件传凭据，密码不会出现在命令行参数中。</code> | 注释：使用选项文件传凭据，密码不会出现在命令行参数中。；不作为配置值或命令执行。 |
| 26 | <code>            $rootClientContents = @&quot;</code> | 开始构造管理员客户端配置的多行字符串，保存到 rootClientContents；变量会替换为本项目的数据库配置。 |
| 27 | <code>[client]</code> | MySQL 选项文件的客户端配置段，下面的 host/port/user/password 只供客户端读取。 |
| 28 | <code>host=127.0.0.1</code> | 设置 MySQL 客户端选项 `host`；变量从本项目配置读取，端口指向商城专用实例。 |
| 29 | <code>port=$($db.port)</code> | 设置 MySQL 客户端选项 `port`；变量从本项目配置读取，端口指向商城专用实例。 |
| 30 | <code>user=root</code> | 设置 MySQL 客户端选项 `user`；变量从本项目配置读取，端口指向商城专用实例。 |
| 31 | <code>password=$($db.rootPassword)</code> | 设置 MySQL 客户端选项 `password`；变量从本项目配置读取，端口指向商城专用实例。 |
| 32 | <code>protocol=TCP</code> | 设置 MySQL 客户端选项 `protocol`；变量从本项目配置读取，端口指向商城专用实例。 |
| 33 | <code>&quot;@</code> | 结束多行字符串，管理员客户端配置保存在 rootClientContents 中。 |
| 34 | <code>            [System.IO.File]::WriteAllText((Join-Path $runtimeDir &#39;mysql-root.cnf&#39;), $rootClientContents, [System.Text.UTF8Encoding]::new($false))</code> | 使用不带 BOM 的 UTF-8 写入商城管理员客户端配置；确保 Windows PowerShell 5.1 生成的文件也能被 MySQL 识别。 |
| 35 | <code>            Push-Location $projectRoot</code> | 临时切换工作目录，确保 Maven/JDBC/MySQL 相对路径在预期项目目录中解析。 |
| 36 | <code>            try {</code> | 开始尝试执行可能失败的操作；抛出的异常交给后续 catch 统一处理。 |
| 37 | <code>                &amp; (Join-Path $db.mySqlHome &#39;bin/mysqladmin.exe&#39;) --defaults-extra-file=.runtime/mysql-root.cnf shutdown</code> | 通过本项目 root 选项文件请求 MySQL 正常 shutdown，使数据库先刷盘再退出。 |
| 38 | <code>                if ($LASTEXITCODE -ne 0) { throw &#39;商城数据库未能正常停止，请查看数据库日志。&#39; }</code> | 判断条件 `$LASTEXITCODE -ne 0`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 39 | <code>            } finally { Pop-Location }</code> | 在 finally 中恢复原工作目录，无论前面操作成功或失败都执行。 |
| 40 | <code>            Write-Host &#39;已正常停止商城独立 MySQL，数据文件保留。&#39;</code> | 向终端显示当前操作结果/启动地址：`Write-Host '已正常停止商城独立 MySQL，数据文件保留。'`；数据库随机密码不会在这里输出。 |
| 41 | <code>        }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 42 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 43 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 44 | <code>Write-Host &#39;停止操作结束；没有删除任何业务数据。&#39;</code> | 向终端显示当前操作结果/启动地址：`Write-Host '停止操作结束；没有删除任何业务数据。'`；数据库随机密码不会在这里输出。 |
| 45 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
