package com.milktea.service.it;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.milktea.common.dto.UserContext;
import com.milktea.common.dto.registercustomer.RegisterCustomerRequest;
import com.milktea.exception.InvalidAuthCodeException;
import com.milktea.service.api.RegisterCustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/test-context.xml"})
@ActiveProfiles(Constants.PROFILE)
public class RegisterCustomerServiceIT extends AbstractTransactionalJUnit4SpringContextTests{
	@Autowired
	private RegisterCustomerService registerCustomerService;
	
	@Before
	public void setup(){
		executeSqlScript("sql/register-customer-test-data.sql", false);
	}
	
	@Test
	public void testRegisterCustomerWithoutAuthCodeWithNewUsername(){
		UserContext uc = new UserContext("0423747411", "Password");
		RegisterCustomerRequest request = new RegisterCustomerRequest(uc);
		request.setUserId("UID");
		request.setChannelId("CID");
		registerCustomerService.registerCustomerWithoutAuthCode(request);
	}
	
	@Test
	public void testRegisterCustomerWithoutAuthCodeWithExistingUsername(){
		UserContext uc = new UserContext("0423747410", "Password");
		RegisterCustomerRequest request = new RegisterCustomerRequest(uc);
		request.setUserId("newUID");
		request.setChannelId("newCID");
		registerCustomerService.registerCustomerWithoutAuthCode(request);
	}
	
	@Test(expected=InvalidAuthCodeException.class)
	public void testRegisterCustomerWithInvalidAuthCode() throws InvalidAuthCodeException{
		UserContext uc = new UserContext("0423747411", "Password");
		RegisterCustomerRequest request = new RegisterCustomerRequest(uc);
		request.setUserId("UID");
		request.setChannelId("CID");
		request.setAuthCode("1234");
		registerCustomerService.registerCustomerWithAuthCode(request);
	}
	
	@Test(expected=InvalidAuthCodeException.class)
	public void testRegisterCustomerWithInvalidAuthCode2() throws InvalidAuthCodeException{
		UserContext uc = new UserContext("0423747412", "Password");
		RegisterCustomerRequest request = new RegisterCustomerRequest(uc);
		request.setUserId("UID");
		request.setChannelId("CID");
		request.setAuthCode("1234");
		registerCustomerService.registerCustomerWithAuthCode(request);
	}
	
	@Test
	public void testRegisterCustomerWithValidAuthCode() throws InvalidAuthCodeException{
		UserContext uc = new UserContext("0423747411", "Password");
		RegisterCustomerRequest request = new RegisterCustomerRequest(uc);
		request.setUserId("UID");
		request.setChannelId("Password");
		request.setAuthCode("45678");
		registerCustomerService.registerCustomerWithAuthCode(request);
	}
	
	@Test
	public void testRegisterCustomerWithValidAuthCodeWithExistingCustomerId() throws InvalidAuthCodeException{
		UserContext uc = new UserContext("0423747410", "Password");
		RegisterCustomerRequest request = new RegisterCustomerRequest(uc);
		request.setUserId("newUID");
		request.setChannelId("newPassword");
		request.setAuthCode("45678");
		registerCustomerService.registerCustomerWithAuthCode(request);
	}
	
}
