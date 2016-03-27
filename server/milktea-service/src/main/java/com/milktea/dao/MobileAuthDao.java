package com.milktea.dao;

import java.util.List;

import com.milktea.entity.MobileAuth;
import com.smt.common.spring.hibernate.dao.HibernateDao;

public interface MobileAuthDao extends HibernateDao<MobileAuth>{

	/**
	 * Find authentication codes for shop
	 * @param mobile
	 * @return
	 */
	public List<MobileAuth> findShopAuthCodes(String mobile);
	
	/**
	 * Find authentication codes for a customer
	 * @param mobile
	 * @return
	 */
	public List<MobileAuth> findCustomerAuthCodes(String mobile);
}
