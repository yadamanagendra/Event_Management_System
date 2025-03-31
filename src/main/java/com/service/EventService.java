package com.service;

import java.util.List;

import com.model.Event;

public interface EventService {
	boolean createEvent(Event event);
	Event getEventById(int eventId);
	List<Event> getAvailableEvents();
	List<Event> getBookedEventsByUser(int userId);
	boolean updateEvent(Event event);
	boolean deleteEvent(int eventId);
	boolean updateEventStatus(int eventId,String status);
}
