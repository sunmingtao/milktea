package com.milktea.common.enums;

import junit.framework.Assert;

import org.junit.Test;

import com.milktea.common.constants.Constants;
import com.milktea.common.dto.registercustomer.RegisterCustomerResponse;
import com.milktea.common.dto.registershop.RegisterShopResponse;

public class WebServiceEnumTest {

	@Test
	public void testForWebServicePath1() {
		WebServiceEnum wsEnum = WebServiceEnum.forWebServicePath(Constants.REGISTER_SHOP_PATH);
		Assert.assertEquals(wsEnum, WebServiceEnum.REGISTER_SHOP);
		Assert.assertEquals(wsEnum.getResponseClassType(), RegisterShopResponse.class);
		Assert.assertEquals(wsEnum.toString(), Constants.REGISTER_SHOP_PATH);
	}
	
	@Test
	public void testForWebServicePath2() {
		WebServiceEnum wsEnum = WebServiceEnum.forWebServicePath(Constants.REGISTER_USER_WITH_AUTH_CODE_PATH);
		Assert.assertEquals(wsEnum, WebServiceEnum.REGISTER_CUSTOMER_WITH_AUTH_CODE);
		Assert.assertEquals(wsEnum.getResponseClassType(), RegisterCustomerResponse.class);
		Assert.assertEquals(wsEnum.toString(), Constants.REGISTER_USER_WITH_AUTH_CODE_PATH);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testForWebServicePath3() {
		WebServiceEnum.forWebServicePath(null);
	}

}
