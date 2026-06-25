package file;

import java.io.*;
import java.util.ArrayList;

public class FileManager {
    
    // save objects using serialization
    public void saveObjects(String fileName, ArrayList<?> list) {

        try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName))){

            output.writeObject(list);
            System.out.println("Data saved successfully to " + fileName);

         } catch(IOException e){
           System.out.println("Error while processing file: " + e.getMessage());
        }
    }

    // load objects using Serialization
    public ArrayList<?> loadObjects(String fileName){
        
        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName))){

             return(ArrayList<?>) input.readObject();

         } catch(IOException | ClassNotFoundException e){

          System.out.println("Error while processing file: " + e.getMessage());
        }

        return new ArrayList<>();
    }

    //write text to a file(BufferedWriter)
    public void writeTextFile(String fileName, String text) {

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            writer.write(text);
            System.out.println("Text written successfully.");

        } catch(IOException e) {
         System.out.println("Error while processing file: " + e.getMessage());
        }
    }

    //read text from a file(BufferedReader)
    public void readTextFile(String fileName) {

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }

        } catch(IOException e) {
          System.out.println("Error while processing file: " + e.getMessage());
        }
    }

    //Create a new file
    public void createFile(String fileName) {

        try {
            File file = new File(fileName);

            if (file.createNewFile()) {
                System.out.println("File created successfully. ");

            } else {
                System.out.println("File already exists.");
            }
            
        } catch (IOException e) {
            System.out.println("Error while processing file: " + e.getMessage());
        }
    }
}
