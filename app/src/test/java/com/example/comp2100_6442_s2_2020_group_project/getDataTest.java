package com.example.comp2100_6442_s2_2020_group_project;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
/**
 *
 * @author: Xiran Yan
 * @uid: 7167582
 */
public class getDataTest {
    ArrayList<String[]> majors=new ArrayList<>();
    List<Course> courses=new ArrayList<>();
    ArrayList<User> users=new ArrayList<>();

    @Test
    public void getMajorTest() {
        String fileName2="src/main/assets/majors.csv";
        majors=new GetDataUtil().readBespokeFile(fileName2);
        String majorNames="";
        for (String[] major : majors) {
            majorNames += major[0]+",";
        }
        assertEquals(majorNames, "majorName,Astronomy and Astrophysics,Biochemistry,Biological Anthropology,Cell and Molecular Biology,Chemistry,Computer Science,Earth Science,Environmental Science,Evolution Ecology and Organismal Biology,Geography,Human Biology,Human Evolutionary Biology,Marine Science,Mathematical Economics,Mathematical Finance,Mathematical Modelling,Mathematics,Physics,Psychology,Quantitative Environmental Modelling,Quantitiative Biology,Resource and Environmental Management,Science Communication,Statistics,Sustainability Studies,Water Science,");
    }

    @Test
    public void getCourseTest() {
        String fileName1="src/main/assets/courses.json";
        courses=new GetDataUtil().readJSONFile(fileName1);
        /*for (Course course : courses) {
            System.out.println(course.courseDetail);
        }*/
        Course firstCourse =courses.get(0);
        String detail=new String();
        for (String str : firstCourse.courseDetail) {
            detail += str+",";
        }
        Course lastCourse =courses.get(courses.size()-1);
        assertEquals(detail, "1205,ASIA,2114,1,Advanced Studies in Asia and the Pacific 2,6,6,College Asia & Pacific,CAP, ,GRD,1/1/20,3/31/20,1/24/20,1/24/20,P,");
        assertEquals(lastCourse.classNumber, "9821");
    }
}

