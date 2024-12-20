package carRentalSystem;

import java.util.HashMap;
import java.util.Map;

public class Rent {
	Map<VehicleType, Double> fee = new HashMap<>();

	private static Rent rent = null;

	private Rent() {
		fee.put(VehicleType.CAR, 200.0);
		fee.put(VehicleType.MOTORBIKE, 100.0);
		fee.put(VehicleType.VAN, 200.0);
	}

	public static Rent getInstance() {
		if (rent == null) {
			rent = new Rent();
		}
		return rent;
	}

	public double getFeeAmount(VehicleType type) {
		return fee.get(type);
	}

}
