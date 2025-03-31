<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, com.util.DBConnection" %>
<%@ page import="java.time.LocalDateTime, java.time.format.DateTimeFormatter" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Event Details</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            max-width: 700px;
            margin-top: 80px;
        }
        .event-card {
            border: 1px solid #dee2e6;
            border-radius: 10px;
            padding: 20px;
            background-color: white;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>

    <!-- 🔹 Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Event Management</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
                    <li class="nav-item"><a class="nav-link" href="userDashboard.jsp">Dashboard</a></li>
                    <li class="nav-item"><a class="nav-link" href="logout.jsp">Logout</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- 🔹 Event Details -->
    <div class="container">
        <div class="event-card">
            <h3 class="text-center mb-3">Event Details</h3>

            <%
                // Retrieving event details from request attributes
                String eventName = (String) request.getAttribute("eventName");
                String eventDate = (String) request.getAttribute("eventDate");
                String eventTime = (String) request.getAttribute("eventTime");
                String location = (String) request.getAttribute("location");
                String description = (String) request.getAttribute("description");
                String category = (String) request.getAttribute("category");
                String status = (String) request.getAttribute("status");

                if (eventName != null) {
            %>

            <table class="table table-bordered">
                <tr>
                    <th>Event Name</th>
                    <td><%= eventName %></td>
                </tr>
                <tr>
                    <th>Date</th>
                    <td><%= eventDate %></td>
                </tr>
                <tr>
                    <th>Time</th>
                    <td><%= eventTime %></td>
                </tr>
                <tr>
                    <th>Location</th>
                    <td><%= location %></td>
                </tr>
                <tr>
                    <th>Category</th>
                    <td><%= category %></td>
                </tr>
                <tr>
                    <th>Description</th>
                    <td><%= description %></td>
                </tr>
                <tr>
                    <th>Status</th>
                    <td><span class="badge bg-success"><%= status %></span></td>
                </tr>
            </table>

            <div class="text-center mt-3">
                <a href="userDashboard.jsp" class="btn btn-primary">Back to Events</a>
            </div>

            <%
                } else {
            %>
                <div class="alert alert-danger text-center">
                    <strong>Event not found!</strong>
                </div>
            <%
                }
            %>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>