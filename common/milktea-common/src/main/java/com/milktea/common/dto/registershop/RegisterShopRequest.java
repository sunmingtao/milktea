package com.milktea.common.dto.registershop;

import com.milktea.common.dto.CommonRequest;
import com.milktea.common.dto.UserContext;
import com.milktea.common.enums.TransactionType;
import com.milktea.common.redemption.RedemptionPolicy;

public class RegisterShopRequest extends CommonRequest{

	/** Authentication code */
	private String authCode;
	
	/** Redemption type (CUP, AMOUNT */
	private TransactionType redeemType;
	
	private String shopName;
	
	/** 
	 * The threshold to redeem a gift 
	 * e.g. 10 cups can redeem another cup of milktea
	 *      Spending $50 can redeem another cup of milktea
	 */
	private Object threshold;
	
	public RegisterShopRequest(UserContext userContext, RedemptionPolicy<?> redemptionPolicy){
		super(userContext);
		if (redemptionPolicy == null){
			throw new IllegalArgumentException("Redemption Policy cannot be null");
		}
		redeemType = redemptionPolicy.getTransactionType();
		threshold = redemptionPolicy.getRedemptionThreshold();
	}
	
	/**
	 * Used for constructing the object by json library
	 */	
	@SuppressWarnings("unused")
	private RegisterShopRequest(){
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	public boolean hasCupRedemptionPolicy(){
		return redeemType == TransactionType.CUP;
	}
	
	public boolean hasAmountRedemptionPolicy(){
		return redeemType == TransactionType.AMOUNT;
	}
	
	public TransactionType getRedeemType() {
		return redeemType;
	}

	public Object getThreshold() {
		return threshold;
	}
	
}
