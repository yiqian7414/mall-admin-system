<script setup>
import BaseModal from "./BaseModal.vue";
import { confirmation, resolveConfirmation } from "../feedback";
</script>
<template>
  <BaseModal
    elevated
    :open="confirmation.open"
    :title="confirmation.title"
    @close="resolveConfirmation(false)"
  >
    <p class="confirm-message">{{ confirmation.message }}</p>
    <div class="form-actions">
      <button class="button secondary" @click="resolveConfirmation(false)">
        取消</button
      ><button
        class="button"
        :class="{ danger: confirmation.danger }"
        @click="resolveConfirmation(true)"
      >
        确认
      </button>
    </div>
  </BaseModal>
</template>
