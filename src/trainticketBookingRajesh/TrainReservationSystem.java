package trainticketBookingRajesh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainReservationSystem {
	private Map<String, Train> trainMap = new HashMap<>();
	private Map<String, Ticket> ticketInfo = new HashMap<>();
	private Map<String, Ticket> waitingListTicketInfo = new HashMap<>();
	private TicketManager ticketManager;
	private int pnr = 0;

	private static TrainReservationSystem instance = null;

	private TrainReservationSystem() {
		ticketManager = new TicketManager();

	}

	public void setTicketInfo(String pnr, Ticket ticket) {
		ticketManager.setTicketInfo(pnr, ticket);
	}

	public Map<String, Ticket> getWaitingListTicketInfo() {
		return waitingListTicketInfo;
	}

	public void setWaitingListTicketInfo(Map<String, Ticket> waitingListTicketInfo) {
		this.waitingListTicketInfo = waitingListTicketInfo;
	}

	public Ticket getTicket(String Pnr) {
		return ticketManager.getTicketInfo().get(Pnr);
	}

	public void ticketHistory() {

		for (Map.Entry<String, Ticket> info : ticketManager.getTicketInfo().entrySet()) {
			System.out.println(info.getValue());
		}
	}

	public static TrainReservationSystem getInstance() {
		if (instance == null) {
			instance = new TrainReservationSystem();
		}
		return instance;
	}

	public void addTrain(Train train) {
		trainMap.put(train.getId(), train);
	}

	public void ticketStatusByPnr(String pnr) {
		Ticket ticket = ticketInfo.get(pnr);
		if (ticket == null) {
			System.out.println("Invalid PNR");
		} else
			System.out.println(ticket);
	}

	public Ticket bookTicket(Train train, Customer customer, String source, String destination, int numOfTickets) {
		return ticketManager.bookTicket(train, customer, source, destination, numOfTickets);

	}

	public void removeTrain(Train train) {
		trainMap.remove(train.getId());
	}
}
