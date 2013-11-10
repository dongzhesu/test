package com.erp.china.demo.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.erp.china.demo.dao.DBBookingDAO;
import com.erp.china.demo.dao.BookingDAO;
import com.erp.china.demo.model.Booking;
import com.erp.china.demo.model.BookingKey;
import com.erp.china.demo.model.Order;

public class BookingService {
	private static Logger logger = Logger.getLogger(BookingService.class);
	private static BookingService instance = null;

	private static BookingDAO bookingDAO;

	public static synchronized BookingService getInstance() {
		if (instance == null) {
			instance = new BookingService();
		}
		return instance;
	}

	private BookingService() {
		bookingDAO = new DBBookingDAO();
	}

	@Transactional
	public void createBooking(Booking booking) {
		bookingDAO.createBooking(booking);
	}

	@Transactional
	public List<Booking> getBookingList() {
		return bookingDAO.bookingList();
	}

	@Transactional
	public List<Booking> getBookingList(Order order) {
		return bookingDAO.bookingList(order);
	}

	@Transactional
    public Booking loadBooking(BookingKey bookingKey) {
        return bookingDAO.loadBooking(bookingKey);
    }

	@Transactional
    public void updateBooking(Booking booking) {
		bookingDAO.updateBooking(booking);
    }

	@Transactional
	public void removeBooking(BookingKey bookingKey) {
		bookingDAO.removeBooking(bookingKey);
	}

	@Transactional
	public boolean deleteBookingByOrderId(String orderId) {
		return bookingDAO.deleteBookingByOrderId(orderId);
	}
}