package com.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.model.Booking;
import com.service.BookingService;
import com.service.BookingServiceImpl;

@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookingService bookingService;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public BookingServlet() {
        super();
        bookingService = new BookingServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("bookEvent".equals(action)) {
            bookEvent(request, response);
        } else if ("updateStatus".equals(action)) {
            updateBookingStatus(request, response);
        } else if ("cancelBooking".equals(action)) {
            cancelBooking(request, response);
        } else {
            response.sendRedirect("error.jsp?msg=Invalid Action");
        }
    }

    private void bookEvent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int eventId = Integer.parseInt(request.getParameter("eventId"));
            int userId = Integer.parseInt(request.getParameter("userId"));
            LocalDate bookingDate = LocalDate.now();
            String status = request.getParameter("status");

            Booking newBooking = new Booking(0, eventId, userId, bookingDate, status); // 5 arguments

            boolean isBooked = bookingService.bookEvent(newBooking);

            if (isBooked) {
                response.sendRedirect("success.jsp?msg=booking_successful");
            } else {
                response.sendRedirect("error.jsp?msg=booking_failed");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("error.jsp?msg=Invalid Input: " + e.getMessage());
        }
    }

    private void updateBookingStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int bookingId = Integer.parseInt(request.getParameter("bookingId"));
            String status = request.getParameter("status");

            boolean isUpdated = bookingService.updateBookingStaus(bookingId, status);

            if (isUpdated) {
                response.sendRedirect("success.jsp?msg=booking_status_updated");
            } else {
                response.sendRedirect("error.jsp?msg=update_failed");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("error.jsp?msg=Invalid Booking ID");
        }
    }

    private void cancelBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int bookingId = Integer.parseInt(request.getParameter("bookingId"));

            boolean isCancelled = bookingService.cancelBooking(bookingId);

            if (isCancelled) {
                response.sendRedirect("success.jsp?msg=booking_cancelled");
            } else {
                response.sendRedirect("error.jsp?msg=cancellation_failed");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("error.jsp?msg=Invalid Booking ID");
        }
    }
}