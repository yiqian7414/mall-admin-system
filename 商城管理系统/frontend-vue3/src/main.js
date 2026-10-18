// 创建 Vue 应用，将根组件挂载到 index.html 的 #app 容器
import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import "./style.css";

createApp(App).use(router).mount("#app");
