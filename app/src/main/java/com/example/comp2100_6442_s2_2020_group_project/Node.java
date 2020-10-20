package com.example.comp2100_6442_s2_2020_group_project;

public class Node<String> {
    Colour colour;
    String courseID;
    String classNumber;
    String courseName;//descr is courseName
    Node<String> parent;
    Node<String> left, right;

    public Node(String courseID, String classNumber, String courseName) {
        this.courseID  = courseID;
        this.classNumber = classNumber;
        this.courseName = courseName;
        this.colour = Colour.RED;
        this.parent = null;

        this.left 			= new Node<>();
        this.right 			= new Node<>();
        this.left.parent 	= this;
        this.right.parent 	= this;
    }

    public Node() {
        this.courseID 	= null;
        this.colour = Colour.BLACK;
    }
}
