package com.controller;

import com.model.Admin;
import com.service.AdminService;
import com.service.AdminServiceImpl;
import com.exception.EventManagementException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdminService adminService;

    @Override
    public void init() throws ServletException {
        adminService = new AdminServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("login".equals(action)) {
                loginAdmin(request, response);
            } else if ("logout".equals(action)) {
                logoutAdmin(request, response);
            } else {
                throw new EventManagementException("Invalid action.");
            }
        } catch (EventManagementException e) {
            redirectWithError(request, response, "adminLogin.jsp", e.getMessage());
        }
    }

    private void loginAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
                throw new EventManagementException("Username and password are required.");
            }

            Admin admin = adminService.validateAdmin(username, password);

            if (admin != null) {
                HttpSession session = request.getSession();
                session.setAttribute("loggedInAdmin", admin);
                session.setAttribute("adminId", admin.getAdminId());

                response.sendRedirect("adminDashboard.jsp");
            } else {
                redirectWithError(request, response, "adminLogin.jsp", "Invalid username or password.");
            }
        } catch (EventManagementException e) {
            redirectWithError(request, response, "adminLogin.jsp", e.getMessage());
        }
    }

    private void logoutAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("adminLogin.jsp?message=Logged out successfully");
    }

    private void redirectWithError(HttpServletRequest request, HttpServletResponse response, String page, String message) throws ServletException, IOException {
        request.setAttribute("errorMessage", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}