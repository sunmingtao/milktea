package com.milktea.common.redemption;

import com.milktea.common.enums.TransactionType;

public class CupRedemptionPolicy implements RedemptionPolicy<Integer> {

	private final Integer cupsToRedeem;

	protected CupRedemptionPolicy(Integer threshhold) {
		super();
		if (threshhold <= 0){
			throw new IllegalArgumentException("Threshhold must be greater than 0");
		}
		this.cupsToRedeem = threshhold;
	}

	@Override
	public TransactionType getTransactionType() {
		return TransactionType.CUP;
	}

	@Override
	public Integer getRedemptionThreshold() {
		return cupsToRedeem;
	}
	
	public String toString(){
		return getTransactionType().toString();
	}

}
