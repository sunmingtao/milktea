package com.milktea.common.dto.redeem;

import com.milktea.common.dto.CommonRequest;
import com.milktea.common.dto.UserContext;
import com.milktea.common.util.StringUtils;

public class RedeemRequest extends CommonRequest{
	
	private String customerId;
	
	public RedeemRequest(UserContext userContext, String customerId){
		super(userContext);
		if (StringUtils.isEmpty(customerId)){
			throw new IllegalArgumentException("CustomerId must not be empty");
		}
		this.customerId = customerId;
	}
	
	/**
	 * Used for constructing the object by json library
	 */	
	@SuppressWarnings("unused")
	private RedeemRequest(){
	}

	public String getCustomerId() {
		return customerId;
	}

	
}
