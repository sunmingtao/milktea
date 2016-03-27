package com.milktea.dao.it;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.milktea.dao.ShopDao;
import com.milktea.entity.Shop;
import com.milktea.service.it.Constants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/test-context.xml"})
@ActiveProfiles(Constants.PROFILE)
public class ShopDaoIT extends AbstractTransactionalJUnit4SpringContextTests{
	
	private static final String SHOP_ID_001 = "s001"; 
	private static final String SHOP_ID_003 = "s003"; 
	
	@Autowired
	private ShopDao shopDao;
	
	@Before
	public void setup(){
		executeSqlScript("sql/dao-test-data.sql", false);
	}
	
	@Test
	public void testSaveNonExistingShopAndFind1(){
		Shop s = new Shop(SHOP_ID_003, "password");
		s.setShopName("SHOP6");
		s.setAmountToRedeem(new BigDecimal("5.50"));
		shopDao.save(s);
		s = shopDao.findShopById(SHOP_ID_003);
		Assert.assertEquals(SHOP_ID_003, s.getShopId());
		Assert.assertEquals("password", s.getPassword());
		Assert.assertTrue(new BigDecimal("5.5").compareTo(s.getAmountToRedeem())==0);
	}
	
	@Test
	public void testSaveNonExistingShopAndFind2(){
		Shop s = new Shop(SHOP_ID_003, "password");
		s.setNumberOfCupsToRedeem(10);
		s.setShopName("SHOP6");
		shopDao.save(s);
		s = shopDao.findShopById(SHOP_ID_003);
		Assert.assertEquals(SHOP_ID_003, s.getShopId());
		Assert.assertEquals("password", s.getPassword());
		Assert.assertTrue(s.getNumberOfCupsToRedeem()==10);
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void testSaveExistingShopId(){
		Shop s = new Shop(SHOP_ID_001, "password");
		s.setShopName("SHOP6");
		shopDao.save(s);
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void testSaveExistingShopName(){
		Shop s = new Shop(SHOP_ID_003, "password");
		s.setShopName("SHOP1");
		shopDao.save(s);
	}
	
	@Test
	public void testFindExistingShopById(){
		Shop s = shopDao.findShopById("0423747411");
		Assert.assertEquals("0423747411", s.getShopId());
		Assert.assertEquals("password", s.getPassword());
		Assert.assertTrue(new BigDecimal("5.5").compareTo(s.getAmountToRedeem())==0);
	}
	
	@Test
	public void testFindNonExistingShopById(){
		Shop s = shopDao.findShopById("0423747412");
		Assert.assertNull(s);
	}
	
	@Test
	public void testFindExistingShopByName(){
		Shop s = shopDao.findShopByName("SHOP1");
		Assert.assertEquals("0423747411", s.getShopId());
		Assert.assertEquals("password", s.getPassword());
		Assert.assertTrue(new BigDecimal("5.5").compareTo(s.getAmountToRedeem())==0);
	}
	
	@Test
	public void testFindNonExistingShopByName(){
		Shop s = shopDao.findShopByName("SHOP6");
		Assert.assertNull(s);
	}
}
