insert into tsystem(system_id, system_version) values ('100', '0.1');

insert into tlookup(lookup_id, system_id, lookup_key, lookup_value) values ('1', '100', 'cust_type_P', 'Person');
insert into tlookup(lookup_id, system_id, lookup_key, lookup_value) values ('2', '100', 'cust_type_C', 'Company');
insert into tlookup(lookup_id, system_id, lookup_key, lookup_value) values ('3', '100', 'payment_type_Cash', 'Cash');
insert into tlookup(lookup_id, system_id, lookup_key, lookup_value) values ('4', '100', 'payment_type_CreditCard', 'Credit Card');

insert into tuser(tuid, system_id, user_login, user_password, user_language, user_create_time, user_last_modified_time) values ('100', '100', 'robby', '12345678', 'en', now(), now());

insert into tstorage(storage_id, storage_name, storage_desc, storage_create_time, storage_last_modified_time) values ('ST001', 'Storage A', 'Storage A Description', now(), now());

insert into tsales(sales_id, sales_name, sales_email, sales_phone, sales_create_time, sales_last_modified_time) values ('S001', 'Salesman A', 'sales_A001@erp.com', '123455678', now(), now());

insert into tproduct(product_id, product_name, product_desc, product_year, product_price, product_price_2, product_qty, product_create_time, product_last_modified_time) values ('P001', 'Product Wine A', 'Product Wine A Desc', '1988', 1500, 1800, 888, now(), now());

insert into tcustomer(customer_id, sales_id, customer_name, customer_type, payment_type, customer_contact, customer_phone, customer_fax, customer_email, customer_address, customer_create_time, customer_last_modified_time)
values ('C001', 'S001', 'Customer A', 'P', 'Cash', 'Contact Customer A', '12345678', '12345678', 'customer_A001@erp.com', 'HK', now(), now());

insert into torder(order_id, customer_id, order_number, order_price, order_date, order_create_time, order_last_modified_time) values ('O001', 'C001', 'Order_001', 1000, now(), now(), now());