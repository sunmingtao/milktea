package com.milktea.service.registershop.helper;

import java.math.BigDecimal;

import com.milktea.common.enums.TransactionType;
import com.milktea.entity.Shop;

public class RedemptionThresholdHelper {
	public static void updateRedemptionThreshold(Shop shop, Object threshold,
			TransactionType redeemType) {
		if (redeemType == TransactionType.AMOUNT){
			shop.setAmountToRedeem((BigDecimal)threshold);
		}else if (redeemType == TransactionType.CUP){
			shop.setNumberOfCupsToRedeem((Integer)threshold);
		}else{
			throw new IllegalArgumentException("Unknown redemption type: "+redeemType);
		}
	}
}
