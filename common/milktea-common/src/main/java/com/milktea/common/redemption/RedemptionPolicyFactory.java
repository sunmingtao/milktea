package com.milktea.common.redemption;

import java.math.BigDecimal;

public class RedemptionPolicyFactory {
	public static RedemptionPolicy<?> createCupRedemptionPolicy(Integer threshold){
		return new CupRedemptionPolicy(threshold);
	}
	
	public static RedemptionPolicy<?> createAmountRedemptionPolicy(BigDecimal threshold){
		return new AmountRedemptionPolicy(threshold);
	}
}
