package com.milktea.common.dto;

import junit.framework.Assert;

import org.junit.Test;

public class UserContextTest {
	
	@Test
	public void testNormalCase(){
		UserContext context = new UserContext("abcde", "12345");
		Assert.assertEquals(context.getUsername(), "abcde");
		Assert.assertEquals(context.getPassword(), "12345");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullUsername(){
		new UserContext(null, "12345");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyUsername1(){
		new UserContext("", "12345");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyUsername2(){
		new UserContext("	", "12345");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullPassword(){
		new UserContext("abcde", null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyPassword1(){
		new UserContext("abcde", " ");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyPassword2(){
		new UserContext("abcde", "");
	}
}
