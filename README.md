# 🛒 ShopSphere — E-Commerce REST API

<p align="center">
  <b>A production-ready E-Commerce REST API built with Spring Boot, Spring Security, JWT, Spring Data JPA, MySQL, Docker, GitHub Actions, Docker Hub, and Railway.</b>
</p>

<p align="center">
  <a href="https://github.com/SyedShahidAhamed/ShopSphere">
    <img src="https://img.shields.io/badge/GitHub-ShopSphere-black?logo=github" alt="GitHub">
  </a>
  <img src="https://img.shields.io/badge/Java-21-orange?logo=openjdk" alt="Java 21">
  <img src="https://img.shields.io/badge/Spring%20Boot-3-brightgreen?logo=springboot" alt="Spring Boot">
  <img src="https://img.shields.io/badge/Spring%20Security-JWT-green?logo=springsecurity" alt="Spring Security">
  <img src="https://img.shields.io/badge/MySQL-Database-blue?logo=mysql" alt="MySQL">
  <img src="https://img.shields.io/badge/Docker-Containerized-2496ED?logo=docker" alt="Docker">
  <img src="https://img.shields.io/badge/Deployment-Railway-purple" alt="Railway">
</p>

---

## 📌 About the Project

**ShopSphere** is a full-featured E-Commerce backend REST API developed using **Java and Spring Boot**.

The project demonstrates real-world backend development practices including:

- Authentication and authorization
- JWT-based security
- Role-based access control
- CRUD operations
- Pagination and sorting
- Dynamic filtering using JPA Specifications
- Bean validation
- DTO and Mapper patterns
- Global exception handling
- Unit, repository, controller, and integration testing
- Docker containerization
- Docker Compose
- GitHub Actions CI/CD
- Docker Hub image publishing
- Railway cloud deployment
- Swagger / OpenAPI documentation

---

# 🌐 Live Application

### 🚀 Production API

**Railway**

https://renewed-delight-production-de66.up.railway.app

### 📚 Production Swagger UI

https://renewed-delight-production-de66.up.railway.app/swagger-ui/index.html

### 📄 Production OpenAPI

https://renewed-delight-production-de66.up.railway.app/v3/api-docs

> The application is deployed as a Docker container on Railway and uses Railway MySQL as its production database.

---

# ✨ Features

## 🔐 Authentication & Authorization

- User Registration
- User Login
- JWT Authentication
- JWT Token Validation
- Spring Security
- Role-Based Authorization
- User Role
- Admin Role
- Method-Level Security using `@PreAuthorize`
- BCrypt Password Encryption
- Stateless Authentication
- Protected REST APIs
- Environment-based JWT configuration

---

## 📦 Product Management

- Create Product
- Update Product
- Delete Product
- Get Product by ID
- Get All Products
- Pagination
- Sorting
- Dynamic Filtering
- Keyword Search
- Category Filtering
- Brand Filtering
- Minimum Price Filtering
- Maximum Price Filtering

### Example Queries

```http
GET /api/products?page=0&size=10&sortBy=price&direction=desc
```

```http
GET /api/products?category=Mobiles
```

```http
GET /api/products?brand=Apple
```

```http
GET /api/products?minPrice=50000&maxPrice=150000
```

```http
GET /api/products?keyword=iphone
```

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
- Update Product Quantity
- Remove Product from Cart
- View User Cart
- Calculate Cart Total

---

## 📦 Order Management

- Place Order
- View Orders
- View Order Details
- Cancel Order
- Order Status Management

---

## 💳 Payment Module

The project contains a payment module with payment entities and REST APIs.

### Implemented

- Payment Entity
- Payment Status
- Payment Method
- Payment APIs

---

# 🛡️ Security Architecture

ShopSphere uses **Spring Security + JWT** for authentication and authorization.

```text
                    Client
                      │
                      ▼
                    Authentication API
                      │
                      ▼
                    Login
                      │
                      ▼
                    JWT Token
                      │
                      ▼
                    Authorization: Bearer <JWT>
                      │
                      ▼
                    JwtAuthenticationFilter
                      │
                      ▼
                    JWT Validation
                      │
                      ▼
                    SecurityContext
                      │
                      ▼
                    Controller
                      │
                      ▼
                    @PreAuthorize
                      │
                      ▼
                    Service Layer
```

### Security Components

```text
SecurityConfig
JwtAuthenticationFilter
JwtService
CustomUserDetailsService
PasswordEncoder
DaoAuthenticationProvider
AuthenticationManager
```

