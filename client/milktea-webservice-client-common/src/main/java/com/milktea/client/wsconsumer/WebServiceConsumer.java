package com.milktea.client.wsconsumer;


public interface WebServiceConsumer<T> {
	public T getResponse(Object request);
}
