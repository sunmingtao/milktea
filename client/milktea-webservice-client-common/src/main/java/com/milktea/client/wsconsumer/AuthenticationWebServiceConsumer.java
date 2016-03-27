package com.milktea.client.wsconsumer;

import com.milktea.common.dto.CommonRequest;
import com.milktea.common.dto.UserContext;
import com.milktea.common.enums.WebServiceEnum;
import com.smt.common.spring.rest.RestRequestContext;
import com.smt.common.spring.rest.UserCredential;

public class AuthenticationWebServiceConsumer<T> extends AbstractWebServiceConsumer<T>{

	public AuthenticationWebServiceConsumer(String serverUrl, WebServiceEnum webServiceEnum) {
		super(serverUrl, webServiceEnum);
	}
	
	protected T doGetResponse(Object request) {
		RestRequestContext<T> requestContext = createRequestContext(request);
		UserContext uc = ((CommonRequest)request).getUserContext();
		requestContext.setUserCredential(new UserCredential(uc.getUsername(), uc.getPassword()));
		return wsClientService.postAndReceiveWithBasicAuth(requestContext);
	}
	
}
