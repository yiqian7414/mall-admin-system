# scripts/准备MySQL.ps1

源文件：[`scripts/准备MySQL.ps1`](../../商城管理系统/scripts/准备MySQL.ps1)。以下行号以当前文件为准，空行也计入。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>param([string]$MySqlHome = &#39;C:\MySQL\MySQL Server 8.0&#39;)</code> | 声明脚本入口参数；调用脚本时可以用 -参数名 传入值，未传入时使用后面的默认值。 |
| 2 | <code>$ErrorActionPreference = &#39;Stop&#39;</code> | 把 PowerShell 可终止错误提升为 Stop，发生错误时中断当前流程，避免失败后继续启动。 |
| 3 | <code>$projectRoot = Split-Path -Parent $PSScriptRoot</code> | 给脚本变量 $projectRoot 赋值 `Split-Path -Parent $PSScriptRoot`；后续命令引用这个运行状态/路径。 |
| 4 | <code>$runtimeDir = Join-Path $projectRoot &#39;.runtime&#39;</code> | 通过 Join-Path 组合当前项目/工具目录与明确的子路径，保存到变量 $runtimeDir，避免手写路径分隔符。 |
| 5 | <code>$dataDir = Join-Path $projectRoot &#39;data/mysql&#39;</code> | 通过 Join-Path 组合当前项目/工具目录与明确的子路径，保存到变量 $dataDir，避免手写路径分隔符。 |
| 6 | <code>$configFile = Join-Path $runtimeDir &#39;mysql.local.json&#39;</code> | 通过 Join-Path 组合当前项目/工具目录与明确的子路径，保存到变量 $configFile，避免手写路径分隔符。 |
| 7 | <code>$serverExe = Join-Path $MySqlHome &#39;bin/mysqld.exe&#39;</code> | 通过 Join-Path 组合当前项目/工具目录与明确的子路径，保存到变量 $serverExe，避免手写路径分隔符。 |
| 8 | <code>$clientExe = Join-Path $MySqlHome &#39;bin/mysql.exe&#39;</code> | 通过 Join-Path 组合当前项目/工具目录与明确的子路径，保存到变量 $clientExe，避免手写路径分隔符。 |
| 9 | <code>if (!(Test-Path -LiteralPath $serverExe)) { throw &#39;没有找到 MySQL，请用 -MySqlHome 指定安装目录，或启动时选择 -Database h2。&#39; }</code> | 判断条件 `!(Test-Path -LiteralPath $serverExe)`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 10 | <code>New-Item -ItemType Directory -Path $runtimeDir -Force &#124; Out-Null</code> | 创建本项目所需目录，-Force 允许目录已存在；Out-Null 隐藏创建结果，不清空已有内容。 |
| 11 | <code>if (!(Test-Path -LiteralPath $configFile)) {</code> | 判断条件 `!(Test-Path -LiteralPath $configFile)`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 12 | <code>    if (Test-Path -LiteralPath (Join-Path $dataDir &#39;mysql&#39;)) { throw &#39;发现已有商城数据库但缺少连接配置，请恢复 .runtime/mysql.local.json，避免覆盖数据。&#39; }</code> | 判断条件 `Test-Path -LiteralPath (Join-Path $dataDir 'mysql')`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 13 | <code>    $localConfig = @{</code> | 给脚本变量 $localConfig 赋值 `@{`；后续命令引用这个运行状态/路径。 |
| 14 | <code>        port = 3307</code> | 在 PowerShell 哈希表中定义配置项 `port`；随机密码调用 Guid 生成，不硬编码。 |
| 15 | <code>        username = &#39;mall_app&#39;</code> | 在 PowerShell 哈希表中定义配置项 `username`；随机密码调用 Guid 生成，不硬编码。 |
| 16 | <code>        password = [Guid]::NewGuid().ToString(&#39;N&#39;)</code> | 在 PowerShell 哈希表中定义配置项 `password`；随机密码调用 Guid 生成，不硬编码。 |
| 17 | <code>        rootPassword = [Guid]::NewGuid().ToString(&#39;N&#39;)</code> | 在 PowerShell 哈希表中定义配置项 `rootPassword`；随机密码调用 Guid 生成，不硬编码。 |
| 18 | <code>        mySqlHome = $MySqlHome</code> | 在 PowerShell 哈希表中定义配置项 `mySqlHome`；随机密码调用 Guid 生成，不硬编码。 |
| 19 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 20 | <code>    $localConfig &#124; ConvertTo-Json &#124; Set-Content -LiteralPath $configFile -Encoding utf8</code> | 将随机端口/账号/密码配置序列化为 JSON，保存到被忽略的 .runtime/mysql.local.json；不向终端输出密码。 |
| 21 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 22 | <code>$localConfig = Get-Content -LiteralPath $configFile -Raw &#124; ConvertFrom-Json</code> | 读取本项目 JSON 配置/进程记录并还原为 PowerShell 对象，后续按属性访问。 |
| 23 | <code>$alreadyInitialized = Test-Path -LiteralPath (Join-Path $dataDir &#39;mysql&#39;)</code> | 通过 Join-Path 组合当前项目/工具目录与明确的子路径，保存到变量 $alreadyInitialized，避免手写路径分隔符。 |
| 24 | <code>if (!$alreadyInitialized) {</code> | 判断条件 `!$alreadyInitialized`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 25 | <code>    New-Item -ItemType Directory -Path $dataDir -Force &#124; Out-Null</code> | 创建本项目所需目录，-Force 允许目录已存在；Out-Null 隐藏创建结果，不清空已有内容。 |
| 26 | <code>    Push-Location $projectRoot</code> | 临时切换工作目录，确保 Maven/JDBC/MySQL 相对路径在预期项目目录中解析。 |
| 27 | <code>    try { &amp; $serverExe --no-defaults --initialize-insecure &quot;--basedir=$MySqlHome&quot; --datadir=./data/mysql --log-error=mysql-init.log }</code> | 仅为前面确认尚未初始化的商城独立数据目录生成MySQL系统表；--no-defaults不加载其他实例配置，首次无密码阶段随后立即设置随机root密码。 |
| 28 | <code>    finally { Pop-Location }</code> | 在 finally 中恢复原工作目录，无论前面操作成功或失败都执行。 |
| 29 | <code>    if ($LASTEXITCODE -ne 0) { throw &#39;商城独立 MySQL 初始化失败，请查看 data/mysql/mysql-init.log。&#39; }</code> | 判断条件 `$LASTEXITCODE -ne 0`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 30 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 31 | <code>$pidFile = Join-Path $runtimeDir &#39;mysql.pid&#39;</code> | 通过 Join-Path 组合当前项目/工具目录与明确的子路径，保存到变量 $pidFile，避免手写路径分隔符。 |
| 32 | <code>$socket = [System.Net.Sockets.TcpClient]::new()</code> | 创建 TCP 测试客户端，用于短连接检查本机端口，不作为数据库业务连接。 |
| 33 | <code>try { $connect = $socket.ConnectAsync(&#39;127.0.0.1&#39;, [int]$localConfig.port); $connect.Wait(500) &#124; Out-Null; $listening = $socket.Connected } catch { $listening = $false } finally { $socket.Dispose() }</code> | 短时连接127.0.0.1的商城端口并读取是否监听；连接失败设false，finally始终释放socket。这里只检测端口，下一段还核对本项目进程归属。 |
| 34 | <code>if ($listening) {</code> | 端口已被占用时先验证是否属于之前由商城脚本启动的数据库；未经验证不向该端口发送初始化SQL。 |
| 35 | <code>    $processRecord = Join-Path $runtimeDir &#39;mysql.process.json&#39;</code> | 通过 Join-Path 组合当前项目/工具目录与明确的子路径，保存到变量 $processRecord，避免手写路径分隔符。 |
| 36 | <code>    if (!(Test-Path -LiteralPath $processRecord)) { throw &#39;3307 端口已有未登记的数据库，停止准备操作以保留现有数据库。&#39; }</code> | 判断条件 `!(Test-Path -LiteralPath $processRecord)`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 37 | <code>    $record = Get-Content -LiteralPath $processRecord -Raw &#124; ConvertFrom-Json</code> | 读取本项目 JSON 配置/进程记录并还原为 PowerShell 对象，后续按属性访问。 |
| 38 | <code>    $ownedProcess = Get-Process -Id $record.pid -ErrorAction SilentlyContinue</code> | 只按先前记录的 PID 查找进程；不存在时返回空值，不扫描或结束其他程序。 |
| 39 | <code>    if (!$ownedProcess -or $ownedProcess.StartTime.ToUniversalTime().Ticks -ne $record.startedAt) {</code> | 要求记录中的PID确有进程，并且UTC启动时间一致；PID被其他程序复用或进程不存在时拒绝操作已有数据库。 |
| 40 | <code>        throw &#39;3307 端口没有对应的商城进程记录，请检查端口占用；不会修改该数据库。&#39;</code> | 抛出错误 `'3307 端口没有对应的商城进程记录，请检查端口占用；不会修改该数据库。'`，终止当前路径，并由异常处理器转换为失败响应或提示。 |
| 41 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 42 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 43 | <code>if (!$listening) {</code> | 判断条件 `!$listening`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 44 | <code>    # MySQL 8 在 Windows 下解析含中文的绝对参数路径不稳定，数据目录使用相对路径。</code> | 注释：MySQL 8 在 Windows 下解析含中文的绝对参数路径不稳定，数据目录使用相对路径。；不作为配置值或命令执行。 |
| 45 | <code>    $serverArgs = @(&#39;--no-defaults&#39;, (&#39;--basedir=&quot;&#39; + $MySqlHome + &#39;&quot;&#39;), &#39;--datadir=./data/mysql&#39;, &#39;--bind-address=127.0.0.1&#39;, &quot;--port=$($localConfig.port)&quot;, &#39;--mysqlx=0&#39;, &#39;--pid-file=mall-mysql.pid&#39;, &#39;--log-error=mall-mysql.log&#39;)</code> | 准备独立MySQL进程参数：只绑定127.0.0.1、使用商城3307端口与./data/mysql、禁用mysqlx、独立PID/日志；相对路径避免Windows中文参数解析问题。 |
| 46 | <code>    $process = Start-Process -FilePath $serverExe -ArgumentList $serverArgs -WorkingDirectory $projectRoot -WindowStyle Hidden -PassThru</code> | 后台启动本项目进程，指定工作目录和 Hidden 窗口；日志重定向到 .runtime，PassThru 返回进程对象。 |
| 47 | <code>    @{ pid = $process.Id; startedAt = $process.StartTime.ToUniversalTime().Ticks; executable = $serverExe } &#124; ConvertTo-Json &#124; Set-Content -LiteralPath (Join-Path $runtimeDir &#39;mysql.process.json&#39;) -Encoding utf8</code> | 构造本项目进程记录，保存 PID、UTC 启动时间及必要路径；停止时同时核对，防止 PID 被复用。 |
| 48 | <code>    $listening = $false</code> | 给脚本变量 $listening 赋值 `$false`；后续命令引用这个运行状态/路径。 |
| 49 | <code>    for ($attempt = 0; $attempt -lt 60; $attempt++) {</code> | 按 `$attempt = 0; $attempt -lt 60; $attempt++` 逐项/逐次循环，执行花括号中的处理。 |
| 50 | <code>        Start-Sleep -Milliseconds 500</code> | 短暂等待 500 毫秒，为后台进程启动/监听端口预留时间后继续检测。 |
| 51 | <code>        $socket = [System.Net.Sockets.TcpClient]::new()</code> | 创建 TCP 测试客户端，用于短连接检查本机端口，不作为数据库业务连接。 |
| 52 | <code>        try { $connect = $socket.ConnectAsync(&#39;127.0.0.1&#39;, [int]$localConfig.port); $connect.Wait(300) &#124; Out-Null; $listening = $socket.Connected } catch { $listening = $false } finally { $socket.Dispose() }</code> | 短时连接127.0.0.1的商城端口并读取是否监听；连接失败设false，finally始终释放socket。这里只检测端口，下一段还核对本项目进程归属。 |
| 53 | <code>        if ($listening) { break }</code> | 判断条件 `$listening`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 54 | <code>        if ($process.HasExited) { throw &#39;商城 MySQL 启动失败，请查看 data/mysql/mall-mysql.log。&#39; }</code> | 判断条件 `$process.HasExited`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 55 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 56 | <code>    if (!$listening) { throw &#39;等待商城 MySQL 启动超时。&#39; }</code> | 判断条件 `!$listening`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 57 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 58 | <code># 首次启动只初始化商城自己的库和账号；之后用商城账号确认连接。</code> | 注释：首次启动只初始化商城自己的库和账号；之后用商城账号确认连接。；不作为配置值或命令执行。 |
| 59 | <code>$clientConfig = Join-Path $runtimeDir &#39;mysql-client.cnf&#39;</code> | 通过 Join-Path 组合当前项目/工具目录与明确的子路径，保存到变量 $clientConfig，避免手写路径分隔符。 |
| 60 | <code>if (!(Test-Path -LiteralPath (Join-Path $runtimeDir &#39;mysql.ready&#39;))) {</code> | 判断条件 `!(Test-Path -LiteralPath (Join-Path $runtimeDir 'mysql.ready'))`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 61 | <code>    $bootstrapSql = @&quot;</code> | 开始 PowerShell 可插值多行字符串；后续配置/SQL保留换行，变量会在字符串构造时替换。 |
| 62 | <code>CREATE DATABASE IF NOT EXISTS mall_admin CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;</code> | 只创建商城自己的 mall_admin 数据库，使用 utf8mb4 中文字符集；已有数据库保留。 |
| 63 | <code>CREATE USER IF NOT EXISTS &#39;mall_app&#39;@&#39;127.0.0.1&#39; IDENTIFIED BY &#39;$($localConfig.password)&#39;;</code> | 为本机 127.0.0.1 创建商城应用数据库账号，密码取自本机随机配置。 |
| 64 | <code>GRANT ALL PRIVILEGES ON mall_admin.* TO &#39;mall_app&#39;@&#39;127.0.0.1&#39;;</code> | 只授予 mall_app 对 mall_admin.* 的权限，不授予其他数据库权限。 |
| 65 | <code>ALTER USER &#39;root&#39;@&#39;localhost&#39; IDENTIFIED BY &#39;$($localConfig.rootPassword)&#39;;</code> | 为本项目新初始化的独立 MySQL root 账号设置随机密码；不连接若依使用的数据库服务。 |
| 66 | <code>&quot;@</code> | 结束多行字符串，其内容随后作为 SQL 发送给本项目 MySQL。 |
| 67 | <code>    $bootstrapSql &#124; &amp; $clientExe --no-defaults --protocol=TCP --host=127.0.0.1 &quot;--port=$($localConfig.port)&quot; --user=root --default-character-set=utf8mb4</code> | 把仅针对商城独立库的初始化 SQL 通过标准输入送给 mysql 客户端，避免写到命令行参数。 |
| 68 | <code>    if ($LASTEXITCODE -ne 0) { throw &#39;商城数据库账号初始化失败，请保留当前数据并检查 mysql 日志。&#39; }</code> | 判断条件 `$LASTEXITCODE -ne 0`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 69 | <code>    Set-Content -LiteralPath (Join-Path $runtimeDir &#39;mysql.ready&#39;) -Value &#39;initialized&#39; -Encoding utf8</code> | 将准备完成标记或本项目运行配置写入明确路径，后续启动复用该文件。 |
| 70 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 71 | <code>$clientContents = @&quot;</code> | 开始构造客户端配置的多行字符串，保存到 clientContents；其中变量会替换为当前商城数据库配置。 |
| 72 | <code>[client]</code> | MySQL 选项文件的客户端配置段，下面的 host/port/user/password 只供客户端读取。 |
| 73 | <code>host=127.0.0.1</code> | 设置 MySQL 客户端选项 `host`；变量从本项目配置读取，端口指向商城专用实例。 |
| 74 | <code>port=$($localConfig.port)</code> | 设置 MySQL 客户端选项 `port`；变量从本项目配置读取，端口指向商城专用实例。 |
| 75 | <code>user=$($localConfig.username)</code> | 设置 MySQL 客户端选项 `user`；变量从本项目配置读取，端口指向商城专用实例。 |
| 76 | <code>password=$($localConfig.password)</code> | 设置 MySQL 客户端选项 `password`；变量从本项目配置读取，端口指向商城专用实例。 |
| 77 | <code>protocol=TCP</code> | 设置 MySQL 客户端选项 `protocol`；变量从本项目配置读取，端口指向商城专用实例。 |
| 78 | <code>default-character-set=utf8mb4</code> | 设置 MySQL 客户端选项 `default-character-set`；变量从本项目配置读取，端口指向商城专用实例。 |
| 79 | <code>&quot;@</code> | 结束多行字符串，完整的客户端配置保存在 clientContents 中。 |
| 80 | <code>[System.IO.File]::WriteAllText($clientConfig, $clientContents, [System.Text.UTF8Encoding]::new($false))</code> | 以不带 BOM 的 UTF-8 写入 MySQL 客户端配置；UTF8Encoding(false) 在 PowerShell 5.1 和 7 中都不会添加 BOM，避免 MySQL 无法识别第一行的 [client]。 |
| 81 | <code>Push-Location $projectRoot</code> | 临时切换工作目录，确保 Maven/JDBC/MySQL 相对路径在预期项目目录中解析。 |
| 82 | <code>try { &amp; $clientExe --defaults-extra-file=.runtime/mysql-client.cnf --database=mall_admin --execute=&#39;SELECT 1;&#39; --skip-column-names }</code> | 使用本项目客户端选项文件连接mall_admin执行SELECT 1；随机密码只由选项文件传递，不显示在命令行。失败时后续明确报错。 |
| 83 | <code>finally { Pop-Location }</code> | 在 finally 中恢复原工作目录，无论前面操作成功或失败都执行。 |
| 84 | <code>if ($LASTEXITCODE -ne 0) { throw &#39;3307 端口上的数据库无法用商城账号访问；请检查端口是否被其他服务占用。&#39; }</code> | 判断条件 `$LASTEXITCODE -ne 0`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 85 | <code>Write-Host &#39;商城独立 MySQL 已就绪：127.0.0.1:3307 / mall_admin。&#39;</code> | 向终端显示当前操作结果/启动地址：`Write-Host '商城独立 MySQL 已就绪：127.0.0.1:3307 / mall_admin。'`；数据库随机密码不会在这里输出。 |
