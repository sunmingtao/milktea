package com.milktea.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.milktea.common.constants.Constants;
import com.milktea.common.dto.mobileauthcode.MobileAuthRequest;
import com.milktea.common.dto.mobileauthcode.MobileAuthResponse;
import com.milktea.common.sms.response.SmsResponse;
import com.milktea.enums.UserType;
import com.milktea.exception.ReachSmsQuotaLimitException;
import com.milktea.service.api.SendMobileAuthCodeService;

@Controller
public class SendMobileAuthCodeController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SendMobileAuthCodeService sendCustomerMobileAuthCodeService;
	
	@Autowired
	private SendMobileAuthCodeService sendShopMobileAuthCodeService;
	
	@RequestMapping(value=Constants.SEND_CUSTOMER_MOBILE_AUTH_CODE_PATH, method = RequestMethod.POST)
	@ResponseBody
	public MobileAuthResponse sendCustomerMobileAuthCode(@RequestBody MobileAuthRequest request){
		return sendMobileAuthCode(request, UserType.CUSTOMER);
	}
	
	@RequestMapping(value=Constants.SEND_SHOP_MOBILE_AUTH_CODE_PATH, method = RequestMethod.POST)
	@ResponseBody
	public MobileAuthResponse sendShopMobileAuthCode(@RequestBody MobileAuthRequest request){
		return sendMobileAuthCode(request, UserType.SHOP);
	}

	private MobileAuthResponse sendMobileAuthCode(MobileAuthRequest request, UserType userType){
		Assert.notNull(request.getMobile(), "Mobile cannot be null");
		logger.info("Message about to sent to "+request.getMobile());
		MobileAuthResponse response = null;
		try {
			SmsResponse smsResponse = sendAuthCode(request, userType);
			response = createMobileAuthResponse(smsResponse);
		} catch (ReachSmsQuotaLimitException e) {
			response = handleReachSmsQuotaLimitException();
			logger.debug(request.getMobile()+" has reached Sms quota limit");
		}
		return response;
	}

	private MobileAuthResponse handleReachSmsQuotaLimitException() {
		MobileAuthResponse response = new MobileAuthResponse();
		response.setStatus(Constants.REACH_SMS_QUOTA_LIMIT_RESPONSE);
		return response;
	}

	private SmsResponse sendAuthCode(MobileAuthRequest request,
			UserType userType) throws ReachSmsQuotaLimitException {
		SmsResponse smsResponse = null;
		if (userType == UserType.SHOP){
			smsResponse = sendShopMobileAuthCodeService.sendAuthenticationCode(request.getMobile());	
		}else {
			smsResponse = sendCustomerMobileAuthCodeService.sendAuthenticationCode(request.getMobile());
		}
		return smsResponse;
	}
	
	private MobileAuthResponse createMobileAuthResponse(SmsResponse smsResponse) {
		MobileAuthResponse response = new MobileAuthResponse();
		if (smsResponse == SmsResponse.SUCCESS){
			response.setStatus(Constants.SUCCESS_RESPONSE);
		}else if (smsResponse == SmsResponse.INVALID_MOBILE){
			response.setStatus(Constants.INVALID_MOBILE_RESPONSE);
		}else{
			response.setStatus(Constants.ERROR_RESPONSE);
			response.setErrorMessage(smsResponse.toString());
		}
		return response;
	}
	
}
