package com.milktea.service.sendauthcode;

import org.springframework.beans.factory.annotation.Autowired;

import com.milktea.entity.MobileAuth;
import com.milktea.enums.UserType;
import com.milktea.service.sendauthcode.smsquota.SmsQuotaService;

public class SendShopMobileAuthCodeService extends AbstractMobileAuthCodeService {

	@Autowired
	private SmsQuotaService shopSmsQuotaService;
	
	@Override
	protected SmsQuotaService getSmsQuotaService() {
		return shopSmsQuotaService;
	}

	@Override
	protected void saveMobileAuthCode(String mobile, String authCode) {
		mobileAuthDao.save(new MobileAuth(mobile, authCode, UserType.CUSTOMER));
	}

}
