package trainticketBookingRajesh;

public class Customer extends Person {

	public Customer(String name, String id) {
		super(name, id,Role.USER);
	}
	
	public Ticket bookTicket(Train train,String source, String destination,int numOfTickets ) {
		TrainReservationSystem system = TrainReservationSystem.getInstance();
		Ticket ticket = system.bookTicket(train,this,source, destination, numOfTickets);
		return ticket;
	}
	
	public void cancelTicket(Ticket ticket,int numOfTickets) {
		
	}

}
