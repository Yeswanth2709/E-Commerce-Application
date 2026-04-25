# E-Commerce-Application

E-commerce backend containing multiple microservices built with **Java 21**, **Spring Boot 3.x**, and **Spring Cloud**.

## Architecture (Microservices)

This repository contains the following services:

- **ServiceDiscovery** — Eureka Server (service registry)
- **ApiGateway** — Spring Cloud Gateway (WebMVC) + Eureka client (single entry point for routing)
- **UserService** — User management (Spring Web, Spring Data JPA, Spring Security, MySQL)
- **ProductService** — Product/catalog service (Spring Web/WebFlux, Spring Data JPA, MySQL, Redis, Validation)
- **PaymentService** — Payment integrations (Razorpay + Stripe)
- **NotificationService** — Event-driven notifications using Kafka

## Tech Stack

- **Java:** 21
- **Build Tool:** Maven
- **Frameworks:** Spring Boot, Spring Cloud
- **Service Discovery:** Netflix Eureka
- **API Gateway:** Spring Cloud Gateway (server-webmvc)
- **Datastores:** MySQL (User/Product), Redis (Product)
- **Messaging:** Kafka (Notification service)
- **Security:** Spring Security (User service)
- **Payments:** Razorpay Java SDK, Stripe Java SDK

## Repository Structure

```text
.
├── ApiGateway/
├── ServiceDiscovery/
├── UserService/
├── ProductService/
├── PaymentService/
├── NotificationService/
└── README.md
```

## Prerequisites

- **JDK 21**
- **Maven 3.9+**
- (Recommended) **Docker** / Docker Compose for running dependencies:
  - MySQL
  - Redis
  - Kafka (+ Zookeeper, depending on your setup)

## How to Build

Each service is a standalone Spring Boot application with its own `pom.xml`.

Build a single service:
```bash
cd ServiceDiscovery
mvn clean package
```

Build all services (run from repo root, if you prefer one-by-one):
```bash
(cd ServiceDiscovery && mvn clean package)
(cd ApiGateway && mvn clean package)
(cd UserService && mvn clean package)
(cd ProductService && mvn clean package)
(cd PaymentService && mvn clean package)
(cd NotificationService && mvn clean package)
```

## How to Run (Suggested Order)

1) **ServiceDiscovery** (Eureka Server)
```bash
cd ServiceDiscovery
mvn spring-boot:run
```

2) Start the remaining services (each in its own terminal):
```bash
cd ApiGateway && mvn spring-boot:run
cd UserService && mvn spring-boot:run
cd ProductService && mvn spring-boot:run
cd PaymentService && mvn spring-boot:run
cd NotificationService && mvn spring-boot:run
```

Once running, services should register with Eureka, and traffic should be routed via the API Gateway.

## Configuration

Each service is expected to have its own Spring configuration (typically `application.yml` / `application.properties`) for:

- Service name + Eureka registration
- Server port
- MySQL connection details (UserService, ProductService)
- Redis config (ProductService)
- Kafka bootstrap servers + topics (NotificationService)
- Payment provider secrets (PaymentService: Razorpay / Stripe)

> Update configuration files with your local/dev credentials before running.

## Notes / Next Improvements (Optional)

- Add Docker Compose for MySQL + Redis + Kafka
- Add OpenAPI/Swagger documentation per service
- Add centralized config (Spring Cloud Config)
- Add distributed tracing/logging (Zipkin/Tempo + Micrometer)
