package carRentalSystem;

public class CashPayement implements Payment {
	@Override
	public void pay(double amount) {
		System.out.println("Paid By CashPayement " + amount);
	}
}
