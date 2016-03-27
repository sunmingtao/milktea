package com.milktea.service.purchase.factory;

import com.milktea.common.enums.TransactionType;
import com.milktea.service.purchase.handler.PurchaseHandler;

public interface PurchaseHandlerFactory {

	public PurchaseHandler instance(TransactionType type);

}
