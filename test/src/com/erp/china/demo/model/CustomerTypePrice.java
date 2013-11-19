package com.erp.china.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TCustomerTypePrice")
public class CustomerTypePrice {
	@Column(name="type_id")
	private String typeId;

	@Column(name="customer_type")
	private Lookup customerType;

	@Column(name="product_id")
	private Product product;

	@Column(name="product_price")
	private double productPrice;

	@Column(name="type_create_time")
	private java.util.Date createdDate;

	@Column(name="type_last_modified_time")
	private java.util.Date lastModifiedDate;

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public Lookup getCustomerType() {
		return customerType;
	}

	public void setCustomerType(Lookup customerType) {
		this.customerType = customerType;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
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