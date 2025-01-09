package trainticketBookingRajesh;

public class Admin extends Person {

	public Admin(String name, String id) {
		super(name, id, Role.ADMIN);
	}

	public void addTrain(Train train) {
		TrainReservationSystem system = TrainReservationSystem.getInstance();
		system.addTrain(train);
	}

	public void removeTrain(Train train) {
		TrainReservationSystem system = TrainReservationSystem.getInstance();
		system.removeTrain(train);

	}

}
