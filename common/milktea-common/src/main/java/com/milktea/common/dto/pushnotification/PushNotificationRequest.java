package com.milktea.common.dto.pushnotification;

import com.milktea.common.dto.CommonRequest;
import com.milktea.common.dto.UserContext;
import com.milktea.common.util.StringUtils;

public class PushNotificationRequest extends CommonRequest{
	
	private String message;
	
	public PushNotificationRequest(UserContext uc, String message) {
		super(uc);
		if (StringUtils.isEmpty(message)){
			throw new IllegalArgumentException("Message contents must not be empty");
		}
		this.message = message;
	}
	
	/**
	 * Used for constructing the object by json library
	 */	
	@SuppressWarnings("unused")
	private PushNotificationRequest() {
		super();
	}

	public String getMessage() {
		return message;
	}

}
