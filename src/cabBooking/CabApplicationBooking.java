package cabBooking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CabApplicationBooking {

	static List<Customer> customers = new ArrayList<>();
	static List<Driver> drivers = new ArrayList<>();
	static List<Location> locations = new ArrayList<>();
	static List<Cab> cab = new ArrayList<>();
	static List<Ride> rides = new ArrayList<>();
	static Scanner sc = new Scanner(System.in);
	static Cab cabRest;

	public static void main(String[] args) {
		intilizations();
		while (true) {
			System.out.println("Enter the choice ");
			System.out.println("1.Booking\n2.CustomerSummary\n3.CabSummary\n4.TotalSummary");
			int id = sc.nextInt();
			switch (id) {
			case 1: {
				booking();
				break;
			}
			case 2: {
				System.out.println("Enter customer id : ");
				int custid = sc.nextInt();
				customerRideSummary(custid);
				break;
			}
			case 3: {
				System.out.println("Enter Driver id : ");
				int cabid = sc.nextInt();
				cabDriverRideSummary(cabid);
				break;
			}
			case 4: {
				TotalSummary();
				break;
			}

			default:
				throw new IllegalArgumentException("Unexpected value: " + id);
			}
		}
	}

	private static void intilizations() {
		driverIntilization();
		cabIntilization();
		customerIntilization();
		locationIntilization();
	}

	public static void booking() {
		showCabDetails();

		System.out.println("Enter the customer Id");
		int customerID = sc.nextInt();
		Customer customer = getCustomer(customerID);

		System.out.println("Enter the source");
		char source = sc.next().charAt(0);

		System.out.println("Enter the Destination");
		char destination = sc.next().charAt(0);

		Cab nearestTaxi = nearestTaxi(source);
		if (nearestTaxi == null) {
			System.out.println("Cab is not available now");
			return;
		}
		Driver driver = getDriver(nearestTaxi.getId());
		System.out.println("driver " + driver);
		nearestTaxi.setDriver(driver);

		int fare = calculateFare(source, destination);
		System.out.println("Fare -> " + fare);
		Ride ride = new Ride(source, destination, nearestTaxi,customer, fare);
		nearestTaxi.addRide(ride);
		rides.add(ride);
		setRestCab(nearestTaxi);
		System.out.println(ride);
	}

	private static Driver getDriver(int driverID) {
		for (int i = 0; i < drivers.size(); i++) {
			if (drivers.get(i).getId() == driverID)
				return drivers.get(i);
		}
		return null;
	}

	public static Cab nearestTaxi(char source) {
		Cab nearestCab = null;
		int minDistance = Integer.MAX_VALUE;
		for (Cab cab : cab) {
			int currDist = calculateDistance(source, cab.getLocation());
			if (cab.isAvailable()
					&& (currDist < minDistance || (currDist == minDistance
							&& cab.getRides().size() < (nearestCab != null ? nearestCab.getRides().size() : Integer.MAX_VALUE)))
					|| (currDist == minDistance
							&& cab.getFare() < (nearestCab != null ? nearestCab.getFare() : Integer.MAX_VALUE))) {
				minDistance = currDist;
				nearestCab = cab;
			}
		}

		return nearestCab;
	}

	public static int calculateDistance(char source, char destincation) {
		int sourceDist = 0;
		int destDist = 0;
		for (int i = 0; i < locations.size(); i++) {
			Location location = locations.get(i);
			if (location.getName() == source) {
				sourceDist = location.getDistanceFromOrigin();
			}
			if (location.getName() == destincation) {
				destDist = location.getDistanceFromOrigin();
			}
		}

		return Math.abs(destDist - sourceDist);
	}

	public static void setRestCab(Cab cab) {
		if (cabRest != null) {
			cabRest.setAvailable(true);
		}

		cab.setAvailable(false);
		cabRest = cab;
	}

	private static void showCabDetails() {
		System.out.println("Available Cabs :");
		for (int i = 0; i < cab.size(); i++) 
			System.out.println(cab.get(i));
	}

	private static int calculateFare(char source, char destination) {
		return calculateDistance(source, destination) * 10;
	}

	private static Customer getCustomer(int id) {
		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getId() == id)
				return customers.get(i);
		}
		return null;
	}
	
	private static Cab getCab(int id) {
		for (int i = 0; i < cab.size(); i++) {
			if(cab.get(i).getId()==id)
				return cab.get(i);
		}
		return null;
	}

	private static void customerRideSummary(int id) {
		System.out.println("Customer Id : " + id);
		System.out.println("Customer Name : " + getCustomer(id).getName());
		System.out.println("Trip Details");

		boolean first = true;
		for (int i = 0; i < rides.size(); i++) {
			if (rides.get(i).getCustomer().getId() == id) {
				Ride ride = rides.get(i);
				if (first) {
					System.out.println("Source\tDestination\tCabDetails\tFare");
					first = false;
				}
				System.out.println(ride.getSource() + "\t" + ride.getDestination() + "\t\t" + ride.getCab().getId()
						+ "\t" + ride.getFare());
			}
		}
	}

	private static void cabDriverRideSummary(int id) {
		System.out.println("Cab Id : " + id);
		System.out.println("Cab Driver Name : " + getCab(id).getDriver().getName());
		System.out.println("Trip Details");

		boolean first = true;
		for (int i = 0; i < rides.size(); i++) {
			if (rides.get(i).getCab().getId() == id) {
				Ride ride = rides.get(i);
				if (first) {
					System.out.println("Source\tDestination\tCustomerDetails\tFare\tZULA Commission");
					first = false;
				}
				System.out.println(ride.getSource() + "\t" + ride.getDestination() + "\t\t" + ride.getCustomer().getId()
						+ "\t" + ride.getFare() +"\t"+ride.getZulaComm());
			}
		}
	}

	private static void TotalSummary() {
		boolean first = true;
		for (int i = 0; i < cab.size(); i++) {
			int cabid = cab.get(i).getId();
			int noOfTrips = 0;
			int fareCollected = 0;
			int zulacomm = 0;
			for (int j = 0; j < rides.size(); j++) {
				if (cabid == rides.get(j).getCab().getId()) {
					noOfTrips++;
					fareCollected += rides.get(j).getFare();
					zulacomm += rides.get(j).getZulaComm();
				}
			}
			System.out.println("Cab Id : " + cabid);
			System.out.println("Total Number of Trips : " + noOfTrips);
			System.out.println("Total Fare collected : " + fareCollected);
			System.out.println("Total Zula Commission : " + zulacomm);

			for (int j = 0; j < rides.size(); j++) {
				if (cabid == rides.get(j).getCab().getId()) {
					Ride ride = rides.get(j);
					if (first) {
						System.out.println("Source\tDestination\tCustomerDetails\tFare\tZULA Commission");
						first = false;
					}
					System.out.println(ride.getSource() + "\t" + ride.getDestination() + "\t\t"
							+ ride.getCustomer().getId() + "\t" + ride.getFare() +"\t"+ ride.getZulaComm());
				}
			}
		}
	}

	private static void cabIntilization() {
		Cab cab1 = new Cab('D', 1);
		Cab cab2 = new Cab('G', 2);
		Cab cab3 = new Cab('H', 3);
		Cab cab4 = new Cab('A', 4);
		cab.add(cab1);
		cab.add(cab2);
		cab.add(cab3);
		cab.add(cab4);
	}

	private static void driverIntilization() {
		Driver driver1 = new Driver(1, "aaa", "111", 43, "M");
		Driver driver2 = new Driver(2, "bbb", "222", 31, "M");
		Driver driver3 = new Driver(3, "ccc", "333", 38, "F");
		Driver driver4 = new Driver(4, "ddd", "444", 28, "F");

		drivers.add(driver1);
		drivers.add(driver2);
		drivers.add(driver3);
		drivers.add(driver4);
	}

	private static void locationIntilization() {
		Location location1 = new Location(1, 'A', 0);
		Location location2 = new Location(3, 'C', 4);
		Location location3 = new Location(4, 'D', 7);
		Location location4 = new Location(6, 'F', 9);
		Location location5 = new Location(2, 'B', 15);
		Location location6 = new Location(7, 'G', 18);
		Location location7 = new Location(8, 'H', 20);
		Location location8 = new Location(5, 'E', 23);

		locations.add(location1);
		locations.add(location2);
		locations.add(location3);
		locations.add(location4);
		locations.add(location5);
		locations.add(location6);
		locations.add(location7);
		locations.add(location8);
	}

	private static void customerIntilization() {
		Customer customer1 = new Customer(1, "zz", "99", 25, "F");
		Customer customer2 = new Customer(2, "yy", "88", 62, "M");
		Customer customer3 = new Customer(3, "xx", "77", 22, "M");
		Customer customer4 = new Customer(4, "ww", "66", 36, "F");

		customers.add(customer1);
		customers.add(customer2);
		customers.add(customer3);
		customers.add(customer4);
	}
}
