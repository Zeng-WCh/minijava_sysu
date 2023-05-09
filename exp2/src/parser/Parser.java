package parser;

import ast.*;
import exceptions.*;
import scanner.Buffer;
import scanner.Scanner;
import token.Token;
import token.TokenType;

import java.util.Stack;
import java.util.Vector;

/**
 * OPPTable generate by ./generateOPPTable.py
 *
 * @author Weichao Zeng
 * @version 1.00 (Last update: 2023/05/09)
 */
class OPPTable {
    public static final int shift = 1;
    public static final int reduce = 2;
    public static final int accept = 3;
    public static final int error = -1;

    public static int opToToken(TokenType op) {
        if (op == TokenType.tok_unary_minus)
            return 0;
        if (op == TokenType.tok_caret)
            return 1;
        if (op == TokenType.tok_star)
            return 2;
        if (op == TokenType.tok_slash)
            return 3;
        if (op == TokenType.tok_plus)
            return 4;
        if (op == TokenType.tok_minus)
            return 5;
        if (op == TokenType.tok_greater)
            return 6;
        if (op == TokenType.tok_greater_equal)
            return 7;
        if (op == TokenType.tok_less)
            return 8;
        if (op == TokenType.tok_less_equal)
            return 9;
        if (op == TokenType.tok_equal)
            return 10;
        if (op == TokenType.tok_not_equal)
            return 11;
        if (op == TokenType.tok_not)
            return 12;
        if (op == TokenType.tok_and)
            return 13;
        if (op == TokenType.tok_or)
            return 14;
        if (op == TokenType.tok_question)
            return 15;
        if (op == TokenType.tok_colon)
            return 16;
        if (op == TokenType.tok_eof)
            return 17;
        if (op == TokenType.tok_lparen)
            return 18;
        if (op == TokenType.tok_rparen)
            return 19;
        if (op == TokenType.tok_sin)
            return 20;
        if (op == TokenType.tok_cos)
            return 21;
        if (op == TokenType.tok_min)
            return 22;
        if (op == TokenType.tok_max)
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
            { 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2 },
            { 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2 },
            { 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2 },
            { 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2 },
            { 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2 },
            { 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2 },
            { 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, -1, -1, 1, 2, 1, 1, 1, 1, 1, 1, 1, -1 },
            { 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, -1, -1, 1, 2, 1, 1, 1, 1, 1, 1, 1, -1 },
            { 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, -1, -1, 1, 2, 1, 1, 1, 1, 1, 1, 1, -1 },
            { 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, -1, -1, 1, 2, 1, 1, 1, 1, 1, 1, 1, -1 },
            { 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, -1, -1, 1, 2, 1, 1, 1, 1, 1, 1, 1, -1 },
            { 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, -1, -1, 1, 2, 1, 1, 1, 1, 1, 1, 1, -1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, -1, -1, 1, 2, 1, 1, 1, 1, 1, 1, 1, -1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, -1, -1, 1, 2, 1, 1, 1, 1, 1, 1, 1, -1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, -1, -1, 1, 2, 1, 1, 1, 1, 1, 1, 1, -1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -1, 1, -1, 1, 1, 1, 1, 1, 1, 1, -1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -1, 3, 1, -1, 1, 1, 1, 1, 1, 1, 1, -1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { -1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, -1, 2, 2, 2, 2, 2, -1, 2, -1, -1, -1, -1, -1, -1, -1, 2 },
            { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1 },
            { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1 },
            { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1 },
            { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1 },
            { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 2, 2, 2, -1, -1, -1, 2, -1, -1, -1, -1, -1, -1, -1,
                    -1 },
            { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 2, 2, 2, -1, -1, -1, 2, -1, -1, -1, -1, -1, -1, -1,
                    -1 },
            { -1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, -1, 2, 2, 2, 2, 2, -1, 2, -1, -1, -1, -1, -1, -1, -1, 2 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },

    };
}

/**
 * Parser for ExprEval
 * 
 * @author Weichao Zeng
 * @version 1.00 (Last update: 2023/05/09)
 */
public class Parser {
    /**
     * The scanner used to tokenize the expression
     */
    private final Scanner sc;
    /**
     * Parsing Stack
     */
    private final Stack<StackElement> stack;
    /**
     * The lookahead token
     */
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

