package com.milktea.exception;

public class InvalidAuthCodeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidAuthCodeException(String s) {
		super(s);
	}
}
