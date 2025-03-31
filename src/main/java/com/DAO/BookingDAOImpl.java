//package com.DAO;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.model.Booking;
//import com.model.Event;
//
//public class BookingDAOImpl implements BookingDAO {
//	private static final String insert="INSERT INTO booking (event_id, user_id, booking_date, status) VALUES (?, ?, ?, ?)";
//	private static final String url="jdbc:mysql://localhost:3306/event_management?user=root&password=12345";
//	private static final String GET_BOOKING_BY_ID = "SELECT * FROM booking WHERE booking_id = ?";
//    private static final String GET_BOOKINGS_BY_USER = "SELECT * FROM booking WHERE user_id = ?";
//    private static final String GET_BOOKINGS_BY_EVENT = "SELECT * FROM booking WHERE event_id = ?";
//    private static final String GET_ALL_BOOKINGS = "SELECT * FROM booking";
//    private static final String Update_status = "UPDATE booking SET status = ? WHERE booking_id = ?";
//    private static final String CANCEL_BOOKING = "UPDATE booking SET status = 'cancelled' WHERE booking_id = ?";
//
//	@Override
//	public boolean addBooking(Booking booking) {
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection connection=DriverManager.getConnection(url);
//			PreparedStatement preparedStatement=connection.prepareStatement(insert);
//			preparedStatement.setInt(1, booking.getEventId());
//            preparedStatement.setInt(2, booking.getUserId());
//            preparedStatement.setDate(3, new java.sql.Date(booking.getBookingDate().getTime()));
//            preparedStatement.setString(4, booking.getStatus());
//			int res=preparedStatement.executeUpdate();
//			return res>0;
//			
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
//
//	@Override
//	public Booking getBookingById(int bookingId) {
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection connection=DriverManager.getConnection(url);
//			PreparedStatement preparedStatement=connection.prepareStatement(GET_BOOKING_BY_ID);
//			preparedStatement.setInt(1, bookingId);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                return new Booking(
//                        resultSet.getInt("booking_id"),
//                        resultSet.getInt("event_id"),
//                        resultSet.getInt("user_id"),
//                        resultSet.getDate("booking_date"),
//                        resultSet.getString("status")
//                );
//			} 
//		}catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@Override
//	public List<Booking> getBookingsByUserId(int userId) {
//		List<Booking> bookings = new ArrayList<>();
//		
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection connection=DriverManager.getConnection(url);
//			PreparedStatement preparedStatement = connection.prepareStatement(GET_BOOKINGS_BY_USER);
//
//	            preparedStatement.setInt(1, userId);
//	            ResultSet resultSet = preparedStatement.executeQuery();
//
//	            while (resultSet.next()) {
//	                bookings.add(new Booking(
//	                        resultSet.getInt("booking_id"),
//	                        resultSet.getInt("event_id"),
//	                        resultSet.getInt("user_id"),
//	                        resultSet.getDate("booking_date"),
//	                        resultSet.getString("status")
//	                ));
//	            }
//
//	        } catch (ClassNotFoundException | SQLException e) {
//	            e.printStackTrace();
//	        }
//	        return bookings;
//	}
//
//	@Override
//	public List<Booking> getBookingsByEventId(int eventId) {
//		 List<Booking> bookings = new ArrayList<>();
//		 try {
//		 Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection connection=DriverManager.getConnection(url);
//	             PreparedStatement preparedStatement = connection.prepareStatement(GET_BOOKINGS_BY_EVENT);
//
//	            preparedStatement.setInt(1, eventId);
//	            ResultSet resultSet = preparedStatement.executeQuery();
//
//	            while (resultSet.next()) {
//	                bookings.add(new Booking(
//	                        resultSet.getInt("booking_id"),
//	                        resultSet.getInt("event_id"),
//	                        resultSet.getInt("user_id"),
//	                        resultSet.getDate("booking_date"),
//	                        resultSet.getString("status")
//	                ));
//	            }
//
//	        } catch (ClassNotFoundException | SQLException e) {
//	            e.printStackTrace();
//	        }
//	        return bookings;
//	    }
//	@Override
//	public List<Booking> getAllBookings() {
//		List<Booking> bookings = new ArrayList<>();
//		 try {
//		 Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection connection=DriverManager.getConnection(url);
//	             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_BOOKINGS);
//	            ResultSet resultSet = preparedStatement.executeQuery();
//
//	            while (resultSet.next()) {
//	               Booking booking= new Booking(
//	                        resultSet.getInt("booking_id"),
//	                        resultSet.getInt("event_id"),
//	                        resultSet.getInt("user_id"),
//	                        resultSet.getDate("booking_date"),
//	                        resultSet.getString("status")
//	                );
//	             bookings.add(booking);
//	            }
//
//	        } catch (ClassNotFoundException | SQLException e) {
//	            e.printStackTrace();
//	        }
//	        return bookings;
//	}
//	@Override
//	public boolean updateBookingStatus(int bookingId, String status) {
//		try {
//		 	Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection connection=DriverManager.getConnection(url);
//             PreparedStatement preparedStatement = connection.prepareStatement(Update_status);
//             preparedStatement.setString(1, status);
//            preparedStatement.setInt(2, bookingId);
//            int result = preparedStatement.executeUpdate();
//            return result > 0;
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//	}
//	@Override
//	public boolean cancelBooking(int bookingId) {
//		 try {
//			 	Class.forName("com.mysql.cj.jdbc.Driver");
//				Connection connection=DriverManager.getConnection(url);
//	             PreparedStatement preparedStatement = connection.prepareStatement(CANCEL_BOOKING);
//
//	            preparedStatement.setInt(1, bookingId);
//	            int result = preparedStatement.executeUpdate();
//	            return result > 0;
//
//	        } catch (ClassNotFoundException | SQLException e) {
//	            e.printStackTrace();
//	        }
//	        return false;
//	    }
//
//	
//
//	
//	}


