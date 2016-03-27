package com.milktea.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.milktea.common.constants.Constants;
import com.milktea.common.dto.purchase.PurchaseRequest;
import com.milktea.common.dto.purchase.PurchaseResponse;
import com.milktea.common.dto.redeem.RedeemRequest;
import com.milktea.common.dto.redeem.RedeemResponse;
import com.milktea.common.dto.retrievesummary.RetrieveTranSummaryRequest;
import com.milktea.common.dto.retrievesummary.RetrieveTranSummaryResponse;
import com.milktea.entity.TransactionSummary;
import com.milktea.exception.InsufficientCreditToRedeemException;
import com.milktea.mapper.TransactionSummaryMapper;
import com.milktea.service.api.PurchaseService;
import com.milktea.service.api.RedeemService;
import com.milktea.service.api.RetrieveTransactionSummaryService;

@Controller
public class TransactionController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private RetrieveTransactionSummaryService retrieveTransactionSummaryService;
	
	@Autowired
	private RedeemService redeemService;
	
	@RequestMapping(value=Constants.PURCHASE_PATH, method = RequestMethod.POST)
	@ResponseBody
	public PurchaseResponse purchase(@RequestBody PurchaseRequest request){
		purchaseService.purchase(request);
		PurchaseResponse response = new PurchaseResponse();
		response.setStatus(Constants.SUCCESS_RESPONSE);
		return response;
	}
	
	@RequestMapping(value=Constants.REDEEM_PATH, method = RequestMethod.POST)
	@ResponseBody
	public RedeemResponse redeem(@RequestBody RedeemRequest request){
		String shopId = request.getUserContext().getUsername();
		RedeemResponse response = new RedeemResponse();
		try {
			redeemService.redeem(shopId, request.getCustomerId());
			response.setStatus(Constants.SUCCESS_RESPONSE);
		} catch (InsufficientCreditToRedeemException e) {
			response.setStatus(Constants.INSUFFICIENT_CREDIT_TO_REDEEM_RESPONSE);
		}
		return response;
	}
	
	@RequestMapping(value=Constants.GET_TRANSACTION_SUMMARY_FOR_CUSTOMER_PATH, 
			method = RequestMethod.POST)
	@ResponseBody
	public RetrieveTranSummaryResponse getTransactionSummaryForCustomer
		(@RequestBody RetrieveTranSummaryRequest request){
		String customerId = request.getUserContext().getUsername();
		List<TransactionSummary> list = retrieveTransactionSummaryService.getTransactionSummaryForCustomer(customerId);
		return createTransactionSummaryResponse(list);
	}
	

	private RetrieveTranSummaryResponse createTransactionSummaryResponse(
			List<TransactionSummary> list) {
		RetrieveTranSummaryResponse response = new RetrieveTranSummaryResponse();
		for (TransactionSummary summary: list){
			response.addTransactionSummary(TransactionSummaryMapper.mapToDto(summary));
		}
		response.setStatus(Constants.SUCCESS_RESPONSE);
		return response;
	}
	
	
}
