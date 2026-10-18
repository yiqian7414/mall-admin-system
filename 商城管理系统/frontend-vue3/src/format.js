export const money = (value) =>
  new Intl.NumberFormat("zh-CN", { style: "currency", currency: "CNY" }).format(
    Number(value || 0),
  );
export const dateTime = (value) =>
  value ? new Date(value).toLocaleString("zh-CN", { hour12: false }) : "—";
export const orderLabels = {
  PENDING: "待付款",
  PAID: "待发货",
  SHIPPED: "已发货",
  COMPLETED: "已完成",
  CANCELLED: "已取消",
};
export const orderTransitions = {
  PENDING: ["PAID", "CANCELLED"],
  PAID: ["SHIPPED", "CANCELLED"],
  SHIPPED: ["COMPLETED"],
  COMPLETED: [],
  CANCELLED: [],
};
export const transitionLabels = {
  PAID: "标记已付款",
  SHIPPED: "确认发货",
  COMPLETED: "确认完成",
  CANCELLED: "取消订单",
};
