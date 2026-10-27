<script setup>
import { ref, reactive, computed, onMounted } from "vue";
import { api, query } from "../api";
import { notify } from "../feedback";
import BaseModal from "../components/BaseModal.vue";
import PaginationBar from "../components/PaginationBar.vue";
import ProductThumbnail from "../components/ProductThumbnail.vue";

const tab = ref("movements"),
  rows = ref([]),
  total = ref(0),
  page = ref(1),
  loading = ref(false),
  error = ref("");
const filters = reactive({ keyword: "", type: "", start: "", end: "" });
const labels = {
  INBOUND: "商品入库",
  OUTBOUND: "手动出库",
  ORDER_DEDUCT: "订单扣减",
  ORDER_RETURN: "取消归还",
  OPENING: "升级期初库存",
};
let request = 0;
async function load(next = page.value) {
  const current = ++request;
  loading.value = true;
  error.value = "";
  try {
    const path =
      tab.value === "overview"
        ? "/products?" +
          query({ keyword: filters.keyword.trim(), page: next, size: 10 })
        : "/inventory/movements?" +
          query({
            ...filters,
            keyword: filters.keyword.trim(),
            page: next,
            size: 10,
          });
    const result = await api(path);
    if (current !== request) return;
    rows.value = result.items;
    total.value = result.total;
    page.value = result.page;
  } catch (cause) {
    if (current === request) error.value = cause.message;
  } finally {
    if (current === request) loading.value = false;
  }
}
function switchTab(next) {
  tab.value = next;
  rows.value = [];
  total.value = 0;
  load(1);
}
function reset() {
  Object.assign(filters, { keyword: "", type: "", start: "", end: "" });
  load(1);
}

const open = ref(false),
  saving = ref(false),
  formError = ref(""),
  choices = ref([]),
  choiceKeyword = ref(""),
  choiceTotal = ref(0),
  choiceLoading = ref(false);
const draft = reactive({
  productId: "",
  type: "INBOUND",
  quantity: 1,
  reason: "采购补货",
  remark: "",
  requestId: "",
});
const selected = computed(() =>
  choices.value.find((product) => product.id === Number(draft.productId)),
);
const afterStock = computed(() =>
  selected.value
    ? selected.value.stock +
      (draft.type === "INBOUND" ? 1 : -1) * Number(draft.quantity || 0)
    : null,
);
const reasons = computed(() =>
  draft.type === "INBOUND"
    ? ["采购补货", "其他入库"]
    : ["商品报损", "内部领用", "其他出库"],
);
let choiceRequest = 0;
async function searchChoices() {
  const current = ++choiceRequest;
  choiceLoading.value = true;
  try {
    const result = await api(
      "/products?" + query({ keyword: choiceKeyword.value.trim(), size: 100 }),
    );
    if (current !== choiceRequest) return;
    choices.value = result.items;
    choiceTotal.value = result.total;
    if (!selected.value) draft.productId = "";
  } catch (cause) {
    if (current === choiceRequest) formError.value = cause.message;
  } finally {
    if (current === choiceRequest) choiceLoading.value = false;
  }
}
async function begin(type, product = null) {
  formError.value = "";
  choiceKeyword.value = "";
  choices.value = [];
  Object.assign(draft, {
    productId: product?.id ?? "",
    type,
    quantity: 1,
    reason: type === "INBOUND" ? "采购补货" : "商品报损",
    remark: "",
    requestId: crypto.randomUUID(),
  });
  open.value = true;
  await searchChoices();
  if (product && !choices.value.some((item) => item.id === product.id))
    choices.value.unshift(product);
  if (product) draft.productId = product.id;
}
async function save() {
  if (saving.value) return;
  const quantity = Number(draft.quantity);
  if (
    !selected.value ||
    !Number.isInteger(quantity) ||
    quantity < 1 ||
    quantity > 1000000000
  ) {
    formError.value = "请选择商品，并填写 1～10 亿之间的整数数量。";
    return;
  }
  if (afterStock.value < 0 || afterStock.value > 1000000000) {
    formError.value = "出库不能超过当前库存，入库后库存不能超过 10 亿。";
    return;
  }
  saving.value = true;
  formError.value = "";
  try {
    await api("/inventory/movements", {
      method: "POST",
      body: {
        ...draft,
        productId: Number(draft.productId),
        quantity,
        remark: draft.remark.trim(),
      },
    });
    open.value = false;
    notify(
      draft.type === "INBOUND"
        ? "入库成功，库存与流水已更新。"
        : "出库成功，库存与流水已更新。",
    );
    await load(1);
  } catch (cause) {
    formError.value = cause.message;
  } finally {
    saving.value = false;
  }
}
function time(value) {
  return value?.replace("T", " ").slice(0, 19) || "—";
}
onMounted(() => load(1));
</script>

