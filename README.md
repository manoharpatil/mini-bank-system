# Mini Bank System

## Description
This project is a microservice-based application for managing bank customers. It provides REST APIs to create, update, and search for customers.

## Features
- Create, update, and search for customers.
- Support for pagination in search results.
- Hibernate Envers for auditing entity changes.
- Dockerized application for easy deployment.

## Prerequisites
- Java 17
- Maven
- Docker (optional)

## Project Structure
```
mini-bank-system/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── minibank/
│   │   │           ├── config/
│   │   │           │   ├── AuditorAwareImpl.java
│   │   │           ├── controller/
│   │   │           │   ├── CustomerController.java
│   │   │           ├── dto/
│   │   │           │   ├── AddressDto.java
│   │   │           │   ├── CustomerDto.java
│   │   │           ├── entity/
│   │   │           │   ├── Account.java
│   │   │           │   ├── Address.java
│   │   │           │   ├── BaseEntity.java
│   │   │           │   ├── Customer.java
│   │   │           │   ├── CustomerType.java
│   │   │           ├── repository/
│   │   │           │   ├── AccountRepository.java
│   │   │           │   ├── CustomerRepository.java
│   │   │           ├── service/
│   │   │           │   ├── AccountService.java
│   │   │           │   ├── CustomerService.java
│   │   │           ├── MiniBankSystemApplication.java
│   │   ├── resources/
│   │   │   ├── application.yml
│   │   │   ├── data.sql
│   │   │   ├── logback.xml
│   │   │   ├── schema.sql
├── test/
│   ├── java/
│   │   └── com/
│   │       └── minibank/
│   │           ├── service/
│   │           │   ├── CustomerServiceTest.java
├── .gitignore
├── Dockerfile
├── pom.xml
├── postman_collection.json
├── README.md
```

## Launching the Application

1. **Build the Application:**
    - Run the following command to build the project using Maven:

   ```bash
   mvn clean install
   ```

2. **Run the Application:**
    - You can run the application using the built JAR file:

   ```bash
   java -jar target/mini-bank-system-0.0.1-SNAPSHOT.jar
   ```

    - Alternatively, use Docker to run the application:

   ```bash
   docker build -t mini-bank-system .
   docker run -p 8080:8080 mini-bank-system
   ```

## API Endpoints

### 1. Create Customer
- **URL:** `/api/customers`
- **Method:** `POST`
- **Request Body:** JSON object with customer personal data, including addresses.
- **Response:** Created customer details with HTTP status `201 Created`.

### 2. Update Customer
- **URL:** `/api/customers/{id}`
- **Method:** `PUT`
- **Request Body:** JSON object with customer personal data, including addresses.
- **Response:** Updated customer details with HTTP status `200 OK`.

### 3. Search Customers
- **URL:** `/api/customers/search`
- **Method:** `GET`
- **Query Params:** `term`, `page`, `size`
- **Response:** List of customers matching the search term with HTTP status `200 OK`.

## Testing
Import the Postman collection (`postman_collection.json`) and run the tests.

### Notes
- This project is designed with flexibility in mind, allowing future extensions such as adding more entities (e.g., transactions), enhancing security, or integrating with other systems.
- The use of Hibernate Envers provides a detailed audit trail for all changes to entities, which is essential for a banking system.