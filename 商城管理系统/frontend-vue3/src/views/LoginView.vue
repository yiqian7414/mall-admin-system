<script setup>
import { ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import { login } from "../session";
const username = ref("admin"),
  password = ref(""),
  error = ref(""),
  loading = ref(false);
const route = useRoute(),
  router = useRouter();
async function submit() {
  if (loading.value) return;
  loading.value = true;
  error.value = "";
  try {
    await login(username.value.trim(), password.value);
    const redirect = route.query.redirect;
    await router.push(
      typeof redirect === "string" &&
        redirect.startsWith("/") &&
        !redirect.startsWith("//") &&
        redirect !== "/login"
        ? redirect
        : "/",
    );
  } catch (cause) {
    error.value = cause.message;
  } finally {
    loading.value = false;
  }
}
</script>
<template>
  <main class="login-page">
    <section class="login-story">
      <div class="login-brand">
        <span class="brand-symbol">M</span> 我的商城管理系统
      </div>
      <div class="login-intro">
        <p class="eyebrow">MALL MANAGEMENT</p>
        <h1>让日常经营<br />井然有序。</h1>
        <p>从商品到订单，在一个工作空间中<br />完成商城的日常管理。</p>
        <div class="login-features">
          <span>商品与库存</span><span>订单流转</span><span>团队协作</span>
        </div>
      </div>
      <div class="story-grid" aria-hidden="true">
        <div class="story-card tall">
          <span>经营工作台</span>
          <div class="bar-chart">
            <i></i><i></i><i></i><i></i><i></i><i></i>
          </div>
        </div>
        <div class="story-card">
          <span>商品 · 分类 · 订单</span>
          <div class="decor-lines"><i></i><i></i><i></i></div>
        </div>
      </div>
      <small class="login-copyright">独立开发 · 逐步成长</small>
    </section>
    <section class="login-form-side">
      <div class="login-box">
        <span class="login-kicker">欢迎回来</span>
        <h2>登录管理后台</h2>
        <p class="muted">使用你的管理账号，开始今天的工作。</p>
        <form @submit.prevent="submit">
          <label for="username">账号</label
          ><input
            id="username"
            v-model="username"
            name="username"
            autocomplete="username"
            maxlength="50"
            placeholder="请输入账号"
            required
            :disabled="loading"
          /><label for="password">密码</label
          ><input
            id="password"
            v-model="password"
            name="password"
            type="password"
            autocomplete="current-password"
            placeholder="请输入密码"
            required
            :disabled="loading"
          />
          <p v-if="error" class="inline-error" role="alert">{{ error }}</p>
          <button class="button login-submit" :disabled="loading">
            {{ loading ? "正在登录…" : "登录" }}
            <span aria-hidden="true">→</span>
          </button>
        </form>
        <details class="demo-account">
          <summary>本地学习环境的初始账号</summary>
          <p>管理员：admin / Admin@123456</p>
          <p>员工：staff / Staff@123456</p>
          <small>请在实际部署前修改默认密码。</small>
        </details>
        <p class="login-note">我的商城管理系统 · Vue 3 + Spring Boot</p>
      </div>
    </section>
  </main>
</template>
