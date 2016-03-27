package com.smt.common.spring.service;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.smt.common.spring.rest.RestRequestContext;
import com.smt.common.spring.rest.RestWebServiceClientService;
import com.smt.common.spring.rest.UserCredential;

@Service
public class SimpleRestWebServiceClientService implements RestWebServiceClientService {

	private RestTemplate restTemplate;
	
	public SimpleRestWebServiceClientService() {
		restTemplate = createRestTemplate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAndReceive(RestRequestContext<T> requestContext) {
		return restTemplate.getForObject(requestContext.getUrl(), 
				requestContext.getResponseTypeClass(), (Map<String,?>)requestContext.getRequestObject());
	}
	
	@Override
	public <T> T postAndReceive(RestRequestContext<T> requestContext) {
		return sendAndReceive(requestContext, HttpMethod.POST);
	}

	private <T> T sendAndReceive(RestRequestContext<T> requestContext, HttpMethod httpMethod) {
		ResponseEntity<T> responseEntity = restTemplate.exchange(requestContext.getUrl(), 
				httpMethod, new HttpEntity<Object>(requestContext.getRequestObject()), 
				requestContext.getResponseTypeClass());
		return responseEntity.getBody();
	}
	
	@Override
	public <T> T postAndReceiveWithBasicAuth(RestRequestContext<T> requestContext) {
		setUserCredential(requestContext.getUserCredential());
		return postAndReceive(requestContext);
	}
	
	private void setUserCredential(UserCredential uc){
		restTemplate.setRequestFactory(
				UserCredentialHelper.createSecureTransport(uc.getUsername(), uc.getPassword()));
	}
	
	protected RestTemplate createRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
		return restTemplate;
	}
	
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
	
}
