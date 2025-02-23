package lunchHallBooking;

public class Customer extends User {

    public Customer(String userId, String name, String email, String password) {
        super(userId, name, email, password);
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Customer");
    }

    // Customer-specific methods
    public void bookHall(int hallId, String date, String startTime, String endTime, BookingManager bookingManager) {
        bookingManager.createBooking(this.userId, hallId, date, startTime, endTime);
    }
}
