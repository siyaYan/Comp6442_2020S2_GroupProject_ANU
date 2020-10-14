package com.example.comp2100_6442_s2_2020_group_project;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * define the course attributes
 * @author: Xiran Yan
 * @uid: 7167582
 */
public class Course implements Serializable {

    public String classNumber;
    public ArrayList<String> courseDetail=new ArrayList<String>() {
        String classNumber;
        String Subject;
        String CourseID;
        String Section;
        String CourseName;
        String MinUnits;
        String MaxUnits;
        String Description;
        String College;
        String RequisiteGroupDescription;
        String GradeBase;
        String StartDate;
        String EndDate;
        String CensusDateDeadline;
        String LastDateToEnrol;
        String ModeOfDelivery;
};

    /**
     * Creates an uninitialised course (for e.g. loading).
     */
    public Course() {
        // Default; uninitialised values.
    }

    public Course(String ClassNumber,ArrayList<String> CourseDetail) {
        this.classNumber = ClassNumber;
        this.courseDetail =CourseDetail;
    }

    public String display() {
        String result=this.classNumber+":";
        for (String i : courseDetail) {
            result+=i.toString()+"/";
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        return false;
    }
}