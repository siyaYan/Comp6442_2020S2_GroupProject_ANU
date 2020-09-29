package com.example.comp2100_6442_s2_2020_group_project;

import org.junit.Test;


public class RBtreeTest {
    @Test
    public void testRBtree(){
        RBTreeBarry<String> tree;

        tree = new RBTreeBarry<String>();
        //suppose we only have comp tree(already put into order)
        tree.insertValue("1100", "1234");
        tree.insertValue("1200", "1235");
        tree.insertValue("1234", "1236");
        tree.insertValue("2200", "1237");
        tree.insertValue("2150", "1238");
        tree.insertValue("1000", "1200");
        tree.insertValue("1400", "3333");
        tree.insertValue("1534", "3211");
        tree.insertValue("2600", "4321");
        tree.insertValue("1850", "1231");
        //display the tree inorder
        System.out.println(tree.inOrder(tree.root));
        //show root
        System.out.println(tree.root.courseID);
        //find node by courseId
        System.out.println(tree.searchNode("1534").classNumber);
   /*   System.out.println(tree.searchNode("1100").classNumber);
        System.out.println(tree.searchNode("1200").classNumber);
        System.out.println(tree.searchNode("1234").classNumber);
        System.out.println(tree.searchNode("2200").classNumber);
        System.out.println(tree.searchNode("2150").classNumber);
        System.out.println(tree.searchNode("1000").classNumber);
        System.out.println(tree.searchNode("2600").classNumber);
        System.out.println(tree.searchNode("1850").classNumber);
        System.out.println(tree.searchNode("1400").classNumber);*/

    }
}
