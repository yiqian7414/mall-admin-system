# frontend-vue3/src/api.js

源文件：[`frontend-vue3/src/api.js`](../../商城管理系统/frontend-vue3/src/api.js)。空行计入行号。

本文件按当前源码重新校对行号；未改变代码的已有讲解予以保留。

库存与图片的业务示例、整体流程见 [新功能说明](../库存管理与商品图片.md)。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>// 统一 JSON、Session Cookie、CSRF 与错误处理。</code> | 注释：统一 JSON、Session Cookie、CSRF 与错误处理。。此行用于解释代码，不会执行。 |
| 2 | <code>let csrfToken = &quot;&quot;;</code> | 模块内缓存当前会话令牌；不放进 localStorage，登录/退出后会主动清空并重新取得。 |
| 3 | <code>let csrfRequest = null;</code> | 保存正在获取 CSRF 的 Promise；多个并发写请求复用它，避免同时发出多次令牌请求。 |
| 4 | <code>let onUnauthorized = () =&gt; {};</code> | 预置一个空的 401 回调；session.js 随后注册清理用户与跳转登录的实际逻辑。 |
| 5 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 6 | <code>export function setUnauthorizedHandler(handler) {</code> | 导出回调注册函数，使请求模块不用直接导入会话模块，避免循环依赖。 |
| 7 | <code>  onUnauthorized = handler;</code> | 保存会话模块提供的 401 处理函数，后续业务请求失效时调用。 |
| 8 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 9 | <code>export function clearCsrfToken() {</code> | 导出清除旧令牌的函数，登录/退出改变会话时由 session.js 调用。 |
| 10 | <code>  csrfToken = &quot;&quot;;</code> | 使下一次写请求必须重新取得令牌，防止继续发送旧会话的 CSRF。 |
| 11 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 12 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 13 | <code>export async function refreshCsrfToken() {</code> | 定义异步令牌刷新函数；返回可等待的 Promise，成功值为 token。 |
| 14 | <code>  if (!csrfRequest) {</code> | 只有当前没有获取请求时才发起请求；已有 Promise 时跳到下方直接复用。 |
| 15 | <code>    csrfRequest = fetch(&quot;/api/auth/csrf&quot;, { credentials: &quot;include&quot; })</code> | 向后端读取当前会话令牌，并允许请求携带/接收 Cookie；把 Promise 保存为共享中的 csrfRequest。 |
| 16 | <code>      .then(async (response) =&gt; {</code> | 令牌 HTTP 请求完成后执行异步回调；接下来检查状态并等待 JSON 解析。 |
| 17 | <code>        if (!response.ok) throw new Error(&quot;无法获取安全令牌，请检查后台服务。&quot;);</code> | fetch 遇到 4xx/5xx 不会自动抛错，必须检查 ok；失败则终止后面的写请求。 |
| 18 | <code>        const data = await response.json();</code> | 等待响应 JSON 解析，得到后端 {token: ...} 对象。 |
| 19 | <code>        csrfToken = data.token;</code> | 把本次响应 token 缓存到模块变量，后续写请求使用它。 |
| 20 | <code>        if (!csrfToken) throw new Error(&quot;后台没有返回安全令牌。&quot;);</code> | 拒绝缺失或空 token 的异常响应，避免继续发送无效写请求。 |
| 21 | <code>        return csrfToken;</code> | 把取得的令牌作为这个 Promise 的成功结果。 |
| 22 | <code>      })</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 23 | <code>      .finally(() =&gt; {</code> | 无论令牌获取成功还是失败，都运行下面的在途请求清理。 |
| 24 | <code>        csrfRequest = null;</code> | 清空在途 Promise，让以后需要时能够发起新的令牌获取；已取得的 token 缓存仍保留。 |
| 25 | <code>      });</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 26 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 27 | <code>  return csrfRequest;</code> | 把同一个在途 Promise 返回给所有调用者，达到并发请求合并的效果。 |
| 28 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 29 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 30 | <code>export async function api(path, { method = &quot;GET&quot;, body, form = false } = {}) {</code> | 导出统一请求函数：path 是 /api 后的路径，配置默认 GET、无正文、JSON；解构默认 {} 允许只传路径。 |
| 31 | <code>  const multipart = body instanceof FormData;</code> | 识别当前请求是否为文件上传；multipart 请求的 Content-Type 和边界交给浏览器生成。 |
| 32 | <code>  const headers = { Accept: &quot;application/json&quot; };</code> | 声明希望接收 JSON 响应；Content-Type 会按是否有正文及是否表单另行添加。 |
| 33 | <code>  if (![&quot;GET&quot;, &quot;HEAD&quot;].includes(method)) {</code> | GET/HEAD 为读取操作；其他写方法需要携带 CSRF 令牌。 |
| 34 | <code>    if (!csrfToken) await refreshCsrfToken();</code> | 没有缓存令牌时先等待共享的令牌获取请求完成，再发送写请求。 |
| 35 | <code>    headers[&quot;X-CSRF-TOKEN&quot;] = csrfToken;</code> | 按后端约定把 CSRF 令牌写进请求头；它不替代会话 Cookie 和角色权限。 |
| 36 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 37 | <code>  if (body !== undefined &amp;&amp; !multipart)</code> | 判断本行条件，只有成立时才进入对应分支；结合下一行看成功、拒绝或提前返回的处理。 |
| 38 | <code>    headers[&quot;Content-Type&quot;] = form</code> | 开始选择正文类型：form=true 的登录使用表单编码，其余业务对象使用 JSON。 |
| 39 | <code>      ? &quot;application/x-www-form-urlencoded;charset=UTF-8&quot;</code> | form=true 分支：Spring Security 表单认证按 username/password 字段读取此格式。 |
| 40 | <code>      : &quot;application/json&quot;;</code> | 普通业务分支：后端 @RequestBody 按 JSON 解析商品、订单等对象。 |
| 41 | <code>  let response;</code> | 声明 HTTP 响应变量，等待 try 中 fetch 成功赋值；catch 若执行会抛错，后面的正文处理不会继续。 |
| 42 | <code>  try {</code> | 开始尝试执行可能失败的操作；抛出的异常交给后续 catch 统一处理。 |
| 43 | <code>    response = await fetch(&quot;/api&quot; + path, {</code> | 实际发送请求，统一加 /api 前缀；开发时由 Vite 代理到后端 8081。 |
| 44 | <code>      method,</code> | 对象简写：把配置里的 method 填入 fetch 选项 method。 |
| 45 | <code>      headers,</code> | 对象简写：把前面准备的 Accept/CSRF/Content-Type 传给 fetch。 |
| 46 | <code>      credentials: &quot;include&quot;,</code> | 请求携带并接收会话 Cookie，后端据此识别已登录身份。 |
| 47 | <code>      body:</code> | 开始计算 fetch 的请求正文，下面按无正文、表单、JSON 三种情况选择。 |
| 48 | <code>        body === undefined</code> | 首先判断调用者是否完全未提供正文。 |
| 49 | <code>          ? undefined</code> | 无正文分支：不为 GET/退出等请求构造不需要的数据。 |
| 50 | <code>          : multipart</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 51 | <code>            ? body</code> | 这是当前表达式或调用的组成部分；按本行字段、参数顺序与相邻代码一起阅读。 |
| 52 | <code>            : form</code> | 有正文时继续判断是否是登录用的表单编码。 |
| 53 | <code>              ? new URLSearchParams(body).toString()</code> | 表单分支：把键值对编码为 username=...&password=...，正确转义特殊字符。 |
| 54 | <code>              : JSON.stringify(body),</code> | JSON 分支：把业务对象序列化为 JSON 字符串供 @RequestBody 读取。 |
| 55 | <code>    });</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 56 | <code>  } catch {</code> | 仅捕获前面 JSON.parse 的解析错误，不捕获网络请求；解析失败时把 data 降级为 null。 |
| 57 | <code>    throw new Error(&quot;无法连接后台服务，请确认后端已启动。&quot;);</code> | 抛出错误 `new Error("无法连接后台服务，请确认后端已启动。")`，终止当前路径，并由异常处理器转换为失败响应或提示。 |
| 58 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 59 | <code>  const text = await response.text();</code> | 在 fetch 的 try/catch 外读取响应文本，空正文可以是合法的 204；若读取本身失败，异常直接传给调用者。 |
| 60 | <code>  let data;</code> | 声明解析后的响应数据，由下面的 JSON 解析尝试赋值。 |
| 61 | <code>  try {</code> | 开始尝试执行可能失败的操作；抛出的异常交给后续 catch 统一处理。 |
| 62 | <code>    data = text ? JSON.parse(text) : null;</code> | 有正文才解析 JSON；空响应设为 null，兼容 204。 |
| 63 | <code>  } catch {</code> | 仅捕获前面 JSON.parse 的解析错误，不捕获网络请求；解析失败时把 data 降级为 null。 |
| 64 | <code>    data = null;</code> | 正文不是有效 JSON 时保留 null，后续用状态码生成统一提示。 |
| 65 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 66 | <code>  if (!response.ok) {</code> | 检查 HTTP 是否成功；4xx/5xx 进入此处，fetch 连接拒绝在前面已抛出，response.text 读取失败也会直接向上传播。 |
| 67 | <code>    if (response.status === 401 &amp;&amp; ![&quot;/auth/login&quot;, &quot;/auth/me&quot;].includes(path))</code> | 业务请求遇到 401 才触发全局退出；登录失败和首次恢复会话的 401 由各自流程处理，避免反复跳转。 |
| 68 | <code>      onUnauthorized();</code> | 执行 session.js 注册的清除用户、清 CSRF 和跳回登录页逻辑。 |
| 69 | <code>    const error = new Error(</code> | 创建可以被各页面 catch 捕获的 Error；下一行选取用户可读消息。 |
| 70 | <code>      data?.message &#124;&#124; `请求失败（${response.status}），请稍后重试。`,</code> | 优先使用后端 JSON message；响应缺失或无 message 时显示带 HTTP 状态码的备用提示，?. 避免空值访问报错。 |
| 71 | <code>    );</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 72 | <code>    error.status = response.status;</code> | 给错误附上 HTTP 状态，session.js 可据此区分未登录与后端连接/其他错误。 |
| 73 | <code>    throw error;</code> | 把失败交给页面/会话调用方处理；不会继续返回成功数据。 |
| 74 | <code>  }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 75 | <code>  return data;</code> | 成功时把解析后的 JSON（或无正文的 null）交给调用页面。 |
| 76 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 77 | （空行） | 空行：分隔逻辑，不执行操作。 |
| 78 | <code>export function query(params) {</code> | 导出查询参数编码工具，避免各页面手工拼接关键词、分页和筛选字段。 |
| 79 | <code>  return new URLSearchParams(</code> | 用标准 URLSearchParams 构造查询串，负责中文、空格、特殊字符的 URL 编码。 |
| 80 | <code>    Object.entries(params).filter(</code> | 先把参数对象变成 [键,值] 数组，再过滤未填写项。 |
| 81 | <code>      ([, value]) =&gt; value !== &quot;&quot; &amp;&amp; value !== null &amp;&amp; value !== undefined,</code> | 解构时跳过键，只读取值；排除空字符串/null/undefined，但保留合法的 0 和 false。 |
| 82 | <code>    ),</code> | 结束前面打开的代码块/对象，并保留逗号供后续成员继续声明；括号与前面的起始符号配对。 |
| 83 | <code>  ).toString();</code> | 把编码后的参数对象转为最终 query 字符串，供页面拼接到 ? 后。 |
| 84 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
