package com.milktea.unit;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Test;

import com.milktea.common.enums.TransactionType;
import com.milktea.common.redemption.RedemptionPolicy;
import com.milktea.entity.Shop;

public class ShopTest {

	@Test
	public void testAmountRedemptionPolicy() {
		Shop shop = new Shop("dummy", "dummy");
		shop.setAmountToRedeem(new BigDecimal("50.00"));
		RedemptionPolicy<?> rp = shop.getRedemptionPolicy();
		Assert.assertEquals(rp.getTransactionType(), TransactionType.AMOUNT);
		BigDecimal threshold = (BigDecimal)rp.getRedemptionThreshold();
		Assert.assertTrue(threshold.compareTo(new BigDecimal("50.00"))==0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidAmountRedemptionPolicy1() {
		Shop shop = new Shop("dummy", "dummy");
		shop.setAmountToRedeem(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidAmountRedemptionPolicy2() {
		Shop shop = new Shop("dummy", "dummy");
		shop.setAmountToRedeem(BigDecimal.ZERO);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidAmountRedemptionPolicy3() {
		Shop shop = new Shop("dummy", "dummy");
		shop.setAmountToRedeem(new BigDecimal("-1"));
	}
	
	@Test
	public void testCupRedemptionPolicy() {
		Shop shop = new Shop("dummy", "dummy");
		shop.setNumberOfCupsToRedeem(10);
		RedemptionPolicy<?> rp = shop.getRedemptionPolicy();
		Assert.assertEquals(rp.getTransactionType(), TransactionType.CUP);
		Integer threshold = (Integer)rp.getRedemptionThreshold();
		Assert.assertEquals(threshold.intValue(), 10);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidCupRedemptionPolicy1() {
		Shop shop = new Shop("dummy", "dummy");
		shop.setNumberOfCupsToRedeem(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidCupRedemptionPolicy2() {
		Shop shop = new Shop("dummy", "dummy");
		shop.setNumberOfCupsToRedeem(0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidCupRedemptionPolicy3() {
		Shop shop = new Shop("dummy", "dummy");
		shop.setNumberOfCupsToRedeem(-1);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testTooManyRedemptionPolicies() {
		Shop shop = new Shop("dummy", "dummy");
		shop.setNumberOfCupsToRedeem(10);
		shop.setAmountToRedeem(new BigDecimal("50.00"));
		shop.getRedemptionPolicy();
	}
}
