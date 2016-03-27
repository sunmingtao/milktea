package com.milktea.dao.it;

import java.math.BigDecimal;
import java.util.List;

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

import com.milktea.dao.CustomerDao;
import com.milktea.dao.ShopDao;
import com.milktea.dao.TransactionSummaryDao;
import com.milktea.entity.Customer;
import com.milktea.entity.Shop;
import com.milktea.entity.TransactionSummary;
import com.milktea.service.it.Constants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/test-context.xml"})
@ActiveProfiles(Constants.PROFILE)
public class TransactionSummaryDaoIT extends AbstractTransactionalJUnit4SpringContextTests{

	private static final String SHOP_ID_001 = "s001";
	private static final String SHOP_ID_002 = "s002";
	private static final String SHOP_ID_003 = "s003";
	private static final String CUST_ID_001 = "c001";
	
	@Autowired
	private TransactionSummaryDao transactionSummaryDao;
	
	@Autowired
	private ShopDao shopDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Before
	public void setup(){
		executeSqlScript("sql/trans-summary-dao-test-data.sql", false);
	}
	
	@Test
	public void testSaveAndFindTransactionSummary(){
		Shop shop = shopDao.findShopById(SHOP_ID_001);
		Customer customer = customerDao.findCustomer(CUST_ID_001);
		TransactionSummary ts = new TransactionSummary(shop, customer);
		ts.addTotalCups(83);
		ts.addTotalCupsRedeemed(70);
		ts.addTotalAmount(new BigDecimal("55.6"));
		ts.addTotalAmountRedeemed(new BigDecimal("40.0"));
		transactionSummaryDao.save(ts);
		TransactionSummary dbts = 
				transactionSummaryDao.findTransactionSummaryByShopIdCustomerId(SHOP_ID_001, CUST_ID_001);
		Assert.assertTrue(dbts.getTotalCups().compareTo(83)==0);
		Assert.assertTrue(dbts.getTotalCupsRedeemed().compareTo(70)==0);
		Assert.assertTrue(dbts.getTotalAmount().compareTo(new BigDecimal("55.6"))==0);
		Assert.assertTrue(dbts.getTotalAmountRedeemed().compareTo(new BigDecimal("40.0"))==0);
		Assert.assertEquals(SHOP_ID_001, dbts.getShop().getShopId());
		Assert.assertEquals(CUST_ID_001, dbts.getCustomer().getCustomerId());
	}
	
	@Test
	public void testSaveAndFindTransactionSummaryByCustomerId(){
		Shop shop001 = shopDao.findShopById(SHOP_ID_001);
		Shop shop002 = shopDao.findShopById(SHOP_ID_002);
		Customer customer = customerDao.findCustomer(CUST_ID_001);
		TransactionSummary ts = new TransactionSummary(shop001, customer);
		ts.addTotalCups(83);
		ts.addTotalCupsRedeemed(70);
		ts.addTotalAmount(new BigDecimal("55.6"));
		ts.addTotalAmountRedeemed(new BigDecimal("40.0"));
		transactionSummaryDao.save(ts);
		ts = new TransactionSummary(shop002, customer);
		ts.addTotalCups(83);
		ts.addTotalCupsRedeemed(70);
		ts.addTotalAmount(new BigDecimal("55.6"));
		ts.addTotalAmountRedeemed(new BigDecimal("40.0"));
		transactionSummaryDao.save(ts);
		List<TransactionSummary> list = 
				transactionSummaryDao.findTransactionSummaryByCustomerId(CUST_ID_001);
		Assert.assertEquals(2, list.size());
		TransactionSummary dbts = list.get(0);
		Assert.assertTrue(dbts.getTotalCups().compareTo(83)==0);
		Assert.assertTrue(dbts.getTotalCupsRedeemed().compareTo(70)==0);
		Assert.assertTrue(dbts.getTotalAmount().compareTo(new BigDecimal("55.6"))==0);
		Assert.assertTrue(dbts.getTotalAmountRedeemed().compareTo(new BigDecimal("40.0"))==0);
		Assert.assertEquals(SHOP_ID_001, dbts.getShop().getShopId());
		Assert.assertEquals(CUST_ID_001, dbts.getCustomer().getCustomerId());
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void testTransactionSummaryUniqueConstraint(){
		Shop shop = shopDao.findShopById(SHOP_ID_001);
		Customer customer = customerDao.findCustomer(CUST_ID_001);
		TransactionSummary ts = new TransactionSummary(shop, customer);
		ts.addTotalCups(83);
		ts.addTotalCupsRedeemed(70);
		ts.addTotalAmount(new BigDecimal("55.6"));
		ts.addTotalAmountRedeemed(new BigDecimal("40.0"));
		transactionSummaryDao.save(ts);
		TransactionSummary ts2 = new TransactionSummary(shop, customer);
		ts2.addTotalCups(83);
		ts2.addTotalCupsRedeemed(70);
		ts2.addTotalAmount(new BigDecimal("55.6"));
		ts2.addTotalAmountRedeemed(new BigDecimal("40.0"));
		transactionSummaryDao.save(ts2);
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void testSaveTransactionSummaryWithInvaidShopId(){
		Customer customer = customerDao.findCustomer(CUST_ID_001);
		TransactionSummary ts = new TransactionSummary(new Shop("NOT_EXISTING"), customer);
		transactionSummaryDao.save(ts);
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void testSaveTransactionSummaryWithInvaidCustomerId(){
		Shop shop = shopDao.findShopById(SHOP_ID_001);
		TransactionSummary ts = new TransactionSummary(shop, new Customer("NOT_EXISTING"));
		transactionSummaryDao.save(ts);
	}
	
	@Test
	public void testSaveAndFindTransactionSummaryByNonExistingCustomerId(){
		List<TransactionSummary> list = 
				transactionSummaryDao.findTransactionSummaryByCustomerId("NOT_EXISTING");
		Assert.assertEquals(0, list.size());
	}
	
	@Test
	public void testFindCustomers(){
		List<Customer> list = transactionSummaryDao.findCustomers(SHOP_ID_003);
		Assert.assertEquals(1, list.size());
		Customer c = list.get(0);
		Assert.assertEquals("CID", c.getChannelId());
		Assert.assertEquals("UID", c.getUserId());
	}
	
	@Test
	public void testFindCustomersWithNonExistingShopId(){
		List<Customer> list = transactionSummaryDao.findCustomers("NOT_EXISTING");
		Assert.assertEquals(0, list.size());
	}
}
