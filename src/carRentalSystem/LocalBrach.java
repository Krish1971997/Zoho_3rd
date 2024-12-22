package carRentalSystem;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class LocalBrach {
	Address addr;
	Map<Spot, Vehicle> map = new HashMap<>();
	BookVehicleHelper bookVehicleHelper;

	public LocalBrach(Address addr) {
		this.addr = addr;
		// System.out.println("Branch Created");
		bookVehicleHelper = new BookVehicleHelper();
	}

	public void addVehicle(Spot spot, Vehicle vehicle) {
		map.put(spot, vehicle);
		VehicleInventory vehicleInventory = VehicleInventory.getInstance();
		vehicleInventory.addVehicle(vehicle);
		CarRentalSystem sys = CarRentalSystem.getInstance();
		sys.addBarCodeInfo(vehicle);
	}

	public boolean bookVehicle(Vehicle vehicle, LocalBrach source, LocalBrach destination, LocalDate startDate,
			LocalDate endDate, Customer customer) {
		return bookVehicleHelper.bookVehicle(vehicle, source, destination, startDate, endDate, customer);
	}

	public void returnVehicle(Vehicle vehicle, LocalBrach destination, LocalDate returnDate, Payment payment,
			Customer customer) {

		if (vehicle.getAvailabity() == VehicleAvailbityType.BOOKED) {
			vehicle.setAvailabity(VehicleAvailbityType.AVAILABLE);
			CarRentalSystem system = CarRentalSystem.getInstance();
			double amount = system.calculateAmount(vehicle, returnDate);
			VehicleLog log = VehicleLog.getInstance();
			log.addLog(vehicle.getLicenceNum(), new Log(returnDate, VehicleAvailbityType.RETURNED, customer));
			payment.pay(amount);
		} else {
			System.out.println("Wrong Entry");
		}
	}
}
