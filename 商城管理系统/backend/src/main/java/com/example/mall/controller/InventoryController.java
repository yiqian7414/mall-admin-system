package com.example.mall.controller;

import com.example.mall.model.Models.Page;
import com.example.mall.model.Models.StockMovement;
import com.example.mall.model.Requests.StockInput;
import com.example.mall.service.InventoryService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventory;
    public InventoryController(InventoryService inventory) { this.inventory = inventory; }

    @GetMapping("/movements")
    public Page<StockMovement> list(@RequestParam(required = false) String keyword,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
            @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        return inventory.findPage(keyword, type, start, end, page, size);
    }

    @PostMapping("/movements")
    @ResponseStatus(HttpStatus.CREATED)
    public StockMovement create(@Valid @RequestBody StockInput input) { return inventory.manual(input); }
}
