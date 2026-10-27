# backend/src/main/java/com/example/mall/service/ProductImageService.java

源文件：[`backend/src/main/java/com/example/mall/service/ProductImageService.java`](../../商城管理系统/backend/src/main/java/com/example/mall/service/ProductImageService.java)。空行计入行号。

读取真实图片、校验尺寸、重新编码并保存到后端的持久目录。

库存与图片的业务示例、整体流程见 [新功能说明](../库存管理与商品图片.md)。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>package com.example.mall.service;</code> | 声明 Java 包，按控制器、业务、数据访问等职责组织类。 |
| 2 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 3 | <code>import com.example.mall.common.BusinessException;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 4 | <code>import java.io.ByteArrayOutputStream;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 5 | <code>import java.io.IOException;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 6 | <code>import java.nio.file.Files;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 7 | <code>import java.nio.file.Path;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 8 | <code>import java.util.Set;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 9 | <code>import java.util.UUID;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 10 | <code>import javax.imageio.ImageIO;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 11 | <code>import org.springframework.beans.factory.annotation.Value;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 12 | <code>import org.springframework.core.io.FileSystemResource;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 13 | <code>import org.springframework.core.io.Resource;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 14 | <code>import org.springframework.stereotype.Service;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 15 | <code>import org.springframework.web.multipart.MultipartFile;</code> | 导入本文件需要的类型或函数，让下面的代码可以调用它。 |
| 16 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 17 | <code>/** 校验实际图像并重新编码成 PNG；不信任原文件名、后缀或客户端 MIME。 */</code> | 代码注释，说明设计意图，不直接执行。 |
| 18 | <code>@Service</code> | 向 Spring 或 Java 声明本行对应类/方法的用途，结合后面的定义生效。 |
| 19 | <code>public class ProductImageService {</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 20 | <code>    private final Path directory;</code> | 声明构造后不再替换的依赖字段，由构造器接收 Spring 管理的对象。 |
| 21 | <code>    public ProductImageService(@Value(&quot;${mall.upload-directory:./data/uploads/products}&quot;) String directory) {</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 22 | <code>        this.directory = Path.of(directory).toAbsolutePath().normalize();</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 23 | <code>    }</code> | 结束当前作用域、参数、回调或集合定义。 |
| 24 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 25 | <code>    public String upload(MultipartFile file) throws IOException {</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 26 | <code>        if (file.isEmpty() &#124;&#124; file.getSize() &gt; 5L * 1024 * 1024)</code> | 判断本行条件，只有成立时才进入对应分支；结合下一行看成功、拒绝或提前返回的处理。 |
| 27 | <code>            throw BusinessException.badRequest(&quot;请选择不超过 5 MB 的商品图片&quot;);</code> | 拒绝不满足业务规则的操作，返回中文原因；处于事务内的数据库变更会回滚。 |
| 28 | <code>        try (var source = file.getInputStream(); var input = ImageIO.createImageInputStream(source)) {</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 29 | <code>            var readers = ImageIO.getImageReaders(input);</code> | 根据实际文件内容寻找图片解码器，不依赖浏览器声称的 MIME 或文件后缀。 |
| 30 | <code>            if (!readers.hasNext()) throw BusinessException.badRequest(&quot;文件不是有效的 JPG、PNG 或 WebP 图片&quot;);</code> | 拒绝不满足业务规则的操作，返回中文原因；处于事务内的数据库变更会回滚。 |
| 31 | <code>            var reader = readers.next();</code> | 声明本地变量或状态引用，保存后续代码要使用的值。 |
| 32 | <code>            try {</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 33 | <code>                if (!Set.of(&quot;jpeg&quot;, &quot;jpg&quot;, &quot;png&quot;, &quot;webp&quot;).contains(reader.getFormatName().toLowerCase(java.util.Locale.ROOT)))</code> | 判断本行条件，只有成立时才进入对应分支；结合下一行看成功、拒绝或提前返回的处理。 |
| 34 | <code>                    throw BusinessException.badRequest(&quot;仅支持 JPG、PNG、WebP 图片&quot;);</code> | 拒绝不满足业务规则的操作，返回中文原因；处于事务内的数据库变更会回滚。 |
| 35 | <code>                reader.setInput(input, true, true);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 36 | <code>                int width = reader.getWidth(0), height = reader.getHeight(0);</code> | 先读取图片尺寸，在解码完整像素之前检查尺寸限制，减少超大图片的内存风险。 |
| 37 | <code>                if (width &lt; 1 &#124;&#124; height &lt; 1 &#124;&#124; width &gt; 4096 &#124;&#124; height &gt; 4096)</code> | 判断本行条件，只有成立时才进入对应分支；结合下一行看成功、拒绝或提前返回的处理。 |
| 38 | <code>                    throw BusinessException.badRequest(&quot;图片宽高均须为 1～4096 像素&quot;);</code> | 拒绝不满足业务规则的操作，返回中文原因；处于事务内的数据库变更会回滚。 |
| 39 | <code>                var image = reader.read(0);</code> | 解码第 0 帧图像；当前功能保存单张商品主图，不保留动画帧。 |
| 40 | <code>                var output = new ByteArrayOutputStream();</code> | 声明本地变量或状态引用，保存后续代码要使用的值。 |
| 41 | <code>                if (!ImageIO.write(image, &quot;png&quot;, output) &#124;&#124; output.size() &gt; 20 * 1024 * 1024)</code> | 把解码得到的像素重新编码成 PNG，统一输出格式，不直接保存不可信原文件。 |
| 42 | <code>                    throw BusinessException.badRequest(&quot;图片处理后过大，请压缩后重试&quot;);</code> | 拒绝不满足业务规则的操作，返回中文原因；处于事务内的数据库变更会回滚。 |
| 43 | <code>                Files.createDirectories(directory);</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 44 | <code>                String filename = UUID.randomUUID() + &quot;.png&quot;;</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 45 | <code>                Files.write(directory.resolve(filename), output.toByteArray(), java.nio.file.StandardOpenOption.CREATE_NEW);</code> | 只创建新的随机文件，已有同名文件不会被覆盖。 |
| 46 | <code>                return &quot;/api/product-images/&quot; + filename;</code> | 返回本行表达式的结果，交给调用方或作为接口响应。 |
| 47 | <code>            } finally { reader.dispose(); }</code> | 释放图片解码器使用的资源；finally 确保失败时也执行。 |
| 48 | <code>        } catch (javax.imageio.IIOException &#124; IllegalArgumentException e) {</code> | 捕获对应异常，把失败消息显示给用户或转换成接口错误。 |
| 49 | <code>            throw BusinessException.badRequest(&quot;图片内容损坏或格式不受支持，请重新选择图片&quot;);</code> | 拒绝不满足业务规则的操作，返回中文原因；处于事务内的数据库变更会回滚。 |
| 50 | <code>        }</code> | 结束当前作用域、参数、回调或集合定义。 |
| 51 | <code>    }</code> | 结束当前作用域、参数、回调或集合定义。 |
| 52 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 53 | <code>    public void validateReference(String url) {</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 54 | <code>        if (url == null &#124;&#124; url.isBlank()) return;</code> | 判断本行条件，只有成立时才进入对应分支；结合下一行看成功、拒绝或提前返回的处理。 |
| 55 | <code>        if (!url.matches(&quot;/api/product-images/[a-f0-9-]{36}\\.png&quot;) &#124;&#124; !Files.isRegularFile(directory.resolve(url.substring(url.lastIndexOf(&#x27;/&#x27;) + 1))))</code> | 判断本行条件，只有成立时才进入对应分支；结合下一行看成功、拒绝或提前返回的处理。 |
| 56 | <code>            throw BusinessException.badRequest(&quot;商品图片不存在，请重新上传&quot;);</code> | 拒绝不满足业务规则的操作，返回中文原因；处于事务内的数据库变更会回滚。 |
| 57 | <code>    }</code> | 结束当前作用域、参数、回调或集合定义。 |
| 58 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 59 | <code>    public Resource read(String filename) {</code> | 声明类、方法或数据结构；参数、返回类型和后面的代码块共同规定其行为。 |
| 60 | <code>        if (!filename.matches(&quot;[a-f0-9-]{36}\\.png&quot;)) throw BusinessException.notFound(&quot;图片不存在&quot;);</code> | 拒绝不满足业务规则的操作，返回中文原因；处于事务内的数据库变更会回滚。 |
| 61 | <code>        Path file = directory.resolve(filename).normalize();</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 62 | <code>        if (!file.startsWith(directory) &#124;&#124; !Files.isRegularFile(file)) throw BusinessException.notFound(&quot;图片不存在&quot;);</code> | 拒绝不满足业务规则的操作，返回中文原因；处于事务内的数据库变更会回滚。 |
| 63 | <code>        return new FileSystemResource(file);</code> | 返回本行表达式的结果，交给调用方或作为接口响应。 |
| 64 | <code>    }</code> | 结束当前作用域、参数、回调或集合定义。 |
| 65 | <code>}</code> | 结束当前作用域、参数、回调或集合定义。 |
