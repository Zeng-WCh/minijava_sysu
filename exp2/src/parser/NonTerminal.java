package parser;

import token.TokenType;
import ast.*;

/**
 * Use this class to represent NonTerminal in parsing stack.
 * 
 * update: add new method genAST() to generate AST, and using ast as IR.
 * 
 * @author Weichao Zeng
 * @version 2.00 (Last update: 2023/05/04)
 */
public class NonTerminal extends StackElement {
    private ast ast = null;

    public NonTerminal(TokenType type, ast value) {
        super(type, "");
        this.ast = value;
    }

    @Override
    public boolean isTerminal() {
        return false;
    }

    public ast genAST() {
        return this.ast;
    }
}
