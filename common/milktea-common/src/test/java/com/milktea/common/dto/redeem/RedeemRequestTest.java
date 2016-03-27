package com.milktea.common.dto.redeem;

import org.junit.Test;

import com.milktea.common.dto.UserContext;

public class RedeemRequestTest {
	@Test
	public void testNormalCase(){
		new RedeemRequest(new UserContext("987654321", "pass"), "123456789");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullCustomerId(){
		new RedeemRequest(new UserContext("987654321", "pass"), null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyCustomerId1(){
		new RedeemRequest(new UserContext("987654321", "pass"), "");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyCustomerId2(){
		new RedeemRequest(new UserContext("987654321", "pass"), "	");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNoUserContext(){
		new RedeemRequest(null, "123456789");
	}
}
