package com.milktea.service.it;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.milktea.common.dto.UserContext;
import com.milktea.common.dto.registershop.RegisterShopRequest;
import com.milktea.common.redemption.RedemptionPolicyFactory;
import com.milktea.exception.InvalidAuthCodeException;
import com.milktea.exception.ShopExistsException;
import com.milktea.service.api.RegisterShopService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/test-context.xml"})
@ActiveProfiles(Constants.PROFILE)
public class RegisterShopServiceIT extends AbstractTransactionalJUnit4SpringContextTests{

	private static final String SHOP_ID_001 = "s001";
	private static final String SHOP_ID_002 = "s002";
	
	private static final String SHOP_NAME_001 = "SHOP_NAME_001";
	private static final String SHOP_NAME_002 = "SHOP_NAME_002";
	
	@Autowired
	private RegisterShopService registerShopService;
	
	@Before
	public void setup(){
		executeSqlScript("sql/register-shop-test-data.sql", false);
	}
	
	@Test
	public void testRegisterCupRedemptionShopWithNonExistingShopId() throws ShopExistsException{
		registerCupRedemptionShopWithoutAuthCode(SHOP_ID_001, SHOP_NAME_001);
		Assert.assertEquals(1,countRowsInTable("T_SHOP"));
	}
	
	@Test
	public void testRegisterAmountRedemptionShopWithNonExistingShopId() throws ShopExistsException{
		registerAmountRedemptionShop(SHOP_ID_001, SHOP_NAME_001);
		Assert.assertEquals(1,countRowsInTable("T_SHOP"));
	}
	
	@Test
	public void testRegisterShopWithExistingShopId() throws ShopExistsException{
		registerCupRedemptionShopWithoutAuthCode(SHOP_ID_001, SHOP_NAME_001);
		registerCupRedemptionShopWithoutAuthCode(SHOP_ID_001, SHOP_NAME_002);
		Assert.assertEquals(1,countRowsInTable("T_SHOP"));
	}
	
	@Test(expected=ShopExistsException.class)
	public void testRegisterShopWithExistingShopName() throws ShopExistsException{
		registerCupRedemptionShopWithoutAuthCode(SHOP_ID_001, SHOP_NAME_001);
		registerCupRedemptionShopWithoutAuthCode(SHOP_ID_002, SHOP_NAME_001);
	}
	
	@Test
	public void testRegisterShopWithValidAuthCode() throws Exception{
		registerCupRedemptionShopWithAuthCode(SHOP_ID_001, SHOP_NAME_001, "47");
		Assert.assertEquals(1,countRowsInTable("T_SHOP"));
	}
	
	@Test(expected=InvalidAuthCodeException.class)
	public void testRegisterShopWithInvalidAuthCode1() throws Exception{
		registerCupRedemptionShopWithAuthCode(SHOP_ID_001, SHOP_NAME_001, "45");
	}
	
	@Test(expected=InvalidAuthCodeException.class)
	public void testRegisterShopWithInvalidAuthCode2() throws Exception{
		registerCupRedemptionShopWithAuthCode(SHOP_ID_002, SHOP_NAME_001, "47");
	}
	
	@Test(expected=InvalidAuthCodeException.class)
	public void testRegisterShopWithInvalidAuthCode3() throws Exception{
		registerCupRedemptionShopWithAuthCode(SHOP_ID_001, SHOP_NAME_001, null);
	}
	
	private void registerCupRedemptionShopWithoutAuthCode(String sid, String sname) throws ShopExistsException{
		RegisterShopRequest request = new RegisterShopRequest(
				new UserContext(sid, "Password"), 
				RedemptionPolicyFactory.createCupRedemptionPolicy(10));
		request.setShopName(sname);
		registerShopService.registerShopWithoutAuthCode(request);
	}
	
	private void registerCupRedemptionShopWithAuthCode(String sid, String sname, String authCode) 
			throws ShopExistsException, InvalidAuthCodeException{
		RegisterShopRequest request = new RegisterShopRequest(
				new UserContext(sid, "Password"), 
				RedemptionPolicyFactory.createCupRedemptionPolicy(10));
		request.setShopName(sname);
		request.setAuthCode(authCode);
		registerShopService.registerShopWithAuthCode(request);
	}
	
	private void registerAmountRedemptionShop(String sid, String sname) throws ShopExistsException{
		RegisterShopRequest request = new RegisterShopRequest(
				new UserContext(sid, "Password"), 
				RedemptionPolicyFactory.createAmountRedemptionPolicy(new BigDecimal("50.00")));
		request.setShopName(sname);
		registerShopService.registerShopWithoutAuthCode(request);
	}
	
}
