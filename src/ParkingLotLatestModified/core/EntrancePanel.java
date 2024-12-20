package ParkingLotLatestModified.core;

import ParkingLotLatestModified.vehicles.Vehicle;

public class EntrancePanel {
	private String id;
	

	public EntrancePanel(String id) {
		this.id = id;
	}

	public ParkingTicket generateParkingTicket(Vehicle vehicle) throws ParkingFullException {
		ParkingLot parkingLot = ParkingLot.getInstance();
		return parkingLot.getNewParkingTicket(vehicle);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}