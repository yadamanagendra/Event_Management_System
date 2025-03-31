package com.model;

import java.time.LocalDate;
import java.util.Date;

public class Booking {
	private int bookingId;
	private int eventId;
	private int userId;
	private LocalDate bookingDate;
	private String status;
	public Booking( int eventId, int userId, LocalDate bookingDate, String status) {
		this.eventId = eventId;
		this.userId = userId;
		this.bookingDate = bookingDate;
		this.status = status;
	}
	public Booking(int bookingId, int eventId, int userId, LocalDate bookingDate, String status) {
		this.bookingId = bookingId;
		this.eventId = eventId;
		this.userId = userId;
		this.bookingDate = bookingDate;
		this.status = status;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public LocalDate getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", eventId=" + eventId + ", userId=" + userId + ", bookingDate="
				+ bookingDate + ", status=" + status + "]";
	}
	
	

}
