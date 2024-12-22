package carRentalSystem;


public class RentalInsurance {
	private double totalSum;

	public RentalInsurance( double totalSum) {
		this.totalSum = totalSum;
	}

	public void pay(Double amount) {
		totalSum = totalSum - amount;
	}
}
