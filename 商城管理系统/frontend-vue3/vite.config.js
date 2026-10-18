import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";

// Vue 插件让 Vite 能处理 .vue 单文件组件
export default defineConfig({
  plugins: [vue()],
  server: {
    proxy: {
      "/api": "http://127.0.0.1:8081",
    },
  },
});
