package com.example.comp2100_6442_s2_2020_group_project;

import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class RegisterTest {

    @Test
    public void registerUserDataTest(){
        String user = "-1"; //use a negative userID so it doesnt interfere with current database.
        String course = "COMP1000";
        new Register().addUserData(user); //register a new user with ID "-1"

        new UserHistory().updateHistory(user,course); //add a course to user's history

        ArrayList<String> expected = new ArrayList<>();
        expected.add(course);

        ArrayList<String> actual = new UserHistory().findUserCourses(user);

        new Register().removeUserData(user); //remove user at the end of test.

        assertEquals("The list " + actual + " is not equal to the expected list " + expected,expected,actual);
    }
    //TODO add tests for registering users.xml
}
