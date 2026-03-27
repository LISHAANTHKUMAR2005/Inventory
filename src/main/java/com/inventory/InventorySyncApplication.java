package com.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class InventorySyncApplication {
    public static void main(String[] args) {
        SpringApplication.run(InventorySyncApplication.class, args);
    }
}
