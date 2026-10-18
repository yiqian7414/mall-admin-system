param([string]$MySqlHome = 'C:\MySQL\MySQL Server 8.0')
$ErrorActionPreference = 'Stop'
$projectRoot = Split-Path -Parent $PSScriptRoot
$runtimeDir = Join-Path $projectRoot '.runtime'
$dataDir = Join-Path $projectRoot 'data/mysql'
$configFile = Join-Path $runtimeDir 'mysql.local.json'
$serverExe = Join-Path $MySqlHome 'bin/mysqld.exe'
$clientExe = Join-Path $MySqlHome 'bin/mysql.exe'
if (!(Test-Path -LiteralPath $serverExe)) { throw '没有找到 MySQL，请用 -MySqlHome 指定安装目录，或启动时选择 -Database h2。' }
New-Item -ItemType Directory -Path $runtimeDir -Force | Out-Null
if (!(Test-Path -LiteralPath $configFile)) {
    if (Test-Path -LiteralPath (Join-Path $dataDir 'mysql')) { throw '发现已有商城数据库但缺少连接配置，请恢复 .runtime/mysql.local.json，避免覆盖数据。' }
    $localConfig = @{
        port = 3307
        username = 'mall_app'
        password = [Guid]::NewGuid().ToString('N')
        rootPassword = [Guid]::NewGuid().ToString('N')
        mySqlHome = $MySqlHome
    }
    $localConfig | ConvertTo-Json | Set-Content -LiteralPath $configFile -Encoding utf8
}
$localConfig = Get-Content -LiteralPath $configFile -Raw | ConvertFrom-Json
$alreadyInitialized = Test-Path -LiteralPath (Join-Path $dataDir 'mysql')
if (!$alreadyInitialized) {
    New-Item -ItemType Directory -Path $dataDir -Force | Out-Null
    Push-Location $projectRoot
    try { & $serverExe --no-defaults --initialize-insecure "--basedir=$MySqlHome" --datadir=./data/mysql --log-error=mysql-init.log }
    finally { Pop-Location }
    if ($LASTEXITCODE -ne 0) { throw '商城独立 MySQL 初始化失败，请查看 data/mysql/mysql-init.log。' }
}
$pidFile = Join-Path $runtimeDir 'mysql.pid'
$socket = [System.Net.Sockets.TcpClient]::new()
try { $connect = $socket.ConnectAsync('127.0.0.1', [int]$localConfig.port); $connect.Wait(500) | Out-Null; $listening = $socket.Connected } catch { $listening = $false } finally { $socket.Dispose() }
if ($listening) {
    $processRecord = Join-Path $runtimeDir 'mysql.process.json'
    if (!(Test-Path -LiteralPath $processRecord)) { throw '3307 端口已有未登记的数据库，停止准备操作以保留现有数据库。' }
    $record = Get-Content -LiteralPath $processRecord -Raw | ConvertFrom-Json
    $ownedProcess = Get-Process -Id $record.pid -ErrorAction SilentlyContinue
    if (!$ownedProcess -or $ownedProcess.StartTime.ToUniversalTime().Ticks -ne $record.startedAt) {
        throw '3307 端口没有对应的商城进程记录，请检查端口占用；不会修改该数据库。'
    }
}
if (!$listening) {
    # MySQL 8 在 Windows 下解析含中文的绝对参数路径不稳定，数据目录使用相对路径。
    $serverArgs = @('--no-defaults', ('--basedir="' + $MySqlHome + '"'), '--datadir=./data/mysql', '--bind-address=127.0.0.1', "--port=$($localConfig.port)", '--mysqlx=0', '--pid-file=mall-mysql.pid', '--log-error=mall-mysql.log')
    $process = Start-Process -FilePath $serverExe -ArgumentList $serverArgs -WorkingDirectory $projectRoot -WindowStyle Hidden -PassThru
    @{ pid = $process.Id; startedAt = $process.StartTime.ToUniversalTime().Ticks; executable = $serverExe } | ConvertTo-Json | Set-Content -LiteralPath (Join-Path $runtimeDir 'mysql.process.json') -Encoding utf8
    $listening = $false
    for ($attempt = 0; $attempt -lt 60; $attempt++) {
        Start-Sleep -Milliseconds 500
        $socket = [System.Net.Sockets.TcpClient]::new()
        try { $connect = $socket.ConnectAsync('127.0.0.1', [int]$localConfig.port); $connect.Wait(300) | Out-Null; $listening = $socket.Connected } catch { $listening = $false } finally { $socket.Dispose() }
        if ($listening) { break }
        if ($process.HasExited) { throw '商城 MySQL 启动失败，请查看 data/mysql/mall-mysql.log。' }
    }
    if (!$listening) { throw '等待商城 MySQL 启动超时。' }
}
# 首次启动只初始化商城自己的库和账号；之后用商城账号确认连接。
$clientConfig = Join-Path $runtimeDir 'mysql-client.cnf'
if (!(Test-Path -LiteralPath (Join-Path $runtimeDir 'mysql.ready'))) {
    $bootstrapSql = @"
CREATE DATABASE IF NOT EXISTS mall_admin CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS 'mall_app'@'127.0.0.1' IDENTIFIED BY '$($localConfig.password)';
GRANT ALL PRIVILEGES ON mall_admin.* TO 'mall_app'@'127.0.0.1';
ALTER USER 'root'@'localhost' IDENTIFIED BY '$($localConfig.rootPassword)';
"@
    $bootstrapSql | & $clientExe --no-defaults --protocol=TCP --host=127.0.0.1 "--port=$($localConfig.port)" --user=root --default-character-set=utf8mb4
    if ($LASTEXITCODE -ne 0) { throw '商城数据库账号初始化失败，请保留当前数据并检查 mysql 日志。' }
    Set-Content -LiteralPath (Join-Path $runtimeDir 'mysql.ready') -Value 'initialized' -Encoding utf8
}
$clientContents = @"
[client]
host=127.0.0.1
port=$($localConfig.port)
user=$($localConfig.username)
password=$($localConfig.password)
protocol=TCP
default-character-set=utf8mb4
"@
[System.IO.File]::WriteAllText($clientConfig, $clientContents, [System.Text.UTF8Encoding]::new($false))
Push-Location $projectRoot
try { & $clientExe --defaults-extra-file=.runtime/mysql-client.cnf --database=mall_admin --execute='SELECT 1;' --skip-column-names }
finally { Pop-Location }
if ($LASTEXITCODE -ne 0) { throw '3307 端口上的数据库无法用商城账号访问；请检查端口是否被其他服务占用。' }
Write-Host '商城独立 MySQL 已就绪：127.0.0.1:3307 / mall_admin。'
