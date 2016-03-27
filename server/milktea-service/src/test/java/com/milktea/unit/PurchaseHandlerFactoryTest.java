package com.milktea.unit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.milktea.common.enums.TransactionType;
import com.milktea.service.it.Constants;
import com.milktea.service.purchase.factory.PurchaseHandlerFactory;
import com.milktea.service.purchase.handler.PurchaseHandler;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/test-context.xml"})
@ActiveProfiles(Constants.PROFILE)
public class PurchaseHandlerFactoryTest {
	@Autowired
	private PurchaseHandlerFactory purchaseHandlerFactory;

	@Test
	public void testAmountRedeemHandler(){
		PurchaseHandler handler = purchaseHandlerFactory.instance(TransactionType.AMOUNT);
		Assert.assertEquals("AmountPurchaseHandler", handler.toString());
	}
	
	@Test
	public void testCupRedeemHandler(){
		PurchaseHandler handler = purchaseHandlerFactory.instance(TransactionType.CUP);
		Assert.assertEquals("CupPurchaseHandler", handler.toString());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRedeemHandlerWithInvalidInput(){
		purchaseHandlerFactory.instance(null);
	}
}
