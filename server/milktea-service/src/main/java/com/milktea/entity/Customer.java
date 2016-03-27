package com.milktea.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.milktea.common.util.StringUtils;

@Entity
@Table(name="T_CUSTOMER")
public class Customer implements Serializable{
	
	private static final long serialVersionUID = -8916811049095642509L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="CUSTOMER_ID")
	private String customerId;

	@Column(name="PASSWORD")
	private String password;

	@Embedded
	private PushNotificationDestination destination = new PushNotificationDestination();
	
	public Customer(String customerId, String password) {
		this.customerId = customerId;
		this.password = password;
	}
	
	public Customer(String customerId) {
		this.customerId = customerId;
	}
	
	@SuppressWarnings("unused")
	private Customer() {
	}
	
	public boolean hasPushNotificationDestination(){
		return destination != null && hasEitherChanneldOrUserId(); 
	}

	private boolean hasEitherChanneldOrUserId() {
		return !StringUtils.isEmpty(destination.getUserId()) 
				|| !StringUtils.isEmpty(destination.getChannelId());
	}

	public PushNotificationDestination getDestination() {
		return destination;
	}
	
	public void setDestination(PushNotificationDestination destination) {
		this.destination = destination;
	}

	public String getCustomerId() {
		return customerId;
	}
	public String getPassword() {
		return password;
	}
	public String getUserId() {
		return destination.getUserId();
	}
	public String getChannelId() {
		return destination.getChannelId();
	}
	public void copyProperties(Customer customer) {
		password = customer.getPassword();
		destination = customer.getDestination();
	}

}
