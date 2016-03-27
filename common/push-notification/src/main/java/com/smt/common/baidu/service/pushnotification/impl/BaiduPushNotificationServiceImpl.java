package com.smt.common.baidu.service.pushnotification.impl;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.smt.common.baidu.service.pushnotification.BaiduPushNotificationService;
import com.smt.common.baidu.service.pushnotification.context.BaiduPushNotificationDestination;

public class BaiduPushNotificationServiceImpl implements BaiduPushNotificationService {

	private String apiKey;
	
	private String secretKey;
	
	@Override
	public int sendPushNotification(BaiduPushNotificationDestination destination, String message) 
			throws ChannelClientException, ChannelServerException {
		PushUnicastMessageRequest request = createPushUnicastMessageRequest(destination, message);
		BaiduChannelClient channelClient = createBaiduChannelClient();
		PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);
		return response.getSuccessAmount(); 
	}

	private BaiduChannelClient createBaiduChannelClient() {
		ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);
		return new BaiduChannelClient(pair);
	}

	private PushUnicastMessageRequest createPushUnicastMessageRequest(
			BaiduPushNotificationDestination destination, String message) {
		PushUnicastMessageRequest request = new PushUnicastMessageRequest();
		request.setDeviceType(3);	// device_type => 1: web 2: pc 3:android 4:ios 5:wp		
		request.setChannelId(Long.parseLong(destination.getChannelId()));	
		request.setUserId(destination.getUserId());	 
		request.setMessageType(1);
		request.setMessage("{\"title\":\""+message+"\",\"description\":\""+message+"\"}");
		return request;
	}
	
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
}
