import { reactive } from "vue";
import {
  api,
  clearCsrfToken,
  refreshCsrfToken,
  setUnauthorizedHandler,
} from "./api";

export const session = reactive({ user: null, restored: false });
let restoring = null;

setUnauthorizedHandler(() => {
  session.user = null;
  clearCsrfToken();
  // 业务请求发现会话过期时回到登录页；登录页不会再次跳转。
  if (location.pathname !== "/login") location.assign("/login");
});

export async function restoreSession() {
  if (session.restored) return;
  if (!restoring)
    restoring = (async () => {
      try {
        session.user = await api("/auth/me");
      } catch (error) {
        if (error.status !== 401) throw error;
      } finally {
        restoring = null;
      }
      session.restored = true;
    })();
  return restoring;
}

export async function login(username, password) {
  session.user = await api("/auth/login", {
    method: "POST",
    body: { username, password },
    form: true,
  });
  session.restored = true;
  clearCsrfToken();
  // 登录会更换 Session，下一次写请求必须使用新的 CSRF Token。
  await refreshCsrfToken();
}

export async function logout() {
  await api("/auth/logout", { method: "POST" });
  session.user = null;
  session.restored = true;
  clearCsrfToken();
  await refreshCsrfToken();
}
