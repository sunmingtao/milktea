package com.milktea.mock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.milktea.dao.CustomerDao;
import com.milktea.dao.ShopDao;
import com.milktea.entity.Customer;
import com.milktea.entity.Shop;

@Service
public class TransactionalService {
	
	private static final String CUST_ID_001 = "c001";
	public static final String SHOP_ID_001 = "s001";
	private static final String SHOP_NAME_001 = "SHOP_001";
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private ShopDao shopDao;
	
	@Transactional
	public void saveCustomerAndShopWithTransaction1(){
		Customer customer = new Customer(CUST_ID_001, "password");
		customerDao.save(customer);
		Shop shop = new Shop(SHOP_ID_001, "password");
		shop.setShopName(SHOP_NAME_001);
		shopDao.save(shop);
		shop = new Shop(SHOP_ID_001, "password");
		shop.setShopName(SHOP_NAME_001);
		shopDao.save(shop);
	}
	
	@Transactional
	public void saveCustomerAndShopWithTransaction2(){
		Customer customer = new Customer(CUST_ID_001, "password");
		customerDao.save(customer);
		Shop shop = new Shop(SHOP_ID_001, "password");
		shop.setShopName(SHOP_NAME_001);
		shopDao.save(shop);
	}
	
	public void saveCustomerAndShopWithoutTransaction(){
		Customer customer = new Customer(CUST_ID_001, "password");
		customerDao.save(customer);
		Shop shop = new Shop(SHOP_ID_001, "password");
		shop.setShopName(SHOP_NAME_001);
		shopDao.save(shop);
		shop = new Shop(SHOP_ID_001, "password");
		shop.setShopName(SHOP_NAME_001);
		shopDao.save(shop);
	}
}
