<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HariVillu Eventz</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<style>
	.card-img-top {
    width: 100%; 
    height: 200px; /* Set a fixed height */
    object-fit: cover; /* Ensures images maintain aspect ratio without distortion */
	}
	.carousel-item img {
    width: 100%;
    height: 400px; /* Adjust based on your layout */
    object-fit: cover;
	}
	.navbar-nav .nav-link {
    padding: 8px 15px;
    border-radius: 5px; /* Adds rounded corners */
    transition: all 0.3s ease-in-out;
}

.navbar-nav .nav-link:hover {
    background-color: #207cf9; /* Button background color */
    color: #000 !important; /* Text color */
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Adds a shadow for effect */
    transform: scale(1.1); /* Slightly increases size */
}

	
</style>
    <!-- Navigation Bar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="index.jsp">HARIVILLU Eventz</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
                    <li class="nav-item"><a class="nav-link" href="registration.jsp">Register</a></li>
                    <li class="nav-item"><a class="nav-link" href="adminLogin.jsp">Admin</a></li>
                </ul>
            </div>
        </div>
    </nav>
   

    <!-- Hero Section -->
    <header class="bg-primary text-white text-center py-5">
        <h1> Harivillu Eventz welcomes you</h1>
        <p>Book your favorite events and manage them with ease!</p>
        <a href="registration.jsp" class="btn btn-light btn-lg">Get Started</a>
    </header>

    <!-- Event Carousel (Showcasing Top 6 Events) -->
    <div id="eventCarousel" class="carousel slide mt-4" data-bs-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="assets/images/musicConcertBanner.jpg" class="d-block w-100" alt="Event 1">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Music Concert</h5>
                    <p>Experience the best live music performances.</p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="assets/images/techConferenceBanner.jpg" class="d-block w-100" alt="Event 2">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Tech Conference</h5>
                    <p>Join the latest tech trends and innovations.</p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="assets/images/foodFestivalBanner.jpg" class="d-block w-100" alt="Event 3">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Food Festival</h5>
                    <p>Taste delicious cuisines from around the world.</p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="assets/images/businessSummitBanner.jpg" class="d-block w-100" alt="Event 4">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Business Summit</h5>
                    <p>Meet industry leaders and grow your network.</p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="assets/images/artExhibitionBanner.jpg" class="d-block w-100" alt="Event 5">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Art Exhibition</h5>
                    <p>Discover breathtaking art from top artists.</p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="assets/images/sportsTournamentBanner.jpg" class="d-block w-100" alt="Event 6">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Sports Tournament</h5>
                    <p>Enjoy exciting live sports action.</p>
                </div>
            </div>
        </div>
        <a class="carousel-control-prev" href="#eventCarousel" role="button" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        </a>
        <a class="carousel-control-next" href="#eventCarousel" role="button" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
        </a>
    </div>

    <!-- List of All Events -->
    <div class="container mt-5">
        <h2 class="text-center mb-4">Upcoming Events</h2>
        <div class="row">
            <div class="col-md-4 mb-4">
                <div class="card">
                    <img src="assets/images/musicConcert.jpg" class="card-img-top" alt="Music Concert">
                    <div class="card-body">
                        <h5 class="card-title">Music Concert</h5>
                        <p class="card-text">Join us for an electrifying night of live music.</p>
                        <a href="booking.jsp" class="btn btn-primary">Book Now</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-4">
                <div class="card">
                    <img src="assets/images/techConference.jpg" class="card-img-top" alt="Tech Conference">
                    <div class="card-body">
                        <h5 class="card-title">Tech Conference</h5>
                        <p class="card-text">Explore cutting-edge technology innovations.</p>
                        <a href="booking.jsp" class="btn btn-primary">Book Now</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-4">
                <div class="card">
                    <img src="assets/images/foodFestival.jpg" class="card-img-top" alt="Food Festival">
                    <div class="card-body">
                        <h5 class="card-title">Food Festival</h5>
                        <p class="card-text">Enjoy a variety of delicious cuisines.</p>
                        <a href="booking.jsp" class="btn btn-primary">Book Now</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-4">
                <div class="card">
                    <img src="assets/images/businessSummit.jpg" class="card-img-top" alt="Business Summit">
                    <div class="card-body">
                        <h5 class="card-title">Business Summit</h5>
                        <p class="card-text">Network with industry experts.</p>
                        <a href="booking.jsp" class="btn btn-primary">Book Now</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-4">
                <div class="card">
                    <img src="assets/images/artExhibition.jpg" class="card-img-top" alt="Art Exhibition">
                    <div class="card-body">
                        <h5 class="card-title">Art Exhibition</h5>
                        <p class="card-text">Experience breathtaking artwork.</p>
                        <a href="booking.jsp" class="btn btn-primary">Book Now</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-4">
                <div class="card">
                    <img src="assets/images/sportsTournament.jpg" class="card-img-top" alt="Sports Tournament">
                    <div class="card-body">
                        <h5 class="card-title">Sports Tournament</h5>
                        <p class="card-text">Enjoy thrilling sports matches.</p>
                        <a href="booking.jsp" class="btn btn-primary">Book Now</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer class="bg-dark text-white text-center py-3">
        <p>&copy; 2025 Event Management System | Contact: support@example.com</p>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
