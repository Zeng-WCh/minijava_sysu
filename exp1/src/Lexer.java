import java.io.IOException;
import java.util.ArrayList;

/**
 * A Class That Represents The Lexicon Analyzer
 * Read from the standart input
 * 
 */
public class Lexer {
    /**
     * Next character
     */
    static int lookahead;
    /**
     * Current position
     */
    static int curPos;
    /**
     * Current character
     */
    static int curChar;
    /**
     * buffer is used to record all character that has been processed
     */
    private ArrayList<Character> buffer;

    /**
     * A private method that used to read next char and do some process
     * 
     * @throws IOException
     */
    private void read() throws IOException {
        lookahead = System.in.read();
        ++curPos;
        // -1 means EOF, just return
        // '\r' or '\n' means a new line, we are one line parsing, so just leave it
        if (lookahead == -1 || (char) lookahead == '\r' || (char) lookahead == '\n') {
            return;
        }
        buffer.add((char) lookahead);
    }

    Lexer() throws IOException {
        lookahead = 0;
        curPos = 0;
        curChar = -1;
        this.buffer = new ArrayList<>();
        read();
    }

    /**
     * 
     * @return Next token that come from standart input
     * @throws IOException
     */
    public Token next() throws IOException {
        // EOF, end of file
        if (lookahead == -1 || (char) lookahead == '\r' || (char) lookahead == '\n') {
            return Token.tok_eof;
        }

        // numbers
        if (Character.isDigit((char) lookahead)) {
            curChar = lookahead;
            read();
            return Token.tok_num;
        }

        if (Character.isSpaceChar((char) lookahead)) {
            curChar = lookahead;
            read();
            return Token.tok_space;
        }

        // +
        if ((char) lookahead == '+') {
            curChar = lookahead;
            read();
            return Token.tok_plus;
        }

        // -
        if ((char) lookahead == '-') {
            curChar = lookahead;
            read();
            return Token.tok_minus;
        }

        // Unknown token
        curChar = lookahead;
        read();
        return Token.tok_unknown;
    }

    /**
     * 
     * @return what exactly current character is.
     *         For example, if current Token is a number 9, then it will return '9'
     */
    public int getCur() {
        return curChar;
    }

    /**
     * 
     * @return where the current position is, can be used in display error info
     */
    public int getPos() {
        return curPos;
    }

    /**
     * 
     * @return all the characters that have been read in, whatever they are valid or
     *         not
     */
    public String getReadIn() {
        StringBuilder strbd = new StringBuilder();
        for (char c : buffer) {
            strbd.append(c);
        }
        return strbd.toString();
    }
}
