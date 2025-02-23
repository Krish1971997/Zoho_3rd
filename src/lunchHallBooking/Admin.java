package lunchHallBooking;

public class Admin extends User {

    public Admin(String userId, String name, String email, String password) {
        super(userId, name, email, password);
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Admin");
    }

    // Admin-specific methods
    public void addLunchHall(LunchHall hall, HallManager hallManager) {
        hallManager.addHall(hall);
    }

    public void viewAllBookings(BookingManager bookingManager) {
        bookingManager.viewAllBookings();
    }
}
