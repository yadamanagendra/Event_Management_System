package com.DAO;

import java.util.List;

import com.exception.EventManagementException;
import com.model.User;

public interface UserDAO {
	boolean registerUser(User user) throws EventManagementException;
	User getUserByUsername(String username) throws EventManagementException;
	User getUserById(int userId) throws EventManagementException;
	List<User> getAllUsers() throws EventManagementException;
	boolean updateUser(User user) throws EventManagementException;
	boolean deleteUser(int userId) throws EventManagementException;
	User validateUser(String username,String password)throws EventManagementException;
}
