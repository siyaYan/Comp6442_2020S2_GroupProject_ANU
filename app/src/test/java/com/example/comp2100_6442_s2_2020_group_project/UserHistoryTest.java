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
        String userID = "-1";
        new Register().addUserData(userID);

        //expect empty history.
        ArrayList<String> expected = new ArrayList<>();

        //add some courses to user's history
        new UserHistory().updateHistory(userID, "COMP1000");
        new UserHistory().updateHistory(userID, "CHEM1000");
        //clear history
        new UserHistory().clearHistory(userID);
        ArrayList<String> updatedList = new UserHistory().findUserCourses(userID);

        //remove user at end of testing
        new Register().removeUserData(userID);

        assertEquals("The list " + updatedList + " is not equal to the expected list " + expected,expected,updatedList);
    }

    /**
     * test output when user does not exist in database
     */
    @Test
    public void testNonExistentUserHistory(){
        String userID = "-1";
        //create a new user so as to not interfere with existing user database
        new Register().addUserData(userID);
        //ensure the user does not exist anymore
        new Register().removeUserData(userID);

        ArrayList<String> actualHistory = new UserHistory().findUserCourses(userID);
        //expect empty history
        ArrayList<String> expected = new ArrayList<>();

        assertEquals("The list " + actualHistory + " is not equal to the expected list " + expected,expected,actualHistory);

    }
}
