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
//import com.exception.EventManagementException;
//import com.model.Admin;
//
//public class AdminDAOImpl implements AdminDAO{
//	private static final String url="jdbc:mysql://localhost:3306/event_management?user=root&password=12345";
//	private static final String insert="insert into admin (user_id, admin_since, permisssions) values (?,?,?)";
//	private static final String select="select * from admin where admin_id=?";
//	@Override
//	public boolean registerAdmin(Admin admin) {
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection connection=DriverManager.getConnection(url);
//			PreparedStatement preparedStatement=connection.prepareStatement(insert);
//			preparedStatement.setInt(1,admin.getUser_id());
//			preparedStatement.setDate(2, new java.sql.Date(admin.getAdmin_since().getTime()));
//			preparedStatement.setString(3, admin.getPermisssions());
//			int res=preparedStatement.executeUpdate();
//			return res>0;
//			
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//	}
//
//	@Override
//	public Admin getAdminById(int adminId) {
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection connection=DriverManager.getConnection(url);
//			PreparedStatement preparedStatement=connection.prepareStatement(select);
//			preparedStatement.setInt(1,adminId);
//			ResultSet resultSet=preparedStatement.executeQuery();
//			if (resultSet.next()) {
//				return new Admin(resultSet.getInt("admin_id"),resultSet.getInt("user_id"),resultSet.getDate("admin_since"),resultSet.getString("permissions"));
//			}
//			
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@Override
//	public boolean updateAdmin(Admin admin) throws EventManagementException {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean deleteAdmin(int adminId) throws EventManagementException {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public List<Admin> getAllAdmins() throws EventManagementException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Admin validateAdmin(String username, String password) throws EventManagementException {
//		// TODO Auto-generated method stub
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

import com.DAO.AdminDAO;
import com.util.DBConnection;
import com.exception.EventManagementException;
import com.model.Admin;

public class AdminDAOImpl implements AdminDAO {

    private Connection conn;

    public AdminDAOImpl() throws EventManagementException {
        try {
			conn = DBConnection.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public boolean registerAdmin(Admin admin) throws EventManagementException {
        String query = "INSERT INTO Admin (user_id, admin_since, permissions) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, admin.getUser_id());
            ps.setDate(2, new java.sql.Date(admin.getAdmin_since().getTime()));
            ps.setString(3, admin.getPermissions());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            throw new EventManagementException("Error registering admin: " + e.getMessage());
        }
    }

    @Override
    public Admin getAdminById(int adminId) throws EventManagementException {
        String query = "SELECT * FROM Admin WHERE admin_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, adminId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Admin(rs.getInt("admin_id"), rs.getInt("user_id"),
                        rs.getDate("admin_since"), rs.getString("permissions"));
            }
        } catch (Exception e) {
            throw new EventManagementException("Error fetching admin details: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateAdmin(Admin admin) throws EventManagementException {
        String query = "UPDATE Admin SET permissions = ? WHERE admin_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, admin.getPermissions());
            ps.setInt(2, admin.getAdminId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            throw new EventManagementException("Error updating admin: " + e.getMessage());
        }
    }

    @Override
    public boolean deleteAdmin(int adminId) throws EventManagementException {
        String query = "DELETE FROM Admin WHERE admin_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, adminId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            throw new EventManagementException("Error deleting admin: " + e.getMessage());
        }
    }

    @Override
    public List<Admin> getAllAdmins() throws EventManagementException {
        List<Admin> adminList = new ArrayList<>();
        String query = "SELECT * FROM Admin";
        try (PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                adminList.add(new Admin(rs.getInt("admin_id"), rs.getInt("user_id"),
                        rs.getDate("admin_since"), rs.getString("permissions")));
            }
        } catch (Exception e) {
            throw new EventManagementException("Error retrieving admin list: " + e.getMessage());
        }
        return adminList;
    }

    @Override
    public Admin validateAdmin(String username, String password) throws EventManagementException {
        String query = "SELECT a.* FROM Admin a INNER JOIN User u ON a.user_id = u.user_id WHERE u.username = ? AND u.password = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Admin(rs.getInt("admin_id"), rs.getInt("user_id"),
                        rs.getDate("admin_since"), rs.getString("permissions"));
            }
        } catch (Exception e) {
            throw new EventManagementException("Error validating admin: " + e.getMessage());
        }
        return null;
    }

	@Override
	public boolean isAdmin(int userId) throws EventManagementException {
		String query = "SELECT count(*) FROM Admin WHERE user_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1,userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1)>0) {
                return true;
            }
        } catch (Exception e) {
            throw new EventManagementException("Error checking admin status: " + e.getMessage());
        }
        return false;
	}
}
