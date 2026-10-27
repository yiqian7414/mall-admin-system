# backend/src/main/resources/application.yml

源文件：[`backend/src/main/resources/application.yml`](../../商城管理系统/backend/src/main/resources/application.yml)。空行计入行号。

本文件按当前源码重新校对行号；未改变代码的已有讲解予以保留。

库存与图片的业务示例、整体流程见 [新功能说明](../库存管理与商品图片.md)。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>server:</code> | 配置内嵌 HTTP 服务器及会话。 |
| 2 | <code>  address: 127.0.0.1</code> | 只监听本机回环地址，其他电脑不能直接访问此学习服务。 |
| 3 | <code>  port: 8081</code> | 后端 HTTP 端口为8081，Vite 的 /api 代理指向这里。 |
| 4 | <code>  servlet:</code> | Servlet 容器相关配置，下面设置服务器会话。 |
| 5 | <code>    session:</code> | 配置 Cookie 关联的服务器登录会话。 |
| 6 | <code>      timeout: 30m</code> | 会话无活动超时时间为30分钟，超时后业务请求需要重新登录。 |
| 7 | <code>      cookie:</code> | 配置浏览器保存的会话 Cookie 属性。 |
| 8 | <code>        http-only: true</code> | 禁止页面 JavaScript 读取 JSESSIONID，浏览器仍会随请求自动携带。 |
| 9 | <code>        same-site: lax</code> | Cookie 使用 SameSite=Lax，限制跨站请求自动携带；写请求仍同时检查CSRF。 |
| 10 | <code>spring:</code> | 开始 Spring Boot 应用/数据库/序列化配置。 |
| 11 | <code>  servlet:</code> | Servlet 容器相关配置，下面设置服务器会话。 |
| 12 | <code>    multipart:</code> | 配置应用参数，缩进决定层级；启动时由 Spring Boot 读取。 |
| 13 | <code>      max-file-size: 5MB</code> | 配置应用参数，缩进决定层级；启动时由 Spring Boot 读取。 |
| 14 | <code>      max-request-size: 6MB</code> | 配置应用参数，缩进决定层级；启动时由 Spring Boot 读取。 |
| 15 | <code>  application:</code> | 配置应用标识。 |
| 16 | <code>    name: mall-backend</code> | 应用名为mall-backend，用于Spring环境与日志标识。 |
| 17 | <code>  datasource:</code> | 配置 JDBC 数据源，连接串/用户/驱动由这些值决定。 |
| 18 | <code>    url: jdbc:h2:file:./data/mall;MODE=MySQL;DATABASE_TO_LOWER=TRUE;DB_CLOSE_ON_EXIT=FALSE;LOCK_TIMEOUT=10000</code> | 默认基础配置使用H2文件库，相对后端工作目录data/mall；兼容MySQL语法、小写标识符、显式控制退出关闭行为，锁等待超时10秒。启动mysql profile时由MySQL连接串覆盖。 |
| 19 | <code>    username: sa</code> | H2本地文件库使用sa账号；MySQL profile会覆盖它。 |
| 20 | <code>    password: &quot;&quot;</code> | H2本地学习库基础密码为空；MySQL随机密码来自环境变量，不写在这里。 |
| 21 | <code>    driver-class-name: org.h2.Driver</code> | 默认使用H2 JDBC驱动，mysql profile改用MySQL驱动。 |
| 22 | <code>  sql:</code> | 配置启动时SQL脚本初始化。 |
| 23 | <code>    init:</code> | 初始化器读取classpath下的schema.sql。 |
| 24 | <code>      mode: always</code> | 无论H2/MySQL都执行建表脚本；脚本使用IF NOT EXISTS，不清空已存在的表。 |
| 25 | <code>      encoding: UTF-8</code> | 以UTF-8读取SQL，保留中文注释/数据编码。 |
| 26 | <code>  jackson:</code> | 配置HTTP JSON的Jackson序列化/反序列化。 |
| 27 | <code>    deserialization:</code> | 配置从请求JSON转换到Java对象时的行为。 |
| 28 | <code>      fail-on-unknown-properties: true</code> | 拒绝 JSON 中未声明的字段，例如向商品接口额外发送 stock 会被拒绝。 |
| 29 | <code>      accept-float-as-int: false</code> | 拒绝把小数库存/数量截断成整数，要求整数请求真正为整数。 |
| 30 | <code>  mvc:</code> | Spring MVC响应行为配置。 |
| 31 | <code>    problemdetails:</code> | 配置RFC Problem Details响应格式的自动处理。 |
| 32 | <code>      enabled: false</code> | 关闭框架Problem Details自动格式，业务错误使用统一中文message结构。 |
| 33 | <code>logging:</code> | 开始日志级别配置。 |
| 34 | <code>  level:</code> | 为指定日志器设置级别。 |
| 35 | <code>    org.springframework.security.config.annotation.authentication.configuration.InitializeUserDetailsBeanManagerConfigurer: ERROR</code> | 降低用户详情认证初始化器的非错误日志输出；真实异常仍记录，不改变权限或认证逻辑。 |
