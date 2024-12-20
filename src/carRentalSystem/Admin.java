package carRentalSystem;

public class Admin extends Person {

	public Admin(Address addr, String id, String name) {
		super(addr, id, name, PersonType.ADMIN);
		System.out.println("Admin created");

	}

	public void addBranch(String id, LocalBrach branch) {
		CarRentalSystem sys = CarRentalSystem.getInstance();
		sys.addBranch(id, branch);

	}

	public void addVehicles(LocalBrach branch, Vehicle vehicle, Spot spot) {
		branch.addVehicle(spot, vehicle);

	}

}
