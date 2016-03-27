package com.milktea.common.dto.registercustomer;


import com.milktea.common.dto.CommonRequest;
import com.milktea.common.dto.UserContext;

public class RegisterCustomerRequest extends CommonRequest{
	
	public RegisterCustomerRequest(UserContext userContext) {
		super(userContext);
	}
	
	/**
	 * Used for constructing the object by json library
	 */	
	@SuppressWarnings("unused")
	private RegisterCustomerRequest() {
	}
	
	/** Authentication code */
	private String authCode;
	
	/**
	 * Identifier for push notification
	 * This field is used by milktea customer registering
	 */
	private String userId;
	/**
	 * Identifier for push notification
	 * This field is used by milktea customer registering
	 */
	private String channelId;
	
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	
}
