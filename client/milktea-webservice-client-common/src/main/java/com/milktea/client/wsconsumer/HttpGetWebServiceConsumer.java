package com.milktea.client.wsconsumer;

import com.milktea.common.enums.WebServiceEnum;
import com.smt.common.spring.rest.RestRequestContext;

public class HttpGetWebServiceConsumer<T> extends AbstractWebServiceConsumer<T>{

	public HttpGetWebServiceConsumer(String serverUrl, WebServiceEnum webServiceEnum) {
		super(serverUrl, webServiceEnum);
	}
	
	protected T doGetResponse(Object request) {
		RestRequestContext<T> requestContext = createRequestContext(request);
		return wsClientService.getAndReceive(requestContext);
	}
	
}
