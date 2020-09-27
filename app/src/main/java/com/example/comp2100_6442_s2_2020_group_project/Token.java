package com.example.comp2100_6442_s2_2020_group_project;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Token class
 * Types are for:
 * - INT: course number, random number
 * - COLLEGE: college
 * - STRING: major, random string
 * - UNKNOWN: init
 *
 * @author Xinyu Zheng
 */
public class Token {
    public enum Type {UNKNOWN, INT, COLLEGE, STRING}
    private static Set<String> collegeSet = new HashSet<>(Arrays.asList("ASTR", "BIOL", "CHEM",
            "COMP", "EMSC", "ENGN", "ENVS", "HLTH", "MATH", "MEDI", "MEDN", "NEUR", "PHIL", "PHYS", "POPH",
            "PSYC", "SCNC", "SCOM", "VCUG"));
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
}
