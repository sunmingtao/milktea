package com.milktea.integration;

import java.math.BigDecimal;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.milktea.common.constants.Constants;
import com.milktea.common.dto.UserContext;
import com.milktea.common.dto.redeem.RedeemRequest;
import com.milktea.common.dto.redeem.RedeemResponse;
import com.milktea.common.dto.retrievesummary.RetrieveTranSummaryRequest;
import com.milktea.common.dto.retrievesummary.RetrieveTranSummaryResponse;
import com.milktea.common.dto.retrievesummary.TransactionSummaryDto;

public class RedeemIT extends MilkteaIT {

	private static final String CUST_ID_001 = "c001";
	
	private static final String SHOP_ID_001 = "s001";

	private static final String SHOP_ID_002 = "s002";
	
	private static final String SHOP_ID_003 = "s003";
	
	private static final String SHOP_ID_004 = "s004";

	private static final String SHOP_ID_005 = "s005";

	private static final String SHOP_ID_006 = "s006";
	
	@Test
	public void testSuccessfulRedeem1() {
		RedeemRequest request = new RedeemRequest(
				new UserContext(SHOP_ID_001, PASSWORD), CUST_ID_001);
		RedeemResponse response = consumer.redeem(request);
		Assert.assertEquals(Constants.SUCCESS_RESPONSE, response.getStatus());
		Assert.assertNull(response.getErrorMessage());
		RetrieveTranSummaryRequest rtsRequest 
			= new RetrieveTranSummaryRequest(new UserContext(CUST_ID_001, PASSWORD));
		
		RetrieveTranSummaryResponse rtsResponse = consumer.getTransactionSummaryForCustomer(rtsRequest);
		List<TransactionSummaryDto> list = rtsResponse.getTransactionSummaryList();
		Assert.assertEquals(6, list.size());
		TransactionSummaryDto dto = list.get(0);
		Assert.assertTrue(dto.getTotalAmount().compareTo(new BigDecimal("50.00"))==0);
		Assert.assertTrue(dto.getTotalAmountRedeemed().compareTo(new BigDecimal("49.95"))==0);
	}
	
	@Test
	public void testInsufficientCreditToRedeem() {
		RedeemRequest request = new RedeemRequest(
				new UserContext(SHOP_ID_005, PASSWORD), CUST_ID_001);
		RedeemResponse response = consumer.redeem(request);
		Assert.assertEquals(Constants.INSUFFICIENT_CREDIT_TO_REDEEM_RESPONSE, response.getStatus());
		Assert.assertNull(response.getErrorMessage());
	}

}
