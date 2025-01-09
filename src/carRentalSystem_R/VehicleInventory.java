package carRentalSystem_R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleInventory {

	Map<VehicleType, List<Vehicle>> catelogue = new HashMap<>();
	static private VehicleInventory instance = null;

	private VehicleInventory() {
	}

	public static VehicleInventory getInstance() {
		if (instance == null) {
			instance = new VehicleInventory();
		}
		return instance;
	}

	public void addVehicle(Vehicle vehicle) {
		VehicleType type = vehicle.getType();
		List<Vehicle> list = catelogue.get(type);
		if (list == null) {
			catelogue.put(type, new ArrayList<>());
			catelogue.get(type).add(vehicle);
		} else {
			list.add(vehicle);
		}
	}

	public void searchVehicle(VehicleType type) {
		List<Vehicle> list = catelogue.get(type);
		for (Vehicle vehicle : list) {
			System.out.println(vehicle);
		}
	}
}
