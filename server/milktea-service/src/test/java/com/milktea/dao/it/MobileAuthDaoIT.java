package com.milktea.dao.it;

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

import com.milktea.dao.MobileAuthDao;
import com.milktea.entity.MobileAuth;
import com.milktea.enums.UserType;
import com.milktea.service.it.Constants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/test-context.xml"})
@ActiveProfiles(Constants.PROFILE)
public class MobileAuthDaoIT extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private MobileAuthDao mobileAuthDao;
	
	@Before
	public void setup(){
		executeSqlScript("sql/mobile-auth-dao-test-data.sql", false);
	}
	
	@Test
	public void testSaveMobileAuthCode(){
		MobileAuth m = new MobileAuth("13003109241", "987654", UserType.CUSTOMER);
		m.setUserType(UserType.CUSTOMER);
		mobileAuthDao.save(m);
		Assert.assertEquals(2, countRowsInTable("T_MOBILE_AUTH"));
		List<MobileAuth> list = mobileAuthDao.findCustomerAuthCodes("13003109241");
		Assert.assertEquals(1, list.size());
		MobileAuth dbm = list.get(0);
		Assert.assertNotNull(dbm.getCreatedTime());
		Assert.assertEquals("13003109241", dbm.getMobile());
		Assert.assertEquals("987654", dbm.getAuthenticationCode());
	}
	
	@Test
	public void testFindExistingMobileAuthCodes(){
		List<MobileAuth> list = mobileAuthDao.findCustomerAuthCodes("0423747411");
		Assert.assertEquals(1, list.size());
		MobileAuth dbm = list.get(0);
		Assert.assertNotNull(dbm.getCreatedTime());
		Assert.assertEquals("0423747411", dbm.getMobile());
		Assert.assertEquals("45678", dbm.getAuthenticationCode());
		Assert.assertEquals(UserType.CUSTOMER, dbm.getUserType());
	}
	
	@Test
	public void testFindNonExistingMobileAuthCodes(){
		List<MobileAuth> list = mobileAuthDao.findCustomerAuthCodes("0423747412");
		Assert.assertEquals(0, list.size());
	}
}
