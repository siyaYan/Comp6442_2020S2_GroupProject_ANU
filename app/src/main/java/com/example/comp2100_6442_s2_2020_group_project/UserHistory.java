package com.example.comp2100_6442_s2_2020_group_project;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class UserHistory {
    String userCourses = "";
    String filename = "userData.csv";
    File original = new File("src/main/assets/" + filename);
    File interim = new File("src/main/assets/interim.csv");


    /**
     * Creates a new csv file, applies the changes (addition of a course to a specific user),
     * then deletes the old csv and renames new csv to userData.csv.
     *
     * @param userID ID of user
     * @param course course to be added to user history
     * @author Bharath Kulkarni
     */
    public void updateHistory(String userID, String course) {
        try {
            CsvReader reader = new CsvReader(new FileReader(original));
            CsvWriter writer = new CsvWriter(new FileWriter(interim), ',');

            reader.readHeaders();
            String[] out = new String[2];
            //write headers
            out[0] = "id";
            out[1] = "courses";
            writer.writeRecord(out);

            while (reader.readRecord()) {
                String courses = reader.get("courses");
                if (userID.equals(reader.get("id"))) {
                    courses = courses + " " + course;
                }
                out[0] = reader.get("id");
                out[1] = courses;
                writer.writeRecord(out);
            }
            reader.close();
            writer.close();

            //delete original
            original.delete();
            //rename interim file to original name (interim.csv -> userData.csv)
            interim.renameTo(original);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Find a list of courses corresponding to a certain user
     *
     * @param userID
     * @return List of courses for the specified user
     * @author Bharath Kulkarni.
     */
    public ArrayList<String> findUserCourses(String userID){
        ArrayList<String> courseList = new ArrayList<>();
        try{
            CsvReader reader = new CsvReader("src/main/assets/userData.csv");
            reader.readHeaders();
            while (reader.readRecord()) {
                if (userID.equals(reader.get("id"))) {
                    if(reader.get("courses").isEmpty()){break;}

                    String[] list = reader.get("courses").split(" ");
                    courseList.addAll(Arrays.asList(list));
                    break;
                }
            }reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return courseList;
    }

    /**
     * This method is for resetting the userData.csv file after testing.
     * @param courseList
     */
    public void setHistory(String userID, ArrayList<String> courseList){
        try {
            CsvReader reader = new CsvReader(new FileReader(original));
            CsvWriter writer = new CsvWriter(new FileWriter(interim), ',');

            reader.readHeaders();
            String[] out = new String[2];
            //write headers
            out[0] = "id";
            out[1] = "courses";
            writer.writeRecord(out);

            while (reader.readRecord()) {
                String courses = reader.get("courses");
                if (userID.equals(reader.get("id"))) {
                    String s = "";
                    for (String course : courseList) {
                        if(s.isEmpty()){
                            s = s+course;
                        }else{
                            s = s + " " + course;
                        }
                    }
                    courses = s;
                }
                out[0] = reader.get("id");
                out[1] = courses;
                writer.writeRecord(out);
            }

            reader.close();
            writer.close();

            //delete original
            original.delete();
            //rename interim file to original name (interim.csv -> userData.csv)
            interim.renameTo(original);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove all courses from a user's history
     * @param userID
     */
    public void clearHistory(String userID){
        try {
            CsvReader reader = new CsvReader(new FileReader(original));
            CsvWriter writer = new CsvWriter(new FileWriter(interim), ',');

            reader.readHeaders();
            String[] out = new String[2];
            //write headers
            out[0] = "id";
            out[1] = "courses";
            writer.writeRecord(out);

            while (reader.readRecord()) {
                String courses = reader.get("courses");
                if (userID.equals(reader.get("id"))) {
                    courses = ""; //user's history is set to empty string.
                }
                out[0] = reader.get("id");
                out[1] = courses;
                writer.writeRecord(out);
            }

            reader.close();
            writer.close();

            //delete original
            original.delete();
            //rename interim file to original name (interim.csv -> userData.csv)
            interim.renameTo(original);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
