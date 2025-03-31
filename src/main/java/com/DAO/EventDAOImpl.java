//package com.DAO;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.exception.EventManagementException;
//import com.model.Admin;
//import com.model.Event;
//
//import java.sql.*;
//
//public class EventDAOImpl implements EventDAO{
//	private static final String url="jdbc:mysql://localhost:3306/event_management?user=root&password=12345";
//	private static final String insert="insert into events (event_name, event_date, event_time, location, created_by, description, status, eventscol)"
//			+ " values (?,?,?,?,?,?,?,?,?)";
//	private static final String select="select * from events where event_id=?";
//	private static final String select_upcoming_events="select * from events where event_date>=CURDATE() ORDER BY event_date ASC";
//	private static final String select_all="select * from events";
//	private static final String update="update events set event_name=?, event_date=?, event_time=?, location=?, created_by=?, description=?, "
//			+ "status=?, eventscol=? where event_id=?";
//	private static final String delete="delete from events where event_id=?";
//	
//	@Override
//	public boolean addEvent(Event event) {
//		
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection connection=DriverManager.getConnection(url);
//			PreparedStatement preparedStatement=connection.prepareStatement(insert);
//			preparedStatement.setString(1,event.getEventName());
//			preparedStatement.setDate(2, new java.sql.Date(event.getEventDate().getTime()));
//			preparedStatement.setString(3, event.getEventTime());
//			preparedStatement.setString(4, event.getLocation());
//			preparedStatement.setInt(5, event.getCreatedBy());
//			preparedStatement.setString(6, event.getDescription());
//			preparedStatement.setString(7, event.getStatus());
//			preparedStatement.setString(8, event.getEventCategory());
//			preparedStatement.setString(9,event.getImagePath());
//			
//			int res=preparedStatement.executeUpdate();
//			return res>0;
//			
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//	}
//
//	@Override
//	public Event getEventById(int eventId) {
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection connection=DriverManager.getConnection(url);
//			PreparedStatement preparedStatement=connection.prepareStatement(select);
//			preparedStatement.setInt(1,eventId);
//			ResultSet resultSet=preparedStatement.executeQuery();
//			if (resultSet.next()) {
//				return new Event(resultSet.getInt("event_id"),resultSet.getString("event_name"),resultSet.getDate("event_date"),resultSet.getString("event_time"),resultSet.getString("location"),resultSet.getInt("created_by"),resultSet.getString("description"),resultSet.getString("status"),resultSet.getString("eventscol"),resultSet.getString("image_path"));
//			}
//			
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	// Method to get all events
// 
//    public List<Event> getAllEvents() {
//    	List<Event> events = new ArrayList<>();
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection connection=DriverManager.getConnection(url);
//			PreparedStatement preparedStatement=connection.prepareStatement(select_all);
//			ResultSet resultSet=preparedStatement.executeQuery();
//	            while (resultSet.next()) {
//	                events.add(new Event(
//	                        resultSet.getInt("event_id"),
//	                        resultSet.getString("event_name"),
//	                        resultSet.getDate("event_date"),
//	                        resultSet.getString("event_time"),
//	                        resultSet.getString("location"),
//	                        resultSet.getInt("created_by"),
//	                        resultSet.getString("description"),
//	                        resultSet.getString("status"),
//	                        resultSet.getString("eventscol"),
//	                        resultSet.getString("image_path")
//	                ));
//	            }
//	        } catch (ClassNotFoundException | SQLException e) {
//	            e.printStackTrace();
//	        }
//	        return events;
//	}
//
//
//    // Method to update an event
//    
//    @Override
//    public boolean updateEvent(Event event) {
//    	try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection connection=DriverManager.getConnection(url);
//			PreparedStatement preparedStatement=connection.prepareStatement(update);
//			preparedStatement.setString(1,event.getEventName());
//			preparedStatement.setDate(2, new java.sql.Date(event.getEventDate().getTime()));
//			preparedStatement.setString(3, event.getEventTime());
//			preparedStatement.setString(4, event.getLocation());
//			preparedStatement.setInt(5, event.getCreatedBy());
//			preparedStatement.setString(6, event.getDescription());
//			preparedStatement.setString(7, event.getStatus());
//			preparedStatement.setString(8, event.getEventCategory());
//			preparedStatement.setInt(9, event.getEventId());
//			
//			int res=preparedStatement.executeUpdate();
//			return res>0;
//			
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//    }
//
//    // Method to delete an event
//
//	@Override
//	public boolean deleteEvent(int eventId) {
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection connection=DriverManager.getConnection(url);
//			PreparedStatement preparedStatement=connection.prepareStatement(delete);
//			preparedStatement.setInt(1, eventId);
//			int res=preparedStatement.executeUpdate();
//			return res>0;		
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//	}
//
//	@Override
//	public List<Event> getEventsByStatus(String status) throws EventManagementException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}


