package com.milktea.unit;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Test;

import com.milktea.entity.Customer;
import com.milktea.entity.TransactionSummary;

public class TransactionSummaryTest {
	
	private static final Customer customer = new Customer(null, null);
	
	@Test
	public void testAddTotalAmount(){
		TransactionSummary summary = new TransactionSummary(null, customer);
		summary.addTotalAmount(new BigDecimal("1.5"));
		summary.addTotalAmount(new BigDecimal("5.7"));
		Assert.assertTrue(summary.getTotalAmount().compareTo(new BigDecimal("7.2"))==0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddInvalidTotalAmount1(){
		TransactionSummary summary = new TransactionSummary(null, customer);
		summary.addTotalAmount(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddInvalidTotalAmount2(){
		TransactionSummary summary = new TransactionSummary(null, customer);
		summary.addTotalAmount(BigDecimal.ZERO);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddInvalidTotalAmount3(){
		TransactionSummary summary = new TransactionSummary(null, customer);
		summary.addTotalAmount(new BigDecimal("-1"));
	}
	
	@Test
	public void testAddTotalCups(){
		TransactionSummary summary = new TransactionSummary(null, customer);
		summary.addTotalCups(8);
		summary.addTotalCups(6);
		Assert.assertTrue(summary.getTotalCups().compareTo(14)==0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddInvalidTotalCups1(){
		TransactionSummary summary = new TransactionSummary(null, customer);
		summary.addTotalCups(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddInvalidTotalCups2(){
		TransactionSummary summary = new TransactionSummary(null, customer);
		summary.addTotalCups(0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddInvalidTotalCups3(){
		TransactionSummary summary = new TransactionSummary(null, customer);
		summary.addTotalCups(-1);
	}
	
	@Test
	public void testAddTotalCupsRedeemed(){
		TransactionSummary summary = new TransactionSummary(null, customer);
		summary.addTotalCupsRedeemed(10);
		summary.addTotalCupsRedeemed(20);
		Assert.assertTrue(summary.getTotalCupsRedeemed().compareTo(30)==0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddTotalCupsRedeemedWithInvalidInput(){
		TransactionSummary summary = new TransactionSummary(null, customer);
		summary.addTotalCupsRedeemed(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddTotalCupsRedeemedWithInvalidInput2(){
		TransactionSummary summary = new TransactionSummary(null, customer);
		summary.addTotalCupsRedeemed(0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddTotalCupsRedeemedWithInvalidInput3(){
		TransactionSummary summary = new TransactionSummary(null, customer);
		summary.addTotalCupsRedeemed(-1);
	}
	
	@Test
	public void testAddTotalAmountRedeemed(){
		TransactionSummary summary = new TransactionSummary(null, customer);
		summary.addTotalAmountRedeemed(new BigDecimal("50"));
		summary.addTotalAmountRedeemed(new BigDecimal("100"));
		Assert.assertTrue(summary.getTotalAmountRedeemed().compareTo(new BigDecimal("150"))==0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddTotalAmountRedeemedWithInvalidInput(){
		TransactionSummary summary = new TransactionSummary(null, customer);
		summary.addTotalAmountRedeemed(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddTotalAmountRedeemedWithInvalidInput2(){
		TransactionSummary summary = new TransactionSummary(null, customer);
		summary.addTotalAmountRedeemed(BigDecimal.ZERO);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddTotalAmountRedeemedWithInvalidInput3(){
		TransactionSummary summary = new TransactionSummary(null, customer);
		summary.addTotalAmountRedeemed(new BigDecimal("-2"));
	}
	
	
}
