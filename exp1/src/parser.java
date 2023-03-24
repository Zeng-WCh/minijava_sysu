import java.io.IOException;

// <expr> ::= <term> <expr_tail>
// <expr_tail> ::= + <term> <expr_tail>
//               | - <term> <expr_tail>
//               | <empty>
// <term> ::= <factor> <term_tail>
// <term_tail> ::= * <factor> <term_tail>
//               | / <factor> <term_tail>
//               | <empty>
// <factor> ::= ( <expr> )
//            | Num

public class Parser {
    private Lexer l;
    private ast root;
    public Parser() throws IOException {
        this.l = new Lexer();
        this.root = null;
    }

    public void parse() throws IOException {
        this.root = parseExpr();
    }

    // <expr> ::= <term> <expr_tail>
    private ast parseExpr() throws IOException {
        ast term = this.parseTerm();
        return parseExprtail(term);
    }

    // <term> ::= <factor> <term_tail>
    private ast parseTerm() throws IOException {
        ast factor = this.parseFactor();
        return parseTermtail(factor);
    }

    // <expr_tail> ::= + <term> <expr_tail>
    //               | - <term> <expr_tail>
    //               | <empty>
    private ast parseExprtail(ast term) throws IOException {
        Token t = this.l.next();

        if (t == Token.tok_plus || t == Token.tok_minus) {
            // '+' or '-'
            char op = this.l.getBuf().charAt(0);
            ast term1 = this.parseTerm();
            ast current = new opAst(op, term, term1);
            return parseExprtail(current);

        }
        else {
            this.l.hold();
            return term;
        }
    }

    // <factor> ::= ( <expr> )
    //            | Num
    private ast parseFactor() throws IOException {
        Token t = this.l.next();
        if (t == Token.tok_lP) {
            ast expr = parseExpr();
            t = this.l.next();
            if (t != Token.tok_rP) {
                logError("Excepted ')'\nContinue parsing...");
                this.l.hold();
            }
            return expr;
        }
        else if (t == Token.tok_num) {
            return new numAst(Integer.parseInt(this.l.getBuf()));
        }
        else {
            // Syntax Error, and just return a 0
            logError("Excepted a number or expression\nContinue parsing...");
            return new numAst();
        }
    }

    // <term_tail> ::= * <factor> <term_tail>
    //               | / <factor> <term_tail>
    //               | <empty>
    private ast parseTermtail(ast factor) throws IOException {
        Token t = this.l.next();
        if (t == Token.tok_star || t == Token.tok_slash) {
            char op = this.l.getBuf().charAt(0);
            ast factor1 = parseFactor();
            ast t2 = new opAst(op, factor, factor1);
            return parseTermtail(t2);
        }
        else {
            this.l.hold();
            return factor;
        }
    }

    private void logError(String info) {
        String pass = this.l.getReadIn();
        int idx = this.l.getIdx() - 1;
        System.out.println(pass);
        for (int i = 0; i < idx; ++i) {
            System.out.write(' ');
        }
        System.out.printf("^ %s\n", info);
    }

    public void postFix() {
        System.out.printf("Postfix is: %s\n", this.root.postFix());
    }

    public void eval() {
        System.out.printf("Result is: %d\n", this.root.eval());
    }

}