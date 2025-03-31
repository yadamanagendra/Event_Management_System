//package com.service;
//
//import java.util.Date;
//import java.util.List;
//
//import com.DAO.UserDAO;
//import com.DAO.UserDAOImpl;
//import com.model.User;
//
//public class UserServiceImpl implements UserService {
//
//	private UserDAO userDAO;
//	public UserServiceImpl(UserDAO userDAO) {
//		this.userDAO=userDAO;
//	}
//
//	@Override
//	public boolean registerUser(User user) {
//		if (user.getUsername()==null || user.getUsername().trim().isEmpty()) {
//			System.out.println("Username cannot be empty");
//			return false;
//		}
//		user.setRegistrationDate(new Date() );
//		user.setStatus("active");
//		return userDAO.registerUser(user);
//	}
//
//	@Override
//	public User getuserByUsername(String username) {
//		return userDAO.getUserByUsername(username);
//	}
//
//	@Override
//	public User getUserById(int userId) {
//		return userDAO.getUserById(userId);
//	}
//
//	@Override
//	public List<User> getAllUsers() {
//		return userDAO.getAllUsers();
//	}
//
//	@Override
//	public boolean updateUser(User user) {
//		return updateUser(user);
//	}
//
//	@Override
//	public boolean deleteUser(int userId) {
//		return userDAO.deleteUser(userId);
//	}
//	
//
//}

package com.service;

import com.DAO.UserDAO;
import com.DAO.UserDAOImpl;
import com.exception.EventManagementException;
import com.model.User;
import java.util.List;
import java.util.regex.Pattern;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean registerUser(User user) {
        if (user == null) {
            throw new EventManagementException("User details cannot be null.");
        }
        if (user.getFullname() == null || user.getFullname().trim().isEmpty()) {
            throw new EventManagementException("Full name cannot be empty.");
        }
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new EventManagementException("Username cannot be empty.");
        }
        if (!isValidEmail(user.getEmail())) {
            throw new EventManagementException("Invalid email format.");
        }
        if (!isValidMobile(String.valueOf(user.getMobile()))) {
            throw new EventManagementException("Invalid mobile number. It should be 10 digits.");
        }
        if (!isValidPassword(user.getPassword())) {
            throw new EventManagementException("Password must be at least 6 characters long.");
        }

        return userDAO.registerUser(user);
    }

    @Override
    public User validateUser(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            throw new EventManagementException("Username cannot be empty.");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new EventManagementException("Password cannot be empty.");
        }

        User user = userDAO.getUserByUsername(username);
        if (user == null) {
            throw new EventManagementException("User not found.");
        }
        if (!user.getPassword().equals(password)) {
            throw new EventManagementException("Invalid password.");
        }

        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new EventManagementException("Username cannot be empty.");
        }
        return userDAO.getUserByUsername(username);
    }

    @Override
    public User getUserById(int userId) {
        if (userId <= 0) {
            throw new EventManagementException("User ID must be positive.");
        }
        return userDAO.getUserById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public boolean updateUser(User user) {
        if (user == null || user.getUserId() <= 0) {
            throw new EventManagementException("Invalid user details.");
        }
        if (!isValidEmail(user.getEmail())) {
            throw new EventManagementException("Invalid email format.");
        }
        if (!isValidMobile(String.valueOf(user.getMobile()))) {
            throw new EventManagementException("Invalid mobile number. It should be 10 digits.");
        }
        return userDAO.updateUser(user);
    }

    @Override
    public boolean deleteUser(int userId) {
        if (userId <= 0) {
            throw new EventManagementException("User ID must be positive.");
        }
        return userDAO.deleteUser(userId);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && Pattern.matches(emailRegex, email);
    }

    private boolean isValidMobile(String mobile) {
        return mobile != null && mobile.matches("\\d{10}");
    }

    private boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }
}