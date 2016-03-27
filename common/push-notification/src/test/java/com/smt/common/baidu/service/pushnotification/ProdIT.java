package com.smt.common.baidu.service.pushnotification;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.smt.common.baidu.service.pushnotification.context.BaiduPushNotificationDestination;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/test-context.xml"})
@ActiveProfiles("prod")
public class ProdIT {

	@Autowired
	private BaiduPushNotificationService pushNotificationService;
	
	@Test
	public void test() throws Exception {
		BaiduPushNotificationDestination destination = new BaiduPushNotificationDestination("4142647980261393470", "1136622317945555187");
		System.out.println(pushNotificationService.sendPushNotification(destination, "Hello"));
	}
	
}
