package com.milktea.service.it;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.milktea.common.dto.UserContext;
import com.milktea.common.dto.purchase.PurchaseRequest;
import com.milktea.common.purchase.PurchaseDetailFactory;
import com.milktea.service.api.PurchaseService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/test-context.xml"})
@ActiveProfiles(Constants.PROFILE)
public class PurchaseServiceIT extends AbstractTransactionalJUnit4SpringContextTests{
	
	private static final String SHOP_ID_001 = "s001";
	
	private static final String SHOP_ID_002 = "s002";
	
	private static final String CUST_ID_001 = "c001";
	
	@Autowired
	private PurchaseService purchaseService;
	
	@Before
	public void setup(){
		executeSqlScript("sql/save-transaction-test-data.sql", false);
	}
	
	@Test
	public void testSaveTransactionDetail1(){
		UserContext uc = new UserContext(SHOP_ID_001, "Password");
		PurchaseRequest request = new PurchaseRequest(uc, CUST_ID_001, PurchaseDetailFactory.createCupPurchaseDetail(2));
		purchaseService.purchase(request);
		Assert.assertEquals(1, countRowsInTable("T_TRANSACTION_DETAIL"));
		Assert.assertEquals(1, countRowsInTable("T_TRANSACTION_SUMMARY"));
		request = new PurchaseRequest(uc, CUST_ID_001, PurchaseDetailFactory.createCupPurchaseDetail(4));
		purchaseService.purchase(request);
		Assert.assertEquals(2, countRowsInTable("T_TRANSACTION_DETAIL"));
		Assert.assertEquals(1, countRowsInTable("T_TRANSACTION_SUMMARY"));
	}
	
	@Test
	public void testSaveTransactionDetail2(){
		UserContext uc = new UserContext(SHOP_ID_001, "Password");
		PurchaseRequest request 
			= new PurchaseRequest(uc, CUST_ID_001, PurchaseDetailFactory.createAmountPurchaseDetail(new BigDecimal("50")));
		purchaseService.purchase(request);
		Assert.assertEquals(1, countRowsInTable("T_TRANSACTION_DETAIL"));
		Assert.assertEquals(1, countRowsInTable("T_TRANSACTION_SUMMARY"));
		request = new PurchaseRequest(uc, CUST_ID_001, PurchaseDetailFactory.createAmountPurchaseDetail(new BigDecimal("100")));
		purchaseService.purchase(request);
		Assert.assertEquals(2, countRowsInTable("T_TRANSACTION_DETAIL"));
		Assert.assertEquals(1, countRowsInTable("T_TRANSACTION_SUMMARY"));
		uc = new UserContext(SHOP_ID_002, "Password");
		request = new PurchaseRequest(uc, CUST_ID_001, PurchaseDetailFactory.createAmountPurchaseDetail(new BigDecimal("50")));
		purchaseService.purchase(request);
		Assert.assertEquals(3, countRowsInTable("T_TRANSACTION_DETAIL"));
		Assert.assertEquals(2, countRowsInTable("T_TRANSACTION_SUMMARY"));
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void testSaveTransactionDetailWithInvalidShopId(){
		UserContext uc = new UserContext("NotExisting", "Password");
		PurchaseRequest request = new PurchaseRequest(uc, CUST_ID_001, PurchaseDetailFactory.createCupPurchaseDetail(2));
		purchaseService.purchase(request);
	}
	
}
