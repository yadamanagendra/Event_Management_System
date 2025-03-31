package com.service;

import java.util.List;

import com.exception.EventManagementException;
import com.model.Admin;

public interface AdminService {
	boolean isAdmin(int userId) throws EventManagementException;
	boolean registerAdmin(Admin admin) throws EventManagementException;
	Admin getAdminById(int adminId) throws EventManagementException;
	boolean updateAdmin(Admin admin)throws EventManagementException;
	boolean deleteAdmin(int adminId)throws EventManagementException;
	List<Admin> getAllAdmins()throws EventManagementException;
	Admin validateAdmin(String username,String password) throws EventManagementException;
}
