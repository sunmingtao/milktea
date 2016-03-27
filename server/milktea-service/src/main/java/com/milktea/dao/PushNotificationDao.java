package com.milktea.dao;

import java.util.List;

import com.milktea.entity.PushNotification;
import com.milktea.enums.PushNotificationStatus;
import com.smt.common.spring.hibernate.dao.HibernateDao;

public interface PushNotificationDao extends HibernateDao<PushNotification>{
	List<PushNotification> findPushNotifications(String shopId);
	
	/**
	 * Find a maximum number of 'maxResults' of the push notifications at 'READY' status
	 * e.g. If maxResults = 10, then return 10 rows at most
	 * @param maxResults
	 * @return
	 */
	List<PushNotification> findReadyPushNotifications(int maxResults);
	
	/**
	 * Return the number of rows that were updated
	 * @param ids
	 * @return
	 */
	int updateStatus(final List<Long> ids, final PushNotificationStatus status);
	
	/**
	 * Return the number of rows that were updated
	 * @param ids
	 * @return
	 */
	int updateStatus(final long id, final PushNotificationStatus status);
	
}
