package parser;

import ast.UnaryOperator;
import ast.ast;
import exceptions.EmptyExpressionException;
import exceptions.ExpressionException;
import scanner.Buffer;
import scanner.Scanner;
import token.TokenBase;

import java.util.Stack;

public class Parser {
    private final Scanner sc;
    private TokenBase lookahead;
    private final Stack<TokenBase> stack;
    private final Integer[][] table;

    private void initTable() {

    }

    public Parser(String expression) throws ExpressionException {
        if (expression == null || expression.isEmpty() || expression.isBlank())
            throw new EmptyExpressionException();
        this.sc = new Scanner(new Buffer(expression));
        this.stack = new Stack<>();
        this.table = new Integer[14][14];
    }

    public ast parse() throws ExpressionException {
        lookahead = this.sc.next();

        while (true) {
            TokenBase top = this.stack.peek();
        }

        return new UnaryOperator();
    }
}
