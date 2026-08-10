# 🛒 ShopSphere - E-Commerce REST API

A production-ready **E-Commerce REST API** built using **Spring Boot, Spring Security, JWT, Spring Data JPA, MySQL, Docker, Docker Compose, GitHub Actions, Docker Hub, and Railway**.

ShopSphere demonstrates real-world backend development practices including authentication, role-based authorization, CRUD operations, pagination, sorting, dynamic filtering, validation, exception handling, automated testing, containerization, CI/CD, and cloud deployment.

---

## 🌐 Live Application

### Production API

**Railway**

https://renewed-delight-production-de66.up.railway.app

### Swagger UI

https://renewed-delight-production-de66.up.railway.app/swagger-ui/index.html

### OpenAPI Documentation

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

# 📦 Product Management

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

### Example
'''http
GET /api/products?page=0&size=10&sortBy=price&direction=desc

Category Filter

GET /api/products?category=Mobiles

Brand Filter

GET /api/products?brand=Apple

Price Filter

GET /api/products?minPrice=50000&maxPrice=150000

Keyword Search

GET /api/products?keyword=iphone

'''
# 📂 Category Management
-Create Category
-Update Category
-Delete Category
-Get Category by ID
-Get All Categories

Endpoints
GET    /api/categories
GET    /api/categories/{id}
POST   /api/categories
PUT    /api/categories/{id}
DELETE /api/categories/{id}

# 🛒 Cart Management
-Add Product to Cart
-Update Product Quantity
-Remove Product from Cart
-View User Cart
-Calculate Cart Total

Endpoints
GET    /api/cart
POST   /api/cart
PUT    /api/cart/{id}
DELETE /api/cart/{id}

# 📦 Order Management
-Place Order
-View Orders
-View Order Details
-Cancel Order
-Order Status Management

Endpoints
POST   /api/orders
GET    /api/orders
PUT    /api/orders/{id}/cancel

# 💳 Payment Module

The project contains a payment module with payment entities and REST APIs.

Implemented:

-Payment Entity
-Payment Status
-Payment Method
-Payment APIs

Endpoints
POST /api/payments
GET  /api/payments/{id}

#🛡️ Security Architecture

-ShopSphere uses Spring Security + JWT for authentication and authorization.

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

Security Components
        -SecurityConfig
        -JwtAuthenticationFilter
        -JwtService
        -CustomUserDetailsService
        -PasswordEncoder
        -DaoAuthenticationProvider
        -AuthenticationManager

# 🏗️ Application Architecture

ShopSphere follows a clean layered architecture.

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

# 📁 Project Structure

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

# 🛠️ Technology Stack

Backend
-Java 21
-Spring Boot 3
-Spring Security
-JWT
-Spring Data JPA
-Hibernate
-Maven
-Lombok
-Bean Validation

Database
-MySQL
-H2 Database for Testing

Testing
-JUnit 5
-Mockito
-Spring Boot Test
-MockMvc

DevOps
-Docker
-Docker Compose
-Docker Hub

GitHub Actions
-Railway

Documentation
-Swagger
-OpenAPI

# 🧪 Testing

ShopSphere includes automated testing at multiple levels.

Unit Testing

Business logic is tested using:

-JUnit 5
-Mockito

Repository Testing

Repository and persistence functionality can be tested using:

-Spring Data JPA
-H2 Database

Controller Testing

REST controllers are tested using:

-Spring Boot Test
-MockMvc

Integration Testing

Integration tests verify the interaction between different application layers.

Maven Verification
-mvn clean verify

# 🐳 Docker

ShopSphere is fully containerized using Docker.

Docker Components:

                 Docker Compose
                       │
             ┌─────────┴─────────┐
             │                   │
             ▼                   ▼
       Spring Boot API         MySQL
        Container            Container
             │                   │
             └─────────┬─────────┘
                       │
                       ▼
                Docker Network
                       │
                       ▼
                 MySQL Volume

Docker Features
-Dockerfile
-Docker Compose
-Spring Boot Container
-MySQL Container
-Docker Networking
-Persistent MySQL Volume
-Environment-based configuration

# configuration
# 🔄 CI/CD Pipeline

ShopSphere uses GitHub Actions for automated build and testing.

The CI/CD pipeline also builds and publishes the Docker image.

                       Developer
                           │
                           ▼
                    git push origin main
                           │
                           ▼
                 ┌────────────────────┐
                 │   GitHub Actions   │
                 └─────────┬──────────┘
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
                    ┌──────┴──────┐
                    │             │
                  PASS           FAIL
                   ✅              ❌
                    │             │
                    ▼             ▼
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

⚙️ GitHub Actions

The GitHub Actions workflow performs automated tasks such as:

-Repository checkout
-Java 21 setup
-Maven dependency setup
-Application build
-Automated tests
-Docker image build
-Docker Hub authentication
-Docker image push

Maven Verification
-mvn clean verify

Docker Image
-shahidjavadev/shopsphere:latest

# 🐳 Docker Hub

The ShopSphere Docker image is published to Docker Hub.

Image
-shahidjavadev/shopsphere:latest

Pull Image
-docker pull shahidjavadev/shopsphere:latest

