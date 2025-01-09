package trainticketBookingRajesh;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Ticket {
	private String pnrNumber;
	private String source;
	private String destination;
	private TicketConfirmedStatus status;
	private List<Integer> seat = new ArrayList<>();
	private int passCount;

	public Ticket(String pnrNumber, String source, String destination, int passCount, TicketConfirmedStatus status) {
		this.pnrNumber = pnrNumber;
		this.source = source;
		this.destination = destination;
		this.passCount = passCount;
		this.status = status;
	}

	public void addSeats(int seatNo) {
		seat.add(seatNo);

	}

	public TicketConfirmedStatus getStatus() {
		return status;
	}

	public void setStatus(TicketConfirmedStatus status) {
		this.status = status;
	}

	public String getPnrNumber() {
		return pnrNumber;
	}

	public void setPnrNumber(String pnrNumber) {
		this.pnrNumber = pnrNumber;
	}

	public int getPassCount() {
		return passCount;
	}

	public void setPassCount(int passCount) {
		this.passCount = passCount;
	}

	public String getSource() {
		return source;
	}

	public String getDestination() {
		return destination;
	}

	public List<Integer> getSeat() {
		return seat;
	}

	public void setSeat(List<Integer> seat) {
		this.seat = seat;
	}

	@Override
	public String toString() {
		return "Ticket [pnrNumber=" + pnrNumber + ", source=" + source + ", destination=" + destination + ", status="
				+ status + ", seat=" + seat + ", passCount=" + passCount + "]";
	}

}
