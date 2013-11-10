package com.erp.china.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TSales")
public class Sales {
	@Column(name="sales_id")
	private String salesId;

	@Column(name="sales_name")
	private String salesName;
 
	@Column(name="sales_email")
	private String salesEmail;
 
	@Column(name="sales_phone")
	private String salesPhone;

	@Column(name="sales_create_time")
	private java.util.Date createdDate;

	@Column(name="sales_last_modified_time")
	private java.util.Date lastModifiedDate;

	public String getSalesId() {
		return salesId;
	}

	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}

	public String getSalesName() {
		return salesName;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}

	public String getSalesEmail() {
		return salesEmail;
	}

	public void setSalesEmail(String salesEmail) {
		this.salesEmail = salesEmail;
	}

	public String getSalesPhone() {
		return salesPhone;
	}

	public void setSalesPhone(String salesPhone) {
		this.salesPhone = salesPhone;
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