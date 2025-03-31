package com.model;

import java.util.Date;

public class User {
	private int userId;
	private String fullname;
	private String username;
	private String email;
	private long mobile;
	private String password;
	private Date registrationDate;
	private String status;
	
	public User() {}

	public User(int userId, String fullname, String username, String email, long mobile, String password,
			Date registrationDate, String status) {
		this.userId = userId;
		this.fullname = fullname;
		this.username = username;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.registrationDate = registrationDate;
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", fullname=" + fullname + ", username=" + username + ", email=" + email
				+ ", mobile=" + mobile + ", password=" + password + ", registrationDate=" + registrationDate
				+ ", status=" + status + "]";
	}
	
	
	}
