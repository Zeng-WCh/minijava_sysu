package parser;

import token.TokenType;
import ast.*;

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
