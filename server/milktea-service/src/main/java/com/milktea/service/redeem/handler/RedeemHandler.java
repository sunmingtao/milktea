package com.milktea.service.redeem.handler;

import com.milktea.exception.InsufficientCreditToRedeemException;
import com.milktea.service.context.TransactionContext;

public interface RedeemHandler {
	void handle(TransactionContext context) throws InsufficientCreditToRedeemException;
}
