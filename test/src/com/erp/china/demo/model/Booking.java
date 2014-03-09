package com.erp.china.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TBooking")
public class Booking {
	private BookingKey bookingId;
 
	@Column(name="booking_qty")
	private int bookingQty;
 
	@Column(name="unit_price")
	private double unitPrice;

	@Column(name="discount")
	private double discount;

	@Column(name="booking_price")
	private double bookingPrice;

	@Column(name="booking_status")
	private String bookingStatus;

	@Column(name="booking_create_time")
	private java.util.Date createdDate;

	@Column(name="booking_last_modified_time")
	private java.util.Date lastModifiedDate;

	public BookingKey getBookingId() {
		return bookingId;
	}

	public void setBookingId(BookingKey bookingId) {
		this.bookingId = bookingId;
	}

	public int getBookingQty() {
		return bookingQty;
	}

	public void setBookingQty(int bookingQty) {
		this.bookingQty = bookingQty;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getBookingPrice() {
		return bookingPrice;
	}

	public void setBookingPrice(double bookingPrice) {
		this.bookingPrice = bookingPrice;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
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