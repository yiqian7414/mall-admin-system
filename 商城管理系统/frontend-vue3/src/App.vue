<script setup>
import ToastList from "./components/ToastList.vue";
import ConfirmDialog from "./components/ConfirmDialog.vue";
</script>

<template>
  <RouterView />
  <ToastList />
  <ConfirmDialog />
</template>
