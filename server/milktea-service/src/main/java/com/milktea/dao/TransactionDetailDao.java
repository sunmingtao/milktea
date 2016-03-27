package com.milktea.dao;

import java.util.List;

import com.milktea.entity.TransactionDetail;
import com.smt.common.spring.hibernate.dao.HibernateDao;

public interface TransactionDetailDao extends HibernateDao<TransactionDetail>{
	public List<TransactionDetail> findTransactionDetail(String shopId, String customerId);
}
