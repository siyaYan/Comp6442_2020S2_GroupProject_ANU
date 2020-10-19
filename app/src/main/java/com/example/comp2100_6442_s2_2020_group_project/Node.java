package com.example.comp2100_6442_s2_2020_group_project;

public class Node<T> {
    Colour colour;
    T courseID;
    T classNumber;
    T courseName;//descr is courseName
    Node<T> parent;
    Node<T> left, right;

    public Node(T courseID, T classNumber, T courseName) {
        this.courseID  = courseID;
        this.classNumber = classNumber;
        this.courseName = courseName;
        this.colour = Colour.RED;
        this.parent = null;


        this.left 			= new Node<T>();
        this.right 			= new Node<T>();
        this.left.parent 	= this;
        this.right.parent 	= this;
    }

    public Node() {
        this.courseID 	= null;
        this.colour = Colour.BLACK;
    }
}
