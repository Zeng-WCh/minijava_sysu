package parser;

import token.TokenType;

public abstract class StackElement {
    private TokenType type;
    private String value;

    public StackElement(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }
    
    public abstract boolean isTerminal();
}
