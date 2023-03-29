import java.io.IOException;

// <expr> ::= <term> <exprT>
// <exprT> ::= + <term> <exprT>
//           | - <term> <exprT>
//           | <empty>
// <term> ::= <factor> <termT>
// <termT> ::= * <factor> <termT>
//           | / <factor> <termT>
//           | <empty>
// <factor> ::= ( <expr> )
//           | Num
//           | - Factor

/**
 * Parser, parse the input expression and build the AST.
 */
public class Parser {
    private Lexer l;
    private ast root;
    public Parser() throws IOException {
        this.l = new Lexer();
        this.root = null;
    }

    /**
     * Parse start point
     * @throws IOException
     */
    public void parse() throws IOException {
        this.root = parseExpr();
    }

    // <expr> ::= <term> <exprT>
    /**
     * Parse Expr and return the root of AST
     * @return root of ast
     * @throws IOException
     */
    private ast parseExpr() throws IOException {
        ast term = this.parseTerm();
        return parseExprT(term);
    }

    // <term> ::= <factor> <termT>
    /**
     * Parse Term and return the AST
     * @return
     * @throws IOException
     */
    private ast parseTerm() throws IOException {
        ast factor = this.parseFactor();
        return parseTermT(factor);
    }

    // <exprT> ::= + <term> <exprT>
    //           | - <term> <exprT>
    //           | <empty>
    /**
     * Parse ExprT and return the AST
     * @param term
     * @return ast node
     * @throws IOException
     */
    private ast parseExprT(ast term) throws IOException {
        Token t = next();

        while (t == Token.tok_num) {
            logError("Expecting an operator, giving a '+'\nContinue parsing...");
            term = new opAst('+', term, new numAst(Double.parseDouble(this.l.getBuf())));
            t = next();
        }

        while (t == Token.tok_plus || t == Token.tok_minus) {
            char op = this.l.getBuf().charAt(0);
            ast term1 = this.parseTerm();
            ast current = new opAst(op, term, term1);
            term = current;
            t = next();

            while (t == Token.tok_num) {
                logError("Expecting an operator, giving a '+'\nContinue parsing...");
                term = new opAst('+', term, new numAst(Double.parseDouble(this.l.getBuf())));
                t = next();
            }
        }        

        this.l.hold();
        return term;
    }

    // <factor> ::= ( <expr> )
    //            | Num
    //            | - <factor>
    /**
     * Parse Factor and return the AST
     * @return ast node
     * @throws IOException
     */
    private ast parseFactor() throws IOException {
        Token t = next();
        if (t == Token.tok_lP) {
            int now = this.l.getIdx();
            ast expr = parseExpr();
            t = next();
            if (t != Token.tok_rP) {
                logError("Missing match ')' for this\nContinue parsing...", now);
                this.l.hold();
            }
            return expr;
        }
        else if (t == Token.tok_num) {
            return new numAst(Double.parseDouble(this.l.getBuf()));
        }
        else if (t == Token.tok_minus) {
            ast factor = parseFactor();
            return new opAst('-', new numAst(0), factor);
        }
        else {
            // Syntax Error, and just return a 0
            logError("Expecting a number or expression\nContinue parsing...");
            this.l.hold();
            return new numAst();
        }
    }

    // <termT> ::= * <factor> <termT>
    //           | / <factor> <termT>
    //           | <empty>
    /**
     * Parse TermT and return the AST
     * @param factor
     * @return
     * @throws IOException
     */
    private ast parseTermT(ast factor) throws IOException {
        Token t = next();

        while (t == Token.tok_num) {
            logError("Expecting an operator, giving a '*'\nContinue parsing...");
            factor = new opAst('*', factor, new numAst(Double.parseDouble(this.l.getBuf())));
            t = next();
        }
        
        while (t == Token.tok_star || t == Token.tok_slash) {
            char op = this.l.getBuf().charAt(0);
            ast factor1 = parseFactor();
            if (op == '/' && factor1.eval() == 0) {
                logError("Divided by 0\nContinue parsing...");
            }
            ast t2 = new opAst(op, factor, factor1);
            factor = t2;
            t = next();

            while (t == Token.tok_num) {
                logError("Expecting an operator, giving a '*'\nContinue parsing...");
                factor = new opAst('*', factor, new numAst(Double.parseDouble(this.l.getBuf())));
                t = next();
            }
        }

        this.l.hold();
        return factor;
    }

    /**
     * Log error and print the error message
     * @param info, error message
     */
    private void logError(String info) {
        logError(info, this.l.getIdx() - 1);
    }

    /**
     * Log error and print the error message
     * @param info, error message
     * @param pos the position of the error
     */
    private void logError(String info, int pos) {
        String pass = this.l.getReadIn();
        System.out.println(pass);
        for (int i = 0; i < pos; ++i) {
            System.out.write(' ');
        }
        System.out.printf("^ %s\n", info);
    }

    /**
     * Log error and print the error message
     * @param info, error message
     * @param pos, the position of the error
     * @param length, the length of the error input
     */
    private void logError(String info, int pos, int length) {
        String pass = this.l.getReadIn();
        System.out.println(pass);
        for (int i = 0; i < pos; ++i) {
            System.out.write(' ');
        }
        System.out.write('^');
        if (length > 1) {
            for (int i = 0; i < length - 1; ++i) {
                System.out.write('~');
            }
        }
        System.out.printf(" %s\n", info);
    }

    /**
     * return the post fix expression string
     * @return
     */
    public String postFix() {
        return this.root.postFix();
        // System.out.printf("Postfix is: %s\n", this.root.postFix());
    }

    /**
     * return the result of the expression
     * @return
     */
    public double eval() {
        return this.root.eval();
        // System.out.printf("Result is: %f\n", this.root.eval());
    }

    /**
     * 
     * @return the next token that is not unknown to the parser
     * @throws IOException
     */
    private Token next() throws IOException {
        Token t = this.l.next();
        int l = -1, r = -1;

        if (t == Token.tok_unknown) {
            l = this.l.getIdx();

            while(t == Token.tok_unknown) {
                t = this.l.next();
            }
        }
        r = this.l.getIdx();

        if (l != -1) {
            logError(String.format("Unknown token: %s\nIgnoring it and continue parsing...", this.l.getBuf(l, r)), l, r - l);
        }

        return t;
    }

}