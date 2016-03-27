package com.milktea.integration;

import junit.framework.Assert;

import org.junit.Test;

import com.milktea.common.constants.Constants;
import com.milktea.common.dto.UserContext;
import com.milktea.common.dto.purchase.PurchaseRequest;
import com.milktea.common.dto.purchase.PurchaseResponse;
import com.milktea.common.purchase.PurchaseDetailFactory;

public class PurchaseIT extends MilkteaIT {

	@Test
	public void testMakeTransactionWithGoodAuthentication() {
		PurchaseRequest request = new PurchaseRequest(
				createValidShopUserContext(), "13003109241", PurchaseDetailFactory.createCupPurchaseDetail(3));
		PurchaseResponse response = consumer.purchase(request);
		Assert.assertEquals(Constants.SUCCESS_RESPONSE, response.getStatus());
		Assert.assertNull(response.getErrorMessage());
	}
	
	private UserContext createValidShopUserContext() {
		UserContext userContext = new UserContext("0423747411", "password");
		return userContext;
	}

	@Test
	public void testMakeTransactionWithBadAuthentication() {
		PurchaseRequest request = new PurchaseRequest(
				createInvalidUserContext(), "13003109241", PurchaseDetailFactory.createCupPurchaseDetail(3));
		PurchaseResponse response = consumer.purchase(request);
		Assert.assertEquals(Constants.INVALIE_USERNAME_OR_PASSWORD_RESPONSE,
				response.getStatus());
		Assert.assertNull(response.getErrorMessage());
	}
	
	private UserContext createInvalidUserContext() {
		UserContext userContext = new UserContext("0423747411", "password1");
		return userContext;
	}

}
