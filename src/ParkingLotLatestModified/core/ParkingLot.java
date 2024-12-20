package ParkingLotLatestModified.core;

import java.util.HashMap;

import ParkingLotLatestModified.other.Location;
import ParkingLotLatestModified.parkingSpots.ParkingSpot;
import ParkingLotLatestModified.vehicles.Vehicle;
import ParkingLotLatestModified.enums.VehicleType;

public class ParkingLot {
	private String name;
	private Location address;
	private ParkingRate parkingRate;

	private int compactSpotCount;
	private int largeSpotCount;
	private int motorbikeSpotCount;
	private int electricSpotCount;
	private final int maxCompactCount = 1; // Example max count
	private final int maxLargeCount = 1;
	private final int maxMotorbikeCount = 1;
	private final int maxElectricCount = 1;

	private HashMap<String, EntrancePanel> entrancePanels = new HashMap<>();
	private HashMap<String, ExitPanel> exitPanels = new HashMap<>();
	private HashMap<String, ParkingFloor> parkingFloors = new HashMap<>();

	private HashMap<String, ParkingTicket> activeTickets = new HashMap<>();

	private static ParkingLot parkingLot = null;

	public void addEntrancePanel(EntrancePanel entrancePanel) {
		// Store the entrance panel in the entrancePanels map
		entrancePanels.put(entrancePanel.getId(), entrancePanel); // Assuming EntrancePanel has an `getId()` method.
	}

	private ParkingLot() {
		// Initialize parking lot data (this would normally be loaded from a database)
	}

	public static ParkingLot getInstance() {
		if (parkingLot == null) {
			parkingLot = new ParkingLot();
		}
		return parkingLot;
	}

	public ParkingRate getParkingRate() {
		return parkingRate;
	}

	// Setter for parkingRate
	public void setParkingRate(ParkingRate parkingRate) {
		this.parkingRate = parkingRate;
	}

	public ParkingSpot getNextAvailableSpot(VehicleType vehicleType) {
		// Loop through all floors and their parking spots
		for (ParkingFloor floor : parkingFloors.values()) {
			ParkingSpot availableSpot = floor.getNextAvailableSpot(vehicleType);
			if (availableSpot != null) {
				return availableSpot;
			}
		}
		return null; // No available spot found for the vehicle type
	}

	public synchronized ParkingTicket getNewParkingTicket(Vehicle vehicle) throws ParkingFullException {
		if (this.isFull(vehicle.getType())) {
			throw new ParkingFullException();
		}
		ParkingTicket ticket = new ParkingTicket();
		vehicle.assignTicket(ticket);
		ticket.setVehicle(vehicle); // Assign vehicle to the ticket

		// Assign the vehicle to the next available spot
		ParkingSpot spot = getNextAvailableSpot(vehicle.getType());
		if (spot == null)
			throw new ParkingFullException();
		ticket.setAssignedSpot(spot); // Set the assigned spot for the ticket
		spot.assignVehicle(vehicle); // Assign vehicle to the spot

		this.incrementSpotCount(vehicle.getType());
		this.activeTickets.put(vehicle.getLicenseNumber(), ticket);
		return ticket;
	}

	public boolean isFull(VehicleType type) {
		switch (type) {
		case CAR:
			return (compactSpotCount + largeSpotCount) >= (maxCompactCount + maxLargeCount);
		case TRUCK:
		case VAN:
			return largeSpotCount >= maxLargeCount;
		case MOTORBIKE:
			return motorbikeSpotCount >= maxMotorbikeCount;
		case ELECTRIC:
			return (electricSpotCount + compactSpotCount + largeSpotCount) >= (maxElectricCount + maxCompactCount
					+ maxLargeCount);
		default:
			return true;
		}
	}

	private boolean incrementSpotCount(VehicleType type) {
		if (type == VehicleType.CAR) {
			if (compactSpotCount < maxCompactCount) {
				compactSpotCount++;
			} else {
				largeSpotCount++;
			}
		} else if (type == VehicleType.TRUCK || type == VehicleType.VAN) {
			largeSpotCount++;
		} else if (type == VehicleType.MOTORBIKE) {
			motorbikeSpotCount++;
		} else if (type == VehicleType.ELECTRIC) {
			if (electricSpotCount < maxElectricCount) {
				electricSpotCount++;
			} else {
				if (compactSpotCount < maxCompactCount) {
					compactSpotCount++;
				} else {
					largeSpotCount++;
				}
			}
		}
		return true;
	}

	public void addExitPanel(ExitPanel exitPanel) {
		// Store the exit panel in the exitPanels map
		exitPanels.put(exitPanel.getId(), exitPanel); // Assuming ExitPanel has an `getId()` method
	}

	public ParkingFloor getParkingFloor(String floorName) {
		return parkingFloors.get(floorName); // Get the ParkingFloor by name from the HashMap
	}

	public void addParkingFloor(ParkingFloor floor) {
		this.parkingFloors.put(floor.getName(), floor);
	}

	public ParkingTicket getActiveTicket(String ticketNumber) {
		System.out.println(ticketNumber);
		return activeTickets.get(ticketNumber);
	}

	public synchronized ParkingTicket getNewParkingTicket1(Vehicle vehicle) throws ParkingFullException {
		if (this.isFull(vehicle.getType())) {
			throw new ParkingFullException();
		}
		ParkingTicket ticket = new ParkingTicket();
		vehicle.assignTicket(ticket); // Assign ticket to the vehicle
		this.activeTickets.put(ticket.getTicketNumber(), ticket); // Add ticket to the active tickets map
		return ticket;
	}

	public void removeTicket(ParkingTicket ticket) {
		if (ticket != null) {
			activeTickets.remove(ticket.getTicketNumber());
		}
	}

	public void decrementSpotCount(VehicleType type) {
		if (type == VehicleType.TRUCK || type == VehicleType.VAN) {
			if (largeSpotCount > 0) {
				largeSpotCount--;
			}
		} else if (type == VehicleType.MOTORBIKE) {
			if (motorbikeSpotCount > 0) {
				motorbikeSpotCount--;
			}
		} else if (type == VehicleType.CAR) {
			if (compactSpotCount > 0) {
				compactSpotCount--;
			} else if (largeSpotCount > 0) {
				largeSpotCount--;
			}
		} else if (type == VehicleType.ELECTRIC) {
			if (electricSpotCount > 0) {
				electricSpotCount--;
			} else if (compactSpotCount > 0) {
				compactSpotCount--;
			} else if (largeSpotCount > 0) {
				largeSpotCount--;
			}
		}
	}

	public HashMap<String, ParkingTicket> getActiveTickets() {
		return activeTickets;
	}

	public int getCompactSpotCount() {
		return compactSpotCount;
	}

	public int getLargeSpotCount() {
		return largeSpotCount;
	}

	public int getMotorbikeSpotCount() {
		return motorbikeSpotCount;
	}

	public int getElectricSpotCount() {
		return electricSpotCount;
	}

	// Method to check if parking is full
	public boolean isFull() {
		return (compactSpotCount >= maxCompactCount && largeSpotCount >= maxLargeCount
				&& motorbikeSpotCount >= maxMotorbikeCount && electricSpotCount >= maxElectricCount);
	}

	public void displayBoard() {
		for (String floorId : parkingFloors.keySet()) {
			System.out.println("Floor Id : " + floorId);
			parkingFloors.get(floorId).displayAllDetails();
			System.lineSeparator();

		}

	}

}
