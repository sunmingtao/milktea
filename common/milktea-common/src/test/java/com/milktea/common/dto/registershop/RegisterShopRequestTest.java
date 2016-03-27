package com.milktea.common.dto.registershop;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Test;

import com.milktea.common.dto.UserContext;
import com.milktea.common.redemption.RedemptionPolicyFactory;

public class RegisterShopRequestTest {
	@Test
	public void testCupRedemptionPolicy(){
		UserContext uc = new UserContext("0423747410", "password");
		RegisterShopRequest request = new RegisterShopRequest(uc, 
				RedemptionPolicyFactory.createCupRedemptionPolicy(10));
		Assert.assertTrue(request.hasCupRedemptionPolicy());
		Assert.assertFalse(request.hasAmountRedemptionPolicy());
	}
	
	@Test
	public void testAmountRedemptionPolicy(){
		UserContext uc = new UserContext("0423747410", "password");
		RegisterShopRequest request = new RegisterShopRequest(uc, 
				RedemptionPolicyFactory.createAmountRedemptionPolicy(new BigDecimal("50.00")));
		Assert.assertFalse(request.hasCupRedemptionPolicy());
		Assert.assertTrue(request.hasAmountRedemptionPolicy());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNoRedemptionPolicy(){
		UserContext uc = new UserContext("0423747410", "password");
		new RegisterShopRequest(uc, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNoUserContext(){
		new RegisterShopRequest(null, 
				RedemptionPolicyFactory.createAmountRedemptionPolicy(new BigDecimal("50.00")));
	}
}
