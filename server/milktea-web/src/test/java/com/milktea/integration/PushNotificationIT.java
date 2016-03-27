package com.milktea.integration;

import junit.framework.Assert;

import org.junit.Test;

import com.milktea.common.constants.Constants;
import com.milktea.common.dto.pushnotification.PushNotificationResponse;

public class PushNotificationIT extends MilkteaIT{
    
	private static final String PREFIX = "SPN";
	
	private static final String SHOP_ID_001 = PREFIX + "s001";
	private static final String SHOP_ID_002 = PREFIX + "s002";
	private static final String SHOP_ID_003 = PREFIX + "s003";
	private static final String SHOP_ID_004 = PREFIX + "s004";
	private static final String SHOP_ID_005 = PREFIX + "s005";
	private static final String SHOP_NAME_001 = PREFIX + "SHOP001";
	private static final String SHOP_NAME_002 = PREFIX + "SHOP002";
	private static final String SHOP_NAME_003 = PREFIX + "SHOP003";
	private static final String SHOP_NAME_004 = PREFIX + "SHOP004";
	private static final String SHOP_NAME_005 = PREFIX + "SHOP005";
	
	private static final String CUST_ID_001 = PREFIX + "c001";
	private static final String CUST_ID_002 = PREFIX + "c002";
	private static final String CUST_ID_003 = PREFIX + "c003";
	private static final String CUST_ID_004 = PREFIX + "c004";
	private static final String CUST_ID_005 = PREFIX + "c005";
	private static final String CUST_ID_006 = PREFIX + "c006";
	private static final String CUST_ID_007 = PREFIX + "c007";
	private static final String CUST_ID_008 = PREFIX + "c008";
	private static final String CUST_ID_009 = PREFIX + "c009";
	private static final String CUST_ID_010 = PREFIX + "c010";
	private static final String CUST_ID_011 = PREFIX + "c011";
	private static final String CUST_ID_012 = PREFIX + "c012";
	private static final String CUST_ID_013 = PREFIX + "c013";
	private static final String CUST_ID_014 = PREFIX + "c014";
	
	/**
	 * Neither Customers has registered (so no push notification destination info)
	 */
	@Test
	public void testPrepareNotification1(){
		registerCupRedemtpionShop(SHOP_ID_001, SHOP_NAME_001);
		purchaseCup(SHOP_ID_001, CUST_ID_001, 3);
		purchaseCup(SHOP_ID_001, CUST_ID_002, 3);
		PushNotificationResponse response = preparePushNotification(SHOP_ID_001, "Hello Message");
		Assert.assertEquals(0, response.getCount());
		consumer.sentPushNotification(100);
	}
	
	/**
	 * Two of the Customers has registered
	 */
	@Test
	public void testPrepareNotification2(){
		registerCupRedemtpionShop(SHOP_ID_002, SHOP_NAME_002);
		registerCustomerWithoutAuthCode(CUST_ID_003);
		registerCustomerWithoutAuthCode(CUST_ID_004);
		purchaseCup(SHOP_ID_002, CUST_ID_003, 3);
		purchaseCup(SHOP_ID_002, CUST_ID_004, 3);
		purchaseCup(SHOP_ID_002, CUST_ID_005, 3);
		PushNotificationResponse response = preparePushNotification(SHOP_ID_002, "Hello Message");
		Assert.assertEquals(Constants.SUCCESS_RESPONSE, response.getStatus());
		Assert.assertEquals(2, response.getCount());
		consumer.sentPushNotification(100);
	}
	
	@Test
	public void testPrepareNotificationWithNonRegisteredShopId(){
		PushNotificationResponse response = preparePushNotification("NonExisting", "Hello Message");
		Assert.assertEquals(Constants.INVALIE_USERNAME_OR_PASSWORD_RESPONSE, response.getStatus());
	}
	
	@Test
	public void testSendNotificationMessage(){
		registerCupRedemtpionShop(SHOP_ID_003, SHOP_NAME_003);
		registerCustomerWithoutAuthCode(CUST_ID_003);
		registerCustomerWithoutAuthCode(CUST_ID_004);
		registerCustomerWithoutAuthCode(CUST_ID_005);
		registerCustomerWithoutAuthCode(CUST_ID_006);
		purchaseCup(SHOP_ID_002, CUST_ID_003, 3);
		purchaseCup(SHOP_ID_002, CUST_ID_004, 3);
		purchaseCup(SHOP_ID_002, CUST_ID_005, 3);
		purchaseCup(SHOP_ID_002, CUST_ID_006, 3);
		purchaseCup(SHOP_ID_002, CUST_ID_007, 3);
		purchaseCup(SHOP_ID_002, CUST_ID_008, 3);
		preparePushNotification(SHOP_ID_002, "Hello Message");
		int count = consumer.sentPushNotification(2);
		Assert.assertEquals(2, count);
		count = consumer.sentPushNotification(100);
		Assert.assertEquals(2, count);
	}
	
	@Test
	public void testSendNotificationWithBadMessage1(){
		registerCupRedemtpionShop(SHOP_ID_004, SHOP_NAME_004);
		registerCustomerWithoutAuthCode(CUST_ID_009);
		registerCustomerWithoutAuthCode(CUST_ID_010);
		purchaseCup(SHOP_ID_004, CUST_ID_009, 3);
		purchaseCup(SHOP_ID_004, CUST_ID_010, 3);
		purchaseCup(SHOP_ID_004, CUST_ID_011, 3);
		PushNotificationResponse response = preparePushNotification(SHOP_ID_004, "clientException");
		Assert.assertEquals(Constants.SUCCESS_RESPONSE, response.getStatus());
		Assert.assertEquals(2, response.getCount());
		int count = consumer.sentPushNotification(100);
		Assert.assertEquals(0, count);
	}
	
	@Test
	public void testSendNotificationWithBadMessage2(){
		registerCupRedemtpionShop(SHOP_ID_005, SHOP_NAME_005);
		registerCustomerWithoutAuthCode(CUST_ID_012);
		registerCustomerWithoutAuthCode(CUST_ID_013);
		purchaseCup(SHOP_ID_005, CUST_ID_012, 3);
		purchaseCup(SHOP_ID_005, CUST_ID_013, 3);
		purchaseCup(SHOP_ID_005, CUST_ID_014, 3);
		PushNotificationResponse response = preparePushNotification(SHOP_ID_005, "serverException");
		Assert.assertEquals(Constants.SUCCESS_RESPONSE, response.getStatus());
		Assert.assertEquals(2, response.getCount());
		int count = consumer.sentPushNotification(100);
		Assert.assertEquals(0, count);
	}
}
