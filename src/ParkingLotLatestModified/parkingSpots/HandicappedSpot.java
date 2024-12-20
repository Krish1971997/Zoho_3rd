package ParkingLotLatestModified.parkingSpots;

import ParkingLotLatestModified.enums.ParkingSpotType;

public class HandicappedSpot extends ParkingSpot {
	int count = 0;

	public HandicappedSpot(String number) {
		super(ParkingSpotType.HANDICAPPED, number); // Type is HANDICAPPED
	}

	
}