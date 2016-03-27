package com.smt.common.spring.service;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import com.smt.common.spring.rest.RestRequestContext;

public class SimpleRestWebServiceClientServiceTest {

	private SimpleRestWebServiceClientService service = new SimpleRestWebServiceClientService();
	
	private MockRestServiceServer mockServer;
	
	@Before
	public void setup(){
		mockServer = MockRestServiceServer.createServer(service.getRestTemplate());
	}
	
	@Test
	public void testPost() {
		RestRequestContext<String> requestContext 
			= new RestRequestContext<String>("http://fake.com", "Request", String.class);
		mockServer.expect(requestTo("http://fake.com")).andExpect(method(HttpMethod.POST))
			.andRespond(withSuccess("resultSuccess", MediaType.TEXT_PLAIN));
		String s = service.postAndReceive(requestContext);
		Assert.assertEquals("resultSuccess", s);
		mockServer.verify();
	}
	
	@Test
	public void testGet() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "request");
		RestRequestContext<String> requestContext 
			= new RestRequestContext<String>("http://fake.com", map, String.class);
		mockServer.expect(requestTo("http://fake.com")).andExpect(method(HttpMethod.GET))
			.andRespond(withSuccess("resultSuccess", MediaType.TEXT_PLAIN));
		String s = service.getAndReceive(requestContext);
		Assert.assertEquals("resultSuccess", s);
		mockServer.verify();
	}

}
