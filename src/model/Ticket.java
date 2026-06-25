package model;

import java.io.Serializable;

public class Ticket implements Serializable{
    private String ticketId;
    private Passenger passenger;
    private Bus bus;
    private String seatNumber;
    private String travelDate;
    private double fare;

    // constructor to initialize a ticket object 
    public Ticket(String ticketId, Passenger passenger, Bus bus, String seatNumber, String travelDate, double fare) {
        this.ticketId = ticketId;
        this.passenger = passenger;
        this.bus = bus;
        this.seatNumber = seatNumber;
        this.travelDate = travelDate;
        this.fare = fare;
    }

    // getters
    public String getTicketId() {
        return ticketId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Bus getBus() {
        return bus;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public double getFare() {
        return fare;
    }

    // setters
    public void setTicketId(String ticketId) {
    if (ticketId == null || ticketId.trim().isEmpty()) {
        throw new IllegalArgumentException("Ticket ID cannot be empty");
    }
    this.ticketId = ticketId;
}
   public void setPassenger(Passenger passenger) {
    if (passenger == null) {
        throw new IllegalArgumentException("Passenger cannot be null");
    }
    this.passenger = passenger;
}

   public void setBus(Bus bus) {
    if (bus == null) {
        throw new IllegalArgumentException("Bus cannot be null");
    }
    this.bus = bus;
}

   public void setSeatNumber(String seatNumber) {
    if (seatNumber == null || seatNumber.trim().isEmpty()) {
        throw new IllegalArgumentException("Seat number cannot be empty");
    }
    this.seatNumber = seatNumber;
}
  
    public void setTravelDate(String travelDate) {
    if (travelDate == null || travelDate.trim().isEmpty()) {
        throw new IllegalArgumentException("Travel date cannot be empty");
    }
    this.travelDate = travelDate;
}

    public void setFare(double fare) {
        if(fare < 0){
            throw new IllegalArgumentException("Fare can't be negative.");
        }
        this.fare = fare;
    }

    // method to display ticket information
    public void displayInfo() {
        System.out.println("====Ticket Information====");
        System.out.println("Ticket ID: " + getTicketId());
        System.out.println("Passenger Name: " + getPassenger().getName());
        System.out.println("Bus Number: " + getBus().getBusNumber());
        System.out.println("Seat Number: " + getSeatNumber());
        System.out.println("Travel Date: " + getTravelDate());
        System.out.println("Fare: " + getFare());
        System.out.println("==========================");
    }
   @Override
public String toString() {
    return "Ticket{" +
            "ticketId='" + ticketId + '\'' +
            ", passenger='" + (passenger != null ? passenger.getName() : "null") + '\'' +
            ", bus='" + (bus != null ? bus.getBusNumber() : "null") + '\'' +
            ", seatNumber='" + seatNumber + '\'' +
            ", travelDate='" + travelDate + '\'' +
            ", fare=" + fare +
            '}';
}
}
