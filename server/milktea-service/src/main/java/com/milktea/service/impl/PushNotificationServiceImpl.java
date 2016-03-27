package com.milktea.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.milktea.common.util.StringUtils;
import com.milktea.dao.PushNotificationDao;
import com.milktea.dao.TransactionSummaryDao;
import com.milktea.entity.Customer;
import com.milktea.entity.PushNotification;
import com.milktea.entity.PushNotificationDestination;
import com.milktea.enums.PushNotificationStatus;
import com.milktea.service.api.PushNotificationService;
import com.smt.common.baidu.service.pushnotification.BaiduPushNotificationService;
import com.smt.common.baidu.service.pushnotification.context.BaiduPushNotificationDestination;

@Service
public class PushNotificationServiceImpl implements PushNotificationService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TransactionSummaryDao transactionSummaryDao;
	
	@Autowired
	private PushNotificationDao pushNotificationDao;
	
	@Autowired
	private BaiduPushNotificationService baiduPushNotificationService;
	
	@Override
	public int preparePushNotification(final String shopId, final String message){
		if (StringUtils.isEmpty(message)){
			throw new IllegalArgumentException("Message cannot be empty");
		}
		List<Customer> list = transactionSummaryDao.findCustomers(shopId);
		int count = 0;
		for (Customer customer : list){
			if (customer.hasPushNotificationDestination()){
				savePushNotification(shopId, message, customer);
				count++;
			}
		}
		return count;
	}

	private void savePushNotification(final String shopId, final String message, final Customer customer) {
		PushNotification pushNotification = createPushNotification(shopId, message, customer);
		pushNotificationDao.save(pushNotification);
	}

	private PushNotification createPushNotification(final String shopId, final String message, 
			final Customer customer) {
		PushNotificationDestination destination 
			= new PushNotificationDestination(customer.getChannelId(), customer.getUserId());
		PushNotification pushNotification 
			= new PushNotification(shopId, message, destination);
		return pushNotification;
	}

	private boolean send(String message, PushNotificationDestination pnDestination) {
		boolean isSuccess = false;
		BaiduPushNotificationDestination destination 
			= new BaiduPushNotificationDestination(pnDestination.getChannelId(), pnDestination.getUserId());
		try {
			baiduPushNotificationService.sendPushNotification(destination, message);
			isSuccess = true;
		} catch (ChannelClientException e) {
			logger.error("Push notification error: "+e.getMessage());
		} catch (ChannelServerException e) {
			logger.error("Push notification error: "+e.getMessage());
		}
		return isSuccess;
	}

	@Override
	public int sendPushNotification(int max) {
		int count = 0;
		List<PushNotification> list = pushNotificationDao.findReadyPushNotifications(max);
		if (list.size() > 0){
			updateStatusToInProgress(list);
			count = sendAndUpdateStatus(list);	
		}
		return count;
	}

	private int sendAndUpdateStatus(List<PushNotification> list) {
		int count = 0;
		for (PushNotification pn: list){
			boolean isSuccess = send(pn.getMessage(), pn.getDestination());
			if (isSuccess){
				count++;
				pushNotificationDao.updateStatus(pn.getId(), PushNotificationStatus.COMPLETE);
			}else{
				pushNotificationDao.updateStatus(pn.getId(), PushNotificationStatus.FAILURE);
			}
		}
		return count;
	}

	private void updateStatusToInProgress(List<PushNotification> list) {
		List<Long> ids = new ArrayList<Long>();
		for (PushNotification pn: list){
			ids.add(pn.getId());
		}
		pushNotificationDao.updateStatus(ids, PushNotificationStatus.IN_PROGRESS);
	}
}
