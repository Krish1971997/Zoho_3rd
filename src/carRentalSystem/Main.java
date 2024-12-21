package carRentalSystem;

import java.time.LocalDate;

public class Main {
	public static void main(String[] args) {

		CarRentalSystem system = CarRentalSystem.getInstance();

		System.out.println("----------------- Creating Admin -------------");
		Address addr1 = new Address("1-21", "Street1", "Chennai", "Chennai", "TN", "INDIA");
		Admin admin1 = new Admin(addr1, "A-1", "Rajesh");
		System.out.println("-----------------------------");

		System.out.println("----------------- Creating Branch -------------");
		Address addr2 = new Address("1-222", "Street1", "TAMBARAM", "Chennai", "TN", "INDIA");
		LocalBrach branch1 = new LocalBrach(addr2);
		admin1.addBranch("B-1", branch1);
		System.out.println("-----------------------------");

		System.out.println("----------------- Creating Branch -------------");
		Address addr4 = new Address("1-222", "Street1", "TAMBARAM", "Chennai", "TN", "INDIA");
		LocalBrach branch2 = new LocalBrach(addr4);
		admin1.addBranch("B-2", branch2);
		System.out.println("-----------------------------");

		System.out.println("----------------- Add Vehicle -------------");
		Car car1 = new Car("AB01", "1123", "TIAGO", false, 4, "TATA");
		Spot spot = new Spot("Floor-1", "1", "1");
		admin1.addVehicles(branch1, car1, spot);
		Car car2 = new Car("AB02", "1122", "TIAGO", false, 6, "TATA");
		Spot spot1 = new Spot("Floor-1", "2", "1");
		admin1.addVehicles(branch1, car2, spot1);
		System.out.println("-----------------------------");

		Address addr3 = new Address("1-121", "Street1", "TAMBARAM", "Chennai", "TN", "INDIA");
		Customer customer1 = new Customer(addr3, "C-1", "Lokesh");
		RentalInsurance insurance = new RentalInsurance( 10000.0);
		customer1.setInsurance(insurance);

		customer1.bookVehicle(car1, branch1, branch2, LocalDate.now(), LocalDate.of(2024, 12, 25), customer1);
		customer1.bookVehicle(car1, branch2, branch1, LocalDate.now(), LocalDate.of(2024, 12, 23), customer1);
		VehicleLog log = VehicleLog.getInstance();
		log.printLog(car1.getLicenceNum());

		System.out.println("------- Cancel Reservation-----");
		customer1.cancelVehicle(car1, customer1);

		customer1.returnVehicle(car1, branch2, LocalDate.of(2024, 12, 30), new CardPayment(), customer1);

		
		log.printLog(car1.getLicenceNum());

		System.out.println("---- Searching Car type vehicle-----");

		VehicleInventory inventory = VehicleInventory.getInstance();
		inventory.searchVehicle(VehicleType.CAR);

		System.out.println("--------------Vehilce info---");
		system.getMemberInfoBasedvehicleNum(car1.getLicenceNum());
		system.getVehicleInfoBasedMemId(customer1.getId());

		System.out.println("--------------BarCode Info ---");
		system.readBarCode(car1.getBarcode());

	}

}
