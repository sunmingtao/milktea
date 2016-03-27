package com.milktea.mapper;

import com.milktea.common.dto.ShopDto;
import com.milktea.common.dto.retrievesummary.TransactionSummaryDto;
import com.milktea.entity.Shop;
import com.milktea.entity.TransactionSummary;

public class TransactionSummaryMapper {
	
	public static TransactionSummaryDto mapToDto(TransactionSummary entity){
		TransactionSummaryDto dto = new TransactionSummaryDto(); 
		dto.setTotalAmount(entity.getTotalAmount());
		dto.setTotalAmountRedeemed(entity.getTotalAmountRedeemed());
		dto.setTotalCups(entity.getTotalCups());
		dto.setTotalCupsRedeemed(entity.getTotalCupsRedeemed());
		dto.setShop(mapToShopDto(entity.getShop()));
		dto.setCustomerId(entity.getCustomer().getCustomerId());
		return dto;
	}
	
	private static ShopDto mapToShopDto(Shop entity){
		ShopDto dto = new ShopDto();
		dto.setAmountToRedeem(entity.getAmountToRedeem());
		dto.setNumberOfCupsToRedeem(entity.getNumberOfCupsToRedeem());
		dto.setShopId(entity.getShopId());
		dto.setShopName(entity.getShopName());
		return dto;
	}
}
