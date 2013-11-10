package com.erp.china.demo.dao;
 
import java.util.List;
import com.erp.china.demo.model.Booking;
import com.erp.china.demo.model.BookingKey;
import com.erp.china.demo.model.Order;

public interface BookingDAO {
	public void createBooking(Booking booking);
	public List<Booking> bookingList();
	public List<Booking> bookingList(Order order);
	public Booking loadBooking(BookingKey bookingKey);
	public void updateBooking(Booking booking);
	public void removeBooking(BookingKey bookingKey);
	public boolean deleteBookingByOrderId(String orderId);
}