package com.smt.common.spring.rest;

public interface RestWebServiceClientService {
	
	public <T> T getAndReceive(RestRequestContext<T> requestContext);
	
	public <T> T postAndReceive(RestRequestContext<T> requestContext);
	
	public <T> T postAndReceiveWithBasicAuth(RestRequestContext<T> requestContext);
}
