package com.smt.common.baidu.service.pushnotification.context;

public class BaiduPushNotificationDestination {
	
	private final String channelId;
	
	private final String userId;

	public BaiduPushNotificationDestination(String channelId, String userId) {
		super();
		this.channelId = channelId;
		this.userId = userId;
	}

	public String getChannelId() {
		return channelId;
	}

	public String getUserId() {
		return userId;
	}

}
