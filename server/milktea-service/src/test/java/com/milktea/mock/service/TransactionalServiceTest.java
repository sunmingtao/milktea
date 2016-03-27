package com.milktea.mock.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.milktea.dao.ShopDao;
import com.milktea.entity.Shop;
import com.milktea.service.it.Constants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/test-context.xml"})
@ActiveProfiles(Constants.PROFILE)
public class TransactionalServiceTest{
	@Autowired
	private TransactionalService service;
	
	@Autowired
	private ShopDao shopDao;
	
	@Test
	public void testTransactionalAnnotation1(){
		try {
			service.saveCustomerAndShopWithTransaction1();
		} catch (Exception e) {
		}
		Shop shop = shopDao.findShopById(TransactionalService.SHOP_ID_001);
		Assert.assertNull(shop);
	}
	
	@Test
	public void testTransactionalAnnotation2(){
		service.saveCustomerAndShopWithTransaction2();
		Shop shop = shopDao.findShopById(TransactionalService.SHOP_ID_001);
		Assert.assertNotNull(shop);
	}
	
	@Test
	public void testNoTransactionAnnotation(){
		try {
			service.saveCustomerAndShopWithoutTransaction();
		} catch (Exception e) {
		}
		Shop shop = shopDao.findShopById(TransactionalService.SHOP_ID_001);
		Assert.assertNotNull(shop);
	}
}
