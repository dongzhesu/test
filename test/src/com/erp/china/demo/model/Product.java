package com.erp.china.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TProduct")
public class Product {
	@Column(name="product_id")
	private String productId;

	@Column(name="product_name")
	private String productName;
 
	@Column(name="product_desc")
	private String productDesc;
 
	@Column(name="product_year")
	private String productYear;

	@Column(name="product_price")
	private double productPrice;

	@Column(name="product_price_2")
	private double productPrice2;

	@Column(name="product_qty")
	private int productQty;

	@Column(name="product_create_time")
	private java.util.Date createdDate;

	@Column(name="product_last_modified_time")
	private java.util.Date lastModifiedDate;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getProductYear() {
		return productYear;
	}

	public void setProductYear(String productYear) {
		this.productYear = productYear;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public double getProductPrice2() {
		return productPrice2;
	}

	public void setProductPrice2(double productPrice2) {
		this.productPrice2 = productPrice2;
	}

	public int getProductQty() {
		return productQty;
	}

	public void setProductQty(int productQty) {
		this.productQty = productQty;
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