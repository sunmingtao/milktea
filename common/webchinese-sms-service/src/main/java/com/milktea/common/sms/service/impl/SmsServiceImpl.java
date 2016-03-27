package com.milktea.common.sms.service.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.milktea.common.sms.response.SmsResponse;
import com.milktea.common.sms.service.SmsService;

public class SmsServiceImpl implements SmsService{

	private String uid;
	private String key;
	private final static String URL = "http://gbk.sms.webchinese.cn"; 
	
	@Override
	public SmsResponse sendSms(String mobile, String message) {
		HttpHeaders headers = new HttpHeaders();
    	headers.set("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
    	
    	MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
    	map.add("Uid", uid);
    	map.add("Key", key);
    	map.add("smsMob", mobile);
    	map.add("smsText", message);
    	
    	HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
    	RestTemplate restTemplate = new RestTemplate();
    	String responseCode = restTemplate.postForObject(URL, requestEntity, String.class);
    	return SmsResponse.forCode(responseCode);
	}


	public void setUid(String uid) {
		this.uid = uid;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
