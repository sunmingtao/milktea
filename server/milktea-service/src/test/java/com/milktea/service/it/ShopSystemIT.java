package com.milktea.service.it;

import java.math.BigDecimal;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.milktea.common.dto.UserContext;
import com.milktea.common.dto.purchase.PurchaseRequest;
import com.milktea.common.dto.registercustomer.RegisterCustomerRequest;
import com.milktea.common.dto.registershop.RegisterShopRequest;
import com.milktea.common.purchase.PurchaseDetailFactory;
import com.milktea.common.redemption.RedemptionPolicyFactory;
import com.milktea.entity.TransactionSummary;
import com.milktea.exception.InsufficientCreditToRedeemException;
import com.milktea.exception.ShopExistsException;
import com.milktea.service.api.PurchaseService;
import com.milktea.service.api.RedeemService;
import com.milktea.service.api.RegisterCustomerService;
import com.milktea.service.api.RegisterShopService;
import com.milktea.service.api.RetrieveTransactionSummaryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/test-context.xml"})
@ActiveProfiles(Constants.PROFILE)
public class ShopSystemIT extends AbstractTransactionalJUnit4SpringContextTests{
	
	private static final String SHOP_ID_001 = "s001";

	private static final String CUST_ID_001 = "c001";
	
	@Autowired
	private RegisterShopService registerShopService;
	
	@Autowired
	private RegisterCustomerService registerCustomerService;
	
	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private RedeemService redeemService;
	
	@Autowired
	private RetrieveTransactionSummaryService retrieveTransactionSummaryService;
	
	@Test
	public void testRegisterShopBuyMilkteaRedeemGetTransactionSummary1() throws Exception{
		registerAmountRedemptionPolicyShop();
		buyMilktea1();
		redeem();
		getTransactionSummary1();
	}
	
	@Test
	public void testRegisterShopBuyMilkteaRedeemGetTransactionSummary2() throws Exception{
		registerCupRedemptionPolicyShop();
		buyMilktea2();
		redeem();
		getTransactionSummary2();
	}
	
	@Test
	public void testRegisterShopRegisterCustomerBuyMilkteaGetTransactionSummary1() throws Exception{
		registerAmountRedemptionPolicyShop();
		registerCustomer();
		buyMilktea1();
		getTransactionSummary3();
	}
	
	@Test(expected=InsufficientCreditToRedeemException.class)
	public void testRegisterShopBuyMilkteaRedeemGetTransactionSummary3() throws Exception{
		registerAmountRedemptionPolicyShop();
		buyMilktea3();
		redeem();
	}
	
	@Test(expected=InsufficientCreditToRedeemException.class)
	public void testRegisterShopBuyMilkteaRedeemGetTransactionSummary4() throws Exception{
		registerAmountRedemptionPolicyShop();
		buyMilktea4();
		redeem();
	}

	private void getTransactionSummary1() {
		List<TransactionSummary> list = retrieveTransactionSummaryService.getTransactionSummaryForCustomer(CUST_ID_001);
		Assert.assertEquals(1, list.size()); 
		TransactionSummary ts = list.get(0);
		Assert.assertEquals(CUST_ID_001, ts.getCustomer().getCustomerId());
		Assert.assertEquals(SHOP_ID_001, ts.getShop().getShopId());
		Assert.assertEquals(0, ts.getTotalCups().intValue());
		Assert.assertEquals(0, ts.getTotalCupsRedeemed().intValue());
		Assert.assertTrue(ts.getTotalAmount().compareTo(new BigDecimal("51.00"))==0);
		Assert.assertTrue(ts.getTotalAmountRedeemed().compareTo(new BigDecimal("50.00"))==0);
		Assert.assertTrue(ts.getShop().getAmountToRedeem().compareTo(new BigDecimal("50.00"))==0);
		Assert.assertTrue(ts.getShop().getNumberOfCupsToRedeem().compareTo(0)==0);
		Assert.assertNull(ts.getCustomer().getChannelId());
		Assert.assertNull(ts.getCustomer().getUserId());
	}
	
