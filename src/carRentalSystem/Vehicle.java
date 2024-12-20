package carRentalSystem;

import java.time.LocalDate;

public class Vehicle {
	private String licenceNum;
	private String barcode;
	private String model;
	private boolean hasRoof;
	private int totalSeats;
	private String compName;
	private LocalDate startDate;
	private LocalDate endDate;
	private VehicleType type;
	private VehicleAvailbityType availabity;
	

	public Vehicle(String licenceNum, String barcode, String model, boolean hasRoof, int totalSeats, String compName,
			VehicleType type) {
		this.licenceNum = licenceNum;
		this.barcode = barcode;
		this.model = model;
		this.hasRoof = hasRoof;
		this.totalSeats = totalSeats;
		this.compName = compName;
		this.type = type;
		this.availabity = VehicleAvailbityType.AVAILABLE;
	}
	
	
	public String getBarcode() {
		return barcode;
	}

	public String getLicenceNum() {
		return licenceNum;
	}

	public void setLicenceNum(String licenceNum) {
		this.licenceNum = licenceNum;
	}

	public VehicleType getType() {
		return type;
	}

	public void setType(VehicleType type) {
		this.type = type;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public VehicleAvailbityType getAvailabity() {
		return availabity;
	}

	public void setAvailabity(VehicleAvailbityType availabity) {
		this.availabity = availabity;
	}

	@Override
	public String toString() {
		return "Vehicle [licenceNum=" + licenceNum + ", model=" + model + ", hasRoof=" + hasRoof + ", totalSeats="
				+ totalSeats + ", compName=" + compName + ", availabity=" + availabity + "]";
	}
	
	

}