package com.DAO;

import com.DAO.BookingDAO;
import com.exception.EventManagementException;
import com.model.Booking;
import com.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAOImpl implements BookingDAO {
	
	 private Connection connection;
	    public BookingDAOImpl(Connection connection) {
	    	this.connection=connection;
	    }

    @Override
    public boolean addBooking(Booking booking) throws EventManagementException {
        String query = "INSERT INTO booking (event_id, user_id, booking_date, status) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, booking.getEventId());
            ps.setInt(2, booking.getUserId());
            ps.setDate(3, Date.valueOf(booking.getBookingDate()));
            ps.setString(4, booking.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new EventManagementException("Error adding booking: " + e.getMessage());
        }
    }

    @Override
    public Booking getBookingById(int bookingId) throws EventManagementException {
        String query = "SELECT * FROM booking WHERE booking_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, bookingId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Booking(rs.getInt("booking_id"), rs.getInt("event_id"), rs.getInt("user_id"),
                        rs.getDate("booking_date").toLocalDate(), rs.getString("status"));
            }
        } catch (SQLException e) {
            throw new EventManagementException("Error fetching booking: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Booking> getBookingsByUserId(int userId) throws EventManagementException {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM booking WHERE user_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bookings.add(new Booking(rs.getInt("booking_id"), rs.getInt("event_id"), rs.getInt("user_id"),
                        rs.getDate("booking_date").toLocalDate(), rs.getString("status")));
            }
        } catch (SQLException e) {
            throw new EventManagementException("Error fetching user bookings: " + e.getMessage());
        }
        return bookings;
    }

    @Override
    public List<Booking> getBookingsByEventId(int eventId) throws EventManagementException {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM booking WHERE event_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, eventId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bookings.add(new Booking(rs.getInt("booking_id"), rs.getInt("event_id"), rs.getInt("user_id"),
                        rs.getDate("booking_date").toLocalDate(), rs.getString("status")));
            }
        } catch (SQLException e) {
            throw new EventManagementException("Error fetching event bookings: " + e.getMessage());
        }
        return bookings;
    }

    @Override
    public List<Booking> getAllBookings() throws EventManagementException {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM booking";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                bookings.add(new Booking(rs.getInt("booking_id"), rs.getInt("event_id"), rs.getInt("user_id"),
                        rs.getDate("booking_date").toLocalDate(), rs.getString("status")));
            }
        } catch (SQLException e) {
            throw new EventManagementException("Error fetching all bookings: " + e.getMessage());
        }
        return bookings;
    }

    @Override
    public boolean updateBookingStatus(int bookingId, String status) throws EventManagementException {
        String query = "UPDATE booking SET status = ? WHERE booking_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, status);
            ps.setInt(2, bookingId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new EventManagementException("Error updating booking status: " + e.getMessage());
        }
    }

    @Override
    public boolean cancelBooking(int bookingId) throws EventManagementException {
        return updateBookingStatus(bookingId, "canceled");
    }
}