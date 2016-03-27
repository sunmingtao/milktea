package com.smt.common.baidu.service.pushnotification.impl;

import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.smt.common.baidu.service.pushnotification.BaiduPushNotificationService;
import com.smt.common.baidu.service.pushnotification.context.BaiduPushNotificationDestination;


public class MockBaiduPushNotificationService implements BaiduPushNotificationService {
	@Override
	public int sendPushNotification(BaiduPushNotificationDestination destination, String message) 
			throws ChannelClientException, ChannelServerException {
		if (message.equals("clientException")){
			throw new ChannelClientException("client exception");
		}else if (message.equals("serverException")){
			throw new ChannelServerException(123L, 1, "server exception");
		}
		return 1; 
	}

}
