import java.io.*;
import java.util.ArrayList;

class lexError extends Error {
    private int pos;
    private String readIn;
    
    public lexError(String info, String buf, int pos) {
        super(info);
        this.readIn = buf;
        this.pos = pos;
    }

    /**
     * 1+
     *  ^ Require more num
     */
    public void logError() {
        System.out.println(this.readIn);
        for (int i = 0; i < this.pos; ++i) {
            System.out.write(' ');
        }
        System.out.write('^');
        System.out.write(' ');
        System.out.println(this.getMessage());
    }
} 

public class lexer {
    private String buf;
    private ArrayList<Character> readIn;
    private int index;
    private int lastChar;

    public lexer() {
        this.buf = "";
        this.readIn = new ArrayList<>();
        this.index = 0;
        this.lastChar = ' ';
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
        while (Character.isSpaceChar(this.lastChar)) {
            this.lastChar = System.in.read();
        }

        // num, [1-9][0-9]+ | [0-9]
        if (Character.isDigit(this.lastChar)) {
            this.buf = String.format("%c", (char)this.lastChar);
            this.lastChar = System.in.read();
            while (Character.isDigit(this.lastChar)) {
                this.buf += String.format("%c", (char)this.lastChar);
                this.lastChar = System.in.read();
            }
            return token.tok_num;
        }

        // +, -
        if ((char)this.lastChar == '+') {
            this.buf = String.format("%c", (char)this.lastChar);
            this.lastChar = System.in.read();
            return token.tok_plus;
        }

        else if ((char)this.lastChar == '-') {
            this.buf = String.format("%c", (char)this.lastChar);
            this.lastChar = System.in.read();
            return token.tok_minus;
        }

        return token.tok_unknown;
    }

    public String getBuf() {
        return this.buf;
    }

    public void clean() {
        this.index = 0;
        this.buf = "";
        this.readIn.clear();
    }
}
