

Project Name

Warehouse Manager (MVP) – a system for managing warehouses, shipments, and packages.

 Project Goal

Create a minimal viable product (MVP) for tracking warehouse inventory, controlling shipments, and forming packages, with the possibility to scale the functionality in the future.


 Functional Requirements (MVP)

1. Warehouse Management

Create, edit, and delete warehouses.
Each warehouse has a capacity limit (e.g., cubic meters).
View current warehouse occupancy (used and available volume).

 2. Product Management
    
 Add, edit, and delete products.
 Each product has:

Name
Volume per unit (e.g., 0.5 m³ per box)
   Quantity in stock
 Check whether a product can fit in a warehouse (based on volume).

 3. Shipment Management

 Create a shipment:

Destination warehouse
List of products with quantities
Automatically update warehouse stock after a shipment.
Check warehouse capacity before adding a shipment.

4. Package Management

Create a package:

Source warehouse
List of products with quantities
Verify product availability in the warehouse before forming a package.
Automatically reduce warehouse stock after sending a package.

5. Reports and Views

 View products in a warehouse with quantity and occupied volume.
View shipments and packages with detailed product information.



#Technology Stack

* Java 21– primary programming language.
* Spring Boot – for building REST API.
* Spring Data JPA + PostgreSQL – for data storage .
* Lombok – reduces boilerplate code (getters, setters, constructors).
* Maven – build system.
* Swagger/OpenAPI – API documentation.
  

