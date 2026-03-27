package com.inventory.service;

import com.inventory.model.InventoryUpdateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryKafkaConsumer {

    private final SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics = "inventory-updates", groupId = "inventory-sync-group")
    public void consume(InventoryUpdateEvent event) {
        log.info("Received Kafka event: {}", event);

        Long storeId = event.getInventory().getStoreId();

        if ("UPDATE".equals(event.getAlertType())) {
            messagingTemplate.convertAndSend("/topic/inventory/" + storeId, event.getInventory());
        } else if ("LOW_STOCK".equals(event.getAlertType())) {
            messagingTemplate.convertAndSend("/topic/low-stock/" + storeId, event.getInventory());
        }
    }
}
