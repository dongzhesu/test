package com.erp.china.demo.model;

import java.io.Serializable;

import javax.persistence.Column;

public class BookingKey implements Serializable {
	@Column(name="order_id")
	private Order order;

	@Column(name="product_id")
	private Product product;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj != null && this.getClass() != obj.getClass()) return false;
		BookingKey bookingKey = (BookingKey) obj;
		boolean parameter1 = (this.order == bookingKey.order);
		boolean parameter2 = (this.product == bookingKey.product);
		return parameter1 && parameter2;
	}

	@Override
	public int hashCode() {
		int hash = 99;
		hash = 33*hash + this.order.hashCode();
		hash = 33*hash + this.product.hashCode();
		return hash;
	}
}