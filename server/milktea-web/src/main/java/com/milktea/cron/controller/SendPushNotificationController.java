package com.milktea.cron.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.milktea.common.constants.Constants;
import com.milktea.service.api.PushNotificationService;

@Controller
public class SendPushNotificationController {
	
	@Autowired
	private PushNotificationService pushNotificationService;
	
	@RequestMapping("test/{id}")
	@ResponseBody
	public String test(@PathVariable String id) {
		return id;
	}
	
	@RequestMapping(value=Constants.SEND_PUSH_NOTIFICATION_PATH, method = RequestMethod.GET)
	@ResponseBody
	public int sendPushNotificationForGet(@PathVariable int max) {
		return pushNotificationService.sendPushNotification(max);
	}
	
}
