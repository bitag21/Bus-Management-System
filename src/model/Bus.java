package model;

import java.io.Serializable;

public class Bus implements Serializable {
    private String busId;
    private String busNumber;
    private int capacity;
    private String route;
    private Driver driver;

    //constructor
    public Bus(String busId, String busNumber, int capacity, String route, Driver driver) {
        this.busId = busId;
        this.busNumber = busNumber;
        setCapacity(capacity);
        this.route = route;
        this.driver = driver;
    }

    //getters
    public String getBusId() {
        return busId;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getRoute() {
        return route;
    }

    public Driver getDriver() {
        return driver;
    }

    //setters
   public void setBusId(String busId) {
    if (busId == null || busId.trim().isEmpty()) {
        throw new IllegalArgumentException("Bus ID cannot be empty");
    }
    this.busId = busId;
}

  public void setBusNumber(String busNumber) {
    if (busNumber == null || busNumber.trim().isEmpty()) {
        throw new IllegalArgumentException("Bus number cannot be empty");
    }
    this.busNumber = busNumber;
}

    public void setCapacity(int capacity) {
        if(capacity < 0){
            throw new IllegalArgumentException("Capacity can't be negative.");
        }
        this.capacity = capacity;
    }

    public void setRoute(String route) {
        this.route = route;
    }   

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    //method to display bus information
    public void displayInfo() {
        System.out.println("====Bus Information====");
        System.out.println("Bus ID: " + getBusId());
        System.out.println("Bus Number: " + getBusNumber());
        System.out.println("Capacity: " + getCapacity());
        System.out.println("Route: " + getRoute());

        if (driver != null) {
            System.out.println("Driver : " + driver.getName());
        
        } else {
            System.out.println("Driver : Not assigned" );
        }
        System.out.println("=======================");
    }
@Override
public String toString() {
    return "Bus{" +
            "busId='" + busId + '\'' +
            ", busNumber='" + busNumber + '\'' +
            ", capacity=" + capacity +
            ", route='" + route + '\'' +
            ", driver=" + (driver != null ? driver.getName() : "Not assigned") +
            '}';
}
}
