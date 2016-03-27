package com.milktea.exception;

public class ReachSmsQuotaLimitException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReachSmsQuotaLimitException(String s) {
		super(s);
	}
}
