import { createRouter, createWebHistory } from "vue-router";
import { session, restoreSession } from "./session";
import AdminLayout from "./components/AdminLayout.vue";

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/login",
      component: () => import("./views/LoginView.vue"),
      meta: { title: "登录", public: true },
    },
    {
      path: "/",
      component: AdminLayout,
      children: [
        {
          path: "",
          component: () => import("./views/DashboardView.vue"),
          meta: {
            title: "工作台",
            subtitle: "了解商城经营情况，及时处理待办事项",
          },
        },
        {
          path: "products",
          component: () => import("./views/ProductsView.vue"),
          meta: {
            title: "商品管理",
            subtitle: "管理商品信息、库存与上下架状态",
          },
        },
        {
          path: "categories",
          component: () => import("./views/CategoriesView.vue"),
          meta: {
            title: "商品分类",
            subtitle: "建立清晰的商品分类，方便日常管理",
          },
        },
        {
          path: "orders",
          component: () => import("./views/OrdersView.vue"),
          meta: {
            title: "订单管理",
            subtitle: "创建订单并跟踪支付、发货与完成情况",
          },
        },
        {
          path: "users",
          component: () => import("./views/UsersView.vue"),
          meta: {
            title: "账号管理",
            subtitle: "为管理员与员工分配后台访问权限",
            admin: true,
          },
        },
      ],
    },
    { path: "/:pathMatch(.*)*", redirect: "/" },
  ],
});

router.beforeEach(async (to) => {
  try {
    await restoreSession();
  } catch {
    /* 登录页允许在后台恢复后重试登录 */
  }
  if (!to.meta.public && !session.user)
    return { path: "/login", query: { redirect: to.fullPath } };
  if (to.path === "/login" && session.user) return "/";
  if (to.meta.admin && session.user?.role !== "ADMIN") return "/";
  document.title = `${to.meta.title || "后台"} · 我的商城管理系统`;
});

export default router;
