package carRentalSystem_R;

import java.time.LocalDate;

public class BookVehicleHelper {
	
	public boolean bookVehicle(Vehicle vehicle, LocalBrach source, LocalBrach destination, LocalDate startDate,
			LocalDate endDate, Customer customer) {
		if (vehicle.isVechicleAvialable()) {
			vehicle.setAvailabity(VehicleAvailbityType.BOOKED);
			VehicleLog log = VehicleLog.getInstance();
			vehicle.setStartDate(startDate);
			vehicle.setEndDate(endDate);
			CarRentalSystem sys = CarRentalSystem.getInstance();
			sys.updateMemVehicleInfo(customer, vehicle);
			sys.updatevehMemInfo(customer, vehicle);
			log.addLog(vehicle.getLicenceNum(), new Log(startDate, vehicle.getAvailabity(), customer));
			return true;
		}
		return false;

	}

}
