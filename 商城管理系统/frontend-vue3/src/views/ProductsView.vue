<script setup>
import { ref, reactive, onMounted } from "vue";
import { api, query } from "../api";
import { money } from "../format";
import { notify, confirmAction } from "../feedback";
import BaseModal from "../components/BaseModal.vue";
import PaginationBar from "../components/PaginationBar.vue";
const products = ref([]),
  categories = ref([]),
  total = ref(0),
  page = ref(1),
  size = 10;
const filters = reactive({ keyword: "", categoryId: "", status: "" });
const loading = ref(false),
  error = ref(""),
  open = ref(false),
  saving = ref(false),
  acting = ref(false),
  formError = ref("");
const draft = reactive({
  id: null,
  name: "",
  sku: "",
  categoryId: "",
  price: "",
  stock: 0,
  status: "ON_SALE",
  description: "",
});
let requestId = 0;
async function load(nextPage = page.value) {
  const id = ++requestId;
  loading.value = true;
  error.value = "";
  try {
    const result = await api(
      "/products?" +
        query({
          ...filters,
          keyword: filters.keyword.trim(),
          page: nextPage,
          size,
        }),
    );
    if (id !== requestId) return;
    products.value = result.items;
    total.value = result.total;
    page.value = result.page;
  } catch (cause) {
    if (id === requestId) error.value = cause.message;
  } finally {
    if (id === requestId) loading.value = false;
  }
}
async function loadCategories() {
  try {
    categories.value = await api("/categories");
  } catch (cause) {
    notify(cause.message, "error");
  }
}
function resetFilters() {
  Object.assign(filters, { keyword: "", categoryId: "", status: "" });
  load(1);
}
async function edit(product = null) {
  Object.assign(draft, {
    id: product?.id ?? null,
    name: product?.name ?? "",
    sku: product?.sku ?? "",
    categoryId: product?.categoryId ?? "",
    price: product?.price ?? "",
    stock: product?.stock ?? 0,
    status: product?.status ?? "ON_SALE",
    description: product?.description ?? "",
  });
  formError.value = "";
  open.value = true;
  await loadCategories();
}
function payload(product) {
  return {
    name: product.name.trim(),
    sku: product.sku.trim(),
    categoryId: Number(product.categoryId),
    price: Number(product.price),
    stock: Number(product.stock),
    status: product.status,
    description: product.description?.trim() || "",
  };
}
async function save() {
  if (saving.value) return;
  if (!draft.name.trim() || !draft.sku.trim()) {
    formError.value = "商品名称和 SKU 不能为空。";
    return;
  }
  saving.value = true;
  formError.value = "";
  try {
    await api(draft.id ? `/products/${draft.id}` : "/products", {
      method: draft.id ? "PUT" : "POST",
      body: payload(draft),
    });
    open.value = false;
    notify(draft.id ? "商品已更新。" : "商品已创建。");
    await load(draft.id ? page.value : 1);
  } catch (cause) {
    formError.value = cause.message;
  } finally {
    saving.value = false;
  }
}
async function toggle(product) {
  if (acting.value) return;
  const status = product.status === "ON_SALE" ? "OFF_SALE" : "ON_SALE";
  if (
    !(await confirmAction(
      status === "ON_SALE" ? "上架商品" : "下架商品",
      `确认${status === "ON_SALE" ? "上架" : "下架"}“${product.name}”？`,
    ))
  )
    return;
  acting.value = true;
  try {
    await api(`/products/${product.id}/status`, {
      method: "PUT",
      body: { status },
    });
    notify("商品状态已更新。");
    await load();
  } catch (cause) {
    notify(cause.message, "error");
  } finally {
    acting.value = false;
  }
}
async function remove(product) {
  if (
    !(await confirmAction(
      "删除商品",
      `确认删除“${product.name}”？已被订单引用的商品不能删除，可改为下架。`,
      true,
    ))
  )
    return;
  acting.value = true;
  try {
    await api(`/products/${product.id}`, { method: "DELETE" });
    notify("商品已删除。");
    await load(
      products.value.length === 1 && page.value > 1
        ? page.value - 1
        : page.value,
    );
  } catch (cause) {
    notify(cause.message, "error");
  } finally {
    acting.value = false;
  }
}
onMounted(() => Promise.all([load(), loadCategories()]));
</script>
<template>
  <section class="panel">
    <div class="toolbar">
      <div>
        <h2>
          商品列表 <span class="count-pill">{{ total }}</span>
        </h2>
        <p class="muted">维护商品资料，随时掌握可售库存</p>
      </div>
      <button class="button" @click="edit()">＋ 新增商品</button>
    </div>
    <form class="filter-bar" @submit.prevent="load(1)">
      <div class="search-input">
        <span aria-hidden="true">⌕</span
        ><input
          v-model="filters.keyword"
          aria-label="搜索商品"
          placeholder="搜索商品名称或 SKU"
        />
      </div>
      <select v-model="filters.categoryId" aria-label="按分类筛选">
        <option value="">全部分类</option>
        <option
          v-for="category in categories"
          :key="category.id"
          :value="category.id"
        >
          {{ category.name }}
        </option></select
      ><select v-model="filters.status" aria-label="按商品状态筛选">
        <option value="">全部状态</option>
        <option value="ON_SALE">已上架</option>
        <option value="OFF_SALE">已下架</option></select
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
            <th>商品信息</th>
            <th>分类</th>
            <th>售价</th>
            <th>库存</th>
            <th>状态</th>
            <th class="actions-column">操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="product in products" :key="product.id">
            <td>
              <div class="product-cell">
                <span class="product-thumbnail">{{
                  product.name.slice(0, 1)
                }}</span>
                <div>
                  <strong>{{ product.name }}</strong
                  ><small class="cell-note">SKU：{{ product.sku }}</small>
                </div>
              </div>
            </td>
            <td>{{ product.categoryName }}</td>
            <td class="money-cell">{{ money(product.price) }}</td>
            <td>
              <span :class="{ 'low-stock': product.stock <= 5 }"
                >{{ product.stock }} 件</span
              >
            </td>
            <td>
              <span
                class="badge"
                :class="product.status === 'ON_SALE' ? 'on-sale' : 'off-sale'"
                >{{ product.status === "ON_SALE" ? "已上架" : "已下架" }}</span
              >
            </td>
            <td>
              <div class="row-actions">
                <button :disabled="acting" @click="edit(product)">编辑</button
                ><button :disabled="acting" @click="toggle(product)">
                  {{ product.status === "ON_SALE" ? "下架" : "上架" }}</button
                ><button
                  class="danger-text"
                  :disabled="acting"
                  @click="remove(product)"
                >
                  删除
                </button>
              </div>
            </td>
          </tr>
          <tr v-if="loading">
            <td colspan="6" class="empty-cell">正在加载商品…</td>
          </tr>
          <tr v-else-if="!products.length">
            <td colspan="6" class="empty-cell">
              没有找到商品，请调整筛选条件或新增商品。
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
    :open="open"
    :title="draft.id ? '编辑商品' : '新增商品'"
    :busy="saving"
    wide
    @close="open = false"
    ><form @submit.prevent="save">
      <div class="form-grid">
        <div class="field">
          <label for="product-name">商品名称 <span>*</span></label
          ><input
            id="product-name"
            v-model="draft.name"
            maxlength="100"
            required
            placeholder="请输入商品名称"
          />
        </div>
        <div class="field">
          <label for="product-sku">商品 SKU <span>*</span></label
          ><input
            id="product-sku"
            v-model="draft.sku"
            maxlength="50"
            pattern="[A-Za-z0-9_\-]+"
            required
            placeholder="唯一编号，例如 MOUSE-001"
          />
        </div>
        <div class="field">
          <label for="product-category">商品分类 <span>*</span></label
          ><select id="product-category" v-model="draft.categoryId" required>
            <option value="" disabled>请选择分类</option>
            <option
              v-for="category in categories"
              :key="category.id"
              :value="category.id"
            >
              {{ category.name }}
            </option></select
          ><small v-if="!categories.length">请先到“商品分类”中创建分类。</small>
        </div>
        <div class="field">
          <label for="product-price">销售价格（元） <span>*</span></label
          ><input
            id="product-price"
            v-model="draft.price"
            type="number"
            min="0"
            max="9999999999.99"
            step="0.01"
            required
            placeholder="0.00"
          />
        </div>
        <div class="field">
          <label for="product-stock">库存数量 <span>*</span></label
          ><input
            id="product-stock"
            v-model="draft.stock"
            type="number"
            min="0"
            max="1000000000"
            step="1"
            required
          />
        </div>
        <div class="field">
          <label for="product-status">商品状态</label
          ><select id="product-status" v-model="draft.status">
            <option value="ON_SALE">上架销售</option>
            <option value="OFF_SALE">暂时下架</option>
          </select>
        </div>
        <div class="field full-width">
          <label for="product-description">商品说明</label
          ><textarea
            id="product-description"
            v-model="draft.description"
            maxlength="1000"
            rows="3"
            placeholder="填写商品的补充说明"
          ></textarea>
        </div>
      </div>
      <p v-if="formError" class="inline-error" role="alert">{{ formError }}</p>
      <div class="form-actions">
        <button
          type="button"
          class="button secondary"
          :disabled="saving"
          @click="open = false"
        >
          取消</button
        ><button class="button" :disabled="saving">
          {{ saving ? "保存中…" : "保存商品" }}
        </button>
      </div>
    </form></BaseModal
  >
</template>
