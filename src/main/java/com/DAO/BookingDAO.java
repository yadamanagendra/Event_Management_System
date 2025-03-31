package com.DAO;

import java.util.List;

import com.model.Booking;

public interface BookingDAO {
	boolean addBooking(Booking booking);
	Booking getBookingById(int bookingId);
	List<Booking> getBookingsByUserId(int userId);
	List<Booking> getBookingsByEventId(int eventId);
	List<Booking> getAllBookings();
	boolean updateBookingStatus(int bookingId,String status);
	boolean cancelBooking(int bookingId);
	

}
