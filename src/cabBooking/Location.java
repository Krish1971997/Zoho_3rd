package cabBooking;

public class Location {
	private int id;
	private char name;
	private int distanceFromOrigin;
	
	public Location(int id, char name, int distanceFromOrigin) {
		super();
		this.id = id;
		this.name = name;
		this.distanceFromOrigin = distanceFromOrigin;
	}

	public int getId() {
		return id;
	}

	public char getName() {
		return name;
	}

	public int getDistanceFromOrigin() {
		return distanceFromOrigin;
	}
}