Run Container
-docker run -p 8080:8080 shahidjavadev/shopsphere:latest

# ☁️ Railway Deployment

ShopSphere is deployed to Railway using the Docker image published to Docker Hub.

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

# Deployment

Platform:
Railway

Application:
Spring Boot

Container:
Docker

Docker Image:
shahidjavadev/shopsphere:latest

Database:
Railway MySQL

# 🗄️ Database Configuration

The production application uses a MySQL database running on Railway.

The application receives database configuration through environment variables.

DB_URL
DB_USERNAME
DB_PASSWORD

The production JDBC URL follows the Railway internal networking format:

-jdbc:mysql://mysql.railway.internal:3306/railway

The actual database credentials are stored as environment variables and are not committed to GitHub.

# 🔐 Environment Variables

Sensitive configuration is handled using environment variables.

Application Variables
DB_URL
DB_USERNAME
DB_PASSWORD
JWT_SECRET
JWT_EXPIRATION
CI/CD Secrets

Sensitive GitHub Actions credentials should be stored as GitHub repository secrets.

Example:

DOCKER_USERNAME
DOCKER_PASSWORD
JWT_SECRET

# REST API Endpoints

# Authentication

POST /api/auth/register
POST /api/auth/login

# Categories
GET    /api/categories
GET    /api/categories/{id}
POST   /api/categories
PUT    /api/categories/{id}
DELETE /api/categories/{id}

# Products
GET    /api/products
GET    /api/products/{id}
POST   /api/products
PUT    /api/products/{id}
DELETE /api/products/{id}

# Query Parameters
page
size
sortBy
direction
category
brand
minPrice
maxPrice
keyword

Example:
GET /api/products?page=0&size=10&sortBy=price&direction=desc

# Cart
GET    /api/cart
POST   /api/cart
PUT    /api/cart/{id}
DELETE /api/cart/{id}

# Orders
POST   /api/orders
GET    /api/orders
PUT    /api/orders/{id}/cancel

# Payments
POST /api/payments
GET  /api/payments/{id}

# 🔑 Using JWT Authentication

After successful login, the API returns a JWT token.

For protected APIs, send the token in the request header:

Authorization: Bearer <JWT_TOKEN>

Example:

curl -X GET \
  "https://renewed-delight-production-de66.up.railway.app/api/categories" \
  -H "Authorization: Bearer <JWT_TOKEN>"

# 📚 API Documentation

Swagger provides interactive API documentation.

Local Swagger
http://localhost:8080/swagger-ui/index.html
Production Swagger
https://renewed-delight-production-de66.up.railway.app/swagger-ui/index.html
Production OpenAPI
https://renewed-delight-production-de66.up.railway.app/v3/api-docs

Swagger can be used to:

-Explore endpoints
-Send API requests
-Test authentication
-Test protected APIs
-View request models
-View response models

# 🚀 Running the Project Locally
Prerequisites

Install:

Java 21
Maven
MySQL
Docker
Docker Compose
Git

1️⃣ Clone Repository
git clone https://github.com/SyedShahidAhamed/ShopSphere.git

2️⃣ Navigate to Project
cd ShopSphere

# 🐳 Option 1 — Docker Compose

Build the project:

mvn clean package

Start the containers:

docker compose up

Run in background:

docker compose up -d

Check running containers:

docker ps

View logs:

docker compose logs -f

Stop containers:

docker compose down

Application:

http://localhost:8080


# ☕ Option 2 — Run Spring Boot Locally

Configure MySQL in your local environment.

Example:

spring.datasource.url=jdbc:mysql://localhost:3306/shopsphere
spring.datasource.username=root
spring.datasource.password=your_password

Then run:

mvn spring-boot:run

Application:

http://localhost:8080

# 🔍 API Testing

The APIs can be tested using:

Swagger UI
Postman
cURL

For protected endpoints:

Authorization: Bearer <JWT_TOKEN>

# 📈 Complete Project Workflow

                    Developer
                        │
                        ▼
                   GitHub Repo
                        │
                   git push
                        │
                        ▼
                GitHub Actions
                        │
              ┌─────────┴─────────┐
              │                   │
              ▼                   ▼
        Maven Build           Automated Tests
              │                   │
              └─────────┬─────────┘
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
                ┌───────┴────────┐
                │                │
                ▼                ▼
          Spring Boot          MySQL
            API              Database
                │
                ▼
          Public REST API
                │
                ▼
             Swagger

# 🔮 Future Improvements

The following features are planned for future versions of ShopSphere:

- Redis Caching
- Email Notifications
- Razorpay Payment Gateway Integration
- Stripe Payment Gateway Integration
- React Frontend
- Advanced Product Search
- Rate Limiting
- Centralized Logging
- Monitoring and Observability
- Microservices Architecture
- Kubernetes Deployment
- AWS Cloud Deployment

# 👨‍💻 Author

## Syed Shahid Ahamed

Java Full Stack Developer | Spring Boot | REST APIs | Docker | CI/CD

### GitHub

https://github.com/SyedShahidAhamed

### LinkedIn

https://www.linkedin.com/in/syed-shahid-ahamed-0717423a9/