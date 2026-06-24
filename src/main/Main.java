package main;

import model.*;
import service.*;
import file.FileManager;
import database.DBConnection;
import exception.BusNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    static ArrayList<Passenger> passengers = new ArrayList<>();
    static ArrayList<Driver> drivers = new ArrayList<>();

    static BusService bS = new BusService();
    static TicketService tS = new TicketService();

    static FileManager fM = new FileManager();
    static DBConnection db = new DBConnection();

    public static void displayMenu(){
        
        System.out.println("\n============================");
        System.out.println("    BUS MANAGEMENT SYSTEM    ");
        System.out.println("============================");
        System.out.println("1. Add Driver ");
        System.out.println("2. Add Bus ");
        System.out.println("3. Add Passenger ");
        System.out.println("4. Book Ticket ");
        System.out.println("5. Display All Buses ");
        System.out.println("6. Display All Tickets ");
        System.out.println("7. Save Data to File ");
        System.out.println("8. Load Data from File ");
        System.out.println("9. Insert Passenger to DB");
        System.out.println("10. Show Passengers from DB");
        System.out.println("11. Search Passenger in Database");
        System.out.println("0. Exit");
    }
        
    public static void main(String[] args){

        while (true) {
            displayMenu();
                
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
                
            switch (choice) {
                case 1:
                    System.out.print("Enter Driver ID: ");
                    String did = scanner.nextLine();

                    System.out.print("Enter Name: ");
                    String dname = scanner.nextLine();

                    System.out.print("Enter Age: ");
                    int dage = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter Phone: ");
                    String dphone = scanner.nextLine();

                    System.out.print("Enter License Number: ");
                    String dlicense = scanner.nextLine();

                    System.out.print("Enter Experience Years: ");
                    int dexpy = scanner.nextInt();
                    scanner.nextLine();

                    try{
                        Driver driver = new Driver(did, dname, dage, dphone, dlicense, dexpy);
                        drivers.add(driver);

                        System.out.println("Driver added successfully.");

                        Person personRef = driver;
                        System.out.println("\n--- Confirming via Person reference ---");
                        personRef.displayInfo();

                    }catch (IllegalArgumentException e) {
                        System.out.println("Could not add driver: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter Bus ID: ");
                    String bid = scanner.nextLine();
                    
                    System.out.print("Enter Bus Number: ");
                    String bnum = scanner.nextLine();
                    
                    System.out.print("Enter Capacity: ");
                    int bcap = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter Route: ");
                    String broute = scanner.nextLine();

                    System.out.print("Enter Driver ID to assign : ");
                    String searchDid = scanner.nextLine();

                    Driver assignedDriver = null;
                        
                    for (Driver d : drivers) {
                        if (d.getId().equalsIgnoreCase(searchDid)) {
                            assignedDriver = d;
                            break;
                        }
                    }

                    if (assignedDriver == null){
                        System.out.println("Driver not found.");
                        break;
                    }
                    
                    try{
                        Bus bus = new Bus(bid, bnum, bcap, broute, assignedDriver);
                        bS.addBus(bus);

                    }catch (IllegalArgumentException e){
                        System.out.println("Could not add bus: " + e.getMessage());
                    }

                    break;
                    
                case 3:
                    System.out.print("Enter Passenger ID: ");
                    String pid = scanner.nextLine();

                    System.out.print("Enter Name: ");
                    String pname = scanner.nextLine();

                    System.out.print("Enter Age: ");
                    int page = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter Phone: ");
                    String pphone = scanner.nextLine();

                    System.out.print("Enter Email: ");
                    String pemail = scanner.nextLine();

                    try{
                        Passenger passenger = new Passenger(pid, pname, page, pphone, pemail);
                        passengers.add(passenger);

                        System.out.println("Passenger added successfully. ");

                        Person personRef = passenger;
                        System.out.println("\n--- Confirming via Person reference ---");
                        personRef.displayInfo();

                    } catch (IllegalArgumentException e) {
                        System.out.println("Could not add passenger: " + e.getMessage());
                    }

                    break;

                case 4:
                    System.out.print("Enter Ticket ID: ");
                    String tid = scanner.nextLine();

                    //find passenger
                    System.out.print("Enter Passenger ID: ");
                    String passengerId = scanner.nextLine();

                    Passenger selectedPassenger = null;

                    for (Passenger p : passengers) {
                        if (p.getId().equalsIgnoreCase(passengerId)) {
                            selectedPassenger = p;
                            break;
                        }
                    }

                    if (selectedPassenger == null) {
                        System.out.println("Passenger not found.");
                        break;
                    }

                    //find bus
                    System.out.print("Enter Bus ID: ");
                    String busId = scanner.nextLine();
                    Bus selectedBus = null;
                    
                    try {
                        selectedBus = bS.searchBus(busId);

                    } catch (BusNotFoundException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                    // ticket details 
                    System.out.print("Enter Seat Number: ");
                    String seat = scanner.nextLine();

                    System.out.print("Enter Travel Date: ");
                    String date = scanner.nextLine();

                    System.out.print("Enter Fare: ");
                    double fare = scanner.nextDouble();
                    scanner.nextLine();

                    try{
                        Ticket ticket = new Ticket(tid, selectedPassenger, selectedBus, seat, date, fare);
                        tS.bookTicket(ticket);

                    } catch(IllegalArgumentException e){
                        System.out.println("Could not book ticket: " + e.getMessage());
                    }

                    break;

                case 5:
                    bS.displayAllBuses();
                    break;

                case 6:
                    tS.displayAllTickets();
                    break;

                case 7:
                    fM.createFile("tickets.txt");
                    StringBuilder data = new StringBuilder();

                    for(Ticket t : tS.getTickets()){
                        data.append("Ticket ID: ").append(t.getTicketId()) 
                        .append("\nPassenger: ").append(t.getPassenger().getName()) 
                        .append("\nBus Number: ").append(t.getBus().getBusNumber()) 
                        .append("\nSeat Number: ").append(t.getSeatNumber()) 
                        .append("\nTravel Date: ").append(t.getTravelDate()) 
                        .append("\nFare: ").append(t.getFare()) 
                        .append("\n---------------------------\n"); 
                    }
                    //write the text file
                    fM.writeTextFile("tickets.txt", data.toString());
                    
                    //serialization
                    fM.saveObjects("tickets.dat", tS.getTickets());
                    break;

                case 8:
                    //read the text file
                    System.out.println("\n===== Tickets =====");
                    fM.readTextFile("tickets.txt"); 
                    
                    //load serialized objects
                    ArrayList<?> loaded = fM.loadObjects("tickets.dat");
                    System.out.println("\nLoaded Objects: " + loaded.size());
                    break;

                case 9:
                    System.out.print("Enter Passenger ID: "); 
                    String dbId = scanner.nextLine(); 

                    System.out.print("Enter Name: "); 
                    String dbName = scanner.nextLine(); 

                    System.out.print("Enter Age: "); 
                    int dbAge = scanner.nextInt(); 
                    scanner.nextLine(); 

                    System.out.print("Enter Phone: "); 
                    String dbPhone = scanner.nextLine(); 

                    System.out.print("Enter Email: "); 
                    String dbEmail = scanner.nextLine(); 
                        
                    db.insertPassenger(dbId, dbName, dbAge, dbPhone, dbEmail);
                    break;

                case 10:
                    db.displayPassengers();
                    break;

                case 11:
                    System.out.print("Enter Passenger ID: ");
                    String id = scanner.nextLine();
                    
                    db.searchPassenger(id);
                    break;
  
                case 0:
                    scanner.close();
                    System.out.println("Exiting system...");
                    return;

                default:
                    System.out.println("Invalid choice!");
                
            } 
        }   
    }     
}