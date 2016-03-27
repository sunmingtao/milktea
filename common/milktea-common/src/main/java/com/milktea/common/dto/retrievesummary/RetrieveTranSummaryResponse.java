package com.milktea.common.dto.retrievesummary;

import java.util.ArrayList;
import java.util.List;

import com.milktea.common.dto.CommonResponse;

public class RetrieveTranSummaryResponse extends CommonResponse{
	private List<TransactionSummaryDto> transactionSummaryList = new ArrayList<TransactionSummaryDto>();
	
	public void addTransactionSummary(TransactionSummaryDto dto){
		transactionSummaryList.add(dto);
	}

	public List<TransactionSummaryDto> getTransactionSummaryList() {
		return transactionSummaryList;
	}
	
}
