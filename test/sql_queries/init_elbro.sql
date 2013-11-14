insert into tsales(sales_id, sales_name, sales_email, sales_phone, sales_create_time, sales_last_modified_time) values ('Sales001', 'Celine Wu', 'celine.wu@elbrowine.com', '(852)5136 8216', now(), now());

insert into tcustomer(customer_id, sales_id, customer_number, customer_name, customer_type, payment_type, customer_contact, customer_phone, customer_fax, customer_email, customer_address, customer_create_time, customer_last_modified_time) values
('C00001', 'Sales001', '106876', 'Paul Parisi', '', '', 'Paul Parisi', '(852)6446 3891', '', '', 'Suite 1927,,Seidel and Shaw, LLC,Level19, 2 IFC, 8 Finance Street,Central', now(), now()),
('C00002', 'Sales001', '115377', '0.5 Contemporary Japanese Cuisine & Bar', '', '', 'Clara', '(852)2504 2777', '', '', 'Shop B, G/F,,12-13 Shepherd Street,Tai Hang', now(), now()),
('C00003', 'Sales001', '106765', '1/5 Neuvo', '', '', 'David Wong', '(852)2521 0817', '', '', '9 Star Street,,,Wan Chai', now(), now()),
('C00004', 'Sales001', '106449', '12 Bottles Company Limited', '', '', 'Joseph', '(852)2546 7628', '', '', '503,,Hong Kong Worsted Mills Industrial Bld,31 Wo Tong Tsui Street,Kwai chung', now(), now()),
('C00005', 'Sales001', '115082', '1968 Restoran Indonesia', '', '', 'Alexander Chang', '(852)2577 9981', '', '', '5F,The L.Place,139 Queen''s Road,Hong Kong', now(), now()),
('C00006', 'Sales001', '106296', '208 Du Cento Otto (Luck Wealthy Ltd)', '', '', 'Joennee Tanjuakio', '(852)2549 0208', '', '', '21 Tai Ping Shan Street,,,Sheung Wan', now(), now()),
('C00007', 'Sales001', '106407', '798 Unit & Co. TST', '', '', 'Fion Chan', '(852)2868 2814', '', '', '1/F, 9 Hau Fook Street,,,Tsim Sha Tsui', now(), now()),
('C00008', 'Sales001', '106169', '798 Unit & Co.', '', '', 'Alex Chan', '(852)2506 0611', '', '', 'Unit 1203, 12/F, Food Forum, Times Square, Causeway Bay', now(), now()),
('C00009', 'Sales001', '115291', '0.5 Contemporary Japanese Cuisine & Bar', '', '', 'Clara', '(852)2504 2777', '', '', 'Shop B, G/F,,12-13 Shepherd Street,Tai Hang', now(), now()),
('C00010', 'Sales001', '115291', 'A Plus Wine Cellar Co Ltd', '', '', 'James Mak', '(852)2152 1999', '', '', 'Room 603 6/F,Sam Cheong Bldg,216-220 Des Voeux Road Central,Central', now(), now()),
('C00011', 'Sales001', '115071', 'A.Hartrodt Hong-Kong Ltd', '', '', 'Paul Duffy', '(852)3761 0338', '','', 'Room1207-16,12/F, Block B,Southmark, 11 Yip Hing St.,Won Chuk Hang', now(), now()),
('C00012', 'Sales001', '106741', 'AA Wu', '', '', 'AA Wu', '(852)9166 9709', '', '', 'Unit 501A, 5th Floor,9 Queen''s Road Central,,Central', now(), now()),
('C00013', 'Sales001', '115139', 'Aberdeen Boat Club', '', '', 'Terry Mung', '(852)2555 6216', '', '', '20 Shum Wan Road,,,Aberdeen', now(), now()),
('C00014', 'Sales001', '106685', 'Jumbo Floating Restaurant', '', '', 'Andy Lai', '(852)2873 7168', '', '', 'Shum Wan Pier Drive,,Wong Chuk Hang,,Aberdeen',  now(), now()),
('C00015', 'Sales001', '106749', 'Dragon Seal', '', '', 'Jovi', '(852)2568 9886', '', '', '101/F, International Commerce Centre,1 Austin Road West,,Kowloon',  now(), now()),
('C00016', 'Sales001', '106783', 'Absolutely Fabulous Company Limited', '', '', 'Connie Leung', '(852)2559 0997', '', '','LG/F,75-75A Hollywood Road,,Central', now(), now()),
('C00017', 'Sales001', '106365', 'Adelaide Cellar Door HK Limited', '', '', 'Celia Wong', '(852)2526 0151', '','', 'Room 801, 8/F,Tai Yau Building,181 Johnston Road,Wanchai', now(), now()),
('C00018', 'Sales001', '106543', 'Adrien Ellul', '', '', 'Adrien Ellul', '(852)9558 0451', '', '', 'Flat C 25/F Tower 1,Bel-Air No. 8, Bel-Air on the Peak,,Island South,No 8 Bel-Air Peak Avenue', now(), now()),
('C00019', 'Sales001', '115193', 'ADV Wine Trading Company', '', '', 'Andrew Mok', '(852)6333 2166', '','', '7 unit, Room A, 11/F,Goodyear Ind. Bld.,119 - 121 How Ming St.,Kwun Tong', now(), now()),
('C00020', 'Sales001', '106224', 'Aedes Bar', '', '', 'Nicky Yau','(852)6052 7783', '', '', 'G/F Front,14 Yuen Yuen Street,,Happy Valley',  now(), now());

insert into tproduct (product_id, product_type, product_code, product_name, product_desc, product_year, product_country, product_price, product_price_2, product_qty, product_create_time, product_last_modified_time) values
('P00001', 'AB', 'AB00108', 'Arbois Bethanie White', 'Arbois Bethanie White', '2008', '', 135, 135, 0, now(), now()),
('P00002', 'AB', 'AB00209', 'Arbois Poulsard Red', 'Arbois Poulsard Red', '2009', '', 75, 75, 0, now(), now()),
('P00003', 'AB', 'AB00308', 'Arbois Rubis Red', 'Arbois Rubis Red', '2008', '', 88, 88, 0, now(), now()),
('P00004', 'RG', 'RG00408', 'Irouleguy Red Domaine De Mignaberry', 'Irouleguy Red Domaine De Mignaberry', '2008', '', 119, 119, 0, now(), now()),
('P00005', 'RG', 'RG00509', 'Irouleguy Red Gorri D''Ansa', 'Irouleguy Red Gorri D''Ansa', '2009', '', 90, 90, 0, now(), now()),
('P00006', 'RG', 'RG00609', 'Irouleguy Red Premia', 'Irouleguy Red Premia', '2009', '', 78, 78, 0, now(), now()),
('P00007', 'JN', 'JN00710', 'Beaujolais Villages Elegance Red', 'Beaujolais Villages Elegance Red', '2010', '', 67.5, 67.5, 0, now(), now()),
('P00008', 'JN', 'JN00810', 'Beaujolais Villages L''Amitie Red', 'Beaujolais Villages L''Amitie Red', '2010', '', 68, 68, 0, now(), now()),
('P00009', 'JN', 'JN00910', 'Julienas Les Armoiries Red', 'Julienas Les Armoiries Red', '2010', '', 88, 88, 0, now(), now()),
('P00010', 'JN', 'JN01010', 'Julienas Tradition Du Bois De La Salle Red', 'Julienas Tradition Du Bois De La Salle Red', '2010', '', 88, 88, 0, now(), now());