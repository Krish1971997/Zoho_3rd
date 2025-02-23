package lunchHallBooking;

public class Booking {
    private int bookingId;
    private String customerId;
    private int hallId;
    private String bookingDate;
    private String startTime;
    private String endTime;

    public Booking(int bookingId, String customerId, int hallId, String bookingDate, String startTime, String endTime) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.hallId = hallId;
        this.bookingDate = bookingDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getBookingId() {
        return bookingId;
    }

	public String getCustomerId() {
		return customerId;
	}

	public int getHallId() {
		return hallId;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}
    
}
