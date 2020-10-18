package com.example.comp2100_6442_s2_2020_group_project;

/**
 * Tokenizer for user input
 *
 * @author Xinyu Zheng
 */
public class InputTokenizer extends Tokenizer {

    private String _buffer;
   // Token token;
    public InputTokenizer(String input) {
        this._buffer = input;
    }

    @Override
    public boolean hasNext() {
        return !_buffer.trim().replaceFirst("^,", "").isEmpty();
    }

    @Override
    public Token getNextToken() {
        if (!this.hasNext()) {
            throw new AssertionError();
        }

        _buffer = _buffer.trim().replaceFirst("^,", "");

        if (Character.isDigit(_buffer.charAt(0))) {
            int nxt = 0;
            int number = 0;
            while (nxt < _buffer.length() && Character.isDigit(_buffer.charAt(nxt))) {
                number = number * 10 + (_buffer.charAt(nxt) - '0');
                nxt++;
            }
            _buffer = _buffer.substring(nxt);
            return new Token("" + number, Token.Type.INT);
        } else {
            int nxt = 0;
            StringBuilder sb = new StringBuilder();
            while (nxt < _buffer.length() && _buffer.charAt(nxt) != ','
                    && !Character.isDigit(_buffer.charAt(nxt))) {
                sb.append(_buffer.charAt(nxt));
                nxt++;
            }
            _buffer = _buffer.substring(nxt);
            return new Token(sb.toString().replaceAll("\\s*",""));
        }
    }
}











