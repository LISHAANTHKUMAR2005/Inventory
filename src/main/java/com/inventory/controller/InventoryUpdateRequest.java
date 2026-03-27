package com.inventory.controller;

import lombok.Data;

@Data
public class InventoryUpdateRequest {
    private Long productId;
    private Long storeId;
    private int quantity;
}
