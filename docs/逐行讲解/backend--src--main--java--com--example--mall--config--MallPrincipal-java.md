# backend/src/main/java/com/example/mall/config/MallPrincipal.java

源文件：[`backend/src/main/java/com/example/mall/config/MallPrincipal.java`](../../商城管理系统/backend/src/main/java/com/example/mall/config/MallPrincipal.java)。以下行号以当前文件为准，空行也计入。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>package com.example.mall.config;</code> | 声明 Java 包名 `com.example.mall.config`，用于组织类并区分同名类型。 |
| 2 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 3 | <code>import com.example.mall.model.Models.User;</code> | 导入 `com.example.mall.model.Models.User`：让当前类可以直接使用该类型。 |
| 4 | <code>import com.example.mall.model.Models.UserAccount;</code> | 导入 `com.example.mall.model.Models.UserAccount`：让当前类可以直接使用该类型。 |
| 5 | <code>import java.util.Collection;</code> | 导入 `java.util.Collection`：让当前类可以直接使用该类型。 |
| 6 | <code>import java.util.List;</code> | 导入 `java.util.List`：使用有序集合。 |
| 7 | <code>import org.springframework.security.core.GrantedAuthority;</code> | 导入 `org.springframework.security.core.GrantedAuthority`：描述用户授予的权限。 |
| 8 | <code>import org.springframework.security.core.authority.SimpleGrantedAuthority;</code> | 导入 `org.springframework.security.core.authority.SimpleGrantedAuthority`：用字符串构造权限对象。 |
| 9 | <code>import org.springframework.security.core.userdetails.UserDetails;</code> | 导入 `org.springframework.security.core.userdetails.UserDetails`：提供认证框架读取用户信息的接口。 |
| 10 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 11 | <code>public class MallPrincipal implements UserDetails {</code> | 声明 类 `MallPrincipal`，后续内容定义它的字段或行为。 |
| 12 | <code>    private final UserAccount account;</code> | 声明类型为 `UserAccount` 的 常量/依赖字段 `account`；通过构造器或框架完成赋值。 |
| 13 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 14 | <code>    public MallPrincipal(UserAccount account) {</code> | 声明 `MallPrincipal` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 15 | <code>        this.account = account;</code> | 把构造器传入的依赖/字段保存到当前对象；this.account = account;，后续实例方法通过 this 的成员使用它。 |
| 16 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 17 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 18 | <code>    public User user() {</code> | 声明 `user` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 19 | <code>        return account.user();</code> | 把 `account.user()` 返回给调用方，并结束当前函数/方法。 |
| 20 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 21 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 22 | <code>    @Override</code> | 表明方法重写了父类或接口方法，由编译器检查签名。 |
| 23 | <code>    public String getUsername() {</code> | 声明 `getUsername` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 24 | <code>        return account.user().username();</code> | 把 `account.user().username()` 返回给调用方，并结束当前函数/方法。 |
| 25 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 26 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 27 | <code>    @Override</code> | 表明方法重写了父类或接口方法，由编译器检查签名。 |
| 28 | <code>    public String getPassword() {</code> | 声明 `getPassword` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 29 | <code>        return account.passwordHash();</code> | 把 `account.passwordHash()` 返回给调用方，并结束当前函数/方法。 |
| 30 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 31 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 32 | <code>    @Override</code> | 表明方法重写了父类或接口方法，由编译器检查签名。 |
| 33 | <code>    public boolean isEnabled() {</code> | 声明 `isEnabled` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 34 | <code>        return account.user().enabled();</code> | 把 `account.user().enabled()` 返回给调用方，并结束当前函数/方法。 |
| 35 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 36 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 37 | <code>    @Override</code> | 表明方法重写了父类或接口方法，由编译器检查签名。 |
| 38 | <code>    public Collection&lt;? extends GrantedAuthority&gt; getAuthorities() {</code> | 声明 `getAuthorities` 方法/构造器；括号内为调用方传入的参数，花括号内实现该操作。 |
| 39 | <code>        return List.of(new SimpleGrantedAuthority(&quot;ROLE_&quot; + account.user().role()));</code> | 把 `List.of(new SimpleGrantedAuthority("ROLE_" + account.user().role()))` 返回给调用方，并结束当前函数/方法。 |
| 40 | <code>    }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 41 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
