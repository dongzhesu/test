package com.erp.china.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TUser")
public class User {
	@Column(name="tuid")
	private String userId;

	@Column(name="system_id")
	private System system;

	@Column(name="user_login")
	private String userLogin;
 
	@Column(name="user_password")
	private String userPassword;
 
	@Column(name="user_language")
	private String userLanguage;

	@Column(name="user_create_time")
	private java.util.Date createdDate;

	@Column(name="user_last_modified_time")
	private java.util.Date lastModifiedDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public System getSystem() {
		return system;
	}

	public void setSystem(System system) {
		this.system = system;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserLanguage() {
		return userLanguage;
	}

	public void setUserLanguage(String userLanguage) {
		this.userLanguage = userLanguage;
	}

	public java.util.Date getCreatedDate() {
		if (createdDate == null) createdDate = new java.util.Date();
		return createdDate;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	public java.util.Date getLastModifiedDate() {
		if (lastModifiedDate == null) lastModifiedDate = new java.util.Date();
		return lastModifiedDate;
	}

	public void setLastModifiedDate(java.util.Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
}