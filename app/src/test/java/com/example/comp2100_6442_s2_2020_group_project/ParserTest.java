package com.example.comp2100_6442_s2_2020_group_project;

import org.junit.Test;

import java.util.List;

public class ParserTest {
    @Test
    public void parserTest() {
        //InputTokenizer myInputTokenizer = new InputTokenizer("ChemicalBiology2 pre");
        //InputTokenizer myInputTokenizer = new InputTokenizer("comp6442 pre");
        InputTokenizer myInputTokenizer = new InputTokenizer("MolecularGeneTechnology");
        //InputTokenizer myInputTokenizer = new InputTokenizer("COMP");
        //InputTokenizer myInputTokenizer = new InputTokenizer("ComputerScience");
        List<String> parsed = new Parser(myInputTokenizer).parseInput();
        for (String s : parsed) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
