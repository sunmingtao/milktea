package com.milktea.common.dto;

public class CommonRequest {
	private UserContext userContext;
	
	public CommonRequest(UserContext userContext) {
		super();
		if (userContext == null){
			throw new IllegalArgumentException("UserContext must not be null");
		}
		this.userContext = userContext;
	}
	
	public CommonRequest() {
		super();
	}
	
	public UserContext getUserContext() {
		return userContext;
	}
	
}
