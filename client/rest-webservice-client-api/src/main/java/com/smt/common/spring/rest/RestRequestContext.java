package com.smt.common.spring.rest;

public class RestRequestContext<T> {
	private UserCredential userCredential;
	private final String url; 
	private final Object requestObject;
	private final Class<T> responseTypeClass;
	
	public String getUrl() {
		return url;
	}
	
	public RestRequestContext(String url, Object requestObject,
			Class<T> responseTypeClass) {
		super();
		this.url = url;
		this.requestObject = requestObject;
		this.responseTypeClass = responseTypeClass;
	
	}
	public Object getRequestObject() {
		return requestObject;
	}
	public Class<T> getResponseTypeClass() {
		return responseTypeClass;
	}
	public UserCredential getUserCredential() {
		return userCredential;
	}
	public void setUserCredential(UserCredential userCredential) {
		this.userCredential = userCredential;
	}
	
}
