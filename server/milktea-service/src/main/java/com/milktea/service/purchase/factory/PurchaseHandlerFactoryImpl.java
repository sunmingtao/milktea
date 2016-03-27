package com.milktea.service.purchase.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.milktea.common.enums.TransactionType;
import com.milktea.service.purchase.handler.PurchaseHandler;

@Component
public class PurchaseHandlerFactoryImpl implements PurchaseHandlerFactory {

	@Autowired
	private PurchaseHandler cupPurchaseHandler;
	
	@Autowired
	private PurchaseHandler amountPurchaseHandler;
	
	@Override
	public PurchaseHandler instance(TransactionType type) {
		if (type == TransactionType.CUP){
			return cupPurchaseHandler;
		}
		if (type == TransactionType.AMOUNT){
			return amountPurchaseHandler;
		}
		throw new IllegalArgumentException("Unknown Transaction Type: "+type);
	}

}
