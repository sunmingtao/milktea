package com.milktea.dao.impl;

import java.util.List;

import com.milktea.dao.TransactionDetailDao;
import com.milktea.entity.TransactionDetail;
import com.smt.common.spring.hibernate.dao.impl.AbstractHibernateDaoImpl;

public class TransactionDetailDaoImpl extends AbstractHibernateDaoImpl<TransactionDetail> implements TransactionDetailDao {
	@SuppressWarnings("unchecked")
	public List<TransactionDetail> findTransactionDetail(String shopId, String customerId) {
		List<TransactionDetail> list 
			= getHibernateTemplate().find(
					"from TransactionDetail t where t.shopId = ? and customerId = ?", shopId, customerId);
		return list;
	}
}
