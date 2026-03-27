package com.inventory.service;

import com.inventory.controller.InventoryUpdateRequest;
import com.inventory.exception.InvalidInventoryException;
import com.inventory.model.Inventory;
import com.inventory.model.InventoryAudit;
import com.inventory.repository.InventoryAuditRepository;
import com.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.inventory.model.InventoryUpdateEvent;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryAuditRepository inventoryAuditRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Cacheable(value = "inventory", key = "#storeId")
    public List<Inventory> getInventoryByStore(Long storeId) {
        log.info("Fetching inventory for storeId: {}", storeId);
        return inventoryRepository.findByStoreId(storeId);
    }

    @Transactional
    @CacheEvict(value = "inventory", key = "#storeId")
    public Inventory updateInventory(Long productId, Long storeId, int quantity) {
        if (quantity < 0) {
            throw new InvalidInventoryException("Quantity cannot be negative");
        }

        log.info("Updating inventory for productId: {}, storeId: {} to quantity: {}", productId, storeId, quantity);

        int oldQuantity = 0;
        Optional<Inventory> existingOpt = inventoryRepository.findByProductIdAndStoreId(productId, storeId);
        Inventory inventory;
        
        if (existingOpt.isPresent()) {
            inventory = existingOpt.get();
            oldQuantity = inventory.getQuantity();
            inventory.setQuantity(quantity);
        } else {
            inventory = new Inventory();
            inventory.setProductId(productId);
            inventory.setStoreId(storeId);
            inventory.setQuantity(quantity);
        }
        
        Inventory savedInventory = inventoryRepository.save(inventory);
        
        // Save Audit Log
        InventoryAudit audit = new InventoryAudit();
        audit.setInventoryId(savedInventory.getId());
        audit.setOldQuantity(oldQuantity);
        audit.setNewQuantity(savedInventory.getQuantity());
        inventoryAuditRepository.save(audit);
        
        // Broadcast real-time update string WebSocket via Kafka
        kafkaTemplate.send("inventory-updates", new InventoryUpdateEvent(savedInventory, "UPDATE"));
        
        // Low stock alert feature
        if (savedInventory.getQuantity() < 10) {
            log.warn("Low stock alert for productId: {} in storeId: {}. Current quantity: {}", productId, storeId, savedInventory.getQuantity());
            kafkaTemplate.send("inventory-updates", new InventoryUpdateEvent(savedInventory, "LOW_STOCK"));
        }
        
        return savedInventory;
    }

    @Transactional
    public List<Inventory> bulkUpdateInventory(List<InventoryUpdateRequest> requests) {
        log.info("Processing bulk update for {} item(s)", requests.size());
        return requests.stream()
                .map(req -> updateInventory(req.getProductId(), req.getStoreId(), req.getQuantity()))
                .collect(Collectors.toList());
    }
}
