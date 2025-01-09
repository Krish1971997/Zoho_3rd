package trainticketBookingRajesh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

public class Train {
	private String id;
	private List<Station> stoppages;
	private final int MAXAVILABLETICKETS = 8;
	private int MAXAVAILABLEWAITINGLISTTICETS = 2;
	private int currentConfirmedTickets = 0;
	private int currentWaitingTickets = 0;
	private int[][] trainSeatsInfo;
	private ArrayList<Integer>[] seats;
	private TrainReservationSystem system = TrainReservationSystem.getInstance();
	private Queue<Ticket> waitingQueue = new LinkedList<>();

	public Ticket bookTicket(Customer customer, String source, String destination, int passengerCount) {
		return system.bookTicket(this, customer, source, destination, passengerCount);

	}

	public List<Integer> getAvailbleSeatNums(String source, String destination) {
		int sourceIndex = getStationIndex(source);
		int destinationIndex = getStationIndex(destination);
		return seats[sourceIndex * trainSeatsInfo.length + destinationIndex];

	}

	public void updatewaitingTickets(String source, String destination, int ticketCount, Ticket ticket) {
		currentWaitingTickets = currentWaitingTickets + ticketCount;
		waitingQueue.add(ticket);

	}

	public Ticket cancelTicket(String PnrID, String count) {
		Ticket ticket = system.getTicket(PnrID);
		int currentCount = ticket.getPassCount();
		if (Integer.parseInt(count) > currentCount) {
			System.out.println("Invalid passenger Count");
			return null;
		}
		ticket.setPassCount(currentCount - Integer.parseInt(count));

		int countVal = Integer.parseInt(count);

		while (!waitingQueue.isEmpty()) {
			Ticket waitingTicket = waitingQueue.poll();

			if (MAXAVILABLETICKETS - currentConfirmedTickets >= waitingTicket.getPassCount()) {
				if (waitingTicket.getPassCount() == 1) {
					currentWaitingTickets--;
					countVal--;
				} else {
					currentWaitingTickets = currentWaitingTickets - 2;
					countVal = countVal - 2;
				}
				List<Integer> confirmedSeats = ticket.getSeat();
				for (int i = 0; i < waitingTicket.getPassCount(); i++) {
					waitingTicket.addSeats(confirmedSeats.remove(0));
				}
				waitingTicket.setStatus(TicketConfirmedStatus.CONFIRMED);
				system.setTicketInfo(waitingTicket.getPnrNumber(), waitingTicket);
			}

		}
		updateTicketsWhenCancel(ticket, countVal);

		return ticket;

	}

	public void updateTicketsWhenCancel(Ticket ticket, int ticketCount) {
		System.out.println("TICKET COUNT : " + ticketCount);
		int sourceIndex = getStationIndex(ticket.getSource());
		int destinationIndex = getStationIndex(ticket.getDestination());
		for (int i = 0; i < destinationIndex; i++) {
			for (int j = sourceIndex + 1; j < trainSeatsInfo.length; j++) {
				if (i < j)
					trainSeatsInfo[i][j] = trainSeatsInfo[i][j] + ticketCount;
			}

		}
		List<Integer> confirmedSeats = ticket.getSeat();
		List<Integer> cancelledSeats = new ArrayList<>();
		for (int i = 0; i < ticketCount; i++) {
			cancelledSeats.add(confirmedSeats.remove(0));
		}

		for (int i = 0; i < destinationIndex; i++) {
			for (int j = sourceIndex + 1; j < trainSeatsInfo.length; j++) {
				if (i < j) {
					List<Integer> list = getAvailbleSeatNums(stoppages.get(i).getCode(), stoppages.get(j).getCode());

					if (ticket.getPnrNumber().equals("3")) {
						System.out.println(trainSeatsInfo[i][j] + " " + cancelledSeats.size());
					}

					if (trainSeatsInfo[i][j] < cancelledSeats.size()) {
						list.addAll(cancelledSeats);
					}

					else if (Math.abs(trainSeatsInfo[i][j]) < cancelledSeats.size()) {

						list.addAll(cancelledSeats.subList(0 + cancelledSeats.size() + trainSeatsInfo[i][j],
								cancelledSeats.size()));

					}

					else
						list.addAll(cancelledSeats);

					Collections.sort(list);

				}
			}
		}

	}

	public void updateTickets(String source, String destination, int ticketCount) {
		int sourceIndex = getStationIndex(source);
		int destinationIndex = getStationIndex(destination);

		for (int i = 0; i < destinationIndex; i++) {
			for (int j = sourceIndex + 1; j < trainSeatsInfo.length; j++) {
				if (i < j) {
					// if (trainSeatsInfo[i][j] > 0) {
					trainSeatsInfo[i][j] = trainSeatsInfo[i][j] - ticketCount;
					// }
				}
			}
		}
		for (int i = 0; i < destinationIndex; i++) {
			for (int j = sourceIndex + 1; j < trainSeatsInfo.length; j++) {
				if (i < j) {
					List<Integer> list = getAvailbleSeatNums(stoppages.get(i).getCode(), stoppages.get(j).getCode());
					for (int k = 0; k < ticketCount; k++) {
						if (list.size() > 0)
							list.remove(0);
					}
				}
			}
		}

	}

	public int getStationIndex(String name) {
		int count = 0;
		for (Station station : stoppages) {
			if (station.getCode().equals(name)) {
				return count;
			}
			count++;
		}
		return -1;

	}

	public List<Station> getStoppages() {
		return stoppages;
	}

	public int[][] getTrainSeatsInfo() {
		return trainSeatsInfo;
	}

	public void setTrainSeatsInfo(int[][] trainSeatsInfo) {
		this.trainSeatsInfo = trainSeatsInfo;
	}

	public Train(String id) {
		this.id = id;
		this.stoppages = new ArrayList<>();
	}

	public void addStoppages(Station station) {
		stoppages.add(station);
		trainSeatsInfo = new int[stoppages.size()][stoppages.size()];
		for (int i = 0; i < trainSeatsInfo.length; i++) {
			Arrays.fill(trainSeatsInfo[i], MAXAVILABLETICKETS);
		}
		for (int i = 0; i < trainSeatsInfo.length; i++) {
			for (int j = 0; j <= i; j++) {
				trainSeatsInfo[i][j] = 0;
			}
		}

		seats = new ArrayList[trainSeatsInfo.length * trainSeatsInfo.length];
		for (int i = 0; i < seats.length; i++) {
			seats[i] = new ArrayList<>();
			seats[i].addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));

		}
		System.out.println("---------------------");

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int availableConfirmSeats() {
		return MAXAVILABLETICKETS - currentConfirmedTickets;
	}

	public int availableWaitingSeats() {
		return MAXAVAILABLEWAITINGLISTTICETS - currentWaitingTickets;
	}
}
