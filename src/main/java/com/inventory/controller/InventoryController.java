package com.inventory.controller;

import com.inventory.model.Inventory;
import com.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{storeId}")
    public ResponseEntity<ApiResponse<List<Inventory>>> getInventoryByStore(@PathVariable Long storeId) {
        List<Inventory> inventoryList = inventoryService.getInventoryByStore(storeId);
        return ResponseEntity.ok(ApiResponse.success(inventoryList, "Inventory fetched successfully"));
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<Inventory>> updateInventory(@RequestBody InventoryUpdateRequest request) {
        Inventory updated = inventoryService.updateInventory(
                request.getProductId(),
                request.getStoreId(),
                request.getQuantity()
        );
        return ResponseEntity.ok(ApiResponse.success(updated, "Inventory updated successfully"));
    }

    @PostMapping("/bulk-update")
    public ResponseEntity<ApiResponse<List<Inventory>>> bulkUpdateInventory(@RequestBody List<InventoryUpdateRequest> requests) {
        List<Inventory> updatedList = inventoryService.bulkUpdateInventory(requests);
        return ResponseEntity.ok(ApiResponse.success(updatedList, "Bulk inventory update successful"));
    }
}
