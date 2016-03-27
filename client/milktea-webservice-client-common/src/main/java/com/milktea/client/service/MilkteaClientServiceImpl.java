package com.milktea.client.service;

import java.util.HashMap;
import java.util.Map;

import com.milktea.client.wsconsumer.WebServiceConsumer;
import com.milktea.client.wsconsumer.factory.WebServiceConsumerFactory;
import com.milktea.client.wsconsumer.factory.WebServiceConsumerFactoryImpl;
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
import com.milktea.common.enums.WebServiceEnum;
import com.smt.common.spring.rest.RestWebServiceClientService;

public class MilkteaClientServiceImpl implements MilkteaClientService {

	private WebServiceConsumerFactory factory;

	protected MilkteaClientServiceImpl(String serverUrl,
			RestWebServiceClientService service) {
		factory = new WebServiceConsumerFactoryImpl(serverUrl, service);
	}

	public RegisterShopResponse registerShop(RegisterShopRequest request) {
		WebServiceConsumer<RegisterShopResponse> consumer = factory
				.getWebServiceConsumer(WebServiceEnum.REGISTER_SHOP);
		return consumer.getResponse(request);
	}

	public RegisterCustomerResponse registerCustomerWithAuthCode(
			RegisterCustomerRequest request) {
		WebServiceConsumer<RegisterCustomerResponse> consumer = factory
				.getWebServiceConsumer(WebServiceEnum.REGISTER_CUSTOMER_WITH_AUTH_CODE);
		return consumer.getResponse(request);
	}

	public RegisterCustomerResponse registerCustomerWithoutAuthCode(
			RegisterCustomerRequest request) {
		WebServiceConsumer<RegisterCustomerResponse> consumer = factory
				.getWebServiceConsumer(WebServiceEnum.REGISTER_CUSTOMER_WITHOUT_AUTH_CODE);
		return consumer.getResponse(request);
	}

	public MobileAuthResponse sendMobileAuthCode(MobileAuthRequest request) {
		WebServiceConsumer<MobileAuthResponse> consumer = factory
				.getWebServiceConsumer(WebServiceEnum.SEND_MOBILE_AUTH_CODE);
		return consumer.getResponse(request);
	}

	public PurchaseResponse purchase(PurchaseRequest request) {
		WebServiceConsumer<PurchaseResponse> consumer = factory
				.getWebServiceConsumer(WebServiceEnum.PURCHASE);
		return consumer.getResponse(request);
	}

	public RetrieveTranSummaryResponse getTransactionSummaryForCustomer(
			RetrieveTranSummaryRequest request) {
		WebServiceConsumer<RetrieveTranSummaryResponse> consumer = factory
				.getWebServiceConsumer(WebServiceEnum.RETRIEVE_TRANSACTION_SUMMARY_FOR_CUSTOMER);
		return consumer.getResponse(request);
	}

	public RedeemResponse redeem(RedeemRequest request) {
		WebServiceConsumer<RedeemResponse> consumer = factory
				.getWebServiceConsumer(WebServiceEnum.REDEEM);
		return consumer.getResponse(request);
	}

	public PushNotificationResponse preparePushNotification(
			PushNotificationRequest request) {
		WebServiceConsumer<PushNotificationResponse> consumer = factory
				.getWebServiceConsumer(WebServiceEnum.PREPARE_PUSH_NOTIFICATION);
		return consumer.getResponse(request);
	}

	@Override
	public int sentPushNotification(int max) {
		WebServiceConsumer<Integer> consumer = factory
				.getWebServiceConsumer(WebServiceEnum.SEND_PUSH_NOTIFICATION);
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("max", max);
		return consumer.getResponse(map);
	}

}
