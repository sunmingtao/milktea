package com.milktea.service.sendauthcode.smsquota;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.milktea.dao.MobileAuthDao;
import com.milktea.entity.MobileAuth;
import com.milktea.util.MobileAuthUtils;

public abstract class AbstractSmsQuotaService implements SmsQuotaService {

	@Autowired
	protected MobileAuthDao mobileAuthDao;
	
	/** 
	 * The number of Sms messages allowed to sent within a certain period 
	 * e.g. If 3 messages are allowed to sent within 7 days, then this value 
	 * should be 3
	 */
	private Integer numberOfSmsAllowedWithinPeriod;
	
	/** 
	 * The period (in milli seconds) within which a certain number of Sms 
	 * messages are allowed to sent.
	 * e.g. If 3 messages are allowed to sent within 7 days, then this value 
	 * should be 7 * 24 * 60 * 60 * 1000;
	 */
	private Long periodInMilliSeconds;
	
	@Override
	public boolean hasReachedLimitForMobile(String mobile) {
		List<MobileAuth> list = findAuthCodes(mobile);
		MobileAuth nThlastMobileAuth = MobileAuthUtils.getNthLatestAuthCode(list, numberOfSmsAllowedWithinPeriod);
		return hasReachedLimit(nThlastMobileAuth);
	}

	protected abstract List<MobileAuth> findAuthCodes(String mobile);

	private boolean hasReachedLimit(MobileAuth nThlastMobileAuth) {
		if (nThlastMobileAuth == null){
			return false;
		}
		Long nThLastSentTimeInMilliSeconds = nThlastMobileAuth.getCreatedTime().getTime();
		Long nowTimeInMilliSeconds = (new Date()).getTime();
		return nowTimeInMilliSeconds - nThLastSentTimeInMilliSeconds < periodInMilliSeconds;
	}

	public void setNumberOfSmsAllowedWithinPeriod(
			Integer numberOfSmsAllowedWithinPeriod) {
		this.numberOfSmsAllowedWithinPeriod = numberOfSmsAllowedWithinPeriod;
	}

	public void setPeriodInMilliSeconds(Long periodInMilliSeconds) {
		this.periodInMilliSeconds = periodInMilliSeconds;
	}
}
