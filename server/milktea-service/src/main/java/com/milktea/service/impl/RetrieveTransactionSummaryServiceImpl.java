package com.milktea.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.milktea.dao.TransactionSummaryDao;
import com.milktea.entity.TransactionSummary;
import com.milktea.service.api.RetrieveTransactionSummaryService;

@Service
public class RetrieveTransactionSummaryServiceImpl implements RetrieveTransactionSummaryService {

	@Autowired
	private TransactionSummaryDao transactionSummaryDao;
	
	@Override
	public List<TransactionSummary> getTransactionSummaryForCustomer(String customerId) {
		return transactionSummaryDao.findTransactionSummaryByCustomerId(customerId);
	}
}
