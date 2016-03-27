package com.milktea.service.api;

import java.util.List;

import com.milktea.entity.TransactionSummary;

public interface RetrieveTransactionSummaryService {
	List<TransactionSummary> getTransactionSummaryForCustomer(String customerId);
}
