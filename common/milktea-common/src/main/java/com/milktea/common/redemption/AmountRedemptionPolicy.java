package com.milktea.common.redemption;

import java.math.BigDecimal;

import com.milktea.common.enums.TransactionType;

public class AmountRedemptionPolicy implements RedemptionPolicy<BigDecimal> {

	private final BigDecimal amountToRedeem;
	
	protected AmountRedemptionPolicy(BigDecimal threshhold) {
		super();
		if (threshhold.compareTo(BigDecimal.ZERO) <= 0){
			throw new IllegalArgumentException("Threshhold must be greater than 0");
		}
		this.amountToRedeem = threshhold;	
	}

	@Override
	public TransactionType getTransactionType() {
		return TransactionType.AMOUNT;
	}

	@Override
	public BigDecimal getRedemptionThreshold() {
		return amountToRedeem;
	}
	
	public String toString(){
		return getTransactionType().toString();
	}

}
