package ParkingLotLatestModified.vehicles;

import ParkingLotLatestModified.enums.VehicleType;

public class ElectricCar extends Vehicle {
    public ElectricCar(String licenseNumber) {
        super(licenseNumber, VehicleType.ELECTRIC);
    }

    @Override
    public void park() {
        // Custom behavior for parking an electric car
        System.out.println("Parking electric car: " + getLicenseNumber());
    }
}