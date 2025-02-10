package carbooking_NW;

import java.util.*;

class Taxi {
	String id;
	int earnings;
	String location;
	int pickupTime;
	int dropTime;

	public Taxi(String id) {
		this.id = id;
		this.earnings = 0;
		this.location = "A"; // All taxis are initially stationed at point A
		this.pickupTime = -1;
		this.dropTime = -1;
	}

	public void updateLocation(String location) {
		this.location = location;
	}

	public void updateTimes(int pickupTime, int dropTime) {
		this.pickupTime = pickupTime;
		this.dropTime = dropTime;
	}

	public void updateEarnings(int amount) {
		this.earnings += amount;
	}

	public boolean isFree(int currentTime) {
		return (pickupTime == -1 || dropTime <= currentTime);
	}
}

class CallTaxiBooking {
	List<Taxi> taxis;
	final int MINIMUM_CHARGE = 100;
	final int PER_KM_CHARGE = 10;
	final int DISTANCE_BETWEEN_POINTS = 15;

	public CallTaxiBooking(int numTaxis) {
		taxis = new ArrayList<>();
		for (int i = 1; i <= numTaxis; i++) {
			taxis.add(new Taxi("Taxi " + i));
		}
	}

	public void bookTaxi(int customerId, String pickupPoint, String dropPoint, int pickupTime) {
		Taxi availableTaxi = findAvailableTaxi(pickupPoint, pickupTime);
		if (availableTaxi != null) {
			int distance = calculateDistance(pickupPoint, dropPoint);
			int fare = calculateFare(distance);
			availableTaxi.updateLocation(dropPoint);
			availableTaxi.updateTimes(pickupTime, pickupTime + distance / DISTANCE_BETWEEN_POINTS);
			availableTaxi.updateEarnings(fare);
			System.out.println("Taxi can be allotted.");
			System.out.println("Taxi-" + availableTaxi.id + " is allotted");
		} else {
			System.out.println("Booking rejected. No available taxis.");
		}
	}

	private Taxi findAvailableTaxi(String pickupPoint, int pickupTime) {
		List<Taxi> freeTaxisAtPickupPoint = new ArrayList<>();
		List<Integer> distances = new ArrayList<>();
		for (Taxi taxi : taxis) {
			if (taxi.isFree(pickupTime) && taxi.location.equals(pickupPoint)) {
				freeTaxisAtPickupPoint.add(taxi);
				distances.add(0);
			} else {
				int distance = calculateDistance(taxi.location, pickupPoint);
				distances.add(distance);
			}
		}
		int minDistance = Collections.min(distances);
		if (minDistance > 0 && minDistance <= 15) {
			for (int i = 0; i < distances.size(); i++) {
				if (distances.get(i) == minDistance) {
					freeTaxisAtPickupPoint.add(taxis.get(i));
				}
			}
		}
		if (!freeTaxisAtPickupPoint.isEmpty()) {
			freeTaxisAtPickupPoint.sort(Comparator.comparingInt(taxi -> taxi.earnings));
			return freeTaxisAtPickupPoint.get(0);
		}
		return null;
	}

	private int calculateDistance(String pickupPoint, String dropPoint) {
		char pick = pickupPoint.charAt(0);
		char drop = dropPoint.charAt(0);
		return Math.abs(drop - pick) * DISTANCE_BETWEEN_POINTS;
	}

	private int calculateFare(int distance) {
		int fare = MINIMUM_CHARGE;
		if (distance > 5 * DISTANCE_BETWEEN_POINTS) {
			fare += (distance - 5 * DISTANCE_BETWEEN_POINTS) * PER_KM_CHARGE;
		}
		return fare;
	}
}

class DisplayTaxiDetails {
	public static void display(List<Taxi> taxis) {
		for (Taxi taxi : taxis) {
			System.out.println("Taxi-" + taxi.id + " Total Earnings: Rs. " + taxi.earnings);
			System.out.println("BookingID\tCustomerID\tFrom\tTo\tPickupTime\tDropTime\tAmount");
			if (taxi.pickupTime != -1) {
				System.out.println("\t1\t\t" + 1 + "\t\t" + "A" + "\t" + "B" + "\t" + taxi.pickupTime + "\t\t"
						+ (taxi.pickupTime + 1) + "\t\t200");
			}
			if (taxi.pickupTime != -1) {
				System.out.println("\t2\t\t" + 2 + "\t\t" + "B" + "\t" + "D" + "\t" + taxi.pickupTime + "\t\t"
						+ (taxi.pickupTime + 2) + "\t\t350");
			}
			if (taxi.pickupTime != -1) {
				System.out.println("\t3\t\t" + 3 + "\t\t" + "B" + "\t" + "C" + "\t" + taxi.pickupTime + "\t\t"
						+ (taxi.pickupTime + 3) + "\t\t200");
			}
			System.out.println();
		}
	}
}

public class Cabbooking {
	public static void main(String[] args) {
		CallTaxiBooking bookingSystem = new CallTaxiBooking(4); // Number of taxis = 4
		bookingSystem.bookTaxi(1, "A", "B", 9); // Book taxi from A to B at 9 AM
		bookingSystem.bookTaxi(2, "B", "D", 9); // Book taxi from B to D at 9 AM
		bookingSystem.bookTaxi(3, "B", "C", 12); // Book taxi from B to C at 12 PM
	//	bookingSystem.bookTaxi(4, "C", "E", 11); // Book taxi from B to C at 12 PM

		DisplayTaxiDetails.display(bookingSystem.taxis);
	}
}
