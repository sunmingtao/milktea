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
import com.milktea.common.dto.registercustomer.RegisterCustomerRequest;
import com.milktea.common.dto.registercustomer.RegisterCustomerResponse;
import com.milktea.exception.InvalidAuthCodeException;
import com.milktea.service.api.RegisterCustomerService;

@Controller
public class RegisterCustomerController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RegisterCustomerService service;
	
	
	@RequestMapping(value=Constants.REGISTER_USER_WITHOUT_AUTH_CODE_PATH, 
			method = RequestMethod.POST)
	@ResponseBody
	public RegisterCustomerResponse registerUserWithoutAuthCode(
			@RequestBody RegisterCustomerRequest request){
		logger.debug("Registering"+request.getUserContext().getUsername());
		RegisterCustomerResponse response = new RegisterCustomerResponse();
		service.registerCustomerWithoutAuthCode(request);
		response.setStatus(Constants.SUCCESS_RESPONSE);	
		return response;
	}
	
	@RequestMapping(value=Constants.REGISTER_USER_WITH_AUTH_CODE_PATH, 
			method = RequestMethod.POST)
	@ResponseBody
	public RegisterCustomerResponse registerUserWithAuthCode(
			@RequestBody RegisterCustomerRequest request){
		logger.debug("Registering"+request.getUserContext().getUsername());
		RegisterCustomerResponse response = new RegisterCustomerResponse();
		try {
			service.registerCustomerWithAuthCode(request);
			response.setStatus(Constants.SUCCESS_RESPONSE);	
		} catch (InvalidAuthCodeException e) {
			response.setStatus(Constants.INVALID_AUTH_CODE_RESPONSE);
			logger.debug("Authentication code: "+request.getAuthCode()+" is invalid");
		}
		return response;
	}
	
	
}
