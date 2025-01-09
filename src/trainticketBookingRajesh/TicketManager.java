package trainticketBookingRajesh;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class TicketManager {
	private int pnr = 0;
	private Map<String, Ticket> ticketInfo = new HashMap<>();
	private Map<String, Ticket> waitingListTicketInfo = new HashMap<>();

	public Map<String, Ticket> getTicketInfo() {
		return ticketInfo;
	}

	public void setTicketInfo(String id, Ticket ticket) {
		ticketInfo.put(id, ticket);
	}

	public Map<String, Ticket> getWaitingListTicketInfo() {
		return waitingListTicketInfo;
	}

	public Ticket bookTicket(Train train, Customer customer, String source, String destination, int numOfTickets) {

		if (train == null || source == null || destination == null || numOfTickets <= 0) {
			System.out.println("Invalid input parameters.");
			return null;
		}

		int availableWaitingTickets = train.availableWaitingSeats();
		int sourceIndex = train.getStationIndex(source);
		int destinationIndex = train.getStationIndex(destination);

		// Check for valid source and destination
		if (sourceIndex >= destinationIndex) {
			System.out.println("Booking not possible. Source is after destination.");
			return null;
		}

		int availableConfirmTickets = train.getTrainSeatsInfo()[sourceIndex][destinationIndex];

		if (availableConfirmTickets >= numOfTickets) {
			// Confirmed tickets booking
			pnr++;
			Ticket ticket = new Ticket(String.valueOf(pnr), source, destination, numOfTickets,
					TicketConfirmedStatus.CONFIRMED);
			ticketInfo.put(String.valueOf(pnr), ticket);

			List<Integer> seats = train.getAvailbleSeatNums(source, destination);
			for (int i = 0; i < numOfTickets; i++) {
				ticket.addSeats(seats.get(i));
			}

			System.out.println("Ticket booked successfully: " + ticket);
			train.updateTickets(source, destination, numOfTickets);
			return ticket;

		} else if (availableWaitingTickets >= numOfTickets) {
			// Waiting list tickets booking
			pnr++;
			Ticket ticket = new Ticket(String.valueOf(pnr), source, destination, numOfTickets,
					TicketConfirmedStatus.WAITING);
			System.out.println("Ticket booked and added to waiting list. PNR: " + pnr);
			ticketInfo.put(String.valueOf(pnr), ticket);
			waitingListTicketInfo.put(String.valueOf(pnr), ticket);
			train.updatewaitingTickets(source, destination, numOfTickets, ticket);

			System.out.println(ticket);
			return ticket;

		} else {
			// No available tickets
			System.out.println("Booking not possible, no available tickets.");
			return null;
		}
	}
}
