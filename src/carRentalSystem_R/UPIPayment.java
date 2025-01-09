package carRentalSystem_R;

public class UPIPayment implements Payment {

	@Override
	public void pay(double amount) {
		System.out.println("Paid By UPI " + amount);
	}
}