---

# 🏗️ Application Architecture

ShopSphere follows a clean layered architecture.

```text
                    Client
                      │
                      ▼
             Spring Boot REST API
                      │
                      ▼
              Spring Security / JWT
                      │
                      ▼
                  Controller
                      │
                      ▼
                     DTO
                      │
                      ▼
                   Mapper
                      │
                      ▼
                  Service
                      │
                      ▼
               Specifications
                      │
                      ▼
                 Repository
                      │
                      ▼
              Spring Data JPA
                      │
                      ▼
                    MySQL
```

---

# 📁 Project Structure

```text
                    src/main/java/com/shahid/shopsphere
                    │
                    ├── config
                    │   └── SecurityConfig.java
                    │
                    ├── controller
                    │   ├── AuthController.java
                    │   ├── ProductController.java
                    │   ├── CategoryController.java
                    │   ├── CartController.java
                    │   ├── OrderController.java
                    │   └── PaymentController.java
                    │
                    ├── dto
                    │
                    ├── entity
                    │
                    ├── exception
                    │
                    ├── mapper
                    │
                    ├── repository
                    │
                    ├── security
                    │   └── JwtAuthenticationFilter.java
                    │
                    ├── service
                    │
                    ├── specifications
                    │
                    ├── util
                    │
                    └── ShopSphereApplication.java
```

---

# 🛠️ Technology Stack

## Backend

| Technology | Usage |
|---|---|
| Java 21 | Programming Language |
| Spring Boot 3 | Backend Framework |
| Spring Security | Authentication & Authorization |
| JWT | Token-Based Authentication |
| Spring Data JPA | Data Access |
| Hibernate | ORM |
| Maven | Build & Dependency Management |
| Lombok | Boilerplate Reduction |
| Bean Validation | Request Validation |

## Database

| Technology | Usage |
|---|---|
| MySQL | Production Database |
| H2 | Testing Database |

## Testing

- JUnit 5
- Mockito
- Spring Boot Test
- MockMvc
- Integration Testing

## DevOps & Deployment

- Docker
- Docker Compose
- Docker Hub
- GitHub Actions
- Railway

## Documentation

- Swagger
- OpenAPI

---

# 🧪 Testing

ShopSphere includes automated testing at multiple levels.

## Unit Testing

Business logic is tested using:

- JUnit 5
- Mockito

## Repository Testing

Repository and persistence functionality can be tested using:

- Spring Data JPA
- H2 Database

## Controller Testing

REST controllers are tested using:

- Spring Boot Test
- MockMvc

## Integration Testing

Integration tests verify the interaction between different application layers.

### Maven Verification

```bash
mvn clean verify
```

---

# 🐳 Docker

ShopSphere is fully containerized using Docker.

### Docker Architecture

```text
                 Docker Compose
                       │
             ┌─────────┴─────────┐
             │                   │
             ▼                   ▼
      Spring Boot API          MySQL
        Container            Container
             │                   │
             └─────────┬─────────┘
                       │
                       ▼
                 Docker Network
                       │
                       ▼
                  MySQL Volume
```

### Docker Features

- Dockerfile
- Docker Compose
- Spring Boot Container
- MySQL Container
- Docker Networking
- Persistent MySQL Volume
- Environment-based configuration

---

# 🔄 CI/CD Pipeline

ShopSphere uses **GitHub Actions** for automated build and testing.

The CI/CD pipeline also builds and publishes the Docker image.

```text
                    Developer
                        │
                        ▼
                    git push origin main
                        │
                        ▼
                    GitHub Actions
                        │
                        ▼
                    Checkout Repository
                        │
                        ▼
                    Setup Java 21
                        │
                        ▼
                    Maven Build & Tests
                        │
                        ├───────────────┐
                        │               │
                      PASS            FAIL
                        │               │
                        ▼               ▼
                    Docker Build       STOP
                        │
                        ▼
                    Docker Hub Push
                        │
                        ▼
                    shahidjavadev/shopsphere:latest
                        │
                        ▼
                    Railway
                        │
                        ▼
                    Production API
```

### GitHub Actions Tasks

- Repository checkout
- Java 21 setup
- Maven dependency setup
- Application build
- Automated tests
- Docker image build
- Docker Hub authentication
- Docker image push

### Maven Verification

```bash
mvn clean verify
```

---

# 🐳 Docker Hub

