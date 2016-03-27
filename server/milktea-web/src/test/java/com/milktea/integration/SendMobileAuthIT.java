package com.milktea.integration;

import junit.framework.Assert;

import org.junit.Test;

import com.milktea.common.constants.Constants;
import com.milktea.common.dto.mobileauthcode.MobileAuthResponse;

public class SendMobileAuthIT extends MilkteaIT{
    
	private static final String CUST_ID_001 = "authCode001";
	private static final String CUST_ID_002 = "authCode";
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullInput(){
		consumer.sendMobileAuthCode(null);
	}
	
	@Test
    public void testSendMobileAuthCodeWithGoodMobileNumber(){
    	MobileAuthResponse response = sendMobileAuthCode(CUST_ID_001);
    	Assert.assertEquals(Constants.SUCCESS_RESPONSE, response.getStatus());
    }
    
    @Test
    public void testSendMobileAuthCodeWithBadMobileNumber(){
    	//The mock sms service rejects any mobile number less than 10 characters
    	MobileAuthResponse response = sendMobileAuthCode(CUST_ID_002);
    	Assert.assertEquals(Constants.INVALID_MOBILE_RESPONSE, response.getStatus());
    }

	
}
