package com.milktea.service.redeem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milktea.common.redemption.RedemptionPolicy;
import com.milktea.common.util.StringUtils;
import com.milktea.dao.ShopDao;
import com.milktea.entity.Shop;
import com.milktea.exception.InsufficientCreditToRedeemException;
import com.milktea.service.api.RedeemService;
import com.milktea.service.context.TransactionContext;
import com.milktea.service.redeem.factory.RedeemHandlerFactory;
import com.milktea.service.redeem.handler.RedeemHandler;

@Service
public class RedeemServiceImpl implements RedeemService {

	@Autowired
	private RedeemHandlerFactory redeemHandlerFactory;
	
	@Autowired
	private ShopDao shopDao;
	
	@Override
	public void redeem(String shopId, String customerId) throws InsufficientCreditToRedeemException{
		if (StringUtils.isEmpty(customerId)){
			throw new IllegalArgumentException("Customer Id cannot be empty: "+customerId);
		}
		RedemptionPolicy<?> policy = getRedemptionPolicy(shopId);
		RedeemHandler handler = redeemHandlerFactory.instance(policy.getTransactionType());
		TransactionContext redeemContext = createRedeemConext(shopId, customerId, policy.getRedemptionThreshold());
		handler.handle(redeemContext);
	}

	private RedemptionPolicy<?> getRedemptionPolicy(String shopId) {
		Shop shop = shopDao.findShopById(shopId);
		if (shop == null){
			throw new IllegalArgumentException("Shop Id is invalid: "+shopId);
		}
		return shop.getRedemptionPolicy();
	}

	private TransactionContext createRedeemConext(String shopId, String customerId, Object threshold) {
		return new TransactionContext(shopId, customerId, threshold);
	}

}
