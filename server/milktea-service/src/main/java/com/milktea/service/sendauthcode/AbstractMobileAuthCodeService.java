package com.milktea.service.sendauthcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milktea.common.sms.response.SmsResponse;
import com.milktea.common.sms.service.SmsService;
import com.milktea.dao.MobileAuthDao;
import com.milktea.exception.ReachSmsQuotaLimitException;
import com.milktea.service.api.SendMobileAuthCodeService;
import com.milktea.service.sendauthcode.generator.AuthenticationCodeGenerator;
import com.milktea.service.sendauthcode.smsquota.SmsQuotaService;

@Service
public abstract class AbstractMobileAuthCodeService implements SendMobileAuthCodeService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected SmsService smsService;

	@Autowired
	protected AuthenticationCodeGenerator authCodeGenerator;
	
	@Autowired
	protected MobileAuthDao mobileAuthDao;
	
	@Override
	public SmsResponse sendAuthenticationCode(String mobile) throws ReachSmsQuotaLimitException{
		if (getSmsQuotaService().hasReachedLimitForMobile(mobile)) {
			throw new ReachSmsQuotaLimitException("You have reached the sms quota limit for this week");
		}
		return sendSmsAndSaveAuthenticationCode(mobile);
	}
	
	protected abstract SmsQuotaService getSmsQuotaService();

	/**
	 * Send a sms message to the specified mobile
	 * and save the authentication code to database
	 * @param mobile
	 * @return
	 */
	private SmsResponse sendSmsAndSaveAuthenticationCode(String mobile) {
		int authCode = authCodeGenerator.generate();
		SmsResponse smsResponse = smsService.sendSms(mobile, "亲爱的用户，你的注册码是" + authCode);
		if (smsResponse == SmsResponse.SUCCESS) {
			saveMobileAuthCode(mobile, String.valueOf(authCode));
		}
		return smsResponse;
	}

	protected abstract void saveMobileAuthCode(String mobile, String authCode);

}
