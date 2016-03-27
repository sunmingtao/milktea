package com.milktea.service.api;

import com.milktea.common.sms.response.SmsResponse;
import com.milktea.exception.ReachSmsQuotaLimitException;

public interface SendMobileAuthCodeService {
	SmsResponse sendAuthenticationCode(String mobile) throws ReachSmsQuotaLimitException;
}
