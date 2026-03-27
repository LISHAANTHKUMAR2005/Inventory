package com.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryUpdateEvent implements Serializable {
    private Inventory inventory;
    private String alertType; // "UPDATE" or "LOW_STOCK"
}
