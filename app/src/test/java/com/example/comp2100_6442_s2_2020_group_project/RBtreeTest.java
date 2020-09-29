package com.example.comp2100_6442_s2_2020_group_project;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RBtreeTest {
    @Test
    public void testRBtree(){
        RBTreeBarry<String> tree;

        tree = new RBTreeBarry<String>();
        tree.insertValue("COMP1100", "1234");
        tree.insertValue("CHEM1100", "1235");
        tree.insertValue("CHEM2100", "1236");
        tree.insertValue("CHEM2200", "1237");
        tree.insertValue("CHEM2150", "1238");

        System.out.println(tree.preOrder(tree.root));
    }
    /*public static void main(String[] args) {
        RBTreeBarry<String> tree;

        tree = new RBTreeBarry<String>();
        tree.insertValue("COMP1100", "1234");
        tree.insertValue("CHEM1100", "1235");
        tree.insertValue("CHEM2100", "1235");
        tree.insertValue("CHEM2200", "1235");
        tree.insertValue("CHEM2150", "1235");

        System.out.println(tree.preOrder(tree.root));
    }*/
}