The ShopSphere Docker image is published to Docker Hub.

### Image

```text
shahidjavadev/shopsphere:latest
```

### Pull Image

```bash
docker pull shahidjavadev/shopsphere:latest
```

### Run Container

```bash
docker run -p 8080:8080 shahidjavadev/shopsphere:latest
```

---

# ☁️ Railway Deployment

ShopSphere is deployed to Railway using the Docker image published to Docker Hub.

```text
                GitHub
                  │
                  ▼
                GitHub Actions
                  │
                  ├── Build
                  ├── Test
                  └── Docker Build
                          │
                          ▼
                      Docker Hub
                          │
                          │ shopsphere:latest
                          ▼
                      Railway
                          │
                          ├── Spring Boot Application
                          │
                          └── MySQL Database
                                │
                                ▼
                            Public API
```

### Deployment Details

| Component | Technology |
|---|---|
| Platform | Railway |
| Application | Spring Boot |
| Container | Docker |
| Docker Image | `shahidjavadev/shopsphere:latest` |
| Database | Railway MySQL |

---

# 🗄️ Database Configuration

The production application uses a **MySQL database running on Railway**.

The application receives database configuration through environment variables.

### Database Variables

```text
DB_URL
DB_USERNAME
DB_PASSWORD
```

### Railway Internal JDBC URL

```text
jdbc:mysql://mysql.railway.internal:3306/railway
```

> The actual database credentials are stored as environment variables and are not committed to GitHub.

---

# 🔐 Environment Variables

Sensitive configuration is handled using environment variables.

## Application Variables

```text
DB_URL
DB_USERNAME
DB_PASSWORD
JWT_SECRET
JWT_EXPIRATION
```

## CI/CD Secrets

Sensitive GitHub Actions credentials should be stored as GitHub repository secrets.

```text
DOCKER_USERNAME
DOCKER_PASSWORD
JWT_SECRET
```

> Never commit passwords, JWT secrets, Docker credentials, database credentials, or other sensitive values to the repository.

---

# 📡 REST API Endpoints

## 🔑 Authentication

| Method | Endpoint |
|---|---|
| `POST` | `/api/auth/register` |
| `POST` | `/api/auth/login` |

---

## 📂 Categories

| Method | Endpoint |
|---|---|
| `GET` | `/api/categories` |
| `GET` | `/api/categories/{id}` |
| `POST` | `/api/categories` |
| `PUT` | `/api/categories/{id}` |
| `DELETE` | `/api/categories/{id}` |

---

## 📦 Products

| Method | Endpoint |
|---|---|
| `GET` | `/api/products` |
| `GET` | `/api/products/{id}` |
| `POST` | `/api/products` |
| `PUT` | `/api/products/{id}` |
| `DELETE` | `/api/products/{id}` |

### Product Query Parameters

| Parameter | Purpose |
|---|---|
| `page` | Page number |
| `size` | Page size |
| `sortBy` | Field used for sorting |
| `direction` | Sort direction |
| `category` | Category filter |
| `brand` | Brand filter |
| `minPrice` | Minimum price |
| `maxPrice` | Maximum price |
| `keyword` | Keyword search |

### Example

```http
GET /api/products?page=0&size=10&sortBy=price&direction=desc
```

---

## 🛒 Cart

| Method | Endpoint |
|---|---|
| `GET` | `/api/cart` |
| `POST` | `/api/cart` |
| `PUT` | `/api/cart/{id}` |
| `DELETE` | `/api/cart/{id}` |

---

## 📦 Orders

| Method | Endpoint |
|---|---|
| `POST` | `/api/orders` |
| `GET` | `/api/orders` |
| `PUT` | `/api/orders/{id}/cancel` |

---

## 💳 Payments

| Method | Endpoint |
|---|---|
| `POST` | `/api/payments` |
| `GET` | `/api/payments/{id}` |

---

# 🔑 Using JWT Authentication

After successful login, the API returns a JWT token.

For protected APIs, send the token in the request header:

```http
Authorization: Bearer <JWT_TOKEN>
```

### cURL Example

```bash
curl -X GET \
  "https://renewed-delight-production-de66.up.railway.app/api/categories" \
  -H "Authorization: Bearer <JWT_TOKEN>"
```

Protected endpoints use the authenticated user's role and method-level authorization where configured.

---

# 📚 API Documentation

Swagger provides interactive API documentation.

### Local Swagger

http://localhost:8080/swagger-ui/index.html

