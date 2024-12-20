package ParkingLotLatestModified.core;

public class ParkingFullException extends Exception {

	private static final long serialVersionUID = 1L;

	// Default constructor with a default error message
	public ParkingFullException() {
		super("The parking lot is full. No more spaces available.");
	}

	// Constructor with a custom error message
	public ParkingFullException(String message) {
		super(message);
	}
}