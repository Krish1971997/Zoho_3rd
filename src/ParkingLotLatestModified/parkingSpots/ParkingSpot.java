package ParkingLotLatestModified.parkingSpots;

import ParkingLotLatestModified.enums.ParkingSpotType;
import ParkingLotLatestModified.vehicles.Vehicle;

public abstract class ParkingSpot {
	  private String number; // Unique identifier for the parking spot (e.g., "A1", "B5")
	  private boolean free;  // Indicates if the spot is free or occupied
	  private Vehicle vehicle; // The vehicle currently occupying the spot
	  private final ParkingSpotType type; // The type of spot (e.g., compact, large, motorbike)

	  // Constructor to initialize the parking spot with its type and set it to free
	  public ParkingSpot(ParkingSpotType type, String number) {
	    this.type = type;
	    this.number = number;
	    this.free = true;  // Initially, the spot is free
	  }

	  // Returns the type of the parking spot (e.g., COMPACT, MOTORBIKE)
	  public ParkingSpotType getType() {
	    return this.type;
	  }

	  // Returns the number of the parking spot (e.g., "A1")
	  public String getNumber() {
	    return this.number;
	  }

	  // Checks if the parking spot is free
	  public boolean isFree() {
	    return this.free;
	  }

	  // Assigns a vehicle to the parking spot, marking it as occupied
	  public boolean assignVehicle(Vehicle vehicle) {
	    if (this.free) {
	      this.vehicle = vehicle;
	      this.free = false;
	      return true; // Successfully assigned the vehicle
	    }
	    return false; // Spot is already occupied
	  }

	  // Removes the vehicle from the parking spot, marking it as free
	  public boolean removeVehicle() {
	    if (!this.free) {
	      this.vehicle = null;
	      this.free = true;
	      return true; // Successfully removed the vehicle
	    }
	    return false; // Spot is already free
	  }
	}

