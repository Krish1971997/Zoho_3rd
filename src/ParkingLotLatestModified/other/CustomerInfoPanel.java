package ParkingLotLatestModified.other;

import ParkingLotLatestModified.core.ParkingFloor;
import ParkingLotLatestModified.core.ParkingLot;
import ParkingLotLatestModified.core.ParkingRate;

public class CustomerInfoPanel {
	  private String id;

	  public void showAvailableSpots(ParkingFloor floor) {
	    floor.getDisplayBoard().showEmptySpotNumber();
	  }

	  public void showParkingRates() {
	    ParkingLot parkingLot = ParkingLot.getInstance();
	    ParkingRate rate = parkingLot.getParkingRate();
	    System.out.println("Parking Rate: " + rate.getRatePerHour() + " per hour.");
	  }
	}
