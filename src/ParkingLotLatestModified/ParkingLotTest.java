package ParkingLotLatestModified;


import ParkingLotLatestModified.core.EntrancePanel;
import ParkingLotLatestModified.core.ExitPanel;
import ParkingLotLatestModified.core.ParkingDisplayBoard;
import ParkingLotLatestModified.core.ParkingFloor;
import ParkingLotLatestModified.core.ParkingFullException;
import ParkingLotLatestModified.core.ParkingLot;
import ParkingLotLatestModified.core.ParkingTicket;
import ParkingLotLatestModified.parkingSpots.CompactSpot;
import ParkingLotLatestModified.parkingSpots.ElectricSpot;
import ParkingLotLatestModified.parkingSpots.LargeSpot;
import ParkingLotLatestModified.parkingSpots.MotorbikeSpot;
import ParkingLotLatestModified.persons.Admin;
import ParkingLotLatestModified.vehicles.Car;
import ParkingLotLatestModified.vehicles.Motorbike;
import ParkingLotLatestModified.vehicles.Truck;
import ParkingLotLatestModified.vehicles.Vehicle;

// Rajesh Zoho class
public class ParkingLotTest {

    public static void main(String[] args) {
        // Create a test parking lot
        ParkingLot parkingLot = ParkingLot.getInstance();
        Admin admin = new Admin();
        
        // Create parking spots
        ParkingFloor floor1 = new ParkingFloor("Floor 1");
        floor1.addParkingSpot(new CompactSpot("C1"));
        floor1.addParkingSpot(new LargeSpot("L1"));
        floor1.addParkingSpot(new MotorbikeSpot("M1"));
        floor1.addParkingSpot(new ElectricSpot("E1"));
        
        // Add floor to the parking lot
        admin.addParkingFloor(floor1);
        
        // Create and assign entrance panels
        EntrancePanel entrancePanel = new EntrancePanel("Entrance 1");
        parkingLot.addEntrancePanel(entrancePanel);
  
        // Create and assign exit panels
        ExitPanel exitPanel = new ExitPanel("Exit 1");
        parkingLot.addExitPanel(exitPanel);

        // Create some vehicles
        Vehicle car = new Car("CAR123");
        Vehicle truck = new Truck("TRK123");
        Vehicle motorbike = new Motorbike("MB123");
        
        // Test: Add new parking tickets
        System.out.println("\n-- Test 1: Issuing Parking Tickets --");
        try {
            ParkingTicket carTicket = entrancePanel.generateParkingTicket(car);
            ParkingTicket truckTicket = parkingLot.getNewParkingTicket(truck);
            ParkingTicket motorbikeTicket = parkingLot.getNewParkingTicket(motorbike);

            // Check if tickets are issued correctly
            System.out.println("Issued car ticket: " + carTicket);
            System.out.println("Issued truck ticket: " + truckTicket.getTicketNumber());
            System.out.println("Issued motorbike ticket: " + motorbikeTicket.getTicketNumber());
        } catch (ParkingFullException e) {
            System.out.println("Parking is full! Could not issue ticket.");
        }

        // Display the current active tickets
        System.out.println("\nActive Tickets: ");
        for (String ticketNumber : parkingLot.getActiveTickets().keySet()) {
            System.out.println("Ticket number: " + ticketNumber);
        }

        // Test: Process vehicle exit and remove tickets
        System.out.println("\n-- Test 2: Processing Vehicle Exit --");
        ParkingTicket carTicket = parkingLot.getActiveTicket("CAR123");
       // exitPanel.closeParkingTicket(carTicket.getTicketNumber());
        exitPanel.closeParkingTicket("CAR123");

        ParkingTicket truckTicket = parkingLot.getActiveTicket("TRK123");
        exitPanel.closeParkingTicket("TRK123");

        // Display the updated active tickets after vehicle exit
        System.out.println("\nActive Tickets After Exit: ");
        for (String ticketNumber : parkingLot.getActiveTickets().keySet()) {
            System.out.println("Ticket number: " + ticketNumber);
        }

        // Test: Check if the parking spots are freed
        System.out.println("\n-- Test 3: Checking Parking Spot Counts --");
        System.out.println("Compact Spot Count: " + parkingLot.getCompactSpotCount());
        System.out.println("Large Spot Count: " + parkingLot.getLargeSpotCount());
        System.out.println("Motorbike Spot Count: " + parkingLot.getMotorbikeSpotCount());
        System.out.println("Electric Spot Count: " + parkingLot.getElectricSpotCount());

        // Test: Parking full check
        System.out.println("\n-- Test 4: Checking if parking is full --");
        System.out.println("Is Parking Full? " + parkingLot.isFull());
        
        parkingLot.displayBoard();
        
     

    }
}

