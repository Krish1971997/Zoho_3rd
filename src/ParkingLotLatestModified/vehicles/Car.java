package ParkingLotLatestModified.vehicles;

import ParkingLotLatestModified.enums.VehicleType;

public class Car extends Vehicle {
    public Car(String licenseNumber) {
        super(licenseNumber, VehicleType.CAR);
    }

    @Override
    public void park() {
        // Custom behavior for parking a car
        System.out.println("Parking car: " + getLicenseNumber());
    }
}

