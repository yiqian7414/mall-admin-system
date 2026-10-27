# backend/src/main/java/com/example/mall/common/BusinessException.java

源文件：[`backend/src/main/java/com/example/mall/common/BusinessException.java`](../../商城管理系统/backend/src/main/java/com/example/mall/common/BusinessException.java)。以下行号以当前文件为准，空行也计入。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>package com.example.mall.common;</code> | 声明 Java 包名 `com.example.mall.common`，用于组织类并区分同名类型。 |
| 2 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 3 | <code>import org.springframework.http.HttpStatus;</code> | 导入 `org.springframework.http.HttpStatus`：使用标准 HTTP 状态码。 |
| 4 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 5 | <code>public class BusinessException extends RuntimeException {</code> | 声明 类 `BusinessException`，后续内容定义它的字段或行为。 |
| 6 | <code>    private final HttpStatus status;</code> | 声明类型为 `HttpStatus` 的 常量/依赖字段 `status`；通过构造器或框架完成赋值。 |
| 7 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 8 | <code>    public BusinessException(HttpStatus status, String message) {</code> | 声明 `BusinessException` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 9 | <code>        super(message);</code> | 调用父类构造器，把错误文字等参数交给父类初始化。 |
| 10 | <code>        this.status = status;</code> | 把构造器传入的依赖/字段保存到当前对象；this.status = status;，后续实例方法通过 this 的成员使用它。 |
| 11 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 12 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 13 | <code>    public HttpStatus status() {</code> | 声明 `status` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 14 | <code>        return status;</code> | 把 `status` 返回给调用方，并结束当前函数/方法。 |
| 15 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 16 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 17 | <code>    public static BusinessException badRequest(String message) {</code> | 声明 `badRequest` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 18 | <code>        return new BusinessException(HttpStatus.BAD_REQUEST, message);</code> | 把 `new BusinessException(HttpStatus.BAD_REQUEST, message)` 返回给调用方，并结束当前函数/方法。 |
| 19 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 20 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 21 | <code>    public static BusinessException notFound(String message) {</code> | 声明 `notFound` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 22 | <code>        return new BusinessException(HttpStatus.NOT_FOUND, message);</code> | 把 `new BusinessException(HttpStatus.NOT_FOUND, message)` 返回给调用方，并结束当前函数/方法。 |
| 23 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 24 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 25 | <code>    public static BusinessException conflict(String message) {</code> | 声明 `conflict` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 26 | <code>        return new BusinessException(HttpStatus.CONFLICT, message);</code> | 把 `new BusinessException(HttpStatus.CONFLICT, message)` 返回给调用方，并结束当前函数/方法。 |
| 27 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 28 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
