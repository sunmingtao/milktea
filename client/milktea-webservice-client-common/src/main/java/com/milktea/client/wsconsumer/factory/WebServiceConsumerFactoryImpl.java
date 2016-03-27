package com.milktea.client.wsconsumer.factory;

import com.milktea.client.wsconsumer.AbstractWebServiceConsumer;
import com.milktea.client.wsconsumer.AuthenticationWebServiceConsumer;
import com.milktea.client.wsconsumer.HttpGetWebServiceConsumer;
import com.milktea.client.wsconsumer.NoAuthenticationWebServiceConsumer;
import com.milktea.client.wsconsumer.WebServiceConsumer;
import com.milktea.common.enums.WebServiceEnum;
import com.smt.common.spring.rest.RestWebServiceClientService;

public class WebServiceConsumerFactoryImpl implements WebServiceConsumerFactory {

	private String serverUrl;
	private RestWebServiceClientService service;

	public WebServiceConsumerFactoryImpl(String serverUrl, RestWebServiceClientService service) {
		super();
		this.serverUrl = serverUrl;
		this.service = service;
	}

	@SuppressWarnings("unchecked")
	public <T> WebServiceConsumer<T> getWebServiceConsumer(WebServiceEnum webServiceEnum) {
		if (webServiceEnum == null){
			throw new IllegalArgumentException("webServiceEnum cannot be null");
		}
		AbstractWebServiceConsumer<T> consumer = null;
		String webServicePath = webServiceEnum.getWebServicePath();
		if (isSendPushNotification(webServicePath)){
			consumer = (AbstractWebServiceConsumer<T>) getSendPushNotificationWebServiceConsumer(webServiceEnum);
		}else if (isAuthenticationRequired(webServicePath)){
			consumer = (AbstractWebServiceConsumer<T>) getAuthenticationWebServiceConsumer(webServiceEnum); 
		}else{
			consumer = (AbstractWebServiceConsumer<T>) getNoAuthenticationWebServiceConsumer(webServiceEnum); 
		}
		consumer.setWsClientService(service);
		return consumer;
	}

	@SuppressWarnings({"unchecked", "rawtypes" })
	private <T> AbstractWebServiceConsumer<T> getSendPushNotificationWebServiceConsumer(
			WebServiceEnum webServiceEnum) {
		return new HttpGetWebServiceConsumer(serverUrl, webServiceEnum);
	}

	@SuppressWarnings({"unchecked", "rawtypes" })
	private <T> WebServiceConsumer<T> getNoAuthenticationWebServiceConsumer(
			WebServiceEnum webServiceEnum) {
		return new NoAuthenticationWebServiceConsumer(serverUrl, webServiceEnum);
	}

	@SuppressWarnings({"unchecked", "rawtypes" })
	private <T> WebServiceConsumer<T> getAuthenticationWebServiceConsumer(
			WebServiceEnum webServiceEnum) {
		return new AuthenticationWebServiceConsumer(serverUrl, webServiceEnum);
	}

	private boolean isAuthenticationRequired(String webServicePath) {
		return webServicePath.startsWith("shop/") || webServicePath.startsWith("customer/");
	}
	
	private boolean isSendPushNotification(String webServicePath){
		return webServicePath.startsWith("spn");
	}

}
