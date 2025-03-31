<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Registration</title>

    <!-- Bootstrap CDN -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

    <script>
        function validateForm() {
            let name = document.forms["registerForm"]["full_name"].value;
            let username = document.forms["registerForm"]["username"].value;
            let email = document.forms["registerForm"]["email"].value;
            let mobile = document.forms["registerForm"]["mobile"].value;
            let password = document.forms["registerForm"]["password"].value;
            let confirmPassword = document.forms["registerForm"]["confirm_password"].value;

            let emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
            let mobilePattern = /^[6-9]\d{9}$/;

            if (name.trim() === "" || username.trim() === "" || email.trim() === "" || mobile.trim() === "" || password.trim() === "") {
                alert("All fields are required!");
                return false;
            }
            if (!emailPattern.test(email)) {
                alert("Enter a valid email!");
                return false;
            }
            if (!mobilePattern.test(mobile)) {
                alert("Enter a valid 10-digit mobile number!");
                return false;
            }
            if (password !== confirmPassword) {
                alert("Passwords do not match!");
                return false;
            }
            return true;
        }
    </script>

    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            max-width: 550px; /* Wider form */
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
                    <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
                    <li class="nav-item"><a class="nav-link active" href="registration.jsp">Register</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- 🔹 Registration Form -->
    <div class="container bg-white p-4 rounded shadow">
        <h3 class="text-center mb-3">User Registration</h3>

        <<form name="registerForm" action="UserServlet" method="post" onsubmit="return validateForm();">
    <input type="hidden" name="action" value="register">

    <div class="mb-3 input-group">
        <span class="input-group-text">Full Name</span>
        <input type="text" class="form-control" name="fullname" placeholder="Enter full name" required>
    </div>

    <div class="mb-3 input-group">
        <span class="input-group-text">Username</span>
        <input type="text" class="form-control" name="username" placeholder="Choose a username" required>
    </div>

    <div class="mb-3 input-group">
        <span class="input-group-text">Email</span>
        <input type="email" class="form-control" name="email" placeholder="Enter email address" required>
    </div>

    <div class="mb-3 input-group">
        <span class="input-group-text">Mobile</span>
        <input type="text" class="form-control" name="mobile" placeholder="Enter 10-digit mobile number" required>
    </div>

    <div class="mb-3 input-group">
        <span class="input-group-text">Password</span>
        <input type="password" class="form-control" name="password" placeholder="Enter password" required>
    </div>

    <div class="mb-3 input-group">
        <span class="input-group-text">Confirm</span>
        <input type="password" class="form-control" name="confirm_password" placeholder="Re-enter password" required>
    </div>

    <button type="submit" class="btn btn-primary w-100">Register</button>

    <p class="mt-3 text-center">Already have an account? <a href="login.jsp" class="btn btn-link">Login</a></p>
</form>    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>