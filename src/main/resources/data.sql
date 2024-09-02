-- Insert initial data for customers
INSERT INTO customer (id, version_num, created_by, creation_date, last_modified_by, last_modified_date, name, lastname, phone_number, email, customer_type) VALUES
(1, 0, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'John', 'Doe', '1234567890', 'john.doe@example.com', 'PRIVATE'),
(2, 0, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'Jane', 'Smith', '0987654321', 'jane.smith@example.com', 'INDIVIDUAL');

-- Insert initial data for addresses
INSERT INTO address (id, version_num, created_by, creation_date, last_modified_by, last_modified_date, city, street, zip_code, customer_id) VALUES
(1, 0, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'New York', '5th Avenue', '10001', 1),
(2, 0, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'Los Angeles', 'Sunset Boulevard', '90001', 2);

-- Insert initial data for accounts
INSERT INTO account (id, version_num, created_by, creation_date, last_modified_by, last_modified_date, number_of_owners) VALUES
(1, 0, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 2);

-- Link customers to accounts
INSERT INTO account_customer (account_id, customer_id) VALUES (1, 1), (1, 2);
