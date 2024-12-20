package ParkingLotLatestModified.vehicles;

import ParkingLotLatestModified.enums.VehicleType;

public class Truck extends Vehicle {
    public Truck(String licenseNumber) {
        super(licenseNumber, VehicleType.TRUCK);
    }

    @Override
    public void park() {
        // Custom behavior for parking a truck
        System.out.println("Parking truck: " + getLicenseNumber());
    }
}
