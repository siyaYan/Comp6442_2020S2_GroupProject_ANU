package com.example.comp2100_6442_s2_2020_group_project;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Token class
 * Types are for:
 * - INT: course number, random number
 * - COLLEGE: college
 * - MAJOR: major
 * - STRING: random string
 * - UNKNOWN: init
 *
 * @author Xinyu Zheng
 */
public class Token {
    public enum Type {UNKNOWN, INT, COLLEGE, MAJOR, STRING}
    private static Set<String> collegeSet = new HashSet<>(Arrays.asList("ASTR", "BIOL", "CHEM",
            "COMP", "EMSC", "ENGN", "ENVS", "HLTH", "MATH", "MEDI", "MEDN", "NEUR", "PHIL", "PHYS", "POPH",
            "PSYC", "SCNC", "SCOM", "VCUG"));
    private static Set<String> majorSet = new HashSet<>(Arrays.asList("geography",
            "quantitativeenvironmentalmodelling", "marinescience", "psychology",
            "waterscience", "biochemistry", "mathematics", "environmentalscience",
            "quantitiativebiology", "computerscience", "mathematicalfinance",
            "resourceandenvironmentalmanagement", "sustainabilitystudies",
            "biologicalanthropology", "sciencecommunication", "astronomyandastrophysics",
            "cellandmolecularbiology", "humanbiology", "mathematicalmodelling", "statistics",
            "humanevolutionarybiology", "mathematicaleconomics", "physics", "earthscience",
            "chemistry", "evolutionecologyandorganismalbiology"));
    private String _content;
    private Type _type = Type.UNKNOWN;

    public Token(String _content, Type _type) {
        this._content = _content;
        this._type = _type;
    }

    public Token(String _content) {
        this._content = _content;
        if (collegeSet.contains(_content)) {
            this._type = Type.COLLEGE;
        } else if (majorSet.contains(_content.toLowerCase())) {
            this._type = Type.MAJOR;
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

    public static void main(String[] args) {
        for (String s : majorSet) {
            System.out.print("\"" + s.toLowerCase().replaceAll("\\s","") +  "\"" + ", ");
        }
    }
}




