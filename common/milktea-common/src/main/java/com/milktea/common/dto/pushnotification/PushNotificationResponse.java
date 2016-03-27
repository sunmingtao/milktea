package com.milktea.common.dto.pushnotification;

import com.milktea.common.dto.CommonResponse;

public class PushNotificationResponse extends CommonResponse{
	/** 
	 * The number of messages that were successfully sent
	 */
	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
