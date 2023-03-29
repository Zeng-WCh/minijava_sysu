import java.io.IOException;
import java.util.ArrayList;

/**
 * Lexer, read in the input and generate tokens.
 */
public class Lexer {
    static private int lastChar;
    private int index;
    private ArrayList<Character> readIn;
    private String buf;
    private Token tokNow;
    private boolean holdOn;
    private boolean end;

    /**
     * Read in the next char and add it to the readIn list
     * @throws IOException
     */
    private void read() throws IOException {
        lastChar = System.in.read();
        ++this.index;
        if (lastChar == '\n' || lastChar == '\r' || lastChar == -1) {
            return;
        }
        this.readIn.add((char)lastChar);
    }

    public Lexer() throws IOException {
        this.index = 0;
        this.readIn = new ArrayList<>();
        read();
        this.buf = String.format("%c", (char)lastChar);
        this.tokNow = Token.tok_eof;
        this.holdOn = false;
        this.end = false;
    }

    /**
     * Get the current token
     * @return token for now
     * @throws IOException
     */
    public Token next() throws IOException {
        if (this.holdOn) {
            this.holdOn = false;
            return this.tokNow;
        }
        if (this.end) {
            return Token.tok_eof;
        }
        // One line parsing
        if ((char)lastChar == '\r' || (char)lastChar == '\n' || lastChar == -1) {
            ++this.index;
            this.end = true;
            this.tokNow = Token.tok_eof;
            return Token.tok_eof;
        }

        // escape white spaces
        while (Character.isSpaceChar((char)lastChar)) {
            read();
        }

        // num
        if (Character.isDigit((char)lastChar)) {
            StringBuilder strBd = new StringBuilder();
            strBd.append((char)lastChar);
            read();
            while (Character.isDigit((char)lastChar)) {
                strBd.append((char)lastChar);
                read();
            }
            this.buf = strBd.toString();
            this.tokNow = Token.tok_num;
            return Token.tok_num;
        }

        if ((char)lastChar == '+') {
            this.buf = String.format("%c", (char)lastChar);
            read();
            this.tokNow = Token.tok_plus;
            return Token.tok_plus;
        }

        // '-' can be an operator
        // '-1' is a num
        // thus need to special process
        // but, this will give to parser to handle it, so just return tok_minus
        if ((char)lastChar == '-') {
            // StringBuilder strBd = new StringBuilder();
            // strBd.append((char)lastChar);
            // read();
            // if (Character.isDigit((char)lastChar)) {
            //     while (Character.isDigit((char)lastChar)) {
            //         strBd.append((char)lastChar);
            //         read();
            //     }
            //     this.buf = strBd.toString();
            //     this.tokNow = Token.tok_num;
            //     return Token.tok_num;
            // }
            this.buf = String.format("%c", (char)lastChar);
            read();
            this.tokNow = Token.tok_minus;
            return Token.tok_minus;
        }

        if ((char)lastChar == '*') {
            this.buf = String.format("%c", (char)lastChar);
            read();
            this.tokNow = Token.tok_star;
            return Token.tok_star;
        }

        if ((char)lastChar == '/') {
            this.buf = String.format("%c", (char)lastChar);
            read();
            this.tokNow = Token.tok_slash;
            return Token.tok_slash;
        }

        if ((char)lastChar == '(') {
            this.buf = String.format("%c", (char)lastChar);
            read();
            this.tokNow = Token.tok_lP;
            return Token.tok_lP;
        }

        if ((char)lastChar == ')') {
            this.buf = String.format("%c", (char)lastChar);
            read();
            this.tokNow = Token.tok_rP;
            return Token.tok_rP;
        }

        this.tokNow = Token.tok_unknown;
        read();
        return Token.tok_unknown;
    }

    // /**
    //  * Print out the error message
    //  * @param info
    //  */
    // private void logError(String info) {
    //     String passed = this.getReadIn();
    //     System.out.println(passed);
    //     for (int i = 0; i < this.index - 1; ++i) {
    //         System.out.write(' ');
    //     }
    //     System.out.printf("^ %s\n", info);
    // }

    /**
     * 
     * @return readIn list as a string
     */
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

    public String getBuf(int l, int r) {
        StringBuilder bd = new StringBuilder();
        for (; l < r; ++l) {
            bd.append(this.readIn.get(l));
        }
        return bd.toString();
    }

    /**
     * Hold the current token, in case the parser need to use it again
     */
    public void hold() {
        this.holdOn = true;
    }

    public int getIdx() {
        return this.index - this.buf.length() - 1;
    }
}
