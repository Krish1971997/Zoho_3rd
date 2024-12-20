package ParkingLotLatestModified.parkingSpots;

import ParkingLotLatestModified.enums.ParkingSpotType;

public class ElectricSpot extends ParkingSpot {
	public ElectricSpot(String number) {
		super(ParkingSpotType.ELECTRIC, number); // Type is ELECTRIC
	}
}
