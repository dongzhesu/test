--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: tbooking; Type: TABLE; Schema: public; Owner: robby; Tablespace: 
--

CREATE TABLE tbooking (
    order_id character varying(25) NOT NULL,
    product_id character varying(25) NOT NULL,
    booking_qty integer,
    unit_price numeric(10,2),
    discount integer,
    booking_price numeric(10,2),
    booking_create_time timestamp without time zone DEFAULT now() NOT NULL,
    booking_last_modified_time timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.tbooking OWNER TO robby;

--
-- Name: tcustomer; Type: TABLE; Schema: public; Owner: robby; Tablespace: 
--

CREATE TABLE tcustomer (
    customer_id character varying(25) NOT NULL,
    sales_id character varying(25) NOT NULL,
    customer_number character varying(25) NOT NULL,
    customer_name character varying(150) NOT NULL,
    customer_type character varying(50),
    payment_type character varying(10),
    customer_contact character varying(150),
    customer_phone character varying(50),
    customer_fax character varying(50),
    customer_email character varying(100),
    customer_address character varying(300),
    customer_create_time timestamp without time zone DEFAULT now() NOT NULL,
    customer_last_modified_time timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.tcustomer OWNER TO robby;

--
-- Name: tcustomer_type_price; Type: TABLE; Schema: public; Owner: robby; Tablespace: 
--

CREATE TABLE tcustomer_type_price (
    type_id character varying(25) NOT NULL,
    customer_type character varying(50) NOT NULL,
    product_id character varying(25) NOT NULL,
    product_price numeric(10,2),
    type_create_time timestamp without time zone DEFAULT now() NOT NULL,
    type_last_modified_time timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.tcustomer_type_price OWNER TO robby;

--
-- Name: tlookup; Type: TABLE; Schema: public; Owner: robby; Tablespace: 
--

CREATE TABLE tlookup (
    lookup_id character varying(25) NOT NULL,
    system_id character varying(25) NOT NULL,
    lookup_key character varying(50) NOT NULL,
    lookup_value character varying(50)
);


ALTER TABLE public.tlookup OWNER TO robby;

--
-- Name: torder; Type: TABLE; Schema: public; Owner: robby; Tablespace: 
--

CREATE TABLE torder (
    order_id character varying(25) NOT NULL,
    customer_id character varying(25) NOT NULL,
    order_type character varying(5),
    order_number character varying(50),
    order_price numeric(10,2),
    order_date timestamp without time zone,
    delivery_date timestamp without time zone,
    order_create_time timestamp without time zone DEFAULT now() NOT NULL,
    order_last_modified_time timestamp without time zone DEFAULT now() NOT NULL,
    remarks character varying(100)
);


ALTER TABLE public.torder OWNER TO robby;

--
-- Name: tproduct; Type: TABLE; Schema: public; Owner: robby; Tablespace: 
--

CREATE TABLE tproduct (
    product_id character varying(25) NOT NULL,
    product_type character varying(5),
    product_code character varying(25),
    product_name character varying(50),
    product_desc character varying(150),
    product_year character varying(4),
    product_country character varying(50),
    product_price numeric(10,2),
    product_price_2 numeric(10,2),
    product_qty integer,
    product_create_time timestamp without time zone DEFAULT now() NOT NULL,
    product_last_modified_time timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.tproduct OWNER TO robby;

--
-- Name: tsales; Type: TABLE; Schema: public; Owner: robby; Tablespace: 
--

CREATE TABLE tsales (
    sales_id character varying(25) NOT NULL,
    sales_name character varying(150) NOT NULL,
    sales_email character varying(100),
    sales_phone character varying(50),
    sales_create_time timestamp without time zone DEFAULT now() NOT NULL,
    sales_last_modified_time timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.tsales OWNER TO robby;

--
-- Name: tstorage; Type: TABLE; Schema: public; Owner: robby; Tablespace: 
--

CREATE TABLE tstorage (
    storage_id character varying(25) NOT NULL,
    storage_name character varying(50),
    storage_desc character varying(150),
    storage_create_time timestamp without time zone DEFAULT now() NOT NULL,
    storage_last_modified_time timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.tstorage OWNER TO robby;

--
-- Name: tsystem; Type: TABLE; Schema: public; Owner: robby; Tablespace: 
--

CREATE TABLE tsystem (
    system_id character varying(25) NOT NULL,
    system_version character varying(50),
    system_last_modified_time timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.tsystem OWNER TO robby;

--
-- Name: tuser; Type: TABLE; Schema: public; Owner: robby; Tablespace: 
--

CREATE TABLE tuser (
    tuid character varying(25) NOT NULL,
    system_id character varying(25) NOT NULL,
    user_login character varying(50) NOT NULL,
    user_password character varying(50) NOT NULL,
    user_language character varying(20),
    user_create_time timestamp without time zone DEFAULT now() NOT NULL,
    user_last_modified_time timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.tuser OWNER TO robby;

--
-- Data for Name: tbooking; Type: TABLE DATA; Schema: public; Owner: robby
--

COPY tbooking (order_id, product_id, booking_qty, unit_price, discount, booking_price, booking_create_time, booking_last_modified_time) FROM stdin;
\.


--
-- Data for Name: tcustomer; Type: TABLE DATA; Schema: public; Owner: robby
--

COPY tcustomer (customer_id, sales_id, customer_number, customer_name, customer_type, payment_type, customer_contact, customer_phone, customer_fax, customer_email, customer_address, customer_create_time, customer_last_modified_time) FROM stdin;
C00001	S001	106876	Paul Parisi			Paul Parisi	(852)6446 3891			Suite 1927,,Seidel and Shaw, LLC,Level19, 2 IFC, 8 Finance Street,Central	2013-11-14 23:33:17.717	2013-11-14 23:33:17.717
\.


--
-- Data for Name: tcustomer_type_price; Type: TABLE DATA; Schema: public; Owner: robby
--

COPY tcustomer_type_price (type_id, customer_type, product_id, product_price, type_create_time, type_last_modified_time) FROM stdin;
031090428044457	1	934608479863684	120.00	2013-11-19 00:56:43.169	2013-11-19 00:56:43.169
040462976296825	2	934608479863684	120.00	2013-11-19 00:56:43.169	2013-11-19 00:56:43.169
046388792692130	3	934608479863684	120.00	2013-11-19 00:56:43.169	2013-11-19 00:56:43.169
051334214408819	4	934608479863684	120.00	2013-11-19 00:56:43.169	2013-11-19 00:56:43.169
\.


--
-- Data for Name: tlookup; Type: TABLE DATA; Schema: public; Owner: robby
--

COPY tlookup (lookup_id, system_id, lookup_key, lookup_value) FROM stdin;
1	100	custType_1	Trade Customer A
2	100	custType_2	Trade Customer B
3	100	custType_3	Trade Customer C
4	100	custType_4	Retail Customer
5	100	paymentType_1	Cash On Delivery
6	100	paymentType_2	Net 30 Days
7	100	paymentType_3	Other
\.


--
-- Data for Name: torder; Type: TABLE DATA; Schema: public; Owner: robby
--

COPY torder (order_id, customer_id, order_type, order_number, order_price, order_date, delivery_date, order_create_time, order_last_modified_time, remarks) FROM stdin;
PO00001	C00001	NV	NV00001	1000.00	2013-11-14 23:56:48.778	2013-11-14 23:56:48.778	2013-11-14 23:56:48.778	2013-11-14 23:56:48.778	\N
\.


--
-- Data for Name: tproduct; Type: TABLE DATA; Schema: public; Owner: robby
--

COPY tproduct (product_id, product_type, product_code, product_name, product_desc, product_year, product_country, product_price, product_price_2, product_qty, product_create_time, product_last_modified_time) FROM stdin;
P00001	AB	AB00108	Product Wine A	Product Wine A Desc	2008		135.00	135.00	100	2013-11-14 23:53:39.778	2013-11-14 23:53:39.778
P00002	AB	AB00208	Product Wine B	Product Wine B Desc	2007		105.00	105.00	45	2013-11-14 23:53:39.778	2013-11-14 23:53:39.778
P00003	AB	AB00308	Product Wine C	Product Wine C Desc	2010		125.50	125.50	58	2013-11-14 23:53:39.778	2013-11-14 23:53:39.778
P00004	AB	AB00408	Product Wine D	Product Wine D Desc	2009		145.00	145.00	78	2013-11-14 23:53:39.778	2013-11-14 23:53:39.778
934608479863684	AB	AB00608	Product Wine F	\N	2001	GB	120.00	120.00	9	2013-11-19 00:56:33.495	2013-11-19 02:06:32.205
P00005	AB	AB00508	Product Wine E	\N	2005	HK	145.00	145.00	90	2013-11-14 23:53:39.778	2013-11-19 02:07:26.021
\.


--
-- Data for Name: tsales; Type: TABLE DATA; Schema: public; Owner: robby
--

COPY tsales (sales_id, sales_name, sales_email, sales_phone, sales_create_time, sales_last_modified_time) FROM stdin;
S001	Salesman A	sales_A001@erp.com	123455678	2013-11-14 23:32:51.682	2013-11-14 23:32:51.682
\.


--
-- Data for Name: tstorage; Type: TABLE DATA; Schema: public; Owner: robby
--

COPY tstorage (storage_id, storage_name, storage_desc, storage_create_time, storage_last_modified_time) FROM stdin;
ST001	Storage A	Storage A Description	2013-11-14 23:32:51.667	2013-11-14 23:32:51.667
\.


--
-- Data for Name: tsystem; Type: TABLE DATA; Schema: public; Owner: robby
--

COPY tsystem (system_id, system_version, system_last_modified_time) FROM stdin;
100	0.1	2013-10-10 00:04:18.094
\.


--
-- Data for Name: tuser; Type: TABLE DATA; Schema: public; Owner: robby
--

COPY tuser (tuid, system_id, user_login, user_password, user_language, user_create_time, user_last_modified_time) FROM stdin;
100	100	robby	12345678	en	2013-10-10 00:04:18.114	2013-10-10 00:04:18.114
\.


--
-- Name: tbooking_pkey; Type: CONSTRAINT; Schema: public; Owner: robby; Tablespace: 
--

ALTER TABLE ONLY tbooking
    ADD CONSTRAINT tbooking_pkey PRIMARY KEY (order_id, product_id);


--
-- Name: tcustomer_pkey; Type: CONSTRAINT; Schema: public; Owner: robby; Tablespace: 
--

ALTER TABLE ONLY tcustomer
    ADD CONSTRAINT tcustomer_pkey PRIMARY KEY (customer_id);


--
-- Name: tcustomer_type_price_pkey; Type: CONSTRAINT; Schema: public; Owner: robby; Tablespace: 
--

ALTER TABLE ONLY tcustomer_type_price
    ADD CONSTRAINT tcustomer_type_price_pkey PRIMARY KEY (type_id);


--
-- Name: tlookup_lookup_key_key; Type: CONSTRAINT; Schema: public; Owner: robby; Tablespace: 
--

ALTER TABLE ONLY tlookup
    ADD CONSTRAINT tlookup_lookup_key_key UNIQUE (lookup_key);


--
-- Name: tlookup_pkey; Type: CONSTRAINT; Schema: public; Owner: robby; Tablespace: 
--

ALTER TABLE ONLY tlookup
    ADD CONSTRAINT tlookup_pkey PRIMARY KEY (lookup_id);


--
-- Name: torder_pkey; Type: CONSTRAINT; Schema: public; Owner: robby; Tablespace: 
--

ALTER TABLE ONLY torder
    ADD CONSTRAINT torder_pkey PRIMARY KEY (order_id);


--
-- Name: tproduct_pkey; Type: CONSTRAINT; Schema: public; Owner: robby; Tablespace: 
--

ALTER TABLE ONLY tproduct
    ADD CONSTRAINT tproduct_pkey PRIMARY KEY (product_id);


--
-- Name: tsales_pkey; Type: CONSTRAINT; Schema: public; Owner: robby; Tablespace: 
--

ALTER TABLE ONLY tsales
    ADD CONSTRAINT tsales_pkey PRIMARY KEY (sales_id);


--
-- Name: tstorage_pkey; Type: CONSTRAINT; Schema: public; Owner: robby; Tablespace: 
--

ALTER TABLE ONLY tstorage
    ADD CONSTRAINT tstorage_pkey PRIMARY KEY (storage_id);


--
-- Name: tsystem_pkey; Type: CONSTRAINT; Schema: public; Owner: robby; Tablespace: 
--

ALTER TABLE ONLY tsystem
    ADD CONSTRAINT tsystem_pkey PRIMARY KEY (system_id);


--
-- Name: tuser_pkey; Type: CONSTRAINT; Schema: public; Owner: robby; Tablespace: 
--

ALTER TABLE ONLY tuser
    ADD CONSTRAINT tuser_pkey PRIMARY KEY (tuid);


--
-- Name: tuser_user_login_key; Type: CONSTRAINT; Schema: public; Owner: robby; Tablespace: 
--

ALTER TABLE ONLY tuser
    ADD CONSTRAINT tuser_user_login_key UNIQUE (user_login);


--
-- Name: fk15e35d653e04219; Type: FK CONSTRAINT; Schema: public; Owner: robby
--

ALTER TABLE ONLY tbooking
    ADD CONSTRAINT fk15e35d653e04219 FOREIGN KEY (product_id) REFERENCES tproduct(product_id);


--
-- Name: fk15e35d655c52d579; Type: FK CONSTRAINT; Schema: public; Owner: robby
--

ALTER TABLE ONLY tbooking
    ADD CONSTRAINT fk15e35d655c52d579 FOREIGN KEY (order_id) REFERENCES torder(order_id);


--
-- Name: fk5323cc52866594b9; Type: FK CONSTRAINT; Schema: public; Owner: robby
--

ALTER TABLE ONLY tcustomer
    ADD CONSTRAINT fk5323cc52866594b9 FOREIGN KEY (sales_id) REFERENCES tsales(sales_id);


--
-- Name: fk699923f8fe410bb; Type: FK CONSTRAINT; Schema: public; Owner: robby
--

ALTER TABLE ONLY tuser
    ADD CONSTRAINT fk699923f8fe410bb FOREIGN KEY (system_id) REFERENCES tsystem(system_id);


--
-- Name: fk6ffed2113e04219; Type: FK CONSTRAINT; Schema: public; Owner: robby
--

ALTER TABLE ONLY tcustomer_type_price
    ADD CONSTRAINT fk6ffed2113e04219 FOREIGN KEY (product_id) REFERENCES tproduct(product_id);


--
-- Name: fk6ffed211c0512dd6; Type: FK CONSTRAINT; Schema: public; Owner: robby
--

ALTER TABLE ONLY tcustomer_type_price
    ADD CONSTRAINT fk6ffed211c0512dd6 FOREIGN KEY (customer_type) REFERENCES tlookup(lookup_id);


--
-- Name: fkb6ee82ee8fe410bb; Type: FK CONSTRAINT; Schema: public; Owner: robby
--

ALTER TABLE ONLY tlookup
    ADD CONSTRAINT fkb6ee82ee8fe410bb FOREIGN KEY (system_id) REFERENCES tsystem(system_id);


--
-- Name: fkcc43af5ac761aa1b; Type: FK CONSTRAINT; Schema: public; Owner: robby
--

ALTER TABLE ONLY torder
    ADD CONSTRAINT fkcc43af5ac761aa1b FOREIGN KEY (customer_id) REFERENCES tcustomer(customer_id);


--
-- Name: tbooking_order_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: robby
--

ALTER TABLE ONLY tbooking
    ADD CONSTRAINT tbooking_order_id_fk FOREIGN KEY (order_id) REFERENCES torder(order_id);


--
-- Name: tbooking_product_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: robby
--

ALTER TABLE ONLY tbooking
    ADD CONSTRAINT tbooking_product_id_fk FOREIGN KEY (product_id) REFERENCES tproduct(product_id);


--
-- Name: tcustomer_sales_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: robby
--

ALTER TABLE ONLY tcustomer
    ADD CONSTRAINT tcustomer_sales_id_fk FOREIGN KEY (sales_id) REFERENCES tsales(sales_id);


--
-- Name: tcustomer_type_fk; Type: FK CONSTRAINT; Schema: public; Owner: robby
--

ALTER TABLE ONLY tcustomer_type_price
    ADD CONSTRAINT tcustomer_type_fk FOREIGN KEY (customer_type) REFERENCES tlookup(lookup_id);


--
-- Name: tlookup_system_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: robby
--

ALTER TABLE ONLY tlookup
    ADD CONSTRAINT tlookup_system_id_fk FOREIGN KEY (system_id) REFERENCES tsystem(system_id);


--
-- Name: torder_customer_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: robby
--

ALTER TABLE ONLY torder
    ADD CONSTRAINT torder_customer_id_fk FOREIGN KEY (customer_id) REFERENCES tcustomer(customer_id);


--
-- Name: tproduct_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: robby
--

ALTER TABLE ONLY tcustomer_type_price
    ADD CONSTRAINT tproduct_id_fk FOREIGN KEY (product_id) REFERENCES tproduct(product_id);


--
-- Name: tuser_system_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: robby
--

ALTER TABLE ONLY tuser
    ADD CONSTRAINT tuser_system_id_fk FOREIGN KEY (system_id) REFERENCES tsystem(system_id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

