package ParkingLotLatestModified.persons;

import ParkingLotLatestModified.enums.ParkingTicketStatus;
import ParkingLotLatestModified.core.ParkingFullException;
import ParkingLotLatestModified.core.ParkingLot;
import ParkingLotLatestModified.core.ParkingTicket;
import ParkingLotLatestModified.other.Account;
import ParkingLotLatestModified.parkingSpots.ParkingSpot;
import ParkingLotLatestModified.vehicles.Vehicle;

public class ParkingAttendant extends Account {

	public ParkingTicket generateTicket(Vehicle vehicle) throws ParkingFullException {
		ParkingLot parkingLot = ParkingLot.getInstance();
		return parkingLot.getNewParkingTicket(vehicle);

	}

	public boolean processTicket(String ticketNumber) {
		ParkingLot parkingLot = ParkingLot.getInstance();
		ParkingTicket ticket = parkingLot.getActiveTicket(ticketNumber);

		if (ticket != null) {
			ticket.setStatus(ParkingTicketStatus.PAID); // Mark the ticket as paid
			ParkingSpot spot = ticket.getAssignedSpot();
			if (spot != null) {
				spot.removeVehicle(); // Remove vehicle from parking spot
				parkingLot.decrementSpotCount(ticket.getVehicle().getType());
			}
			parkingLot.removeTicket(ticket);
			return true;
		}
		return false; // Ticket not found
	}
}
