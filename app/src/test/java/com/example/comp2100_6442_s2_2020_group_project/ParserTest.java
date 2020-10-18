package com.example.comp2100_6442_s2_2020_group_project;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class ParserTest {
    @BeforeClass
    public static void setup() {
        Token.loadData();
    }

    @Test
    public void parserTest() {
        InputTokenizer myInputTokenizer = new InputTokenizer("ChemicalBiology2 pre");
        //InputTokenizer myInputTokenizer = new InputTokenizer("comp6442 pre");
        //InputTokenizer myInputTokenizer = new InputTokenizer(" comp1100, MolecularGeneTechnology, pre, MolecularGeneTechnology, comp1100, pre, comp1100, pre, MolecularGeneTechnology, pre ");
        //InputTokenizer myInputTokenizer = new InputTokenizer("COMP");
        //InputTokenizer myInputTokenizer = new InputTokenizer("ComputerScience");
        List<List<String>> parsed = new Parser(myInputTokenizer).parseInput();
        for (List<String> ls : parsed) {
            for (String s : ls) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
