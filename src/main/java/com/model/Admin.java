package com.model;
import java.util.Date;

public class Admin {
	private int adminId;
	private int user_id;
	private Date admin_since;
	private String permissions;
	public Admin(int adminId, int user_id, Date admin_since, String permissions) {
		super();
		this.adminId = adminId;
		this.user_id = user_id;
		this.admin_since = admin_since;
		this.permissions = permissions;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Date getAdmin_since() {
		return admin_since;
	}
	public void setAdmin_since(Date admin_since) {
		this.admin_since = admin_since;
	}
	public String getPermissions() {
		return permissions;
	}
	public void setPermissions(String permisssions) {
		this.permissions = permissions;
	}
	
	

}
