<script setup>
import { ref, reactive, onMounted } from "vue";
import { api } from "../api";
import { session } from "../session";
import { notify, confirmAction } from "../feedback";
import BaseModal from "../components/BaseModal.vue";
const users = ref([]),
  loading = ref(false),
  error = ref(""),
  open = ref(false),
  saving = ref(false),
  formError = ref("");
const draft = reactive({
  id: null,
  username: "",
  displayName: "",
  password: "",
  role: "STAFF",
  enabled: true,
});
async function load() {
  loading.value = true;
  error.value = "";
  try {
    users.value = await api("/users");
  } catch (cause) {
    error.value = cause.message;
  } finally {
    loading.value = false;
  }
}
function edit(user = null) {
  Object.assign(draft, {
    id: user?.id ?? null,
    username: user?.username ?? "",
    displayName: user?.displayName ?? "",
    password: "",
    role: user?.role ?? "STAFF",
    enabled: user?.enabled ?? true,
  });
  formError.value = "";
  open.value = true;
}
async function save() {
  if (saving.value) return;
  if (!draft.username.trim() || !draft.displayName.trim()) {
    formError.value = "账号与显示名称不能为空。";
    return;
  }
  saving.value = true;
  formError.value = "";
  const body = {
    displayName: draft.displayName.trim(),
    role: draft.role,
    enabled: draft.enabled,
  };
  if (!draft.id) body.username = draft.username.trim();
  if (draft.password) body.password = draft.password;
  try {
    const result = await api(draft.id ? `/users/${draft.id}` : "/users", {
      method: draft.id ? "PUT" : "POST",
      body,
    });
    if (draft.id === session.user?.id) session.user = result;
    open.value = false;
    notify(draft.id ? "账号已更新。" : "账号已创建。");
    await load();
  } catch (cause) {
    formError.value = cause.message;
  } finally {
    saving.value = false;
  }
}
async function remove(user) {
  if (
    !(await confirmAction(
      "停用账号",
      `确认停用账号“${user.username}”？历史记录将保留，之后可在编辑中重新启用。`,
      true,
    ))
  )
    return;
  try {
    await api(`/users/${user.id}`, { method: "DELETE" });
    notify("账号已停用。");
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
          后台账号 <span class="count-pill">{{ users.length }}</span>
        </h2>
        <p class="muted">管理员可管理账号；员工可使用商品、分类与订单功能</p>
      </div>
      <button class="button" @click="edit()">＋ 新增账号</button>
    </div>
    <div v-if="error" class="error-banner" role="alert">
      {{ error }}<button @click="load">重试</button>
    </div>
    <div class="table-wrap">
      <table>
        <thead>
          <tr>
            <th>账号</th>
            <th>显示名称</th>
            <th>角色</th>
            <th>状态</th>
            <th class="actions-column">操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="user in users" :key="user.id">
            <td class="table-title">
              {{ user.username }}
              <span v-if="user.id === session.user?.id" class="count-pill"
                >当前</span
              >
            </td>
            <td>{{ user.displayName }}</td>
            <td>
              <span
                class="badge"
                :class="user.role === 'ADMIN' ? 'admin' : 'staff'"
                >{{ user.role === "ADMIN" ? "管理员" : "员工" }}</span
              >
            </td>
            <td>
              <span
                class="badge"
                :class="user.enabled ? 'on-sale' : 'off-sale'"
                >{{ user.enabled ? "已启用" : "已停用" }}</span
              >
            </td>
            <td>
              <div class="row-actions">
                <button @click="edit(user)">编辑</button
                ><button
                  class="danger-text"
                  :disabled="user.id === session.user?.id || !user.enabled"
                  :title="
                    user.id === session.user?.id ? '不能停用当前登录账号' : ''
                  "
                  @click="remove(user)"
                >
                  停用
                </button>
              </div>
            </td>
          </tr>
          <tr v-if="loading">
            <td colspan="5" class="empty-cell">正在加载账号…</td>
          </tr>
          <tr v-else-if="!users.length">
            <td colspan="5" class="empty-cell">暂无账号。</td>
          </tr>
        </tbody>
      </table>
    </div>
  </section>
  <BaseModal
    :open="open"
    :title="draft.id ? '编辑账号' : '新增账号'"
    :busy="saving"
    @close="open = false"
    ><form @submit.prevent="save">
      <div class="field">
        <label for="user-username">登录账号 <span>*</span></label
        ><input
          id="user-username"
          v-model="draft.username"
          autocomplete="off"
          :disabled="!!draft.id"
          minlength="3"
          maxlength="30"
          pattern="[a-zA-Z0-9_]+"
          required
          placeholder="3～30 位字母、数字或下划线"
        />
      </div>
      <div class="field">
        <label for="user-display-name">显示名称 <span>*</span></label
        ><input
          id="user-display-name"
          v-model="draft.displayName"
          maxlength="60"
          required
          placeholder="例如：商品管理员"
        />
      </div>
      <div class="field">
        <label for="user-password">{{
          draft.id ? "新密码（留空保持不变）" : "登录密码 *"
        }}</label
        ><input
          id="user-password"
          v-model="draft.password"
          type="password"
          autocomplete="new-password"
          minlength="8"
          maxlength="64"
          :required="!draft.id"
          placeholder="至少 8 个字符"
        />
      </div>
      <div class="form-grid">
        <div class="field">
          <label for="user-role">账号角色</label
          ><select
            id="user-role"
            v-model="draft.role"
            :disabled="draft.id === session.user?.id"
          >
            <option value="STAFF">员工</option>
            <option value="ADMIN">管理员</option>
          </select>
        </div>
        <div class="field">
          <label for="user-enabled">账号状态</label
          ><select
            id="user-enabled"
            v-model="draft.enabled"
            :disabled="draft.id === session.user?.id"
          >
            <option :value="true">启用</option>
            <option :value="false">停用</option>
          </select>
        </div>
      </div>
      <p v-if="draft.id === session.user?.id" class="field-hint">
        当前账号不能被自己停用或降级。
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
        ><button class="button" :disabled="saving">
          {{ saving ? "保存中…" : "保存账号" }}
        </button>
      </div>
    </form></BaseModal
  >
</template>
