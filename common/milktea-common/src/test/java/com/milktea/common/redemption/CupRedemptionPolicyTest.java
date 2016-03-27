package com.milktea.common.redemption;

import junit.framework.Assert;

import org.junit.Test;

public class CupRedemptionPolicyTest {
	
	@Test
	public void testCupRedemptionPolicyWithGoodThreshold(){
		CupRedemptionPolicy policy = new CupRedemptionPolicy(10);
		Assert.assertTrue(policy.getRedemptionThreshold().compareTo(10)==0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCupRedemptionPolicyWithBadThreshold1(){
		new CupRedemptionPolicy(0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCupRedemptionPolicyWithBadThreshold2(){
		new CupRedemptionPolicy(-1);
	}
	
	@Test(expected=NullPointerException.class)
	public void testCupRedemptionPolicyWithBadThreshold(){
		new CupRedemptionPolicy(null);
	}
}
