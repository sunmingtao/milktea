package com.milktea.common.dto.mobileauthcode;

import com.milktea.common.util.StringUtils;

public class MobileAuthRequest {
	
	private String mobile;
	
	public MobileAuthRequest(String mobile) {
		super();
		if (StringUtils.isEmpty(mobile)){
			throw new IllegalArgumentException("Mobile number must not be empty");
		}
		this.mobile = mobile;
	}
	
	/**
	 * Used for constructing the object by json library
	 */	
	@SuppressWarnings("unused")
	private MobileAuthRequest() {
		super();
	}

	public String getMobile() {
		return mobile;
	}

}