package com.DAO;

import com.DAO.EventDAO;
import com.exception.EventManagementException;
import com.model.Event;
import com.util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventDAOImpl implements EventDAO {

    private static final String INSERT_EVENT = "INSERT INTO events (event_name, event_date, event_time, location, created_by, description, status, eventscol, image_path) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_EVENT_BY_ID = "SELECT * FROM events WHERE event_id = ?";
    private static final String SELECT_ALL_EVENTS = "SELECT * FROM events";
    private static final String UPDATE_EVENT = "UPDATE events SET event_name=?, event_date=?, event_time=?, location=?, description=?, status=?, eventscol=?, image_path=? WHERE event_id=?";
    private static final String DELETE_EVENT = "DELETE FROM events WHERE event_id = ?";
    private static final String SELECT_EVENTS_BY_STATUS = "SELECT * FROM events WHERE status = ?";
    
    private Connection connection;
    public EventDAOImpl(Connection connection) {
    	this.connection=connection;
    }

    @Override
    public boolean addEvent(Event event) throws EventManagementException {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_EVENT, Statement.RETURN_GENERATED_KEYS)) {
             
            pstmt.setString(1, event.getEventName());
            pstmt.setDate(2, Date.valueOf(event.getEventDate())); // Convert LocalDate to SQL Date
            pstmt.setString(3, event.getEventTime());
            pstmt.setString(4, event.getLocation());
            pstmt.setInt(5, event.getCreatedBy());
            pstmt.setString(6, event.getDescription());
            pstmt.setString(7, event.getStatus());
            pstmt.setString(8, event.getEventCategory());
            pstmt.setString(9, event.getImagePath());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new EventManagementException("Error while adding event: " + e.getMessage());
        }
    }

    @Override
    public Event getEventById(int eventId) throws EventManagementException {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_EVENT_BY_ID)) {
             
            pstmt.setInt(1, eventId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Event(
                        rs.getInt("event_id"),
                        rs.getString("event_name"),
                        rs.getDate("event_date").toLocalDate(), // Convert SQL Date to LocalDate
                        rs.getString("event_time"),
                        rs.getString("location"),
                        rs.getInt("created_by"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getString("eventscol"),
                        rs.getString("imagePath")
                );
            }
        } catch (SQLException e) {
            throw new EventManagementException("Error fetching event by ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Event> getAllEvents() throws EventManagementException {
        List<Event> eventList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_EVENTS);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                eventList.add(new Event(
                        rs.getInt("event_id"),
                        rs.getString("event_name"),
                        rs.getDate("event_date").toLocalDate(),
                        rs.getString("event_time"),
                        rs.getString("location"),
                        rs.getInt("created_by"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getString("eventscol"),
                        rs.getString("imagePath")
                ));
            }
        } catch (SQLException e) {
            throw new EventManagementException("Error fetching all events: " + e.getMessage());
        }
        return eventList;
    }

    @Override
    public boolean updateEvent(Event event) throws EventManagementException {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_EVENT)) {
             
            pstmt.setString(1, event.getEventName());
            pstmt.setDate(2, Date.valueOf(event.getEventDate()));
            pstmt.setString(3, event.getEventTime());
            pstmt.setString(4, event.getLocation());
            pstmt.setString(5, event.getDescription());
            pstmt.setString(6, event.getStatus());
            pstmt.setString(7, event.getEventCategory());
            pstmt.setString(8, event.getImagePath());
            pstmt.setInt(9, event.getEventId());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            throw new EventManagementException("Error updating event: " + e.getMessage());
        }
    }

    @Override
    public boolean deleteEvent(int eventId) throws EventManagementException {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(DELETE_EVENT)) {
             
            pstmt.setInt(1, eventId);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new EventManagementException("Error deleting event: " + e.getMessage());
        }
    }

    @Override
    public List<Event> getEventsByStatus(String status) throws EventManagementException {
        List<Event> eventList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_EVENTS_BY_STATUS)) {
             
            pstmt.setString(1, status);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                eventList.add(new Event(
                        rs.getInt("event_id"),
                        rs.getString("event_name"),
                        rs.getDate("event_date").toLocalDate(),
                        rs.getString("event_time"),
                        rs.getString("location"),
                        rs.getInt("created_by"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getString("eventscol"),
                        rs.getString("image_path")
                ));
            }
        } catch (SQLException e) {
            throw new EventManagementException("Error fetching events by status: " + e.getMessage());
        }
        return eventList;
    }
}