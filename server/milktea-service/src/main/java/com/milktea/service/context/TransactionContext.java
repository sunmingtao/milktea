package com.milktea.service.context;

public class TransactionContext {
	private final String shopId;
	private final String customerId;
	private final Object transactionValue;
	
	public TransactionContext(String shopId, String customerId, Object transactionValue) {
		super();
		this.shopId = shopId;
		this.customerId = customerId;
		this.transactionValue = transactionValue;
	}

	public String getShopId() {
		return shopId;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	
	public Object getTransactionValue() {
		return transactionValue;
	}
	
}