    /**
     * Print the stack for debug
     */
    private void printStack() {
        for (StackElement st : this.stack) {
            if (st.isTerminal()) {
                System.out.printf("Type: Terminal, %s %s\n", st.getType(), st.getValue());
            } else {
                System.out.printf("Type: NonTerminal, %s\n", ((NonTerminal) st).genAST().getClass());
            }
        }
    }

    /**
     * To check if current token require a decimal, used in errorHandler
     * 
     * @param type, current type of token
     * @return true if the token require a decimal, false otherwise
     */
    private static boolean requireDecimal(TokenType type) {
        return type == TokenType.tok_plus || type == TokenType.tok_minus || type == TokenType.tok_star
                || type == TokenType.tok_slash || type == TokenType.tok_greater || type == TokenType.tok_greater_equal
                || type == TokenType.tok_less || type == TokenType.tok_less_equal || type == TokenType.tok_equal
                || type == TokenType.tok_comma
                || type == TokenType.tok_not_equal;
    }

    /**
     * A error handler function, used to throw different subclass of ExpressionException
     * 
     * @param stackToken, the topest Terminal in stack
     * @param readIn, the newest token just read in
     * @throws ExpressionException
     */
    private static void errorHandler(TokenType stackToken, TokenType readIn) throws ExpressionException {
        if (stackToken == TokenType.tok_lparen && readIn != TokenType.tok_rparen) 
            throw new MissingRightParenthesisException();
        if (stackToken == TokenType.tok_rparen && readIn == TokenType.tok_decimal)
            throw new MissingOperatorException();
        if (stackToken == TokenType.tok_eof && readIn == TokenType.tok_rparen)
            throw new MissingLeftParenthesisException();
        if (stackToken == TokenType.tok_eof && readIn == TokenType.tok_colon)
            throw new TrinaryOperationException();
        // For example like min(1, 2) the stack will have at least a terminal named '(' when meet ',', so should throw MissingOprand
        // So I consider to throw a Missing LeftParen Exception given that ',' can only present between '(' and ')'
        if (stackToken == TokenType.tok_eof && readIn == TokenType.tok_comma)
            throw new MissingLeftParenthesisException();
        if (stackToken == TokenType.tok_true || stackToken == TokenType.tok_false) {
            if (requireDecimal(readIn))
                throw new TypeMismatchedException();
            else {
                throw new MissingOperatorException();
            }
        }
        throw new ExpressionException();
    }

