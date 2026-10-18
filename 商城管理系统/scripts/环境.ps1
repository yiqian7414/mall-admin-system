# 所有环境变量只影响当前脚本和它启动的子进程。
function Get-MallToolchain {
    $javaCandidates = @(
        $env:JAVA_HOME,
        (Join-Path $env:APPDATA 'Code/User/globalStorage/pleiades.java-extension-pack-jdk/java/21'),
        (Join-Path $env:APPDATA 'Code/User/globalStorage/pleiades.java-extension-pack-jdk/java/17')
    )
    $javaHome = $javaCandidates | Where-Object { $_ -and (Test-Path -LiteralPath (Join-Path $_ 'bin/javac.exe')) } | Select-Object -First 1
    if (!$javaHome) { throw '请安装 JDK 17 或更新版本，并设置 JAVA_HOME。' }
    $mavenCandidates = @(
        $(if ($env:MAVEN_HOME) { Join-Path $env:MAVEN_HOME 'bin/mvn.cmd' }),
        (Join-Path $env:APPDATA 'Code/User/globalStorage/pleiades.java-extension-pack-jdk/maven/latest/bin/mvn.cmd'),
        @(Get-Command mvn.cmd -CommandType Application -ErrorAction SilentlyContinue | Select-Object -ExpandProperty Source)
    )
    $maven = $mavenCandidates | Where-Object { $_ -and (Test-Path -LiteralPath $_) } | Select-Object -First 1
    if (!$maven) { throw '请安装 Maven 3.6.3 或更新版本，并加入 PATH 或设置 MAVEN_HOME。' }
    $node = Get-Command node.exe -CommandType Application -ErrorAction SilentlyContinue | Select-Object -First 1 -ExpandProperty Source
    $npm = Get-Command npm.cmd -CommandType Application -ErrorAction SilentlyContinue | Select-Object -First 1 -ExpandProperty Source
    if (!$node -or !$npm) { throw '请安装符合 frontend-vue3/package.json 要求的 Node.js。' }
    return @{ JavaHome = $javaHome; Java = (Join-Path $javaHome 'bin/java.exe'); Maven = $maven; Node = $node; Npm = $npm }
}

function Test-MallPort([int]$Port) {
    $socket = [System.Net.Sockets.TcpClient]::new()
    try {
        $connect = $socket.ConnectAsync('127.0.0.1', $Port)
        $connect.Wait(500) | Out-Null
        return $socket.Connected
    } catch { return $false }
    finally { $socket.Dispose() }
}

function Save-MallProcess($Process, [string]$Name, [string]$RuntimeDir) {
    @{ pid = $Process.Id; startedAt = $Process.StartTime.ToUniversalTime().Ticks } |
        ConvertTo-Json | Set-Content -LiteralPath (Join-Path $RuntimeDir ($Name + '.process.json')) -Encoding utf8
}

