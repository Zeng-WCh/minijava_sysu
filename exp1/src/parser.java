import javax.imageio.IIOException;

import java.io.IOException;

public class parser {
    private lexer l;

    public parser() {
        this.l = new lexer();
    }

    public void parse() throws IOException {
        parseExpr();
    }

    private void parseExpr() throws IOException {
        parseTerm();
        parseRest();
    }

    private void parseTerm() throws IOException {
        if (match(token.tok_num)) {
            System.out.print(this.l.getBuf());
        } else {
            throw new Error("syntax error");
        }
    }

    private void parseRest() throws IOException {
        token t = this.l.next();
        if (t == token.tok_plus) {
            parseTerm();
            System.out.write('+');
            parseRest();
        } else if (t == token.tok_minus) {
            parseTerm();
            System.out.write('-');
            parseRest();
        } else {
            // do nothing with the input
        }
    }

    private boolean match(token t) throws IOException {
        return t == this.l.next();
    }

}
