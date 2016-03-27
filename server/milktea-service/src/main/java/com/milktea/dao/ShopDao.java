package com.milktea.dao;

import com.milktea.entity.Shop;
import com.smt.common.spring.hibernate.dao.HibernateDao;

public interface ShopDao extends HibernateDao<Shop>{
	public Shop findShopById(String shopId);
	
	public boolean existsShopId(String shopId);
	
	public Shop findShopByName(String shopName);
	
	public boolean existsShopName(String shopName);
}
