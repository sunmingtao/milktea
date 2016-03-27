package com.milktea.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="T_TRANSACTION_SUMMARY")
public class TransactionSummary {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="SHOP_ID", referencedColumnName="SHOP_ID")
	private Shop shop;
	
	@ManyToOne
	@JoinColumn(name="CUSTOMER_ID", referencedColumnName="CUSTOMER_ID")
	private Customer customer;
	
	/** 
	 * Total number of cups (of milktea) purchased by the customer
	 */
	@Column(name="TOTAL_CUPS")
	private Integer totalCups = 0;
	
	/** 
	 * The total amount spent by the customer 
	 */
	@Column(name="TOTAL_AMOUNT")
	private BigDecimal totalAmount = BigDecimal.ZERO;
	
	/** 
	 * Total number of cups (of milktea) purchased by the customer
	 */
	@Column(name="TOTAL_CUPS_REDEEMED")
	private Integer totalCupsRedeemed = 0;
	
	/** 
	 * The total amount spent by the customer 
	 */
	@Column(name="TOTAL_AMOUNT_REDEEMED")
	private BigDecimal totalAmountRedeemed = BigDecimal.ZERO;
	

	protected TransactionSummary() {
		super();
	}
	
	public TransactionSummary(Shop shop, Customer customer) {
		super();
		this.shop = shop;
		this.customer = customer;
	}
	
	public void addTotalCups(Integer cupsToAdd){
		if (cupsToAdd == null || cupsToAdd <= 0){
			throw new IllegalArgumentException("Invalid cups to add: "+cupsToAdd);
		}
		totalCups+=cupsToAdd;
	}
	
	public void addTotalAmount(BigDecimal amountToAdd){
		if (amountToAdd == null || amountToAdd.compareTo(BigDecimal.ZERO) <=0 ){
			throw new IllegalArgumentException("Invalid amount to add: "+amountToAdd);
		}
		totalAmount=totalAmount.add(amountToAdd);
	}
	
	public void addTotalCupsRedeemed(Integer cupsRedeemedToAdd){
		if (cupsRedeemedToAdd == null || cupsRedeemedToAdd <= 0){
			throw new IllegalArgumentException("Invalid cups redeemed to add: "+cupsRedeemedToAdd);
		}
		totalCupsRedeemed+=cupsRedeemedToAdd;
	}
	
	public void addTotalAmountRedeemed(BigDecimal amountRedeemedToAdd){
		if (amountRedeemedToAdd == null || amountRedeemedToAdd.compareTo(BigDecimal.ZERO) <=0 ){
			throw new IllegalArgumentException("Invalid amount redeemed to add: "+amountRedeemedToAdd);
		}
		totalAmountRedeemed=totalAmountRedeemed.add(amountRedeemedToAdd);
	}
	
	public Shop getShop() {
		return shop;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public Integer getTotalCups() {
		return totalCups;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public Integer getTotalCupsRedeemed() {
		return totalCupsRedeemed;
	}

	public BigDecimal getTotalAmountRedeemed() {
		return totalAmountRedeemed;
	}

}
