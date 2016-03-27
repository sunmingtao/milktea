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
import com.milktea.service.redeem.factory.RedeemHandlerFactory;
import com.milktea.service.redeem.handler.AmountRedeemHandler;
import com.milktea.service.redeem.handler.CupRedeemHandler;
import com.milktea.service.redeem.handler.RedeemHandler;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/test-context.xml"})
@ActiveProfiles(Constants.PROFILE)
public class RedeemHandlerFactoryTest {
	@Autowired
	private RedeemHandlerFactory redeemFactory;

	@Test
	public void testAmountRedeemHandler(){
		RedeemHandler handler = redeemFactory.instance(TransactionType.AMOUNT);
		Assert.assertTrue(handler instanceof AmountRedeemHandler);
	}
	
	@Test
	public void testCupRedeemHandler(){
		RedeemHandler handler = redeemFactory.instance(TransactionType.CUP);
		Assert.assertTrue(handler instanceof CupRedeemHandler);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRedeemHandlerWithInvalidInput(){
		redeemFactory.instance(null);
	}
}
