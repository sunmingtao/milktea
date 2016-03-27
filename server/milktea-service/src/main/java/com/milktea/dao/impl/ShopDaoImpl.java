package com.milktea.dao.impl;

import java.util.List;

import com.milktea.dao.ShopDao;
import com.milktea.entity.Shop;
import com.smt.common.spring.hibernate.dao.impl.AbstractHibernateDaoImpl;

public class ShopDaoImpl extends AbstractHibernateDaoImpl<Shop> implements ShopDao {

	@SuppressWarnings("unchecked")
	@Override
	public Shop findShopById(String shopId) {
		List<Shop> list = getHibernateTemplate().find("from Shop s where s.shopId = ?", shopId);
		if (list.size() == 1){
			return list.get(0);	
		}
		return null;
	}

	@Override
	public boolean existsShopId(String shopId) {
		return findShopById(shopId) != null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Shop findShopByName(String shopName) {
		List<Shop> list = getHibernateTemplate().find("from Shop s where s.shopName = ?", shopName);
		if (list.size() == 1){
			return list.get(0);	
		}
		return null;
	}

	@Override
	public boolean existsShopName(String shopName) {
		return findShopByName(shopName) != null;
	}
}
