<script setup>
import { ref, computed, onMounted } from "vue";
import { api } from "../api";
import { money, dateTime, orderLabels } from "../format";
const data = ref(null),
  loading = ref(false),
  error = ref("");
const cards = computed(() =>
  data.value
    ? [
        {
          label: "商品总数",
          value: data.value.productCount,
          hint: `${data.value.categoryCount} 个商品分类`,
          mark: "▦",
          color: "blue",
        },
        {
          label: "订单总数",
          value: data.value.orderCount,
          hint: `${data.value.pendingOrderCount} 笔订单待付款`,
          mark: "▣",
          color: "purple",
        },
        {
          label: "累计销售额",
          value: money(data.value.totalSales),
          hint: "已付款且未取消的订单金额",
          mark: "¥",
          color: "green",
        },
        {
          label: "库存预警",
          value: data.value.lowStockCount,
          hint: "库存不超过 5 件的商品",
          mark: "!",
          color: "orange",
        },
      ]
    : [],
);
async function load() {
  loading.value = true;
  error.value = "";
  try {
    data.value = await api("/dashboard");
  } catch (cause) {
    error.value = cause.message;
  } finally {
    loading.value = false;
  }
}
onMounted(load);
</script>
<template>
  <div v-if="error" class="error-banner" role="alert">
    {{ error }}<button @click="load">重试</button>
  </div>
  <div v-if="loading && !data" class="loading-state">正在读取经营数据…</div>
  <template v-if="data"
    ><section class="stats-grid">
      <article v-for="card in cards" :key="card.label" class="stat-card">
        <div>
          <p>{{ card.label }}</p>
          <strong>{{ card.value }}</strong
          ><small>{{ card.hint }}</small>
        </div>
        <span class="stat-symbol" :class="card.color">{{ card.mark }}</span>
      </article>
    </section>
    <section class="welcome-banner">
      <div>
        <p class="eyebrow">TODAY'S WORKSPACE</p>
        <h2>每一笔订单，都从有序管理开始。</h2>
        <p>查看商品库存，处理待办订单，让经营保持顺畅。</p>
      </div>
      <RouterLink to="/orders" class="button"
        >管理订单 <span>→</span></RouterLink
      >
    </section>
    <div class="dashboard-grid">
      <section class="panel">
        <header class="panel-header">
          <div>
            <h2>最近订单</h2>
            <p>最新创建的订单与处理状态</p>
          </div>
          <RouterLink to="/orders" class="text-link">全部订单 →</RouterLink>
        </header>
        <div class="table-wrap">
          <table>
            <thead>
              <tr>
                <th>订单 / 客户</th>
                <th>金额</th>
                <th>状态</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="order in data.recentOrders" :key="order.id">
                <td>
                  <RouterLink
                    :to="{ path: '/orders', query: { orderId: order.id } }"
                    class="table-title"
                    >{{ order.orderNo }}</RouterLink
                  ><small class="cell-note"
                    >{{ order.customerName }} ·
                    {{ dateTime(order.createdAt) }}</small
                  >
                </td>
                <td class="money-cell">{{ money(order.totalAmount) }}</td>
                <td>
                  <span class="badge" :class="order.status.toLowerCase()">{{
                    orderLabels[order.status]
                  }}</span>
                </td>
              </tr>
              <tr v-if="!data.recentOrders.length">
                <td colspan="3" class="empty-cell">
                  还没有订单，去创建第一笔订单吧。
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </section>
      <section class="panel">
        <header class="panel-header">
          <div>
            <h2>库存关注</h2>
            <p>及时补充低库存商品</p>
          </div>
          <RouterLink to="/products" class="text-link">管理商品 →</RouterLink>
        </header>
        <div class="stock-list">
          <div
            v-for="product in data.lowStockProducts"
            :key="product.id"
            class="stock-item"
          >
            <span class="product-thumbnail">{{
              product.name.slice(0, 1)
            }}</span>
            <div>
              <strong>{{ product.name }}</strong
              ><small>{{ product.sku }}</small>
            </div>
            <span class="stock-count">剩余 {{ product.stock }} 件</span>
          </div>
          <p v-if="!data.lowStockProducts.length" class="empty-cell">
            当前没有低库存商品。
          </p>
        </div>
      </section>
    </div></template
  >
</template>
