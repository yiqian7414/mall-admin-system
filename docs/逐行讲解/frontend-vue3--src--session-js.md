# frontend-vue3/src/session.js

源文件：[`frontend-vue3/src/session.js`](../../商城管理系统/frontend-vue3/src/session.js)。以下行号以当前文件为准，空行也计入。

| 行号 | 原代码 | 作用 |
| ---: | --- | --- |
| 1 | <code>import { reactive } from &quot;vue&quot;;</code> | 从 Vue 导入 reactive，用于创建跨页面共享的会话状态对象。 |
| 2 | <code>import {</code> | 开始分行 import；下一行起列出要从 ./api 导入的请求/令牌工具。 |
| 3 | <code>  api,</code> | 导入统一 api 请求函数，登录、退出和恢复身份都通过它发送请求。 |
| 4 | <code>  clearCsrfToken,</code> | 导入旧 CSRF 缓存清理函数。 |
| 5 | <code>  refreshCsrfToken,</code> | 导入当前会话 CSRF 获取函数。 |
| 6 | <code>  setUnauthorizedHandler,</code> | 导入 401 回调注册函数，避免 api 模块反向导入 session 导致循环依赖。 |
| 7 | <code>} from &quot;./api&quot;;</code> | 结束导入列表；四个函数都来自同目录的 ./api 模块。 |
| 8 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 9 | <code>export const session = reactive({ user: null, restored: false });</code> | 创建共享响应式会话：user=null 表示未登录，restored=false 表示尚未完成会话恢复判断。它只保存公开 User，不保存密码。 |
| 10 | <code>let restoring = null;</code> | 缓存正在恢复身份的 Promise，多个路由同时等待时复用同一次 /auth/me 请求。 |
| 11 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 12 | <code>setUnauthorizedHandler(() =&gt; {</code> | 注册业务请求出现 401 时执行的回调；此函数由 api.js 调用。 |
| 13 | <code>  session.user = null;</code> | 清除前端当前用户，使导航与权限界面回到未登录状态。 |
| 14 | <code>  clearCsrfToken();</code> | 清除可能属于过期会话的 CSRF 令牌，下一次写请求重新取得。 |
| 15 | <code>  // 业务请求发现会话过期时回到登录页；登录页不会再次跳转。</code> | 注释：业务请求发现会话过期时回到登录页；登录页不会再次跳转。。此行用于解释代码，不会执行。 |
| 16 | <code>  if (location.pathname !== &quot;/login&quot;) location.assign(&quot;/login&quot;);</code> | 只有不在登录页时才整页跳转 /login，防止 401 导致登录页反复跳转自身。 |
| 17 | <code>});</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 18 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 19 | <code>export async function restoreSession() {</code> | 定义恢复会话函数，导航守卫在允许进入业务页前等待它完成。 |
| 20 | <code>  if (session.restored) return;</code> | 已经成功恢复或确认未登录时直接结束；restored 表示完成判断，而不是仅发起过一次尝试。 |
| 21 | <code>  if (!restoring)</code> | 没有在途恢复任务时才创建新任务，避免并发导航重复请求 /auth/me。 |
| 22 | <code>    restoring = (async () =&gt; {</code> | 创建异步立即调用函数，并把返回 Promise 缓存在 restoring。 |
| 23 | <code>      try {</code> | 开始尝试执行可能失败的操作；抛出的异常交给后续 catch 统一处理。 |
| 24 | <code>        session.user = await api(&quot;/auth/me&quot;);</code> | 用 Cookie 请求 /auth/me，将后端返回的公开 User 写入共享状态。 |
| 25 | <code>      } catch (error) {</code> | 捕获恢复身份失败，下面区分正常未登录和真正连接/服务错误。 |
| 26 | <code>        if (error.status !== 401) throw error;</code> | 401 表示当前没有有效登录，可以正常继续显示登录页；其他错误重新抛出，让下次有机会重试。 |
| 27 | <code>      } finally {</code> | 无论成功或失败都清理恢复任务缓存。 |
| 28 | <code>        restoring = null;</code> | 清空在途 Promise，使失败后还能重新请求；不在此处假装身份已恢复。 |
| 29 | <code>      }</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 30 | <code>      session.restored = true;</code> | 只有成功读到 User 或已处理 401 才执行：记录会话恢复判断已完成；非401抛错会跳过此行。 |
| 31 | <code>    })();</code> | 立即执行前面的 async 箭头函数，取得 Promise。 |
| 32 | <code>  return restoring;</code> | 返回在途恢复任务给路由守卫 await；并发调用会共享它。 |
| 33 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 34 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 35 | <code>export async function login(username, password) {</code> | 登录函数接收用户填写的用户名和密码。 |
| 36 | <code>  session.user = await api(&quot;/auth/login&quot;, {</code> | 向 /auth/login 发送请求并等待成功，返回 User 后才更新前端身份。 |
| 37 | <code>    method: &quot;POST&quot;,</code> | 使用 POST 登录方法，因此 api 会自动附带 CSRF。 |
| 38 | <code>    body: { username, password },</code> | 把 username/password 作为正文对象交给统一请求函数。 |
| 39 | <code>    form: true,</code> | form=true 指定 URLencoded 表单格式，配合 Spring Security 表单认证。 |
| 40 | <code>  });</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 41 | <code>  session.restored = true;</code> | 登录已确认身份，标记恢复完成，后续导航无需再调用 /auth/me。 |
| 42 | <code>  clearCsrfToken();</code> | 登录更换会话身份后丢弃旧 CSRF。 |
| 43 | <code>  // 登录会更换 Session，下一次写请求必须使用新的 CSRF Token。</code> | 注释：登录会更换 Session，下一次写请求必须使用新的 CSRF Token。。此行用于解释代码，不会执行。 |
| 44 | <code>  await refreshCsrfToken();</code> | 重新获取登录后会话的 CSRF，后续保存商品/订单等写请求才能通过安全检查。 |
| 45 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
| 46 | （空行） | 空行：分隔相邻逻辑，方便阅读；不执行任何操作。 |
| 47 | <code>export async function logout() {</code> | 定义退出函数，先请求后端成功退出再清前端身份。 |
| 48 | <code>  await api(&quot;/auth/logout&quot;, { method: &quot;POST&quot; });</code> | 发送带 CSRF 的 POST /auth/logout；若失败会抛错，下面不会提前假装退出成功。 |
| 49 | <code>  session.user = null;</code> | 后端退出成功后将前端用户清空。 |
| 50 | <code>  session.restored = true;</code> | 标记当前已确认处于未登录状态，避免路由再次恢复旧身份。 |
| 51 | <code>  clearCsrfToken();</code> | 清除登录会话的旧令牌。 |
| 52 | <code>  await refreshCsrfToken();</code> | 取得退出后匿名会话的新 CSRF，以便再次登录。 |
| 53 | <code>}</code> | 结束前面打开的代码块、对象、数组或函数调用；括号与前面的起始符号配对。 |
