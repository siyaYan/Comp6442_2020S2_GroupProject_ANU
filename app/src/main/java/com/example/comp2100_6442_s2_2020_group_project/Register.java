package com.example.comp2100_6442_s2_2020_group_project;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Register {

    //TODO update users.xml
    public void updateUsers(User user) {
    }

    /**
     * create an entry for new user in the userData.csv database
     * @param userID
     * @author Bharath Kulkarni
     */
    public void addUserData(String userID) {
        File original = new File("src/main/assets/userData.csv");
        File interim = new File("src/main/assets/interim.csv");
        String[] out = new String[2];
        try{
            CsvWriter writer = new CsvWriter(new FileWriter(interim),',');
            CsvReader reader = new CsvReader(new FileReader(original));
            reader.readHeaders();

            //write headers
            out[0] = "id";
            out[1] = "courses";
            writer.writeRecord(out);

            //copy over data from old csv
            while(reader.readRecord()){
                out[0] = reader.get("id");
                out[1] = reader.get("courses");
                writer.writeRecord(out);
            }

            //add new user to the end of the list
            out[0] = userID;
            out[1] = "";
            writer.writeRecord(out);

            reader.close();
            writer.close();

            //delete original
            original.delete();
            //rename interim file to original name (interim.csv -> userData.csv)
            interim.renameTo(original);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Remove a user's ID and history from the userData.csv database
     * @param userID
     * @author Bharath Kulkarni
     */
    public void removeUserData(String userID){
        File original = new File("src/main/assets/userData.csv");
        File interim = new File("src/main/assets/interim.csv");
        String[] out = new String[2];
        try{
            CsvWriter writer = new CsvWriter(new FileWriter(interim),',');
            CsvReader reader = new CsvReader(new FileReader(original));
            reader.readHeaders();

            //write headers
            out[0] = "id";
            out[1] = "courses";
            writer.writeRecord(out);

            //copy over data from old csv
            while(reader.readRecord()){
                // add all users from old csv to new csv except specified user.
                if(!reader.get("id").equals(userID)) {
                    out[0] = reader.get("id");
                    out[1] = reader.get("courses");
                    writer.writeRecord(out);
                }
            }

            reader.close();
            writer.close();

            //delete original
            original.delete();
            //rename interim file to original name (interim.csv -> userData.csv)
            interim.renameTo(original);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
