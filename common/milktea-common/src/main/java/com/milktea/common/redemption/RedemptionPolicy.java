package com.milktea.common.redemption;

import com.milktea.common.enums.TransactionType;

public interface RedemptionPolicy<T> {
	
	public TransactionType getTransactionType();
	
	public T getRedemptionThreshold();
}
