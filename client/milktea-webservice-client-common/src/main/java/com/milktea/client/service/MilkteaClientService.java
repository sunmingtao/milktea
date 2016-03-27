package com.milktea.client.service;

import com.milktea.common.dto.mobileauthcode.MobileAuthRequest;
import com.milktea.common.dto.mobileauthcode.MobileAuthResponse;
import com.milktea.common.dto.purchase.PurchaseRequest;
import com.milktea.common.dto.purchase.PurchaseResponse;
import com.milktea.common.dto.pushnotification.PushNotificationRequest;
import com.milktea.common.dto.pushnotification.PushNotificationResponse;
import com.milktea.common.dto.redeem.RedeemRequest;
import com.milktea.common.dto.redeem.RedeemResponse;
import com.milktea.common.dto.registercustomer.RegisterCustomerRequest;
import com.milktea.common.dto.registercustomer.RegisterCustomerResponse;
import com.milktea.common.dto.registershop.RegisterShopRequest;
import com.milktea.common.dto.registershop.RegisterShopResponse;
import com.milktea.common.dto.retrievesummary.RetrieveTranSummaryRequest;
import com.milktea.common.dto.retrievesummary.RetrieveTranSummaryResponse;

public interface MilkteaClientService {
	RegisterShopResponse registerShop(RegisterShopRequest request);
	RegisterCustomerResponse registerCustomerWithAuthCode(RegisterCustomerRequest request);
	RegisterCustomerResponse registerCustomerWithoutAuthCode(RegisterCustomerRequest request);
	MobileAuthResponse sendMobileAuthCode(MobileAuthRequest request);
	PurchaseResponse purchase(PurchaseRequest request);
	RetrieveTranSummaryResponse getTransactionSummaryForCustomer(RetrieveTranSummaryRequest request);
	RedeemResponse redeem(RedeemRequest request);
	PushNotificationResponse preparePushNotification(PushNotificationRequest request);
	/**
	 * Call the web service that sends the push notifications stored in database
	 * @param max the maximum number of push notifications to be sent
	 * @return the number of push notification that were successfully sent
	 */
	int sentPushNotification(int max);
}
