package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/busmanagementsystem";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "BusProject2026";

    public static Connection getConnection(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found.");
            throw new RuntimeException(e);

        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }    

    // statement to display all passengers from the database
    public void displayPassengers(){
        String sql = "SELECT * FROM passenger";

        try(
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
        ){
            
            while (resultSet.next()) {
                System.out.println("---------------------------");
                System.out.println("Passenger ID : " + resultSet.getString("id"));
                System.out.println("Name         : " + resultSet.getString("name"));
                System.out.println("Age          : " + resultSet.getInt("age"));
                System.out.println("Phone        : " + resultSet.getString("phone"));
                System.out.println("Email        : " + resultSet.getString("email"));
            }

        } catch (SQLException e) {
            System.out.println("Display failed: " + e.getMessage());
        }

    }

    //prepared statement to insert a passenger into the database
    public void insertPassenger(String id, String name, int age, String phone, String email){
        
        String sql = "INSERT INTO passenger VALUES (?, ?, ?, ?, ?)";

        try(

            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, email);

            preparedStatement.executeUpdate();

            System.out.println("Passenger inserted successfully.");

        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }

    //result set to search for a passenger by ID
    public void searchPassenger(String id){

        String sql = "SELECT * FROM passenger WHERE id = ?";

        try(

            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                System.out.println("=========================");
                System.out.println("    Passenger Found");
                System.out.println("=========================");
                System.out.println("Passenger ID    : " + resultSet.getString("id"));
                System.out.println("Name  : " + resultSet.getString("name"));
                System.out.println("Age   : " + resultSet.getInt("age"));
                System.out.println("Phone : " + resultSet.getString("phone"));
                System.out.println("Email : " + resultSet.getString("email"));

            } else {
                System.out.println("Passenger not found.");
            }

        } catch (SQLException e) {
            System.out.println("Search failed: " + e.getMessage());
        }
    }
    
}