	private void getTransactionSummary2() {
		List<TransactionSummary> list = retrieveTransactionSummaryService.getTransactionSummaryForCustomer(CUST_ID_001);
		Assert.assertEquals(1, list.size()); 
		TransactionSummary ts = list.get(0);
		Assert.assertEquals(CUST_ID_001, ts.getCustomer().getCustomerId());
		Assert.assertEquals(SHOP_ID_001, ts.getShop().getShopId());
		Assert.assertEquals(11, ts.getTotalCups().intValue());
		Assert.assertEquals(10, ts.getTotalCupsRedeemed().intValue());
		Assert.assertTrue(ts.getTotalAmount().compareTo(new BigDecimal("0.00"))==0);
		Assert.assertTrue(ts.getTotalAmountRedeemed().compareTo(new BigDecimal("0.00"))==0);
		Assert.assertTrue(ts.getShop().getAmountToRedeem().compareTo(new BigDecimal("0.00"))==0);
		Assert.assertTrue(ts.getShop().getNumberOfCupsToRedeem().compareTo(10)==0);
		Assert.assertNull(ts.getCustomer().getChannelId());
		Assert.assertNull(ts.getCustomer().getUserId());
	}
	
	private void getTransactionSummary3() {
		List<TransactionSummary> list = retrieveTransactionSummaryService.getTransactionSummaryForCustomer(CUST_ID_001);
		Assert.assertEquals(1, list.size()); 
		TransactionSummary ts = list.get(0);
		Assert.assertEquals(CUST_ID_001, ts.getCustomer().getCustomerId());
		Assert.assertEquals(SHOP_ID_001, ts.getShop().getShopId());
		Assert.assertEquals(0, ts.getTotalCups().intValue());
		Assert.assertEquals(0, ts.getTotalCupsRedeemed().intValue());
		Assert.assertTrue(ts.getTotalAmount().compareTo(new BigDecimal("51.00"))==0);
		Assert.assertTrue(ts.getTotalAmountRedeemed().compareTo(new BigDecimal("0.00"))==0);
		Assert.assertTrue(ts.getShop().getAmountToRedeem().compareTo(new BigDecimal("50.00"))==0);
		Assert.assertTrue(ts.getShop().getNumberOfCupsToRedeem().compareTo(0)==0);
		Assert.assertEquals("CID", ts.getCustomer().getChannelId());
		Assert.assertEquals("UID", ts.getCustomer().getUserId());
	}

	private void redeem() throws InsufficientCreditToRedeemException {
		redeemService.redeem(SHOP_ID_001, CUST_ID_001);
	}

	private void buyMilktea1() {
		PurchaseRequest request = new PurchaseRequest(new UserContext(SHOP_ID_001, "Password"), CUST_ID_001, 
				PurchaseDetailFactory.createAmountPurchaseDetail(new BigDecimal("51")));
		purchaseService.purchase(request);
	}
	
	private void buyMilktea2() {
		PurchaseRequest request = new PurchaseRequest(new UserContext(SHOP_ID_001, "Password"), CUST_ID_001, 
				PurchaseDetailFactory.createCupPurchaseDetail(11));
		purchaseService.purchase(request);
	}
	
	private void buyMilktea3() {
		PurchaseRequest request = new PurchaseRequest(new UserContext(SHOP_ID_001, "Password"), CUST_ID_001, 
				PurchaseDetailFactory.createAmountPurchaseDetail(new BigDecimal("49")));
		purchaseService.purchase(request);
	}
	
	private void buyMilktea4() {
		PurchaseRequest request = new PurchaseRequest(new UserContext(SHOP_ID_001, "Password"), CUST_ID_001, 
				PurchaseDetailFactory.createCupPurchaseDetail(9));
		purchaseService.purchase(request);
	}

	private void registerAmountRedemptionPolicyShop() throws ShopExistsException {
		RegisterShopRequest request = new RegisterShopRequest(new UserContext(SHOP_ID_001, "Password"),
				RedemptionPolicyFactory.createAmountRedemptionPolicy(new BigDecimal("50.00")));
		request.setShopName("SHOP1");
		registerShopService.registerShopWithoutAuthCode(request);
	}
	
	private void registerCupRedemptionPolicyShop() throws ShopExistsException {
		RegisterShopRequest request = new RegisterShopRequest(new UserContext(SHOP_ID_001, "Password"),
				RedemptionPolicyFactory.createCupRedemptionPolicy(10));
		request.setShopName("SHOP2");
		registerShopService.registerShopWithoutAuthCode(request);
	}
	
	private void registerCustomer() {
		RegisterCustomerRequest request = new RegisterCustomerRequest(new UserContext(CUST_ID_001, "Password"));
		request.setChannelId("CID");
		request.setUserId("UID");
		registerCustomerService.registerCustomerWithoutAuthCode(request);
	}
}
