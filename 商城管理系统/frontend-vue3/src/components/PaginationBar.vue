<script setup>
import { computed } from "vue";
const props = defineProps({
  page: Number,
  size: Number,
  total: Number,
  loading: Boolean,
});
const emit = defineEmits(["change"]);
const pages = computed(() => Math.max(1, Math.ceil(props.total / props.size)));
</script>
<template>
  <div class="pagination">
    <span>共 {{ total }} 条，每页 {{ size }} 条</span>
    <div>
      <button
        class="button secondary small"
        :disabled="page <= 1 || loading"
        @click="emit('change', page - 1)"
      >
        上一页</button
      ><span>{{ page }} / {{ pages }}</span
      ><button
        class="button secondary small"
        :disabled="page >= pages || loading"
        @click="emit('change', page + 1)"
      >
        下一页
      </button>
    </div>
  </div>
</template>
