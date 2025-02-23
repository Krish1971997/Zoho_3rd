package lunchHallBooking;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Managers
        HallManager hallManager = new HallManager();
        BookingManager bookingManager = new BookingManager();

        // Users
        Admin admin = new Admin("A1", "Admin User", "admin@example.com", "admin123");
        Customer customer = new Customer("C1", "John Doe", "john@example.com", "pass123");

        // Add Halls by Admin
        System.out.println("---- Admin: Adding Lunch Halls ----");
        List<String> amenities1 = Arrays.asList("Projector", "WiFi");
        LunchHall hall1 = new LunchHall(1, "Hall A", "Building 1", 50, amenities1);
        admin.addLunchHall(hall1, hallManager);

        List<String> amenities2 = Arrays.asList("Whiteboard", "WiFi");
        LunchHall hall2 = new LunchHall(2, "Hall B", "Building 2", 30, amenities2);
        admin.addLunchHall(hall2, hallManager);

        // View all halls
        System.out.println("\n---- Admin: Viewing All Halls ----");
        hallManager.viewAllHalls();

        // Customer books a hall
        System.out.println("\n---- Customer: Booking Hall ----");
        customer.bookHall(1, "2025-02-20", "10:00", "12:00", bookingManager);

        // Customer tries to double book same slot (should be prevented)
        System.out.println("\n---- Customer: Trying to Double Book ----");
        customer.bookHall(1, "2025-02-20", "10:00", "12:00", bookingManager);

        // View all bookings by Admin
        System.out.println("\n---- Admin: Viewing All Bookings ----");
        admin.viewAllBookings(bookingManager);
    }
}
