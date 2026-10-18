<script setup>
import { ref, reactive, computed, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { api, query } from "../api";
import {
  money,
  dateTime,
  orderLabels,
  orderTransitions,
  transitionLabels,
} from "../format";
import { notify, confirmAction } from "../feedback";
import BaseModal from "../components/BaseModal.vue";
import PaginationBar from "../components/PaginationBar.vue";
const route = useRoute(),
  router = useRouter();
const orders = ref([]),
  total = ref(0),
  page = ref(1),
  size = 10;
const filters = reactive({ keyword: "", status: "" });
const loading = ref(false),
  error = ref(""),
  createOpen = ref(false),
  saving = ref(false),
  formError = ref("");
const detailOpen = ref(false),
  detail = ref(null),
  detailLoading = ref(false),
  detailError = ref(""),
  changing = ref(false);
const available = ref([]),
  productKeyword = ref(""),
  selectedProduct = ref(""),
  productsLoading = ref(false);
const draft = reactive({ customerName: "", phone: "", address: "", items: [] });
const draftTotal = computed(() =>
  draft.items.reduce(
    (sum, item) => sum + item.price * Number(item.quantity || 0),
    0,
  ),
);
let requestId = 0;
async function load(nextPage = page.value) {
  const id = ++requestId;
  loading.value = true;
  error.value = "";
  try {
    const result = await api(
      "/orders?" +
        query({
          ...filters,
          keyword: filters.keyword.trim(),
          page: nextPage,
          size,
        }),
    );
    if (id !== requestId) return;
    orders.value = result.items;
    total.value = result.total;
    page.value = result.page;
  } catch (cause) {
    if (id === requestId) error.value = cause.message;
  } finally {
    if (id === requestId) loading.value = false;
  }
}
function resetFilters() {
  Object.assign(filters, { keyword: "", status: "" });
  load(1);
}
async function loadProducts() {
  productsLoading.value = true;
  formError.value = "";
  try {
    const result = await api(
      "/products?" +
        query({
          keyword: productKeyword.value.trim(),
          status: "ON_SALE",
          page: 1,
          size: 100,
        }),
    );
    available.value = result.items.filter((product) => product.stock > 0);
    selectedProduct.value = "";
  } catch (cause) {
    formError.value = cause.message;
  } finally {
    productsLoading.value = false;
  }
}
function create() {
  Object.assign(draft, { customerName: "", phone: "", address: "", items: [] });
  productKeyword.value = "";
  formError.value = "";
  createOpen.value = true;
  loadProducts();
}
function addItem() {
  const product = available.value.find(
    (item) => item.id === Number(selectedProduct.value),
  );
  if (!product) {
    formError.value = "请先选择要加入订单的商品。";
    return;
  }
  const existing = draft.items.find((item) => item.productId === product.id);
  if (existing) {
    if (existing.quantity >= product.stock) {
      formError.value = "商品数量不能超过当前库存。";
      return;
    }
    existing.quantity++;
  } else if (draft.items.length >= 100) {
    formError.value = "每笔订单最多包含 100 种商品。";
    return;
  } else
    draft.items.push({
      productId: product.id,
      productName: product.name,
      price: product.price,
      stock: product.stock,
      quantity: 1,
    });
  formError.value = "";
  selectedProduct.value = "";
}
async function save() {
  if (saving.value) return;
  formError.value = "";
  if (
    !draft.customerName.trim() ||
    !draft.phone.trim() ||
    !draft.address.trim()
  ) {
    formError.value = "请完整填写客户、电话和收货地址。";
    return;
  }
  if (!/^[0-9+()\s-]{5,30}$/.test(draft.phone.trim())) {
    formError.value = "联系电话须为 5～30 位数字、空格或 + ( ) -。";
    return;
  }
  if (!draft.items.length) {
    formError.value = "请至少添加一件商品。";
    return;
  }
  if (
    draft.items.some(
      (item) =>
        !Number.isInteger(Number(item.quantity)) ||
        Number(item.quantity) < 1 ||
        Number(item.quantity) > Math.min(item.stock, 1000000),
    )
  ) {
    formError.value = "商品数量须为正整数，且不能超过当前库存。";
    return;
  }
  saving.value = true;
  try {
    const result = await api("/orders", {
      method: "POST",
      body: {
        customerName: draft.customerName.trim(),
        phone: draft.phone.trim(),
        address: draft.address.trim(),
        items: draft.items.map((item) => ({
          productId: item.productId,
          quantity: Number(item.quantity),
        })),
      },
    });
    createOpen.value = false;
    notify("订单已创建，商品库存已扣减。");
    await load(1);
    await showDetail(result.id);
  } catch (cause) {
    formError.value = cause.message;
  } finally {
    saving.value = false;
  }
}
async function showDetail(id) {
  detailOpen.value = true;
  detailLoading.value = true;
  detailError.value = "";
  detail.value = null;
  try {
    detail.value = await api(`/orders/${id}`);
  } catch (cause) {
    detailError.value = cause.message;
  } finally {
    detailLoading.value = false;
  }
}
function closeDetail() {
  detailOpen.value = false;
  if (route.query.orderId) router.replace({ path: "/orders", query: {} });
}
async function changeStatus(status) {
  if (changing.value || !detail.value) return;
  const message =
    status === "PAID"
      ? "请确认已经收到客户付款。此操作只记录人工收款结果，不会发起真实支付。"
      : status === "CANCELLED"
        ? "确认取消此订单？已扣减的商品库存将自动归还。"
        : status === "SHIPPED"
          ? "请确认已完成实际发货，再更新订单状态。"
          : "确认此订单已完成？";
  if (
    !(await confirmAction(
      transitionLabels[status],
      message,
      status === "CANCELLED",
    ))
  )
    return;
  changing.value = true;
  try {
    detail.value = await api(`/orders/${detail.value.id}/status`, {
      method: "PUT",
      body: { status },
    });
    notify("订单状态已更新。");
    await load();
  } catch (cause) {
    notify(cause.message, "error");
  } finally {
    changing.value = false;
  }
}
onMounted(async () => {
  await load();
  if (route.query.orderId) showDetail(route.query.orderId);
});
</script>
<template>
  <section class="panel">
    <div class="toolbar">
      <div>
        <h2>
          订单列表 <span class="count-pill">{{ total }}</span>
        </h2>
        <p class="muted">付款状态由后台人工确认，创建订单时扣减库存</p>
      </div>
      <button class="button" @click="create">＋ 创建订单</button>
    </div>
    <form class="filter-bar" @submit.prevent="load(1)">
      <div class="search-input">
        <span aria-hidden="true">⌕</span
        ><input
          v-model="filters.keyword"
          aria-label="搜索订单"
          placeholder="搜索订单号、客户或电话"
        />
      </div>
      <select v-model="filters.status" aria-label="按订单状态筛选">
        <option value="">全部状态</option>
        <option
          v-for="(label, value) in orderLabels"
          :key="value"
          :value="value"
        >
          {{ label }}
        </option></select
      ><button class="button" :disabled="loading">查询</button
      ><button type="button" class="button secondary" @click="resetFilters">
        重置
      </button>
    </form>
    <div v-if="error" class="error-banner" role="alert">
      {{ error }}<button @click="load()">重试</button>
    </div>
    <div class="table-wrap">
      <table>
        <thead>
          <tr>
            <th>订单编号</th>
            <th>客户信息</th>
            <th>订单金额</th>
            <th>状态</th>
            <th>创建时间</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="order in orders" :key="order.id">
            <td>
              <button
                class="table-title link-button"
                @click="showDetail(order.id)"
              >
                {{ order.orderNo }}
              </button>
            </td>
            <td>
              <strong>{{ order.customerName }}</strong
              ><small class="cell-note">{{ order.phone }}</small>
            </td>
            <td class="money-cell">{{ money(order.totalAmount) }}</td>
            <td>
              <span class="badge" :class="order.status.toLowerCase()">{{
                orderLabels[order.status]
              }}</span>
            </td>
            <td class="muted nowrap">{{ dateTime(order.createdAt) }}</td>
            <td>
              <div class="row-actions">
                <button @click="showDetail(order.id)">查看详情</button>
              </div>
            </td>
          </tr>
          <tr v-if="loading">
            <td colspan="6" class="empty-cell">正在加载订单…</td>
          </tr>
          <tr v-else-if="!orders.length">
            <td colspan="6" class="empty-cell">
              没有找到订单，调整筛选条件或创建第一笔订单。
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <PaginationBar
      :page="page"
      :size="size"
      :total="total"
      :loading="loading"
      @change="load"
    />
  </section>
  <BaseModal
    :open="createOpen"
    title="创建订单"
    :busy="saving"
    wide
    @close="createOpen = false"
    ><form @submit.prevent="save">
      <h3 class="form-section-title">客户信息</h3>
      <div class="form-grid">
        <div class="field">
          <label for="customer-name">客户姓名 <span>*</span></label
          ><input
            id="customer-name"
            v-model="draft.customerName"
            maxlength="100"
            required
            placeholder="请输入客户姓名"
          />
        </div>
        <div class="field">
          <label for="customer-phone">联系电话 <span>*</span></label
          ><input
            id="customer-phone"
            v-model="draft.phone"
            type="tel"
            minlength="5"
            maxlength="30"
            required
            placeholder="请输入联系电话"
          />
        </div>
        <div class="field full-width">
          <label for="customer-address">收货地址 <span>*</span></label
          ><textarea
            id="customer-address"
            v-model="draft.address"
            maxlength="300"
            rows="2"
            required
            placeholder="请输入详细收货地址"
          ></textarea>
        </div>
      </div>
      <h3 class="form-section-title">订单商品</h3>
      <div class="order-product-search">
        <input
          v-model="productKeyword"
          aria-label="查找可售商品"
          placeholder="输入名称或 SKU 查找商品"
          @keydown.enter.prevent="loadProducts"
        /><button
          type="button"
          class="button secondary"
          :disabled="productsLoading"
          @click="loadProducts"
        >
          {{ productsLoading ? "查询中…" : "查找" }}
        </button>
      </div>
      <div class="order-product-search">
        <select
          v-model="selectedProduct"
          aria-label="选择订单商品"
          :disabled="productsLoading"
        >
          <option value="">请选择在售且有库存的商品</option>
          <option
            v-for="product in available"
            :key="product.id"
            :value="product.id"
          >
            {{ product.name }} · {{ money(product.price) }} · 库存
            {{ product.stock }}
          </option></select
        ><button type="button" class="button secondary" @click="addItem">
          加入订单
        </button>
      </div>
      <p class="field-hint">
        每次展示最多 100 件可售商品，可输入名称或 SKU
        查找更多商品。库存与价格以提交时后台检查为准。
      </p>
      <div class="table-wrap order-items">
        <table>
          <thead>
            <tr>
              <th>商品</th>
              <th>单价</th>
              <th>数量</th>
              <th>小计</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item, index) in draft.items" :key="item.productId">
              <td>{{ item.productName }}</td>
              <td>{{ money(item.price) }}</td>
              <td>
                <input
                  v-model="item.quantity"
                  :aria-label="item.productName + '数量'"
                  class="quantity-input"
                  type="number"
                  min="1"
                  :max="Math.min(item.stock, 1000000)"
                  step="1"
                  required
                />
              </td>
              <td>{{ money(item.price * Number(item.quantity || 0)) }}</td>
              <td>
                <button
                  type="button"
                  class="text-link danger-text"
                  @click="draft.items.splice(index, 1)"
                >
                  移除
                </button>
              </td>
            </tr>
            <tr v-if="!draft.items.length">
              <td colspan="5" class="empty-cell">
                请从上方选择商品并加入订单。
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="order-total">
        订单合计 <strong>{{ money(draftTotal) }}</strong>
      </div>
      <p v-if="formError" class="inline-error" role="alert">{{ formError }}</p>
      <div class="form-actions">
        <button
          type="button"
          class="button secondary"
          :disabled="saving"
          @click="createOpen = false"
        >
          取消</button
        ><button class="button" :disabled="saving || productsLoading">
          {{ saving ? "创建中…" : "确认创建订单" }}
        </button>
      </div>
    </form></BaseModal
  >
  <BaseModal
    :open="detailOpen"
    title="订单详情"
    :busy="changing"
    wide
    @close="closeDetail"
    ><div v-if="detailLoading" class="loading-state">正在读取订单…</div>
    <p v-if="detailError" class="inline-error" role="alert">
      {{ detailError }}
    </p>
    <template v-if="detail"
      ><div class="order-detail-heading">
        <div>
          <small class="muted">订单编号</small>
          <h3>{{ detail.orderNo }}</h3>
        </div>
        <span class="badge" :class="detail.status.toLowerCase()">{{
          orderLabels[detail.status]
        }}</span>
      </div>
      <dl class="detail-grid">
        <div>
          <dt>客户姓名</dt>
          <dd>{{ detail.customerName }}</dd>
        </div>
        <div>
          <dt>联系电话</dt>
          <dd>{{ detail.phone }}</dd>
        </div>
        <div class="full-width">
          <dt>收货地址</dt>
          <dd>{{ detail.address }}</dd>
        </div>
        <div class="full-width">
          <dt>创建时间</dt>
          <dd>{{ dateTime(detail.createdAt) }}</dd>
        </div>
      </dl>
      <div class="table-wrap order-items">
        <table>
          <thead>
            <tr>
              <th>商品</th>
              <th>成交单价</th>
              <th>数量</th>
              <th>小计</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="item in detail.items" :key="item.productId">
              <td>{{ item.productName }}</td>
              <td>{{ money(item.unitPrice) }}</td>
              <td>{{ item.quantity }}</td>
              <td>{{ money(item.lineTotal) }}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="order-total">
        订单总额 <strong>{{ money(detail.totalAmount) }}</strong>
      </div>
      <p v-if="detail.status === 'CANCELLED'" class="field-hint">
        订单已取消，扣减的库存已归还。
      </p>
      <div class="form-actions">
        <button
          class="button secondary"
          :disabled="changing"
          @click="closeDetail"
        >
          关闭</button
        ><button
          v-for="status in orderTransitions[detail.status]"
          :key="status"
          class="button"
          :class="{ 'danger-outline': status === 'CANCELLED' }"
          :disabled="changing"
          @click="changeStatus(status)"
        >
          {{ changing ? "处理中…" : transitionLabels[status] }}
        </button>
      </div></template
    ></BaseModal
  >
</template>
