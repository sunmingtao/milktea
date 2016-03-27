package com.smt.common.baidu.service.pushnotification;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;

public class Test {

	public static void main(String[] args) {
		String apiKey = "FN8IAC4BIpfE4mt0PlrTQqsv";
		String secretKey = "SDcmGRtiKMs0jGsH0lkxQt3Mq83boZnk";
		ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);
		
		// 2. ï¿½ï¿½ï¿½ï¿½BaiduChannelClientï¿½ï¿½ï¿½ï¿½Êµï¿½ï¿½
		BaiduChannelClient channelClient = new BaiduChannelClient(pair);
		
		// 3. ï¿½ï¿½Òªï¿½Ë½â½»ï¿½ï¿½Ï¸ï¿½Ú£ï¿½ï¿½ï¿½×¢ï¿½ï¿½YunLogHandlerï¿½ï¿½
//		channelClient.setChannelLogHandler(new YunLogHandler() {
//			@Override
//			public void onHandle(YunLogEvent event) {
//				System.out.println(event.getMessage());
//			}
//		});
		
		try {
			
			// 4. ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
			//		ï¿½Ö»ï¿½Ëµï¿½ChannelIdï¿½ï¿½ ï¿½Ö»ï¿½Ëµï¿½UserIdï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½1111111111111ï¿½ï¿½ï¿½æ£¬ï¿½Ã»ï¿½ï¿½ï¿½ï¿½æ»»Îªï¿½Ô¼ï¿½ï¿½ï¿½
			PushUnicastMessageRequest request = new PushUnicastMessageRequest();
			request.setDeviceType(3);	// device_type => 1: web 2: pc 3:android 4:ios 5:wp		
			request.setChannelId(4142647980261393470L);	
			request.setUserId("1136622317945555187");	 
			
			request.setMessageType(1);
			request.setMessage("{\"title\":\"Notify_title_danbo\",\"description\":\"Notify_description_content\"}");
			
			// 5. ï¿½ï¿½ï¿½ï¿½pushMessageï¿½Ó¿ï¿½
			PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);
				
			// 6. ï¿½ï¿½Ö¤ï¿½ï¿½ï¿½Í³É¹ï¿½
			System.out.println("push amount : " + response.getSuccessAmount()); 
			
		} catch (ChannelClientException e) {
			// ï¿½ï¿½ï¿½ï¿½Í»ï¿½ï¿½Ë´ï¿½ï¿½ï¿½ï¿½ì³£
			e.printStackTrace();
		} catch (ChannelServerException e) {
			// ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ë´ï¿½ï¿½ï¿½ï¿½ì³£
			System.out.println(
					String.format("request_id: %d, error_code: %d, error_message: %s" , 
						e.getRequestId(), e.getErrorCode(), e.getErrorMsg()
						)
					);
		}

	}

}
