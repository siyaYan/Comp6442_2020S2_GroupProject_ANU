package com.example.comp2100_6442_s2_2020_group_project;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    ArrayList<String[]> majors=new ArrayList<>();
    List<Course> courses=new ArrayList<>();
    ArrayList<User> users=new ArrayList<>();
    //Context appContext;
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.comp2100_6442_s2_2020_group_project", appContext.getPackageName());
    }
        /*@Test
        public void getMajorTest() {
            Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            String fileName="majors.csv";
            majors=new androidFileParser().parseCsv(fileName,appContext);
            for (String[] major : majors) {
                System.out.println(major[1]);
            }
        }*/
      /*  @Test
        public void getCourseTest() {
            String fileName="someCourses.json";
            courses=new androidFileParser(fileName,appContext);
            for (Course course : courses) {
                System.out.println(course.courseDetail);
            }
        }*/


}