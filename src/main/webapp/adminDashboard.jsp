<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.model.Admin" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    HttpSession adminSession = request.getSession(false);
    Admin loggedInAdmin = (Admin) adminSession.getAttribute("loggedInAdmin");

    if (loggedInAdmin == null) {
        response.sendRedirect("adminLogin.jsp?message=Please login first.");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <!-- Navigation Bar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Event Management - Admin</a>
            <div class="ml-auto">
                <span class="text-white me-3">Welcome, Admin!</span>
                <form action="AdminServlet" method="post" style="display: inline;">
    				<input type="hidden" name="action" value="logout">
  					  <button type="submit" class="btn btn-danger">Logout</button>
				</form>
            </div>
        </div>
    </nav>

    <!-- Dashboard Content -->
    <div class="container mt-4">
        <h2 class="text-center">Admin Dashboard</h2>
        <div class="row mt-4">
            <div class="col-md-4">
                <div class="card text-white bg-primary mb-3">
                    <div class="card-header">Manage Users</div>
                    <div class="card-body">
                        <p class="card-text">View, update, and delete user accounts.</p>
                        <a href="manageUsers.jsp" class="btn btn-light">Go to Users</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-white bg-success mb-3">
                    <div class="card-header">Manage Events</div>
                    <div class="card-body">
                        <p class="card-text">Create, update, and delete events.</p>
                        <a href="manageEvents.jsp" class="btn btn-light">Go to Events</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-white bg-warning mb-3">
                    <div class="card-header">Manage Bookings</div>
                    <div class="card-body">
                        <p class="card-text">Approve or cancel event bookings.</p>
                        <a href="manageBookings.jsp" class="btn btn-light">Go to Bookings</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>