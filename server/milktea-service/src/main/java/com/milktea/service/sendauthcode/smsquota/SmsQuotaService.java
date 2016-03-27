package com.milktea.service.sendauthcode.smsquota;

public interface SmsQuotaService {
	boolean hasReachedLimitForMobile(String mobile);
}
