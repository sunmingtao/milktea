package com.milktea.service.api;

import com.milktea.common.dto.registercustomer.RegisterCustomerRequest;
import com.milktea.exception.InvalidAuthCodeException;

public interface RegisterCustomerService {
	void registerCustomerWithoutAuthCode(RegisterCustomerRequest request);
	void registerCustomerWithAuthCode(RegisterCustomerRequest request) throws InvalidAuthCodeException;
}
