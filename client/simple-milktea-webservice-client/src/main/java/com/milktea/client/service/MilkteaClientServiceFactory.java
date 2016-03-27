package com.milktea.client.service;

import com.smt.common.spring.service.SimpleRestWebServiceClientService;

public class MilkteaClientServiceFactory {
	public static MilkteaClientService instance(String url){
		return new MilkteaClientServiceImpl(url, new SimpleRestWebServiceClientService());
	}
}
