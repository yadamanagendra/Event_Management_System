<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">
    <h2 class="text-danger text-center">Error Occurred</h2>
    <p class="text-center text-danger"><%= request.getParameter("msg") != null ? request.getParameter("msg") : "Something went wrong!" %></p>
    <div class="text-center">
        <a href="index.jsp" class="btn btn-primary">Go Back</a>
    </div>
</body>
</html>