package com.milktea.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.milktea.common.constants.Constants;
import com.milktea.common.dto.registershop.RegisterShopRequest;
import com.milktea.common.dto.registershop.RegisterShopResponse;
import com.milktea.exception.ShopExistsException;
import com.milktea.service.api.RegisterShopService;

@Controller
public class RegisterShopController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RegisterShopService service;
	
	@RequestMapping(value=Constants.REGISTER_SHOP_PATH, 
			method = RequestMethod.POST)
	@ResponseBody
	public RegisterShopResponse registerShop(
			@RequestBody RegisterShopRequest request){
		logger.debug("Registering "+request.getUserContext().getUsername());
		RegisterShopResponse response = new RegisterShopResponse();
		try {
			service.registerShopWithoutAuthCode(request);
			response.setStatus(Constants.SUCCESS_RESPONSE);	
		} catch (ShopExistsException e) {
			response.setStatus(Constants.SHOP_EXISTS_RESPONSE);
			logger.debug("Shop "+request.getUserContext().getUsername()+" exists");
		}
		return response;
	}
	
}
