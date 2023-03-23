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
public class parser {
    private lexer l;
    private ast root;

    public parser() {
        this.l = new lexer();
        this.root = null;
    }

    public void parse() throws IOException {
        this.root = parseExpr();
    }

    private ast parseExpr() throws IOException {
        ast term = parseTerm();
        return parseExprTail(term);
    }

    private ast parseExprTail(ast term) throws IOException {
        token t = this.l.next();

        if (t == token.tok_eof) {
            return null;
        }


        if (t == token.tok_plus) {
            ast t2 = parseTerm();
            ast ret = new opAst('+', term, t2);
            return parseExprTail(ret);
        }
        else if (t == token.tok_minus) {
            ast t2 = parseTerm();
            ast ret = new opAst('-', term, t2);
            return parseExprTail(ret);
        }

        this.l.hold();
        return term;
    }

    private ast parseTerm() throws IOException {
        ast factor = parseFactor();
        return parseTermTail(factor);
    }

    private ast parseTermTail(ast term) throws IOException {
        token t = this.l.next();

        if (t == token.tok_eof) {
            return null;
        }

        if (t == token.tok_star) {
            ast t2 = parseFactor();
            ast ret = new opAst('*', term, t2);
            return parseTermTail(ret);
        }
        else if (t == token.tok_slash) {
            ast t2 = parseFactor();
            ast ret = new opAst('/', term, t2);
            return parseTermTail(ret);
        }

        this.l.hold();
        return term;
    }

    private ast parseFactor() throws IOException {
        token t = this.l.next();

        if (t == token.tok_num) {
            return new numAst(Integer.parseInt(this.l.getBuf()));
        } else if (t == token.tok_leftParam) {
            ast expr = parseExpr();
            if (match(token.tok_rightParam))
                return expr;
            else {
                logError("Expecting ')'");
                this.l.hold();
                return expr;
            }
        } else {
            logError("Expecting number or '('");
            this.l.hold();
            return new numAst(0);
        }
    }

    private boolean match(token t) throws IOException {
        return t == this.l.next();
    }

    public String dump() {
        return this.root.backTrace();
    }

    public int eval() {
        return this.root.eval();
    }

    private void logError(String msg) {
        String s = this.l.getReadIn().replace('\n', ' ');
        int idx = this.l.getIdx();
        System.out.printf("%s\n", s);
        for (int i = 0; i < idx - 1; ++i) {
            System.out.print(' ');
        }
        System.out.printf("^ %s\n", msg);
        System.out.printf("Continue parsing...\n");
    }
}