<template>
  <section class="panel">
    <div class="toolbar">
      <div>
        <h2>商品库存与出入库</h2>
        <p class="muted">
          库存仅通过出入库业务变更；订单自动记录，发货不重复扣减。
        </p>
      </div>
      <div class="inventory-actions">
        <button class="button" @click="begin('INBOUND')">＋ 商品入库</button
        ><button class="button secondary" @click="begin('OUTBOUND')">
          － 手动出库
        </button>
      </div>
    </div>
    <div class="inventory-tabs" role="tablist" aria-label="库存视图">
      <button
        id="stock-overview-tab"
        type="button"
        role="tab"
        :aria-selected="tab === 'overview'"
        aria-controls="inventory-panel"
        :class="{ selected: tab === 'overview' }"
        @click="switchTab('overview')"
      >
        库存总览
      </button>
      <button
        id="stock-movements-tab"
        type="button"
        role="tab"
        :aria-selected="tab === 'movements'"
        aria-controls="inventory-panel"
        :class="{ selected: tab === 'movements' }"
        @click="switchTab('movements')"
      >
        出入库记录
      </button>
    </div>
    <form class="filter-bar inventory-filters" @submit.prevent="load(1)">
      <input
        v-model="filters.keyword"
        aria-label="搜索库存商品"
        placeholder="搜索商品名称或 SKU"
      />
      <template v-if="tab === 'movements'">
        <select v-model="filters.type" aria-label="库存业务类型">
          <option value="">全部类型</option>
          <option v-for="(label, key) in labels" :key="key" :value="key">
            {{ label }}
          </option>
        </select>
        <input
          v-model="filters.start"
          type="date"
          min="1900-01-01"
          max="9998-12-31"
          aria-label="开始日期"
        />
        <input
          v-model="filters.end"
          type="date"
          :min="filters.start || '1900-01-01'"
          max="9998-12-31"
          aria-label="结束日期"
        />
      </template>
      <button class="button" :disabled="loading">查询</button
      ><button type="button" class="button secondary" @click="reset">
        重置
      </button>
    </form>
    <div v-if="error" class="error-banner" role="alert">
      {{ error }}<button @click="load()">重试</button>
    </div>
    <div
      id="inventory-panel"
      role="tabpanel"
      :aria-labelledby="
        tab === 'overview' ? 'stock-overview-tab' : 'stock-movements-tab'
      "
      class="table-wrap"
    >
      <table v-if="tab === 'overview'">
        <thead>
          <tr>
            <th>商品</th>
            <th>分类</th>
            <th>当前库存</th>
            <th>状态</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="product in rows" :key="product.id">
            <td>
              <div class="product-cell">
                <ProductThumbnail
                  :image-url="product.imageUrl"
                  :name="product.name"
                />
                <div>
                  <strong>{{ product.name }}</strong
                  ><small class="cell-note">SKU：{{ product.sku }}</small>
                </div>
              </div>
            </td>
            <td>{{ product.categoryName }}</td>
            <td :class="{ 'low-stock': product.stock <= 5 }">
              {{ product.stock }} 件
            </td>
            <td>{{ product.status === "ON_SALE" ? "已上架" : "已下架" }}</td>
            <td>
              <div class="row-actions">
                <button @click="begin('INBOUND', product)">入库</button
                ><button
                  :disabled="product.stock === 0"
                  @click="begin('OUTBOUND', product)"
                >
                  出库
                </button>
              </div>
            </td>
          </tr>
          <tr v-if="loading || !rows.length">
            <td colspan="5" class="empty-cell">
              {{ loading ? "正在加载…" : "没有找到商品。" }}
            </td>
          </tr>
        </tbody>
      </table>
      <table v-else class="movement-table">
        <thead>
          <tr>
            <th>商品</th>
            <th>业务类型</th>
            <th>变动数量</th>
            <th>变动前</th>
            <th>变动后</th>
            <th>关联单号</th>
            <th>原因 / 备注</th>
            <th>操作人</th>
            <th>时间</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in rows" :key="item.id">
            <td>
              <strong>{{ item.productName }}</strong
              ><small class="cell-note">SKU：{{ item.sku }}</small>
            </td>
            <td>{{ labels[item.type] }}</td>
            <td
              class="movement-delta"
              :class="item.delta > 0 ? 'stock-in' : 'stock-out'"
            >
              {{ item.delta > 0 ? "+" : "" }}{{ item.delta }}
            </td>
            <td>{{ item.beforeStock }}</td>
            <td>{{ item.afterStock }}</td>
            <td class="movement-reference">{{ item.reference }}</td>
            <td>
              {{ item.reason
              }}<small v-if="item.remark" class="cell-note">{{
                item.remark
              }}</small>
            </td>
            <td>{{ item.operator === "system" ? "系统" : item.operator }}</td>
            <td>{{ time(item.createdAt) }}</td>
          </tr>
          <tr v-if="loading || !rows.length">
            <td colspan="9" class="empty-cell">
              {{ loading ? "正在加载…" : "暂无出入库记录。" }}
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <PaginationBar
      :page="page"
      :size="10"
      :total="total"
      :loading="loading"
      @change="load"
    />
  </section>

  <BaseModal
    :open="open"
    :title="draft.type === 'INBOUND' ? '商品入库' : '手动出库'"
    :busy="saving"
    @close="open = false"
  >
    <form @submit.prevent="save">
      <div class="field">
        <label for="stock-search">查找商品</label>
        <div class="inventory-actions">
          <input
            id="stock-search"
            v-model="choiceKeyword"
            placeholder="输入商品名称或 SKU"
            :disabled="saving"
            @keydown.enter.prevent="searchChoices"
          /><button
            type="button"
            class="button secondary"
            :disabled="choiceLoading || saving"
            @click="searchChoices"
          >
            查找
          </button>
        </div>
      </div>
      <div class="field">
        <label for="stock-product">商品 <span>*</span></label
        ><select
          id="stock-product"
          v-model="draft.productId"
          required
          :disabled="choiceLoading || saving"
        >
          <option value="">请选择商品</option>
          <option
            v-for="product in choices"
            :key="product.id"
            :value="product.id"
          >
            {{ product.name }} · {{ product.sku }} · 库存 {{ product.stock }} 件
          </option></select
        ><small v-if="choiceTotal > 100"
          >匹配 {{ choiceTotal }} 件，展示前 100
          件，请输入关键词缩小范围。</small
        >
      </div>
      <p class="inventory-notice">
        当前库存：{{ selected ? selected.stock + " 件" : "请先选择商品" }}
      </p>
      <div class="field">
        <label for="stock-quantity"
          >{{ draft.type === "INBOUND" ? "入库" : "出库" }}数量
          <span>*</span></label
        ><input
          id="stock-quantity"
          v-model="draft.quantity"
          type="number"
          min="1"
          :max="
            draft.type === 'OUTBOUND'
              ? (selected?.stock ?? 1000000000)
              : 1000000000
          "
          step="1"
          required
          :disabled="saving"
        />
      </div>
      <div class="field">
        <label for="stock-reason"
          >{{ draft.type === "INBOUND" ? "入库" : "出库" }}原因
          <span>*</span></label
        ><select id="stock-reason" v-model="draft.reason" :disabled="saving">
          <option v-for="reason in reasons" :key="reason">{{ reason }}</option>
        </select>
      </div>
      <div class="field">
        <label for="stock-remark">备注（选填）</label
        ><textarea
          id="stock-remark"
          v-model="draft.remark"
          maxlength="200"
          rows="3"
          :disabled="saving"
        ></textarea>
      </div>
      <div class="inventory-notice" aria-live="polite">
        <span>{{ draft.type === "INBOUND" ? "入库" : "出库" }}后库存</span
        ><strong>{{ afterStock === null ? "—" : afterStock + " 件" }}</strong>
      </div>
      <p class="muted">
        库存以提交时后端检查为准。此处手动出库用于报损、领用等，订单发货不需要再次出库。
      </p>
      <p v-if="formError" class="inline-error" role="alert">{{ formError }}</p>
      <div class="form-actions">
        <button
          type="button"
          class="button secondary"
          :disabled="saving"
          @click="open = false"
        >
          取消</button
        ><button class="button" :disabled="saving || choiceLoading">
          {{
            saving
              ? "提交中…"
              : draft.type === "INBOUND"
                ? "确认入库"
                : "确认出库"
          }}
        </button>
      </div>
    </form>
  </BaseModal>
</template>
