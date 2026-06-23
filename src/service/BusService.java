package service;

import model.Bus;
import model.Driver;
import java.util.ArrayList;
import exception.BusNotFoundException;

public class BusService {

    private ArrayList<Bus> buses = new ArrayList<>();

    // Add a new bus to the system
    public void addBus(Bus bus) {
        buses.add(bus);
        System.out.println("Bus added successfully. ");
    }

    //method overloading to add a bus with driver details
    public void addBus(String busId, String busNumber, int capacity, String route, Driver driver) {
        Bus bus = new Bus(busId, busNumber, capacity, route, driver);
        buses.add(bus);

        System.out.println("Bus with driver added successfully. ");
    }

    //display all buses in the system
    public void displayAllBuses() {
        if (buses.isEmpty()) {
            System.out.println("No buses available in the system.");
            return;
        }

        for (Bus bus : buses) {
            bus.displayInfo();
        }
    }

    //search for a bus by its ID
    public Bus searchBus(String busId) throws BusNotFoundException {

        for (Bus bus : buses) {

            if (bus.getBusId().equalsIgnoreCase(busId)) {
                return bus;
            }
        }
        throw new BusNotFoundException("Bus not found with ID: " + busId);
    }

    //deletea bus from the system by its ID
    public void deleteBus(String busId) {
        try {
            Bus bus = searchBus(busId);
            buses.remove(bus);
            System.out.println("Bus deleted successfully.");

        } catch (BusNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Delete bus operation completed.");
        }
    }

    //update bus details by its ID
    public void updateBus(String busId, String newRoute, int newCapacity) {
        try {
            Bus bus = searchBus(busId);
            bus.setRoute(newRoute);
            bus.setCapacity(newCapacity);

            System.out.println("Bus updated successfully.");

        } catch (BusNotFoundException e) {
            System.out.println(e.getMessage());

        } finally {
            System.out.println("Update bus operation completed.");
        }

    }

    public ArrayList<Bus> getBuses() {
        return buses;
    }
}
