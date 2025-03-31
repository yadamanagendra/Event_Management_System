package com.DAO;

import java.util.List;

import com.exception.EventManagementException;
import com.model.Event;

public interface EventDAO {
	boolean addEvent(Event event) throws EventManagementException;
	Event getEventById(int eventId) throws EventManagementException;
	List<Event> getAllEvents() throws EventManagementException ;
	boolean updateEvent(Event event) throws EventManagementException;
	boolean deleteEvent(int eventId) throws EventManagementException;
	List<Event> getEventsByStatus(String status) throws EventManagementException;
}

