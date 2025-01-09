package carRentalSystem_R;

import java.time.LocalDate;

public class Customer extends Person {

	private RentalInsurance insurance = null;

	public Customer(Address addr, String id, String name) {
		super(addr, id, name, PersonType.CUSTOMER);
	}

	public RentalInsurance getInsurance() {
		return insurance;
	}

	public void setInsurance(RentalInsurance insurance) {
		this.insurance = insurance;
	}

	public void bookVehicle(Vehicle vehicle, LocalBrach source, LocalBrach destination, LocalDate startDate,
			LocalDate endDate, Customer customer) {
		boolean isBooked = source.bookVehicle(vehicle, source, destination, startDate, endDate, customer);
		if (isBooked) {
			System.out.println("Vehicle booked");
		} else
			System.out.println("Vehicle is not booked");
	}

	public void returnVehicle(Vehicle vehicle, LocalBrach destination, LocalDate returnDate, Payment payment,
			Customer customer) {
		destination.returnVehicle(vehicle, destination, returnDate, payment, customer);
	}

	public void cancelVehicle(Vehicle vehicle, Customer customer) {
		CarRentalSystem system = CarRentalSystem.getInstance();
		Vehicle custVehicle = system.getVehicleInfoBasedMemId(customer.getId());
		if (custVehicle.getLicenceNum() != vehicle.getLicenceNum()) {
			System.out.println("Wrong Entry");
		} else {
			vehicle.setAvailabity(VehicleAvailbityType.AVAILABLE);
			vehicle.setEndDate(null);
			vehicle.setStartDate(null);
			VehicleLog log = VehicleLog.getInstance();
			log.addLog(vehicle.getLicenceNum(), new Log(LocalDate.now(), VehicleAvailbityType.CANCELLED, customer));
			System.out.println("cancel the vehicle");
		}
	}
}
