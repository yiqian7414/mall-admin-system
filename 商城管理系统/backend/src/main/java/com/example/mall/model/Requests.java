package com.example.mall.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

/** 请求与响应分开，防止前端写入 id、库存流水、订单金额等服务器管理的字段。 */
public final class Requests {
    private Requests() {
    }

    public record CategoryInput(@NotBlank(message = "请填写分类名称") @Size(max = 60, message = "分类名称最多 60 个字符") String name,
            @Size(max = 500, message = "分类说明最多 500 个字符") String description) {
    }

    public record ProductInput(@NotBlank(message = "请填写商品名称") @Size(max = 100, message = "商品名称最多 100 个字符") String name,
            @NotBlank(message = "请填写商品编号") @Pattern(regexp = "[A-Za-z0-9_-]{1,50}", message = "商品编号仅支持 1 至 50 位字母、数字、下划线和短横线") String sku,
            @NotNull(message = "请选择分类") @Positive(message = "分类编号无效") Long categoryId,
            @NotNull(message = "请填写商品价格") @DecimalMin(value = "0.00", message = "商品价格不能小于 0") @Digits(integer = 10, fraction = 2, message = "价格最多 10 位整数和 2 位小数") BigDecimal price,
            @Pattern(regexp = "^$|/api/product-images/[a-f0-9-]{36}\\.png", message = "请先上传有效的商品图片") String imageUrl,
            @NotNull(message = "请选择商品状态") @Pattern(regexp = "ON_SALE|OFF_SALE", message = "商品状态无效") String status,
            @Size(max = 1000, message = "商品说明最多 1000 个字符") String description) {
    }

    public record StockInput(
            @NotNull(message = "请选择商品") @Positive Long productId,
            @NotNull(message = "请选择出入库类型") @Pattern(regexp = "INBOUND|OUTBOUND") String type,
            @NotNull(message = "请填写数量") @Min(1) @Max(1000000000) Integer quantity,
            @NotBlank(message = "请选择出入库原因") @Size(max = 80) String reason,
            @Size(max = 200, message = "备注最多 200 字") String remark,
            @NotBlank @Pattern(regexp = "[a-f0-9-]{36}") String requestId) {
    }

    public record OrderLineInput(@NotNull(message = "请选择商品") @Positive(message = "商品编号无效") Long productId,
            @NotNull(message = "请填写购买数量") @Min(value = 1, message = "购买数量至少为 1") @Max(value = 1000000, message = "单项购买数量不能超过 100 万") Integer quantity) {
    }

    public record OrderInput(
            @NotBlank(message = "请填写客户姓名") @Size(max = 100, message = "客户姓名最多 100 个字符") String customerName,
            @NotBlank(message = "请填写联系电话") @Pattern(regexp = "[0-9+()\\-\\s]{5,30}", message = "联系电话格式无效") String phone,
            @NotBlank(message = "请填写收货地址") @Size(max = 300, message = "收货地址最多 300 个字符") String address,
            @NotEmpty(message = "订单至少包含一件商品") @Size(max = 100, message = "订单最多包含 100 种商品") List<@NotNull(message = "订单商品不能为空") @Valid OrderLineInput> items) {
    }

    public record StatusInput(
            @NotNull(message = "请选择订单状态") @Pattern(regexp = "PENDING|PAID|SHIPPED|COMPLETED|CANCELLED", message = "订单状态无效") String status) {
    }

    public record ProductStatusInput(
            @NotNull(message = "请选择商品状态") @Pattern(regexp = "ON_SALE|OFF_SALE", message = "商品状态无效") String status) {
    }

    public record CreateUserInput(
            @NotBlank(message = "请填写用户名") @Pattern(regexp = "[A-Za-z0-9_]{3,30}", message = "用户名为 3 至 30 位字母、数字或下划线") String username,
            @NotBlank(message = "请填写姓名") @Size(max = 60, message = "姓名最多 60 个字符") String displayName,
            @NotBlank(message = "请填写密码") @Size(min = 8, max = 64, message = "密码长度为 8 至 64 个字符") String password,
            @NotNull(message = "请选择角色") @Pattern(regexp = "ADMIN|STAFF", message = "角色无效") String role,
            @NotNull(message = "请选择账号状态") Boolean enabled) {
    }

    public record UpdateUserInput(
            @NotBlank(message = "请填写姓名") @Size(max = 60, message = "姓名最多 60 个字符") String displayName,
            @NotNull(message = "请选择角色") @Pattern(regexp = "ADMIN|STAFF", message = "角色无效") String role,
            @NotNull(message = "请选择账号状态") Boolean enabled, @Size(max = 64, message = "密码最多 64 个字符") String password) {
    }
}