    public ast parse() throws ExpressionException {
        lookahead = this.sc.next();
        Terminal top = null;
        while (true) {
            top = this.getTopTerminal();
            printStack();
            // System.out.println("Current Top: " + top.getType());
            // System.out.println("Current Lookahead: " + lookahead.toString());
            int op = OPPTable.OPPTable[OPPTable.opToToken(top.getType())][OPPTable.opToToken(lookahead.getType())];
            switch (op) {
                case OPPTable.shift:
                    this.shift(new Terminal(lookahead.getType(), lookahead.toString()));
                    lookahead = this.sc.next();
                    break;
                case OPPTable.reduce:
                    this.reduce(top, new Terminal(lookahead.getType(), lookahead.toString()));
                    break;
                case OPPTable.accept:
                    if (this.stack.peek().isTerminal())
                        throw new ExpressionException();
                    return ((NonTerminal) this.stack.peek()).genAST();
                case OPPTable.error:
                    errorHandler(top.getType(), lookahead.getType());
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Get the Terminal on the nearest top of the stack
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
            --i;
        }
        return null;
    }

    private void shift(Terminal t) {
        this.stack.push(t);
    }

    private void reduce(Terminal topofStack, Terminal t) throws ExpressionException {
        TokenType type = topofStack.getType();
        switch (type) {
            case tok_decimal:
                this.stack.pop();
                this.stack.push(new NonTerminal(TokenType.tok_decimal,
                        new ArithExpr(Double.parseDouble(topofStack.getValue()))));
                break;
            case tok_true:
            case tok_false:
                this.stack.pop();
                this.stack.push(new NonTerminal(type, new BoolExpr((type == TokenType.tok_true))));
                break;
            case tok_plus:
            case tok_minus:
            case tok_star:
            case tok_slash:
            case tok_caret:
                reduceOperator(type);
                break;
            case tok_unary_minus:
                reduceUnaryOperator(type);
                break;
            case tok_colon:
                reduceTripleOperator(type);
                break;
            case tok_rparen:
                reduceParam(type);
                break;
            case tok_greater:
            case tok_greater_equal:
            case tok_less:
            case tok_less_equal:
            case tok_equal:
            case tok_not_equal:
                reduceRalationOperator(type);
                break;
            case tok_and:
            case tok_or:
                reduceBooleanOperator(type);
                break;
            case tok_not:
                reduceBooleanUnaryOperator(type);
                break;
            default:
                break;
        }
    }

    private void reduceBooleanOperator(TokenType type) throws ExpressionException {
        if (this.stack.peek().isTerminal()) {
            throw new MissingOperandException();
        }
        NonTerminal r = (NonTerminal) this.stack.pop();
        this.stack.pop();
        if (this.stack.peek().isTerminal()) {
            throw new MissingOperandException();
        }
        NonTerminal l = (NonTerminal) this.stack.pop();
        this.stack.push(new NonTerminal(type, new BoolExpr(l.genAST(), type, r.genAST())));
    }

    private void reduceBooleanUnaryOperator(TokenType type) throws ExpressionException {
        if (this.stack.peek().isTerminal()) {
            throw new MissingOperandException();
        }

        NonTerminal oprand = (NonTerminal) this.stack.pop();

        this.stack.pop();

        this.stack.push(new NonTerminal(type, new BoolExpr(oprand.genAST(), type)));
    }

    private void reduceOperator(TokenType type) throws ExpressionException {
        if (this.stack.peek().isTerminal()) {
            throw new MissingOperandException();
        }
        NonTerminal r = (NonTerminal) this.stack.pop();
        this.stack.pop();
        if (this.stack.peek().isTerminal()) {
            throw new MissingOperandException();
        }
        NonTerminal l = (NonTerminal) this.stack.pop();

        ArithExpr lOprand = null, rOprand = null;

        try {
            lOprand = (ArithExpr) l.genAST();
            rOprand = (ArithExpr) r.genAST();
        } catch (Exception e) {
            throw new TypeMismatchedException();
        }

        this.stack.push(new NonTerminal(type, new ArithExpr(lOprand, type, rOprand)));
    }

    private void reduceUnaryOperator(TokenType type) throws ExpressionException {
        if (this.stack.peek().isTerminal())
            throw new MissingOperandException();
        NonTerminal Oprand = (NonTerminal) this.stack.pop();
        this.stack.pop();
        ArithExpr UnaryOprand = null;
        try {
            UnaryOprand = (ArithExpr) Oprand.genAST();
        } catch (Exception e) {
            throw new TypeMismatchedException();
        }
        this.stack.push(new NonTerminal(type, new ArithExpr(UnaryOprand, type)));
    }

    private void reduceTripleOperator(TokenType type) throws ExpressionException {
        if (this.stack.peek().isTerminal())
            throw new TrinaryOperationException();
        NonTerminal r2 = (NonTerminal) this.stack.pop();
        if (this.stack.pop().getType() != TokenType.tok_colon)
            throw new TrinaryOperationException();
        if (this.stack.peek().isTerminal())
            throw new TrinaryOperationException();
        NonTerminal r1 = (NonTerminal) this.stack.pop();
        if (this.stack.pop().getType() != TokenType.tok_question)
            throw new TrinaryOperationException();
        if (this.stack.peek().isTerminal())
            throw new TrinaryOperationException();
        NonTerminal l = (NonTerminal) this.stack.pop();

        BoolExpr condition = null;
        ArithExpr left = null, right = null;

        try {
            condition = (BoolExpr) l.genAST();
            left = (ArithExpr) r1.genAST();
            right = (ArithExpr) r2.genAST();
        } catch (Exception e) {
            throw new TypeMismatchedException();
        }

        this.stack.push(new NonTerminal(type,
                new ArithExpr(condition, TokenType.tok_question, left, TokenType.tok_colon, right)));
    }

    private void reduceRalationOperator(TokenType type) throws ExpressionException {
        if (this.stack.peek().isTerminal())
            throw new MissingOperandException();
        NonTerminal r = (NonTerminal) this.stack.pop();
        this.stack.pop();
        if (this.stack.peek().isTerminal())
            throw new ExpressionException();
        NonTerminal l = (NonTerminal) this.stack.pop();
        this.stack.push(new NonTerminal(type, new BoolExpr(l.genAST(), type, r.genAST())));
    }

    private void reduceParam(TokenType type) throws ExpressionException {
        // )
        this.stack.pop();
        Vector<StackElement> st = new Vector<>();
        StackElement top = this.stack.pop();
        // read until meet '('
        boolean getLparen = false;
        while (!this.stack.empty()) {
            st.add(top);
            top = this.stack.pop();
            if (top.getType() == TokenType.tok_lparen) {
                getLparen = true;
                break;
            }
        }

        if (!getLparen) {
            this.stack.push(top);
            throw new MissingLeftParenthesisException();
        }

        StackElement func = this.stack.peek();
        TokenType stType = func.getType();
        if (stType == TokenType.tok_sin || stType == TokenType.tok_cos) {
            this.stack.pop();
            ast tmp = buildParam(st, stType);
            ArithExpr unary = null;
            try {
                unary = (ArithExpr) tmp;
            } catch (Exception e) {
                throw new TypeMismatchedException();
            }
            this.stack.push(new NonTerminal(stType, new ArithExpr(new UnaryFunc(stType, unary))));
        } else if (stType == TokenType.tok_min || stType == TokenType.tok_max) {
            this.stack.pop();
            this.stack.push(new NonTerminal(stType, buildParam(st, stType)));
        } else {
            this.stack.push(new NonTerminal(type, buildParam(st, stType)));
        }
    }

    private ast buildParam(Vector<StackElement> st, TokenType type) throws ExpressionException {
        if ((type == TokenType.tok_sin || type == TokenType.tok_cos) && st.size() != 1) {
            throw new FunctionCallException();
        }

        if ((type == TokenType.tok_min || type == TokenType.tok_max) && st.size() < 3) {
            throw new MissingOperandException();
        }

        if (st.size() == 1) {
            if (st.lastElement().isTerminal())
                throw new ExpressionException();
            return ((NonTerminal) st.lastElement()).genAST();
        } else {
            ArithExpr right = null;
            ArithExpr left = null;
            ArithExprList r = null;
            if (st.size() == 3) {
                if (st.lastElement().isTerminal())
                    throw new ExpressionException();
                right = (ArithExpr) ((NonTerminal) st.remove(st.size() - 1)).genAST();
                st.remove(st.size() - 1);
                if (st.lastElement().isTerminal())
                    throw new ExpressionException();
                left = (ArithExpr) ((NonTerminal) st.remove(st.size() - 1)).genAST();
                return new ArithExpr(new VariablFunc(type, left, new ArithExprList(right, type)));
            }
            while (st.size() != 3) {
                if (r == null) {
                    if (st.lastElement().isTerminal())
                        throw new ExpressionException();
                    right = (ArithExpr) ((NonTerminal) st.remove(st.size() - 1)).genAST();
                    r = new ArithExprList(right, type);
                    st.add(new NonTerminal(type, r));
                } else {
                    if (st.lastElement().isTerminal())
                        throw new ExpressionException();
                    r = (ArithExprList) ((NonTerminal) st.remove(st.size() - 1)).genAST();
                    st.remove(st.size() - 1);
                    if (st.lastElement().isTerminal())
                        throw new ExpressionException();
                    left = (ArithExpr) ((NonTerminal) st.remove(st.size() - 1)).genAST();
                    r = new ArithExprList(left, r, type);
                    st.add(new NonTerminal(type, r));
                }
            }
            if (st.lastElement().isTerminal())
                throw new ExpressionException();
            r = (ArithExprList) ((NonTerminal) st.remove(st.size() - 1)).genAST();
            st.remove(st.size() - 1);
            if (st.lastElement().isTerminal())
                throw new ExpressionException();
            left = (ArithExpr) ((NonTerminal) st.remove(st.size() - 1)).genAST();
            return new ArithExpr(new VariablFunc(type, left, r));
        }
    }
}
