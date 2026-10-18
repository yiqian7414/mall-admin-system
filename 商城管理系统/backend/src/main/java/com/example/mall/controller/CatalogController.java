package com.example.mall.controller;

import com.example.mall.model.Models.Category;
import com.example.mall.model.Models.Page;
import com.example.mall.model.Models.Product;
import com.example.mall.model.Requests.CategoryInput;
import com.example.mall.model.Requests.ProductInput;
import com.example.mall.model.Requests.ProductStatusInput;
import com.example.mall.service.CatalogService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CatalogController {
    private final CatalogService catalog;

    public CatalogController(CatalogService catalog) {
        this.catalog = catalog;
    }

    @GetMapping("/categories")
    public List<Category> categories() {
        return catalog.categories();
    }

    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@Valid @RequestBody CategoryInput input) {
        return catalog.createCategory(input);
    }

    @PutMapping("/categories/{id}")
    public Category updateCategory(@PathVariable long id, @Valid @RequestBody CategoryInput input) {
        return catalog.updateCategory(id, input);
    }

    @DeleteMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable long id) {
        catalog.deleteCategory(id);
    }

    @GetMapping("/products")
    public Page<Product> products(@RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long categoryId, @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        return catalog.products(keyword, categoryId, status, page, size);
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@Valid @RequestBody ProductInput input) {
        return catalog.createProduct(input);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable long id, @Valid @RequestBody ProductInput input) {
        return catalog.updateProduct(id, input);
    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable long id) {
        catalog.deleteProduct(id);
    }

    @PutMapping("/products/{id}/status")
    public Product changeStatus(@PathVariable long id, @Valid @RequestBody ProductStatusInput input) {
        return catalog.changeProductStatus(id, input.status());
    }
}
