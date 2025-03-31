//package com.DAO;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//import org.apache.naming.java.javaURLContextFactory;
//
//import com.model.Event;
//import com.model.User;
//
//public class UserDAOImpl implements UserDAO {
//	
//	private static final String insert="insert into user (full_name, username, email, mobile, password, registration_date, status)"
//			+ " values (?,?,?,?,?,?,?)";
//	private static final String url="jdbc:mysql://localhost:3306/event_management?user=root&password=12345";
//	private static final String SELECT_USER= "select * from user where username=? and password=?";
//	@Override
//	public boolean registerUser(User user) {
//		
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection connection=DriverManager.getConnection(url);
//			PreparedStatement preparedStatement=connection.prepareStatement(insert);
//			preparedStatement.setString(1,user.getFullname());
//			preparedStatement.setString(2, user.getUsername());
//			preparedStatement.setString(3, user.getEmail());
//			preparedStatement.setLong(4, user.getMobile());
//			preparedStatement.setString(5, user.getPassword());
//			preparedStatement.setDate(6, new java.sql.Date(user.getRegistrationDate().getTime()));
//			preparedStatement.setString(7,user.getStatus());
//			int res=preparedStatement.executeUpdate();
//			return res!=0;
//			
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//	}
//	
//
//	@Override
//	public User getUserByUsername(String username) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public User getUserById(int userId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public List<User> getAllUsers() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public boolean updateUser(User user) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//
//	@Override
//	public boolean deleteUser(int userId) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//
//	@Override
//	public User validateUser(String username, String password) {
//
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection connection=DriverManager.getConnection(url);
//			PreparedStatement preparedStatement=connection.prepareStatement(SELECT_USER);
//
//			preparedStatement.setString(1, username);
//			preparedStatement.setString(2,password);
//			
//			ResultSet resultSet=preparedStatement.executeQuery();
//			if (resultSet.next()) {
//				return new User(resultSet.getInt("user_id"),resultSet.getString("fullname"),resultSet.getString("username"),resultSet.getString("email"),resultSet.getLong("mobile"),resultSet.getString("password"),resultSet.getDate("registration_date"),resultSet.getString("status"));
//			}
//		
//			
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//}

package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DAO.UserDAO;
import com.util.DBConnection;
import com.exception.EventManagementException;
import com.model.User;

public class UserDAOImpl implements UserDAO {

    private Connection conn;

    public UserDAOImpl() throws EventManagementException {
        try {
			conn = DBConnection.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public boolean registerUser(User user) throws EventManagementException {
        String query = "INSERT INTO User (full_name, username, email, mobile, password, registration_date, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, user.getFullname());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getEmail());
            ps.setLong(4, user.getMobile());
            ps.setString(5, user.getPassword());
            ps.setDate(6, new java.sql.Date(user.getRegistrationDate().getTime()));
            ps.setString(7, user.getStatus());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            throw new EventManagementException("Error registering user: " + e.getMessage());
        }
    }

    @Override
    public User getUserByUsername(String username) throws EventManagementException {
        String query = "SELECT * FROM User WHERE username = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("user_id"), rs.getString("full_name"), rs.getString("username"),
                        rs.getString("email"), rs.getLong("mobile"), rs.getString("password"),
                        rs.getDate("registration_date"), rs.getString("status"));
            }
        } catch (Exception e) {
            throw new EventManagementException("Error fetching user by username: " + e.getMessage());
        }
        return null;
    }

    @Override
    public User getUserById(int userId) throws EventManagementException {
        String query = "SELECT * FROM User WHERE user_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("user_id"), rs.getString("full_name"), rs.getString("username"),
                        rs.getString("email"), rs.getLong("mobile"), rs.getString("password"),
                        rs.getDate("registration_date"), rs.getString("status"));
            }
        } catch (Exception e) {
            throw new EventManagementException("Error fetching user by ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() throws EventManagementException {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM User";
        try (PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                userList.add(new User(rs.getInt("user_id"), rs.getString("full_name"), rs.getString("username"),
                        rs.getString("email"), rs.getLong("mobile"), rs.getString("password"),
                        rs.getDate("registration_date"), rs.getString("status")));
            }
        } catch (Exception e) {
            throw new EventManagementException("Error retrieving users: " + e.getMessage());
        }
        return userList;
    }

    @Override
    public boolean updateUser(User user) throws EventManagementException {
        String query = "UPDATE User SET full_name = ?, email = ?, mobile = ?, password = ?, status = ? WHERE user_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, user.getFullname());
            ps.setString(2, user.getEmail());
            ps.setLong(3, user.getMobile());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getStatus());
            ps.setInt(6, user.getUserId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            throw new EventManagementException("Error updating user: " + e.getMessage());
        }
    }

    @Override
    public boolean deleteUser(int userId) throws EventManagementException {
        String query = "DELETE FROM User WHERE user_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            throw new EventManagementException("Error deleting user: " + e.getMessage());
        }
    }

    @Override
    public User validateUser(String username, String password) throws EventManagementException {
        String query = "SELECT * FROM User WHERE username = ? AND password = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("user_id"), rs.getString("full_name"), rs.getString("username"),
                        rs.getString("email"), rs.getLong("mobile"), rs.getString("password"),
                        rs.getDate("registration_date"), rs.getString("status"));
            }
        } catch (Exception e) {
            throw new EventManagementException("Error validating user: " + e.getMessage());
        }
        return null;
    }
}
