package com.milktea.dao;

import java.util.List;

import com.milktea.entity.Customer;
import com.milktea.entity.TransactionSummary;
import com.smt.common.spring.hibernate.dao.HibernateDao;

public interface TransactionSummaryDao extends HibernateDao<TransactionSummary>{
	public TransactionSummary findTransactionSummaryByShopIdCustomerId(String shopId, String customerId);
	
	public List<TransactionSummary> findTransactionSummaryByCustomerId(String customerId);
	
	public List<TransactionSummary> findTransactionSummaryByShopId(String shopId);
	
	public List<Customer> findCustomers(String shopId);
}
