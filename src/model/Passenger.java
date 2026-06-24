package model;
/**
 * Passenger class that extends the Person class.
 * Represents a passenger with an additional email attribute*/
public class Passenger extends Person{

  // Stores the passenger's email address  
    private String email;


    //constructor
    public Passenger(String id, String name, int age, String phone, String email) {
        super(id, name, age, phone);
        this.email = email;
    }

    //getters
    public String getEmail() {
        return email;
    }


    //setters
    public void setEmail(String email) {
        this.email = email;
    }

    //overriding the displayInfo method from Person class(runtime polymorphism)
    @Override
    public void displayInfo() {
        System.out.println("====Passenger Information====");
        System.out.println("Passenger ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        System.out.println("Phone: " + getPhone());
        System.out.println("Email: " + getEmail());
        System.out.println("============================");
    }

}
