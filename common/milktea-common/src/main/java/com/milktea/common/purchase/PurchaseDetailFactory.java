package com.milktea.common.purchase;

import java.math.BigDecimal;

public class PurchaseDetailFactory {
	public static PurchaseDetail<?> createCupPurchaseDetail(Integer value){
		return new CupPurchaseDetail(value);
	}
	
	public static PurchaseDetail<?> createAmountPurchaseDetail(BigDecimal value){
		return new AmountPurchaseDetail(value);
	}
}
