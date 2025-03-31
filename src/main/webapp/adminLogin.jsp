<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Login</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            max-width: 450px;
            margin-top: 80px;
            background-color: #ffffff;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 5px 5px 15px rgba(0, 0, 0, 0.2);
        }
        .input-group-text {
            width: 150px;
            text-align: center;
            font-weight: bold;
            background-color: #e9ecef;
        }
        .btn-primary {
            background-color: #ff6600;
            border: none;
        }
        .btn-primary:hover {
            background-color: #cc5200;
        }
        .alert {
            font-size: 14px;
            padding: 10px;
        }
    </style>
</head>
<body>

    <!-- 🔹 Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="index.jsp">Event Management</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
                    <li class="nav-item"><a class="nav-link active" href="adminLogin.jsp">Admin Login</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- 🔹 Admin Login Form -->
    <div class="container">
        <h3 class="text-center mb-3 text-dark">Admin Login</h3>

        <!-- 🔹 Error Messages -->
        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
            <div class="alert alert-danger text-center"><strong>Error!</strong> <%= errorMessage %></div>
        <%
            }
        %>

        <form action="AdminServlet" method="post">
            <input type="hidden" name="action" value="login">

            <div class="mb-3 input-group">
                <span class="input-group-text">Username</span>
                <input type="text" class="form-control" name="username" placeholder="Enter username" required>
            </div>

            <div class="mb-3 input-group">
                <span class="input-group-text">Password</span>
                <input type="password" class="form-control" name="password" placeholder="Enter password" required>
            </div>

            <button type="submit" class="btn btn-primary w-100">Login</button>
        </form>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
