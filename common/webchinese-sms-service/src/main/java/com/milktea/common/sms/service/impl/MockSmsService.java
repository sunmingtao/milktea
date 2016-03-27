package com.milktea.common.sms.service.impl;

import com.milktea.common.sms.response.SmsResponse;
import com.milktea.common.sms.service.SmsService;

public class MockSmsService implements SmsService{

	@Override
	public SmsResponse sendSms(String mobile, String message) {
		if (mobile.length() < 10){
			return SmsResponse.INVALID_MOBILE;
		}
		return SmsResponse.SUCCESS;
	}

}
