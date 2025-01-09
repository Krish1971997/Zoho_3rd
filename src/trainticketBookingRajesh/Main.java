package trainticketBookingRajesh;

import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		TrainReservationSystem system = TrainReservationSystem.getInstance();
		Admin admin = new Admin("RAJESH", "A-01");
		Train train1 = new Train("T-001");
		train1.addStoppages(new Station("Station A", "A"));
		train1.addStoppages(new Station("Station B", "B"));
		train1.addStoppages(new Station("Station C", "C"));
		train1.addStoppages(new Station("Station D", "D"));
		train1.addStoppages(new Station("Station E", "E"));
		admin.addTrain(train1);

		Scanner scanner = new Scanner(System.in);

		while (true) {

			System.out.println("*****Enter Yout Option************");
			System.out.println("	1: for book ticket");
			System.out.println("	2: for ticket cancellation");
			System.out.println("	3: for PNR Check");
			System.out.println("	4: Print Summary");
			System.out.println("	5: Print Available Tickets");
			System.out.println("    6: Get AvialbleTickets at stations");
			System.out.println("	Any other for exit");

			System.out.println("*********************************");
			System.out.print("Enter Your Choice : ");

			String option = scanner.next();
			switch (option) {
			case "1":
				// System.out.println("Enter name and Id with space in same line");
				String name = "Hii";
				String id = "001";
				Customer customer = new Customer(name, id);
				System.out.println("Enter Source and Destination with space in same line");
				String source = scanner.next();
				String destination = scanner.next();
				System.out.println("Enter No of Seats");
				int passengerCount = scanner.nextInt();
				train1.bookTicket(customer, source, destination, passengerCount);
				break;
			case "2":
				System.out.print("Enter The PNR : ");
				String pnrid = scanner.next();
				System.out.print("Enter cancelledTickets num : ");
				String cancelledTickets = scanner.next();
				train1.cancelTicket(pnrid, cancelledTickets);
				break;
			case "3":
				System.out.println("Enter PNR ID");
				String ID = scanner.next();
				system.ticketStatusByPnr(ID);
				break;
			case "4":
				System.out.println("All Ticket History");
				system.ticketHistory();
				break;

			case "5":
				System.out.println(" Available Tickets ");
				int[][] tickets = train1.getTrainSeatsInfo();
				for (int i = 0; i < tickets.length; i++) {
					for (int j = 0; j < tickets[0].length; j++) {
						System.out.print(tickets[i][j] + " ");
					}
					System.out.println();
				}

				break;
			case "6":
				System.out.print("Enter Source Station : ");
				String sourceStation = scanner.next();
				System.out.print("Enter Destination Station : ");
				String destinationStation = scanner.next();
				List<Integer> seats = train1.getAvailbleSeatNums(sourceStation, destinationStation);
				System.out.println(seats);
				break;

			default:
				scanner.close();
				System.exit(0);
				break;
			}

		}
	}

}
