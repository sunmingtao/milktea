package com.milktea.service.purchase.handler;

import org.springframework.stereotype.Component;

import com.milktea.entity.TransactionDetail;
import com.milktea.entity.TransactionSummary;

@Component
public class CupPurchaseHandler extends AbstractPurchaseHandler {


	@Override
	protected void updatePurchaseValue(TransactionDetail transactionDetail,
			Object purchaseValue) {
		transactionDetail.setNumberOfCups((Integer)purchaseValue);
	}

	@Override
	protected void updateTotalPurchaseValue(TransactionSummary summary,
			Object purchaseValue) {
		summary.addTotalCups((Integer)purchaseValue);
	}
	
	public String toString(){
		return "CupPurchaseHandler";
	}

}
