package com.inventory.controller;

import com.inventory.model.Inventory;
import com.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final InventoryRepository inventoryRepository;

    @GetMapping("/low-stock")
    public ResponseEntity<ApiResponse<List<Inventory>>> getLowStock() {
        List<Inventory> lowStock = inventoryRepository.findByQuantityLessThan(10);
        return ResponseEntity.ok(ApiResponse.success(lowStock, "Low stock items retrieved successfully"));
    }

    @GetMapping("/top-products")
    public ResponseEntity<ApiResponse<List<Inventory>>> getTopProducts() {
        List<Inventory> topProducts = inventoryRepository.findTop10ByOrderByQuantityDesc();
        return ResponseEntity.ok(ApiResponse.success(topProducts, "Top products retrieved successfully"));
    }
}
