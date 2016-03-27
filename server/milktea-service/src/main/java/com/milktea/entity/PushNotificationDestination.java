package com.milktea.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PushNotificationDestination {
	
	@Column(name="USER_ID")
	private String userId;

	@Column(name="CHANNEL_ID")
	private String channelId;

	public PushNotificationDestination(){
		
	}

	public PushNotificationDestination(String channelId, String userId) {
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
