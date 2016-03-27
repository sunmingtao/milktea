package com.milktea.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.milktea.dao.PushNotificationDao;
import com.milktea.entity.PushNotification;
import com.milktea.enums.PushNotificationStatus;
import com.smt.common.spring.hibernate.dao.impl.AbstractHibernateDaoImpl;

public class PushNotificationDaoImpl extends
		AbstractHibernateDaoImpl<PushNotification> implements
		PushNotificationDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<PushNotification> findPushNotifications(String shopId) {
		return getHibernateTemplate().find(
				"from PushNotification p where p.shopId = ?", shopId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PushNotification> findReadyPushNotifications(
			final int maxResults) {
		final String hql = "from PushNotification p where p.status = :status";
		List<PushNotification> list = getHibernateTemplate().execute(
				new HibernateCallback<List<PushNotification>>() {
					@Override
					public List<PushNotification> doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						query.setParameter("status",
								PushNotificationStatus.READY);
						query.setMaxResults(maxResults);
						return query.list();
					}
				});
		return list;
	}

	@Override
	public int updateStatus(final List<Long> ids,
			final PushNotificationStatus status) {
		final String hql = "update PushNotification p set p.status = :status where p.id in (:ids)";
		int rowsUpdated = getHibernateTemplate().execute(
				new HibernateCallback<Integer>() {
					public Integer doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						query.setParameter("status", status);
						query.setParameterList("ids", ids);
						return query.executeUpdate();
					}
				});
		return rowsUpdated;
	}

	@Override
	public int updateStatus(long id, PushNotificationStatus status) {
		List<Long> ids = new ArrayList<Long>(1);
		ids.add(id);
		return updateStatus(ids, status);
	}

}
