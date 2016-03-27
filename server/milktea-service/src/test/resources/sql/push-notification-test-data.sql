INSERT INTO T_SHOP (SHOP_ID, SHOP_NAME, PASSWORD, AMOUNT_REDEEM) values ('s001', 'SHOP1', 'password', 5.5);

INSERT INTO T_TRANSACTION_SUMMARY (SHOP_ID, CUSTOMER_ID, TOTAL_CUPS, TOTAL_AMOUNT, TOTAL_CUPS_REDEEMED, TOTAL_AMOUNT_REDEEMED) 
VALUES ('s001', 'c001', 0, 50, 0, 0);
INSERT INTO T_TRANSACTION_SUMMARY (SHOP_ID, CUSTOMER_ID, TOTAL_CUPS, TOTAL_AMOUNT, TOTAL_CUPS_REDEEMED, TOTAL_AMOUNT_REDEEMED) 
VALUES ('s001', 'c002', 0, 50, 0, 0);
INSERT INTO T_TRANSACTION_SUMMARY (SHOP_ID, CUSTOMER_ID, TOTAL_CUPS, TOTAL_AMOUNT, TOTAL_CUPS_REDEEMED, TOTAL_AMOUNT_REDEEMED) 
VALUES ('s001', 'c003', 0, 50, 0, 0);
INSERT INTO T_TRANSACTION_SUMMARY (SHOP_ID, CUSTOMER_ID, TOTAL_CUPS, TOTAL_AMOUNT, TOTAL_CUPS_REDEEMED, TOTAL_AMOUNT_REDEEMED) 
VALUES ('s001', 'c004', 0, 50, 0, 0);
INSERT INTO T_TRANSACTION_SUMMARY (SHOP_ID, CUSTOMER_ID, TOTAL_CUPS, TOTAL_AMOUNT, TOTAL_CUPS_REDEEMED, TOTAL_AMOUNT_REDEEMED) 
VALUES ('s001', 'c005', 0, 50, 0, 0);
INSERT INTO T_TRANSACTION_SUMMARY (SHOP_ID, CUSTOMER_ID, TOTAL_CUPS, TOTAL_AMOUNT, TOTAL_CUPS_REDEEMED, TOTAL_AMOUNT_REDEEMED) 
VALUES ('s001', 'c006', 0, 50, 0, 0);
INSERT INTO T_TRANSACTION_SUMMARY (SHOP_ID, CUSTOMER_ID, TOTAL_CUPS, TOTAL_AMOUNT, TOTAL_CUPS_REDEEMED, TOTAL_AMOUNT_REDEEMED) 
VALUES ('s001', 'c007', 0, 50, 0, 0);
INSERT INTO T_TRANSACTION_SUMMARY (SHOP_ID, CUSTOMER_ID, TOTAL_CUPS, TOTAL_AMOUNT, TOTAL_CUPS_REDEEMED, TOTAL_AMOUNT_REDEEMED) 
VALUES ('s001', 'c008', 0, 50, 0, 0);
INSERT INTO T_TRANSACTION_SUMMARY (SHOP_ID, CUSTOMER_ID, TOTAL_CUPS, TOTAL_AMOUNT, TOTAL_CUPS_REDEEMED, TOTAL_AMOUNT_REDEEMED) 
VALUES ('s001', 'c009', 0, 50, 0, 0);

INSERT INTO T_CUSTOMER (CUSTOMER_ID, PASSWORD, USER_ID, CHANNEL_ID) values ('c001', '123', 'U123', 'C123');
INSERT INTO T_CUSTOMER (CUSTOMER_ID, PASSWORD, USER_ID, CHANNEL_ID) values ('c002', '123', 'U123', null);
INSERT INTO T_CUSTOMER (CUSTOMER_ID, PASSWORD, USER_ID, CHANNEL_ID) values ('c003', '123', null, 'C123');
INSERT INTO T_CUSTOMER (CUSTOMER_ID, PASSWORD, USER_ID, CHANNEL_ID) values ('c004', '123', null, 'C123');
INSERT INTO T_CUSTOMER (CUSTOMER_ID, PASSWORD, USER_ID, CHANNEL_ID) values ('c005', '123', null, 'C123');
INSERT INTO T_CUSTOMER (CUSTOMER_ID, PASSWORD, USER_ID, CHANNEL_ID) values ('c006', '123', null, 'C123');
INSERT INTO T_CUSTOMER (CUSTOMER_ID, PASSWORD, USER_ID, CHANNEL_ID) values ('c007', '123', null, 'C123');
INSERT INTO T_CUSTOMER (CUSTOMER_ID, PASSWORD, USER_ID, CHANNEL_ID) values ('c008', '123', null, 'C123');
INSERT INTO T_CUSTOMER (CUSTOMER_ID, PASSWORD, USER_ID, CHANNEL_ID) values ('c009', '123', null, null);


