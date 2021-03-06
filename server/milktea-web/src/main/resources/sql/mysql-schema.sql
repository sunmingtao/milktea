DROP TABLE IF EXISTS T_TRANSACTION_SUMMARY;
DROP TABLE IF EXISTS T_TRANSACTION_DETAIL;
DROP TABLE IF EXISTS T_CUSTOMER;
DROP TABLE IF EXISTS T_SHOP;
DROP TABLE IF EXISTS T_MOBILE_AUTH;


CREATE TABLE T_CUSTOMER(
   ID bigint AUTO_INCREMENT PRIMARY KEY NOT NULL,
   CUSTOMER_ID varchar(255) UNIQUE NOT NULL,
   CHANNEL_ID varchar(255),
   PASSWORD varchar(255),
   USER_ID varchar(255)
)ENGINE=InnoDb;



CREATE TABLE T_SHOP 
(
 ID bigint AUTO_INCREMENT PRIMARY KEY NOT NULL,
 SHOP_ID VARCHAR(15) NOT NULL UNIQUE, 
 SHOP_NAME VARCHAR(255) NOT NULL UNIQUE, 
 PASSWORD VARCHAR(20) NOT NULL,
 NUM_CUP_REDEEM INTEGER,
 AMOUNT_REDEEM NUMERIC (8,2)
)ENGINE=InnoDb;



CREATE TABLE T_MOBILE_AUTH
(
   ID bigint AUTO_INCREMENT PRIMARY KEY NOT NULL,
   AUTH_CODE varchar(255) NOT NULL,
   USER_TYPE VARCHAR(10) NOT NULL,
   CREATED_TIME timestamp NOT NULL,
   MOBILE varchar(255) NOT NULL
)ENGINE=InnoDb;



CREATE TABLE T_TRANSACTION_DETAIL
(ID bigint AUTO_INCREMENT PRIMARY KEY NOT NULL,
 SHOP_ID VARCHAR(14) NOT NULL,
 CUSTOMER_ID VARCHAR(14) NOT NULL,
 NUM_OF_CUPS INTEGER,
 AMOUNT NUMERIC (8,2),
 TRANSATION_TIME timestamp NOT NULL
)ENGINE=InnoDb;

ALTER TABLE T_TRANSACTION_DETAIL ADD FOREIGN KEY (SHOP_ID) REFERENCES T_SHOP (SHOP_ID);



CREATE TABLE T_TRANSACTION_SUMMARY
(ID bigint AUTO_INCREMENT PRIMARY KEY NOT NULL,
 SHOP_ID VARCHAR(14) NOT NULL,
 CUSTOMER_ID VARCHAR(14) NOT NULL,
 TOTAL_CUPS INTEGER,
 TOTAL_AMOUNT NUMERIC (8,2),
 TOTAL_CUPS_REDEEMED INTEGER,
 TOTAL_AMOUNT_REDEEMED NUMERIC (8,2)
)ENGINE=InnoDb;

ALTER TABLE T_TRANSACTION_SUMMARY ADD FOREIGN KEY (SHOP_ID) REFERENCES T_SHOP (SHOP_ID);
ALTER TABLE T_TRANSACTION_SUMMARY ADD UNIQUE (SHOP_ID, CUSTOMER_ID);

