package com.milktea.common.constants;

public final class Constants {
	public static final String REGISTER_SHOP_PATH = "registerShop";
	public static final String REGISTER_USER_WITHOUT_AUTH_CODE_PATH = "registerUserWithoutAuthCode";
	public static final String REGISTER_USER_WITH_AUTH_CODE_PATH = "registerUserWithAuthCode";
	public static final String SEND_CUSTOMER_MOBILE_AUTH_CODE_PATH = "sendCustMobileAuthCode";
	public static final String SEND_SHOP_MOBILE_AUTH_CODE_PATH = "sendShopMobileAuthCode";
	public static final String PURCHASE_PATH = "shop/purchase";
	public static final String GET_TRANSACTION_SUMMARY_FOR_CUSTOMER_PATH = "customer/getTransactionSummary";
	public static final String PREPARE_PUSH_NOTIFICATION_PATH = "shop/preparePushNotification";
	public static final String SEND_PUSH_NOTIFICATION_PATH = "spn/{max}";
	public static final String REDEEM_PATH = "shop/redeem";
	
	public static final String SUCCESS_RESPONSE="SUCCESS";
	public static final String USER_EXISTS_RESPONSE="USER_EXISTS";
	public static final String SHOP_EXISTS_RESPONSE = "SHOP_EXISTS";
	public static final String INVALID_AUTH_CODE_RESPONSE="INVALID_AUTHENTICATION_CODE";
	public static final String ERROR_RESPONSE="ERROR";
	public static final String INVALIE_USERNAME_OR_PASSWORD_RESPONSE = "INVALIE_USERNAME_OR_PASSWORD";
	public static final String NO_CONNECTION_RESPONSE = "NO_CONNECTION";
	public static final String REACH_SMS_QUOTA_LIMIT_RESPONSE = "REACH_SMS_QUOTA_LIMIT";
	public static final String INVALID_MOBILE_RESPONSE = "INVALID_MOBILE";
	public static final String INSUFFICIENT_CREDIT_TO_REDEEM_RESPONSE = "INSUFFICIENT_CREDIT_TO_REDEEM";
	
	
	
}
