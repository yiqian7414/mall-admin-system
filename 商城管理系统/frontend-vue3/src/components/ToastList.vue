<script setup>
import { notices, dismissNotice } from "../feedback";
</script>
<template>
  <div class="toast-list" aria-live="polite">
    <div
      v-for="notice in notices"
      :key="notice.id"
      class="toast"
      :class="notice.type"
    >
      <span>{{ notice.type === "error" ? "!" : "✓" }}</span>
      <p>{{ notice.message }}</p>
      <button aria-label="关闭提示" @click="dismissNotice(notice.id)">×</button>
    </div>
  </div>
</template>
