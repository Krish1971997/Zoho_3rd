package ParkingLotLatestModified.core;

import ParkingLotLatestModified.enums.ParkingTicketStatus;
import ParkingLotLatestModified.parkingSpots.ParkingSpot;

public class ExitPanel {
	private String id;

	public ExitPanel(String string) {
		this.id = string;
	}
	
	private double calculateAmountDue(ParkingTicket ticket) {
	    // Get the start time and end time (current time)
	    long startTime = ticket.getStartTime(); // Assuming startTime is in milliseconds
	    long currentTime = System.currentTimeMillis(); // Current time in milliseconds
	    
	    // Calculate the duration in milliseconds
	    long durationInMillis = currentTime - startTime;
	    
	    // Convert duration from milliseconds to hours (rounded up)
	    double hoursParked = Math.ceil(durationInMillis / (1000.0 * 60 * 60)); // 1 hour = 1000 ms * 60 sec * 60 min
	    
	    // Define a base hourly rate (example)
	    double hourlyRate = 5.0; // 5 currency units per hour
	    
	    // You can customize the rate based on the vehicle type, peak hours, etc.
	    // For example, assume that "premium" vehicles pay more:
	    double rateMultiplier = 1.0;  // Default multiplier for regular vehicles
	    
	   
	    
	    // Calculate the total fee
	    double amountDue = hoursParked * hourlyRate * rateMultiplier;
	    
	    // Return the calculated amount due
	    return amountDue;
	}


	public boolean closeParkingTicket(String ticketNumber) {
		System.out.println(ticketNumber);
		ParkingLot parkingLot = ParkingLot.getInstance();
		ParkingTicket ticket = parkingLot.getActiveTicket(ticketNumber);
		System.out.println(ticket);

		if (ticket != null) {
			
			ParkingSpot spot = ticket.getAssignedSpot();
			if (spot != null) {
				double amountDue = calculateAmountDue(ticket);
		        boolean paymentSuccessful = processPayment(amountDue);
		        if (!paymentSuccessful) {
		            System.out.println("Payment failed. Ticket cannot be closed.");
		            return false;
		        }
		        else {
			        ticket.setStatus(ParkingTicketStatus.PAID);
		        }
				spot.removeVehicle();
				parkingLot.decrementSpotCount(ticket.getVehicle().getType());
			}
			System.out.println("Vehicle " + ticket.getVehicle().getLicenseNumber() + " has exited.");
			parkingLot.removeTicket(ticket);
			return true;
		}
		return false; // Ticket not found
	}

	private boolean processPayment(double amountDue) {
		System.out.println("Amount paid");
		return true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
