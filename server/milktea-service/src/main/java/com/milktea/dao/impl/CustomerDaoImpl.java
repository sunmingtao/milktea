package com.milktea.dao.impl;

import java.util.List;

import com.milktea.dao.CustomerDao;
import com.milktea.entity.Customer;
import com.smt.common.spring.hibernate.dao.impl.AbstractHibernateDaoImpl;

public class CustomerDaoImpl extends AbstractHibernateDaoImpl<Customer> implements CustomerDao {

	@SuppressWarnings("unchecked")
	@Override
	public Customer findCustomer(String customerId) {
		List<Customer> list = getHibernateTemplate().find("from Customer u where u.customerId = ?", customerId);
		if (list.size() == 1){
			return list.get(0);	
		}
		return null;
	}

	@Override
	public boolean exists(String customerId) {
		return findCustomer(customerId) != null;
	}

}
