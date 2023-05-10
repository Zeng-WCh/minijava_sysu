package parser;

import ast.*;
import exceptions.*;
import scanner.Buffer;
import scanner.Scanner;
import token.Token;
import token.TokenType;

import java.util.Stack;

/**
 * OPPTable generate by ./generateOPPTable.py
 *
 * @author Weichao Zeng
 * @version 1.00 (Last update: 2023/05/10)
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
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2 },
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
     * The result of parsing
     */
    private ast ExprAst;

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
        this.ExprAst = null;
    }

    /**
     * Print the stack for debug
     */
    public void printStack() {
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
     * @param type current type of token
     * @return true if the token require a decimal, false otherwise
     */
    private static boolean requireDecimal(TokenType type) {
        return type == TokenType.tok_plus || type == TokenType.tok_minus || type == TokenType.tok_star
                || type == TokenType.tok_slash || type == TokenType.tok_greater || type == TokenType.tok_greater_equal
                || type == TokenType.tok_less || type == TokenType.tok_less_equal || type == TokenType.tok_equal
                || type == TokenType.tok_comma
                || type == TokenType.tok_not_equal || type == TokenType.tok_eof;
    }

    /**
     * To check if current token is a relation operator
     * 
     * @param type token needs to be checked
     * @return true if is
     */
    private static boolean isRelation(TokenType type) {
        return type == TokenType.tok_and || type == TokenType.tok_or || type == TokenType.tok_greater
                || type == TokenType.tok_less || type == TokenType.tok_equal || type == TokenType.tok_not_equal
                || type == TokenType.tok_greater_equal || type == TokenType.tok_less_equal;
    }

    /**
     * A error handler function, used to throw different subclass of
     * ExpressionException
     * 
     * @param stackToken, the topest Terminal in stack
     * @param readIn,     the newest token just read in
     * @throws ExpressionException if error occurs
     */
    private static void errorHandler(TokenType stackToken, TokenType readIn) throws ExpressionException {
        if (stackToken == TokenType.tok_lparen && readIn == TokenType.tok_colon)
            throw new TrinaryOperationException();
        if (stackToken == TokenType.tok_lparen && readIn != TokenType.tok_rparen)
            throw new MissingRightParenthesisException();
        if (stackToken == TokenType.tok_rparen && (readIn == TokenType.tok_decimal || readIn == TokenType.tok_lparen))
            throw new MissingOperatorException();
        if (stackToken == TokenType.tok_eof && readIn == TokenType.tok_rparen)
            throw new MissingLeftParenthesisException();
        if (stackToken == TokenType.tok_eof && readIn == TokenType.tok_colon)
            throw new TrinaryOperationException();
        if (stackToken == TokenType.tok_comma && readIn == TokenType.tok_eof)
            throw new MissingOperandException();
        if (stackToken == TokenType.tok_sin || stackToken == TokenType.tok_cos || stackToken == TokenType.tok_min
                || stackToken == TokenType.tok_max)
            throw new FunctionCallException();
        if (isRelation(stackToken) && (readIn == TokenType.tok_eof || readIn == TokenType.tok_colon))
            throw new TypeMismatchedException();
        // For example like min(1, 2) the stack will have at least a terminal named '('
        // when meet ',', so should throw MissingOprand
        // So I consider to throw a Missing LeftParen Exception given that ',' can only
        // present between '(' and ')'
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

    /**
     * The parse function, use OPPTable to determine the action
     * 
     * @return the ast of the expression if success
     * @throws ExpressionException if expression is invaild
     */
    public ast parse() throws ExpressionException {
        lookahead = this.sc.next();
        Terminal top = null;
        while (this.ExprAst == null) {
            top = this.getTopTerminal();
            // printStack();
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
                    ArithExpr exp = null;
                    try {
                        exp = (ArithExpr) ((NonTerminal) this.stack.pop()).genAST();
                    } catch (Exception e) {
                        throw new TypeMismatchedException();
                    }
                    this.ExprAst = new Expr(exp);
                    break;
                case OPPTable.error:
                    errorHandler(top.getType(), lookahead.getType());
                    break;
                default:
                    break;
            }
        }
        return this.ExprAst;
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

    /**
     * Shift the token into stack
     * 
     * @param t, the token to be shifted
     */
    private void shift(Terminal t) {
        this.stack.push(t);
    }

    /**
     * Reduce from the stack
     * 
     * @param topofStack, the topest terminal token
     * @param t,          the token just read in
     * @throws ExpressionException if reduce failed
     */
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

    /**
     * Reduce the boolean operator like and, or
     * 
     * @param type, the type of the operator
     * @throws ExpressionException if error occurs
     */
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

    /**
     * Reduce the unary bool expression
     * 
     * @param type, the operator
     * @throws ExpressionException if error occurs
     */
    private void reduceBooleanUnaryOperator(TokenType type) throws ExpressionException {
        if (this.stack.peek().isTerminal()) {
            throw new MissingOperandException();
        }

        NonTerminal oprand = (NonTerminal) this.stack.pop();

        this.stack.pop();
        this.stack.push(new NonTerminal(type, new BoolExpr(oprand.genAST(), type)));
    }

    /**
     * Reduce the operator like +, -, *, /, ^
     * 
     * @param type the operator
     * @throws ExpressionException if error occurs
     */
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

    /**
     * Reduce the unary operator
     * 
     * @param type only unary minus can be
     * @throws ExpressionException if error occurs
     */
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

    /**
     * Reduce the trinary operator, like true?1:2
     * 
     * @param type the reduce flag
     * @throws ExpressionException if error occurs
     */
    private void reduceTripleOperator(TokenType type) throws ExpressionException {
        if (this.stack.peek().isTerminal()) {
            StackElement tmpTop = this.stack.pop();
            if (tmpTop.getType() == TokenType.tok_colon || tmpTop.getType() == TokenType.tok_question)
                throw new MissingOperandException();
            throw new TrinaryOperationException();
        }
        NonTerminal r2 = (NonTerminal) this.stack.pop();
        if (this.stack.pop().getType() != TokenType.tok_colon)
            throw new TrinaryOperationException();
        if (this.stack.peek().isTerminal())
            throw new MissingOperandException();
        NonTerminal r1 = (NonTerminal) this.stack.pop();
        if (this.stack.pop().getType() != TokenType.tok_question)
            throw new TrinaryOperationException();
        if (this.stack.peek().isTerminal())
            throw new MissingOperandException();
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

    /**
     * Reduce boolean relation operators
     * 
     * @param type reduce flag
     * @throws ExpressionException if error occurs
     */
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

    /**
     * Reduce Param Expression, also notice that function call will be reduced here
     * 
     * @param type reduce flag
     * @throws ExpressionException if error occurs
     */
    private void reduceParam(TokenType type) throws ExpressionException {
        // )
        this.stack.pop();
        Stack<StackElement> st = new Stack<>();
        StackElement top = this.stack.pop();
        // (), empty expression
        if (top.isTerminal() && top.getType() == TokenType.tok_lparen) {
            throw new MissingOperandException();
        }
        // read until meet '('
        boolean getLparen = false;
        while (!this.stack.empty()) {
            st.push(top);
            top = this.stack.pop();
            if (top.getType() == TokenType.tok_lparen) {
                getLparen = true;
                break;
            }
        }

        Stack<StackElement> s = new Stack<>();

        while (!st.empty()) {
            s.push(st.pop());
        }

        if (!getLparen) {
            this.stack.push(top);
            throw new MissingLeftParenthesisException();
        }

        StackElement func = this.stack.peek();
        TokenType stType = func.getType();
        // if (stType == TokenType.tok_sin || stType == TokenType.tok_cos) {
        // this.stack.pop();
        // ast tmp = buildParam(s, stType);
        // ArithExpr unary = null;
        // try {
        // unary = (ArithExpr) tmp;
        // } catch (Exception e) {
        // throw new TypeMismatchedException();
        // }
        // // ast p = buildParam2(st, stType);
        // // try {
        // // unary = (ArithExpr) p;
        // // } catch (Exception e) {
        // // throw new TypeMismatchedException();
        // // }
        // this.stack.push(new NonTerminal(stType, new ArithExpr(new UnaryFunc(stType,
        // unary))));
        // } else if (stType == TokenType.tok_min || stType == TokenType.tok_max) {
        // this.stack.pop();
        // this.stack.push(new NonTerminal(stType, buildParam(s, stType)));
        // } else {
        // ast param = buildParam(s, stType);
        // if (param instanceof BoolExpr) {
        // this.stack.push(new NonTerminal(type, new BoolExpr(param)));
        // } else if (param instanceof ArithExpr) {
        // this.stack.push(new NonTerminal(type, new ArithExpr((ArithExpr) param)));
        // } else
        // throw new ExpressionException();
        // }
        ast node = buildParam(s, stType);

        if (stType == TokenType.tok_sin || stType == TokenType.tok_cos) {
            this.stack.pop();
            ArithExpr exp = null;
            try {
                exp = (ArithExpr) node;
            } catch (Exception e) {
                if (node instanceof ArithExprList || node instanceof VariablFunc)
                    throw new FunctionCallException();
                throw new TypeMismatchedException();
            }
            this.stack.push(new NonTerminal(stType, new ArithExpr(new UnaryFunc(stType, exp))));
        } else if (stType == TokenType.tok_min || stType == TokenType.tok_max) {
            this.stack.pop();
            VariablFunc exp = null;
            try {
                exp = (VariablFunc) node;
            } catch (Exception e) {
                if (node instanceof ArithExpr) {
                    throw new MissingOperandException();
                }
                throw new TypeMismatchedException();
            }
            this.stack.push(new NonTerminal(stType, new ArithExpr(exp)));
        } else {
            if (node instanceof VariablFunc)
                throw new SyntacticException();
            // if (node instanceof BoolExpr)
            // this.stack.push(new NonTerminal(stType, new BoolExpr(node)));
            // else if (node instanceof ArithExpr)
            // this.stack.push(new NonTerminal(stType, new ArithExpr((ArithExpr) node)));
            // else
            this.stack.push(new NonTerminal(stType, node));
        }
    }

    /**
     * Build a '()' expression
     * 
     * @param st       stack contains all elements
     * @param function function call type, if not cos, sin, min, max, then we will
     *                 ignore it
     * @return the ast node
     * @throws ExpressionException if error occurs
     */
    private ast buildParam(Stack<StackElement> st, TokenType function) throws ExpressionException {
        boolean init = true;
        boolean func = false;
        if (function == TokenType.tok_sin || function == TokenType.tok_cos || function == TokenType.tok_min
                || function == TokenType.tok_max)
            func = true;

        // while (!st.empty()) {
        // StackElement t = st.pop();
        // if (t.isTerminal())
        // System.out.printf("%s\n", t.getType());
        // else {
        // System.out.printf("%s\n", ((NonTerminal) t).genAST().getClass());
        // }
        // }

        while (st.size() != 1) {
            StackElement right = st.pop();
            // System.out.printf("%s\n", right.getType());

            if (right.isTerminal())
                throw new MissingOperandException();
            if (init) {
                NonTerminal r = (NonTerminal) right;
                ArithExpr exp = null;

                try {
                    exp = (ArithExpr) r.genAST();
                } catch (Exception e) {
                    throw new TypeMismatchedException();
                }

                if (func) {
                    st.push(new NonTerminal(function, new ArithExprList(exp, function)));
                } else {
                    st.push(new NonTerminal(TokenType.tok_eof, new ArithExprList(exp, TokenType.tok_eof)));
                }

                init = false;
            } else {
                NonTerminal r = (NonTerminal) right;
                StackElement comma = st.pop();

                if (!(comma.isTerminal()) || comma.getType() != TokenType.tok_comma)
                    throw new MissingOperatorException();

                if (st.empty()) {
                    throw new MissingOperandException();
                }

                StackElement left = st.pop();

                if (left.isTerminal())
                    throw new MissingOperandException();

                NonTerminal l = (NonTerminal) left;

                ArithExprList rt = null;
                ArithExpr lt = null;

                try {
                    rt = (ArithExprList) r.genAST();
                    lt = (ArithExpr) l.genAST();
                } catch (Exception e) {
                    throw new TypeMismatchedException();
                }

                if (func)
                    st.push(new NonTerminal(function, new ArithExprList(lt, rt, function)));
                else
                    st.push(new NonTerminal(TokenType.tok_eof, new ArithExprList(lt, rt, TokenType.tok_eof)));

                // StackElement comma = st.pop();
                // if (!(comma.isTerminal()) || ((Terminal) comma).getType() !=
                // TokenType.tok_comma)
                // throw new MissingOperatorException();
                // if (init) {
                // ArithExpr exp = null;
                // try {
                // exp = (ArithExpr) ((NonTerminal) right).genAST();
                // } catch (Exception e) {
                // throw new TypeMismatchedException();
                // }
                // if (func)
                // st.push(new NonTerminal(function, new ArithExprList(exp, function)));
                // else
                // st.push(new NonTerminal(TokenType.tok_eof, new ArithExprList(exp,
                // TokenType.tok_eof)));
                // init = false;
                // }
                // else {
                // StackElement left = st.pop();

                // if (left.isTerminal())
                // throw new MissingOperandException();

                // ArithExprList expList = null;
                // ArithExpr exp = null;

                // try {
                // expList = (ArithExprList) ((NonTerminal) right).genAST();
                // exp = (ArithExpr) ((NonTerminal) left).genAST();
                // } catch (Exception e) {
                // throw new TypeMismatchedException();
                // }

                // if (func)
                // st.push(new NonTerminal(function, new ArithExprList(exp, expList,
                // function)));
                // else
                // st.push(new NonTerminal(function, new ArithExprList(exp, expList,
                // TokenType.tok_eof)));
                // }
            }
        }

        if (st.peek().isTerminal())
            throw new MissingOperandException();
        ast ret = ((NonTerminal) st.pop()).genAST();
        if (ret instanceof ArithExprList) {
            if (func)
                return new VariablFunc(function, ((ArithExprList) ret).getExpr(), ((ArithExprList) ret).getExprList());
            throw new SyntacticException();
        }
        if (ret instanceof BoolExpr) {
            return new BoolExpr((BoolExpr) ret);
        }
        if (ret instanceof ArithExpr) {
            return new ArithExpr((ArithExpr) ret);
        }

        throw new ExpressionException();
    }
}
