package com.milktea.service.purchase.handler;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.milktea.entity.TransactionDetail;
import com.milktea.entity.TransactionSummary;

@Component
public class AmountPurchaseHandler extends AbstractPurchaseHandler {

	protected void updatePurchaseValue(TransactionDetail transactionDetail, Object purchaseValue) {
		transactionDetail.setAmount((BigDecimal)purchaseValue);
	}
	
	protected void updateTotalPurchaseValue(TransactionSummary summary, Object purchaseValue) {
		summary.addTotalAmount((BigDecimal)purchaseValue);
	}
	
	public String toString(){
		return "AmountPurchaseHandler";
	}
}
