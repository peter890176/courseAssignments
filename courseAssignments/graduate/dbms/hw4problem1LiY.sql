CREATE TABLE product_type (
    product_name VARCHAR(100) PRIMARY KEY,
FOREIGN KEY (genre_type) REFERENCES product_genre(genre_type)
);
CREATE TABLE product_type (
    genre_type VARCHAR(100)
);
CREATE TABLE valid_identification (
    id_type VARCHAR(100),
    FOREIGN KEY(serial_number) REFERENCES customer_order(serial_number)
);
CREATE TABLE customer_order (
    quantity_of_purchase INT,
    FOREIGN KEY(product_name) REFERENCES product_type(product_name),
    FOREIGN KEY(chain_id) REFERENCES grocery_store(chain_id)
);
CREATE TABLE grocery_store (
    chain_id INT,
    street_number VARCHAR(100),
    street_name VARCHAR(100),
    zip_code INT,
    state_abbrevation VARCHAR(10),
    open_time VARCHAR(100),
    closing_time VARCHAR(100),
    customer_information VARCHAR(100),
    manage_start_date VARCHAR(100),
    FOREIGN KEY(product_name) REFERENCES product_type(product_name),
    FOREIGN KEY(genre_type) REFERENCES product_genre(genre_type)
);
CREATE TABLE staff_member (
    staff_no INT,
    staff_type VARCHAR(100),
	first_name VARCHAR(100),
    last_name VARCHAR(100),
    FOREIGN KEY(chain_id) REFERENCES grocery_store(chain_id)
);
CREATE TABLE customer (
    id INT,
	first_name VARCHAR(100),
    last_name VARCHAR(100),
    local_address VARCHAR(100),
    FOREIGN KEY(id_type) REFERENCES valid_identification(id_type),
    FOREIGN KEY(credit_card_number) REFERENCES credit_card(credit_card_number)
);
CREATE TABLE credit_card (
    credit_card_number INT,
	credit_card_type VARCHAR(100),
    expiration_date VARCHAR(100),
    FOREIGN KEY(chain_id) REFERENCES grocery_store(chain_id)
);
