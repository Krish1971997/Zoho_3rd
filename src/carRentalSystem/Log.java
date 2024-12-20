package carRentalSystem;

import java.time.LocalDate;

public class Log {
	LocalDate date;
	VehicleAvailbityType type;
	Customer customer;

	public Log(LocalDate date, VehicleAvailbityType type, Customer customer) {
		this.date = date;
		this.type = type;
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Log [date=" + date + ", type=" + type + ", customer=" + customer + "]";
	}

	

}
