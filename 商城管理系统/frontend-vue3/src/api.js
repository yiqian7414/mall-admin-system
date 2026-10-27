// 统一 JSON、Session Cookie、CSRF 与错误处理。
let csrfToken = "";
let csrfRequest = null;
let onUnauthorized = () => {};

export function setUnauthorizedHandler(handler) {
  onUnauthorized = handler;
}
export function clearCsrfToken() {
  csrfToken = "";
}

export async function refreshCsrfToken() {
  if (!csrfRequest) {
    csrfRequest = fetch("/api/auth/csrf", { credentials: "include" })
      .then(async (response) => {
        if (!response.ok) throw new Error("无法获取安全令牌，请检查后台服务。");
        const data = await response.json();
        csrfToken = data.token;
        if (!csrfToken) throw new Error("后台没有返回安全令牌。");
        return csrfToken;
      })
      .finally(() => {
        csrfRequest = null;
      });
  }
  return csrfRequest;
}

export async function api(path, { method = "GET", body, form = false } = {}) {
  const multipart = body instanceof FormData;
  const headers = { Accept: "application/json" };
  if (!["GET", "HEAD"].includes(method)) {
    if (!csrfToken) await refreshCsrfToken();
    headers["X-CSRF-TOKEN"] = csrfToken;
  }
  if (body !== undefined && !multipart)
    headers["Content-Type"] = form
      ? "application/x-www-form-urlencoded;charset=UTF-8"
      : "application/json";
  let response;
  try {
    response = await fetch("/api" + path, {
      method,
      headers,
      credentials: "include",
      body:
        body === undefined
          ? undefined
          : multipart
            ? body
            : form
              ? new URLSearchParams(body).toString()
              : JSON.stringify(body),
    });
  } catch {
    throw new Error("无法连接后台服务，请确认后端已启动。");
  }
  const text = await response.text();
  let data;
  try {
    data = text ? JSON.parse(text) : null;
  } catch {
    data = null;
  }
  if (!response.ok) {
    if (response.status === 401 && !["/auth/login", "/auth/me"].includes(path))
      onUnauthorized();
    const error = new Error(
      data?.message || `请求失败（${response.status}），请稍后重试。`,
    );
    error.status = response.status;
    throw error;
  }
  return data;
}

export function query(params) {
  return new URLSearchParams(
    Object.entries(params).filter(
      ([, value]) => value !== "" && value !== null && value !== undefined,
    ),
  ).toString();
}
