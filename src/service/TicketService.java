package service;

import model.Ticket;
import model.Passenger;
import model.Bus;
import exception.TicketNotFoundException;

import java.util.ArrayList;

public class TicketService {

    // list to store all booked tickets
    private ArrayList<Ticket> tickets = new ArrayList<>();

    // Book a ticket
    public void bookTicket(Ticket ticket) {
        tickets.add(ticket);
        System.out.println("Ticket booked successfully.");
    }

    // method overloading to book a ticket
    public void bookTicket(String ticketId, Passenger passenger, Bus bus, String seatNumber, String travelDate, double fare) {

        Ticket ticket = new Ticket(ticketId, passenger, bus, seatNumber, travelDate, fare);
        tickets.add(ticket);
        System.out.println("Ticket booked successfully.");
        
    }

    // display all tickets
    public void displayAllTickets() {

        if (tickets.isEmpty()) {
            System.out.println("No tickets booked.");
            return;
        }

        for (Ticket ticket : tickets) {
            ticket.displayInfo();
        }
    }

    // search for a ticket by its ID
    public Ticket searchTicket(String ticketId) throws TicketNotFoundException {
        for (Ticket ticket : tickets) {
            if (ticket.getTicketId().equalsIgnoreCase(ticketId)) {
                return ticket;
            }
        }
        throw new TicketNotFoundException("Ticket not found with ID: " + ticketId);
    }

    // cancel a ticket by its ID
    public void cancelTicket(String ticketId) {
        try{
            Ticket ticket = searchTicket(ticketId);
            tickets.remove(ticket);
            System.out.println("Ticket cancelled successfully.");

        } catch (TicketNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Cancel ticket operation completed.");
        }  
    }

    // update ticket details
    public void updateTicket(String ticketId, String newSeatNumber, String newTravelDate, double newFare) {
        try{
            Ticket ticket = searchTicket(ticketId);
        
            ticket.setSeatNumber(newSeatNumber);
            ticket.setTravelDate(newTravelDate);
            ticket.setFare(newFare);

            System.out.println("Ticket updated successfully.");

        } catch(TicketNotFoundException e){
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Update ticket operation completed.");
        }
    }
    
    public ArrayList<Ticket> getTickets() {
        return tickets;
     }
}
