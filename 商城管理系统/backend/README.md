# 商城后端运行说明

本目录保存 Java 17 及以上版本使用的 Spring Boot 后端源码、SQL、配置和集成测试。完整项目优先按 [项目运行说明](../README.md) 启动。

单独开发后端时，在本目录执行：

```powershell
mvn spring-boot:run
```

该方式默认使用本目录 `data/` 中的 H2 文件数据库。项目根目录的启动脚本默认使用商城独立 MySQL；两种数据库的数据分别保存。

后端地址为 http://127.0.0.1:8081，健康检查接口为 `/api/health`。独立运行 MySQL 模式时，需要提供 `MALL_DB_URL`、`MALL_DB_USER`、`MALL_DB_PASSWORD`，并启用 `mysql` profile。

运行集成测试：

```powershell
mvn '-Dmaven.repo.local=.m2/repository' test
```

代码分层、认证和事务规则见 [后端学习说明](../../docs/后端学习说明.md)。
