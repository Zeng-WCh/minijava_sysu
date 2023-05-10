package parser;

import token.TokenType;

/**
 * Use this class to represent Terminal and NonTerminal in parsing stack.
 * 
 * @author Weichao Zeng
 * @version 1.00 (Last update: 2023/05/09)
 */
public abstract class StackElement {
    /**
     * the token type, which is used if this is a terminal
     */
    private TokenType type;
    /**
     * the token value, which is used if this is a terminal
     */
    private String value;

    /**
     * Construct a StackElement
     * 
     * @param type  token type
     * @param value the value of the token
     */
    public StackElement(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    /**
     * get the token type
     * 
     * @return the token type
     */
    public TokenType getType() {
        return this.type;
    }

    /**
     * get the token value
     * 
     * @return what the token represents
     */
    public String getValue() {
        return this.value;
    }

    /**
     * judge whether this is a terminal
     * 
     * @return true if this is a terminal, false otherwise
     */
    public abstract boolean isTerminal();
}
