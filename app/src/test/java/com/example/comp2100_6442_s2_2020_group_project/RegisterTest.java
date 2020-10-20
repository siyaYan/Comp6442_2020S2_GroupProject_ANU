package com.example.comp2100_6442_s2_2020_group_project;

import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class RegisterTest {

    @Test
    public void registerUserDataTest(){
        //use a negative userID so it doesn't interfere with current users in database.
        String user = "-1";
        String course = "COMP1000";
        new Register().addUserData(user);

        new UserHistory().updateHistory(user,course);

        ArrayList<String> expected = new ArrayList<>();
        expected.add(course);

        ArrayList<String> actual = new UserHistory().findUserCourses(user);
        //remove user at the end of test.
        new Register().removeUserData(user);

        assertEquals("The list " + actual + " is not equal to the expected list " + expected,expected,actual);
    }
    //TODO add tests for registering users.xml
}
