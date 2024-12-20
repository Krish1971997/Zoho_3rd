package ParkingLotLatestModified.vehicles;

import ParkingLotLatestModified.enums.VehicleType;

public class Motorbike extends Vehicle {
    public Motorbike(String licenseNumber) {
        super(licenseNumber, VehicleType.MOTORBIKE);
    }

    @Override
    public void park() {
        // Custom behavior for parking a motorbike
        System.out.println("Parking motorbike: " + getLicenseNumber());
    }
}
