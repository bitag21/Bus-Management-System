package service;

import model.Ticket;
import model.Passenger;
import model.Bus;

import java.util.ArrayList;

public class TicketService {
    private ArrayList<Ticket> tickets = new ArrayList<>();

    //book a ticket
    public void bookTicket(Ticket ticket) {
        tickets.add(ticket);
        System.out.println("Ticket booked successfully.");
    }

    //merhod overloading to book a ticket
    public void bookTicket(String ticketId, Passenger passenger, Bus bus, String seatNumber, String travelDate, double fare) {

        Ticket ticket = new Ticket(ticketId, passenger, bus, seatNumber, travelDate, fare);
        tickets.add(ticket);
        System.out.println("Ticket booked successfully.");
        
    }

    //display all tickets
    public void displayAllTickets() {

        if (tickets.isEmpty()) {
            System.out.println("No tickets booked.");
            return;
        }

        for (Ticket ticket : tickets) {
            ticket.displayInfo();
        }
    }

    //search for a ticket by its ID
    public Ticket searchTicket(String ticketId) {
        for (Ticket ticket : tickets) {
            if (ticket.getTicketId().equalsIgnoreCase(ticketId)) {
                return ticket;
            }
        }
        return null; // Return null if ticket not found
    }

    //cancel a ticket by its ID
    public void cancelTicket(String ticketId) {
        Ticket ticket = searchTicket(ticketId);

        if (ticket != null) {
            tickets.remove(ticket);
            System.out.println("Ticket cancelled successfully.");

        }else{
            System.out.println("Ticket not found.");
        }  
    }

    //update ticket details
    public void updateTicket(String ticketId, String newSeatNumber, String newTravelDate, double newFare) {
        Ticket ticket = searchTicket(ticketId);

        if (ticket != null) {
        
            ticket.setSeatNumber(newSeatNumber);
            ticket.setTravelDate(newTravelDate);
            ticket.setFare(newFare);

            System.out.println("Ticket updated successfully.");

        } else {
            System.out.println("Ticket not found.");
        }
    }
    
    public ArrayList<Ticket> getTickets() {
        return tickets;
     }
}

