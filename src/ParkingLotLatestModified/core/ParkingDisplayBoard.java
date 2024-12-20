package ParkingLotLatestModified.core;

import ParkingLotLatestModified.parkingSpots.CompactSpot;
import ParkingLotLatestModified.parkingSpots.ElectricSpot;
import ParkingLotLatestModified.parkingSpots.HandicappedSpot;
import ParkingLotLatestModified.parkingSpots.LargeSpot;
import ParkingLotLatestModified.parkingSpots.MotorbikeSpot;

public class ParkingDisplayBoard {
	  private String id;
	  private HandicappedSpot handicappedFreeSpot;
	  private CompactSpot compactFreeSpot;
	  private LargeSpot largeFreeSpot;
	  private MotorbikeSpot motorbikeFreeSpot;
	  private ElectricSpot electricFreeSpot;

	  public void showEmptySpotNumber() {
	    String message = "";
	    message += handicappedFreeSpot.isFree() ? "Free Handicapped: " + handicappedFreeSpot.getNumber() : "Handicapped is full";
	    message += System.lineSeparator();

	    message += compactFreeSpot.isFree() ? "Free Compact: " + compactFreeSpot.getNumber() : "Compact is full";
	    message += System.lineSeparator();

	    message += largeFreeSpot.isFree() ? "Free Large: " + largeFreeSpot.getNumber() : "Large is full";
	    message += System.lineSeparator();

	    message += motorbikeFreeSpot.isFree() ? "Free Motorbike: " + motorbikeFreeSpot.getNumber() : "Motorbike is full";
	    message += System.lineSeparator();

	    message += electricFreeSpot.isFree() ? "Free Electric: " + electricFreeSpot.getNumber() : "Electric is full";

	    System.out.println(message);
	  }

	  // Setters for free spots
	  public void setHandicappedFreeSpot(HandicappedSpot spot) {
	    this.handicappedFreeSpot = spot;
	  }

	  public void setCompactFreeSpot(CompactSpot spot) {
	    this.compactFreeSpot = spot;
	  }

	  public void setLargeFreeSpot(LargeSpot spot) {
	    this.largeFreeSpot = spot;
	  }

	  public void setMotorbikeFreeSpot(MotorbikeSpot spot) {
	    this.motorbikeFreeSpot = spot;
	  }

	  public void setElectricFreeSpot(ElectricSpot spot) {
	    this.electricFreeSpot = spot;
	  }
	}

