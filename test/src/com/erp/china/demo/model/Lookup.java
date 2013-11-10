package com.erp.china.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TLookup")
public class Lookup {
	@Column(name="lookup_id")
	private String lookupId;

	@Column(name="system_id")
	private System system;
 
	@Column(name="lookup_key")
	private String lookupKey;

	@Column(name="lookup_value")
	private String lookupValue;

	public String getLookupId() {
		return lookupId;
	}

	public void setLookupId(String lookupId) {
		this.lookupId = lookupId;
	}

	public System getSystem() {
		return system;
	}

	public void setSystem(System system) {
		this.system = system;
	}

	public String getLookupKey() {
		return lookupKey;
	}

	public void setLookupKey(String lookupKey) {
		this.lookupKey = lookupKey;
	}

	public String getLookupValue() {
		return lookupValue;
	}

	public void setLookupValue(String lookupValue) {
		this.lookupValue = lookupValue;
	}
}