package com.example.comp2100_6442_s2_2020_group_project;

public class Node<T> {
    Colour colour;
    T courseID;
    Node<T> parent;
    Node<T> left, right;

    public Node(T courseID) {
        this.courseID  = courseID;
        this.colour = Colour.RED; //property 3 (if a node is red, both children are black) may be violated if parent is red
        this.parent = null;

        // Initialise children leaf nodes
        this.left 			= new Node<T>();  //leaf node
        this.right 			= new Node<T>();  //leaf node
        this.left.parent 	= this; //reference to parent
        this.right.parent 	= this; //reference to parent
    }

    public Node() {
        this.courseID 	= null; //leaf nodes are null
        this.colour = Colour.BLACK; //leaf nodes are always black
    }
}
