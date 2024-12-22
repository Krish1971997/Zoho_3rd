package carRentalSystem;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CarRentalSystem {

	private Map<String, LocalBrach> localbranch = new HashMap<>();
	private Map<String, Vehicle> memVehInfo = new HashMap<>();
	private Map<String, Customer> vehMemInfo = new HashMap<>();
	private Map<String, Vehicle> barCodeReader = new HashMap<>();

	private static CarRentalSystem instance = null;

	private CarRentalSystem() {
	}

	public void addBarCodeInfo(Vehicle vehicle) {
		barCodeReader.put(vehicle.getBarcode(), vehicle);
	}

	public void readBarCode(String barcode) {
		System.out.println("Barcode : " + barcode);
		System.out.println(barCodeReader.get(barcode));
	}

	public Vehicle getVehicleInfoBasedMemId(String id) {
		System.out.println(memVehInfo.get(id));
		return memVehInfo.get(id);
	}

	public void getMemberInfoBasedvehicleNum(String licenceNo) {
		System.out.println(vehMemInfo.get(licenceNo));
	}

	public void updateMemVehicleInfo(Customer customer, Vehicle vehicle) {
		memVehInfo.put(customer.getId(), vehicle);
	}

	public void updatevehMemInfo(Customer customer, Vehicle vehicle) {
		vehMemInfo.put(vehicle.getLicenceNum(), customer);
	}

	public static synchronized CarRentalSystem getInstance() {
		if (instance == null) {
			instance = new CarRentalSystem();
		}
		return instance;
	}

	public void addBranch(String id, LocalBrach branch) {
		localbranch.put(id, branch);
	}

	public double calculateAmount(Vehicle vehicle, LocalDate returnDate) {
		int startDate = vehicle.getStartDate().getDayOfYear();
		int end = vehicle.getEndDate().getDayOfYear();
		int returnDateVal = returnDate.getDayOfYear();

		double fine = 0.0;
		double vehicleAmount = Rent.getInstance().getFeeAmount(vehicle.getType());
		if (returnDateVal > end) {
			fine = 10 * (returnDateVal - end);
		}

		double actualAmount = vehicleAmount * (end - startDate);
		return actualAmount + fine;
	}
}
