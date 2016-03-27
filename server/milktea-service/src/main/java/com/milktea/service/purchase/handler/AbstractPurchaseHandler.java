package com.milktea.service.purchase.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.milktea.dao.CustomerDao;
import com.milktea.dao.ShopDao;
import com.milktea.dao.TransactionDetailDao;
import com.milktea.dao.TransactionSummaryDao;
import com.milktea.entity.Customer;
import com.milktea.entity.Shop;
import com.milktea.entity.TransactionDetail;
import com.milktea.entity.TransactionSummary;
import com.milktea.service.context.TransactionContext;

public abstract class AbstractPurchaseHandler implements PurchaseHandler {
	
	@Autowired
	protected TransactionDetailDao transactionDetailDao;
	
	@Autowired
	protected TransactionSummaryDao transactionSummaryDao;
	
	@Autowired
	protected ShopDao shopDao;
	
	@Autowired
	protected CustomerDao customerDao;
	
	@Override
	@Transactional
	public void handle(TransactionContext context) {
		saveTransactionDetail(context);
		saveOrUpdateTransactionSummary(context);
	}

	private void saveTransactionDetail(TransactionContext context) {
		TransactionDetail transactionDetail 
			= new TransactionDetail(context.getShopId(), context.getCustomerId());
		updatePurchaseValue(transactionDetail, context.getTransactionValue());
		transactionDetailDao.save(transactionDetail);
	}
	
	private void saveOrUpdateTransactionSummary(TransactionContext context) {
		Shop shop = retrieveShopEntity(context.getShopId());
		Customer customer = retrieveOrSaveCustomerEntity(context.getCustomerId());
		TransactionSummary summary = retrieveOrCreateTransactionSummaryEntity(shop, customer);		
		updateTotalPurchaseValue(summary, context.getTransactionValue());
		transactionSummaryDao.saveOrUpdate(summary);
	}

	private Shop retrieveShopEntity(String shopId) {
		return shopDao.findShopById(shopId);
	}
	
	private Customer retrieveOrSaveCustomerEntity(String customerId){
		Customer customer = customerDao.findCustomer(customerId);
		if (customer == null){
			customer = new Customer(customerId);
			customerDao.save(customer);
		}
		return customer;
	}

	private TransactionSummary retrieveOrCreateTransactionSummaryEntity(Shop shop, Customer customer) {
		TransactionSummary summary 
			= transactionSummaryDao.findTransactionSummaryByShopIdCustomerId(shop.getShopId(), customer.getCustomerId());
		if (summary == null){
			summary = new TransactionSummary(shop, customer);
		}
		return summary;
	}

	protected abstract void updateTotalPurchaseValue(TransactionSummary summary, Object purchaseValue);
	
	protected abstract void updatePurchaseValue(TransactionDetail transactionDetail, Object purchaseValue);
}
