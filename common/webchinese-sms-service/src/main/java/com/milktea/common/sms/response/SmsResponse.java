package com.milktea.common.sms.response;

public enum SmsResponse {
	
	SUCCESS("1"),
	INVALID_KEY("-2"),
	INSUFFICIENT_MESSAGES("-3"),
	BANNED_USER("-11"),
	ILLEGAL_CHARACTER("-14"),
	INVALID_MOBILE("-4"),
	EMPTY_MOBILE("-41"),
	EMPTY_MESSAGE("-42");
	
	
	private String responseCode;
	
	private SmsResponse(String responseCode){
		this.responseCode = responseCode;
	}
	
	
	public static SmsResponse forCode(String responseCode){
		int responseCodeInt = Integer.parseInt(responseCode);
		if (responseCodeInt >=1){
			return SmsResponse.SUCCESS;
		}else{
			for (SmsResponse response: SmsResponse.values()){
				if (response.responseCode.equals(responseCode)){
					return response;
				}
			}	
		}
		
		throw new IllegalArgumentException("Unknown response code: "+responseCode);
	}
}
