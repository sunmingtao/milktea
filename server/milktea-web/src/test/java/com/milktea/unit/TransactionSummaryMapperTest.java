package com.milktea.unit;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.milktea.common.dto.ShopDto;
import com.milktea.common.dto.retrievesummary.TransactionSummaryDto;
import com.milktea.entity.Customer;
import com.milktea.entity.Shop;
import com.milktea.entity.TransactionSummary;
import com.milktea.mapper.TransactionSummaryMapper;

public class TransactionSummaryMapperTest {
	
	private static final String SHOP_ID = "s001";
	private static final String CUST_ID = "c001";
	private static final String SHOP_NAME = "SHOP001";
	
	@Test
	public void testFullFields(){
		Shop shop = new Shop(SHOP_ID, "Password");
		shop.setShopName(SHOP_NAME);
		shop.setAmountToRedeem(new BigDecimal("20.00"));
		shop.setNumberOfCupsToRedeem(5);
		Customer cusomter = new Customer(CUST_ID, "Password");
		TransactionSummary entity = new TransactionSummary(shop, cusomter);
		entity.addTotalCups(83);
		entity.addTotalCupsRedeemed(70);
		entity.addTotalAmount(new BigDecimal("55.6"));
		entity.addTotalAmountRedeemed(new BigDecimal("40.0"));
		TransactionSummaryDto dto = TransactionSummaryMapper.mapToDto(entity);
		Assert.assertTrue(dto.getTotalCups().compareTo(83)==0);
		Assert.assertTrue(dto.getTotalCupsRedeemed().compareTo(70)==0);
		Assert.assertTrue(dto.getTotalAmount().compareTo(new BigDecimal("55.6"))==0);
		Assert.assertTrue(dto.getTotalAmountRedeemed().compareTo(new BigDecimal("40.0"))==0);
		ShopDto shopDto = dto.getShop();
		Assert.assertEquals(SHOP_ID, shopDto.getShopId());
		Assert.assertEquals(SHOP_NAME, shopDto.getShopName());
		Assert.assertEquals(5, shopDto.getNumberOfCupsToRedeem().intValue());
		Assert.assertTrue(shopDto.getAmountToRedeem().compareTo(new BigDecimal("20.00"))==0);
		Assert.assertEquals(CUST_ID, dto.getCustomerId());
	}
	
}
