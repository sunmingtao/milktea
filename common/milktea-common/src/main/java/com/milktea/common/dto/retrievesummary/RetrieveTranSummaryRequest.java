package com.milktea.common.dto.retrievesummary;

import com.milktea.common.dto.CommonRequest;
import com.milktea.common.dto.UserContext;

public class RetrieveTranSummaryRequest extends CommonRequest{
	public RetrieveTranSummaryRequest(UserContext userContext){
		super(userContext);
	}
	
	/**
	 * Used for constructing the object by json library
	 */	
	@SuppressWarnings("unused")
	private RetrieveTranSummaryRequest(){
	}
}
