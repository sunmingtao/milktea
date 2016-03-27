package com.milktea.integration;

import junit.framework.Assert;

import org.junit.Test;

import com.milktea.common.constants.Constants;
import com.milktea.common.dto.UserContext;
import com.milktea.common.dto.purchase.PurchaseRequest;
import com.milktea.common.dto.purchase.PurchaseResponse;
import com.milktea.common.dto.registercustomer.RegisterCustomerRequest;
import com.milktea.common.dto.registershop.RegisterShopRequest;
import com.milktea.common.dto.registershop.RegisterShopResponse;
import com.milktea.common.dto.retrievesummary.RetrieveTranSummaryRequest;
import com.milktea.common.dto.retrievesummary.RetrieveTranSummaryResponse;
import com.milktea.common.purchase.PurchaseDetailFactory;
import com.milktea.common.redemption.RedemptionPolicyFactory;

public class RegisterShopIT extends MilkteaIT{
    
	private static final String SHOP_ID_001 = "regShopId001";
	private static final String SHOP_ID_002 = "regShopId002";
	private static final String SHOP_ID_003 = "regShopId003";
	private static final String SHOP_ID_004 = "regShopId004";
	private static final String SHOP_ID_005 = "regShopId005";
	private static final String SHOP_ID_006 = "regShopId006";
	
	private static final String SHOP_NAME_001 = "regShopName001";
	private static final String SHOP_NAME_002 = "regShopName002";
	private static final String SHOP_NAME_003 = "regShopName003";
	private static final String SHOP_NAME_004 = "regShopName004";
	private static final String SHOP_NAME_005 = "regShopName005";
	
    @Test
    public void testRegisterShop(){
    	RegisterShopResponse response = registerCupRedemtpionShop(SHOP_ID_001, SHOP_NAME_001);
		Assert.assertEquals(Constants.SUCCESS_RESPONSE, response.getStatus());
		response = registerCupRedemtpionShop(SHOP_ID_001, SHOP_NAME_001);
		Assert.assertEquals(Constants.SHOP_EXISTS_RESPONSE, response.getStatus());
    }
    
    @Test
    public void testRegisterShopAndMakeTransactionWithGoodUsernamePassword(){
    	RegisterShopResponse response = registerCupRedemtpionShop(SHOP_ID_002, SHOP_NAME_002);
		Assert.assertEquals(Constants.SUCCESS_RESPONSE, response.getStatus());
		PurchaseRequest makeTransactionRequest 
			= new PurchaseRequest(createValidNewShopUserContext2(), "13003109241", PurchaseDetailFactory.createCupPurchaseDetail(3));
		PurchaseResponse makeTransactionResponse = consumer.purchase(makeTransactionRequest);
		Assert.assertEquals(Constants.SUCCESS_RESPONSE, makeTransactionResponse.getStatus());
    }
    
    @Test
    public void testRegisterShopAndMakeTransactionWithNonExistingUser(){
    	RegisterShopRequest request = new RegisterShopRequest(createValidNewShopUserContext3(), 
    			RedemptionPolicyFactory.createCupRedemptionPolicy(10));
    	request.setShopName(SHOP_NAME_003);
		RegisterShopResponse response = consumer.registerShop(request);
		Assert.assertEquals(Constants.SUCCESS_RESPONSE, response.getStatus());
		PurchaseRequest makeTransactionRequest 
			= new PurchaseRequest(createNonExistingUserContext(), "13003109241", PurchaseDetailFactory.createCupPurchaseDetail(3));
		PurchaseResponse makeTransactionResponse = consumer.purchase(makeTransactionRequest);
		Assert.assertEquals(Constants.INVALIE_USERNAME_OR_PASSWORD_RESPONSE, makeTransactionResponse.getStatus());
    	Assert.assertNull(makeTransactionResponse.getErrorMessage());
    }
    
    @Test
    public void testRegisterShopAndMakeTransactionWithExistingUserButBadPassword(){
    	RegisterShopRequest request = new RegisterShopRequest(createValidNewShopUserContext4(), 
    			RedemptionPolicyFactory.createCupRedemptionPolicy(10));
    	request.setShopName(SHOP_NAME_004);
		RegisterShopResponse response = consumer.registerShop(request);
		Assert.assertEquals(Constants.SUCCESS_RESPONSE, response.getStatus());
		PurchaseRequest makeTransactionRequest 
			= new PurchaseRequest(createExistingUserBadPasswordContext(), "13003109241", PurchaseDetailFactory.createCupPurchaseDetail(3));
		PurchaseResponse makeTransactionResponse = consumer.purchase(makeTransactionRequest);
		Assert.assertEquals(Constants.INVALIE_USERNAME_OR_PASSWORD_RESPONSE, makeTransactionResponse.getStatus());
    	Assert.assertNull(makeTransactionResponse.getErrorMessage());
    }
    
    @Test
    public void testRegisterShopAndMakeTransactionAndRegisterCustomerAndGetTransactionDetail(){
    	//Register shop
    	RegisterShopRequest request = new RegisterShopRequest(createValidNewShopUserContext5(), 
    			RedemptionPolicyFactory.createCupRedemptionPolicy(10));
    	request.setShopName(SHOP_NAME_005);
    	RegisterShopResponse shopResponse = consumer.registerShop(request);
		Assert.assertEquals(Constants.SUCCESS_RESPONSE, shopResponse.getStatus());
		//Make transaction
		PurchaseRequest makeTransactionRequest 
			= new PurchaseRequest(createValidNewShopUserContext5(), "1800567567", PurchaseDetailFactory.createCupPurchaseDetail(3));
		consumer.purchase(makeTransactionRequest);
		//Register Customer
		RegisterCustomerRequest customerRequest = new RegisterCustomerRequest(new UserContext("1800567567", PASSWORD));
		consumer.registerCustomerWithoutAuthCode(customerRequest);
		//Get transaction summary
		RetrieveTranSummaryRequest transactionSummaryRequest = new RetrieveTranSummaryRequest(new UserContext("1800567567", PASSWORD));
		RetrieveTranSummaryResponse response = consumer.getTransactionSummaryForCustomer(transactionSummaryRequest);
		Assert.assertEquals(1, response.getTransactionSummaryList().size());
    }

    private UserContext createValidNewShopUserContext() {
    	UserContext userContext = new UserContext("0423747412", PASSWORD);
		return userContext;
	}
    
    private UserContext createValidNewShopUserContext2() {
		UserContext userContext = new UserContext("regShopId002", PASSWORD);
		return userContext;
	}
    
    private UserContext createValidNewShopUserContext3() {
    	UserContext userContext = new UserContext("0423747414",PASSWORD);
		return userContext;
	}
    
    private UserContext createNonExistingUserContext() {
    	UserContext userContext = new UserContext("Idontexist",PASSWORD);
		return userContext;
	}
	
	private UserContext createValidNewShopUserContext4() {
		UserContext userContext = new UserContext("0423747415",PASSWORD);
		return userContext;
	}
	
	private UserContext createValidNewShopUserContext5() {
		UserContext userContext = new UserContext("0423747416",PASSWORD);
		return userContext;
	}
	
	private UserContext createExistingUserBadPasswordContext() {
		UserContext userContext = new UserContext("0423747415",BAD_PASSWORD);
		return userContext;
	}
}
