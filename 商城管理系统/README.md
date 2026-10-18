# 商城管理系统

Vue 3 + Spring Boot 商城管理后台。本文件夹保存源码、配置、数据库、依赖及项目运行脚本。

## 启动项目

在本文件夹中打开 PowerShell，执行：

```powershell
.\启动商城.ps1
```

已有构建结果且没有修改后端源码时，可执行 `.\启动商城.ps1 -SkipBuild`。脚本会准备商城专用 MySQL，启动后端和前端。

启动完成后访问 [商城管理系统](http://127.0.0.1:5174/)。首次初始化的管理员账号为 `admin / Admin@123456`，员工账号为 `staff / Staff@123456`；修改过密码后使用新密码。

## 数据库与关闭

单独启动商城数据库：

```powershell
.\scripts\准备MySQL.ps1
```

DataGrip 连接 `127.0.0.1:3307`，数据库为 `mall_admin`。数据库账号和密码以 `.runtime/mysql.local.json` 为准，与网页登录账号分开。修改文件中的密码后，还需要同步修改 MySQL 中的账号密码。

关闭前端、后端并保留数据库运行：

```powershell
.\停止商城.ps1 -KeepDatabase
```

如果还要关闭商城 MySQL，在本文件夹中继续执行：

```powershell
& "C:\MySQL\MySQL Server 8.0\bin\mysqladmin.exe" --defaults-extra-file=.runtime/mysql-root.cnf shutdown
```

该命令使用本机已有的管理员连接文件。运行 `.\停止商城.ps1` 同时停止数据库时，配置中的 `rootPassword` 必须与 MySQL 实际管理员密码一致。

正常关闭会保留数据。关闭 DataGrip 只会断开客户端连接，MySQL 服务需要单独停止。

## 目录与检查

| 内容 | 位置 |
| --- | --- |
| Vue 3 前端源码 | `frontend-vue3/src/` |
| Java 后端源码、SQL 与测试 | `backend/src/` |
| 启动与工具链脚本 | 根目录的 `.ps1` 文件及 `scripts/` |
| 商城 MySQL 数据 | `data/mysql/` |
| H2 备用数据 | `backend/data/` |
| 本机凭据、日志和进程记录 | `.runtime/` |
| 安装依赖与构建产物 | `node_modules/`、`.m2/`、`target/`、`dist/` 等子目录 |

运行项目检查：

```powershell
.\检查项目.ps1
```

项目介绍、截图、功能范围和成员信息见 [仓库首页](../README.md)。前后端说明位于仓库 docs 文件夹。
