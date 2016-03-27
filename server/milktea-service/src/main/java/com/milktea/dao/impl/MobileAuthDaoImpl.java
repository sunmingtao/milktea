package com.milktea.dao.impl;

import java.util.List;

import com.milktea.dao.MobileAuthDao;
import com.milktea.entity.MobileAuth;
import com.milktea.enums.UserType;
import com.smt.common.spring.hibernate.dao.impl.AbstractHibernateDaoImpl;

public class MobileAuthDaoImpl extends AbstractHibernateDaoImpl<MobileAuth> implements MobileAuthDao {

	@Override
	public List<MobileAuth> findShopAuthCodes(String mobile) {
		return findAuthCodes(mobile, UserType.SHOP);
	}

	@Override
	public List<MobileAuth> findCustomerAuthCodes(String mobile) {
		return findAuthCodes(mobile, UserType.CUSTOMER);
	}

	@SuppressWarnings("unchecked")
	private List<MobileAuth> findAuthCodes(String mobile, UserType userType) {
		return getHibernateTemplate().find("from MobileAuth where mobile = ? and userType = ?", new Object[]{mobile, userType});
	}
}
