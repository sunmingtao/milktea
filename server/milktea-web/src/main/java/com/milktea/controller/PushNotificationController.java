package com.milktea.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.milktea.common.constants.Constants;
import com.milktea.common.dto.pushnotification.PushNotificationRequest;
import com.milktea.common.dto.pushnotification.PushNotificationResponse;
import com.milktea.service.api.PushNotificationService;

@Controller
public class PushNotificationController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PushNotificationService pushNotificationService;
	
	@RequestMapping(value=Constants.PREPARE_PUSH_NOTIFICATION_PATH, method = RequestMethod.POST)
	@ResponseBody
	public PushNotificationResponse preparePushNotification(@RequestBody PushNotificationRequest request){
		String shopId = request.getUserContext().getUsername();
		int count = pushNotificationService.preparePushNotification(shopId, request.getMessage());
		PushNotificationResponse pushNotificationResponse = new PushNotificationResponse();
		pushNotificationResponse.setStatus(Constants.SUCCESS_RESPONSE);
		pushNotificationResponse.setCount(count);
		return pushNotificationResponse;
	}
}
