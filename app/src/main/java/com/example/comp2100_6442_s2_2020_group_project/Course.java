package com.example.comp2100_6442_s2_2020_group_project;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {

    public String classNumber;
    public ArrayList<String> courseDetail=new ArrayList<String>() {
            String ClassNumber="1317";//display 0
            String Subject="MATH";//display 1
            String CourseID="3349";//display 2
            String Section="1";//display 3
            String CourseName="Special Topics in Mathematics";//display 4
        String MinUnits="6";
        String MaxUnits="6";
        String Description="Mathematical Science Institute";
        String College="CMBE_CPS";
        String RequisiteGroupDescription="Incompatible with MATH6209";//display 9
        String GradeBase="GRD";
        String StartDate="43831";
        String EndDate="43921";
        String CensusDateDeadline="43854";//todo date to string format transfer
        String  LastDateEnrol="43854";
        String  ModeofDelivery="P";
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