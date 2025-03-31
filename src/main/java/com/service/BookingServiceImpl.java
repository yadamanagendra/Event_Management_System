//package com.service;
//
//import java.util.List;
//
//import com.DAO.BookingDAO;
//import com.model.Booking;
//
//public class BookingServiceImpl implements BookingService {
//	 private BookingDAO bookingDAO;
//
//	    // Constructor to initialize BookingDAO
//	    public BookingServiceImpl(BookingDAO bookingDAO) {
//	        this.bookingDAO = bookingDAO;
//	    }
//
//	@Override
//	public boolean bookEvent(Booking booking) {
//		if (booking.getUserId() <= 0 || booking.getEventId() <= 0) {
//            System.out.println("Invalid user or event ID.");
//            return false;
//        }
//        booking.setStatus("Confirmed");  // Setting default status
//        return bookingDAO.addBooking(booking);
//	}
//
//	@Override
//	public Booking getBookingDetails(int bookingId) {
//		return bookingDAO.getBookingById(bookingId);
//	}
//
//	@Override
//	public List<Booking> getBookingsByUserId(int userId) {
//		return bookingDAO.getBookingsByUserId(userId);
//	}
//
//	@Override
//	public List<Booking> getBookingsByEventId(int eventId) {
//		return bookingDAO.getBookingsByEventId(eventId);
//	}
//	@Override
//	public List<Booking> getAllBookings() {
//		return bookingDAO.getAllBookings();
//	}
//
//	@Override
//	public boolean cancelBooking(int bookingId) {
//		Booking booking = bookingDAO.getBookingById(bookingId);
//        if (booking != null && !booking.getStatus().equalsIgnoreCase("Cancelled")) {
//            booking.setStatus("Cancelled");
//            return bookingDAO.updateBookingStatus(bookingId,"Cancelled");
//        }
//        return false;
//	}
//
//	@Override
//	public boolean updateBookingStaus(int bookingId, String status) {
//		
//		return bookingDAO.updateBookingStatus(bookingId, status);
//	}
//}


package com.service;

import com.DAO.*;
import com.model.Booking;
import com.service.BookingService;
import com.util.DBConnection;

import java.net.CookieHandler;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookingServiceImpl implements BookingService {

    private BookingDAO bookingDAO;
    public BookingServiceImpl() {
    	Connection connection;
    	try {
			connection=DBConnection.getConnection();
			this.bookingDAO=new BookingDAOImpl(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    }

    @Override
    public boolean bookEvent(Booking booking) {
        if (booking == null) {
            throw new IllegalArgumentException("Booking details cannot be null.");
        }
        if (booking.getEventId() <= 0 || booking.getUserId() <= 0) {
            throw new IllegalArgumentException("Invalid event or user ID.");
        }

        return bookingDAO.addBooking(booking);
    }

    @Override
    public Booking getBookingDetails(int bookingId) {
        if (bookingId <= 0) {
            throw new IllegalArgumentException("Booking ID must be positive.");
        }
        return bookingDAO.getBookingById(bookingId);
    }

    @Override
    public List<Booking> getBookingsByUserId(int userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("User ID must be positive.");
        }
        return bookingDAO.getBookingsByUserId(userId);
    }

    @Override
    public List<Booking> getBookingsByEventId(int eventId) {
        if (eventId <= 0) {
            throw new IllegalArgumentException("Event ID must be positive.");
        }
        return bookingDAO.getBookingsByEventId(eventId);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingDAO.getAllBookings();
    }

    @Override
    public boolean updateBookingStaus(int bookingId, String status) {
        if (bookingId <= 0 || status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid booking ID or status.");
        }
        return bookingDAO.updateBookingStatus(bookingId, status);
    }

    @Override
    public boolean cancelBooking(int bookingId) {
        if (bookingId <= 0) {
            throw new IllegalArgumentException("Booking ID must be positive.");
        }
        return bookingDAO.cancelBooking(bookingId);
    }
}