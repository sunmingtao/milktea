package com.milktea.dao.impl;

import java.util.List;

import com.milktea.dao.TransactionSummaryDao;
import com.milktea.entity.Customer;
import com.milktea.entity.TransactionSummary;
import com.smt.common.spring.hibernate.dao.impl.AbstractHibernateDaoImpl;

public class TransactionSummaryDaoImpl extends AbstractHibernateDaoImpl<TransactionSummary> implements TransactionSummaryDao {

	@SuppressWarnings("unchecked")
	@Override
	public TransactionSummary findTransactionSummaryByShopIdCustomerId(
			String shopId, String customerId) {
		List<TransactionSummary> list 
			= getHibernateTemplate().find(
				"from TransactionSummary t where t.shop.shopId = ? and t.customer.customerId = ?", shopId, customerId);
		if (list.size() == 1){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<TransactionSummary> findTransactionSummaryByCustomerId(
			String customerId) {
		@SuppressWarnings("unchecked")
		List<TransactionSummary> list 
			= getHibernateTemplate().find(
					"from TransactionSummary t where t.customer.customerId = ?", customerId);
		return list;
	}

	@Override
	public List<TransactionSummary> findTransactionSummaryByShopId(String shopId) {
		@SuppressWarnings("unchecked")
		List<TransactionSummary> list 
			= getHibernateTemplate().find(
					"from TransactionSummary t where t.shop.shopId = ?", shopId);
		return list;
	}

	
	@Override
	public List<Customer> findCustomers(String shopId) {
		@SuppressWarnings("unchecked")
		List<Customer> list = getHibernateTemplate().find(
				"select t.customer from TransactionSummary t where t.shop.shopId = ?", shopId);
		return list;
	}
}
