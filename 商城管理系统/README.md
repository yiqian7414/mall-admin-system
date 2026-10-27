# 商城管理系统

Vue 3 + Spring Boot 商城管理后台。本文件夹保存源码、配置、数据库、依赖及项目运行脚本。

## 库存与商品图片

- 新商品库存固定为 0，新增和编辑商品均不能直接设置库存。
- “库存管理”提供库存总览、手动入库、手动出库及只读流水；订单扣减和取消归还自动记录，付款、发货时不重复扣减。
- 商品可选上传一张 JPG、PNG、WebP 图片（原图不超过 5 MB、宽高不超过 4096），后端校验后重新编码保存。
- 图片默认保存在 `backend/data/uploads/products/`。迁移或备份时请同时保留数据库和上传目录；Git 忽略的本机数据不会自动上传到源码仓库。
- 首次升级会追加图片字段、创建库存流水表并登记旧商品的期初库存，不清空已有商品、订单或库存。

操作步骤、代码调用流程和示例见 [库存管理与商品图片](../docs/库存管理与商品图片.md)。

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

逐行讲解、架构说明及学习路线统一放在同级的 [商城学习资料](../docs/README.md) 文件夹。移动项目时，完整保留本文件夹；同时移动学习资料可保持两者之间的文档链接有效。
