package com.milktea.client.wsconsumer.factory;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.milktea.client.wsconsumer.AuthenticationWebServiceConsumer;
import com.milktea.client.wsconsumer.NoAuthenticationWebServiceConsumer;
import com.milktea.client.wsconsumer.WebServiceConsumer;
import com.milktea.common.enums.WebServiceEnum;
import com.smt.common.spring.rest.RestWebServiceClientService;

public class WebServiceConsumerFactoryImplTest {

	private RestWebServiceClientService wsClientService = null; 
			
	@Before
	public void setup(){
		wsClientService = EasyMock.createMock(RestWebServiceClientService.class);
	}
	
	@Test
	public void testRegisterShop() {
		WebServiceConsumerFactoryImpl factory = new WebServiceConsumerFactoryImpl("http://fake.com", wsClientService);
		WebServiceConsumer<?> wsConsumer = factory.getWebServiceConsumer(WebServiceEnum.REGISTER_SHOP);
		Assert.assertTrue(wsConsumer instanceof NoAuthenticationWebServiceConsumer);
	}
	
	@Test
	public void testRegisterCustomerWithAuthCode() {
		WebServiceConsumerFactoryImpl factory = new WebServiceConsumerFactoryImpl("http://fake.com", wsClientService);
		WebServiceConsumer<?> wsConsumer = factory.getWebServiceConsumer(WebServiceEnum.REGISTER_CUSTOMER_WITH_AUTH_CODE);
		Assert.assertTrue(wsConsumer instanceof NoAuthenticationWebServiceConsumer);
	}
	
	@Test
	public void testRegisterCustomerWithoutAuthCode() {
		WebServiceConsumerFactoryImpl factory = new WebServiceConsumerFactoryImpl("http://fake.com", wsClientService);
		WebServiceConsumer<?> wsConsumer = factory.getWebServiceConsumer(WebServiceEnum.REGISTER_CUSTOMER_WITHOUT_AUTH_CODE);
		Assert.assertTrue(wsConsumer instanceof NoAuthenticationWebServiceConsumer);
	}
	
	@Test
	public void testPurchase() {
		WebServiceConsumerFactoryImpl factory = new WebServiceConsumerFactoryImpl("http://fake.com", wsClientService);
		WebServiceConsumer<?> wsConsumer = factory.getWebServiceConsumer(WebServiceEnum.PURCHASE);
		Assert.assertTrue(wsConsumer instanceof AuthenticationWebServiceConsumer);
	}
	
	@Test
	public void testRedeem() {
		WebServiceConsumerFactoryImpl factory = new WebServiceConsumerFactoryImpl("http://fake.com", wsClientService);
		WebServiceConsumer<?> wsConsumer = factory.getWebServiceConsumer(WebServiceEnum.REDEEM);
		Assert.assertTrue(wsConsumer instanceof AuthenticationWebServiceConsumer);
	}
	
	@Test
	public void testSendMobileAuthCode() {
		WebServiceConsumerFactoryImpl factory = new WebServiceConsumerFactoryImpl("http://fake.com", wsClientService);
		WebServiceConsumer<?> wsConsumer = factory.getWebServiceConsumer(WebServiceEnum.SEND_MOBILE_AUTH_CODE);
		Assert.assertTrue(wsConsumer instanceof NoAuthenticationWebServiceConsumer);
	}
	
	@Test
	public void testRetrieveTransactionSummary() {
		WebServiceConsumerFactoryImpl factory = new WebServiceConsumerFactoryImpl("http://fake.com", wsClientService);
		WebServiceConsumer<?> wsConsumer = factory.getWebServiceConsumer(WebServiceEnum.RETRIEVE_TRANSACTION_SUMMARY_FOR_CUSTOMER);
		Assert.assertTrue(wsConsumer instanceof AuthenticationWebServiceConsumer);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullWebServiceEnum() {
		WebServiceConsumerFactoryImpl factory = new WebServiceConsumerFactoryImpl("http://fake.com", wsClientService);
		factory.getWebServiceConsumer(null);
	}
}
