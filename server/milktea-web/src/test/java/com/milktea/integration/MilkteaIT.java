package com.milktea.integration;

import org.junit.Before;

import com.milktea.client.service.MilkteaClientService;
import com.milktea.client.service.MilkteaClientServiceFactory;
import com.milktea.common.dto.UserContext;
import com.milktea.common.dto.mobileauthcode.MobileAuthRequest;
import com.milktea.common.dto.mobileauthcode.MobileAuthResponse;
import com.milktea.common.dto.purchase.PurchaseRequest;
import com.milktea.common.dto.purchase.PurchaseResponse;
import com.milktea.common.dto.pushnotification.PushNotificationRequest;
import com.milktea.common.dto.pushnotification.PushNotificationResponse;
import com.milktea.common.dto.registercustomer.RegisterCustomerRequest;
import com.milktea.common.dto.registercustomer.RegisterCustomerResponse;
import com.milktea.common.dto.registershop.RegisterShopRequest;
import com.milktea.common.dto.registershop.RegisterShopResponse;
import com.milktea.common.purchase.PurchaseDetailFactory;
import com.milktea.common.redemption.RedemptionPolicyFactory;

public abstract class MilkteaIT {

	private static final String LOCAL = "http://localhost:8080/milktea-web";
	private static final String BAE_1 = "http://1.milktea.duapp.com";
	private static final String BAE_2 = "http://2.milktea.duapp.com";

	protected static final String GOOD_AUTH_CODE = "33";
	protected static final String BAD_AUTH_CODE = "34";

	protected static final String PASSWORD = "password";
	protected static final String BAD_PASSWORD = "badPassword";

	protected MilkteaClientService consumer;

	@Before
	public void initialize() {
		consumer = MilkteaClientServiceFactory.instance(LOCAL);
	}

	protected RegisterCustomerResponse registerCustomerWithoutAuthCode(
			String cid) {
		RegisterCustomerRequest request = new RegisterCustomerRequest(
				new UserContext(cid, PASSWORD));
		request.setChannelId("CID:007");
		request.setUserId("UID:888");
		return consumer.registerCustomerWithoutAuthCode(request);
	}

	protected RegisterShopResponse registerCupRedemtpionShop(String sid,
			String sname) {
		RegisterShopRequest request = new RegisterShopRequest(new UserContext(
				sid, PASSWORD),
				RedemptionPolicyFactory.createCupRedemptionPolicy(10));
		request.setShopName(sname);
		return consumer.registerShop(request);
	}

	protected MobileAuthResponse sendMobileAuthCode(String cid) {
		MobileAuthRequest request = new MobileAuthRequest(cid);
		return consumer.sendMobileAuthCode(request);
	}

	protected PurchaseResponse purchaseCup(String shopId, String customerId,
			int cup) {
		PurchaseRequest request = new PurchaseRequest(new UserContext(shopId,
				PASSWORD), customerId,
				PurchaseDetailFactory.createCupPurchaseDetail(cup));
		return consumer.purchase(request);
	}

	protected PushNotificationResponse preparePushNotification(String shopId, String message) {
		PushNotificationRequest request = new PushNotificationRequest(
				new UserContext(shopId, PASSWORD), message);
		return consumer.preparePushNotification(request);
	}
	
}
