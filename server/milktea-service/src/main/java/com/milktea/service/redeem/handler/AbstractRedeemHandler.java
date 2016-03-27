package com.milktea.service.redeem.handler;

import org.springframework.beans.factory.annotation.Autowired;

import com.milktea.dao.TransactionDetailDao;
import com.milktea.dao.TransactionSummaryDao;
import com.milktea.entity.TransactionDetail;
import com.milktea.entity.TransactionSummary;
import com.milktea.exception.InsufficientCreditToRedeemException;
import com.milktea.service.context.TransactionContext;

public abstract class AbstractRedeemHandler implements RedeemHandler {
	
	@Autowired
	protected TransactionDetailDao transactionDetailDao;
	
	@Autowired
	protected TransactionSummaryDao transactionSummaryDao;
	
	@Override
	public void handle(TransactionContext context) throws InsufficientCreditToRedeemException{
		TransactionSummary summary = createOrFindTransactionSummary(context);
		updateTransactionSummary(summary, context.getTransactionValue());
		if (isValid(summary)){
			transactionSummaryDao.saveOrUpdate(summary);
			transactionDetailDao.save(createTransactionDetailEntity(context));
		}else{
			throw new InsufficientCreditToRedeemException("Not enough credit to redeem");
		}
	}
	
	protected abstract boolean isValid(TransactionSummary summary);

	protected abstract void updateTransactionSummary(TransactionSummary summary, Object threshhold);


	protected abstract TransactionDetail createTransactionDetailEntity(
			TransactionContext context);

	private TransactionSummary createOrFindTransactionSummary(TransactionContext context) {
		TransactionSummary summary = transactionSummaryDao.findTransactionSummaryByShopIdCustomerId(
				context.getShopId(), context.getCustomerId());
		return summary;
	}

}
