# 🛒 Real-Time Inventory Sync Microservice (My Contribution)

---

## 👨‍💻 Contribution Summary

I designed and developed the **Inventory Sync Microservice**, a core backend module in a Real-Time Grocery & Essentials Delivery System.

This service ensures **accurate, consistent, and real-time synchronization of inventory across multiple stores**, enabling seamless order processing and stock management.

Additionally, I enhanced the system with **production-level features** such as caching, event-driven architecture, rate limiting, and audit logging.

---

## 🎯 My Responsibilities

* Designed and implemented a **real-time inventory management system**
* Built REST APIs for inventory operations
* Integrated **WebSocket for live updates**
* Implemented **bulk inventory processing**
* Ensured **data consistency using concurrency control**
* Developed **low-stock alert mechanism**
* Implemented **Redis caching for performance optimization**
* Designed **Kafka-based event-driven communication**
* Added **API rate limiting using Redis**
* Implemented **audit logging for tracking inventory changes**
* Developed **dashboard APIs for analytics**
* Added **global exception handling and validation**
* Structured the module following **microservice architecture principles**

---

## 🚀 Advanced Enhancements (Production-Level)

### ⚡ Redis Caching

* Integrated Redis for faster inventory retrieval
* Used `@Cacheable` and `@CacheEvict` for optimized performance
* Reduced database load significantly

---

### 📡 Kafka Event-Driven Architecture

* Implemented Kafka producer for inventory updates
* Decoupled WebSocket communication from service layer
* Kafka consumer broadcasts updates to:

  * `/topic/inventory/{storeId}`
  * `/topic/low-stock/{storeId}`

---

### 🚦 API Rate Limiting

* Implemented using Redis (`StringRedisTemplate`)
* Limited requests to **60 requests/min per IP**
* Prevents abuse and ensures system stability

---

### 📝 Audit Logging

* Created `InventoryAudit` entity
* Tracks:

  * Old quantity
  * New quantity
  * Timestamp
* Useful for monitoring and debugging

---

### 📊 Dashboard APIs

* `/api/inventory/dashboard/low-stock`
* `/api/inventory/dashboard/top-products`
* Provides analytics for decision-making

---

## ✨ Core Features Implemented

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

* Controller Layer → Handles HTTP requests
* Service Layer → Business logic & validations
* Repository Layer → Database operations

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

### Dashboard APIs

GET /api/inventory/dashboard/low-stock
GET /api/inventory/dashboard/top-products

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
* Redis
* Apache Kafka
* Lombok
* Maven

---

## 🔐 Security & Data Protection

* Input validation for all requests
* Prevention of invalid stock updates
* API rate limiting
* Data consistency using transactions
* Designed for JWT authentication integration

---

## ⚙️ How to Run

```bash
git clone https://github.com/YOUR-USERNAME/Inventory.git
cd Inventory
mvn spring-boot:run
```

---

## ⚠️ Important Setup

To use advanced features:

* Redis must be running on port **6379**
* Kafka must be running on port **9092**

If not available, core APIs will still function.

---

## 🎬 Demo Flow

1. Update inventory via API
2. Observe real-time updates via WebSocket
3. Trigger low-stock alert
4. Perform bulk update
5. Show dashboard analytics

---

## 📈 Performance Highlights

* Real-time updates (no polling)
* Redis caching for fast reads
* Kafka for scalable event processing
* Rate limiting for stability
* Optimized database queries

---

## 🔮 Future Enhancements

* JWT-based authentication
* Role-based access control
* React-based admin dashboard
* AI-based demand prediction

---

## 🏆 Conclusion

This module delivers a **scalable, high-performance, and real-time inventory management system** enhanced with modern backend technologies like Redis and Kafka.

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
