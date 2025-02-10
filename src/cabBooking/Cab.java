package cabBooking;

import java.util.ArrayList;
import java.util.List;

public class Cab {
	private char location;
	private int id;
	private boolean isAvailable;
	private List<Ride> rides;
	private double fare;
	private Driver driver;
	
	public Cab(char location, int id) {
		this.location = location;
		this.id = id;
		rides=new ArrayList<>();
		isAvailable=true;
	}

	public char getLocation() {
		return location;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "location= " + location + ", ID=" + id + "";
	}
	
	public void addRide(Ride ride) {
		this.location=ride.getDestination();
		rides.add(ride);
		this.fare+=ride.getFare();
		this.driver=ride.getDriver();
	}
	
	public boolean isAvailable() {
		return isAvailable;
	}
	
	public void setAvailable(boolean isAvailable) {
		this.isAvailable=isAvailable;
	}
	
	public double getFare() {
		return fare;
	}
	
	public void setDriver(Driver driver) {
		this.driver=driver;
	}
	
	public Driver getDriver() {
		return driver;
	}

	public List<Ride> getRides() {
		return rides;
	}	
}
