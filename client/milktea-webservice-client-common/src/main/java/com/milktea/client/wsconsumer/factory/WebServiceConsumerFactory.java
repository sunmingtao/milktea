package com.milktea.client.wsconsumer.factory;

import com.milktea.client.wsconsumer.WebServiceConsumer;
import com.milktea.common.enums.WebServiceEnum;

public interface WebServiceConsumerFactory {
	public <T> WebServiceConsumer<T> getWebServiceConsumer(WebServiceEnum wsEnum);
}