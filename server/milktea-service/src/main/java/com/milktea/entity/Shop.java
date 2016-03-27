package com.milktea.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.milktea.common.redemption.RedemptionPolicy;
import com.milktea.common.redemption.RedemptionPolicyFactory;

@Entity
@Table(name="T_SHOP")
public class Shop implements Serializable{

	private static final long serialVersionUID = -4953655578842529980L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="SHOP_ID")
	private String shopId;
	
	@Column(name="SHOP_NAME")
	private String shopName;

	@Column(name="PASSWORD")
	private String password;
	
	/**
	 * The number of cups (of milktea) that can be used to redeem a gift 
	 * (e.g. 10 cups can redeem another cup of milktea)
	 */
	@Column(name="NUM_CUP_REDEEM")
	private Integer numberOfCupsToRedeem = 0;
	
	/**
	 * The amount that can be used to redeem a gift 
	 * (e.g. Spending $50 can redeem another cup of milktea)
	 */
	@Column(name="AMOUNT_REDEEM", precision=8, scale=2)
	private BigDecimal amountToRedeem = BigDecimal.ZERO;
	
	@SuppressWarnings("unused")
	private Shop(){
	}

	public Shop(String shopId, String password) {
		super();
		this.shopId = shopId;
		this.password = password;
	}
	
	public Shop(String shopId) {
		super();
		this.shopId = shopId;
	}

	public RedemptionPolicy<?> getRedemptionPolicy(){
		if (isCupRedemptionPolicy()){
			return RedemptionPolicyFactory.createCupRedemptionPolicy(numberOfCupsToRedeem);
		}
		if (isAmountRedemptionPolicy()){
			return RedemptionPolicyFactory.createAmountRedemptionPolicy(amountToRedeem);
		}
		throw new IllegalStateException("Shop id: "+shopId+"'s redemption policy is not valid");
	}
	
	private boolean isAmountRedemptionPolicy() {
		return hasAmountToRedeemValue() && hasNoCupToRedeemValue();
	}
	
	private boolean isCupRedemptionPolicy() {
		return hasCupToRedeemValue() && hasNoAmountToRedeemValue();
	}

	private boolean hasNoCupToRedeemValue() {
		return numberOfCupsToRedeem == null || numberOfCupsToRedeem == 0;
	}
	
	private boolean hasCupToRedeemValue() {
		return !hasNoCupToRedeemValue();
	}

	private boolean hasNoAmountToRedeemValue() {
		return amountToRedeem == null || amountToRedeem.compareTo(BigDecimal.ZERO) == 0;
	}
	
	private boolean hasAmountToRedeemValue() {
		return !hasNoAmountToRedeemValue();
	}

	public String getShopId() {
		return shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getPassword() {
		return password;
	}

	public Integer getNumberOfCupsToRedeem() {
		return numberOfCupsToRedeem;
	}

	public void setNumberOfCupsToRedeem(Integer numberOfCupsToRedeem) {
		if (numberOfCupsToRedeem == null || numberOfCupsToRedeem <=0){
			throw new IllegalArgumentException("numberOfCupsToRedeem is invalid: "+numberOfCupsToRedeem);
		}
		this.numberOfCupsToRedeem = numberOfCupsToRedeem;
	}

	public BigDecimal getAmountToRedeem() {
		return amountToRedeem;
	}

	public void setAmountToRedeem(BigDecimal amountToRedeem) {
		if (amountToRedeem == null || amountToRedeem.compareTo(BigDecimal.ZERO) <= 0){
			throw new IllegalArgumentException("amountToRedeem is invalid: "+amountToRedeem);
		}
		this.amountToRedeem = amountToRedeem;
	}

	public void copyProperties(Shop shop) {
		shopName = shop.getShopName();
		password = shop.getPassword();
		numberOfCupsToRedeem = shop.getNumberOfCupsToRedeem();
		amountToRedeem = shop.getAmountToRedeem();
	}
	
}
