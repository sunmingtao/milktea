package com.milktea.common.purchase;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Test;

import com.milktea.common.enums.TransactionType;


public class AmountPurchaseDetailTest{
	
	@Test
	public void test(){
		AmountPurchaseDetail detail = new AmountPurchaseDetail(new BigDecimal("50.00"));
		Assert.assertEquals(TransactionType.AMOUNT.toString(), detail.toString());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test2(){
		new AmountPurchaseDetail(new BigDecimal("0.00"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test3(){
		new AmountPurchaseDetail(new BigDecimal("-1.00"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test4(){
		new AmountPurchaseDetail(null);
	}
	
}
