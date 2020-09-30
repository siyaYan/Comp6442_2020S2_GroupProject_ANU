package com.example.comp2100_6442_s2_2020_group_project;

import org.junit.Test;


public class RBtreeTest {
    @Test
    public void testRBtree(){
        RBTreeBarry<String> tree;

        tree = new RBTreeBarry<String>();
        //test some real data
        tree.insertValue("MEDI3004","1369","IMU Clinical Experience Bridging Course");
        tree.insertValue("LAWS4010","1418","Jessup Moot");
        tree.insertValue("LAWS4217","1419","Family Law");
        tree.insertValue("COMP2710","1618","Special Topics in Computer Science");
        tree.insertValue("COMP1100","2183","Programming as Problem Solving");
        tree.insertValue("COMP1110","3328","Structured Programming");
        tree.insertValue("MATH3349","1317","Special Topics in Mathematics");
        tree.insertValue("MATH3349","1318","Special Topics in Mathematics");
        tree.insertValue("COMP2100","2184","Software Design Methodologies");
        tree.insertValue("COMP2300","2185","Computer Organisation and Program Execution");
        tree.insertValue("COMP3710","2186","Topics in Computer Science");
        tree.insertValue("COMP3710","2186","Topics in Computer Science");
        //display the tree inorder
        //System.out.println(tree.inOrder(tree.root));
        //show root
        System.out.println(tree.root.courseID);
        //find node by courseId
        System.out.println(tree.searchNode("MATH3349","courseId").courseName);
        System.out.println(tree.searchNode("Topics in Computer Science","courseName").classNumber);
   //  System.out.println(tree.searchNode("1100").classNumber);


    }
}
