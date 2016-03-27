package com.milktea.service.api;


public interface PushNotificationService {
	
	/**
	 * Store the push notification context (message contents, 
	 * sender id, destination context) into database
	 * Return the number of push notifications that are ready to send
	 * @param shopId
	 * @param message
	 * @return
	 */
	int preparePushNotification(String shopId, String message);
	
	/**
	 * Send push notifications that are stored in database
	 * Return the number of message that were successfully sent
	 * @param max the maximum number of notifications to be sent
	 */
	int sendPushNotification(int max);
}
