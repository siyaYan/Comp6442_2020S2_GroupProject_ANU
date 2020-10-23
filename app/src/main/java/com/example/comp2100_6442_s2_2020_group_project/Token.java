package com.example.comp2100_6442_s2_2020_group_project;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Token class
 * Types are for:
 * - INT: course number, random number
 * - SUBJECT: subject
 * - MAJOR: major
 * - NAME: course full name
 * - OPERATION: pre
 * - STRING: random string
 * - UNKNOWN: init
 *
 * @author Xinyu Zheng
 */
public class Token {
    public enum Type {UNKNOWN, INT, SUBJECT, MAJOR, NAME, OPERATION, STRING}
    private static Set<String> subjectSet = new HashSet<>();
    private static Set<String> majorSet = new HashSet<>();
    /*Arrays.asList("geography",
            "quantitativeenvironmentalmodelling", "marinescience", "psychology",
            "waterscience", "biochemistry", "mathematics", "environmentalscience",
            "quantitiativebiology", "computerscience", "mathematicalfinance",
            "resourceandenvironmentalmanagement", "sustainabilitystudies",
            "biologicalanthropology", "sciencecommunication", "astronomyandastrophysics",
            "cellandmolecularbiology", "humanbiology", "mathematicalmodelling", "statistics",
            "humanevolutionarybiology", "mathematicaleconomics", "physics", "earthscience",
            "chemistry", "evolutionecologyandorganismalbiology")*/
    public static Set<String> nameSet = new HashSet<>();
    private static Set<String> operationSet = new HashSet<>(Arrays.asList("pre"));
    private String _content;
    private Type _type = Type.UNKNOWN;
    private static List<Course> courseList = new ArrayList<>();
    private static ArrayList<String[]> majorList = new ArrayList<>();

    public static void loadData() {
        String fileName1 = "src/main/assets/courses.json";
        courseList = new GetDataUtil().readJSONFile(fileName1);
        String fileName2 = "src/main/assets/majors.csv";
        majorList = new GetDataUtil().readBespokeFile(fileName2);


        for (Course course : courseList) {
            subjectSet.add(course.courseDetail.get(1).toLowerCase());
            nameSet.add(course.courseDetail.get(4).toLowerCase().replaceAll("\\s*",""));
        }
        for (String[] major : majorList) {
            majorSet.add(major[0].toLowerCase().replaceAll("\\s*",""));
        }
    }
    /**
     * init token
     *
     * @author Xinyu Zheng
     * @author:Xiran Yan(modify)
     */
    public Token(String fileName1,String fileName2,Context context) {
        System.out.println(context.toString());
        courseList = new AndroidFileParser().parseJSON(fileName1,context);
        majorList =  new AndroidFileParser().parseCSV(fileName2,context);

        for (Course course : courseList) {
            subjectSet.add(course.courseDetail.get(1).toLowerCase());
            nameSet.add(course.courseDetail.get(4).toLowerCase().replaceAll("\\s*",""));
        }
        for (String[] major : majorList) {
            majorSet.add(major[0].toLowerCase().replaceAll("\\s*",""));
        }
      /*  for (String s : nameSet) {
            System.out.print(s + "\n");
        }
        System.out.println();*/

    }

    public Token(String _content, Type _type) {
        this._content = _content;
        this._type = _type;
    }

    public Token(String _content) {
        this._content = _content;
        if (subjectSet.contains(_content)) {
            this._type = Type.SUBJECT;
        } else if (majorSet.contains(_content)) {
            this._type = Type.MAJOR;
        } else if (nameSet.contains(_content)) {
            this._type = Type.NAME;
        } else if (operationSet.contains(_content)) {
            this._type = Type.OPERATION;
        } else {
            this._type = Type.STRING;
        }
    }

    public String getContent() {
        return _content;
    }

    public Type getType() {
        return _type;
    }

    @Override
    public String toString() {
        return this._content;
    }

}




