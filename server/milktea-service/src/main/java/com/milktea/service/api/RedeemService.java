package com.milktea.service.api;

import com.milktea.exception.InsufficientCreditToRedeemException;

public interface RedeemService {
	void redeem(String shopId, String customerId) throws InsufficientCreditToRedeemException;
}
