package com.milktea.service.it;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.milktea.common.sms.response.SmsResponse;
import com.milktea.exception.ReachSmsQuotaLimitException;
import com.milktea.service.api.SendMobileAuthCodeService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/test-context.xml"})
@ActiveProfiles(Constants.PROFILE)
public class SendCustomerAuthCodeServiceIT extends AbstractTransactionalJUnit4SpringContextTests{
	@Autowired
	private SendMobileAuthCodeService sendCustomerMobileAuthService;
	
	@Test
	public void testSendAuthenticationCodeToGoodMobile() throws ReachSmsQuotaLimitException{
		SmsResponse smsResponse = sendCustomerMobileAuthService.sendAuthenticationCode("1234567890");
		Assert.assertEquals(SmsResponse.SUCCESS, smsResponse);
		Assert.assertEquals(1, countRowsInTable("T_MOBILE_AUTH"));
	}
	
	@Test
	public void testSendAuthenticationCodeToBadMobile() throws ReachSmsQuotaLimitException{
		SmsResponse smsResponse = sendCustomerMobileAuthService.sendAuthenticationCode("123");
		Assert.assertEquals(SmsResponse.INVALID_MOBILE, smsResponse);
		Assert.assertEquals(0, countRowsInTable("T_MOBILE_AUTH"));
	}
	
	@Test
	public void testSmsQuotaServiceNoReachLimit() throws ReachSmsQuotaLimitException{
		String mobile = "13901804000";
		sendCustomerMobileAuthService.sendAuthenticationCode(mobile);
		sendCustomerMobileAuthService.sendAuthenticationCode(mobile);
		sendCustomerMobileAuthService.sendAuthenticationCode(mobile);
	}
	
	@Test(expected=ReachSmsQuotaLimitException.class)
	public void testSmsQuotaServiceReachLimit() throws ReachSmsQuotaLimitException{
		String mobile = "13901804000";
		sendCustomerMobileAuthService.sendAuthenticationCode(mobile);
		sendCustomerMobileAuthService.sendAuthenticationCode(mobile);
		sendCustomerMobileAuthService.sendAuthenticationCode(mobile);
		sendCustomerMobileAuthService.sendAuthenticationCode(mobile);
	}
	
	@Test
	public void testSmsQuotaServiceReachLimitAndSendAgain() throws 
			ReachSmsQuotaLimitException, InterruptedException{
		String mobile = "13901804000";
		sendCustomerMobileAuthService.sendAuthenticationCode(mobile);
		sendCustomerMobileAuthService.sendAuthenticationCode(mobile);
		sendCustomerMobileAuthService.sendAuthenticationCode(mobile);
		try {
			sendCustomerMobileAuthService.sendAuthenticationCode(mobile);
			Assert.fail();
		} catch (ReachSmsQuotaLimitException e) {
			Assert.assertTrue(true);
		}
		Thread.sleep(6000);
		sendCustomerMobileAuthService.sendAuthenticationCode(mobile);
	}
	
}
