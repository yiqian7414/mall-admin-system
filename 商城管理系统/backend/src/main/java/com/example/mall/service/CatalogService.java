package com.example.mall.service;

import com.example.mall.common.BusinessException;
import com.example.mall.model.Models.Category;
import com.example.mall.model.Models.Page;
import com.example.mall.model.Models.Product;
import com.example.mall.model.Requests.CategoryInput;
import com.example.mall.model.Requests.ProductInput;
import com.example.mall.repository.CategoryRepository;
import com.example.mall.repository.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CatalogService {
    private final CategoryRepository categories;
    private final ProductRepository products;

    public CatalogService(CategoryRepository categories, ProductRepository products) {
        this.categories = categories;
        this.products = products;
    }

    public List<Category> categories() {
        return categories.findAll();
    }

    @Transactional
    public Category createCategory(CategoryInput input) {
        long id = categories.insert(input.name().trim(), text(input.description()));
        return category(id);
    }

    @Transactional
    public Category updateCategory(long id, CategoryInput input) {
        category(id);
        categories.update(id, input.name().trim(), text(input.description()));
        return category(id);
    }

    @Transactional
    public void deleteCategory(long id) {
        category(id);
        if (categories.countProducts(id) > 0)
            throw BusinessException.conflict("分类下仍有商品，请先移动或删除这些商品");
        categories.delete(id);
    }

    public Page<Product> products(String keyword, Long categoryId, String status, int page, int size) {
        checkPage(page, size);
        if (categoryId != null && categoryId <= 0)
            throw BusinessException.badRequest("分类编号无效");
        if (status != null && !status.isBlank() && !List.of("ON_SALE", "OFF_SALE").contains(status)) {
            throw BusinessException.badRequest("商品状态无效");
        }
        return products.findPage(keyword, categoryId, status, page, size);
    }

    @Transactional
    public Product createProduct(ProductInput input) {
        category(input.categoryId());
        return product(products.insert(input));
    }

    @Transactional
    public Product updateProduct(long id, ProductInput input) {
        products.lockById(id).orElseThrow(() -> BusinessException.notFound("商品不存在"));
        category(input.categoryId());
        products.update(id, input);
        return product(id);
    }

    @Transactional
    public void deleteProduct(long id) {
        products.lockById(id).orElseThrow(() -> BusinessException.notFound("商品不存在"));
        if (products.countOrderReferences(id) > 0) {
            throw BusinessException.conflict("商品已被订单引用，请将商品下架以保留订单记录");
        }
        products.delete(id);
    }

    @Transactional
    public Product changeProductStatus(long id, String status) {
        products.lockById(id).orElseThrow(() -> BusinessException.notFound("商品不存在"));
        products.updateStatus(id, status);
        return product(id);
    }

    private Category category(long id) {
        return categories.findById(id).orElseThrow(() -> BusinessException.notFound("分类不存在"));
    }

    private Product product(long id) {
        return products.findById(id).orElseThrow(() -> BusinessException.notFound("商品不存在"));
    }

    public static void checkPage(int page, int size) {
        if (page < 1 || size < 1 || size > 100)
            throw BusinessException.badRequest("页码至少为 1，每页数量为 1 至 100");
    }

    private String text(String value) {
        return value == null ? "" : value.trim();
    }
}
