package com.milktea.service.it;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.milktea.service.api.PushNotificationService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/test-context.xml"})
@ActiveProfiles(Constants.PROFILE)
public class PushNotificationServiceIT extends AbstractTransactionalJUnit4SpringContextTests{
	
	private static final String SHOP_ID_001 = "s001";
	
	@Autowired
	private PushNotificationService pushNotificationService;
	
	@Before
	public void setup(){
		executeSqlScript("sql/push-notification-test-data.sql", false);
	}
	
	@Test
	public void prepareNotification(){
		int count = pushNotificationService.preparePushNotification(SHOP_ID_001, "Hello");
		Assert.assertEquals(8, count);
	}
	
	@Test
	public void prepareNotificationWithNonExistingShopId(){
		int count = pushNotificationService.preparePushNotification("NonExisting", "Hello");
		Assert.assertEquals(0, count);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyMessage1(){
		int count = pushNotificationService.preparePushNotification(SHOP_ID_001, null);
		Assert.assertEquals(0, count);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyMessage2(){
		int count = pushNotificationService.preparePushNotification(SHOP_ID_001, " ");
		Assert.assertEquals(0, count);
	}
	
	@Test
	public void sendNotification(){
		pushNotificationService.preparePushNotification(SHOP_ID_001, "Message");
		int count = pushNotificationService.sendPushNotification(5);
		Assert.assertEquals(5, count);
		count = pushNotificationService.sendPushNotification(5);
		Assert.assertEquals(3, count);
		count = pushNotificationService.sendPushNotification(5);
		Assert.assertEquals(0, count);
	}
	
	@Test
	public void sendNotificationWithNoNotificationReady(){
		int count = pushNotificationService.sendPushNotification(5);
		Assert.assertEquals(0, count);
	}
	
	@Test
	public void sendNotificationWithBadMessage1(){
		pushNotificationService.preparePushNotification(SHOP_ID_001, "clientException");
		int count = pushNotificationService.sendPushNotification(5);
		Assert.assertEquals(0, count);
	}
	
	@Test
	public void sendNotificationWithBadMessage2(){
		pushNotificationService.preparePushNotification(SHOP_ID_001, "serverException");
		int count = pushNotificationService.sendPushNotification(5);
		Assert.assertEquals(0, count);
	}
	
}
