package com.milktea.service.it;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.milktea.exception.InsufficientCreditToRedeemException;
import com.milktea.service.api.PurchaseService;
import com.milktea.service.api.RedeemService;
import com.milktea.service.api.RegisterShopService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/test-context.xml"})
@ActiveProfiles(Constants.PROFILE)
public class RedeemServiceIT extends AbstractTransactionalJUnit4SpringContextTests{
	
	private static final String CUST_ID_001 = "c001";
	
	private static final String SHOP_ID_001 = "s001";

	private static final String SHOP_ID_002 = "s002";
	
	private static final String SHOP_ID_003 = "s003";
	
	private static final String SHOP_ID_004 = "s004";

	private static final String SHOP_ID_005 = "s005";

	private static final String SHOP_ID_006 = "s006";
	
	@Autowired
	private RegisterShopService registerShopService;
	
	@Autowired
	private RedeemService redeemService;
	
	@Autowired
	private PurchaseService saveTransactionService;
	
	@Before
	public void setup(){
		executeSqlScript("sql/redeem-test-data.sql", false);
	}
	
	@Test
	public void testSuccessfulAmountRedeem1() throws InsufficientCreditToRedeemException{
		redeemService.redeem(SHOP_ID_001, CUST_ID_001);
	}
	
	@Test
	public void testSuccessfulCupRedeem1() throws InsufficientCreditToRedeemException{
		redeemService.redeem(SHOP_ID_002, CUST_ID_001);
	}
	
	@Test
	public void testSuccessfulAmountRedeem2() throws InsufficientCreditToRedeemException{
		redeemService.redeem(SHOP_ID_003, CUST_ID_001);
	}
	
	@Test
	public void testSuccessfulCupRedeem2() throws InsufficientCreditToRedeemException{
		redeemService.redeem(SHOP_ID_004, CUST_ID_001);
	}
	
	@Test(expected=InsufficientCreditToRedeemException.class)
	public void testInsuffientAmountToRedeem() throws InsufficientCreditToRedeemException{
		redeemService.redeem(SHOP_ID_005, CUST_ID_001);
	}
	
	@Test(expected=InsufficientCreditToRedeemException.class)
	public void testInsuffientCupToRedeem() throws InsufficientCreditToRedeemException{
		redeemService.redeem(SHOP_ID_006, CUST_ID_001);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyCustomerId1() throws InsufficientCreditToRedeemException{
		redeemService.redeem(SHOP_ID_006, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyCustomerId2() throws InsufficientCreditToRedeemException{
		redeemService.redeem(SHOP_ID_006, "");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyCustomerId3() throws InsufficientCreditToRedeemException{
		redeemService.redeem(SHOP_ID_006, " ");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyShopId1() throws InsufficientCreditToRedeemException{
		redeemService.redeem(SHOP_ID_006, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyShopId2() throws InsufficientCreditToRedeemException{
		redeemService.redeem(SHOP_ID_006, "");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyShopId3() throws InsufficientCreditToRedeemException{
		redeemService.redeem(SHOP_ID_006, " ");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonExistingShopId() throws InsufficientCreditToRedeemException{
		redeemService.redeem("Non-existing", CUST_ID_001);
	}
}
