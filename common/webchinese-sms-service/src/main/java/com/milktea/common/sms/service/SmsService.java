package com.milktea.common.sms.service;

import com.milktea.common.sms.response.SmsResponse;

public interface SmsService {
	public SmsResponse sendSms(String mobile, String message);
}
