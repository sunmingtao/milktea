package com.smt.common.baidu.service.pushnotification;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.smt.common.baidu.service.pushnotification.context.BaiduPushNotificationDestination;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/test-context.xml"})
@ActiveProfiles("dev")
public class DevTest extends AbstractJUnit4SpringContextTests{

	@Autowired
	private BaiduPushNotificationService pushNotificationService;
	
	@Test
	public void testSendPushNotification() throws Exception{
		int count = pushNotificationService.sendPushNotification(
				new BaiduPushNotificationDestination("CID", "UID"), "Hello");
		Assert.assertEquals(count, 1);
	}
	
	@Test(expected=ChannelClientException.class)
	public void testSendPushNotificationWithInvalidMessage1() throws Exception{
		pushNotificationService.sendPushNotification(
				new BaiduPushNotificationDestination("CID", "UID"), "clientException");
	}
	@Test(expected=ChannelServerException.class)
	public void testSendPushNotificationWithInvalidMessage2() throws Exception{
		pushNotificationService.sendPushNotification(
				new BaiduPushNotificationDestination("CID", "UID"), "serverException");
	}
}
