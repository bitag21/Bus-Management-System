package model;

public class Driver extends Person {

    // Driver's license number
    private String licenseNumber;
    private int experienceYears;

    //constructor
    public Driver(String id, String name, int age, String phone, String licenseNumber, int experienceYears) {
        super(id, name, age, phone);
        this.licenseNumber = licenseNumber;
        this.experienceYears = experienceYears;
    }

    //getters
    public String getLicenseNumber() {
        return licenseNumber;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    //setters
    public void setLicenseNumber(String licenseNumber){
        this.licenseNumber = licenseNumber;
    }
    
    public void setExperienceYears(int experienceYears) {
        if(experienceYears < 0){
            throw new IllegalArgumentException("Experience years can't be negative.");
        }
        this.experienceYears = experienceYears;
    }

    //overriding the displayInfo method from Person class(runtime polymorphism)
    @Override
    public void displayInfo() {
        System.out.println("====Driver Information====");
        System.out.println("Driver ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        System.out.println("Phone: " + getPhone());
        System.out.println("License Number: " + getLicenseNumber());
        System.out.println("Experience Years: " + getExperienceYears());
        System.out.println("==========================");
    }
}
