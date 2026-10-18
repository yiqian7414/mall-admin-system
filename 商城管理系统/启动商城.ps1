param(
    [ValidateSet('mysql', 'h2')][string]$Database = 'mysql',
    [switch]$SkipBuild,
    [int]$FrontendPort = 5174
)
$ErrorActionPreference = 'Stop'
$projectRoot = $PSScriptRoot
. (Join-Path $projectRoot 'scripts/环境.ps1')
$tools = Get-MallToolchain
$env:JAVA_HOME = $tools.JavaHome
$runtimeDir = Join-Path $projectRoot '.runtime'
New-Item -ItemType Directory -Path $runtimeDir -Force | Out-Null
$backendDir = Join-Path $projectRoot 'backend'
$frontendDir = Join-Path $projectRoot 'frontend-vue3'
$jarFile = Join-Path $backendDir 'target/mall-backend-1.0.0.jar'

if (Test-MallPort 8081) { throw '8081 端口已被占用。若商城已运行，请直接访问页面；重新启动前先运行 停止商城.ps1。' }
if (Test-MallPort $FrontendPort) { throw "前端端口 $FrontendPort 已被占用，请停止已有商城或用 -FrontendPort 指定其他端口。" }

if ($Database -eq 'mysql') {
    & (Join-Path $projectRoot 'scripts/准备MySQL.ps1')
    $db = Get-Content -LiteralPath (Join-Path $runtimeDir 'mysql.local.json') -Raw | ConvertFrom-Json
    $env:SPRING_PROFILES_ACTIVE = 'mysql'
    $env:MALL_DB_URL = "jdbc:mysql://127.0.0.1:$($db.port)/mall_admin?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true"
    $env:MALL_DB_USER = $db.username
    $env:MALL_DB_PASSWORD = $db.password
} else {
    $env:SPRING_PROFILES_ACTIVE = 'h2'
    Remove-Item Env:MALL_DB_URL -ErrorAction SilentlyContinue
    Remove-Item Env:MALL_DB_USER -ErrorAction SilentlyContinue
    Remove-Item Env:MALL_DB_PASSWORD -ErrorAction SilentlyContinue
}
if (!$SkipBuild -or !(Test-Path -LiteralPath $jarFile)) {
    Push-Location $backendDir
    try {
        & $tools.Maven -B '-Dmaven.repo.local=.m2/repository' -DskipTests package
        if ($LASTEXITCODE -ne 0) { throw '后端构建失败，请根据上方错误修复。' }
    } finally { Pop-Location }
}
if (!(Test-Path -LiteralPath (Join-Path $frontendDir 'node_modules'))) {
    Push-Location $frontendDir
    try {
        & $tools.Npm ci --no-audit --no-fund
        if ($LASTEXITCODE -ne 0) { throw '前端依赖安装失败。' }
    } finally { Pop-Location }
}
$backendProcess = Start-Process -FilePath $tools.Java -ArgumentList @('-jar', ('"' + $jarFile + '"')) -WorkingDirectory $backendDir -WindowStyle Hidden -RedirectStandardOutput (Join-Path $runtimeDir 'backend.log') -RedirectStandardError (Join-Path $runtimeDir 'backend-error.log') -PassThru
Save-MallProcess $backendProcess 'backend' $runtimeDir
$backendReady = $false
for ($attempt = 0; $attempt -lt 80; $attempt++) {
    Start-Sleep -Milliseconds 500
    try {
        $health = Invoke-RestMethod -Uri 'http://127.0.0.1:8081/api/health' -TimeoutSec 2
        if ($health.application -eq 'mall-admin') { $backendReady = $true; break }
    } catch { }
    if ($backendProcess.HasExited) { throw '后端启动失败，请查看 .runtime/backend.log 和 backend-error.log。' }
}
if (!$backendReady) { throw '后端启动超时，请查看 .runtime/backend.log。' }
$viteFile = Join-Path $frontendDir 'node_modules/vite/bin/vite.js'
$frontendProcess = Start-Process -FilePath $tools.Node -ArgumentList @(('"' + $viteFile + '"'), '--host', '127.0.0.1', '--port', $FrontendPort, '--strictPort') -WorkingDirectory $frontendDir -WindowStyle Hidden -RedirectStandardOutput (Join-Path $runtimeDir 'frontend.log') -RedirectStandardError (Join-Path $runtimeDir 'frontend-error.log') -PassThru
Save-MallProcess $frontendProcess 'frontend' $runtimeDir
$frontendReady = $false
for ($attempt = 0; $attempt -lt 40; $attempt++) {
    Start-Sleep -Milliseconds 250
    if (Test-MallPort $FrontendPort) { $frontendReady = $true; break }
    if ($frontendProcess.HasExited) { throw '前端启动失败，请查看 .runtime/frontend-error.log。' }
}
if (!$frontendReady) { throw '前端启动超时，请查看 .runtime/frontend.log。' }
Write-Host "商城已启动：http://127.0.0.1:$FrontendPort/"
Write-Host '学习账号：admin / Admin@123456；员工：staff / Staff@123456。'
Write-Host "当前数据库：$Database；停止服务请运行 .\停止商城.ps1。"

