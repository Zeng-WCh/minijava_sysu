import java.io.IOException;

public class Postfix {
    public static void main(String[] args) throws IOException {
        Parser p = new Parser();
        p.parse();
        p.eval();
        p.postFix();
    }
}