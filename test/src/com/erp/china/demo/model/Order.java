package com.erp.china.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TOrder")
public class Order {
	@Column(name="order_id")
	private String orderId;

	@Column(name="customer_id")
	private Customer customer;

	@Column(name="order_number")
	private String orderNumber;

	@Column(name="order_price")
	private double orderPrice;

	@Column(name="order_date")
	private java.util.Date orderDate;

	@Column(name="order_create_time")
	private java.util.Date createdDate;

	@Column(name="order_last_modified_time")
	private java.util.Date lastModifiedDate;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public java.util.Date getOrderDate() {
		if (orderDate == null) orderDate = new java.util.Date();
		return orderDate;
	}

	public void setOrderDate(java.util.Date orderDate) {
		this.orderDate = orderDate;
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