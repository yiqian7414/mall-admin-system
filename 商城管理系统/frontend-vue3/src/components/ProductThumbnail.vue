<script setup>
import { ref, watch } from "vue";
const props = defineProps({ imageUrl: String, name: String });
const failed = ref(false);
watch(
  () => props.imageUrl,
  () => {
    failed.value = false;
  },
);
</script>
<template>
  <img
    v-if="imageUrl && !failed"
    class="product-thumbnail product-photo"
    :src="imageUrl"
    :alt="name || '商品图片'"
    loading="lazy"
    @error="failed = true"
  />
  <span v-else class="product-thumbnail" aria-hidden="true">{{
    name?.slice(0, 1) || "商"
  }}</span>
</template>
