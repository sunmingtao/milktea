package com.milktea.common.enums;

import com.milktea.common.constants.Constants;
import com.milktea.common.dto.mobileauthcode.MobileAuthResponse;
import com.milktea.common.dto.purchase.PurchaseResponse;
import com.milktea.common.dto.pushnotification.PushNotificationResponse;
import com.milktea.common.dto.redeem.RedeemResponse;
import com.milktea.common.dto.registercustomer.RegisterCustomerResponse;
import com.milktea.common.dto.registershop.RegisterShopResponse;
import com.milktea.common.dto.retrievesummary.RetrieveTranSummaryResponse;

public enum WebServiceEnum {
	
	REGISTER_SHOP(Constants.REGISTER_SHOP_PATH, RegisterShopResponse.class),
	
	REGISTER_CUSTOMER_WITHOUT_AUTH_CODE(Constants.REGISTER_USER_WITHOUT_AUTH_CODE_PATH, RegisterCustomerResponse.class),
	
	REGISTER_CUSTOMER_WITH_AUTH_CODE(Constants.REGISTER_USER_WITH_AUTH_CODE_PATH, RegisterCustomerResponse.class),
	
	SEND_MOBILE_AUTH_CODE(Constants.SEND_CUSTOMER_MOBILE_AUTH_CODE_PATH, MobileAuthResponse.class),
	
	PURCHASE(Constants.PURCHASE_PATH, PurchaseResponse.class), 
	
	RETRIEVE_TRANSACTION_SUMMARY_FOR_CUSTOMER(Constants.GET_TRANSACTION_SUMMARY_FOR_CUSTOMER_PATH, RetrieveTranSummaryResponse.class),
	
	REDEEM(Constants.REDEEM_PATH, RedeemResponse.class), 
	
	PREPARE_PUSH_NOTIFICATION(Constants.PREPARE_PUSH_NOTIFICATION_PATH, PushNotificationResponse.class),
	
	SEND_PUSH_NOTIFICATION(Constants.SEND_PUSH_NOTIFICATION_PATH, Integer.class);
	
	private final String webServicePath;
	private final Class<?> responseClassType;
	
	private WebServiceEnum(String webServicePath, Class<?> responseClassType){
		this.webServicePath = webServicePath;
		this.responseClassType = responseClassType;
	}
	
	public static WebServiceEnum forWebServicePath(String webServicePath){
		for (WebServiceEnum wsEnum : WebServiceEnum.values()){
			if (wsEnum.getWebServicePath().equals(webServicePath)){
				return wsEnum;
			}
		}
		throw new IllegalArgumentException("Web Service Path: "+webServicePath+" cannot be found");
	}

	public String getWebServicePath() {
		return webServicePath;
	}

	public Class<?> getResponseClassType() {
		return responseClassType;
	}
	
	public String toString(){
		return webServicePath;
	}
}
