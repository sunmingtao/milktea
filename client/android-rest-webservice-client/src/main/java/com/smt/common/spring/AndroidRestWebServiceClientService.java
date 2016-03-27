package com.smt.common.spring;

import java.util.Collections;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.milktea.common.dto.CommonRequest;
import com.smt.common.spring.rest.RestRequestContext;
import com.smt.common.spring.rest.RestWebServiceClientService;

public class AndroidRestWebServiceClientService implements RestWebServiceClientService {

	@Override
	public <T> T getAndReceive(RestRequestContext<T> requestContext) {
		RestTemplate restTemplate = createRestTemplate();
		ResponseEntity<T> responseEntity = restTemplate.exchange(requestContext.getUrl(), 
				HttpMethod.GET, new HttpEntity<Object>(requestContext.getRequestObject()), 
				requestContext.getResponseTypeClass());
		return responseEntity.getBody();
	}
	
	@Override
	public <T> T postAndReceive(RestRequestContext<T> requestContext) {
		RestTemplate restTemplate = createRestTemplate();
		ResponseEntity<T> responseEntity = restTemplate.exchange(requestContext.getUrl(), 
				HttpMethod.POST, new HttpEntity<Object>(requestContext.getRequestObject()), 
				requestContext.getResponseTypeClass());
		return responseEntity.getBody();
	}
	
	@Override
	public <T> T postAndReceiveWithBasicAuth(RestRequestContext<T> requestContext) {
		RestTemplate restTemplate = createRestTemplate();
		ResponseEntity<T> responseEntity = restTemplate.exchange(requestContext.getUrl(), 
				HttpMethod.POST, createHttpEntity(requestContext.getRequestObject()), 
				requestContext.getResponseTypeClass());
		return responseEntity.getBody();
	}
	
	protected HttpEntity<Object> createHttpEntity(Object request) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAuthorization(createAuthHeader(request));
		requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		return new HttpEntity<Object>(request, requestHeaders);
	}

	private HttpAuthentication createAuthHeader(Object request) {
		String username = ((CommonRequest)request).getUserContext().getUsername();
		String password = ((CommonRequest)request).getUserContext().getPassword();
		HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
		return authHeader;
	}
	
	private void addMessageConverters(RestTemplate restTemplate) {
		restTemplate.getMessageConverters().add(
				new MappingJacksonHttpMessageConverter());
	}

	protected RestTemplate createRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		addMessageConverters(restTemplate);
		return restTemplate;
	}

}
