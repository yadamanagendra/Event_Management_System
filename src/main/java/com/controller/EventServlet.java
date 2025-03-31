package com.controller;

import java.io.IOException; import java.time.LocalDate; import java.time.format.DateTimeFormatter; import java.util.List; import javax.servlet.RequestDispatcher; import javax.servlet.ServletException; import javax.servlet.annotation.WebServlet; import javax.servlet.http.HttpServlet; import javax.servlet.http.HttpServletRequest; import javax.servlet.http.HttpServletResponse;

import com.model.Event; import com.service.EventService; import com.service.EventServiceImpl;

@WebServlet("/EventServlet") public class EventServlet extends HttpServlet { private static final long serialVersionUID = 1L; private EventService eventService;

public EventServlet() {
    super();
    eventService = new EventServiceImpl();
}

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");

    if (action != null) {
        switch (action) {
            case "list":
                listAvailableEvents(request, response);
                break;
            default:
                response.sendRedirect("error.jsp");
                break;
        }
    }
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");

    if (action != null) {
        switch (action) {
            case "create":
                createEvent(request, response);
                break;
            case "updateStatus":
                updateEventStatus(request, response);
                break;
            case "delete":
                deleteEvent(request, response);
                break;
            default:
                response.sendRedirect("error.jsp");
                break;
        }
    }
}

private void listAvailableEvents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Event> availableEvents = eventService.getAvailableEvents();
    request.setAttribute("availableEvents", availableEvents);
    RequestDispatcher dispatcher = request.getRequestDispatcher("booking.jsp");
    dispatcher.forward(request, response);
}

private void createEvent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
        String eventName = request.getParameter("eventName");
        String eventDateStr = request.getParameter("eventDate");
        String eventTime = request.getParameter("eventTime");
        String location = request.getParameter("location");
        int createdBy = Integer.parseInt(request.getParameter("createdBy"));
        String description = request.getParameter("description");
        String status = request.getParameter("status");
        String eventCategory = request.getParameter("eventCategory");
        String imagePath = request.getParameter("imagePath");

        LocalDate eventDate = LocalDate.parse(eventDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Event event = new Event(0, eventName, eventDate, eventTime, location, createdBy, description, status, eventCategory, imagePath);

        boolean isCreated = eventService.createEvent(event);
        if (isCreated) {
            response.sendRedirect("eventSuccess.jsp");
        } else {
            response.sendRedirect("eventError.jsp");
        }
    } catch (Exception e) {
        e.printStackTrace();
        response.sendRedirect("error.jsp");
    }
}

private void updateEventStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
        int eventId = Integer.parseInt(request.getParameter("eventId"));
        String status = request.getParameter("status");

        boolean isUpdated = eventService.updateEventStatus(eventId, status);
        if (isUpdated) {
            response.sendRedirect("eventUpdated.jsp");
        } else {
            response.sendRedirect("eventError.jsp");
        }
    } catch (Exception e) {
        e.printStackTrace();
        response.sendRedirect("error.jsp");
    }
}

private void deleteEvent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
        int eventId = Integer.parseInt(request.getParameter("eventId"));

        boolean isDeleted = eventService.deleteEvent(eventId);
        if (isDeleted) {
            response.sendRedirect("eventDeleted.jsp");
        } else {
            response.sendRedirect("eventError.jsp");
        }
    } catch (Exception e) {
        e.printStackTrace();
        response.sendRedirect("error.jsp");
    }
}

}