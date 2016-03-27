package com.milktea.common.purchase;

import java.math.BigDecimal;

import com.milktea.common.enums.TransactionType;

public class AmountPurchaseDetail implements PurchaseDetail<BigDecimal>{
	
	private final BigDecimal value;
	
	protected AmountPurchaseDetail(BigDecimal value) {
		super();
		if (value == null || value.compareTo(BigDecimal.ZERO) <= 0){
			throw new IllegalArgumentException("Invalid transaction amount: "+value);
		}
		this.value = value;
	}

	public BigDecimal getValue() {
		return value;
	}

	public TransactionType getPurchaseType(){
		return TransactionType.AMOUNT;
	}
	
	public String toString(){
		return getPurchaseType().toString();
	}
	
}
