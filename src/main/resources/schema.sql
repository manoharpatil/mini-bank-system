-- Create table for customer
CREATE TABLE customer (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  version_num INT,
  created_by VARCHAR(50),
  creation_date TIMESTAMP,
  last_modified_by VARCHAR(50),
  last_modified_date TIMESTAMP,
  name VARCHAR(255),
  lastname VARCHAR(255),
  phone_number VARCHAR(20),
  email VARCHAR(255) UNIQUE,
  customer_type VARCHAR(20)
);

-- Create table for address
CREATE TABLE address (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  version_num INT,
  created_by VARCHAR(50),
  creation_date TIMESTAMP,
  last_modified_by VARCHAR(50),
  last_modified_date TIMESTAMP,
  city VARCHAR(255),
  street VARCHAR(255),
  zip_code VARCHAR(10),
  customer_id BIGINT,
  FOREIGN KEY (customer_id) REFERENCES customer(id)
);

-- Create table for account
CREATE TABLE account (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  version_num INT,
  created_by VARCHAR(50),
  creation_date TIMESTAMP,
  last_modified_by VARCHAR(50),
  last_modified_date TIMESTAMP,
  number_of_owners INT
);

-- Create table to link accounts and customers (Many-to-Many relationship)
CREATE TABLE account_customer (
  account_id BIGINT,
  customer_id BIGINT,
  FOREIGN KEY (account_id) REFERENCES account(id),
  FOREIGN KEY (customer_id) REFERENCES customer(id),
  PRIMARY KEY (account_id, customer_id)
);
