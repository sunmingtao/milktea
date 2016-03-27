package com.milktea.dao.it;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.milktea.dao.CustomerDao;
import com.milktea.dao.TransactionDetailDao;
import com.milktea.entity.Customer;
import com.milktea.entity.PushNotificationDestination;
import com.milktea.entity.TransactionDetail;
import com.milktea.service.it.Constants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/test-context.xml"})
@ActiveProfiles(Constants.PROFILE)
public class DaoIT extends AbstractTransactionalJUnit4SpringContextTests{

	private static final String CUST_ID_001 = "c001";
	
	private static final String SHOP_ID_001 = "s001";

	private static final String SHOP_ID_002 = "s002";
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private TransactionDetailDao transactionDetailDao;
	
	@Before
	public void setup(){
		executeSqlScript("sql/dao-test-data.sql", false);
	}

	@Test
	public void testSaveAndFindCustomer(){
		Assert.assertEquals(1, countRowsInTable("T_CUSTOMER"));
		Customer customer = new Customer("0423747412", "Password");
		customer.setDestination(new PushNotificationDestination(
				"CID", "UID"));
		customerDao.save(customer);
		Assert.assertEquals(2, countRowsInTable("T_CUSTOMER"));
		Customer dbCustomer = customerDao.findCustomer("0423747412");
		Assert.assertEquals("0423747412", dbCustomer.getCustomerId());
		Assert.assertEquals("UID", dbCustomer.getUserId());
		Assert.assertEquals("CID", dbCustomer.getChannelId());
		Assert.assertEquals("Password", dbCustomer.getPassword());
	}
	
	@Test
	public void testFindExistingCustomer(){
		Customer customer = customerDao.findCustomer("0423747410");
		Assert.assertEquals("0423747410", customer.getCustomerId());
		Assert.assertEquals("123", customer.getPassword());
		Assert.assertEquals("C123", customer.getChannelId());
		Assert.assertEquals("U123", customer.getUserId());
	}
	
	@Test
	public void testFindNonExistingCustomer(){
		Customer customer = customerDao.findCustomer("0423747411");
		Assert.assertNull(customer);
	}
	
	
	
	@Test
	public void testSaveAndFindTransactionDetail1(){
		Assert.assertEquals(1, countRowsInTable("T_TRANSACTION_DETAIL"));
		TransactionDetail t = new TransactionDetail("0423747411","13003109241");
		t.setNumberOfCups(2);
		transactionDetailDao.save(t);
		Assert.assertEquals(2, countRowsInTable("T_TRANSACTION_DETAIL"));
		List<TransactionDetail> list = transactionDetailDao.findTransactionDetail("0423747411","13003109241");
		Assert.assertEquals(1, list.size());
		TransactionDetail dbt = list.get(0);
		Assert.assertTrue(dbt.getNumberOfCups().compareTo(2)==0);
		Assert.assertNull(dbt.getAmount());
		Assert.assertNotNull(dbt.getTransactionTime());
	}
	
	@Test
	public void testSaveAndFindTransactionDetail2(){
		Assert.assertEquals(1, countRowsInTable("T_TRANSACTION_DETAIL"));
		TransactionDetail t = new TransactionDetail("0423747411","13003109241");
		t.setAmount(new BigDecimal("6.00"));
		transactionDetailDao.save(t);
		Assert.assertEquals(2, countRowsInTable("T_TRANSACTION_DETAIL"));
		List<TransactionDetail> list = transactionDetailDao.findTransactionDetail("0423747411","13003109241");
		Assert.assertEquals(1, list.size());
		TransactionDetail dbt = list.get(0);
		Assert.assertTrue(new BigDecimal("6.00").compareTo(dbt.getAmount())==0);
		Assert.assertNull(dbt.getNumberOfCups());
		Assert.assertNotNull(dbt.getTransactionTime());
	}
	
}
