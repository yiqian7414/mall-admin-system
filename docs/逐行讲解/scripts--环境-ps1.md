# scripts/环境.ps1

源文件：[`scripts/环境.ps1`](../../商城管理系统/scripts/环境.ps1)。以下行号以当前文件为准，空行也计入。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code># 所有环境变量只影响当前脚本和它启动的子进程。</code> | 注释：所有环境变量只影响当前脚本和它启动的子进程。；不作为配置值或命令执行。 |
| 2 | <code>function Get-MallToolchain {</code> | 定义 PowerShell 函数 `Get-MallToolchain`；后续脚本调用它时执行函数体。 |
| 3 | <code>    $javaCandidates = @(</code> | 给脚本变量 $javaCandidates 赋值 `@(`；后续命令引用这个运行状态/路径。 |
| 4 | <code>        $env:JAVA_HOME,</code> | 设置子进程继承的环境变量；只影响当前脚本及其子进程。 |
| 5 | <code>        (Join-Path $env:APPDATA &#39;Code/User/globalStorage/pleiades.java-extension-pack-jdk/java/21&#39;),</code> | 继续声明工具候选路径；Join-Path 将环境目录与已知的 JDK/Maven 子目录组合。 |
| 6 | <code>        (Join-Path $env:APPDATA &#39;Code/User/globalStorage/pleiades.java-extension-pack-jdk/java/17&#39;)</code> | 继续声明工具候选路径；Join-Path 将环境目录与已知的 JDK/Maven 子目录组合。 |
| 7 | <code>    )</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 8 | <code>    $javaHome = $javaCandidates &#124; Where-Object { $_ -and (Test-Path -LiteralPath (Join-Path $_ &#39;bin/javac.exe&#39;)) } &#124; Select-Object -First 1</code> | 通过 Join-Path 组合当前项目/工具目录与明确的子路径，保存到变量 $javaHome，避免手写路径分隔符。 |
| 9 | <code>    if (!$javaHome) { throw &#39;请安装 JDK 17 或更新版本，并设置 JAVA_HOME。&#39; }</code> | 判断条件 `!$javaHome`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 10 | <code>    $mavenCandidates = @(</code> | 给脚本变量 $mavenCandidates 赋值 `@(`；后续命令引用这个运行状态/路径。 |
| 11 | <code>        $(if ($env:MAVEN_HOME) { Join-Path $env:MAVEN_HOME &#39;bin/mvn.cmd&#39; }),</code> | 通过 Join-Path 组合当前项目/工具目录与明确的子路径，保存到变量 $(if ($env:MAVEN_HOME) { Join-Path $env:MAVEN_HOME 'bin/mvn.cmd' }),，避免手写路径分隔符。 |
| 12 | <code>        (Join-Path $env:APPDATA &#39;Code/User/globalStorage/pleiades.java-extension-pack-jdk/maven/latest/bin/mvn.cmd&#39;),</code> | 继续声明工具候选路径；Join-Path 将环境目录与已知的 JDK/Maven 子目录组合。 |
| 13 | <code>        @(Get-Command mvn.cmd -CommandType Application -ErrorAction SilentlyContinue &#124; Select-Object -ExpandProperty Source)</code> | 用 PowerShell 的 Get-Command 查找 PATH 中的 Maven；没有找到时返回空结果，避免 Windows PowerShell 5.1 将外部 where.exe 的错误输出当成终止错误。 |
| 14 | <code>    )</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 15 | <code>    $maven = $mavenCandidates &#124; Where-Object { $_ -and (Test-Path -LiteralPath $_) } &#124; Select-Object -First 1</code> | 筛选真实存在且可用的工具路径，再取首个候选；不修改系统安装。 |
| 16 | <code>    if (!$maven) { throw &#39;请安装 Maven 3.6.3 或更新版本，并加入 PATH 或设置 MAVEN_HOME。&#39; }</code> | 判断条件 `!$maven`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 17 | <code>    $node = Get-Command node.exe -CommandType Application -ErrorAction SilentlyContinue &#124; Select-Object -First 1 -ExpandProperty Source</code> | 从 PATH 查找 node.exe 的应用程序路径，取第一个结果；找不到时交给后面的工具缺失提示处理。 |
| 18 | <code>    $npm = Get-Command npm.cmd -CommandType Application -ErrorAction SilentlyContinue &#124; Select-Object -First 1 -ExpandProperty Source</code> | 从 PATH 查找 npm.cmd 的应用程序路径，取第一个结果；找不到时交给后面的工具缺失提示处理。 |
| 19 | <code>    if (!$node -or !$npm) { throw &#39;请安装符合 frontend-vue3/package.json 要求的 Node.js。&#39; }</code> | 判断条件 `!$node -or !$npm`；成立时执行随后分支，用于控制校验、权限或状态流转。 |
| 20 | <code>    return @{ JavaHome = $javaHome; Java = (Join-Path $javaHome &#39;bin/java.exe&#39;); Maven = $maven; Node = $node; Npm = $npm }</code> | 把 `@{ JavaHome = $javaHome; Java = (Join-Path $javaHome 'bin/java.exe'); Maven = $maven; Node = $node; Npm = $npm }` 返回给调用方，并结束当前函数/方法。 |
| 21 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 22 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 23 | <code>function Test-MallPort([int]$Port) {</code> | 定义 PowerShell 函数 `Test-MallPort`；后续脚本调用它时执行函数体。 |
| 24 | <code>    $socket = [System.Net.Sockets.TcpClient]::new()</code> | 创建 TCP 测试客户端，用于短连接检查本机端口，不作为数据库业务连接。 |
| 25 | <code>    try {</code> | 开始尝试执行可能失败的操作；抛出的异常交给后续 catch 统一处理。 |
| 26 | <code>        $connect = $socket.ConnectAsync(&#39;127.0.0.1&#39;, $Port)</code> | 尝试连接本机指定端口，短时等待后读取 Connected，并释放测试 socket；用于判断服务是否就绪。 |
| 27 | <code>        $connect.Wait(500) &#124; Out-Null</code> | 最多等 500 毫秒完成端口连接测试，忽略 Wait 的返回输出，随后读取 socket.Connected。 |
| 28 | <code>        return $socket.Connected</code> | 把 `$socket.Connected` 返回给调用方，并结束当前函数/方法。 |
| 29 | <code>    } catch { return $false }</code> | 端口连接异常时返回 false，表示该端口当前未就绪，不把预期连接拒绝当脚本失败。 |
| 30 | <code>    finally { $socket.Dispose() }</code> | 无论连接成功失败都释放测试 TCP 客户端，避免资源泄漏。 |
| 31 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 32 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 33 | <code>function Save-MallProcess($Process, [string]$Name, [string]$RuntimeDir) {</code> | 定义 PowerShell 函数 `Save-MallProcess`；后续脚本调用它时执行函数体。 |
| 34 | <code>    @{ pid = $Process.Id; startedAt = $Process.StartTime.ToUniversalTime().Ticks } &#124;</code> | 构造本项目进程记录，保存 PID、UTC 启动时间及必要路径；停止时同时核对，防止 PID 被复用。 |
| 35 | <code>        ConvertTo-Json &#124; Set-Content -LiteralPath (Join-Path $RuntimeDir ($Name + &#39;.process.json&#39;)) -Encoding utf8</code> | 把前一行进程记录对象转为 JSON，并用 UTF-8 写入本项目 .runtime 下的记录文件。 |
| 36 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 37 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
