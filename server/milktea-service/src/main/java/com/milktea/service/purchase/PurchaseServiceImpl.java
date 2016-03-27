package com.milktea.service.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milktea.common.dto.purchase.PurchaseRequest;
import com.milktea.service.api.PurchaseService;
import com.milktea.service.context.TransactionContext;
import com.milktea.service.purchase.factory.PurchaseHandlerFactory;
import com.milktea.service.purchase.handler.PurchaseHandler;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	private PurchaseHandlerFactory purchaseHandlerFactory;
	
	@Override
	public void purchase(PurchaseRequest request) {
		PurchaseHandler purchaseHandler = purchaseHandlerFactory.instance(request.getTransactionType());
		purchaseHandler.handle(createTransactionContext(request));
	}

	private TransactionContext createTransactionContext(PurchaseRequest request) {
		String shopId = request.getUserContext().getUsername();
		return new TransactionContext(shopId, request.getCustomerId(), request.getPurchaseValue());
	}

}
