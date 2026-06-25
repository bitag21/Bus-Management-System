# Project Submission Notes — Bus Management System

**Course:** Object Oriented Programming
**Project:** Bus Management System (Java Console Application)

This document explains how each course requirement was implemented in the
project, with references to the exact classes and methods involved. It is
meant to make grading easier by pointing directly to where each concept is
demonstrated.

---

## Chapter 1 & 2 — Introduction to OOP, Classes & Objects, Encapsulation

**Classes and Objects**
- `Person`, `Driver`, `Passenger`, `Bus`, and `Ticket` are all defined as
  clear classes with relevant attributes and behavior (`model/` package).
- Objects are instantiated and used meaningfully in `Main.java` — for
  example, a `Driver` object is created and then assigned to a `Bus` object,
  and a `Passenger` object is linked to a `Ticket` object, modeling a real
  bus ticketing scenario.

**Encapsulation**
- All class fields are declared `private`.
- Every field has a corresponding getter and setter.
- Several setters include validation logic that throws
  `IllegalArgumentException` when given invalid data, protecting the
  object's internal state:
  - `Person.setAge(int)` — rejects negative age
  - `Bus.setCapacity(int)` — rejects negative capacity
  - `Driver.setExperienceYears(int)` — rejects negative experience
  - `Ticket.setFare(double)` — rejects negative fare

---

## Chapter 3 — Inheritance

- `Person` is an **abstract class** that defines shared attributes
  (`id`, `name`, `age`, `phone`) and an abstract method `displayInfo()`.
- `Driver extends Person` and `Passenger extends Person` — both represent
  an **"is-a" relationship** (a Driver *is a* Person, a Passenger *is a*
  Person).
- Both subclasses call `super(id, name, age, phone)` in their constructors,
  demonstrating **reuse of fields and constructor logic** from the base
  class instead of duplicating it.

---

## Chapter 4 — Polymorphism

**Method Overriding (runtime polymorphism)**
- `Driver.displayInfo()` and `Passenger.displayInfo()` both override the
  abstract method defined in `Person`, each printing information relevant
  to that specific subclass.

**Method Overloading (compile-time polymorphism)**
- `BusService.addBus(Bus bus)` and
  `BusService.addBus(String, String, int, String, Driver)` — two versions
  of the same method name with different parameter lists.
- `TicketService.bookTicket(Ticket ticket)` and
  `TicketService.bookTicket(String, Passenger, Bus, String, String, double)`
  — same pattern.

**Superclass Reference to Subclass Object**
- In `Main.java`, after a `Driver` or `Passenger` object is created, it is
  also assigned to a variable declared with the superclass type:
  ```java
  Person personRef = driver;
  personRef.displayInfo();
  ```
- Even though `personRef` is declared as `Person`, calling
  `displayInfo()` on it executes `Driver`'s overridden version at runtime —
  this demonstrates **dynamic method dispatch**, the core idea behind this
  requirement. The same pattern is shown for `Passenger` when a passenger
  is added.

---

## Chapter 5 — Exception Handling

| Keyword | Where it's used |
|---|---|
| `try` | `BusService.deleteBus()`, `BusService.updateBus()`, `TicketService.cancelTicket()`, `TicketService.updateTicket()`, all DB methods in `DBConnection` |
| `catch` | Same locations as above — catching `BusNotFoundException`, `TicketNotFoundException`, and `SQLException` |
| `finally` | `BusService.deleteBus()`, `BusService.updateBus()`, `TicketService.cancelTicket()`, `TicketService.updateTicket()` — used for "operation completed" cleanup messages |
| `throw` | Validation setters (`setAge`, `setCapacity`, `setFare`, `setExperienceYears`) and custom exceptions (`BusNotFoundException`, `TicketNotFoundException`) |
| `throws` | `BusService.searchBus(String) throws BusNotFoundException`, `TicketService.searchTicket(String) throws TicketNotFoundException` |

**Custom Exceptions**
- `BusNotFoundException extends Exception` — thrown when a bus ID search
  fails.
- `TicketNotFoundException extends Exception` — thrown when a ticket ID
  search fails.
- Both are checked exceptions, requiring callers to handle them explicitly,
  which is demonstrated in `Main.java` (for buses) and inside
  `TicketService` itself (for tickets).

**Input Validation Handling**
- `Main.java` wraps user-input-driven object creation (Add Driver, Add Bus,
  Add Passenger, Book Ticket) in `try/catch (IllegalArgumentException e)`
  blocks, so invalid input (e.g., negative age) is reported to the user
  instead of crashing the program.

---

## Chapter 6 — Files and Streams

| Concept | Where it's used |
|---|---|
| `File` class | `FileManager.createFile(String)` |
| Text file I/O | `FileManager.writeTextFile()` / `FileManager.readTextFile()` |
| Buffering | `BufferedWriter` and `BufferedReader` wrap the underlying file streams |
| Filter streams | `BufferedReader`/`BufferedWriter` and `ObjectInputStream`/`ObjectOutputStream` all wrap an underlying stream to add functionality, which is the definition of a filter stream |
| Serialization | All model classes (`Person`, `Driver`, `Passenger`, `Bus`, `Ticket`) implement `Serializable`; `FileManager.saveObjects()`/`loadObjects()` use `ObjectOutputStream`/`ObjectInputStream` to persist and restore full object lists |

Menu option **7** (Save Data to File) writes both a human-readable text
file (`tickets.txt`) and a serialized binary file (`tickets.dat`). Menu
option **8** (Load Data from File) reads both back.

---

## Chapter 7 — Database Programming

| Concept | Where it's used |
|---|---|
| Loading drivers | `Class.forName("com.mysql.cj.jdbc.Driver")` in `DBConnection.getConnection()` |
| Establishing connections | `DriverManager.getConnection(URL, USERNAME, PASSWORD)` |
| Creating statements | `Statement` used in `displayPassengers()` (no user input); `PreparedStatement` used in `insertPassenger()` and `searchPassenger()` (user-supplied values, parameterized safely) |
| Processing ResultSet | `resultSet.next()` loop with typed getters (`getString`, `getInt`) in all three database methods |

Menu options **9, 10, and 11** correspond to inserting, displaying, and
searching passenger records in the MySQL database.

---

## Known Design Notes (for transparency)

- The database password in `DBConnection.java` is hardcoded for simplicity
  in this academic project; a production system would externalize this to
  a config file or environment variable.
- Database operations are implemented for the `passenger` table only.
  Other entities (`Bus`, `Driver`, `Ticket`) are managed in memory and
  persisted via file serialization instead, which already satisfies the
  file-handling requirement separately.
- There are no duplicate-ID checks across drivers, buses, or passengers
  within a single program run.

---

## Team Contributors
- Wuberst Goshu
- Tsion Zefere
- Yedidiya Amanuel
- Tsion Yazachew
- Tsedale Solomon
