package com.erp.china.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.erp.china.demo.model.Booking;
import com.erp.china.demo.model.BookingKey;
import com.erp.china.demo.model.Order;

public class DBBookingDAO extends AbstractDAO implements BookingDAO {
	private static SessionFactory sessionFactory;

	public DBBookingDAO() {
		logger = Logger.getLogger(DBBookingDAO.class);
		sessionFactory = configureSessionFactory();
	}

	private static SessionFactory configureSessionFactory() throws HibernateException {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	    return sessionFactory;
	}

	public void createBooking(Booking booking) {
		logger.info("creating new Booking");
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		session.save(booking);
		tx.commit();
		logger.info("succeed to create new Booking");
	}

	public List<Booking> bookingList() {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		List bookingList = session.createQuery("from Booking").list();
		if (bookingList == null) bookingList = new ArrayList();
		return bookingList;
	}

	public List<Booking> bookingList(Order order) {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		session.flush();
		session.clear();
		List bookingList = session.createQuery("from Booking where order_id = '"+order.getOrderId()+"'").list();
		if (bookingList == null) bookingList = new ArrayList();
		return bookingList;
	}

	public Booking loadBooking(BookingKey bookingKey) {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		Booking booking = (Booking) session.load(Booking.class, bookingKey);
		return booking;
	}

	public void updateBooking(Booking booking) {
		logger.info("modifying existing Booking");
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		Booking existingBooking = (Booking) session.load(Booking.class, booking.getBookingId());
		existingBooking.setBookingQty(booking.getBookingQty());
		existingBooking.setUnitPrice(booking.getUnitPrice());
		existingBooking.setDiscount(booking.getDiscount());
		existingBooking.setBookingPrice(booking.getBookingPrice());
		existingBooking.setBookingStatus(booking.getBookingStatus());
		existingBooking.setLastModifiedDate(new java.util.Date());
		session.saveOrUpdate(existingBooking);
		tx.commit();
		logger.info("succeed to modify existing Booking");
	}

	public void removeBooking(BookingKey bookingKey) {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		Booking booking = (Booking) session.load(Booking.class, bookingKey);
		if (booking != null) {
			sessionFactory.getCurrentSession().delete(booking);
		}
		tx.commit();
	}

	public boolean deleteBookingByOrderId(String orderId) {
		boolean isDeleted = true;
		try{
			Session session = getCurrentSession(sessionFactory);
			Transaction tx = session.beginTransaction();
			Query deleteQuery = session.createQuery("delete from Booking where order_id = '"+orderId+"'");
			int row = deleteQuery.executeUpdate();
			tx.commit();
		}catch (Exception e) {
			isDeleted = false;
		}
		return isDeleted;
	}
}