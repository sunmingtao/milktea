package com.milktea.client.service;

import com.smt.common.spring.AndroidRestWebServiceClientService;

public class MilkteaClientServiceFactory {
	public static MilkteaClientService instance(String url){
		return new MilkteaClientServiceImpl(url, new AndroidRestWebServiceClientService());
	}
}
