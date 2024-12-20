package ParkingLotLatestModified.core;

import java.util.UUID;

import ParkingLotLatestModified.enums.ParkingTicketStatus;
import ParkingLotLatestModified.parkingSpots.ParkingSpot;
import ParkingLotLatestModified.vehicles.Vehicle;

public class ParkingTicket {
  private String ticketNumber;
  private Vehicle vehicle;
  private ParkingTicketStatus status;
  private ParkingSpot assignedSpot;
  private long startTime; 
  
  public ParkingTicket() {
    this.ticketNumber = UUID.randomUUID().toString();  // Generate a unique ticket number
    this.status = ParkingTicketStatus.ACTIVE;  // Initially, the ticket is active
    this.startTime = System.currentTimeMillis(); 
  }
  
  public long getStartTime() {
      return startTime;
  }

  public String getTicketNumber() {
    return ticketNumber;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  public ParkingTicketStatus getStatus() {
    return status;
  }

  public void setStatus(ParkingTicketStatus status) {
    this.status = status;
  }

  public ParkingSpot getAssignedSpot() {
    return assignedSpot;
  }

  public void setAssignedSpot(ParkingSpot spot) {
    this.assignedSpot = spot;
  }

  @Override
public String toString() {
	return "ParkingTicket [ticketNumber=" + ticketNumber + ", vehicle=" + vehicle + ", status=" + status
			+ ", assignedSpot=" + assignedSpot + "]";
}

public void saveInDB() {
    // Simulate saving the ticket in the database
   //  System.out.println("Saving ticket in database...");
  }
  
}
