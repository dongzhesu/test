alter table tcustomer alter column customer_type type varchar(50);
alter table tcustomer add column customer_number varchar(25);

-- add new product_code, product_type, and product_country fields on tproduct (without preserve the table sequences)
alter table tproduct add column product_code varchar(25);
alter table tproduct add column product_type varchar(5);
alter table tproduct add column product_country varchar(50);

-- add new product_code field on tproduct (preserve the table sequences)
alter table tproduct add column product_code varchar(25), add column lproduct_name VARCHAR(50), add column lproduct_desc VARCHAR(150), add column lproduct_year VARCHAR(4),
add column lproduct_price NUMERIC(10,2), add column lproduct_price_2 NUMERIC(10,2), add column lproduct_qty INTEGER NULL, add column lcreate_time TIMESTAMP NOT NULL default now(),
add column llast_modified_time TIMESTAMP NOT NULL default now();
update tproduct set lproduct_name = product_name, lproduct_desc = product_desc, lproduct_year = product_year, lproduct_price = product_price, lproduct_price_2 = product_price_2,
lproduct_qty = product_qty, lcreate_time=product_create_time, llast_modified_time=product_last_modified_time;
alter table tproduct drop column product_name cascade, drop column product_desc cascade, drop column product_year cascade, drop column product_price cascade,
drop column product_price_2 cascade, drop column product_qty cascade, drop column product_create_time cascade, drop column product_last_modified_time cascade;
alter table tproduct rename column lproduct_name to product_name;
alter table tproduct rename column lproduct_desc to product_desc;
alter table tproduct rename column lproduct_year to product_year;
alter table tproduct rename column lproduct_price to product_price;
alter table tproduct rename column lproduct_price_2 to product_price_2;
alter table tproduct rename column lproduct_qty to product_qty;
alter table tproduct rename column lcreate_time to product_create_time;
alter table tproduct rename column llast_modified_time to product_last_modified_time;


-- add new order_type, delivery_date, and delivery_instructions fields on tproduct (without preserve the table sequences)
alter table torder add column delivery_date TIMESTAMP NULL;
alter table torder add column order_type varchar(5);
alter table torder add column remarks varchar(100);

-- add new delivery_date field on torder (preserve the table sequences)
alter table torder add column delivery_date TIMESTAMP NULL, add column lcreate_time TIMESTAMP NOT NULL default now(), add column llast_modified_time TIMESTAMP NOT NULL default now();
update torder set delivery_date = order_date, lcreate_time=order_create_time, llast_modified_time=order_last_modified_time;
alter table torder drop column order_create_time cascade, drop column order_last_modified_time cascade;
alter table torder rename column lcreate_time to order_create_time;
alter table torder rename column llast_modified_time to order_last_modified_time;