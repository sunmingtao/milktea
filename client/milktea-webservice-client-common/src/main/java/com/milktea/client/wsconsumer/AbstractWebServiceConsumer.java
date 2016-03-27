package com.milktea.client.wsconsumer;

import com.milktea.common.constants.Constants;
import com.milktea.common.dto.CommonResponse;
import com.milktea.common.enums.WebServiceEnum;
import com.smt.common.spring.rest.RestRequestContext;
import com.smt.common.spring.rest.RestWebServiceClientService;

public abstract class AbstractWebServiceConsumer<T> implements WebServiceConsumer<T>{

	protected String serverUrl;
	protected WebServiceEnum webServiceEnum;
	protected RestWebServiceClientService wsClientService;
	
	public AbstractWebServiceConsumer(String serverUrl, WebServiceEnum webServiceEnum) {
		super();
		this.serverUrl = serverUrl;
		this.webServiceEnum = webServiceEnum;
	}

	public T getResponse(Object request) {
		if (request == null){
			throw new IllegalArgumentException("Request cannot be null");
		}
		T t  = null;
		try {
			t = doGetResponse(request);
		} catch (Exception e) {
			t = handleException(e);
		} 
		return t;
	}

	protected abstract T doGetResponse(Object request);

	private T handleException(Exception e) {
		T t = null;
		if (e.getMessage().contains("401")){
			t = createInvalidUsernamePasswordResponse();
		}else if (e.getMessage().contains("I/O error")){
			t = createNoConnectionResponse();
		}else{
			throw new RuntimeException(e);
		}
		return t;
	}

	@SuppressWarnings("unchecked")
	private T createNoConnectionResponse() {
		CommonResponse response = createInstance();
		response.setStatus(Constants.NO_CONNECTION_RESPONSE);
		return (T)response;
	}

	@SuppressWarnings("unchecked")
	protected T createInvalidUsernamePasswordResponse(){
		CommonResponse response = createInstance();
		response.setStatus(Constants.INVALIE_USERNAME_OR_PASSWORD_RESPONSE);
		return (T)response;
	}

	private CommonResponse createInstance(){
		CommonResponse response = null;
		try {
			Class<?> clazz = webServiceEnum.getResponseClassType();
			response = (CommonResponse)clazz.newInstance();
		} catch (Exception e) {
			//Don't know what to do here
			throw new RuntimeException(e);
		}
		return response;
	}


	protected RestRequestContext<T> createRequestContext(Object request){
		@SuppressWarnings("unchecked")
		RestRequestContext<T> context = new RestRequestContext<T>
			(constructFullUrl(), request, (Class<T>)webServiceEnum.getResponseClassType());
		return context;
	}
	
	protected String constructFullUrl() {
		return this.serverUrl.concat("/").concat(webServiceEnum.toString());
	}
	
	public void setWsClientService(RestWebServiceClientService wsClientService) {
		this.wsClientService = wsClientService;
	}

}
