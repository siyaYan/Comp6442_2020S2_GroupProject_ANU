package com.example.comp2100_6442_s2_2020_group_project;

import java.util.ArrayList;
import java.util.List;

/**
 * <input> ::= <c> | <f> | <mj>
 * <c> ::= <col> | <col><id> | <col><id> <op>
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
    }

    /**
     * <input> ::= <c> | <fn> | <mj>
     */
    public List<String> parseInput() {
        List<String> rtn = new ArrayList<>();
        if (currentToken == null) {
            if (!_tokenizer.hasNext()) return rtn;
            currentToken = _tokenizer.getNextToken();
        }
        if (currentToken.getType() == Token.Type.COLLEGE) {
            rtn.addAll(parseC());
        } else if (currentToken.getType() == Token.Type.NAME) {
            rtn.addAll(parseF());
        } else if (currentToken.getType() == Token.Type.MAJOR) {
            rtn.add(currentToken.getContent());
            rtn.add("major");
        }
        return rtn;
    }

    /**
     * <c> ::= <col> | <col><id> | <col><id> <op>
     */
    public List<String> parseC() {
        List<String> rtn = new ArrayList<>();
        rtn.add(currentToken.getContent());
        if (!_tokenizer.hasNext()) {
            rtn.add("college");
            return rtn;
        }
        currentToken = _tokenizer.getNextToken();
        if (currentToken.getType() == Token.Type.INT) {
            rtn.set(rtn.size() - 1, rtn.get(rtn.size() - 1) + currentToken.getContent());
        }
        rtn.add("courseId");
        if (!_tokenizer.hasNext()) {
            return rtn;
        }
        currentToken = _tokenizer.getNextToken();
        if (currentToken.getType() == Token.Type.OPERATION) {
            rtn.add(currentToken.getContent());
        }
        return rtn;
    }

    public List<String> parseF() {
        List<String> rtn = new ArrayList<>();
        rtn.add(currentToken.getContent());
        rtn.add("courseName");
        if (!_tokenizer.hasNext()) {
            return rtn;
        }
        currentToken = _tokenizer.getNextToken();
        if (currentToken.getType() == Token.Type.OPERATION) {
            rtn.add(currentToken.getContent());
        }
        return rtn;
    }

    /**
     * <f> ::= <fn> | <fn> <op>
     */
    public static void main(String[] args) {
        InputTokenizer myInputTokenizer = new InputTokenizer("softwareconstruction pre");
        //InputTokenizer myInputTokenizer = new InputTokenizer("comp6442 pre");
        //InputTokenizer myInputTokenizer = new InputTokenizer("SoftwareConstruction");
        //InputTokenizer myInputTokenizer = new InputTokenizer("COMP");
        List<String> parsed = new Parser(myInputTokenizer).parseInput();
        for (String s : parsed) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
