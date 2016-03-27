package com.milktea.common.purchase;

import com.milktea.common.enums.TransactionType;

public class CupPurchaseDetail implements PurchaseDetail<Integer>{
	
	private final Integer value;
	
	protected CupPurchaseDetail(Integer value) {
		super();
		if (value == null || value <= 0){
			throw new IllegalArgumentException("Cup number cannot be empty");
		}
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public TransactionType getPurchaseType(){
		return TransactionType.CUP;
	}
	
	public String toString(){
		return getPurchaseType().toString();
	}
}
