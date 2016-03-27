package com.milktea.service.it;


import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.milktea.common.dto.UserContext;
import com.milktea.common.dto.registercustomer.RegisterCustomerRequest;
import com.milktea.common.sms.response.SmsResponse;
import com.milktea.exception.InvalidAuthCodeException;
import com.milktea.exception.ReachSmsQuotaLimitException;
import com.milktea.service.api.RegisterCustomerService;
import com.milktea.service.api.SendMobileAuthCodeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/test-context.xml"})
@ActiveProfiles(Constants.PROFILE)
public class SystemIT extends AbstractTransactionalJUnit4SpringContextTests{
	
	private static final String SHOP_ID_001 = "s001";

	@Autowired
	private RegisterCustomerService registerCustomerService;
	
	@Autowired
	private SendMobileAuthCodeService sendMobileAuthService;
	
	@Test
	public void testSendAuthCodeAndRegisterCustomerWithValidAuthCode() throws InvalidAuthCodeException, ReachSmsQuotaLimitException{
		SmsResponse smsResponse = sendMobileAuthService.sendAuthenticationCode("9999999999");
		Assert.assertEquals(SmsResponse.SUCCESS, smsResponse);
		RegisterCustomerRequest request = new RegisterCustomerRequest(createUserContext());
		request.setUserId("uid888");
		request.setChannelId("cid777");
		request.setAuthCode("33");
		registerCustomerService.registerCustomerWithAuthCode(request);
		Assert.assertTrue(true);
	}
	
	@Test(expected=InvalidAuthCodeException.class)
	public void testSendAuthCodeAndRegisterCustomerWithInvalidAuthCode1() throws InvalidAuthCodeException, ReachSmsQuotaLimitException{
		SmsResponse smsResponse = sendMobileAuthService.sendAuthenticationCode("9999999999");
		Assert.assertEquals(SmsResponse.SUCCESS, smsResponse);
		RegisterCustomerRequest request = new RegisterCustomerRequest(createUserContext());
		request.setUserId("uid888");
		request.setChannelId("cid777");
		request.setAuthCode("99");
		registerCustomerService.registerCustomerWithAuthCode(request);
	}
	
	@Test(expected=InvalidAuthCodeException.class)
	public void testSendAuthCodeAndRegisterCustomerWithInvalidAuthCode2() throws InvalidAuthCodeException, ReachSmsQuotaLimitException{
		SmsResponse smsResponse = sendMobileAuthService.sendAuthenticationCode("8888888888");
		Assert.assertEquals(SmsResponse.SUCCESS, smsResponse);
		RegisterCustomerRequest request = new RegisterCustomerRequest(createUserContext());
		request.setUserId("uid888");
		request.setChannelId("cid777");
		request.setAuthCode("33");
		registerCustomerService.registerCustomerWithAuthCode(request);
	}
	
	private UserContext createUserContext() {
		return new UserContext("9999999999", "Password");
	}
}
