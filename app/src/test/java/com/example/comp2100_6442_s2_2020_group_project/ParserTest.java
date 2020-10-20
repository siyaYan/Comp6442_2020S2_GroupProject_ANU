package com.example.comp2100_6442_s2_2020_group_project;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author: Xiran Yan
 * @uid: 7167582
 */

public class ParserTest {
    @BeforeClass
    public static void setup() {
        Token.loadData();
    }

    @Test
    public void parserTest() {
        //InputTokenizer myInputTokenizer = new InputTokenizer("ChemicalBiology2 pre");
        //InputTokenizer myInputTokenizer = new InputTokenizer("comp6442 pre");
        //InputTokenizer myInputTokenizer = new InputTokenizer(" comp1100, MolecularGeneTechnology, pre, MolecularGeneTechnology, comp1100, pre, comp1100, pre, MolecularGeneTechnology, pre ");
        InputTokenizer myInputTokenizer = new InputTokenizer(" comp1100, MolecularGeneTechnology, pre,");
        //InputTokenizer myInputTokenizer = new InputTokenizer("ComputerScience");
        List<List<String>> parsed = new Parser(myInputTokenizer).parseInput();
        List<String> ls1 = parsed.get(0);
        String ls1s = ls1.get(0)+","+ls1.get(1)+",";
        List<String> ls2 = parsed.get(1);
        String ls2s = ls2.get(0)+","+ls2.get(1)+","+ls2.get(2);
        assertEquals(ls1s+ls2s, "comp1100,courseId,moleculargenetechnology,courseName,pre");
    }
}
