<%@ page import="com.model.User" %>
<%@ page import="com.model.Event" %>
<%@ page import="com.service.EventServiceImpl" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="true" %>

<%
    User loggedInUser = (User) session.getAttribute("loggedInUser");
    if (loggedInUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    EventServiceImpl eventService = new EventServiceImpl();
    List<Event> upcomingEvents = eventService.getBookedEventsByUser(loggedInUser.getUserId());
    List<Event> availableEvents = eventService.getAvailableEvents();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>User Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .profile-container {
            display: flex;
            align-items: center;
            gap: 10px;
        }
        .profile-icon {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            background: url('profile-icon.png') center/cover no-repeat;
        }
    </style>
</head>
<body class="bg-light">

    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Event Management</a>
            <div class="profile-container text-white">
                <div class="profile-icon"></div>
                <span><%= loggedInUser.getFullname() %></span>
                <a href="logout.jsp" class="btn btn-sm btn-outline-light ms-3">Logout</a>
            </div>
        </div>
    </nav>

    <!-- Main Content -->
    <div class="container mt-4">
        <h2>Welcome, <%= loggedInUser.getFullname() %>!</h2>

        <!-- Upcoming Events Section -->
        <div class="card mt-3">
            <div class="card-header bg-primary text-white">
                Upcoming Events You Have Booked
            </div>
            <div class="card-body">
                <% if (upcomingEvents.isEmpty()) { %>
                    <p class="text-muted">No upcoming events booked.</p>
                <% } else { %>
                    <ul class="list-group">
                        <% for (Event event : upcomingEvents) { %>
                            <li class="list-group-item">
                                <strong><%= event.getEventName() %></strong><br>
                                Date: <%= event.getEventDate() %><br>
                                Time: <%= event.getEventTime() %><br>
                                Location: <%= event.getLocation() %>
                            </li>
                        <% } %>
                    </ul>
                <% } %>
            </div>
        </div>

        <!-- Providable Events Section -->
        <div class="card mt-3">
            <div class="card-header bg-success text-white">
                Events Available for Booking
            </div>
            <div class="card-body">
                <% if (availableEvents.isEmpty()) { %>
                    <p class="text-muted">No events available for booking at the moment.</p>
                <% } else { %>
                    <ul class="list-group">
                        <% for (Event event : availableEvents) { %>
                            <li class="list-group-item">
                                <strong><%= event.getEventName() %></strong><br>
                                <span class="text-muted"><%= event.getDescription() %></span><br>
                                <a href="booking.jsp?eventId=<%= event.getEventId() %>" class="btn btn-primary btn-sm mt-2">Book Now</a>
                            </li>
                        <% } %>
                    </ul>
                <% } %>
            </div>
        </div>
    </div>

</body>
</html>