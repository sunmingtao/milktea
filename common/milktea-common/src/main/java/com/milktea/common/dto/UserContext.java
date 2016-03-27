package com.milktea.common.dto;

import com.milktea.common.util.StringUtils;

public final class UserContext {
	private String username;
	private String password;
	
	public UserContext(String username, String password) {
		super();
		if (StringUtils.isEmpty(username)){
			throw new IllegalArgumentException("Username must not be empty");
		}
		if (StringUtils.isEmpty(password)){
			throw new IllegalArgumentException("Password must not be empty");
		}
		this.username = username;
		this.password = password;
	}
	
	protected UserContext() {
		super();
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
}
