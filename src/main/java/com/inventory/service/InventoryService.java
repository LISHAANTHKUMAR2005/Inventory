package com.inventory.service;

import com.inventory.controller.InventoryUpdateRequest;
import com.inventory.exception.InvalidInventoryException;
import com.inventory.model.Inventory;
import com.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
    private final SimpMessagingTemplate messagingTemplate;

    public List<Inventory> getInventoryByStore(Long storeId) {
        log.info("Fetching inventory for storeId: {}", storeId);
        return inventoryRepository.findByStoreId(storeId);
    }

    @Transactional
    public Inventory updateInventory(Long productId, Long storeId, int quantity) {
        if (quantity < 0) {
            throw new InvalidInventoryException("Quantity cannot be negative");
        }

        log.info("Updating inventory for productId: {}, storeId: {} to quantity: {}", productId, storeId, quantity);

        Optional<Inventory> existingOpt = inventoryRepository.findByProductIdAndStoreId(productId, storeId);
        Inventory inventory;
        
        if (existingOpt.isPresent()) {
            inventory = existingOpt.get();
            inventory.setQuantity(quantity);
        } else {
            inventory = new Inventory();
            inventory.setProductId(productId);
            inventory.setStoreId(storeId);
            inventory.setQuantity(quantity);
        }
        
        Inventory savedInventory = inventoryRepository.save(inventory);
        
        // Broadcast real-time update string WebSocket
        messagingTemplate.convertAndSend("/topic/inventory/" + storeId, savedInventory);
        
        // Low stock alert feature
        if (savedInventory.getQuantity() < 10) {
            log.warn("Low stock alert for productId: {} in storeId: {}. Current quantity: {}", productId, storeId, savedInventory.getQuantity());
            messagingTemplate.convertAndSend("/topic/low-stock/" + storeId, savedInventory);
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
