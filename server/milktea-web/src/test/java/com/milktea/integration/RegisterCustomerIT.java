package com.milktea.integration;

import junit.framework.Assert;

import org.junit.Test;

import com.milktea.common.constants.Constants;
import com.milktea.common.dto.UserContext;
import com.milktea.common.dto.registercustomer.RegisterCustomerRequest;
import com.milktea.common.dto.registercustomer.RegisterCustomerResponse;

public class RegisterCustomerIT extends MilkteaIT{
    
	private static final String CUST_ID_001 = "regCust0001";
	private static final String CUST_ID_002 = "regCust0002";
	private static final String CUST_ID_003 = "regCust0003";
	private static final String CUST_ID_004 = "regCust0004";
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullInput(){
		consumer.registerCustomerWithoutAuthCode(null);
	}
	
	@Test
    public void testRegisterCustomerWithoutAuthCode(){
		RegisterCustomerResponse response = registerCustomerWithoutAuthCode(CUST_ID_001);
		Assert.assertEquals(Constants.SUCCESS_RESPONSE, response.getStatus());
		response = registerCustomerWithoutAuthCode(CUST_ID_001);
		Assert.assertEquals(Constants.SUCCESS_RESPONSE, response.getStatus());
    }
	
	@Test
    public void testRegisterCustomerWithGoodAuthCode(){
		sendMobileAuthCode(CUST_ID_002);
		RegisterCustomerResponse response = registerCustomerWithAuthCode(CUST_ID_002, GOOD_AUTH_CODE);
		Assert.assertEquals(Constants.SUCCESS_RESPONSE, response.getStatus());
    }
    
    @Test
    public void testRegisterCustomerWithBadAuthCode(){
    	sendMobileAuthCode(CUST_ID_003);
		RegisterCustomerResponse response = registerCustomerWithAuthCode(CUST_ID_003, BAD_AUTH_CODE);
		Assert.assertEquals(Constants.INVALID_AUTH_CODE_RESPONSE, response.getStatus());
    }
    
    @Test
    public void testRegisterCustomerWithMobileNotSendAuthCode(){
		RegisterCustomerResponse response = registerCustomerWithAuthCode(CUST_ID_004, GOOD_AUTH_CODE);
		Assert.assertEquals(Constants.INVALID_AUTH_CODE_RESPONSE, response.getStatus());
    }
    
    protected RegisterCustomerResponse registerCustomerWithAuthCode(String cid, String authCode){
		RegisterCustomerRequest request = new RegisterCustomerRequest(new UserContext(cid, PASSWORD));
		request.setChannelId("CID:007");
		request.setUserId("UID:888");
		request.setAuthCode(authCode);
		return consumer.registerCustomerWithAuthCode(request);
	}
	
}
