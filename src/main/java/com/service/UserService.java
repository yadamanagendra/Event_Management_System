package com.service;

import java.util.List;

import com.model.User;

public interface UserService {
	boolean registerUser(User user);
	User getUserByUsername(String username);
	User getUserById(int userId);
	List<User> getAllUsers();
	User validateUser(String username,String password);
	boolean updateUser(User user);
	boolean deleteUser(int userId);
}
