# backend/src/main/resources/schema.sql

源文件：[`backend/src/main/resources/schema.sql`](../../商城管理系统/backend/src/main/resources/schema.sql)。空行计入行号。

本文件按当前源码重新校对行号；未改变代码的已有讲解予以保留。

库存与图片的业务示例、整体流程见 [新功能说明](../库存管理与商品图片.md)。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>-- H2 MySQL 模式与 MySQL 8 共用的建表语句。已有表和数据不会被删除。</code> | SQL 注释：H2 MySQL 模式与 MySQL 8 共用的建表语句。已有表和数据不会被删除。。 |
| 2 | <code>CREATE TABLE IF NOT EXISTS app_setting (</code> | 创建数据库表 CREATE TABLE IF NOT EXISTS app_setting (；IF NOT EXISTS 让已经建表的数据库保留原表。 |
| 3 | <code>    setting_key VARCHAR(80) PRIMARY KEY,</code> | 定义字段 `setting_key`，类型为 `VARCHAR(80)`；PRIMARY KEY 作为记录唯一标识。 |
| 4 | <code>    setting_value VARCHAR(255) NOT NULL</code> | 定义字段 `setting_value`，类型为 `VARCHAR(255)`；NOT NULL 要求必须有值。 |
| 5 | <code>);</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 6 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 7 | <code>CREATE TABLE IF NOT EXISTS mall_user (</code> | 创建数据库表 CREATE TABLE IF NOT EXISTS mall_user (；IF NOT EXISTS 让已经建表的数据库保留原表。 |
| 8 | <code>    id BIGINT AUTO_INCREMENT PRIMARY KEY,</code> | 定义字段 `id`，类型为 `BIGINT`；PRIMARY KEY 作为记录唯一标识。 |
| 9 | <code>    username VARCHAR(30) NOT NULL UNIQUE,</code> | 定义字段 `username`，类型为 `VARCHAR(30)`；NOT NULL 要求必须有值。 |
| 10 | <code>    display_name VARCHAR(60) NOT NULL,</code> | 定义字段 `display_name`，类型为 `VARCHAR(60)`；NOT NULL 要求必须有值。 |
| 11 | <code>    password_hash VARCHAR(100) NOT NULL,</code> | 定义字段 `password_hash`，类型为 `VARCHAR(100)`；NOT NULL 要求必须有值。 |
| 12 | <code>    role VARCHAR(10) NOT NULL,</code> | 定义字段 `role`，类型为 `VARCHAR(10)`；NOT NULL 要求必须有值。 |
| 13 | <code>    enabled BOOLEAN NOT NULL DEFAULT TRUE</code> | 定义字段 `enabled`，类型为 `BOOLEAN`；NOT NULL 要求必须有值；DEFAULT 指定未提供时的初始值。 |
| 14 | <code>);</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 15 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 16 | <code>CREATE TABLE IF NOT EXISTS category (</code> | 创建数据库表 CREATE TABLE IF NOT EXISTS category (；IF NOT EXISTS 让已经建表的数据库保留原表。 |
| 17 | <code>    id BIGINT AUTO_INCREMENT PRIMARY KEY,</code> | 定义字段 `id`，类型为 `BIGINT`；PRIMARY KEY 作为记录唯一标识。 |
| 18 | <code>    name VARCHAR(60) NOT NULL UNIQUE,</code> | 定义字段 `name`，类型为 `VARCHAR(60)`；NOT NULL 要求必须有值。 |
| 19 | <code>    description VARCHAR(500) NOT NULL DEFAULT &#x27;&#x27;</code> | 定义字段 `description`，类型为 `VARCHAR(500)`；NOT NULL 要求必须有值；DEFAULT 指定未提供时的初始值。 |
| 20 | <code>);</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 21 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 22 | <code>CREATE TABLE IF NOT EXISTS product (</code> | 创建数据库表 CREATE TABLE IF NOT EXISTS product (；IF NOT EXISTS 让已经建表的数据库保留原表。 |
| 23 | <code>    id BIGINT AUTO_INCREMENT PRIMARY KEY,</code> | 定义字段 `id`，类型为 `BIGINT`；PRIMARY KEY 作为记录唯一标识。 |
| 24 | <code>    name VARCHAR(100) NOT NULL,</code> | 定义字段 `name`，类型为 `VARCHAR(100)`；NOT NULL 要求必须有值。 |
| 25 | <code>    sku VARCHAR(50) NOT NULL UNIQUE,</code> | 定义字段 `sku`，类型为 `VARCHAR(50)`；NOT NULL 要求必须有值。 |
| 26 | <code>    category_id BIGINT NOT NULL,</code> | 定义字段 `category_id`，类型为 `BIGINT`；NOT NULL 要求必须有值。 |
| 27 | <code>    price DECIMAL(12, 2) NOT NULL,</code> | 定义字段 `price`，类型为 `DECIMAL(12, 2)`；NOT NULL 要求必须有值。 |
| 28 | <code>    stock INT NOT NULL,</code> | 定义字段 `stock`，类型为 `INT`；NOT NULL 要求必须有值。 |
| 29 | <code>    status VARCHAR(20) NOT NULL,</code> | 定义字段 `status`，类型为 `VARCHAR(20)`；NOT NULL 要求必须有值。 |
| 30 | <code>    description VARCHAR(1000) NOT NULL DEFAULT &#x27;&#x27;,</code> | 定义字段 `description`，类型为 `VARCHAR(1000)`；NOT NULL 要求必须有值；DEFAULT 指定未提供时的初始值。 |
| 31 | <code>    image_url VARCHAR(255) NOT NULL DEFAULT &#x27;&#x27;,</code> | 为商品增加可选图片地址字段，空字符串代表没有上传主图。 |
| 32 | <code>    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,</code> | 定义字段 `created_at`，类型为 `TIMESTAMP`；NOT NULL 要求必须有值；DEFAULT 指定未提供时的初始值。 |
| 33 | <code>    CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES category(id)</code> | 定义数据库约束 `CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES category(id)`，由数据库保证关联或数据合法性。 |
| 34 | <code>);</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 35 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 36 | <code>CREATE TABLE IF NOT EXISTS mall_order (</code> | 创建数据库表 CREATE TABLE IF NOT EXISTS mall_order (；IF NOT EXISTS 让已经建表的数据库保留原表。 |
| 37 | <code>    id BIGINT AUTO_INCREMENT PRIMARY KEY,</code> | 定义字段 `id`，类型为 `BIGINT`；PRIMARY KEY 作为记录唯一标识。 |
| 38 | <code>    order_no VARCHAR(50) NOT NULL UNIQUE,</code> | 定义字段 `order_no`，类型为 `VARCHAR(50)`；NOT NULL 要求必须有值。 |
| 39 | <code>    customer_name VARCHAR(100) NOT NULL,</code> | 定义字段 `customer_name`，类型为 `VARCHAR(100)`；NOT NULL 要求必须有值。 |
| 40 | <code>    phone VARCHAR(30) NOT NULL,</code> | 定义字段 `phone`，类型为 `VARCHAR(30)`；NOT NULL 要求必须有值。 |
| 41 | <code>    address VARCHAR(300) NOT NULL,</code> | 定义字段 `address`，类型为 `VARCHAR(300)`；NOT NULL 要求必须有值。 |
| 42 | <code>    status VARCHAR(20) NOT NULL,</code> | 定义字段 `status`，类型为 `VARCHAR(20)`；NOT NULL 要求必须有值。 |
| 43 | <code>    total_amount DECIMAL(18, 2) NOT NULL,</code> | 定义字段 `total_amount`，类型为 `DECIMAL(18, 2)`；NOT NULL 要求必须有值。 |
| 44 | <code>    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP</code> | 定义字段 `created_at`，类型为 `TIMESTAMP`；NOT NULL 要求必须有值；DEFAULT 指定未提供时的初始值。 |
| 45 | <code>);</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 46 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 47 | <code>CREATE TABLE IF NOT EXISTS order_item (</code> | 创建数据库表 CREATE TABLE IF NOT EXISTS order_item (；IF NOT EXISTS 让已经建表的数据库保留原表。 |
| 48 | <code>    id BIGINT AUTO_INCREMENT PRIMARY KEY,</code> | 定义字段 `id`，类型为 `BIGINT`；PRIMARY KEY 作为记录唯一标识。 |
| 49 | <code>    order_id BIGINT NOT NULL,</code> | 定义字段 `order_id`，类型为 `BIGINT`；NOT NULL 要求必须有值。 |
| 50 | <code>    product_id BIGINT NOT NULL,</code> | 定义字段 `product_id`，类型为 `BIGINT`；NOT NULL 要求必须有值。 |
| 51 | <code>    product_name VARCHAR(100) NOT NULL,</code> | 定义字段 `product_name`，类型为 `VARCHAR(100)`；NOT NULL 要求必须有值。 |
| 52 | <code>    unit_price DECIMAL(12, 2) NOT NULL,</code> | 定义字段 `unit_price`，类型为 `DECIMAL(12, 2)`；NOT NULL 要求必须有值。 |
| 53 | <code>    quantity INT NOT NULL,</code> | 定义字段 `quantity`，类型为 `INT`；NOT NULL 要求必须有值。 |
| 54 | <code>    line_total DECIMAL(18, 2) NOT NULL,</code> | 定义字段 `line_total`，类型为 `DECIMAL(18, 2)`；NOT NULL 要求必须有值。 |
| 55 | <code>    CONSTRAINT fk_item_order FOREIGN KEY (order_id) REFERENCES mall_order(id),</code> | 定义数据库约束 `CONSTRAINT fk_item_order FOREIGN KEY (order_id) REFERENCES mall_order(id),`，由数据库保证关联或数据合法性。 |
| 56 | <code>    CONSTRAINT fk_item_product FOREIGN KEY (product_id) REFERENCES product(id)</code> | 定义数据库约束 `CONSTRAINT fk_item_product FOREIGN KEY (product_id) REFERENCES product(id)`，由数据库保证关联或数据合法性。 |
| 57 | <code>);</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 58 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 59 | <code>CREATE TABLE IF NOT EXISTS stock_movement (</code> | 定义数据库结构或约束；建表使用 IF NOT EXISTS，避免删除已有表和数据。 |
| 60 | <code>    id BIGINT AUTO_INCREMENT PRIMARY KEY,</code> | 定义字段 `id`，类型为 `BIGINT`；PRIMARY KEY 作为记录唯一标识。 |
| 61 | <code>    product_id BIGINT NOT NULL,</code> | 定义字段 `product_id`，类型为 `BIGINT`；NOT NULL 要求必须有值。 |
| 62 | <code>    product_name VARCHAR(100) NOT NULL,</code> | 定义字段 `product_name`，类型为 `VARCHAR(100)`；NOT NULL 要求必须有值。 |
| 63 | <code>    sku VARCHAR(50) NOT NULL,</code> | 定义数据库结构或约束；建表使用 IF NOT EXISTS，避免删除已有表和数据。 |
| 64 | <code>    type VARCHAR(30) NOT NULL,</code> | 定义数据库结构或约束；建表使用 IF NOT EXISTS，避免删除已有表和数据。 |
| 65 | <code>    delta INT NOT NULL,</code> | 定义数据库结构或约束；建表使用 IF NOT EXISTS，避免删除已有表和数据。 |
| 66 | <code>    before_stock INT NOT NULL,</code> | 定义数据库结构或约束；建表使用 IF NOT EXISTS，避免删除已有表和数据。 |
| 67 | <code>    after_stock INT NOT NULL,</code> | 定义数据库结构或约束；建表使用 IF NOT EXISTS，避免删除已有表和数据。 |
| 68 | <code>    reference VARCHAR(80) NOT NULL,</code> | 定义数据库结构或约束；建表使用 IF NOT EXISTS，避免删除已有表和数据。 |
| 69 | <code>    reason VARCHAR(80) NOT NULL,</code> | 定义数据库结构或约束；建表使用 IF NOT EXISTS，避免删除已有表和数据。 |
| 70 | <code>    remark VARCHAR(200) NOT NULL DEFAULT &#x27;&#x27;,</code> | 定义数据库结构或约束；建表使用 IF NOT EXISTS，避免删除已有表和数据。 |
| 71 | <code>    operator VARCHAR(60) NOT NULL,</code> | 定义数据库结构或约束；建表使用 IF NOT EXISTS，避免删除已有表和数据。 |
| 72 | <code>    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,</code> | 定义字段 `created_at`，类型为 `TIMESTAMP`；NOT NULL 要求必须有值；DEFAULT 指定未提供时的初始值。 |
| 73 | <code>    CONSTRAINT fk_movement_product FOREIGN KEY (product_id) REFERENCES product(id),</code> | 定义数据库结构或约束；建表使用 IF NOT EXISTS，避免删除已有表和数据。 |
| 74 | <code>    CONSTRAINT uk_movement_reference UNIQUE (product_id, type, reference)</code> | 定义数据库结构或约束；建表使用 IF NOT EXISTS，避免删除已有表和数据。 |
| 75 | <code>);</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
