package com.milktea.service.registershop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milktea.common.dto.UserContext;
import com.milktea.common.dto.registershop.RegisterShopRequest;
import com.milktea.dao.MobileAuthDao;
import com.milktea.dao.ShopDao;
import com.milktea.entity.MobileAuth;
import com.milktea.entity.Shop;
import com.milktea.exception.InvalidAuthCodeException;
import com.milktea.exception.ShopExistsException;
import com.milktea.service.api.RegisterShopService;
import com.milktea.service.registershop.helper.RedemptionThresholdHelper;
import com.milktea.util.MobileAuthUtils;

@Service
public class RegisterShopServiceImpl implements RegisterShopService {

	@Autowired
	private ShopDao shopDao;
	
	@Autowired
	private MobileAuthDao mobileAuthDao;
	
	@Override
	public void registerShopWithoutAuthCode(RegisterShopRequest request) throws ShopExistsException {
		UserContext userContext = request.getUserContext();
		String shopId = userContext.getUsername();
		String shopName = request.getShopName();
		Shop shop = createShop(request);
		if (shopDao.existsShopName(shopName)){
			throw new ShopExistsException(shopName + " exists");
		}
		if (shopDao.existsShopId(shopId)){
			updateShop(shop);
		}else {
			saveShop(shop);
		}
	}
	
	@Override
	public void registerShopWithAuthCode(RegisterShopRequest request) throws ShopExistsException, InvalidAuthCodeException {
		String shopId = request.getUserContext().getUsername();
		List<MobileAuth> list = mobileAuthDao.findShopAuthCodes(shopId);
		if (MobileAuthUtils.hasFoundCorrectAuthCode(list, request.getAuthCode())) {
			registerShopWithoutAuthCode(request);
		}else{
			throw new InvalidAuthCodeException("Authentication code: " + request.getAuthCode() 
					+ " is incorrect");	
		}
	}
	
	private void saveShop(Shop shop) {
		shopDao.save(shop);
	}

	private void updateShop(final Shop shop) {
		Shop dbShop = shopDao.findShopById(shop.getShopId());
		dbShop.copyProperties(shop);
		shopDao.update(dbShop);
	}

	private Shop createShop(RegisterShopRequest request) {
		Shop shop = new Shop(request.getUserContext().getUsername(), request.getUserContext().getPassword());
		shop.setShopName(request.getShopName());
		//TODO Refactor
		RedemptionThresholdHelper.updateRedemptionThreshold(shop, request.getThreshold(), request.getRedeemType());
		return shop;
	}
	
}
