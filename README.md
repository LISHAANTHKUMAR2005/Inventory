# 🛒 Inventory Sync Microservice (My Contribution)

## 👨‍💻 Contribution Summary

I was responsible for designing and developing the **Inventory Sync Microservice**, a core backend module in the Real-Time Grocery & Essentials Delivery System.

This service ensures **accurate, consistent, and real-time synchronization of inventory across multiple stores**, enabling seamless order processing and stock management.

---

## 🎯 My Responsibilities

* Designed and implemented a **real-time inventory management system**
* Built REST APIs for inventory operations
* Integrated **WebSocket for live updates**
* Implemented **bulk inventory processing**
* Ensured **data consistency using concurrency control**
* Developed **low-stock alert mechanism**
* Added **global exception handling and validation**
* Structured the module following **microservice architecture principles**

---

## ✨ Key Features Implemented

### 📦 Store-wise Inventory Management

* Managed inventory for multiple stores
* Supported product-level stock tracking
* Enabled add/update/retrieve operations

---

### ⚡ Real-Time Inventory Sync (Core Feature)

* Implemented **WebSocket (STOMP protocol)** for live updates
* Eliminated need for polling
* Broadcast updates instantly to:

  * `/topic/inventory/{storeId}`

---

### 🚨 Low Stock Alert System

* Automatically detects when stock < 10
* Sends real-time alerts via:

  * `/topic/low-stock/{storeId}`
* Helps prevent stock-out scenarios

---

### 🔁 Bulk Inventory Updates

* Designed API for updating multiple products in one request
* Improves performance for store-side operations

---

### 🔒 Concurrency Handling

* Implemented **pessimistic locking**
* Prevents race conditions and duplicate updates
* Ensures data integrity under concurrent access

---

### ⚠️ Exception Handling & Validation

* Centralized error handling using `@ControllerAdvice`
* Custom exceptions:

  * InvalidInventoryException
  * ResourceNotFoundException
* Input validation:

  * Prevent negative stock values
  * Ensure valid product/store IDs

---

### 📊 Standard API Response Format

All APIs return a consistent response:

```json
{
  "status": "success",
  "data": {},
  "message": ""
}
```

---

## 🏗️ Architecture Design

This module follows a **layered microservice architecture**:

* **Controller Layer** → Handles HTTP requests
* **Service Layer** → Business logic & validations
* **Repository Layer** → Database operations

### 🔗 Integration Ready

* Order Service → Stock deduction
* Store Service → Inventory updates
* Delivery Service → Availability checks

---

## 🗄️ Database Design

### Inventory Table

| Column     | Description        |
| ---------- | ------------------ |
| id         | Primary key        |
| product_id | Product identifier |
| store_id   | Store identifier   |
| quantity   | Available stock    |
| created_at | Created timestamp  |
| updated_at | Updated timestamp  |

---

## 📡 API Endpoints

### Get Inventory

GET /api/inventory/{storeId}

### Update Inventory

PUT /api/inventory/update

### Bulk Update

POST /api/inventory/bulk-update

---

## ⚡ Real-Time Channels

| Feature           | WebSocket Topic              |
| ----------------- | ---------------------------- |
| Inventory Updates | `/topic/inventory/{storeId}` |
| Low Stock Alerts  | `/topic/low-stock/{storeId}` |

---

## 🛠️ Tech Stack Used

* Java 17
* Spring Boot
* Spring Data JPA
* MySQL
* WebSocket (STOMP)
* Lombok
* Maven

---

## 🔐 Security & Data Protection

* Input validation for all requests
* Prevention of invalid stock updates
* Data consistency using transactions
* Designed for JWT integration

---

## ⚙️ How to Run

```bash
git clone https://github.com/YOUR-USERNAME/Inventory.git
cd Inventory
mvn spring-boot:run
```

---

## 🎬 Demo Flow

1. Update inventory via API
2. Observe real-time updates via WebSocket
3. Trigger low-stock alert
4. Perform bulk update

---

## 📈 Performance Highlights

* Real-time updates (no polling)
* Optimized database queries
* Scalable microservice design

---

## 🔮 Improvements & Future Enhancements

* JWT-based authentication
* Redis caching for faster reads
* Kafka for event-driven updates
* Frontend dashboard integration

---

## 🏆 Conclusion

This module provides a **robust, scalable, and real-time inventory management solution**, making it a critical component of the overall grocery delivery system.

It ensures:

* Data consistency
* High performance
* Real-time synchronization
* Reliability under concurrent usage

---

## 👨‍💻 Author

**Your Name**
Inventory Sync Module Developer
HCL Hackathon Project
