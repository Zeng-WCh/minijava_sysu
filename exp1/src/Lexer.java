import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;

public class Lexer {
    private int lastChar;
    private int index;
    private ArrayList<Character> readIn;
    private String buf;
    private Token tokNow;
    private boolean holdOn;

    private void read() throws IOException {
        this.lastChar = System.in.read();
        if (this.lastChar == '\n' || this.lastChar == '\r' || this.lastChar == -1) {
            return;
        }
        ++this.index;
        this.readIn.add((char)this.lastChar);
    }

    public Lexer() throws IOException {
        this.index = 0;
        this.readIn = new ArrayList<>();
        read();
        this.buf = String.format("%c", (char)this.lastChar);
        this.tokNow = Token.tok_eof;
        this.holdOn = false;
    }

    public Token next() throws IOException {
        if (this.holdOn) {
            this.holdOn = false;
            return this.tokNow;
        }
        // One line parsing
        if ((char)this.lastChar == '\r' || (char)this.lastChar == '\n' || this.lastChar == -1) {
            this.tokNow = Token.tok_eof;
            return Token.tok_eof;
        }
        // escape white spaces
        if (Character.isWhitespace((char)this.lastChar)) {
            read();
        }

        // num
        if (Character.isDigit((char)this.lastChar)) {
            StringBuilder strBd = new StringBuilder();
            strBd.append((char)this.lastChar);
            read();
            while (Character.isDigit((char)this.lastChar)) {
                strBd.append((char)this.lastChar);
                read();
            }
            this.buf = strBd.toString();
            this.tokNow = Token.tok_num;
            return Token.tok_num;
        }

        if ((char)this.lastChar == '+') {
            this.buf = String.format("%c", (char)this.lastChar);
            read();
            this.tokNow = Token.tok_plus;
            return Token.tok_plus;
        }

        // '-' can be an operator
        // '-1' is a num
        // thus need to special process
        // but, this will give to parser to handle it, so just return tok_minus
        if ((char)this.lastChar == '-') {
            // StringBuilder strBd = new StringBuilder();
            // strBd.append((char)this.lastChar);
            // read();
            // if (Character.isDigit((char)this.lastChar)) {
            //     while (Character.isDigit((char)this.lastChar)) {
            //         strBd.append((char)this.lastChar);
            //         read();
            //     }
            //     this.buf = strBd.toString();
            //     this.tokNow = Token.tok_num;
            //     return Token.tok_num;
            // }
            this.buf = String.format("%c", (char)this.lastChar);
            read();
            this.tokNow = Token.tok_minus;
            return Token.tok_minus;
        }

        if ((char)this.lastChar == '*') {
            this.buf = String.format("%c", (char)this.lastChar);
            read();
            this.tokNow = Token.tok_star;
            return Token.tok_star;
        }

        if ((char)this.lastChar == '/') {
            this.buf = String.format("%c", (char)this.lastChar);
            read();
            this.tokNow = Token.tok_slash;
            return Token.tok_slash;
        }

        if ((char)this.lastChar == '(') {
            this.buf = String.format("%c", (char)this.lastChar);
            read();
            this.tokNow = Token.tok_lP;
            return Token.tok_lP;
        }

        if ((char)this.lastChar == ')') {
            this.buf = String.format("%c", (char)this.lastChar);
            read();
            this.tokNow = Token.tok_rP;
            return Token.tok_rP;
        }

        logError(String.format("Unknown token: %c", this.lastChar));
        this.tokNow = Token.tok_unknown;
        read();
        return Token.tok_unknown;
    }

    public void Debug() throws IOException {
        while (this.next() != Token.tok_eof) {
            System.out.println(this.tokNow);
        }
    }

    private void logError(String info) {
        String passed = this.getReadIn();
        System.out.println(passed);
        for (int i = 0; i < this.index - 1; ++i) {
            System.out.write(' ');
        }
        System.out.printf("^ %s\n", info);
    }

    public String getReadIn() {
        StringBuilder strBd = new StringBuilder();
        for (char ch : this.readIn) {
            strBd.append(ch);
        }
        return strBd.toString();
    }

    public String getBuf() {
        return this.buf;
    }

    public void hold() {
        this.holdOn = true;
    }

    public int getIdx() {
        return this.index;
    }
}
