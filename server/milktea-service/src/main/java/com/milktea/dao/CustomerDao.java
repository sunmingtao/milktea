package com.milktea.dao;

import com.milktea.entity.Customer;
import com.smt.common.spring.hibernate.dao.HibernateDao;

public interface CustomerDao extends HibernateDao<Customer>{
	public Customer findCustomer(String customerId);
	
	public boolean exists(String customerId);
}
