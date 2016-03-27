package com.smt.common.spring.service;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public class UserCredentialHelper {
	public static ClientHttpRequestFactory createSecureTransport(
			String username, String password) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		httpClient.setCredentialsProvider(createBasicCredentialsProvider(username, password));
		return new HttpComponentsClientHttpRequestFactory(httpClient);
	}
	
	private static BasicCredentialsProvider createBasicCredentialsProvider(String username, String password){
		BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY,
				new UsernamePasswordCredentials(username, password));
		return credentialsProvider;
	}
}
