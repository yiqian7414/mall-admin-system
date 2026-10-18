param([switch]$KeepDatabase)
$ErrorActionPreference = 'Stop'
$projectRoot = $PSScriptRoot
$runtimeDir = Join-Path $projectRoot '.runtime'
foreach ($name in @('frontend', 'backend')) {
    $recordFile = Join-Path $runtimeDir ($name + '.process.json')
    if (!(Test-Path -LiteralPath $recordFile)) { continue }
    $record = Get-Content -LiteralPath $recordFile -Raw | ConvertFrom-Json
    $process = Get-Process -Id $record.pid -ErrorAction SilentlyContinue
    # 同时核对 PID 和启动时间，避免 PID 被其他程序复用时误停进程。
    if ($process -and $process.StartTime.ToUniversalTime().Ticks -eq $record.startedAt) {
        Stop-Process -Id $process.Id
        $process.WaitForExit(5000) | Out-Null
        Write-Host "已停止商城 $name。"
    }
}
if (!$KeepDatabase) {
    $recordFile = Join-Path $runtimeDir 'mysql.process.json'
    $configFile = Join-Path $runtimeDir 'mysql.local.json'
    if ((Test-Path -LiteralPath $recordFile) -and (Test-Path -LiteralPath $configFile)) {
        $record = Get-Content -LiteralPath $recordFile -Raw | ConvertFrom-Json
        $process = Get-Process -Id $record.pid -ErrorAction SilentlyContinue
        if ($process -and $process.StartTime.ToUniversalTime().Ticks -eq $record.startedAt) {
            $db = Get-Content -LiteralPath $configFile -Raw | ConvertFrom-Json
            # 使用选项文件传凭据，密码不会出现在命令行参数中。
            $rootClientContents = @"
[client]
host=127.0.0.1
port=$($db.port)
user=root
password=$($db.rootPassword)
protocol=TCP
"@
            [System.IO.File]::WriteAllText((Join-Path $runtimeDir 'mysql-root.cnf'), $rootClientContents, [System.Text.UTF8Encoding]::new($false))
            Push-Location $projectRoot
            try {
                & (Join-Path $db.mySqlHome 'bin/mysqladmin.exe') --defaults-extra-file=.runtime/mysql-root.cnf shutdown
                if ($LASTEXITCODE -ne 0) { throw '商城数据库未能正常停止，请查看数据库日志。' }
            } finally { Pop-Location }
            Write-Host '已正常停止商城独立 MySQL，数据文件保留。'
        }
    }
}
Write-Host '停止操作结束；没有删除任何业务数据。'

