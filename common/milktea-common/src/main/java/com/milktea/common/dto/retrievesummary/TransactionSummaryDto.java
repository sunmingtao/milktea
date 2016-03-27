package com.milktea.common.dto.retrievesummary;

import java.math.BigDecimal;

import com.milktea.common.dto.ShopDto;

public class TransactionSummaryDto {
	
	private String shopId;
	
	private String customerId;

	/** 
	 * Total number of cups (of milktea) purchased by the customer
	 */
	private Integer totalCups = 0;
	
	/** 
	 * The total amount spent by the customer 
	 */
	private BigDecimal totalAmount = BigDecimal.ZERO;
	
	/** 
	 * Total number of cups (of milktea) purchased by the customer
	 */
	private Integer totalCupsRedeemed = 0;
	
	/** 
	 * The total amount spent by the customer 
	 */
	private BigDecimal totalAmountRedeemed = BigDecimal.ZERO;
	
	private ShopDto shop;
	
	public TransactionSummaryDto() {
		super();
	}
	
	public TransactionSummaryDto(String shopId, String customerId) {
		super();
		this.shopId = shopId;
		this.customerId = customerId;
	}
	
	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Integer getTotalCups() {
		return totalCups;
	}

	public void setTotalCups(Integer totalCups) {
		this.totalCups = totalCups;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getTotalCupsRedeemed() {
		return totalCupsRedeemed;
	}

	public void setTotalCupsRedeemed(Integer totalCupsRedeemed) {
		this.totalCupsRedeemed = totalCupsRedeemed;
	}

	public BigDecimal getTotalAmountRedeemed() {
		return totalAmountRedeemed;
	}

	public void setTotalAmountRedeemed(BigDecimal totalAmountRedeemed) {
		this.totalAmountRedeemed = totalAmountRedeemed;
	}

	public ShopDto getShop() {
		return shop;
	}

	public void setShop(ShopDto shop) {
		this.shop = shop;
	}

}
