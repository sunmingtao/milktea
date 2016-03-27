package com.milktea.aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.milktea.common.constants.Constants;
import com.milktea.common.dto.CommonResponse;
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

@Aspect
@Component
public class MilkteaAspect {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private static final Map<Class<?>, Class<?>> REQUEST_RESPONSE_MAP = new HashMap<Class<?>, Class<?>>();

	static {
		REQUEST_RESPONSE_MAP.put(MobileAuthRequest.class, MobileAuthResponse.class);
		REQUEST_RESPONSE_MAP.put(RegisterCustomerRequest.class, RegisterCustomerResponse.class);
		REQUEST_RESPONSE_MAP.put(RegisterShopRequest.class, RegisterShopResponse.class);
		REQUEST_RESPONSE_MAP.put(RetrieveTranSummaryRequest.class, RetrieveTranSummaryResponse.class);
		REQUEST_RESPONSE_MAP.put(PurchaseRequest.class, PurchaseResponse.class);
		REQUEST_RESPONSE_MAP.put(RedeemRequest.class, RedeemResponse.class);
		REQUEST_RESPONSE_MAP.put(PushNotificationRequest.class, PushNotificationResponse.class);
	}

	@Around("execution (* com.milktea.controller.*.*(..)) && args(request)")
	public Object handleException(ProceedingJoinPoint point, Object request)
			throws Throwable {
		CommonResponse response;
		try {
			response = (CommonResponse) point.proceed();
		} catch (Exception e) {
			response = createErrorResponse(request);
			e.printStackTrace();
			response.setErrorMessage(e.getMessage());
			response.setStatus(Constants.ERROR_RESPONSE);
		}
		return response;
	}

	private CommonResponse createErrorResponse(Object request) {
		Class<?> requestClass = request.getClass();
		Class<?> responseClass = REQUEST_RESPONSE_MAP.get(requestClass);
		try {
			return (CommonResponse) responseClass.newInstance();
		} catch (Exception e) {
			logger.error("Cannot create instance. \n" + e.getMessage());
		}
		return null;
	}
}
