package com.milktea.service.purchase.handler;

import com.milktea.service.context.TransactionContext;

public interface PurchaseHandler{
	void handle(TransactionContext context);
}
