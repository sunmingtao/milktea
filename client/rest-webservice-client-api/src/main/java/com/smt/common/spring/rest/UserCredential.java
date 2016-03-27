package com.smt.common.spring.rest;

public class UserCredential {
	private final String username;
	private final String password;
	public UserCredential(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
}
