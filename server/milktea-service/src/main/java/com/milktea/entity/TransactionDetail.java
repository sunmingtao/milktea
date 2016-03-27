package com.milktea.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name="T_TRANSACTION_DETAIL")
public class TransactionDetail {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="SHOP_ID")
	private String shopId;
	
	@Column(name="CUSTOMER_ID")
	private String customerId;

	/** 
	 * Number of cups (of milktea) purchased by the customer
	 * Negative number is allowed, meaning redemption  
	 */
	@Column(name="NUM_OF_CUPS")
	private Integer numberOfCups;
	
	/** 
	 * The amount spent by the customer 
	 * Negative number is allowed, meaning redemption
	 */
	@Column(name="AMOUNT")
	private BigDecimal amount;
	
	@Column(name="TRANSATION_TIME", insertable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@Generated(GenerationTime.INSERT)
	private Date transactionTime;

	@SuppressWarnings("unused")
	private TransactionDetail() {
		super();
	}
	
	public TransactionDetail(String shopId, String customerId) {
		super();
		this.shopId = shopId;
		this.customerId = customerId;
	}

	public String getShopId() {
		return shopId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public Integer getNumberOfCups() {
		return numberOfCups;
	}

	public void setNumberOfCups(Integer numberOfCups) {
		this.numberOfCups = numberOfCups;
	}
	
	/**
	 * If number of cups is negative number, it indicates a redeem transaction
	 * @param numberOfCups
	 */
	public void setNumberOfCupsRedeemed(Integer numberOfCups){
		if (numberOfCups == null || numberOfCups <=0 ){
			throw new IllegalArgumentException("Number of cups redeemed is invalid: "+numberOfCups);
		}
		this.numberOfCups = 0 - numberOfCups;
	}
	
	/**
	 * If amount is negative number, it indicates a redeem transaction
	 * @param amount
	 */
	public void setAmountRedeemed(BigDecimal amount) {
		if(amount == null || amount.compareTo(BigDecimal.ZERO)<=0){
			throw new IllegalArgumentException("Number of amount redeemed is invalid: "+amount);
		}
		this.amount = BigDecimal.ZERO.subtract(amount);
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getTransactionTime() {
		return transactionTime;
	}

}
