package model;

import java.io.Serializable;

/**
 * Abstract superclass representing a person in the system.*/
public abstract class Person implements Serializable {
    private  String id;
    private String name;
    private int age;
    private String phone;

    // constructor
    public Person(String id, String name, int age, String phone){
        this.id = id;
        this.name = name;
        setAge(age);
        this.phone = phone;
    }
    //getters

    public String getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public String getPhone(){
        return phone;
    }

    //setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age){
        if(age < 0){
            throw new IllegalArgumentException("Age can't be negative.");
        }
        this.age = age;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

     /**
     * Abstract method that must be implemented by subclasses*/
    //abstract method to display person's information
    public abstract void displayInfo();  
}
