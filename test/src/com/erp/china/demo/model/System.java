package com.erp.china.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TSystem")
public class System {
	@Column(name="system_id")
	private String systemId;
 
	@Column(name="system_version")
	private String systemVersion;

	@Column(name="system_last_modified_time")
	private java.util.Date lastModifiedDate;

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getSystemVersion() {
		return systemVersion;
	}

	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}

	public java.util.Date getLastModifiedDate() {
		if (lastModifiedDate == null) lastModifiedDate = new java.util.Date();
		return lastModifiedDate;
	}

	public void setLastModifiedDate(java.util.Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
}