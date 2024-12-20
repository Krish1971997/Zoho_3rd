package ParkingLotLatestModified.vehicles;

import ParkingLotLatestModified.core.ParkingTicket;
import ParkingLotLatestModified.enums.VehicleType;

public abstract class Vehicle {
    private String licenseNumber;
    private final VehicleType type;
    private ParkingTicket ticket;

    // Constructor to initialize the vehicle type and license number
    public Vehicle(String licenseNumber, VehicleType type) {
        this.licenseNumber = licenseNumber;
        this.type = type;
    }

    // Getter for license number
    public String getLicenseNumber() {
        return licenseNumber;
    }

    // Getter for vehicle type
    public VehicleType getType() {
        return type;
    }

    // Assign a parking ticket to this vehicle
    public void assignTicket(ParkingTicket ticket) {
        this.ticket = ticket;
    }

    // Getter for parking ticket
    public ParkingTicket getTicket() {
        return ticket;
    }

    // Abstract method to be overridden by subclasses
    public abstract void park();  // To be implemented by specific vehicle types

    @Override
    public String toString() {
        return "Vehicle [LicenseNumber=" + licenseNumber + ", Type=" + type + "]";
    }
}
