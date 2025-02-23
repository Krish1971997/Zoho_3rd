package lunchHallBooking;

import java.util.*;

public class HallManager {
    private Map<Integer, LunchHall> hallMap;

    public HallManager() {
        this.hallMap = new HashMap<>();
    }

    // Add a new hall
    public void addHall(LunchHall hall) {
        if (hallMap.containsKey(hall.getHallId())) {
            System.out.println("Hall with ID " + hall.getHallId() + " already exists.");
            return;
        }
        hallMap.put(hall.getHallId(), hall);
        System.out.println("Lunch Hall added successfully: " + hall.getName());
    }

    // Update an existing hall
    public void updateHall(int hallId, String newName, String newLocation, int newCapacity, List<String> newAmenities) {
        LunchHall hall = hallMap.get(hallId);
        if (hall == null) {
            System.out.println("Hall not found with ID: " + hallId);
            return;
        }
        hall = new LunchHall(hallId, newName, newLocation, newCapacity, newAmenities);
        hallMap.put(hallId, hall);
        System.out.println("Lunch Hall updated successfully: " + newName);
    }

    // Delete a hall
    public void deleteHall(int hallId) {
        if (hallMap.remove(hallId) != null) {
            System.out.println("Lunch Hall deleted successfully with ID: " + hallId);
        } else {
            System.out.println("Hall not found with ID: " + hallId);
        }
    }

    // View all halls
    public void viewAllHalls() {
        if (hallMap.isEmpty()) {
            System.out.println("No halls available.");
        } else {
            System.out.println("Available Lunch Halls:");
            for (LunchHall hall : hallMap.values()) {
                System.out.println("ID: " + hall.getHallId() + ", Name: " + hall.getName() + 
                                   ", Location: " + hall.getLocation() + ", Capacity: " + hall.getCapacity());
            }
        }
    }
}
