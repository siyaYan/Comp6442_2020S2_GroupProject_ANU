package com.example.comp2100_6442_s2_2020_group_project;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test for Tokenizer
 *
 * @author Xinyu Zheng
 */
public class TokenizerTest {

    private static Tokenizer tokenizer;

    @Test
    public void TestCourseNumber() {

        tokenizer = new InputTokenizer("2100");

        Token courseNumberToken = tokenizer.getNextToken();
        assertSame(courseNumberToken.getType(), Token.Type.INT);
        assertEquals(courseNumberToken.getContent(), "2100");

    }

    @Test
    public void TestCourseNumberSpace() {
        tokenizer = new InputTokenizer("   2100  ");

        Token courseNumberToken = tokenizer.getNextToken();
        assertSame(courseNumberToken.getType(), Token.Type.INT);
        assertEquals(courseNumberToken.getContent(), "2100");

    }

    @Test
    public void TestFullCourseNumber() {

        tokenizer = new InputTokenizer("COMP2100");

        Token courseCollegeToken = tokenizer.getNextToken();
        assertSame(courseCollegeToken.getType(), Token.Type.COLLEGE);
        assertEquals(courseCollegeToken.getContent(), "COMP");

        Token courseNumberToken = tokenizer.getNextToken();
        assertSame(courseNumberToken.getType(), Token.Type.INT);
        assertEquals(courseNumberToken.getContent(), "2100");

    }


    @Test
    public void TestFullCourseNumberCapital() {

        tokenizer = new InputTokenizer("COMP2100");

        Token courseCollegeToken = tokenizer.getNextToken();
        assertSame(courseCollegeToken.getType(), Token.Type.COLLEGE);
        assertEquals(courseCollegeToken.getContent(), "COMP");

        Token courseNumberToken = tokenizer.getNextToken();
        assertSame(courseNumberToken.getType(), Token.Type.INT);
        assertEquals(courseNumberToken.getContent(), "2100");

    }

    @Test
    public void TestFullCourseNumberComplicated() {

        tokenizer = new InputTokenizer("  Comp 2100   ");

        Token courseCollegeToken = tokenizer.getNextToken();
        assertSame(courseCollegeToken.getType(), Token.Type.COLLEGE);
        assertEquals(courseCollegeToken.getContent(), "Comp");

        Token courseNumberToken = tokenizer.getNextToken();
        assertSame(courseNumberToken.getType(), Token.Type.INT);
        assertEquals(courseNumberToken.getContent(), "2100");

    }

    @Test
    public void TestStringNumber() {

        tokenizer = new InputTokenizer(" abc 3456 ");

        Token stringToken = tokenizer.getNextToken();
        assertSame(stringToken.getType(), Token.Type.STRING);
        assertEquals(stringToken.getContent(), "abc");

        Token numberToken= tokenizer.getNextToken();
        assertSame(numberToken.getType(), Token.Type.INT);
        assertEquals(numberToken.getContent(), "3456");

    }

    @Test
    public void TestMajorSet() {

        tokenizer = new InputTokenizer(" computerscience ");

        Token stringToken = tokenizer.getNextToken();
        assertSame(stringToken.getType(), Token.Type.MAJOR);
        assertEquals(stringToken.getContent(), "computerscience");

    }

}
