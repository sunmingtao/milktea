package com.milktea.service.redeem.handler;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.milktea.entity.TransactionDetail;
import com.milktea.entity.TransactionSummary;
import com.milktea.service.context.TransactionContext;

@Component
public class AmountRedeemHandler extends AbstractRedeemHandler {

	protected TransactionDetail createTransactionDetailEntity(TransactionContext context) {
		TransactionDetail detail = new TransactionDetail(context.getShopId(), context.getCustomerId());
		detail.setAmountRedeemed((BigDecimal)context.getTransactionValue());
		return detail;
	}

	@Override
	protected void updateTransactionSummary(TransactionSummary summary,
			Object threshhold) {
		summary.addTotalAmountRedeemed((BigDecimal)threshhold);
	}

	@Override
	protected boolean isValid(TransactionSummary summary) {
		return hasEnoughAmountToRedeem(summary);
	}

	private boolean hasEnoughAmountToRedeem(TransactionSummary summary) {
		return summary.getTotalAmount().compareTo(summary.getTotalAmountRedeemed()) >= 0;
	}

}
