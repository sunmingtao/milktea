package com.milktea.service.redeem.factory;

import com.milktea.common.enums.TransactionType;
import com.milktea.service.redeem.handler.RedeemHandler;

public interface RedeemHandlerFactory {

	public RedeemHandler instance(TransactionType type);

}
