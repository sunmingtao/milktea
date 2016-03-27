package com.milktea.unit;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.milktea.entity.MobileAuth;
import com.milktea.enums.UserType;
import com.milktea.util.MobileAuthUtils;

public class MobileAuthUtilsTest {
	@Test
	public void testGetLatestAuthCode(){
		List<MobileAuth> list = new ArrayList<MobileAuth>();
		MobileAuth m1 = new MobileAuth("12345", "777", UserType.CUSTOMER);
		m1.setId(1);
		MobileAuth m2 = new MobileAuth("12345", "888", UserType.CUSTOMER);
		m2.setId(2);
		MobileAuth m3 = new MobileAuth("12345", "999", UserType.CUSTOMER);
		m3.setId(3);
		MobileAuth m4 = new MobileAuth("12345", "666", UserType.CUSTOMER);
		m4.setId(4);
		list.add(m1);
		list.add(m2);
		list.add(m3);
		list.add(m4);
		String authCode = MobileAuthUtils.getLatestAuthCode(list);
		Assert.assertEquals("666", authCode);
	}
	
	@Test
	public void testGetNthLatestAuthCode(){
		List<MobileAuth> list = new ArrayList<MobileAuth>();
		MobileAuth m1 = new MobileAuth("12345", "777", UserType.CUSTOMER);
		m1.setId(1);
		MobileAuth m2 = new MobileAuth("12345", "888", UserType.CUSTOMER);
		m2.setId(2);
		MobileAuth m3 = new MobileAuth("12345", "999", UserType.CUSTOMER);
		m3.setId(3);
		MobileAuth m4 = new MobileAuth("12345", "666", UserType.CUSTOMER);
		m4.setId(4);
		list.add(m1);
		list.add(m2);
		list.add(m3);
		list.add(m4);
		MobileAuth m = MobileAuthUtils.getNthLatestAuthCode(list, 3);
		Assert.assertEquals("888", m.getAuthenticationCode());
		m = MobileAuthUtils.getNthLatestAuthCode(list, 1);
		Assert.assertEquals("666", m.getAuthenticationCode());
		m = MobileAuthUtils.getNthLatestAuthCode(list, 4);
		Assert.assertEquals("777", m.getAuthenticationCode());
		m = MobileAuthUtils.getNthLatestAuthCode(list, 5);
		Assert.assertNull(m);
	}
	
	@Test
	public void testGetNthLatestAuthCode2(){
		List<MobileAuth> list = new ArrayList<MobileAuth>();
		MobileAuth m = MobileAuthUtils.getNthLatestAuthCode(list, 1);
		Assert.assertNull(m);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetNthLatestAuthCode3(){
		List<MobileAuth> list = new ArrayList<MobileAuth>();
		MobileAuthUtils.getNthLatestAuthCode(list, 0);
	}
}
