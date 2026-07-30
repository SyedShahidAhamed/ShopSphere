# 🛒 ShopSphere

A production-style E-Commerce REST API built using Spring Boot, Spring Security, JWT Authentication, and Spring Data JPA.

The project follows a clean layered architecture and implements industry-standard backend practices such as DTOs, Mapper Pattern, Pagination, Sorting, Dynamic Filtering, Validation, and Global Exception Handling.

---

## 🚀 Features

### 🔐 Authentication & Security
- User Registration
- User Login
- JWT Authentication
- Spring Security
- Role-Based Authorization
- BCrypt Password Encryption

### 📦 Product Management
- Create Product
- Update Product
- Delete Product
- Get Product by ID
- Get All Products
- Pagination
- Sorting
- Dynamic Filtering (JPA Specifications)
- Keyword Search

### 📂 Category Management
- Create Category
- Update Category
- Delete Category
- Get Categories

### 🛒 Cart Management
- Add Product to Cart
- Update Cart Quantity
- Remove Product from Cart
- View User Cart

### 📋 Order Management
- Place Order
- View Orders
- Cancel Order

### 💳 Payment
- Payment Module

### 🛠 Backend Features
- DTO Pattern
- Mapper Pattern
- Bean Validation
- Global Exception Handling
- Custom Exceptions
- Pagination
- Sorting
- Dynamic Filtering
- Clean Layered Architecture

---

## 🛠 Tech Stack

- Java 21
- Spring Boot 3
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- JWT (JSON Web Token)
- Lombok
- Bean Validation
- Swagger / OpenAPI

---

## 📁 Project Structure

```
src/main/java
│
├── config
├── controller
├── dto
├── entity
├── exception
├── mapper
├── repository
├── security
├── service
├── specifications
├── util
└── ShopSphereApplication
```

---

## 🏗 Architecture

```
Controller
      │
      ▼
Service
      │
      ▼
Mapper
      │
      ▼
Repository
      │
      ▼
Specification
      │
      ▼
MySQL Database
```

---

## 📌 API Endpoints

### Authentication

```
POST   /api/auth/register
POST   /api/auth/login
```

### Categories

```
GET    /api/categories
GET    /api/categories/{id}
POST   /api/categories
PUT    /api/categories/{id}
DELETE /api/categories/{id}
```

### Products

```
GET    /api/products
GET    /api/products/{id}
POST   /api/products
PUT    /api/products/{id}
DELETE /api/products/{id}
```

Supports:

```
?page=0
&size=10
&sortBy=price
&direction=desc
&category=Mobiles
&brand=Apple
&minPrice=50000
&maxPrice=150000
&keyword=iphone
```

### Cart

```
GET    /api/cart
POST   /api/cart
PUT    /api/cart/{id}
DELETE /api/cart/{id}
```

### Orders

```
POST   /api/orders
GET    /api/orders
PUT    /api/orders/{id}/cancel
```

### Payments

```
POST   /api/payments
GET    /api/payments/{id}
```

---

## ⚙️ Getting Started

### Clone Repository

```bash
git clone https://github.com/your-username/ShopSphere.git
```

### Navigate

```bash
cd ShopSphere
```

### Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/shopsphere
spring.datasource.username=root
spring.datasource.password=your_password
```

### Run

```bash
mvn spring-boot:run
```

---

## 📸 API Documentation

Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

OpenAPI Docs

```
http://localhost:8080/v3/api-docs
```

---

## 🔮 Future Improvements

- Docker
- JUnit 5
- Mockito
- Integration Testing
- React Frontend
- Razorpay Integration
- Deployment (Render / Railway / AWS)

---

## 👨‍💻 Author

**Syed Shahid Ahamed**

GitHub: https://github.com/SyedShahidAhamed