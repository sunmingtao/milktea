package com.milktea.service.api;

import com.milktea.common.dto.registershop.RegisterShopRequest;
import com.milktea.exception.InvalidAuthCodeException;
import com.milktea.exception.ShopExistsException;

public interface RegisterShopService {
	void registerShopWithAuthCode(RegisterShopRequest request) throws ShopExistsException, InvalidAuthCodeException;
	
	void registerShopWithoutAuthCode(RegisterShopRequest request) throws ShopExistsException;
}
