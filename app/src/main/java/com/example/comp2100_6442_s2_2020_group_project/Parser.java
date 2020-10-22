package com.example.comp2100_6442_s2_2020_group_project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <input> ::= <cs> | <fs> | <mj>
 * <cs> ::= <c> | <c> <cs> | <c> <fs>
 * <c> ::= <sub> | <sub> <id> | <sub> <id> <op>
 * <fs> ::= <f> | <f> <fs> | <f> <cs>
 * <f> ::= <fn> | <fn> <op>
 * <id> ::= <unsigned integer>
 *
 * @author Xinyu Zheng
 */
public class Parser {

    private InputTokenizer _tokenizer;
    private Token currentToken;

    public Parser(InputTokenizer tokenizer) {
        this._tokenizer = tokenizer;
        this.currentToken = _tokenizer.getNextToken();
    }

    /**
     * <input> ::= <cs> | <fn> | <mj>
     */
    public List<List<String>> parseInput() {
        List<List<String>> rtn = new ArrayList<>();
        if (currentToken == null) {
            return rtn;
        } else if (currentToken.getType() == Token.Type.SUBJECT) {
            rtn.addAll(parseCs());
        } else if (currentToken.getType() == Token.Type.NAME) {
            rtn.addAll(parseFs());
        } else if (currentToken.getType() == Token.Type.MAJOR) {
            rtn.add(Arrays.asList(currentToken.getContent(), "major"));
        }
        return rtn;
    }

    /**
     * <cs> ::= <c> | <c> <cs> | <c> <fs>
     */
    public List<List<String>> parseCs() {
        List<List<String>> rtn = new ArrayList<>();
        if (currentToken == null) return rtn;
        rtn.add(parseC());
        if (currentToken == null) return rtn;
        if (currentToken.getType() == Token.Type.SUBJECT) rtn.addAll(parseCs());
        else rtn.addAll(parseFs());
        return rtn;
    }

    /**
     * <c> ::= <sub> | <sub><id> | <sub><id> <op>
     */
    public List<String> parseC() {
        List<String> rtn = new ArrayList<>();
        if (currentToken != null && currentToken.getType() == Token.Type.SUBJECT) {
            rtn.add(currentToken.getContent());
            currentToken = _tokenizer.getNextToken();
        }
        if (currentToken == null) {
            rtn.add("college");
            return rtn;
        } else if (currentToken.getType() == Token.Type.INT) {
            rtn.set(rtn.size() - 1, rtn.get(rtn.size() - 1) + currentToken.getContent());
            rtn.add("courseId");
            currentToken = _tokenizer.getNextToken();
        }
        if (currentToken != null && currentToken.getType() == Token.Type.OPERATION) {
            rtn.add(currentToken.getContent());
            currentToken = _tokenizer.getNextToken();
        }
        return rtn;
    }

    /**
     * <fs> ::= <f> | <f> <fs> | <f> <cs>
     */
    public List<List<String>> parseFs() {
        List<List<String>> rtn = new ArrayList<>();
        if (currentToken == null) return rtn;
        rtn.add(parseF());
        if (currentToken == null) return rtn;
        if (currentToken.getType() == Token.Type.SUBJECT) rtn.addAll(parseCs());
        else rtn.addAll(parseFs());
        return rtn;
    }

    /**
     * <f> ::= <fn> | <fn> <op>
     */
    public List<String> parseF() {
        List<String> rtn = new ArrayList<>();
        if (currentToken != null && currentToken.getType() == Token.Type.NAME) {
            rtn.add(currentToken.getContent());
            rtn.add("courseName");
            currentToken = _tokenizer.getNextToken();
        }
        if (currentToken != null && currentToken.getType() == Token.Type.OPERATION) {
            rtn.add(currentToken.getContent());
            currentToken = _tokenizer.getNextToken();
        }
        return rtn;
    }

}
