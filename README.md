# 🛒 Real-Time Inventory Sync Microservice

🚀 **A Scalable, Event-Driven, Real-Time Inventory Management System**

---

## 📌 Overview

The **Inventory Sync Microservice** is a core backend component of a Real-Time Grocery & Essentials Delivery Platform.
It is responsible for maintaining **accurate, consistent, and real-time inventory across multiple stores**.

This service is designed using a **microservices architecture**, enhanced with **Kafka, Redis, and WebSocket** to ensure high performance and scalability.

---

## 🎯 Key Objectives

* Maintain real-time inventory consistency
* Prevent stock inconsistencies during concurrent updates
* Provide instant updates without polling
* Enable scalable and decoupled communication
* Support analytics and monitoring

---

## ✨ Core Features

### 📦 Inventory Management

* Store-wise inventory tracking
* Product-level stock updates
* Bulk update support

### ⚡ Real-Time Updates

* WebSocket-based live synchronization
* No page refresh required
* Instant updates via `/topic/inventory/{storeId}`

### 🚨 Low Stock Alert

* Automatic alert when stock < 10
* Real-time notification system
* `/topic/low-stock/{storeId}`

### 🔁 Bulk Operations

* Update multiple products in one request
* Optimized for large-scale operations

### 🔒 Concurrency Control

* Pessimistic locking for safe updates
* Prevents race conditions

---

## 🚀 Advanced Features (Production-Level)

### ⚡ Redis Caching

* Improves performance
* Reduces database load
* Faster data retrieval

### 📡 Kafka Event-Driven Architecture

* Decouples services
* Async communication
* Scalable design

### 🚦 Rate Limiting

* 60 requests/min per IP
* Prevents abuse
* Ensures stability

### 📝 Audit Logging

* Tracks inventory changes
* Stores history of updates

### 📊 Dashboard APIs

* Low stock monitoring
* Top product insights

---

## 👥 User Stories

### 🛒 Store Owner

* Update product stock
* Manage bulk inventory

### 👤 Customer

* View available products
* Get real-time updates

### 🛠️ Admin

* Monitor inventory
* Analyze product performance

---

## 📋 Requirement Specification

### ✅ Functional

* Inventory CRUD operations
* Real-time updates
* Low stock alerts
* Bulk updates
* Dashboard analytics

### ⚙️ Non-Functional

* High performance
* Scalability
* Reliability
* Security
* Real-time processing

---

## 🔄 Process Flow

1. User places an order
2. Order Service checks inventory
3. Inventory is validated
4. Database is updated
5. Kafka event is published
6. Consumer receives event
7. WebSocket sends update
8. Frontend updates instantly

---

## 🏗️ System Architecture

### Backend

* Microservice architecture
* REST + Kafka communication
* WebSocket for real-time updates
* Redis for caching

### Frontend

* React applications
* API integration
* WebSocket for live updates

---

## 🧠 Internal Flow (Inventory Service)

* Controller → Handles request
* Service → Business logic
* Repository → Database
* Kafka → Event publishing
* Consumer → WebSocket broadcast

---

## 🗄️ Database Schema

| Field     | Description |
| --------- | ----------- |
| id        | Primary Key |
| productId | Product ID  |
| storeId   | Store ID    |
| quantity  | Stock Value |

---

## 📡 API Endpoints

### Inventory APIs

* `GET /api/inventory/{storeId}`
* `PUT /api/inventory/update`
* `POST /api/inventory/bulk-update`

### Dashboard APIs

* `GET /api/inventory/dashboard/low-stock`
* `GET /api/inventory/dashboard/top-products`

---

## ⚡ Real-Time Channels

| Feature           | Endpoint                     |
| ----------------- | ---------------------------- |
| Inventory Updates | `/topic/inventory/{storeId}` |
| Low Stock Alerts  | `/topic/low-stock/{storeId}` |

---

## 🛠️ Tech Stack

### Backend

* Java 17
* Spring Boot
* Spring Data JPA
* MySQL

### Real-Time & Messaging

* WebSocket (STOMP)
* Apache Kafka

### Performance & Security

* Redis
* Rate Limiting

### Tools

* Maven
* Lombok

---

## 🔐 Security & Reliability

* Input validation
* API rate limiting
* Transaction management
* Global exception handling
* Concurrency-safe updates

---

## 📊 Evaluation Mapping

| Criteria           | Implementation             |
| ------------------ | -------------------------- |
| Architecture       | Microservices + Kafka      |
| Security           | Validation + Rate Limiting |
| Exception Handling | Global Handler             |
| Performance        | WebSocket + Redis          |
| Prototype          | Fully Working System       |

---

## ⚙️ Setup Instructions

```bash
git clone https://github.com/YOUR-USERNAME/Inventory.git
cd Inventory
mvn spring-boot:run
```

---

## ⚠️ Requirements

* Redis → 6379
* Kafka → 9092

---

## 🎬 Demo Highlights

* Real-time inventory update
* Low stock alert trigger
* Bulk update execution

---

## 📈 Performance Highlights

* Real-time processing
* Reduced latency
* Scalable architecture
* Optimized queries

---

## 🔮 Future Enhancements

* JWT authentication
* Role-based access
* Admin dashboard UI
* AI demand prediction

---

## 🏆 Conclusion

This project demonstrates a **modern, scalable, and real-time inventory management system**, combining microservices with event-driven architecture to achieve high performance and reliability.

---

## 👨‍💻 Author

**Your Name**
Inventory Sync Microservice Developer
HCL Hackathon Project
