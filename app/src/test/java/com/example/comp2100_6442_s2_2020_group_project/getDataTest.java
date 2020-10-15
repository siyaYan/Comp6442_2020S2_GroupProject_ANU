package com.example.comp2100_6442_s2_2020_group_project;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class getDataTest {
    ArrayList<String[]> majors=new ArrayList<>();
    List<Course> courses=new ArrayList<>();
    ArrayList<User> users=new ArrayList<>();
    @Test
    public void getMajorTest() {
        String fileName2="src/main/assets/majors.csv";
        majors=new getDataUtil().readBespokeFile(fileName2);
        for (String[] major : majors) {
            System.out.println(major[1]);
        }
    }
    @Test
    public void getCourseTest() {
        String fileName1="src/main/assets/someCourses.json";
        courses=new getDataUtil().readJSONFile(fileName1);
        for (Course course : courses) {
            System.out.println(course.courseDetail);
        }
    }
    @Test
    public void getUserTest() {
        String fileName="src/main/res/raw/users.xml";
        users=new getDataUtil().readXMLFile(fileName);
        for (User user : users) {
            System.out.println(user.userName);
        }
    }
}

