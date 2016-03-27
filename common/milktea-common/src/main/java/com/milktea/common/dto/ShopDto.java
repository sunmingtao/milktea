package com.milktea.common.dto;

import java.math.BigDecimal;

public class ShopDto {
	
	private String shopId;
	
	private String shopName;

	/**
	 * The number of cups (of milktea) that can be used to redeem a gift 
	 * (e.g. 10 cups can redeem another cup of milktea)
	 */
	private Integer numberOfCupsToRedeem = 0;
	
	/**
	 * The amount that can be used to redeem a gift 
	 * (e.g. Spending $50 can redeem another cup of milktea)
	 */
	private BigDecimal amountToRedeem = BigDecimal.ZERO;

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Integer getNumberOfCupsToRedeem() {
		return numberOfCupsToRedeem;
	}

	public void setNumberOfCupsToRedeem(Integer numberOfCupsToRedeem) {
		this.numberOfCupsToRedeem = numberOfCupsToRedeem;
	}

	public BigDecimal getAmountToRedeem() {
		return amountToRedeem;
	}

	public void setAmountToRedeem(BigDecimal amountToRedeem) {
		this.amountToRedeem = amountToRedeem;
	}
	
}
