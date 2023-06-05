# Database Documentation

This documentation describes the database schema, seeding, available API requests, and security measures for the `m295` database.

## Database Schema

Our database consists of four tables:

- `users`
- `Products`
- `PurchaseHistory`
- `PurchaseToProductMapping`

### `users`

This table stores user data.

```sql
CREATE TABLE IF NOT EXISTS users (
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    PRIMARY KEY (id)
);
```

```sql
CREATE TABLE IF NOT EXISTS Products (
    id INT NOT NULL AUTO_INCREMENT,
    productname VARCHAR(255),
    seller VARCHAR(255),
    price DOUBLE,
    PRIMARY KEY (id)
);
```

```sql
CREATE TABLE IF NOT EXISTS PurchaseHistory (
    id INT NOT NULL AUTO_INCREMENT,
    userId int,
    isPending BOOL,
    PRIMARY KEY (id),
    FOREIGN KEY (userId) REFERENCES users(id)
);
```

```sql
Create Table IF NOT EXISTS PurchaseToProductMapping (
    purchaseId int,
    productId int,
    quantity int,
    FOREIGN KEY (purchaseId) REFERENCES PurchaseHistory(id),
    FOREIGN KEY (productId) REFERENCES Products(id)
);
```
```sql
-- Inserting test data for users table
INSERT INTO users (username, email, password)
VALUES
    ('John Doe', 'john@example.com', 'password123'),
    ('Jane Smith', 'jane@example.com', 'password456'),
    ('Robert Johnson', 'robert@example.com', 'password789');

-- Inserting test data for Products table
INSERT INTO Products (productname, seller, price)
VALUES
    ('Product A', 'Seller A', 10.99),
    ('Product B', 'Seller B', 19.99),
    ('Product C', 'Seller C', 5.99);

-- Inserting test data for PurchaseHistory table
INSERT INTO PurchaseHistory (userId, isPending)
VALUES
    (1, TRUE),
    (2, FALSE),
    (3, TRUE);

-- Inserting test data for PurchaseToProductMapping table
INSERT INTO PurchaseToProductMapping (purchaseId, productId, quantity)
VALUES
    (1, 1, 2),
    (1, 2, 1),
    (2, 3, 3),
    (3, 1, 1),
    (3, 3, 2);
```

# Spring Boot REST API Documentation

## Overview

This is a Spring Boot REST API for Online Store. The server can be reached at `http://localhost:8080` for HTTP and `https://localhost:8443` for HTTPS.

## Paths

### /user/{userId}

#### GET
- Description: Get single user with no other information
- Parameters: userId (Path, Integer, Required)
- Responses:
    - 200 (application/json): User found

#### DELETE
- Description: Delete single user out of the database
- Parameters: userId (Path, Integer, Required)
- Responses:
    - 204: User removed

#### PUT
- Description: Update a user
- Parameters: userId (Path, Integer, Required)
- Request Body: (application/json) User
- Responses:
    - 204: User has been updated correctly

### /user

#### POST
- Description: Create a new User
- Request Body: (application/json) User
- Responses:
    - 201: User has been created correctly

### /product/{productId}/user

#### GET
- Description: Get users that bought this product
- Parameters: productId (Path, Integer, Required)
- Responses:
    - 200 (application/json): Users found

### /users

#### GET
- Description: Get all users
- Responses:
    - 200 (application/json): OK

#### POST
- Description: Create a new user
- Request Body: (application/json) User
- Responses:
    - 200: OK

### /salehistory/{saleHistoryId}

#### GET
- Description: Get a sale history by ID
- Parameters: saleHistoryId (Path, Integer, Required)
- Responses:
    - 200 (application/json): OK

### /products

#### GET
- Description: Get all products
- Responses:
    - 200 (application/json): OK

#### POST
- Description: Create a new product
- Request Body: (application/json) Product
- Responses:
    - 200: OK

### /products/{productId}

#### GET
- Description: Get a product by ID
- Parameters: productId (Path, Integer, Required)
- Responses:
    - 200 (application/json): OK

#### PUT
- Description: Update a product by ID
- Parameters: productId (Path, Integer, Required)
- Request Body: (application/json) Product
- Responses:
    - 204: Product has been updated correctly

#### DELETE
- Description: Delete a product by ID
- Parameters: productId (Path, Integer, Required)
- Responses:
    - 204: Product has been removed




## Authentication

You are authenticated using basic Auth. The username is 'user' and the password is '1234'