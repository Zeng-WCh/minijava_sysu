import java.util.ArrayList;
import java.io.*;

public class lexer {
    private ArrayList<Character> input;
    private int index;

    public lexer() {
        this.input = new ArrayList<>();
        this.index = 0;
    }

    public token next() {
        return token.tok_unknown;
    }
}
