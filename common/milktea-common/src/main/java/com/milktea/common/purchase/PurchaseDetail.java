package com.milktea.common.purchase;

import com.milktea.common.enums.TransactionType;

public interface PurchaseDetail<T> {
	public T getValue();

	public TransactionType getPurchaseType();
}
