package lunchHallBooking;

import java.util.List;

public class LunchHall {
	private int hallId;
	private String name;
	private String location;
	private int capacity;
	private List<String> amenities;

	public LunchHall(int hallId, String name, String location, int capacity, List<String> amenities) {
		this.hallId = hallId;
		this.name = name;
		this.location = location;
		this.capacity = capacity;
		this.amenities = amenities;
	}

	public int getHallId() {
		return hallId;
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public int getCapacity() {
		return capacity;
	}

	public List<String> getAmenities() {
		return amenities;
	}
}
