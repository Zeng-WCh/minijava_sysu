package parser;

import token.TokenType;

/**
 * @author Weichao Zeng
 * @version 1.00 (Last update: 2023/04/28)
 */
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
