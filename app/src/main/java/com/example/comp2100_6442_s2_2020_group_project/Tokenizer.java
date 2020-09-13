package com.example.comp2100_6442_s2_2020_group_project;

/**
 * Tokenizer abstract class
 *
 * @author Xinyu Zheng
 */
public abstract class Tokenizer {

    /**
     * test if there is still token
     * @return true if there is still token
     */
    public abstract boolean hasNext();

    /**
     * get next token
     * @return next token
     */
    public abstract Token getNextToken();

}
