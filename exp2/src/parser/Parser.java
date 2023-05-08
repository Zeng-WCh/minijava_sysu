package parser;

import ast.ast;
import exceptions.EmptyExpressionException;
import exceptions.ExpressionException;
import scanner.Buffer;
import scanner.Scanner;
import token.Token;
import token.TokenType;

import java.util.Stack;

class OPPTable {
    public static final int shift = 1;
    public static final int reduce = 2;
    public static final int accept = 3;
    public static final int error = -1;

    public static int opToToken(TokenType op) {
        if (op == TokenType.tok_lparen)
            return 0;
        if (op == TokenType.tok_rparen)
            return 1;
        if (op == TokenType.tok_sin)
            return 2;
        if (op == TokenType.tok_cos)
            return 3;
        if (op == TokenType.tok_min)
            return 4;
        if (op == TokenType.tok_max)
            return 5;
        if (op == TokenType.tok_unary_minus)
            return 6;
        if (op == TokenType.tok_caret)
            return 7;
        if (op == TokenType.tok_star)
            return 8;
        if (op == TokenType.tok_slash)
            return 9;
        if (op == TokenType.tok_plus)
            return 10;
        if (op == TokenType.tok_minus)
            return 11;
        if (op == TokenType.tok_greater)
            return 12;
        if (op == TokenType.tok_greater_equal)
            return 13;
        if (op == TokenType.tok_less)
            return 14;
        if (op == TokenType.tok_less_equal)
            return 15;
        if (op == TokenType.tok_equal)
            return 16;
        if (op == TokenType.tok_not_equal)
            return 17;
        if (op == TokenType.tok_not)
            return 18;
        if (op == TokenType.tok_and)
            return 19;
        if (op == TokenType.tok_or)
            return 20;
        if (op == TokenType.tok_question)
            return 21;
        if (op == TokenType.tok_colon)
            return 22;
        if (op == TokenType.tok_eof)
            return 23;
        if (op == TokenType.tok_true)
            return 24;
        if (op == TokenType.tok_false)
            return 25;
        if (op == TokenType.tok_decimal)
            return 26;
        if (op == TokenType.tok_comma)
            return 27;
        return -1;
    }

    public static int[][] OPPTable = {
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -1, -1, 1, 1, 1, 1 },
            { -1, 2, -1, -1, -1, -1, -1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, -1, 2, 2, 2, 2, 2, -1, -1, -1, 2 },
            { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1 },
            { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1 },
            { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1 },
            { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1 },
            { 1, 2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 2 },
            { 1, 2, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 2 },
            { 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 2 },
            { 1, 2, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 2 },
            { 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 2 },
            { 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 2 },
            { 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, -1, -1, 1, 1, 1, -1 },
            { 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, -1, -1, 1, 1, 1, -1 },
            { 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, -1, -1, 1, 1, 1, -1 },
            { 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, -1, -1, 1, 1, 1, -1 },
            { 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, -1, -1, 1, 1, 1, -1 },
            { 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, -1, -1, 1, 1, 1, -1 },
            { 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, -1, -1, 1, 1, 1, -1 },
            { 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, -1, -1, 1, 1, 1, -1 },
            { 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, -1, -1, 1, 1, 1, -1 },
            { 1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -1, 1, 1, 1, -1 },
            { 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 2 },
            { 1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -1, 3, 1, 1, 1, -1 },
            { -1, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 2, 2, 2, -1, -1, -1, -1, -1,
                    -1 },
            { -1, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 2, 2, 2, -1, -1, -1, -1, -1,
                    -1 },
            { -1, 2, -1, -1, -1, -1, -1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, -1, 2, 2, 2, 2, 2, -1, -1, -1, 2 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -1, -1, 1, 1, 1, 1 },

    };
}

public class Parser {
    private final Scanner sc;
    private final Stack<StackElement> stack;
    private Token lookahead;

    /**
     * Constructor of Parser
     * 
     * @param expression, the expression to be parsed
     * @throws EmptyExpressionException if the expression is empty
     * @throws ExpressionException      if the expression is invalid when parsing
     */
    public Parser(String expression) throws ExpressionException {
        if (expression == null || expression.isEmpty() || expression.isBlank())
            throw new EmptyExpressionException();
        this.sc = new Scanner(new Buffer(expression));
        this.stack = new Stack<>();
        this.stack.push(new Terminal(TokenType.tok_eof, "$"));
    }

    public ast parse() throws ExpressionException {
        lookahead = this.sc.next();
        Terminal top = null;
        while (true) {
            top = this.getTopTerminal();
            int op = OPPTable.OPPTable[OPPTable.opToToken(top.getType())][OPPTable.opToToken(lookahead.getType())];
            switch (op) {
                case OPPTable.shift:
                    this.shift(new Terminal(top.getType(), top.getValue()));
                    lookahead = this.sc.next();
                    break;
                case OPPTable.reduce:
                    this.reduce(top, new Terminal(top.getType(), top.getValue()));
                    break;
                case OPPTable.accept:
                    return this.getTopNonTerminal().genAST();  
                case OPPTable.error:
                    throw new ExpressionException();
            }
        }
    }

    /**
     * 
     * @return the Terminal on the nearest top of the stack, null if not found
     */
    private Terminal getTopTerminal() {
        int i = this.stack.size() - 1;
        while (i >= 0) {
            StackElement st = this.stack.get(i);
            if (st.isTerminal()) {
                return (Terminal) st;
            }
        }
        return null;
    }

    private NonTerminal getTopNonTerminal() {
        int i = this.stack.size() - 1;
        while (i >= 0) {
            StackElement st = this.stack.get(i);
            if (!st.isTerminal()) {
                return (NonTerminal) st;
            }
        }
        return null;
    }

    private void shift(Terminal t) {
        this.stack.push(t);
    }

    private void reduce(Terminal topofStack, Terminal t) {
        TokenType type = topofStack.getType();

        switch (type) {
            case tok_eof:
            break;
            case tok_lparen:
            break;
            case tok_rparen:
            break;
        }
    }
}
