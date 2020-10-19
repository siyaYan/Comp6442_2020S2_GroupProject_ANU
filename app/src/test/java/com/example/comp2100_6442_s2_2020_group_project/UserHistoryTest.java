package com.example.comp2100_6442_s2_2020_group_project;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class UserHistoryTest {
    @Test
    public void testUpdateUserHistory() throws IOException {
        String user = "1"; //add a course to arbitrary user for testing. the course is removed at the end to maintain data integrity.
        String course = "COMP1000"; //CHEM1000 does not exist in the database, it is used for testing

        ArrayList<String> originalList = new UserHistory().findUserCourses(user);

        ArrayList<String> expected = new UserHistory().findUserCourses(user);
        expected.add(course); //add course to originalList

        new UserHistory().updateHistory(user, course); //add course to user's history

        ArrayList<String> updatedList = new UserHistory().findUserCourses(user);

        new UserHistory().setHistory(user,originalList); //reset the user's history (ie. remove the course we added).

        assertEquals("The list " + updatedList + " is not equal to the expected list " + expected,expected,updatedList);
    }

    @Test
    public void testClearHistory(){
        String user = "1"; //add a course to arbitrary user for testing.

        ArrayList<String> originalList = new UserHistory().findUserCourses(user);

        ArrayList<String> expected = new ArrayList<>();

        //add some courses to user's history
        new UserHistory().updateHistory(user, "COMP1000");
        new UserHistory().updateHistory(user, "CHEM1000");
        //clear history
        new UserHistory().clearHistory(user);

        ArrayList<String> updatedList = new UserHistory().findUserCourses(user);

        new UserHistory().setHistory(user,originalList); //reset the user's history.

        assertEquals("The list " + updatedList + " is not equal to the expected list " + expected,expected,updatedList);
    }
}
