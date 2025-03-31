package com.controller;

import com.model.User;
import com.service.UserService;
import com.service.UserServiceImpl;
import com.exception.EventManagementException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "register":
                    registerUser(request, response);
                    break;
                case "login":
                    loginUser(request, response);
                    break;
                case "logout":
                    logoutUser(request, response);
                    break;
                default:
                    throw new EventManagementException("Invalid action");
            }
        } catch (EventManagementException e) {
            redirectWithError(request, response, "error.jsp", e.getMessage());
        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullname = request.getParameter("fullname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String mobilestr = request.getParameter("mobile");
        String password = request.getParameter("password");

        try {
            if (fullname.isEmpty() || username.isEmpty() || email.isEmpty() || mobilestr.isEmpty() || password.isEmpty()) {
                throw new EventManagementException("All fields are required.");
            }

            long mobile = Long.parseLong(mobilestr);
            Date registrationDate = new Date();
            
            User user = new User(0, fullname, username, email, mobile, password, registrationDate, "active");  

            boolean isRegistered = userService.registerUser(user);

            if (isRegistered) {
                response.sendRedirect("success.jsp?message=Registration Successful");
            } else {
                throw new EventManagementException("Registration failed. Try again.");
            }
        } catch (NumberFormatException e) {
            redirectWithError(request, response, "registration.jsp", "Invalid mobile number format.");
        } catch (EventManagementException e) {
            redirectWithError(request, response, "registration.jsp", e.getMessage());
        }
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            if (username.isEmpty() || password.isEmpty()) {
                throw new EventManagementException("Username and Password cannot be empty.");
            }

            User user = userService.validateUser(username, password);

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("loggedInUser", user);
                session.setAttribute("userId", user.getUserId());
                session.setAttribute("username", user.getUsername());
                session.setAttribute("email", user.getEmail());

                response.sendRedirect("userDashboard.jsp");
            } else {
                redirectWithError(request, response, "login.jsp", "Invalid username or password.");
            }
        } catch (EventManagementException e) {
            redirectWithError(request, response, "login.jsp", e.getMessage());
        }
    }

    private void logoutUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login.jsp?message=Logged out successfully");
    }

    private void redirectWithError(HttpServletRequest request, HttpServletResponse response, String page, String message) throws ServletException, IOException {
        request.setAttribute("errorMessage", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}