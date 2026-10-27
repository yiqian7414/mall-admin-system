# backend/src/main/java/com/example/mall/common/ApiExceptionHandler.java

源文件：[`backend/src/main/java/com/example/mall/common/ApiExceptionHandler.java`](../../商城管理系统/backend/src/main/java/com/example/mall/common/ApiExceptionHandler.java)。空行计入行号。

本文件按当前源码重新校对行号；未改变代码的已有讲解予以保留。

库存与图片的业务示例、整体流程见 [新功能说明](../库存管理与商品图片.md)。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>package com.example.mall.common;</code> | 声明 Java 包名 `com.example.mall.common`，用于组织类并区分同名类型。 |
| 2 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 3 | <code>import jakarta.validation.ConstraintViolationException;</code> | 导入 `jakarta.validation.ConstraintViolationException`：让当前类可以直接使用该类型。 |
| 4 | <code>import java.util.Map;</code> | 导入 `java.util.Map`：使用键值映射。 |
| 5 | <code>import org.slf4j.Logger;</code> | 导入 `org.slf4j.Logger`：让当前类可以直接使用该类型。 |
| 6 | <code>import org.slf4j.LoggerFactory;</code> | 导入 `org.slf4j.LoggerFactory`：让当前类可以直接使用该类型。 |
| 7 | <code>import org.springframework.dao.DataIntegrityViolationException;</code> | 导入 `org.springframework.dao.DataIntegrityViolationException`：捕获数据库唯一键/外键等约束失败。 |
| 8 | <code>import org.springframework.dao.PessimisticLockingFailureException;</code> | 导入 `org.springframework.dao.PessimisticLockingFailureException`：让当前类可以直接使用该类型。 |
| 9 | <code>import org.springframework.http.HttpStatus;</code> | 导入 `org.springframework.http.HttpStatus`：使用标准 HTTP 状态码。 |
| 10 | <code>import org.springframework.http.ResponseEntity;</code> | 导入 `org.springframework.http.ResponseEntity`：同时控制响应体和 HTTP 状态。 |
| 11 | <code>import org.springframework.http.converter.HttpMessageNotReadableException;</code> | 导入 `org.springframework.http.converter.HttpMessageNotReadableException`：让当前类可以直接使用该类型。 |
| 12 | <code>import org.springframework.web.bind.MethodArgumentNotValidException;</code> | 导入 `org.springframework.web.bind.MethodArgumentNotValidException`：捕获请求 DTO 校验失败。 |
| 13 | <code>import org.springframework.web.HttpRequestMethodNotSupportedException;</code> | 导入 `org.springframework.web.HttpRequestMethodNotSupportedException`：让当前类可以直接使用该类型。 |
| 14 | <code>import org.springframework.web.bind.annotation.ExceptionHandler;</code> | 导入 `org.springframework.web.bind.annotation.ExceptionHandler`：为指定异常提供统一响应。 |
| 15 | <code>import org.springframework.web.bind.annotation.RestControllerAdvice;</code> | 导入 `org.springframework.web.bind.annotation.RestControllerAdvice`：集中处理控制器异常。 |
| 16 | <code>import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;</code> | 导入 `org.springframework.web.method.annotation.MethodArgumentTypeMismatchException`：让当前类可以直接使用该类型。 |
| 17 | <code>import org.springframework.web.servlet.resource.NoResourceFoundException;</code> | 导入 `org.springframework.web.servlet.resource.NoResourceFoundException`：让当前类可以直接使用该类型。 |
| 18 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 19 | <code>@RestControllerAdvice</code> | 注册全局异常响应处理器。 |
| 20 | <code>public class ApiExceptionHandler {</code> | 声明 类 `ApiExceptionHandler`，后续内容定义它的字段或行为。 |
| 21 | <code>    private static final Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);</code> | 声明类型为 `Logger` 的 常量/依赖字段 `log`，初始值为 `LoggerFactory.getLogger(ApiExceptionHandler.class)`。 |
| 22 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 23 | <code>    @ExceptionHandler(org.springframework.web.multipart.MaxUploadSizeExceededException.class)</code> | 向 Spring 或 Java 声明本行对应类/方法的用途，结合后面的定义生效。 |
| 24 | <code>    ResponseEntity&lt;Map&lt;String, String&gt;&gt; uploadTooLarge(Exception exception) {</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 25 | <code>        return response(HttpStatus.PAYLOAD_TOO_LARGE, &quot;图片不能超过 5 MB&quot;);</code> | 返回本行表达式的结果，交给调用方或作为接口响应。 |
| 26 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 27 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 28 | <code>    @ExceptionHandler({org.springframework.web.bind.MissingServletRequestParameterException.class,</code> | 向 Spring 或 Java 声明本行对应类/方法的用途，结合后面的定义生效。 |
| 29 | <code>            org.springframework.web.multipart.support.MissingServletRequestPartException.class})</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 30 | <code>    ResponseEntity&lt;Map&lt;String, String&gt;&gt; missingParameter(Exception exception) {</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 31 | <code>        return response(HttpStatus.BAD_REQUEST, &quot;缺少必要参数，请检查输入&quot;);</code> | 返回本行表达式的结果，交给调用方或作为接口响应。 |
| 32 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 33 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 34 | <code>    @ExceptionHandler(BusinessException.class)</code> | 由下方方法处理括号中列出的异常。括号内参数为 `BusinessException.class`。 |
| 35 | <code>    ResponseEntity&lt;Map&lt;String, String&gt;&gt; business(BusinessException exception) {</code> | 声明返回类型为 `ResponseEntity<Map<String, String>>` 的方法 `business`；用于处理 `business` 对应的请求/错误。本行开始参数声明，后续可能继续分行。 |
| 36 | <code>        return response(exception.status(), exception.getMessage());</code> | 把 `response(exception.status(), exception.getMessage())` 返回给调用方，并结束当前函数/方法。 |
| 37 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 38 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 39 | <code>    @ExceptionHandler(MethodArgumentNotValidException.class)</code> | 由下方方法处理括号中列出的异常。括号内参数为 `MethodArgumentNotValidException.class`。 |
| 40 | <code>    ResponseEntity&lt;Map&lt;String, String&gt;&gt; invalid(MethodArgumentNotValidException exception) {</code> | 声明返回类型为 `ResponseEntity<Map<String, String>>` 的方法 `invalid`；用于处理 `invalid` 对应的请求/错误。本行开始参数声明，后续可能继续分行。 |
| 41 | <code>        var errors = exception.getBindingResult().getAllErrors();</code> | 让 Java 编译器根据右侧推断局部变量 `errors` 的类型，值来自 `exception.getBindingResult().getAllErrors()`。 |
| 42 | <code>        return response(HttpStatus.BAD_REQUEST, errors.isEmpty() ? &quot;请求参数无效&quot; : errors.get(0).getDefaultMessage());</code> | 把 `response(HttpStatus.BAD_REQUEST, errors.isEmpty() ? "请求参数无效" : errors.get(0).getDefaultMessage())` 返回给调用方，并结束当前函数/方法。 |
| 43 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 44 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 45 | <code>    @ExceptionHandler({ HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class,</code> | 由下方方法处理括号中列出的异常。括号内参数为 `{ HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class`。 |
| 46 | <code>            ConstraintViolationException.class })</code> | 继续前面的异常列表，包含方法/字段约束校验异常；这些异常统一转为 400 输入错误。 |
| 47 | <code>    ResponseEntity&lt;Map&lt;String, String&gt;&gt; malformed(Exception exception) {</code> | 声明返回类型为 `ResponseEntity<Map<String, String>>` 的方法 `malformed`；用于处理 `malformed` 对应的请求/错误。本行开始参数声明，后续可能继续分行。 |
| 48 | <code>        return response(HttpStatus.BAD_REQUEST, &quot;请求格式或参数无效，请检查输入内容&quot;);</code> | 把 `response(HttpStatus.BAD_REQUEST, "请求格式或参数无效，请检查输入内容")` 返回给调用方，并结束当前函数/方法。 |
| 49 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 50 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 51 | <code>    @ExceptionHandler(DataIntegrityViolationException.class)</code> | 由下方方法处理括号中列出的异常。括号内参数为 `DataIntegrityViolationException.class`。 |
| 52 | <code>    ResponseEntity&lt;Map&lt;String, String&gt;&gt; integrity(DataIntegrityViolationException exception) {</code> | 声明返回类型为 `ResponseEntity<Map<String, String>>` 的方法 `integrity`；用于处理 `integrity` 对应的请求/错误。本行开始参数声明，后续可能继续分行。 |
| 53 | <code>        return response(HttpStatus.CONFLICT, &quot;名称或编号已存在，或该记录仍被其他数据引用&quot;);</code> | 把 `response(HttpStatus.CONFLICT, "名称或编号已存在，或该记录仍被其他数据引用")` 返回给调用方，并结束当前函数/方法。 |
| 54 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 55 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 56 | <code>    @ExceptionHandler(PessimisticLockingFailureException.class)</code> | 由下方方法处理括号中列出的异常。括号内参数为 `PessimisticLockingFailureException.class`。 |
| 57 | <code>    ResponseEntity&lt;Map&lt;String, String&gt;&gt; lock(PessimisticLockingFailureException exception) {</code> | 声明返回类型为 `ResponseEntity<Map<String, String>>` 的方法 `lock`；用于处理 `lock` 对应的请求/错误。本行开始参数声明，后续可能继续分行。 |
| 58 | <code>        return response(HttpStatus.CONFLICT, &quot;相关数据正在被其他操作修改，请稍后重试&quot;);</code> | 把 `response(HttpStatus.CONFLICT, "相关数据正在被其他操作修改，请稍后重试")` 返回给调用方，并结束当前函数/方法。 |
| 59 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 60 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 61 | <code>    @ExceptionHandler(NoResourceFoundException.class)</code> | 由下方方法处理括号中列出的异常。括号内参数为 `NoResourceFoundException.class`。 |
| 62 | <code>    ResponseEntity&lt;Map&lt;String, String&gt;&gt; missing(NoResourceFoundException exception) {</code> | 声明返回类型为 `ResponseEntity<Map<String, String>>` 的方法 `missing`；用于处理 `missing` 对应的请求/错误。本行开始参数声明，后续可能继续分行。 |
| 63 | <code>        return response(HttpStatus.NOT_FOUND, &quot;接口不存在&quot;);</code> | 把 `response(HttpStatus.NOT_FOUND, "接口不存在")` 返回给调用方，并结束当前函数/方法。 |
| 64 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 65 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 66 | <code>    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)</code> | 由下方方法处理括号中列出的异常。括号内参数为 `HttpRequestMethodNotSupportedException.class`。 |
| 67 | <code>    ResponseEntity&lt;Map&lt;String, String&gt;&gt; unsupportedMethod(HttpRequestMethodNotSupportedException exception) {</code> | 声明返回类型为 `ResponseEntity<Map<String, String>>` 的方法 `unsupportedMethod`；用于处理 `unsupportedMethod` 对应的请求/错误。本行开始参数声明，后续可能继续分行。 |
| 68 | <code>        return response(HttpStatus.METHOD_NOT_ALLOWED, &quot;该接口不支持此请求方式&quot;);</code> | 把 `response(HttpStatus.METHOD_NOT_ALLOWED, "该接口不支持此请求方式")` 返回给调用方，并结束当前函数/方法。 |
| 69 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 70 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 71 | <code>    @ExceptionHandler(Exception.class)</code> | 由下方方法处理括号中列出的异常。括号内参数为 `Exception.class`。 |
| 72 | <code>    ResponseEntity&lt;Map&lt;String, String&gt;&gt; unexpected(Exception exception) {</code> | 声明返回类型为 `ResponseEntity<Map<String, String>>` 的方法 `unexpected`；用于处理 `unexpected` 对应的请求/错误。本行开始参数声明，后续可能继续分行。 |
| 73 | <code>        log.error(&quot;处理请求时发生异常&quot;, exception);</code> | 在后端日志中记录完整异常供排查；返回前端的提示由异常处理器统一控制。 |
| 74 | <code>        return response(HttpStatus.INTERNAL_SERVER_ERROR, &quot;服务器处理失败，请稍后重试&quot;);</code> | 把 `response(HttpStatus.INTERNAL_SERVER_ERROR, "服务器处理失败，请稍后重试")` 返回给调用方，并结束当前函数/方法。 |
| 75 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 76 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 77 | <code>    private ResponseEntity&lt;Map&lt;String, String&gt;&gt; response(HttpStatus status, String message) {</code> | 声明 `response` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 78 | <code>        return ResponseEntity.status(status).body(Map.of(&quot;message&quot;, message));</code> | 把 `ResponseEntity.status(status).body(Map.of("message", message))` 返回给调用方，并结束当前函数/方法。 |
| 79 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 80 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
