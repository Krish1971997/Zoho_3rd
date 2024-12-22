package carRentalSystem;

public class UPIPayment implements Payment {

	@Override
	public void pay(double amount) {
		System.out.println("Paid By UPI " + amount);
	}
}
