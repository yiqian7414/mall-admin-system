package com.example.mall.controller;

import com.example.mall.model.Models.Order;
import com.example.mall.model.Models.Page;
import com.example.mall.model.Requests.OrderInput;
import com.example.mall.model.Requests.StatusInput;
import com.example.mall.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orders;

    public OrderController(OrderService orders) {
        this.orders = orders;
    }

    @GetMapping
    public Page<Order> list(@RequestParam(required = false) String keyword,
            @RequestParam(required = false) String status, @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return orders.findPage(keyword, status, page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@Valid @RequestBody OrderInput input) {
        return orders.create(input);
    }

    @GetMapping("/{id}")
    public Order get(@PathVariable long id) {
        return orders.get(id);
    }

    @PutMapping("/{id}/status")
    public Order changeStatus(@PathVariable long id, @Valid @RequestBody StatusInput input) {
        return orders.changeStatus(id, input.status());
    }
}
