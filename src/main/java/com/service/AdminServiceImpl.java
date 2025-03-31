//package com.service;
//
//import com.DAO.AdminDAO;
//import com.model.Admin;
//
//public class AdminServiceImpl implements AdminService
//{
//	private AdminDAO adminDAO;
//	public AdminServiceImpl(AdminDAO adminDAO) {
//		this.adminDAO=adminDAO;
//	}
//	@Override
//	public boolean registerAdmin(Admin admin) {
//		return adminDAO.registerAdmin(admin);
//	}
//
//	@Override
//	public Admin getAdminById(int adminId) {
//		return adminDAO.getAdminById(adminId);
//	}
//
//}

package com.service;

import com.DAO.AdminDAO;
import com.DAO.AdminDAOImpl;
import com.exception.EventManagementException;
import com.model.Admin;
import com.service.AdminService;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    private AdminDAO adminDAO;

    public AdminServiceImpl() {
        this.adminDAO = new AdminDAOImpl();
    }

    @Override
    public boolean registerAdmin(Admin admin) {
        if (admin.getUser_id() <= 0 || admin.getAdmin_since() == null || admin.getPermissions().isEmpty()) {
            return false; // Basic validation
        }
        return adminDAO.registerAdmin(admin);
    }

    @Override
    public Admin getAdminById(int adminId) {
        return adminId > 0 ? adminDAO.getAdminById(adminId) : null;
    }

    @Override
    public boolean updateAdmin(Admin admin) {
        if (admin.getAdminId() <= 0 || admin.getUser_id() <= 0 || admin.getAdmin_since() == null || admin.getPermissions().isEmpty()) {
            return false;
        }
        return adminDAO.updateAdmin(admin);
    }

    @Override
    public boolean deleteAdmin(int adminId) {
        return adminId > 0 && adminDAO.deleteAdmin(adminId);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminDAO.getAllAdmins();
    }

	@Override
	public boolean isAdmin(int userId) throws EventManagementException {
		// TODO Auto-generated method stub
		return adminDAO.isAdmin(userId);
	}

	@Override
	public Admin validateAdmin(String username, String password) throws EventManagementException {
		// TODO Auto-generated method stub
		return adminDAO.validateAdmin(username, password);
	}
}