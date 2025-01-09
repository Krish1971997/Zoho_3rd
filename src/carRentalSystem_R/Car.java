package carRentalSystem_R;

public class Car extends Vehicle {

	public Car(String licenceNum, String barcode, String model, boolean hasRoof, int totalSeats, String compName) {
		super(licenceNum, barcode, model, hasRoof, totalSeats, compName, VehicleType.CAR);
	}
}
