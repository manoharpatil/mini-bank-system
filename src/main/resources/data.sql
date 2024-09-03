-- Insert initial data for customers
INSERT INTO customer (id, version_num, created_by, creation_date, last_modified_by, last_modified_date, name, lastname, phone_number, email, customer_type) VALUES
(1, 0, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'John_1', 'Doe_1', '12345678901', 'john.doe_1@example.com', 'PRIVATE'),
(2, 0, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'Jane_1', 'Smith_1', '09876543211', 'jane.smith_1@example.com', 'INDIVIDUAL');

-- Insert initial data for addresses
INSERT INTO address (id, version_num, created_by, creation_date, last_modified_by, last_modified_date, city, street, zip_code, customer_id) VALUES
(1, 0, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'New York_1', '5th Avenue_1', '10001', 1),
(2, 0, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'Los Angeles_1', 'Sunset Boulevard_1', '90001', 2);

-- Insert initial data for accounts
INSERT INTO account (id, version_num, created_by, creation_date, last_modified_by, last_modified_date, number_of_owners) VALUES
(1, 0, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 2);

-- Link customers to accounts
INSERT INTO account_customer (account_id, customer_id) VALUES (1, 1), (1, 2);
