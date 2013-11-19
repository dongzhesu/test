create table tsales(
	sales_id                 VARCHAR(25) PRIMARY KEY,
	sales_name               VARCHAR(150) NOT NULL,
	sales_email              VARCHAR(100) NULL,
	sales_phone              VARCHAR(50) NULL,
	sales_create_time        TIMESTAMP NOT NULL default now(),
	sales_last_modified_time TIMESTAMP NOT NULL default now()
);

create table tstorage(
	storage_id                 VARCHAR(25) PRIMARY KEY,
	storage_name               VARCHAR(50),
	storage_desc               VARCHAR(150),
	storage_create_time        TIMESTAMP NOT NULL default now(),
	storage_last_modified_time TIMESTAMP NOT NULL default now()
);

create table tcustomer(
	customer_id                 VARCHAR(25) PRIMARY KEY,
	sales_id                    VARCHAR(25) NOT NULL,
	customer_number             VARCHAR(25) NOT NULL,
	customer_name               VARCHAR(150) NOT NULL,
	customer_type               VARCHAR(50) NULL, -- corresponding to tlookup table
	payment_type                VARCHAR(10) NULL,
	customer_contact            VARCHAR(150) NULL,
	customer_phone              VARCHAR(50) NULL,
	customer_fax                VARCHAR(50) NULL,
	customer_email              VARCHAR(100) NULL,
	customer_address            VARCHAR(300) NULL,
	customer_create_time        TIMESTAMP NOT NULL default now(),
	customer_last_modified_time TIMESTAMP NOT NULL default now(),
	CONSTRAINT tcustomer_sales_id_fk FOREIGN KEY (sales_id) REFERENCES tsales (sales_id)
);

create table torder(
	order_id                 VARCHAR(25) PRIMARY KEY,
	customer_id              VARCHAR(25) NOT NULL,
	order_type               VARCHAR(5),
	order_number             VARCHAR(50),
	order_price              NUMERIC(10,2),
	order_date               TIMESTAMP NULL,
	delivery_date            TIMESTAMP NULL,
	order_create_time        TIMESTAMP NOT NULL default now(),
	order_last_modified_time TIMESTAMP NOT NULL default now(),
	CONSTRAINT torder_customer_id_fk FOREIGN KEY (customer_id) REFERENCES tcustomer (customer_id)
);

create table tproduct(
	product_id                 VARCHAR(25) PRIMARY KEY,
	product_type               VARCHAR(5),
	product_code               VARCHAR(25),
	product_name               VARCHAR(50),
	product_desc               VARCHAR(150),
	product_year               VARCHAR(4),
	product_country            VARCHAR(50),
	product_price              NUMERIC(10,2),
	product_price_2            NUMERIC(10,2),
	product_qty                INTEGER NULL,
	product_create_time        TIMESTAMP NOT NULL default now(),
	product_last_modified_time TIMESTAMP NOT NULL default now()
);

create table tbooking(
	order_id                   VARCHAR(25) NOT NULL,
	product_id                 VARCHAR(25) NOT NULL,
	booking_qty                INTEGER NULL,
	unit_price                 NUMERIC(10,2),
	discount                   INTEGER NULL,
	booking_price              NUMERIC(10,2),
	booking_create_time        TIMESTAMP NOT NULL default now(),
	booking_last_modified_time TIMESTAMP NOT NULL default now(),
	CONSTRAINT tbooking_order_id_fk FOREIGN KEY (order_id) REFERENCES torder (order_id),
	CONSTRAINT tbooking_product_id_fk FOREIGN KEY (product_id) REFERENCES tproduct (product_id),
	PRIMARY KEY (order_id, product_id)
);

create table tsystem(
	system_id                 VARCHAR(25) PRIMARY KEY,
	system_version            VARCHAR(50), -- keep track the current version
	system_last_modified_time TIMESTAMP NOT NULL default now()
);

create table tuser(
	tuid                    VARCHAR(25) PRIMARY KEY,
	system_id               VARCHAR(25) NOT NULL,
	user_login 	            VARCHAR(50) NOT NULL UNIQUE, -- login for authentication
	user_password           VARCHAR(50) NOT NULL,
	user_language           VARCHAR(20),
	user_create_time        TIMESTAMP NOT NULL default now(),
	user_last_modified_time TIMESTAMP NOT NULL default now(),
	CONSTRAINT tuser_system_id_fk FOREIGN KEY (system_id) REFERENCES tsystem (system_id)
);

create table tlookup(
	lookup_id    VARCHAR(25) PRIMARY KEY,
	system_id    VARCHAR(25) NOT NULL,
	lookup_key   VARCHAR(50) NOT NULL UNIQUE,
	lookup_value VARCHAR(50),
	CONSTRAINT tlookup_system_id_fk FOREIGN KEY (system_id) REFERENCES tsystem (system_id)
);

create table tcustomer_type_price(
	type_id                 VARCHAR(25) PRIMARY KEY,
	customer_type           VARCHAR(50) NOT NULL,
	product_id 	            VARCHAR(25) NOT NULL,
	product_price           NUMERIC(10,2),
	type_create_time        TIMESTAMP NOT NULL default now(),
	type_last_modified_time TIMESTAMP NOT NULL default now(),
	CONSTRAINT tcustomer_type_fk FOREIGN KEY (customer_type) REFERENCES tlookup (lookup_id),
	CONSTRAINT tproduct_id_fk FOREIGN KEY (product_id) REFERENCES tproduct (product_id)
);