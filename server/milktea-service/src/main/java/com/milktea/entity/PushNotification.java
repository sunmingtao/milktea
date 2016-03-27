package com.milktea.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
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

import com.milktea.enums.PushNotificationStatus;

@Entity
@Table(name="T_PUSH_NOTIFICATION")
public class PushNotification {
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="SHOP_ID")
	private String shopId;
	
	@Embedded
	private PushNotificationDestination destination;
	
	@Column(name="MESSAGE")
	private String message;
	
	@Column(name="STATUS")
	@Enumerated(EnumType.STRING)
	@Generated(GenerationTime.INSERT)
	private PushNotificationStatus status;
	
	@Column(name="CREATED_TIME", insertable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@Generated(GenerationTime.INSERT)
	private Date createdTime;

	@SuppressWarnings("unused")
	private PushNotification(){
		
	}
	
	public PushNotification(String shopId, String message, PushNotificationDestination destination) {
		super();
		this.destination = destination;
		this.message = message;
		this.shopId = shopId;
	}

	public long getId() {
		return id;
	}

	public PushNotificationDestination getDestination() {
		return destination;
	}

	public String getMessage() {
		return message;
	}

	public PushNotificationStatus getStatus() {
		return status;
	}

	public Date getCreatedTime() {
		return createdTime;
	}
	
}
