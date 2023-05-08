package parser;

import token.TokenType;
import ast.ast;

public class NonTerminal extends StackElement {
    private ast ast;

    public NonTerminal(TokenType type, String value) {
        super(type, value);
    }

    @Override
    public boolean isTerminal() {
        return false;
    }

    public ast genAST() {
        return this.ast;
    }
}
