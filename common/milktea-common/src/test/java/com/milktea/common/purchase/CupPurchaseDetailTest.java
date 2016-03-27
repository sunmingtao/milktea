package com.milktea.common.purchase;

import junit.framework.Assert;

import org.junit.Test;

import com.milktea.common.enums.TransactionType;


public class CupPurchaseDetailTest{
	
	@Test
	public void test(){
		CupPurchaseDetail detail = new CupPurchaseDetail(23);
		Assert.assertEquals(TransactionType.CUP.toString(), detail.toString());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test2(){
		new CupPurchaseDetail(0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test3(){
		new CupPurchaseDetail(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test4(){
		new CupPurchaseDetail(null);
	}
	
}
