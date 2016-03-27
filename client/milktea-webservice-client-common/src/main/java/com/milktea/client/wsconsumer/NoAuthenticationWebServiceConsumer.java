package com.milktea.client.wsconsumer;

import com.milktea.common.enums.WebServiceEnum;
import com.smt.common.spring.rest.RestRequestContext;

public class NoAuthenticationWebServiceConsumer<T> extends AbstractWebServiceConsumer<T>{

	public NoAuthenticationWebServiceConsumer(String serverUrl, WebServiceEnum webServiceEnum) {
		super(serverUrl, webServiceEnum);
	}
	
	protected T doGetResponse(Object request) {
		RestRequestContext<T> requestContext = createRequestContext(request);
		return wsClientService.postAndReceive(requestContext);
	}
	
}
