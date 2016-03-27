package com.milktea.common.redemption;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Test;

public class AmountRedemptionPolicyTest {
	
	@Test
	public void testAmountRedemptionPolicyWithGoodThreshold(){
		AmountRedemptionPolicy policy = new AmountRedemptionPolicy(new BigDecimal("1.00"));
		Assert.assertTrue(policy.getRedemptionThreshold().compareTo(new BigDecimal("1.00"))==0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAmountRedemptionPolicyWithBadThreshold1(){
		new AmountRedemptionPolicy(new BigDecimal("0.00"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAmountRedemptionPolicyWithBadThreshold2(){
		new AmountRedemptionPolicy(new BigDecimal("-1.00"));
	}
	
	@Test(expected=NullPointerException.class)
	public void testAmountRedemptionPolicyWithBadThreshold(){
		new AmountRedemptionPolicy(null);
	}
}
