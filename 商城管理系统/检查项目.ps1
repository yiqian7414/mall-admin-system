$ErrorActionPreference = 'Stop'
$projectRoot = $PSScriptRoot
. (Join-Path $projectRoot 'scripts/环境.ps1')
$tools = Get-MallToolchain
$env:JAVA_HOME = $tools.JavaHome
# 测试类明确使用独立的 H2 内存库，不读写正在运行的 MySQL 商城数据。
Push-Location (Join-Path $projectRoot 'backend')
try {
    & $tools.Maven -B '-Dmaven.repo.local=.m2/repository' test
    if ($LASTEXITCODE -ne 0) { throw '后端集成测试失败。' }
} finally { Pop-Location }
Push-Location (Join-Path $projectRoot 'frontend-vue3')
try {
    & $tools.Npm run build
    if ($LASTEXITCODE -ne 0) { throw '前端构建失败。' }
} finally { Pop-Location }
Write-Host '后端集成测试与前端构建均通过。'
