package com.milktea.dao.it;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.milktea.dao.PushNotificationDao;
import com.milktea.entity.PushNotification;
import com.milktea.entity.PushNotificationDestination;
import com.milktea.enums.PushNotificationStatus;
import com.milktea.service.it.Constants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/test-context.xml"})
@ActiveProfiles(Constants.PROFILE)
public class PushNotificationDaoIT extends AbstractTransactionalJUnit4SpringContextTests{

	private static final String SHOP_ID_001 = "s001";
	private static final String SHOP_ID_002 = "s002";
	
	@Autowired
	private PushNotificationDao pushNotificationDao;
	
	@Before
	public void setup(){
		executeSqlScript("sql/push-notification-dao-test-data.sql", false);
	}
	
	@Test
	public void savePushNotification(){
		PushNotification pn = new PushNotification(SHOP_ID_001, "Hello World", 
				new PushNotificationDestination("CID", "UID"));
		pushNotificationDao.save(pn);
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void shopIdFKConstraint(){
		PushNotification pn = new PushNotification(SHOP_ID_002, "Hello World", 
				new PushNotificationDestination("CID", "UDI"));
		pushNotificationDao.save(pn);
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void messageNotNullConstraint(){
		PushNotification pn = new PushNotification(SHOP_ID_001, null, 
				new PushNotificationDestination("CID", "UDI"));
		pushNotificationDao.save(pn);
	}
	
	@Test
	public void findPushNotifications(){
		List<PushNotification> list = pushNotificationDao.findPushNotifications(SHOP_ID_001);
		Assert.assertEquals(6, list.size());
		PushNotification pn = list.get(0);
		Assert.assertEquals("UID", pn.getDestination().getUserId());
		Assert.assertEquals("CID", pn.getDestination().getChannelId());
		Assert.assertEquals("HI There1", pn.getMessage());
		Assert.assertEquals(PushNotificationStatus.READY, pn.getStatus());
		Assert.assertNotNull(pn.getCreatedTime());
	}
	
	@Test
	public void findPushNotificationsWithNonExistingShopId(){
		List<PushNotification> list = pushNotificationDao.findPushNotifications("NonExisting");
		Assert.assertEquals(0, list.size());
	}
	
	@Test
	public void findReadyPushNotifications1(){
		List<PushNotification> list = pushNotificationDao.findReadyPushNotifications(4);
		Assert.assertEquals(4, list.size());
	}
	
	@Test
	public void findReadyPushNotifications2(){
		List<PushNotification> list = pushNotificationDao.findReadyPushNotifications(10);
		Assert.assertEquals(6, list.size());
	}
	
	@Test
	public void findReadyPushNotifications3(){
		deleteFromTables("T_PUSH_NOTIFICATION");
		List<PushNotification> list = pushNotificationDao.findReadyPushNotifications(5);
		Assert.assertEquals(0, list.size());
	}
	
	@Test
	public void updateStatusToInProgress(){
		List<Long> ids = new ArrayList<Long>();
		List<PushNotification> list = pushNotificationDao.findReadyPushNotifications(5);
		for (PushNotification pn: list){
			ids.add(pn.getId());
		}
		Assert.assertEquals(5, pushNotificationDao.updateStatus
				(ids, PushNotificationStatus.IN_PROGRESS));
	}
	
}
