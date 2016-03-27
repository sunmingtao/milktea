package com.smt.common.baidu.service.pushnotification;

import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.smt.common.baidu.service.pushnotification.context.BaiduPushNotificationDestination;

public interface BaiduPushNotificationService {
	
	/**
	 * Sends push notification and returns the message number sent
	 * @param destination
	 * @param message
	 * @return
	 * @throws ChannelClientException
	 * @throws ChannelServerException
	 */
	int sendPushNotification(BaiduPushNotificationDestination destination, String message) 
			throws ChannelClientException, ChannelServerException;
	
}
