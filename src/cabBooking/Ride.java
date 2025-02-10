package cabBooking;

public class Ride {
	static int idGenerater = 1;
	private int id;
	private char source;
	private char destination;
	private Cab cab;
	private Driver driver;
	private Customer customer;
	private int fare;
	private int zulaComm;

	public Ride(char source, char destination, Cab cab, Customer customer, int fare) {
		super();
		this.id = idGenerater++;
		this.source = source;
		this.destination = destination;
		this.cab = cab;
		this.driver = cab.getDriver();
		this.customer = customer;
		this.fare = fare;
		this.zulaComm = (int) (fare * 0.3);
	}

	public int getId() {
		return id;
	}

	public char getSource() {
		return source;
	}

	public char getDestination() {
		return destination;
	}

	public Cab getCab() {
		return cab;
	}

	public Driver getDriver() {
		return driver;
	}

	public Customer getCustomer() {
		return customer;
	}

	public int getFare() {
		return fare;
	}

	public int getZulaComm() {
		return zulaComm;
	}

	@Override
	public String toString() {
		return "Ride [id=" + id + ", source=" + source + ", destination=" + destination + ", cab=" + cab + ", driver="
				+ driver + ", customer=" + customer + ", fare=" + fare + ", zulaComm=" + zulaComm + "]";
	}
}
