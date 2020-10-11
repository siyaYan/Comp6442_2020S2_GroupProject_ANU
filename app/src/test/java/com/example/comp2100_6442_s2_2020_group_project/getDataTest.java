package com.example.comp2100_6442_s2_2020_group_project;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class getDataTest {
    ArrayList<String[]> majors=new ArrayList<>();
    List<Course> courses=new ArrayList<>();
    @Test
    public void getMajorTest() {
        File file = new File("src/main/assets/majors.csv");
        majors=new getDataUtil().readBespokeFile(file);
        for (String[] major : majors) {
            System.out.println(major[1]);
        }
    }
    @Test
    public void getCourseTest() {
        File file = new File("src/main/assets/someCourses.json");
        courses=new getDataUtil().readJSONFile(file);
        for (Course course : courses) {
            System.out.println(course.courseDetail);
        }
    }
}

