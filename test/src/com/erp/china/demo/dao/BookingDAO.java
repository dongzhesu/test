package com.erp.china.demo.dao;
 
import java.util.List;
import com.erp.china.demo.model.Booking;
import com.erp.china.demo.model.BookingKey;

public interface BookingDAO {
	public void createBooking(Booking booking);
	public List<Booking> bookingList();
	public Booking loadBooking(BookingKey bookingKey);
	public void updateBooking(Booking booking);
	public void removeBooking(BookingKey bookingKey);
	public boolean deleteBookingByOrderId(String orderId);
}