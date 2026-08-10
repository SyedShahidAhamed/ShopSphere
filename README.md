# 🛒 ShopSphere - Spring Boot E-Commerce REST API

A production-ready **E-Commerce REST API** built using **Spring Boot**, **Spring Security (JWT)**, **Spring Data JPA**, **MySQL**, **Docker**, and **Docker Compose**.

The project follows **Clean Layered Architecture** and demonstrates industry-standard backend development practices including authentication, CRUD operations, pagination, sorting, dynamic filtering, validation, testing, and containerized deployment.

---

# 🚀 Features

## 🔐 Authentication & Authorization

- User Registration
- User Login
- JWT Authentication
- Spring Security
- Role-Based Authorization
- BCrypt Password Encryption

---

## 📦 Product Management

- Create Product
- Update Product
- Delete Product
- Get Product by ID
- Get All Products
- Pagination
- Sorting
- Dynamic Filtering (JPA Specifications)
- Keyword Search

---

## 📂 Category Management

- Create Category
- Update Category
- Delete Category
- Get Category by ID
- Get All Categories

---

## 🛒 Cart Management

- Add Product to Cart
- Update Quantity
- Remove Product
- View User Cart
- Calculate Total Amount

---

## 📦 Order Management

- Place Order
- View Orders
- Cancel Order
- Order Status Management

---

## 💳 Payment Module

- Payment Entity
- Payment Status
- Payment Method

---

## 🛠 Backend Features

- DTO Pattern
- Mapper Pattern
- Bean Validation
- Global Exception Handling
- Custom Exceptions
- Pagination
- Sorting
- Dynamic Filtering (Specification API)
- Clean Layered Architecture

---

# 🧪 Testing

The project includes comprehensive testing using:

- ✅ Unit Testing
- ✅ Repository Testing
- ✅ Controller Testing
- ✅ Integration Testing

### Frameworks

- JUnit 5
- Mockito
- Spring Boot Test
- MockMvc
- H2 Database (Testing)

---

# 🐳 Docker Support

The application is fully containerized using Docker.

### Implemented

- Dockerfile
- Docker Compose
- MySQL Container
- Spring Boot Container
- Docker Networking
- Docker Volumes (Persistent Database)

---

# 🏗 Project Architecture

```
                Client

                  │

                  ▼

        Spring Boot REST API

                  │

                  ▼

          Spring Security

                  │

                  ▼

              Service Layer

                  │

                  ▼

               Mapper Layer

                  │

                  ▼

           Repository Layer

                  │

                  ▼

        Spring Data JPA

                  │

                  ▼

              MySQL Database
```

---

# 🐳 Docker Architecture

```
                 Docker Compose

      ┌─────────────────────────────┐

      │      ShopSphere API         │

      └─────────────┬───────────────┘
                    │
         JDBC (mysql:3306)
                    │
                    ▼

      ┌─────────────────────────────┐
      │      MySQL Database         │
      └─────────────┬───────────────┘
                    │
                    ▼

          Docker Volume
         (Persistent Storage)
```

---

# 🛠 Tech Stack

### Backend

- Java 21
- Spring Boot 3
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- Maven

### Database

- MySQL
- H2 Database (Testing)

### Testing

- JUnit 5
- Mockito
- MockMvc

### DevOps

- Docker
- Docker Compose

### Documentation

- Swagger / OpenAPI

### Utilities

- Lombok
- Bean Validation

---

# 📁 Project Structure

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

# 📌 REST API Endpoints

## Authentication

```
POST   /api/auth/register
POST   /api/auth/login
```

---

## Categories

```
GET    /api/categories
GET    /api/categories/{id}
POST   /api/categories
PUT    /api/categories/{id}
DELETE /api/categories/{id}
```

---

## Products

```
GET    /api/products
GET    /api/products/{id}
POST   /api/products
PUT    /api/products/{id}
DELETE /api/products/{id}
```

### Supports

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

---

## Cart

```
GET    /api/cart
POST   /api/cart
PUT    /api/cart/{id}
DELETE /api/cart/{id}
```

---

## Orders

```
POST   /api/orders
GET    /api/orders
PUT    /api/orders/{id}/cancel
```

---

## Payments

```
POST   /api/payments
GET    /api/payments/{id}
```

---

# ⚙️ Running the Project

## Option 1 : Run using Docker (Recommended)

### Clone Repository

```bash
git clone https://github.com/SyedShahidAhamed/ShopSphere.git
```

### Navigate

```bash
cd ShopSphere
```

### Build the Project

```bash
mvn clean package
```

### Start Containers

```bash
docker compose up
```

Run in Background

```bash
docker compose up -d
```

Stop Containers

```bash
docker compose down
```

The application will be available at:

```
http://localhost:8080
```

---

# Option 2 : Run Locally

Configure MySQL in

```
application.properties
```

Example

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/shopsphere
spring.datasource.username=root
spring.datasource.password=your_password
```

Run

```bash
mvn spring-boot:run
```

---

# 📚 API Documentation

### Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

### OpenAPI Docs

```
http://localhost:8080/v3/api-docs
```

---

# 📊 Project Status

✅ Authentication

✅ Authorization

✅ Product Module

✅ Category Module

✅ Cart Module

✅ Order Module

✅ Payment Module

✅ Pagination

✅ Sorting

✅ Dynamic Filtering

✅ Validation

✅ Exception Handling

✅ JWT Security

✅ Testing

✅ Docker

✅ Docker Compose

---
# 🔄 CI/CD Pipeline

ShopSphere uses GitHub Actions to automate the build, testing, Docker image creation, and deployment workflow.

```text
Developer
    │
    ▼
Git Push to main
    │
    ▼
GitHub Actions
    │
    ├── Checkout Repository
    │
    ├── Setup Java 21
    │
    ├── Maven Build & Tests
    │       └── mvn clean verify
    │
    ├── Build Docker Image
    │
    ├── Push Image to Docker Hub
    │
    ▼
Docker Hub
    │
    │  shopsphere:latest
    ▼
Railway
    │
    ├── Detects new Docker image
    │
    └── Automatically redeploys
    │
    ▼
🚀 Production Deployment

---

# 🔮 Future Enhancements

- Redis Caching
- Email Notifications
- Payment Gateway Integration (Razorpay/Stripe)
- CI/CD using GitHub Actions
- Cloud Deployment (AWS / Render / Railway)
- React Frontend
- Microservices Architecture
- Kubernetes Deployment

---

# 👨‍💻 Author

**Syed Shahid Ahamed**

- GitHub: https://github.com/SyedShahidAhamed
- LinkedIn: https://www.linkedin.com/in/syed-shahid-ahamed-0717423a9/

---

# ⭐ If you found this project useful, don't forget to star the repository!
