<script setup>
import { ref, watch, nextTick, onBeforeUnmount } from "vue";
const props = defineProps({
  open: Boolean,
  title: String,
  wide: Boolean,
  busy: Boolean,
  elevated: Boolean,
});
const emit = defineEmits(["close"]);
const panel = ref(null);
let previousFocus;
function keydown(event) {
  if (event.key === "Escape" && !props.busy) emit("close");
  if (event.key !== "Tab") return;
  const nodes = [
    ...panel.value.querySelectorAll(
      'button:not(:disabled), input:not(:disabled), select:not(:disabled), textarea:not(:disabled), [tabindex="0"]',
    ),
  ].filter((node) => node.offsetParent !== null);
  if (!nodes.length) {
    event.preventDefault();
    return;
  }
  const first = nodes[0],
    last = nodes[nodes.length - 1];
  if (event.shiftKey && document.activeElement === first) {
    event.preventDefault();
    last.focus();
  } else if (!event.shiftKey && document.activeElement === last) {
    event.preventDefault();
    first.focus();
  }
}
watch(
  () => props.open,
  async (open) => {
    if (open) {
      previousFocus = document.activeElement;
      await nextTick();
      panel.value?.querySelector("input, select, textarea, button")?.focus();
    } else previousFocus?.focus();
  },
);
onBeforeUnmount(() => previousFocus?.focus());
</script>
<template>
  <Teleport to="body">
    <div
      v-if="open"
      class="modal-backdrop"
      :class="{ elevated }"
      @click.self="!busy && emit('close')"
    >
      <section
        ref="panel"
        role="dialog"
        aria-modal="true"
        :aria-label="title"
        class="modal"
        :class="{ wide }"
        @keydown="keydown"
      >
        <header class="modal-header">
          <h2>{{ title }}</h2>
          <button
            class="icon-button"
            :disabled="busy"
            aria-label="关闭弹窗"
            @click="emit('close')"
          >
            ×
          </button>
        </header>
        <div class="modal-body"><slot /></div>
      </section>
    </div>
  </Teleport>
</template>
