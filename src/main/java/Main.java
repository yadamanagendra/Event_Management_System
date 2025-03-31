//
///*import com.DAO.*;
//
//import com.model.User;
//import com.service.*;
//import java.util.Date;
//
//public class Main {
//    public static void main(String[] args) {
//    	UserServiceImpl userService = new UserServiceImpl(new UserDAOImpl());
//
//        // Create a test user
//        User newUser = new User(0,"John Doe", "john_doe", "john@example.com", 9876543210l, "password123", new Date(), "Active");
//
//        // Register user
//        boolean isRegistered = userService.registerUser(newUser);
//        System.out.println(isRegistered ? "✅ User registered successfully!" : "❌ User registration failed.");
//    }
//}*/
//
////import com.DAO.*;
////import com.model.Admin;
////import com.service.*;
////import java.util.Date;
////
////public class Main {
////    public static void main(String[] args) {
////        AdminServiceImpl adminService = new AdminServiceImpl(new AdminDAOImpl());
////
////        // Creating an admin with an existing user_id (replace with actual user_id)
////        Admin newAdmin = new Admin(0, 1, new Date(), "FULL_ACCESS");
////
////        // Register admin
////        boolean isRegistered = adminService.registerAdmin(newAdmin);
////        System.out.println(isRegistered ? "✅ Admin registered successfully!" : "❌ Admin registration failed.");
////    }
////}
//
////import com.DAO.*;
////
////import com.model.*;
////import com.model.User;
////import com.service.*;
////import java.util.Date;
////
////public class Main {
////    public static void main(String[] args) {
////        // Initialize Services
////        UserServiceImpl userService = new UserServiceImpl(new UserDAOImpl());
////        EventServiceImpl eventService = new EventServiceImpl(new EventDAOImpl());
////
////        // Test User Registration
////        User newUser = new User(0, "pohn Doe", "pohn_doe", "pohn@example.com", 9976543210l, "password123", new Date(), "Active");
////        boolean isUserRegistered = userService.registerUser(newUser);
////        System.out.println(isUserRegistered ? "✅ User registered successfully!" : "❌ User registration failed.");
////
////        // Test Event Creation
////        Event newEvent = new Event(0,"Tech Conference", new Date(), "10:00:00", "Los Angeles",1, "A conference on emerging tech", "upcoming", "Technology");
////        boolean isEventCreated = eventService.createEvent(newEvent);
////        System.out.println(isEventCreated ? "✅ Event created successfully!" : "❌ Event creation failed.");
////    }
////}
//
//import com.DAO.*;
//import com.model.Booking;
//import com.service.*;
//
//import java.util.List;
//import java.util.Date;
 //
//public class Main {
//    public static void main(String[] args) {
//        // Initialize services
//        BookingServiceImpl bookingService = new BookingServiceImpl(new BookingDAOImpl());
//
//        // Test Data
//        int eventId = 1;  // Replace with a valid event ID from the database
//        int userId = 1;   // Replace with a valid user ID from the database
//
//        // 1️⃣ **Book an Event**
//        Booking newBooking = new Booking(0, eventId, userId, new Date(), "Confirmed");
//        boolean isBooked = bookingService.bookEvent(newBooking);
//        System.out.println(isBooked ? "✅ Booking successful!" : "❌ Booking failed.");
//
//        // 2️⃣ **Get Booking Details**
//        int bookingId = 1; // Replace with an actual booking ID
//        Booking booking = bookingService.getBookingDetails(bookingId);
//        if (booking != null) {
//            System.out.println("🔹 Booking Found: " + booking);
//        } else {
//            System.out.println("❌ Booking not found.");
//        }
//
//        // 3️⃣ **Get All Bookings for a User**
//        List<Booking> userBookings = bookingService.getBookingsByUserId(userId);
//        System.out.println("📋 Bookings for User ID " + userId + ": " + userBookings);
//
//        // 4️⃣ **Get All Bookings for an Event**
//        List<Booking> eventBookings = bookingService.getBookingsByEventId(eventId);
//        System.out.println("📋 Bookings for Event ID " + eventId + ": " + eventBookings);
//
//        // 5️⃣ **Cancel a Booking**
//        boolean isCancelled = bookingService.cancelBooking(bookingId);
//        System.out.println(isCancelled ? "✅ Booking canceled successfully!" : "❌ Booking cancellation failed.");
//    }
//}
