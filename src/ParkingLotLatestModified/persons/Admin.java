package ParkingLotLatestModified.persons;

import ParkingLotLatestModified.core.EntrancePanel;
import ParkingLotLatestModified.core.ExitPanel;
import ParkingLotLatestModified.core.ParkingDisplayBoard;
import ParkingLotLatestModified.core.ParkingFloor;
import ParkingLotLatestModified.core.ParkingLot;
import ParkingLotLatestModified.other.Account;
import ParkingLotLatestModified.other.CustomerInfoPanel;
import ParkingLotLatestModified.parkingSpots.ParkingSpot;

public class Admin extends Account {

	public boolean addParkingFloor(ParkingFloor floor) {
		ParkingLot parkingLot = ParkingLot.getInstance();
		parkingLot.addParkingFloor(floor);
		return true; // Assuming parking floor was added successfully
	}

	public boolean addParkingSpot(String floorName, ParkingSpot spot) {
		ParkingLot parkingLot = ParkingLot.getInstance();
		ParkingFloor floor = parkingLot.getParkingFloor(floorName);
		if (floor != null) {
			floor.addParkingSpot(spot);
			return true;
		}
		return false;
	}

	public boolean addParkingDisplayBoard(String floorName, ParkingDisplayBoard displayBoard) {
		ParkingLot parkingLot = ParkingLot.getInstance();
		ParkingFloor floor = parkingLot.getParkingFloor(floorName);
		if (floor != null) {
			floor.setDisplayBoard(displayBoard);
			return true;
		}
		return false;
	}

	public boolean addCustomerInfoPanel(String floorName, CustomerInfoPanel infoPanel) {
		ParkingLot parkingLot = ParkingLot.getInstance();
		ParkingFloor floor = parkingLot.getParkingFloor(floorName);
		if (floor != null) {
			floor.addCustomerInfoPanel(infoPanel);
			return true;
		}
		return false;
	}

	public boolean addEntrancePanel(EntrancePanel entrancePanel) {
		ParkingLot parkingLot = ParkingLot.getInstance();
		parkingLot.addEntrancePanel(entrancePanel);
		return true;
	}

	public boolean addExitPanel(ExitPanel exitPanel) {
		ParkingLot parkingLot = ParkingLot.getInstance();
		parkingLot.addExitPanel(exitPanel);
		return true;
	}
}
