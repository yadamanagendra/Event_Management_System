//package com.service;
//
//import java.util.List;
//
//import com.DAO.EventDAO;
//import com.model.Event;
//
//public class EventServiceImpl implements EventService{
//	private EventDAO eventDAO;
//	public EventServiceImpl(EventDAO eventDAO) {
//		this.eventDAO = eventDAO;
//	}
//
//	@Override
//	public boolean createEvent(Event event) {
//		if (event.getEventName()==null|| event.getEventName().isEmpty()) {
//			System.out.println("Event Name cannot be empty");
//			return false;
//		}
//		return eventDAO.addEvent(event);
//	}
//
//	@Override
//	public Event getEventById(int eventId) {
//		return eventDAO.getEventById(eventId);
//	}
//
//	@Override
//	public List<Event> getAllEvents() {
//		
//		return eventDAO.getAllEvents();
//	}
//
//	@Override
//	public boolean updateEvent(Event event) {
//		
//		return eventDAO.updateEvent(event);
//	}
//	@Override
//	public boolean deleteEvent(int eventId) {
//		return eventDAO.deleteEvent(eventId);
//	}
//}


package com.service;

import com.DAO.*;
import com.exception.EventManagementException;
import com.model.Event;
import com.service.EventService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.util.*;

public class EventServiceImpl implements EventService {
    private EventDAO eventDAO;
    public EventServiceImpl() {
    	Connection connection;
		try {
			connection = DBConnection.getConnection();
			this.eventDAO=new EventDAOImpl(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public boolean createEvent(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Event details cannot be null.");
        }
        if (event.getEventName() == null || event.getEventName().trim().isEmpty()) {
            throw new IllegalArgumentException("Event name cannot be empty.");
        }
        if (event.getEventDate() == null || event.getEventDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Event date cannot be in the past.");
        }
        if (event.getLocation() == null || event.getLocation().trim().isEmpty()) {
            throw new IllegalArgumentException("Event location cannot be empty.");
        }

        return eventDAO.addEvent(event);
    }

    @Override
    public Event getEventById(int eventId) {
        if (eventId <= 0) {
            throw new IllegalArgumentException("Event ID must be positive.");
        }
        return eventDAO.getEventById(eventId);
    }


    @Override
    public boolean updateEvent(Event event) {
        if (event == null || event.getEventId() <= 0) {
            throw new IllegalArgumentException("Invalid event details.");
        }
        return eventDAO.updateEvent(event);
    }

    @Override
    public boolean deleteEvent(int eventId) {
        if (eventId <= 0) {
            throw new IllegalArgumentException("Event ID must be positive.");
        }
        return eventDAO.deleteEvent(eventId);
    }

    @Override
    public List<Event> getBookedEventsByUser(int userId) {
        List<Event> bookedEvents = new ArrayList<>();
        String sql = "SELECT e.* FROM Events e " +
                     "INNER JOIN Booking b ON e.event_id = b.event_id " +
                     "WHERE b.user_id = ? AND e.event_date >= CURDATE()"
                     + "ORDER BY e.event_date ASC LIMIT 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Event event = new Event(
                    rs.getInt("event_id"),
                    rs.getString("event_name"),
                    rs.getDate("event_date").toLocalDate(),
                    rs.getString("event_time"),
                    rs.getString("location"),
                    rs.getInt("created_by"),   // Fetching created_by
                    rs.getString("description"),
                    rs.getString("status"),
                    rs.getString("eventscol"), // Matching eventCategory
                    rs.getString("image_path") // Matching imagePath
                );
                bookedEvents.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookedEvents;
    }

    @Override
    public List<Event> getAvailableEvents() {
        List<Event> availableEvents = new ArrayList<>();
        String sql = "SELECT * FROM Events WHERE event_date >= CURDATE()";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Event event = new Event(
                    rs.getInt("event_id"),
                    rs.getString("event_name"),
                    rs.getDate("event_date").toLocalDate(),
                    rs.getString("event_time"),
                    rs.getString("location"),
                    rs.getInt("created_by"),   // Fetching created_by
                    rs.getString("description"),
                    rs.getString("status"),
                    rs.getString("eventscol"), // Matching eventCategory
                    rs.getString("image_path") // Matching imagePath
                );
                availableEvents.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableEvents;
    }

    @Override
    public boolean updateEventStatus(int eventId, String status) {
        String sql = "UPDATE Events SET status = ? WHERE event_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, eventId);
            
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
