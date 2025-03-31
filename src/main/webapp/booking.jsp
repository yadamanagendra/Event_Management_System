<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <%@ page import="java.util.List" %> <%@ page import="com.model.Event" %>

<!DOCTYPE html><html>
<head>
    <meta charset="UTF-8">
    <title>Book an Event</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Book Your Event</h2><form action="BookingServlet" method="post">
        <input type="hidden" name="action" value="book">
        
        <!-- Event Selection Dropdown -->
        <div class="mb-3">
            <label for="eventId" class="form-label">Select Event</label>
            <select class="form-control" id="eventId" name="eventId" required>
                <option value="">Select an Event</option>
                <% 
                    List<Event> availableEvents = (List<Event>) request.getAttribute("availableEvents");
                    if (availableEvents != null) {
                        for (Event event : availableEvents) {
                %>
                <option value="<%= event.getEventId() %>"><%= event.getEventName() %> - <%= event.getDescription() %></option>
                <%      
                        }
                    }
                %>
            </select>
        </div>
        
        <!-- Available Locations Dropdown -->
        <div class="mb-3">
            <label for="location" class="form-label">Select Location</label>
            <select class="form-control" id="location" name="location" required>
                <option value="">Select a Location</option>
                <option value="Los Angeles">Los Angeles</option>
                <option value="New York">New York</option>
                <option value="San Francisco">San Francisco</option>
                <option value="Chicago">Chicago</option>
            </select>
        </div>
        
        <!-- Booking Date -->
        <div class="mb-3">
            <label for="bookingDate" class="form-label">Booking Date</label>
            <input type="date" class="form-control" id="bookingDate" name="bookingDate" required>
        </div>
        
        <!-- Submit Button -->
        <button type="submit" class="btn btn-primary w-100">Book Now</button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>