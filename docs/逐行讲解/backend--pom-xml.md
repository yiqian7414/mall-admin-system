# backend/pom.xml

源文件：[`backend/pom.xml`](../../商城管理系统/backend/pom.xml)。空行计入行号。

本文件按当前源码重新校对行号；未改变代码的已有讲解予以保留。

库存与图片的业务示例、整体流程见 [新功能说明](../库存管理与商品图片.md)。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>&lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt;</code> | 声明 XML 版本与编码。 |
| 2 | <code>&lt;project xmlns=&quot;http://maven.apache.org/POM/4.0.0&quot; xmlns:xsi=&quot;http://www.w3.org/2001/XMLSchema-instance&quot;</code> | 开始/结束 Maven 配置节点 `<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"`，用于组织父工程、依赖或构建插件。 |
| 3 | <code>         xsi:schemaLocation=&quot;http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd&quot;&gt;</code> | 指定 Maven POM 使用的 XML 结构定义，供工具检查 pom.xml 格式。 |
| 4 | <code>    &lt;modelVersion&gt;4.0.0&lt;/modelVersion&gt;</code> | 设置 Maven 配置项 `modelVersion` 为 `4.0.0`。 |
| 5 | <code>    &lt;parent&gt;</code> | 开始/结束 Maven 配置节点 `<parent>`，用于组织父工程、依赖或构建插件。 |
| 6 | <code>        &lt;groupId&gt;org.springframework.boot&lt;/groupId&gt;</code> | 设置 Maven 配置项 `groupId` 为 `org.springframework.boot`。 |
| 7 | <code>        &lt;artifactId&gt;spring-boot-starter-parent&lt;/artifactId&gt;</code> | 设置 Maven 配置项 `artifactId` 为 `spring-boot-starter-parent`。 |
| 8 | <code>        &lt;version&gt;3.5.16&lt;/version&gt;</code> | 设置 Maven 配置项 `version` 为 `3.5.16`。 |
| 9 | <code>        &lt;relativePath/&gt;</code> | 开始/结束 Maven 配置节点 `<relativePath/>`，用于组织父工程、依赖或构建插件。 |
| 10 | <code>    &lt;/parent&gt;</code> | 关闭 `parent` 元素，结束对应区域的包含关系。 |
| 11 | <code>    &lt;groupId&gt;com.example&lt;/groupId&gt;</code> | 设置 Maven 配置项 `groupId` 为 `com.example`。 |
| 12 | <code>    &lt;artifactId&gt;mall-backend&lt;/artifactId&gt;</code> | 设置 Maven 配置项 `artifactId` 为 `mall-backend`。 |
| 13 | <code>    &lt;version&gt;1.0.0&lt;/version&gt;</code> | 设置 Maven 配置项 `version` 为 `1.0.0`。 |
| 14 | <code>    &lt;name&gt;mall-backend&lt;/name&gt;</code> | 设置 Maven 配置项 `name` 为 `mall-backend`。 |
| 15 | <code>    &lt;description&gt;商城管理系统教学后端&lt;/description&gt;</code> | 设置 Maven 配置项 `description` 为 `商城管理系统教学后端`。 |
| 16 | <code>    &lt;properties&gt;</code> | 开始/结束 Maven 配置节点 `<properties>`，用于组织父工程、依赖或构建插件。 |
| 17 | <code>        &lt;java.version&gt;17&lt;/java.version&gt;</code> | 设置 Maven 配置项 `java.version` 为 `17`。 |
| 18 | <code>    &lt;/properties&gt;</code> | 关闭 `properties` 元素，结束对应区域的包含关系。 |
| 19 | <code>    &lt;dependencies&gt;</code> | 开始/结束 Maven 配置节点 `<dependencies>`，用于组织父工程、依赖或构建插件。 |
| 20 | <code>        &lt;dependency&gt;</code> | 开始/结束 Maven 配置节点 `<dependency>`，用于组织父工程、依赖或构建插件。 |
| 21 | <code>            &lt;groupId&gt;com.twelvemonkeys.imageio&lt;/groupId&gt;</code> | 定义页面元素或组件及其属性；其中 {{ }}、冒号属性和事件会与 Vue 状态连接。 |
| 22 | <code>            &lt;artifactId&gt;imageio-webp&lt;/artifactId&gt;</code> | 加入 WebP 图片解码支持；JDK 自带 ImageIO 主要处理 JPEG、PNG 等格式。 |
| 23 | <code>            &lt;version&gt;3.12.0&lt;/version&gt;</code> | 定义页面元素或组件及其属性；其中 {{ }}、冒号属性和事件会与 Vue 状态连接。 |
| 24 | <code>        &lt;/dependency&gt;</code> | 关闭 `dependency` 元素，结束对应区域的包含关系。 |
| 25 | <code>        &lt;dependency&gt;</code> | 开始/结束 Maven 配置节点 `<dependency>`，用于组织父工程、依赖或构建插件。 |
| 26 | <code>            &lt;groupId&gt;org.springframework.boot&lt;/groupId&gt;</code> | 设置 Maven 配置项 `groupId` 为 `org.springframework.boot`。 |
| 27 | <code>            &lt;artifactId&gt;spring-boot-starter-web&lt;/artifactId&gt;</code> | 设置 Maven 配置项 `artifactId` 为 `spring-boot-starter-web`。 |
| 28 | <code>        &lt;/dependency&gt;</code> | 关闭 `dependency` 元素，结束对应区域的包含关系。 |
| 29 | <code>        &lt;dependency&gt;</code> | 开始/结束 Maven 配置节点 `<dependency>`，用于组织父工程、依赖或构建插件。 |
| 30 | <code>            &lt;groupId&gt;org.springframework.boot&lt;/groupId&gt;</code> | 设置 Maven 配置项 `groupId` 为 `org.springframework.boot`。 |
| 31 | <code>            &lt;artifactId&gt;spring-boot-starter-validation&lt;/artifactId&gt;</code> | 设置 Maven 配置项 `artifactId` 为 `spring-boot-starter-validation`。 |
| 32 | <code>        &lt;/dependency&gt;</code> | 关闭 `dependency` 元素，结束对应区域的包含关系。 |
| 33 | <code>        &lt;dependency&gt;</code> | 开始/结束 Maven 配置节点 `<dependency>`，用于组织父工程、依赖或构建插件。 |
| 34 | <code>            &lt;groupId&gt;org.springframework.boot&lt;/groupId&gt;</code> | 设置 Maven 配置项 `groupId` 为 `org.springframework.boot`。 |
| 35 | <code>            &lt;artifactId&gt;spring-boot-starter-security&lt;/artifactId&gt;</code> | 设置 Maven 配置项 `artifactId` 为 `spring-boot-starter-security`。 |
| 36 | <code>        &lt;/dependency&gt;</code> | 关闭 `dependency` 元素，结束对应区域的包含关系。 |
| 37 | <code>        &lt;dependency&gt;</code> | 开始/结束 Maven 配置节点 `<dependency>`，用于组织父工程、依赖或构建插件。 |
| 38 | <code>            &lt;groupId&gt;org.springframework.boot&lt;/groupId&gt;</code> | 设置 Maven 配置项 `groupId` 为 `org.springframework.boot`。 |
| 39 | <code>            &lt;artifactId&gt;spring-boot-starter-jdbc&lt;/artifactId&gt;</code> | 设置 Maven 配置项 `artifactId` 为 `spring-boot-starter-jdbc`。 |
| 40 | <code>        &lt;/dependency&gt;</code> | 关闭 `dependency` 元素，结束对应区域的包含关系。 |
| 41 | <code>        &lt;dependency&gt;</code> | 开始/结束 Maven 配置节点 `<dependency>`，用于组织父工程、依赖或构建插件。 |
| 42 | <code>            &lt;groupId&gt;com.h2database&lt;/groupId&gt;</code> | 设置 Maven 配置项 `groupId` 为 `com.h2database`。 |
| 43 | <code>            &lt;artifactId&gt;h2&lt;/artifactId&gt;</code> | 设置 Maven 配置项 `artifactId` 为 `h2`。 |
| 44 | <code>            &lt;scope&gt;runtime&lt;/scope&gt;</code> | 设置 Maven 配置项 `scope` 为 `runtime`。 |
| 45 | <code>        &lt;/dependency&gt;</code> | 关闭 `dependency` 元素，结束对应区域的包含关系。 |
| 46 | <code>        &lt;dependency&gt;</code> | 开始/结束 Maven 配置节点 `<dependency>`，用于组织父工程、依赖或构建插件。 |
| 47 | <code>            &lt;groupId&gt;com.mysql&lt;/groupId&gt;</code> | 设置 Maven 配置项 `groupId` 为 `com.mysql`。 |
| 48 | <code>            &lt;artifactId&gt;mysql-connector-j&lt;/artifactId&gt;</code> | 设置 Maven 配置项 `artifactId` 为 `mysql-connector-j`。 |
| 49 | <code>            &lt;scope&gt;runtime&lt;/scope&gt;</code> | 设置 Maven 配置项 `scope` 为 `runtime`。 |
| 50 | <code>        &lt;/dependency&gt;</code> | 关闭 `dependency` 元素，结束对应区域的包含关系。 |
| 51 | <code>        &lt;dependency&gt;</code> | 开始/结束 Maven 配置节点 `<dependency>`，用于组织父工程、依赖或构建插件。 |
| 52 | <code>            &lt;groupId&gt;org.springframework.boot&lt;/groupId&gt;</code> | 设置 Maven 配置项 `groupId` 为 `org.springframework.boot`。 |
| 53 | <code>            &lt;artifactId&gt;spring-boot-starter-test&lt;/artifactId&gt;</code> | 设置 Maven 配置项 `artifactId` 为 `spring-boot-starter-test`。 |
| 54 | <code>            &lt;scope&gt;test&lt;/scope&gt;</code> | 设置 Maven 配置项 `scope` 为 `test`。 |
| 55 | <code>        &lt;/dependency&gt;</code> | 关闭 `dependency` 元素，结束对应区域的包含关系。 |
| 56 | <code>        &lt;dependency&gt;</code> | 开始/结束 Maven 配置节点 `<dependency>`，用于组织父工程、依赖或构建插件。 |
| 57 | <code>            &lt;groupId&gt;org.springframework.security&lt;/groupId&gt;</code> | 设置 Maven 配置项 `groupId` 为 `org.springframework.security`。 |
| 58 | <code>            &lt;artifactId&gt;spring-security-test&lt;/artifactId&gt;</code> | 设置 Maven 配置项 `artifactId` 为 `spring-security-test`。 |
| 59 | <code>            &lt;scope&gt;test&lt;/scope&gt;</code> | 设置 Maven 配置项 `scope` 为 `test`。 |
| 60 | <code>        &lt;/dependency&gt;</code> | 关闭 `dependency` 元素，结束对应区域的包含关系。 |
| 61 | <code>    &lt;/dependencies&gt;</code> | 关闭 `dependencies` 元素，结束对应区域的包含关系。 |
| 62 | <code>    &lt;build&gt;</code> | 开始/结束 Maven 配置节点 `<build>`，用于组织父工程、依赖或构建插件。 |
| 63 | <code>        &lt;plugins&gt;</code> | 开始/结束 Maven 配置节点 `<plugins>`，用于组织父工程、依赖或构建插件。 |
| 64 | <code>            &lt;plugin&gt;</code> | 开始/结束 Maven 配置节点 `<plugin>`，用于组织父工程、依赖或构建插件。 |
| 65 | <code>                &lt;groupId&gt;org.springframework.boot&lt;/groupId&gt;</code> | 设置 Maven 配置项 `groupId` 为 `org.springframework.boot`。 |
| 66 | <code>                &lt;artifactId&gt;spring-boot-maven-plugin&lt;/artifactId&gt;</code> | 设置 Maven 配置项 `artifactId` 为 `spring-boot-maven-plugin`。 |
| 67 | <code>            &lt;/plugin&gt;</code> | 关闭 `plugin` 元素，结束对应区域的包含关系。 |
| 68 | <code>        &lt;/plugins&gt;</code> | 关闭 `plugins` 元素，结束对应区域的包含关系。 |
| 69 | <code>    &lt;/build&gt;</code> | 关闭 `build` 元素，结束对应区域的包含关系。 |
| 70 | <code>&lt;/project&gt;</code> | 关闭 `project` 元素，结束对应区域的包含关系。 |
