package com.milktea.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milktea.common.dto.UserContext;
import com.milktea.common.dto.registercustomer.RegisterCustomerRequest;
import com.milktea.dao.CustomerDao;
import com.milktea.dao.MobileAuthDao;
import com.milktea.entity.Customer;
import com.milktea.entity.MobileAuth;
import com.milktea.entity.PushNotificationDestination;
import com.milktea.exception.InvalidAuthCodeException;
import com.milktea.service.api.RegisterCustomerService;
import com.milktea.util.MobileAuthUtils;

@Service
public class RegisterCustomerServiceImpl implements RegisterCustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private MobileAuthDao mobileAuthDao;
	
	@Override
	public void registerCustomerWithAuthCode(RegisterCustomerRequest request)
			throws InvalidAuthCodeException {
		UserContext userContext = request.getUserContext();
		String customerId = userContext.getUsername();
		List<MobileAuth> list = mobileAuthDao.findCustomerAuthCodes(customerId);
		if (MobileAuthUtils.hasFoundCorrectAuthCode(list, request.getAuthCode())) {
			registerCustomerWithoutAuthCode(request);
		}else{
			throw new InvalidAuthCodeException("Authentication code: " + request.getAuthCode() 
					+ " is incorrect");	
		}
	}

	@Override
	public void registerCustomerWithoutAuthCode(RegisterCustomerRequest request) {
		String customerId = request.getUserContext().getUsername();
		Customer customer =  createCustomer(request);
		if (customerDao.exists(customerId)) {
			updateCustomer(customer);
		}else{
			saveCustomer(customer);
		}
	}
	
	private void saveCustomer(Customer customer){
		customerDao.save(customer);
	}
	
	private void updateCustomer(Customer customer){
		Customer dbCustomer = customerDao.findCustomer(customer.getCustomerId());
		dbCustomer.copyProperties(customer);
		customerDao.update(dbCustomer);
	}
	
	private Customer createCustomer(RegisterCustomerRequest request) {
		String customerId = request.getUserContext().getUsername();
		String password = request.getUserContext().getPassword();
		Customer user = new Customer(customerId, password);
		user.setDestination(new PushNotificationDestination(
				request.getChannelId(), request.getUserId()));
		return user;
	}

}
