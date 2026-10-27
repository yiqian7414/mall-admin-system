# backend/src/main/resources/application-mysql.yml

源文件：[`backend/src/main/resources/application-mysql.yml`](../../商城管理系统/backend/src/main/resources/application-mysql.yml)。以下行号以当前文件为准，空行也计入。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code># 只连接你显式提供的数据库。先创建一个全新的空数据库；不要填写原若依的数据库。</code> | 注释：只连接你显式提供的数据库。先创建一个全新的空数据库；不要填写原若依的数据库。；不作为配置值或命令执行。 |
| 2 | <code>spring:</code> | 开始MySQL profile配置，覆盖基础H2的数据源。 |
| 3 | <code>  datasource:</code> | 设置本次运行的MySQL连接信息。 |
| 4 | <code>    url: ${MALL_DB_URL}</code> | 从当前进程环境变量读取商城专用MySQL JDBC地址，启动脚本提供127.0.0.1:3307/mall_admin。 |
| 5 | <code>    username: ${MALL_DB_USER}</code> | 从环境变量读取商城数据库账号，不复用若依账号。 |
| 6 | <code>    password: ${MALL_DB_PASSWORD}</code> | 从环境变量读取本机随机数据库密码，源码不硬编码凭据。 |
| 7 | <code>    driver-class-name: com.mysql.cj.jdbc.Driver</code> | 使用MySQL Connector/J驱动解释jdbc:mysql连接串。 |
