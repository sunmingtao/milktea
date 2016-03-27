package com.milktea.service.sendauthcode.smsquota;

import java.util.List;

import org.springframework.stereotype.Service;

import com.milktea.entity.MobileAuth;

@Service
public class CustomerSmsQuotaService extends AbstractSmsQuotaService {

	@Override
	protected List<MobileAuth> findAuthCodes(String mobile) {
		return mobileAuthDao.findCustomerAuthCodes(mobile);
	}

}
