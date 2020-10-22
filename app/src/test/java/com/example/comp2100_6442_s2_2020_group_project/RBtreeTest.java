package com.example.comp2100_6442_s2_2020_group_project;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Bharath Kulkarni
 *
 *
 * @author: Xiran Yan
 * @uid: 7167582
 */
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
        //System.out.println(tree.inOrder(tree.root));
        //System.out.println(tree.preOrder(tree.root));
        assertEquals(tree.root.courseID, "COMP2710");
        List<Node> compnodes=tree.searchNodes(tree.root,"COMP",new ArrayList<Node>());
        Node idNode=tree.searchNode("cOMP2100","courseId");
        Node nameNode=tree.searchNode(" specialTopicsinComputerScience ","courseName");
        String compCourse ="";
        for(int i=0;i<compnodes.size();i++) {
            compCourse +=compnodes.get(i).courseID+",";
        }
        assertEquals(compCourse, "COMP2710,COMP3710,COMP2100,COMP2300,COMP1110,COMP1100,");
        assertEquals(idNode.courseName, "Software Design Methodologies");
        assertEquals(nameNode.courseID, "COMP2710");
    }
}
