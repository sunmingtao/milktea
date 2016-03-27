package com.milktea.service.sendauthcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milktea.entity.MobileAuth;
import com.milktea.enums.UserType;
import com.milktea.service.sendauthcode.smsquota.SmsQuotaService;

@Service
public class SendCustomerMobileAuthCodeService extends AbstractMobileAuthCodeService {

	@Autowired
	private SmsQuotaService customerSmsQuotaService;
	
	@Override
	protected SmsQuotaService getSmsQuotaService() {
		return customerSmsQuotaService;
	}

	@Override
	protected void saveMobileAuthCode(String mobile, String authCode) {
		mobileAuthDao.save(new MobileAuth(mobile, authCode, UserType.CUSTOMER));
	}
	
}
