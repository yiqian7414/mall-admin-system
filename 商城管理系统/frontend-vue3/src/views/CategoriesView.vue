<script setup>
import { ref, reactive, onMounted } from "vue";
import { api } from "../api";
import { notify, confirmAction } from "../feedback";
import BaseModal from "../components/BaseModal.vue";
const categories = ref([]),
  loading = ref(false),
  error = ref(""),
  open = ref(false),
  saving = ref(false),
  formError = ref("");
const draft = reactive({ id: null, name: "", description: "" });
async function load() {
  loading.value = true;
  error.value = "";
  try {
    categories.value = await api("/categories");
  } catch (cause) {
    error.value = cause.message;
  } finally {
    loading.value = false;
  }
}
function edit(category = null) {
  Object.assign(draft, {
    id: category?.id ?? null,
    name: category?.name ?? "",
    description: category?.description ?? "",
  });
  formError.value = "";
  open.value = true;
}
async function save() {
  if (saving.value) return;
  if (!draft.name.trim()) {
    formError.value = "请输入分类名称。";
    return;
  }
  saving.value = true;
  formError.value = "";
  try {
    await api(draft.id ? `/categories/${draft.id}` : "/categories", {
      method: draft.id ? "PUT" : "POST",
      body: { name: draft.name.trim(), description: draft.description.trim() },
    });
    open.value = false;
    notify(draft.id ? "分类已更新。" : "分类已创建。");
    await load();
  } catch (cause) {
    formError.value = cause.message;
  } finally {
    saving.value = false;
  }
}
async function remove(category) {
  if (
    !(await confirmAction(
      "删除分类",
      `确认删除“${category.name}”？有关联商品的分类不能删除。`,
      true,
    ))
  )
    return;
  try {
    await api(`/categories/${category.id}`, { method: "DELETE" });
    notify("分类已删除。");
    await load();
  } catch (cause) {
    notify(cause.message, "error");
  }
}
onMounted(load);
</script>
<template>
  <section class="panel">
    <div class="toolbar">
      <div>
        <h2>
          分类列表 <span class="count-pill">{{ categories.length }}</span>
        </h2>
        <p class="muted">使用分类组织商城商品</p>
      </div>
      <button class="button" @click="edit()">＋ 新增分类</button>
    </div>
    <div v-if="error" class="error-banner" role="alert">
      {{ error }}<button @click="load">重试</button>
    </div>
    <div class="table-wrap">
      <table>
        <thead>
          <tr>
            <th class="id-column">编号</th>
            <th>分类名称</th>
            <th>分类说明</th>
            <th class="actions-column">操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="category in categories" :key="category.id">
            <td class="muted">#{{ category.id }}</td>
            <td class="table-title">{{ category.name }}</td>
            <td class="description-cell">{{ category.description || "—" }}</td>
            <td>
              <div class="row-actions">
                <button @click="edit(category)">编辑</button
                ><button class="danger-text" @click="remove(category)">
                  删除
                </button>
              </div>
            </td>
          </tr>
          <tr v-if="loading">
            <td colspan="4" class="empty-cell">正在加载分类…</td>
          </tr>
          <tr v-else-if="!categories.length">
            <td colspan="4" class="empty-cell">
              暂无分类，点击“新增分类”开始创建。
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </section>
  <BaseModal
    :open="open"
    :title="draft.id ? '编辑分类' : '新增分类'"
    :busy="saving"
    @close="open = false"
    ><form @submit.prevent="save">
      <div class="field">
        <label for="category-name">分类名称 <span>*</span></label
        ><input
          id="category-name"
          v-model="draft.name"
          maxlength="60"
          required
          placeholder="例如：数码配件"
        />
      </div>
      <div class="field">
        <label for="category-description">分类说明</label
        ><textarea
          id="category-description"
          v-model="draft.description"
          maxlength="500"
          rows="3"
          placeholder="说明这一分类包含的商品"
        ></textarea>
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
          {{ saving ? "保存中…" : "保存分类" }}
        </button>
      </div>
    </form></BaseModal
  >
</template>
