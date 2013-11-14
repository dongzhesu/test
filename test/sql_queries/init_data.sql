insert into tsystem(system_id, system_version) values ('100', '0.1');

insert into tlookup(lookup_id, system_id, lookup_key, lookup_value) values ('1', '100', 'custType_1', 'Trade Customer A');
insert into tlookup(lookup_id, system_id, lookup_key, lookup_value) values ('2', '100', 'custType_2', 'Trade Customer B');
insert into tlookup(lookup_id, system_id, lookup_key, lookup_value) values ('3', '100', 'custType_3', 'Trade Customer C');
insert into tlookup(lookup_id, system_id, lookup_key, lookup_value) values ('4', '100', 'custType_4', 'Retail Customer');
insert into tlookup(lookup_id, system_id, lookup_key, lookup_value) values ('5', '100', 'paymentType_1', 'Cash On Delivery');
insert into tlookup(lookup_id, system_id, lookup_key, lookup_value) values ('6', '100', 'paymentType_2', 'Net 30 Days');
insert into tlookup(lookup_id, system_id, lookup_key, lookup_value) values ('7', '100', 'paymentType_3', 'Other');

insert into tuser(tuid, system_id, user_login, user_password, user_language, user_create_time, user_last_modified_time) values ('100', '100', 'robby', '12345678', 'en', now(), now());

insert into tstorage(storage_id, storage_name, storage_desc, storage_create_time, storage_last_modified_time) values ('ST001', 'Storage A', 'Storage A Description', now(), now());

insert into tsales(sales_id, sales_name, sales_email, sales_phone, sales_create_time, sales_last_modified_time) values ('S001', 'Salesman A', 'sales_A001@erp.com', '123455678', now(), now());

insert into tcustomer(customer_id, sales_id, customer_number, customer_name, customer_type, payment_type, customer_contact, customer_phone, customer_fax, customer_email, customer_address, customer_create_time, customer_last_modified_time) values
('C00001', 'S001', '106876', 'Paul Parisi', '', '', 'Paul Parisi', '(852)6446 3891', '', '', 'Suite 1927,,Seidel and Shaw, LLC,Level19, 2 IFC, 8 Finance Street,Central', now(), now());

insert into tproduct (product_id, product_type, product_code, product_name, product_desc, product_year, product_country, product_price, product_price_2, product_qty, product_create_time, product_last_modified_time) values
('P00001', 'AB', 'AB00108', 'Product Wine A', 'Product Wine A Desc', '2008', '', 135, 135, 100, now(), now()),
('P00002', 'AB', 'AB00208', 'Product Wine B', 'Product Wine B Desc', '2007', '', 105, 105, 45, now(), now()),
('P00003', 'AB', 'AB00308', 'Product Wine C', 'Product Wine C Desc', '2010', '', 125.5, 125.5, 58, now(), now()),
('P00004', 'AB', 'AB00408', 'Product Wine D', 'Product Wine D Desc', '2009', '', 145, 145, 78, now(), now()),
('P00005', 'AB', 'AB00508', 'Product Wine E', 'Product Wine E Desc', '2005', '', 155, 155, 90, now(), now());

insert into torder(order_id, customer_id, order_type, order_number, order_price, order_date, delivery_date, order_create_time, order_last_modified_time) values
('PO00001', 'C00001', 'NV', 'NV00001', 1000, now(), now(), now(), now());