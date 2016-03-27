package com.milktea.common.sms;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.milktea.common.sms.response.SmsResponse;
import com.milktea.common.sms.service.SmsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/test-context.xml"})
@ActiveProfiles("dev")
public class DevTest extends AbstractJUnit4SpringContextTests{

	@Autowired
	private SmsService smsService;
	
	@Test
	public void testSendSms(){
		Assert.assertEquals(SmsResponse.INVALID_MOBILE, smsService.sendSms("1234", "Hi"));
		Assert.assertEquals(SmsResponse.SUCCESS, smsService.sendSms("1234567890", "Hi"));
	}
}
