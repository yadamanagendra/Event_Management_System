package com.service;

import java.util.List;

import com.model.Booking;

public interface BookingService {
	boolean bookEvent(Booking booking);
	Booking getBookingDetails(int bookingId);
	List<Booking> getBookingsByUserId(int userId);
	List<Booking> getBookingsByEventId(int eventId);
	List<Booking> getAllBookings();
	boolean updateBookingStaus(int bookingId,String status);
	boolean cancelBooking(int bookingId);

}
