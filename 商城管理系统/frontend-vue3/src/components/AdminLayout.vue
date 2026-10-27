<script setup>
import { ref, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import { session, logout } from "../session";
import { confirmAction, notify } from "../feedback";
const route = useRoute(),
  router = useRouter();
const sidebarOpen = ref(false),
  signingOut = ref(false);
const links = computed(() => [
  { path: "/", label: "工作台", mark: "⌂" },
  { path: "/products", label: "商品管理", mark: "▦" },
  { path: "/categories", label: "商品分类", mark: "▤" },
  { path: "/inventory", label: "库存管理", mark: "▧" },
  { path: "/orders", label: "订单管理", mark: "▣" },
  ...(session.user?.role === "ADMIN"
    ? [{ path: "/users", label: "账号管理", mark: "♙" }]
    : []),
]);
async function signOut() {
  if (!(await confirmAction("退出登录", "确认退出当前账号？"))) return;
  signingOut.value = true;
  try {
    await logout();
    router.push("/login");
  } catch (error) {
    notify(error.message, "error");
  } finally {
    signingOut.value = false;
  }
}
</script>
<template>
  <div class="admin-shell">
    <button
      v-if="sidebarOpen"
      class="sidebar-shade"
      aria-label="收起导航"
      @click="sidebarOpen = false"
    ></button>
    <aside class="sidebar" :class="{ open: sidebarOpen }">
      <RouterLink class="brand" to="/" @click="sidebarOpen = false"
        ><span class="brand-symbol">M</span
        ><span>我的商城<small>管理工作空间</small></span></RouterLink
      >
      <p class="nav-caption">商城管理</p>
      <nav aria-label="主导航">
        <RouterLink
          v-for="link in links"
          :key="link.path"
          :to="link.path"
          :class="{ active: route.path === link.path }"
          @click="sidebarOpen = false"
          ><span class="nav-symbol">{{ link.mark }}</span
          >{{ link.label }}</RouterLink
        >
      </nav>
      <div class="sidebar-footer">
        <span class="online-dot"></span> Vue 3 · Spring Boot<small
          >独立商城管理系统</small
        >
      </div>
    </aside>
    <div class="workspace">
      <header class="topbar">
        <div class="breadcrumb">
          <button
            class="icon-button mobile-menu"
            aria-label="展开导航"
            @click="sidebarOpen = true"
          >
            ☰</button
          ><span>商城后台</span><span class="crumb-divider">/</span
          ><strong>{{ route.meta.title }}</strong>
        </div>
        <div class="account">
          <span class="avatar">{{
            session.user?.displayName?.slice(0, 1) || "用"
          }}</span>
          <div>
            <strong>{{ session.user?.displayName }}</strong
            ><small>{{
              session.user?.role === "ADMIN" ? "管理员" : "员工"
            }}</small>
          </div>
          <button class="logout-button" :disabled="signingOut" @click="signOut">
            {{ signingOut ? "退出中…" : "退出" }}
          </button>
        </div>
      </header>
      <main class="page-content">
        <div class="page-heading">
          <div>
            <h1>{{ route.meta.title }}</h1>
            <p>{{ route.meta.subtitle }}</p>
          </div>
          <span class="environment-tag">商城管理</span>
        </div>
        <RouterView />
      </main>
      <footer class="workspace-footer">我的商城管理系统 · 学习与实践</footer>
    </div>
  </div>
</template>
