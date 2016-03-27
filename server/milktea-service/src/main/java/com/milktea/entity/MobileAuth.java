package com.milktea.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import com.milktea.enums.UserType;

@Entity
@Table(name="T_MOBILE_AUTH")
public class MobileAuth {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="MOBILE")
	private String mobile;
	
	@Column(name="AUTH_CODE")
	private String authenticationCode;
	
	@Column(name="USER_TYPE")
	@Enumerated(EnumType.STRING)
	private UserType userType;
	
	@Column(name="CREATED_TIME", insertable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@Generated(GenerationTime.INSERT)
	private Date createdTime;

	public MobileAuth(String mobile, String authenticationCode, UserType userType) {
		super();
		this.mobile = mobile;
		this.authenticationCode = authenticationCode;
		this.userType = userType;
	}

	public MobileAuth(){
		
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public String getAuthenticationCode() {
		return authenticationCode;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	

}
