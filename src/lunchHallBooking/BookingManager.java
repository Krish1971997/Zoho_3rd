package lunchHallBooking;

import java.util.ArrayList;
import java.util.List;

public class BookingManager {
    private List<Booking> bookings;

    public BookingManager() {
        this.bookings = new ArrayList<>();
    }

    public void createBooking(String customerId, int hallId, String date, String startTime, String endTime) {
        // Check for availability
        for (Booking booking : bookings) {
            if (booking.getHallId() == hallId && booking.getBookingDate().equals(date) && 
                booking.getStartTime().equals(startTime)) {
                System.out.println("Hall is already booked for this time slot.");
                return;
            }
        }

        // Create booking if available
        int bookingId = bookings.size() + 1;
        Booking newBooking = new Booking(bookingId, customerId, hallId, date, startTime, endTime);
        bookings.add(newBooking);
        System.out.println("Booking successful with ID: " + bookingId);
    }

    public void viewAllBookings() {
        for (Booking booking : bookings) {
            System.out.println("Booking ID: " + booking.getBookingId());
        }
    }
}
