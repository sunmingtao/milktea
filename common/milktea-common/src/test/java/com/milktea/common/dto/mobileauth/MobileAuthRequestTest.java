package com.milktea.common.dto.mobileauth;

import org.junit.Test;

import com.milktea.common.dto.mobileauthcode.MobileAuthRequest;

public class MobileAuthRequestTest {
	
	@Test
	public void testNormalCase(){
		new MobileAuthRequest("1359888996");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullMobile(){
		new MobileAuthRequest(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyMobile1(){
		new MobileAuthRequest(" ");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyMobile2(){
		new MobileAuthRequest("		");
	}
}
