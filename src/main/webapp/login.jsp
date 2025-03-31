<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Login</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            max-width: 450px;
            margin-top: 80px;
        }
        .input-group-text {
            width: 120px;
            text-align: center;
            font-weight: bold;
            background-color: #e9ecef;
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
                    <li class="nav-item"><a class="nav-link active" href="login.jsp">Login</a></li>
                    <li class="nav-item"><a class="nav-link" href="registration.jsp">Register</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- 🔹 Login Form -->
    <div class="container bg-white p-4 rounded shadow">
        <h3 class="text-center mb-3">User Login</h3>

        <!-- 🔹 Error & Success Messages -->
        <%
            String errorMessage = request.getParameter("error");
            String successMessage = request.getParameter("success");
            if (errorMessage != null) {
        %>
            <div class="alert alert-danger text-center"><%= errorMessage %></div>
        <%
            }
            if (successMessage != null) {
        %>
            <div class="alert alert-success text-center"><%= successMessage %></div>
        <%
            }
        %>

        <form action="UserServlet" method="post">
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

            <p class="mt-3 text-center">New here? <a href="registration.jsp" class="btn btn-link">Register</a></p>
        </form>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>