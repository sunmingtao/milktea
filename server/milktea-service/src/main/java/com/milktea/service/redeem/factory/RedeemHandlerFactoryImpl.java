package com.milktea.service.redeem.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.milktea.common.enums.TransactionType;
import com.milktea.service.redeem.handler.RedeemHandler;

@Component
public class RedeemHandlerFactoryImpl implements RedeemHandlerFactory {

	@Autowired
	private RedeemHandler cupRedeemHandler;
	
	@Autowired
	private RedeemHandler amountRedeemHandler;
	
	@Override
	public RedeemHandler instance(TransactionType type) {
		if (type == TransactionType.CUP){
			return cupRedeemHandler;
		}
		if (type == TransactionType.AMOUNT){
			return amountRedeemHandler;
		}
		throw new IllegalArgumentException("Unknown Transaction Type: "+type);
	}

}
