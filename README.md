Warehouse Manager (MVP)

A Warehouse Management System (WMS) designed to handle warehouses, shipments, and product packaging. This project represents the Minimum Viable Product (MVP) version, built with scalability in mind — the system will later evolve into a microservice-based architecture.

Project Goal

The goal is to create a robust MVP for managing warehouse inventories, controlling shipments, and forming product packages. In the next stages, each module will become a standalone microservice communicating through REST APIs or event-driven messaging (Kafka).

Functional Requirements (MVP)
1. Warehouse Management

Create, edit, and delete warehouses

Each warehouse has a defined capacity limit (e.g., in cubic meters)

View current occupancy — used and available volume

2. Product Management

Add, edit, and delete products

Each product includes: Name, Volume per unit (e.g., 0.5 m³ per box), Quantity in stock

Check if a product fits within a warehouse’s remaining capacity

3.Shipment Management

Create a shipment with destination warehouse and list of products/quantities

Automatically update warehouse stock after shipment

Validate capacity availability before shipment creation

4. Package Management

Create a package with source warehouse and list of products/quantities

Validate product availability before packaging

Automatically decrease stock levels after dispatch

 5. Reports & Views

View all products in a warehouse (with quantity and total occupied volume)

View shipments and packages with detailed product data

Future Architecture: Microservices

After the MVP phase, the system will transition into a distributed microservice architecture, where each module operates as an independent service: Warehouse Service (manages warehouses), Product Service (manages products), Shipment Service (controls shipments), Package Service (handles packaging), Report Service (analytics). Microservices will communicate via REST API or event-driven messaging (Kafka/RabbitMQ), with Eureka & Spring Cloud Gateway for service discovery and routing, and Spring Cloud Config Server for centralized configuration management.

Technology Stack

Language: Java 21 | Framework: Spring Boot 3 | Database: PostgreSQL | ORM: Spring Data JPA | Migrations: Liquibase | Build Tool: Maven | Documentation: Swagger / OpenAPI | Logging: SLF4J + Logback | Containerization: Docker & Docker Compose | Cloud & Scalability (Future): Spring Cloud, Eureka, Config Server, Gateway | Event Streaming (Future): Apache Kafka | Utilities: Lombok

Future Improvements

Add authentication & role-based access (Spring Security + JWT)

Implement microservice communication via Kafka

Integrate with Spring Cloud for distributed configuration and routing

Add Grafana & Prometheus monitoring dashboards



Author: Rukaiia R.