### Production Swagger

https://renewed-delight-production-de66.up.railway.app/swagger-ui/index.html

### Production OpenAPI

https://renewed-delight-production-de66.up.railway.app/v3/api-docs

Swagger can be used to:

- Explore endpoints
- Send API requests
- Test authentication
- Test protected APIs
- View request models
- View response models

---

# 🚀 Running the Project Locally

## Prerequisites

Install:

- Java 21
- Maven
- MySQL
- Docker
- Docker Compose
- Git

---

## 1️⃣ Clone Repository

```bash
git clone https://github.com/SyedShahidAhamed/ShopSphere.git
```

## 2️⃣ Navigate to Project

```bash
cd ShopSphere
```

---

# 🐳 Option 1 — Docker Compose

### Build the Project

```bash
mvn clean package
```

### Start Containers

```bash
docker compose up
```

### Run in Background

```bash
docker compose up -d
```

### Check Running Containers

```bash
docker ps
```

### View Logs

```bash
docker compose logs -f
```

### Stop Containers

```bash
docker compose down
```

### Application

http://localhost:8080

---

# ☕ Option 2 — Run Spring Boot Locally

Configure MySQL in your local environment.

### Example Configuration

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/shopsphere
spring.datasource.username=root
spring.datasource.password=your_password
```

Then run:

```bash
mvn spring-boot:run
```

### Application

http://localhost:8080

---

# 🔍 API Testing

The APIs can be tested using:

- Swagger UI
- Postman
- cURL

For protected endpoints:

```http
Authorization: Bearer <JWT_TOKEN>
```

---

# 📈 Complete Project Workflow

```text
                      Developer
                          │
                          ▼
                      GitHub Repository
                          │
                          │ git push
                          ▼
                      GitHub Actions
                          │
                          ├───────────────┐
                          │               │
                          ▼               ▼
                      Maven Build     Automated Tests
                          │               │
                          └───────┬───────┘
                                  │
                                PASS
                                  │
                                  ▼
                            Docker Build
                                  │
                                  ▼
                              Docker Hub
                                  │
                                  ▼
                      shopsphere:latest
                                  │
                                  ▼
                              Railway
                              ┌────┴────┐
                              │         │
                              ▼         ▼
                      Spring Boot    MySQL
                          API       Database
                              │
                              ▼
                        Public REST API
                              │
                              ▼
                            Swagger
```

---

# 📊 Project Status

| Module | Status |
|---|---|
| Authentication | ✅ Complete |
| Authorization | ✅ Complete |
| JWT Security | ✅ Complete |
| Product Module | ✅ Complete |
| Category Module | ✅ Complete |
| Cart Module | ✅ Complete |
| Order Module | ✅ Complete |
| Payment Module | ✅ Complete |
| Pagination | ✅ Complete |
| Sorting | ✅ Complete |
| Dynamic Filtering | ✅ Complete |
| Validation | ✅ Complete |
| Exception Handling | ✅ Complete |
| Unit Testing | ✅ Complete |
| Repository Testing | ✅ Complete |
| Controller Testing | ✅ Complete |
| Integration Testing | ✅ Complete |
| Docker | ✅ Complete |
| Docker Compose | ✅ Complete |
| GitHub Actions | ✅ Complete |
| Docker Hub | ✅ Complete |
| Railway Deployment | ✅ Complete |
| Swagger / OpenAPI | ✅ Complete |

---

# 🔮 Future Improvements

The following features can be considered for future versions:

- Redis caching
- Email notifications
- Payment gateway integration using Razorpay or Stripe
- Advanced product search
- Product image management
- Inventory management
- Email-based account verification
- Password reset functionality
- Refresh token support
- Rate limiting
- API monitoring and observability
- More comprehensive test coverage
- React frontend
- Microservices architecture
- Kubernetes deployment
- AWS-based deployment
- Improved CI/CD automation

---

# 👨‍💻 Author

## Syed Shahid Ahamed

Computer Science / Software Engineering Student and Backend Developer focused on **Java, Spring Boot, REST APIs, SQL, and backend development**.

### GitHub

https://github.com/SyedShahidAhamed

### LinkedIn

https://www.linkedin.com/in/syed-shahid-ahamed-0717423a9/

---

# ⭐ Support

If you found this project useful or interesting, consider giving the repository a ⭐ on GitHub.

---

## 📄 License

This project is intended for learning, portfolio, and demonstration purposes.
