# Bus Management System

A console-based Java application for managing buses, drivers, passengers, and
ticket bookings — built to demonstrate the core pillars of Object-Oriented
Programming, exception handling, file persistence, and JDBC database
programming.

## Features

- Add and manage **drivers**, **buses**, and **passengers**
- Book, search, update, and cancel **tickets**
- Save/load ticket data with **text files** and **Java serialization**
- Insert and query passenger records in a **MySQL database**
- Custom exception handling for missing buses/tickets and invalid input

## Project Structure

```
Bus-Management-System/
└── src/
    ├── database/
    │   └── DBConnection.java
    ├── exception/
    │   ├── BusNotFoundException.java
    │   └── TicketNotFoundException.java
    ├── file/
    │   └── FileManager.java
    ├── main/
    │   └── Main.java
    ├── model/
    │   ├── Person.java         (abstract base class)
    │   ├── Driver.java         (extends Person)
    │   ├── Passenger.java      (extends Person)
    │   ├── Bus.java
    │   └── Ticket.java
    └── service/
        ├── BusService.java
        └── TicketService.java
```

## Getting Started

### Prerequisites
- Java JDK 11+
- MySQL Server
- MySQL Connector/J (JDBC driver `.jar`) added to your classpath

### Database Setup
```sql
CREATE DATABASE busmanagementsystem;

USE busmanagementsystem;

CREATE TABLE passenger (
    id      VARCHAR(20) PRIMARY KEY,
    name    VARCHAR(100),
    age     INT,
    phone   VARCHAR(20),
    email   VARCHAR(100)
);
```


```java
private static final String URL = "jdbc:mysql://localhost:3306/busmanagementsystem";
private static final String USERNAME = "root";
private static final String PASSWORD = "BusProject2026";
```

> ⚠️ Credentials are hardcoded here for simplicity in this academic project.
> In a production app these would be externalized to a config file or
> environment variables instead of being committed to source control.

### Running the App
1. Compile all files under `src/`.
2. Run `main.Main`.
3. Use the on-screen menu to navigate between options.

## Menu Overview

| Option | Action |
|---|---|
| 1 | Add Driver |
| 2 | Add Bus |
| 3 | Add Passenger |
| 4 | Book Ticket |
| 5 | Display All Buses |
| 6 | Display All Tickets |
| 7 | Save Data to File (text + serialized) |
| 8 | Load Data from File |
| 9 | Insert Passenger to Database |
| 10 | Show Passengers from Database |
| 11 | Search Passenger in Database |
| 0 | Exit |

## OOP Concepts Demonstrated

| Concept | Where |
|---|---|
| **Encapsulation** | Private fields with validated getters/setters across all model classes |
| **Inheritance** | `Driver` and `Passenger` extend abstract `Person` |
| **Polymorphism (overriding)** | `displayInfo()` overridden in `Driver`/`Passenger` |
| **Polymorphism (overloading)** | `BusService.addBus(...)`, `TicketService.bookTicket(...)` |
| **Polymorphism (superclass reference)** | `Person personRef = driver;` then `personRef.displayInfo()` in `Main` (Add Driver/Add Passenger) — resolves to the subclass's overridden method at runtime |
| **Exception Handling** | Custom `BusNotFoundException` / `TicketNotFoundException`, `try/catch/finally`, validation via `throw` in setters |
| **File I/O** | `BufferedReader`/`BufferedWriter` for text, `ObjectInputStream`/`ObjectOutputStream` for serialization |
| **Database Programming** | JDBC driver loading, `Connection`, `Statement` + `PreparedStatement`, `ResultSet` processing |

## Contributors
- Wuberst Goshu
- Tsedale Solomon
- Tsion Yazachew
- Yedidiya Amanuel
- Tsion Zefere

