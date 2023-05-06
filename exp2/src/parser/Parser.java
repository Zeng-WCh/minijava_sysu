package parser;

import ast.ast;
import exceptions.EmptyExpressionException;
import exceptions.ExpressionException;
import scanner.Buffer;
import scanner.Scanner;
import token.Token;
import token.TokenType;

import java.util.Stack;
import java.util.HashMap;

public class Parser {
    private final Scanner sc;
    private Token lookahead;
    private final Stack<StackElement> stack;
    /**
     * Op used to represent the operator level, higher one must be parsed first
     */
    private final HashMap<TokenType, Integer> Op;

    /**
     * Init the Operator Table and return
     */
    private static HashMap<TokenType, Integer> InitTable() {
        HashMap<TokenType, Integer> ret = new HashMap<>();
        
        // Level 1
        ret.put(TokenType.tok_lparen, 11);
        ret.put(TokenType.tok_rparen, 11);

        // Level 2
        ret.put(TokenType.tok_sin, 10);
        ret.put(TokenType.tok_cos, 10);
        ret.put(TokenType.tok_max, 10);
        ret.put(TokenType.tok_min, 10);

        // Level 3
        ret.put(TokenType.tok_unary_minus, 9);

        // Level 4
        ret.put(TokenType.tok_caret, 8);

        // Level 5
        ret.put(TokenType.tok_star, 7);
        ret.put(TokenType.tok_slash, 7);

        // Level 6
        ret.put(TokenType.tok_add, 6);
        ret.put(TokenType.tok_minus, 6);

        // Level 7
        ret.put(TokenType.tok_equal, 5);
        ret.put(TokenType.tok_not_equal, 5);
        ret.put(TokenType.tok_less, 5);
        ret.put(TokenType.tok_less_equal, 5);
        ret.put(TokenType.tok_greater, 5);
        ret.put(TokenType.tok_greater_equal, 5);

        // Level 8
        ret.put(TokenType.tok_not, 4);

        // Level 9
        ret.put(TokenType.tok_and, 3);

        // Level 10
        ret.put(TokenType.tok_or, 2);

        // level 11
        ret.put(TokenType.tok_question, 1);
        ret.put(TokenType.tok_colon, 1);

        // level 12
        ret.put(null, 0);

        return ret;
    }

    /**
     * Constructor of Parser
     * 
     * @param expression, the expression to be parsed
     * @throws EmptyExpressionException if the expression is empty
     * @throws ExpressionException if the expression is invalid when parsing
     */
    public Parser(String expression) throws ExpressionException {
        if (expression == null || expression.isEmpty() || expression.isBlank())
            throw new EmptyExpressionException();
        this.sc = new Scanner(new Buffer(expression));
        this.stack = new Stack<>();
        this.Op = InitTable();
    }

    public ast parse() throws ExpressionException {
        lookahead = this.sc.next();

        do {
            Terminal t = new Terminal(lookahead.getType(), lookahead.toString());
            StackElement top = this.getTopElement();
            if (top == null)
                this.stack.push(t);
            else {

            }
        } while(true);


        // return null;
    }

    private StackElement getTopElement() {
        if (this.stack.empty()) {
            return null;
        }
        int i = this.stack.size() - 1;
        while (i >= 0) {
            StackElement st = this.stack.get(i);
            if (st.isTerminal()) {
                return st;
            }
        }
        return null;
    }
}
