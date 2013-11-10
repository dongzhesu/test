package com.erp.china.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TStorage")
public class Storage {
	@Column(name="storage_id")
	private String storageId;

	@Column(name="storage_name")
	private String storageName;
 
	@Column(name="storage_desc")
	private String storageDesc;

	@Column(name="storage_create_time")
	private java.util.Date createdDate;

	@Column(name="storage_last_modified_time")
	private java.util.Date lastModifiedDate;

	public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public String getStorageName() {
		return storageName;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}

	public String getStorageDesc() {
		return storageDesc;
	}

	public void setStorageDesc(String storageDesc) {
		this.storageDesc = storageDesc;
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