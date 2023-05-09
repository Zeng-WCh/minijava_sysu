package token;

/**
 * This class is the base class of all tokens.
 * @author Weichao Zeng
 * @version 1.00 (Last Updated: 2023/05/09)
 */
public abstract class Token {
    /**
     * The type of the token.
     */
    private final TokenType type;

    /**
     * Construct a token.
     * 
     * @param type The type of the token.
     */
    public Token(TokenType type) {
        this.type = type;
    }

    /**
     * Get the type of the token.
     * 
     * @return The type of the token.
     */
    public TokenType getType() {
        return this.type;
    }

    /**
     * Return the token value as a string.
     * 
     * @return The token value as a string.
     */
    public abstract String toString();

    /**
     * Return whether the token is an operator.
     * 
     * @return true if the token is an operator, else false.
     */
    public abstract boolean isOperator();
}
