package ParkingLotLatestModified.core;

import java.util.HashMap;

import ParkingLotLatestModified.other.CustomerInfoPanel;
import ParkingLotLatestModified.parkingSpots.CompactSpot;
import ParkingLotLatestModified.parkingSpots.ElectricSpot;
import ParkingLotLatestModified.parkingSpots.HandicappedSpot;
import ParkingLotLatestModified.parkingSpots.LargeSpot;
import ParkingLotLatestModified.parkingSpots.MotorbikeSpot;
import ParkingLotLatestModified.parkingSpots.ParkingSpot;
import ParkingLotLatestModified.vehicles.Vehicle;
import ParkingLotLatestModified.enums.VehicleType;

public class ParkingFloor {
	private String name;
	private HashMap<String, HandicappedSpot> handicappedSpots = new HashMap<>();
	private HashMap<String, CompactSpot> compactSpots = new HashMap<>();
	private HashMap<String, LargeSpot> largeSpots = new HashMap<>();
	private HashMap<String, MotorbikeSpot> motorbikeSpots = new HashMap<>();
	private HashMap<String, ElectricSpot> electricSpots = new HashMap<>();
	private ParkingDisplayBoard displayBoard;
	private CustomerInfoPanel customerInfoPanel; // Field for the customer info panel

	public void addCustomerInfoPanel(CustomerInfoPanel customerInfoPanel) {
		this.customerInfoPanel = customerInfoPanel;
	}
	
	public void displayAllDetails() {
		System.out.println(" handicappedSpots ");
		for (String Id : handicappedSpots.keySet()) {
			System.out.println(Id+" --- "+handicappedSpots.get(Id).isFree());
		}
		System.out.println("---------------------------------");

		System.out.println(" compactSpots ");
		
		for (String Id : compactSpots.keySet()) {
			System.out.println(Id+" --- "+compactSpots.get(Id).isFree());
		}
		System.out.println("---------------------------------");

		System.out.println(" largeSpots ");
		for (String Id : largeSpots.keySet()) {
			System.out.println(Id+" --- "+largeSpots.get(Id).isFree());
		}
		System.out.println("---------------------------------");

		System.out.println(" motorbikeSpots ");
		for (String Id : motorbikeSpots.keySet()) {
			System.out.println(Id+" --- "+motorbikeSpots.get(Id).isFree());
		}
		System.out.println("---------------------------------");
		System.out.println(" electricSpots ");
		for (String Id : electricSpots.keySet()) {
			System.out.println(Id+" --- "+electricSpots.get(Id).isFree());
		}
		
	}

	// Method to get the customer info panel (optional)
	public CustomerInfoPanel getCustomerInfoPanel() {
		return this.customerInfoPanel;
	}

	public ParkingFloor(String name) {
		this.name = name;
	}

	public ParkingDisplayBoard getDisplayBoard() {
		return displayBoard;
	}

	public void setDisplayBoard(ParkingDisplayBoard displayBoard) {
		this.displayBoard = displayBoard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ParkingSpot getNextAvailableSpot(VehicleType vehicleType) {

		System.out.println(vehicleType);
		switch (vehicleType) {
		case CAR:
			// Find first free compact or large spot
			return getNextFreeCompactSpot();
		case MOTORBIKE:
			return getNextFreeMotorbikeSpot();
		case ELECTRIC:
			return getNextFreeElectricSpot();
		case TRUCK:
			return getNextFreeLargeSpot();
		default:
			return null;
		}
	}

	public void addParkingSpot(ParkingSpot spot) {
		switch (spot.getType()) {
		case HANDICAPPED:
			handicappedSpots.put(spot.getNumber(), (HandicappedSpot) spot);
			break;
		case COMPACT:
			compactSpots.put(spot.getNumber(), (CompactSpot) spot);
			break;
		case LARGE:
			largeSpots.put(spot.getNumber(), (LargeSpot) spot);
			break;
		case MOTORBIKE:
			motorbikeSpots.put(spot.getNumber(), (MotorbikeSpot) spot);
			break;
		case ELECTRIC:
			electricSpots.put(spot.getNumber(), (ElectricSpot) spot);
			break;
		default:
			System.out.println("Wrong parking spot type!");
		}
	}

	public boolean isFull() {
	    // Check all handicapped spots
	    for (HandicappedSpot spot : handicappedSpots.values()) {
	        if (spot.isFree()) {
	            return false; // If any spot is free, return false
	        }
	    }

	    // Check all compact spots
	    for (CompactSpot spot : compactSpots.values()) {
	        if (spot.isFree()) {
	            return false; // If any spot is free, return false
	        }
	    }

	    // Check all large spots
	    for (LargeSpot spot : largeSpots.values()) {
	        if (spot.isFree()) {
	            return false; // If any spot is free, return false
	        }
	    }

	    // Check all motorbike spots
	    for (MotorbikeSpot spot : motorbikeSpots.values()) {
	        if (spot.isFree()) {
	            return false; // If any spot is free, return false
	        }
	    }

	    // Check all electric spots
	    for (ElectricSpot spot : electricSpots.values()) {
	        if (spot.isFree()) {
	            return false; // If any spot is free, return false
	        }
	    }

	    // If no spot is free, return true (i.e., the parking lot is full)
	    return true;
	}


	public void assignVehicleToSpot(Vehicle vehicle, ParkingSpot spot) {
		spot.assignVehicle(vehicle);
		updateDisplayBoardForSpotType(spot);
	}

	private void updateDisplayBoardForSpotType(ParkingSpot spot) {
		// This method checks the type of spot and updates the display board accordingly
		switch (spot.getType()) {
		case HANDICAPPED:
			displayBoard.setHandicappedFreeSpot(getNextFreeHandicappedSpot());
			break;
		case COMPACT:
			displayBoard.setCompactFreeSpot(getNextFreeCompactSpot());
			break;
		case LARGE:
			displayBoard.setLargeFreeSpot(getNextFreeLargeSpot());
			break;
		case MOTORBIKE:
			displayBoard.setMotorbikeFreeSpot(getNextFreeMotorbikeSpot());
			break;
		case ELECTRIC:
			displayBoard.setElectricFreeSpot(getNextFreeElectricSpot());
			break;
		default:
			System.out.println("Unknown parking spot type!");
		}
	}

	private HandicappedSpot getNextFreeHandicappedSpot() {
		return handicappedSpots.values().stream().filter(ParkingSpot::isFree).findFirst().orElse(null);
	}

	public CompactSpot getNextFreeCompactSpot() {
		for (CompactSpot spot : compactSpots.values()) {
			if (spot.isFree()) {
				return spot; // Return the first free spot found
			}
		}
		return null; // No free spot found
	}

	public LargeSpot getNextFreeLargeSpot() {
		return largeSpots.values().stream().filter(ParkingSpot::isFree).findFirst().orElse(null);
	}

	public MotorbikeSpot getNextFreeMotorbikeSpot() {
		return motorbikeSpots.values().stream().filter(ParkingSpot::isFree).findFirst().orElse(null);
	}

	public ElectricSpot getNextFreeElectricSpot() {
		return electricSpots.values().stream().filter(ParkingSpot::isFree).findFirst().orElse(null);
	}
}
