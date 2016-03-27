package com.milktea.common.sms;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.milktea.common.sms.service.SmsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/test-context.xml"})
public class ProdIT extends AbstractJUnit4SpringContextTests{

	@Autowired
	private SmsService smsService;
	
	@Test
	@Ignore
	public void testSendSms(){
		System.out.println("Response: "+
				smsService.sendSms("13003109241", "老妈，这是一条测试信息！"));
	}
}
