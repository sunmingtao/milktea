package com.milktea.common.dto.purchase;

import com.milktea.common.dto.CommonRequest;
import com.milktea.common.dto.UserContext;
import com.milktea.common.enums.TransactionType;
import com.milktea.common.purchase.PurchaseDetail;
import com.milktea.common.util.StringUtils;

public class PurchaseRequest extends CommonRequest{
	
	private String customerId;
	private TransactionType transactionType;
	private Object purchaseValue;
	
	public PurchaseRequest(UserContext userContext, String customerId, 
			PurchaseDetail<?> transactionDetail){
		super(userContext);
		if (StringUtils.isEmpty(customerId)){
			throw new IllegalArgumentException("CustomerId must not be empty");
		}
		this.customerId = customerId;
		this.purchaseValue = transactionDetail.getValue();
		this.transactionType = transactionDetail.getPurchaseType();
	}
	
	/**
	 * Used for constructing the object by json library
	 */	
	@SuppressWarnings("unused")
	private PurchaseRequest(){
	}
	
	public TransactionType getTransactionType() {
		return transactionType;
	}

	public Object getPurchaseValue() {
		return purchaseValue;
	}

	public String getCustomerId() {
		return customerId;
	}
	
}
