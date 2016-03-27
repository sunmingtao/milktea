package com.milktea.common.dto.purchase;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Test;

import com.milktea.common.dto.UserContext;
import com.milktea.common.purchase.PurchaseDetailFactory;

public class TransactionDetailRequestTest {
	
	@Test
	public void testNormalCupTransaction1(){
		PurchaseRequest request = 
				new PurchaseRequest(
						new UserContext("123456789", "pass"), "987654321", PurchaseDetailFactory.createCupPurchaseDetail(3));
		int cups = (Integer)request.getPurchaseValue();
		Assert.assertEquals(cups, 3);
		Assert.assertEquals("987654321", request.getCustomerId());
		Assert.assertNotNull(request.getUserContext());
	}
	
	@Test
	public void testNormalAmountTransaction1(){
		PurchaseRequest request = 
				new PurchaseRequest(new UserContext("123456789", "pass"), "987654321", 
						PurchaseDetailFactory.createAmountPurchaseDetail(new BigDecimal("10.50")));
		BigDecimal amount = (BigDecimal)request.getPurchaseValue();
		Assert.assertTrue(amount.compareTo(new BigDecimal("10.50"))==0);
		Assert.assertEquals("987654321", request.getCustomerId());
		Assert.assertNotNull(request.getUserContext());
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullCustomerId(){
		new PurchaseRequest(new UserContext("123456789", "pass"), null, 
				PurchaseDetailFactory.createCupPurchaseDetail(10));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyCustomerId1(){
		new PurchaseRequest(new UserContext("123456789", "pass"), "", 
				PurchaseDetailFactory.createCupPurchaseDetail(10));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyCustomerId2(){
		new PurchaseRequest(new UserContext("123456789", "pass"), "	", 
				PurchaseDetailFactory.createCupPurchaseDetail(10));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNoUserContext(){
		new PurchaseRequest(null, "987654321", PurchaseDetailFactory.createCupPurchaseDetail(10));
	}
}
