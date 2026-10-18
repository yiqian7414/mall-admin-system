# 商城前端运行说明

本目录保存 Vue 3 + JavaScript + Vue Router + Vite 前端源码。完整项目优先按 [项目运行说明](../README.md) 启动。

单独开发前端时，在本目录运行：

```powershell
npm.cmd ci
npm.cmd run dev -- --port 5174 --strictPort
```

页面地址为 http://127.0.0.1:5174/，后端应在 http://127.0.0.1:8081 运行。Vite 将 `/api` 请求转发到后端；Node.js 版本要求见 `package.json`。

构建命令为 `npm.cmd run build`，结果保存在 `dist/`。实际部署需要 Web 服务转发 `/api` 并支持前端路由回退。

组件结构、请求流程及学习路线见 [前端学习说明](../../docs/前端学习说明.md)。
