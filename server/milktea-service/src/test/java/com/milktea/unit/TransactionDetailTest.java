package com.milktea.unit;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.milktea.entity.TransactionDetail;

public class TransactionDetailTest {
	
	@Test
	public void testSetNumberOfCupsRedeemed(){
		TransactionDetail detail = new TransactionDetail("dummy" , "dummy");
		detail.setNumberOfCupsRedeemed(3);
		Assert.assertTrue(detail.getNumberOfCups().compareTo(-3)==0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetNumberOfCupsRedeemedWithInvalidInput(){
		TransactionDetail detail = new TransactionDetail("dummy" , "dummy");
		detail.setNumberOfCupsRedeemed(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetNumberOfCupsRedeemedWithInvalidInput2(){
		TransactionDetail detail = new TransactionDetail("dummy" , "dummy");
		detail.setNumberOfCupsRedeemed(0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetNumberOfCupsRedeemedWithInvalidInput3(){
		TransactionDetail detail = new TransactionDetail("dummy" , "dummy");
		detail.setNumberOfCupsRedeemed(-1);
	}
	
	@Test
	public void testSetAmountRedeemed(){
		TransactionDetail detail = new TransactionDetail("dummy" , "dummy");
		detail.setAmountRedeemed(new BigDecimal("20"));
		Assert.assertTrue(detail.getAmount().compareTo(new BigDecimal("-20"))==0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetAmountRedeemedWithInvalidInput(){
		TransactionDetail detail = new TransactionDetail("dummy" , "dummy");
		detail.setAmountRedeemed(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetAmountRedeemedWithInvalidInput2(){
		TransactionDetail detail = new TransactionDetail("dummy" , "dummy");
		detail.setAmountRedeemed(new BigDecimal("0"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetAmountRedeemedWithInvalidInput3(){
		TransactionDetail detail = new TransactionDetail("dummy" , "dummy");
		detail.setAmountRedeemed(new BigDecimal("-1"));
	}
}
