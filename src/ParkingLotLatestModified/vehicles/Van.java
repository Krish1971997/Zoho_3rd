package ParkingLotLatestModified.vehicles;

import ParkingLotLatestModified.enums.VehicleType;

public class Van extends Vehicle {
    public Van(String licenseNumber) {
        super(licenseNumber, VehicleType.VAN);
    }

    @Override
    public void park() {
        // Custom behavior for parking a van
        System.out.println("Parking van: " + getLicenseNumber());
    }
}