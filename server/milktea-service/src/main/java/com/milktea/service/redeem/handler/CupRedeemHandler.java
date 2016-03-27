package com.milktea.service.redeem.handler;

import org.springframework.stereotype.Component;

import com.milktea.entity.TransactionDetail;
import com.milktea.entity.TransactionSummary;
import com.milktea.service.context.TransactionContext;

@Component
public class CupRedeemHandler extends AbstractRedeemHandler {

	protected TransactionDetail createTransactionDetailEntity(TransactionContext context) {
		TransactionDetail detail = new TransactionDetail(context.getShopId(), context.getCustomerId());
		detail.setNumberOfCupsRedeemed((Integer)context.getTransactionValue());
		return detail;
	}

	@Override
	protected void updateTransactionSummary(TransactionSummary summary,
			Object threshhold) {
		summary.addTotalCupsRedeemed((Integer)threshhold);
	}

	@Override
	protected boolean isValid(TransactionSummary summary) {
		return hasEnoughCupsToRedeem(summary);
	}

	private boolean hasEnoughCupsToRedeem(TransactionSummary summary) {
		return summary.getTotalCups() >= summary.getTotalCupsRedeemed();
	}

}
