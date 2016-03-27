INSERT INTO T_CUSTOMER (CUSTOMER_ID, PASSWORD) values ('c001', 'Password');

--testSuccessfulAmountRedeem1()
INSERT INTO T_SHOP (SHOP_ID, SHOP_NAME, PASSWORD, AMOUNT_REDEEM) values ('s001', 'SHOP1', 'password', 49.95);
INSERT INTO T_TRANSACTION_DETAIL (SHOP_ID, CUSTOMER_ID, NUM_OF_CUPS, AMOUNT) values ('s001', 'c001', 0, 50);
INSERT INTO T_TRANSACTION_SUMMARY (SHOP_ID, CUSTOMER_ID, TOTAL_CUPS, TOTAL_AMOUNT, TOTAL_CUPS_REDEEMED, TOTAL_AMOUNT_REDEEMED) 
	VALUES ('s001', 'c001', 0, 50, 0, 0);

--testSuccessfulCupRedeem1()
INSERT INTO T_SHOP (SHOP_ID, SHOP_NAME, PASSWORD, NUM_CUP_REDEEM) values ('s002', 'SHOP2', 'password', 10);
INSERT INTO T_TRANSACTION_DETAIL (SHOP_ID, CUSTOMER_ID, NUM_OF_CUPS, AMOUNT) values ('s002', 'c001', 11, 0.00);
INSERT INTO T_TRANSACTION_SUMMARY (SHOP_ID, CUSTOMER_ID, TOTAL_CUPS, TOTAL_AMOUNT, TOTAL_CUPS_REDEEMED, TOTAL_AMOUNT_REDEEMED) 
		VALUES ('s002', 'c001', 11, 0.00, 0, 0.00);
		
--testSuccessfulAmountRedeem2()
INSERT INTO T_SHOP (SHOP_ID, SHOP_NAME, PASSWORD, AMOUNT_REDEEM) values ('s003', 'SHOP3', 'password', 50.00);
INSERT INTO T_TRANSACTION_DETAIL (SHOP_ID, CUSTOMER_ID, NUM_OF_CUPS, AMOUNT) values ('s003', 'c001', 0, 50.00);
INSERT INTO T_TRANSACTION_SUMMARY (SHOP_ID, CUSTOMER_ID, TOTAL_CUPS, TOTAL_AMOUNT, TOTAL_CUPS_REDEEMED, TOTAL_AMOUNT_REDEEMED) 
VALUES ('s003', 'c001', 0, 50.00, 0, 0);

--testSuccessfulCupRedeem2()
INSERT INTO T_SHOP (SHOP_ID, SHOP_NAME, PASSWORD, NUM_CUP_REDEEM) values ('s004', 'SHOP4', 'password', 10);
INSERT INTO T_TRANSACTION_DETAIL (SHOP_ID, CUSTOMER_ID, NUM_OF_CUPS, AMOUNT) values ('s004', 'c001', 10, 0.00);
INSERT INTO T_TRANSACTION_SUMMARY (SHOP_ID, CUSTOMER_ID, TOTAL_CUPS, TOTAL_AMOUNT, TOTAL_CUPS_REDEEMED, TOTAL_AMOUNT_REDEEMED) 
		VALUES ('s004', 'c001', 10, 0.00, 0, 0.00);

--testInsuffientAmountToRedeem()
INSERT INTO T_SHOP (SHOP_ID, SHOP_NAME, PASSWORD, AMOUNT_REDEEM) values ('s005', 'SHOP5', 'password', 50.00);
INSERT INTO T_TRANSACTION_DETAIL (SHOP_ID, CUSTOMER_ID, NUM_OF_CUPS, AMOUNT) values ('s005', 'c001', 0, 49.95);
INSERT INTO T_TRANSACTION_SUMMARY (SHOP_ID, CUSTOMER_ID, TOTAL_CUPS, TOTAL_AMOUNT, TOTAL_CUPS_REDEEMED, TOTAL_AMOUNT_REDEEMED) 
VALUES ('s005', 'c001', 0, 49.95, 0, 0);
		
--testInsuffientCupToRedeem()
INSERT INTO T_SHOP (SHOP_ID, SHOP_NAME, PASSWORD, NUM_CUP_REDEEM) values ('s006', 'SHOP6', 'password', 11);
INSERT INTO T_TRANSACTION_DETAIL (SHOP_ID, CUSTOMER_ID, NUM_OF_CUPS, AMOUNT) values ('s006', 'c001', 10, 0.00);
INSERT INTO T_TRANSACTION_SUMMARY (SHOP_ID, CUSTOMER_ID, TOTAL_CUPS, TOTAL_AMOUNT, TOTAL_CUPS_REDEEMED, TOTAL_AMOUNT_REDEEMED) 
		VALUES ('s006', 'c001', 10, 0.00, 0, 0.00);