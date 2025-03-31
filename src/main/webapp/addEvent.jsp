<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Event</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            max-width: 600px;
            margin-top: 50px;
        }
        .card {
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
    </style>

    <script>
        function validateForm() {
            let eventName = document.forms["eventForm"]["event_name"].value.trim();
            let eventDate = document.forms["eventForm"]["event_date"].value;
            let eventTime = document.forms["eventForm"]["event_time"].value;
            let location = document.forms["eventForm"]["location"].value.trim();
            let description = document.forms["eventForm"]["description"].value.trim();
            
            if (eventName === "" || eventDate === "" || eventTime === "" || location === "" || description === "") {
                alert("All fields are required!");
                return false;
            }
            return true;
        }
    </script>
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
                    <li class="nav-item"><a class="nav-link" href="adminDashboard.jsp">Dashboard</a></li>
                    <li class="nav-item"><a class="nav-link" href="logout.jsp">Logout</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- 🔹 Add Event Form -->
    <div class="container">
        <div class="card">
            <h3 class="text-center mb-3">Add New Event</h3>
            <form name="eventForm" action="EventServlet" method="post" onsubmit="return validateForm();">
                <input type="hidden" name="action" value="addEvent">

                <div class="mb-3">
                    <label>Event Name:</label>
                    <input type="text" class="form-control" name="event_name" placeholder="Enter event name" required>
                </div>

                <div class="mb-3">
                    <label>Date:</label>
                    <input type="date" class="form-control" name="event_date" required>
                </div>

                <div class="mb-3">
                    <label>Time:</label>
                    <input type="time" class="form-control" name="event_time" required>
                </div>

                <div class="mb-3">
                    <label>Location:</label>
                    <input type="text" class="form-control" name="location" placeholder="Enter event location" required>
                </div>

                <div class="mb-3">
                    <label>Category:</label>
                    <select class="form-control" name="category" required>
                        <option value="Conference">Conference</option>
                        <option value="Workshop">Workshop</option>
                        <option value="Concert">Concert</option>
                        <option value="Seminar">Seminar</option>
                        <option value="Other">Other</option>
                    </select>
                </div>

                <div class="mb-3">
                    <label>Description:</label>
                    <textarea class="form-control" name="description" rows="3" placeholder="Enter event details" required></textarea>
                </div>

                <div class="text-center">
                    <button type="submit" class="btn btn-success">Add Event</button>
                    <a href="adminDashboard.jsp" class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>