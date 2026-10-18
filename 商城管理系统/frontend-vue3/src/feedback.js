import { reactive } from "vue";

export const notices = reactive([]);
let nextId = 1;
export function notify(message, type = "success") {
  const id = nextId++;
  notices.push({ id, message, type });
  setTimeout(() => dismissNotice(id), 4500);
}
export function dismissNotice(id) {
  const index = notices.findIndex((item) => item.id === id);
  if (index >= 0) notices.splice(index, 1);
}

export const confirmation = reactive({
  open: false,
  title: "",
  message: "",
  danger: false,
  resolve: null,
});
export function confirmAction(title, message, danger = false) {
  return new Promise((resolve) => {
    Object.assign(confirmation, {
      open: true,
      title,
      message,
      danger,
      resolve,
    });
  });
}
export function resolveConfirmation(accepted) {
  confirmation.resolve?.(accepted);
  confirmation.open = false;
  confirmation.resolve = null;
}
