import java.io.*;
import java.util.ArrayList;

public class lexer {
    private String buf;
    private ArrayList<Character> readIn;
    private int index;
    private int lastChar;
    private token now;
    private boolean holdOn;

    public lexer() throws IOException {
        this.readIn = new ArrayList<>();
        this.index = 1;
        this.lastChar = System.in.read();
        this.buf = "" + this.lastChar;
        this.readIn.add((char)this.lastChar);
        this.now = token.tok_unknown;
        this.holdOn = false;
    }

    private void read() throws IOException {
        this.lastChar = System.in.read();
        if (this.lastChar == '\n' || this.lastChar == '\r') {
            return;
        }
        this.readIn.add((char)this.lastChar);
        this.index++;
    }

    public token next() throws IOException {
        // +,-, num
        // this.lastChar = System.in.read();
        // readIn.add((char)this.lastChar);
        // this.index++;
        // if (Character.isDigit((this.lastChar))) {
        //     this.buf = String.format("%c", (char)this.lastChar);
        //     this.lastChar = System.in.read();
        //     while (Character.isDigit((this.lastChar))) {
        //         this.buf = String.format("%c", (char)this.lastChar);
        //         this.lastChar = System.in.read();
        //     }
        // }
        if (this.holdOn) {
            this.holdOn = false;
            return this.now;
        }

        if ((char)this.lastChar == '\n' || (char)this.lastChar == '\r') {
            return token.tok_eof;
        }

        while (Character.isSpaceChar(this.lastChar)) {
            read();
        }

        // num, [1-9][0-9]+ | [0-9]
        if (Character.isDigit(this.lastChar)) {
            this.buf = String.format("%c", (char)this.lastChar);
            read();
            while (Character.isDigit(this.lastChar)) {
                this.buf += String.format("%c", (char)this.lastChar);
                read();
            }
            this.now = token.tok_num;
            return token.tok_num;
        }

        if ((char)this.lastChar == '+') {
            this.buf = String.format("%c", (char)this.lastChar);
            read();
            this.now = token.tok_plus;
            return token.tok_plus;
        }

        else if ((char)this.lastChar == '-') {
            this.buf = String.format("%c", (char)this.lastChar);
            read();
            this.now = token.tok_minus;
            return token.tok_minus;
        }
        
        else if ((char)this.lastChar == '*') {
            this.buf = String.format("%c", (char)this.lastChar);
            read();
            this.now = token.tok_star;
            return token.tok_star;
        }

        else if ((char)this.lastChar == '/') {
            this.buf = String.format("%c", (char)this.lastChar);
            read();
            this.now = token.tok_slash;
            return token.tok_slash;
        }

        else if ((char)this.lastChar == '(') {
            this.buf = String.format("%c", (char)this.lastChar);
            read();
            this.now = token.tok_leftParam;
            return token.tok_leftParam;
        }

        else if ((char)this.lastChar == ')') {
            this.buf = String.format("%c", (char)this.lastChar);
            read();
            this.now = token.tok_rightParam;
            return token.tok_rightParam;
        }

        else {
            System.out.println("Unknown token: " + (char)this.lastChar);
            read();
        }
        return token.tok_unknown;
    }

    public String getBuf() {
        return this.buf;
    }

    public int getIdx() {
        return this.index - 1;
    }

    public token now() {
        return this.now;
    }

    public void hold() {
        this.holdOn = true;
    }

    public String getReadIn() {
        StringBuilder ret = new StringBuilder();
        for (char c : this.readIn) {
            ret.append(c);
        }
        return ret.toString();
    }
}
