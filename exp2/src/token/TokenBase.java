package token;

/**
 * This class is the base class of all tokens.
 */
public abstract class TokenBase {
    /**
     * The type of the token.
     */
    private final TokenType type;

    public TokenBase(TokenType type) {
        this.type = type;
    }

    public TokenType getType() {
        return this.type;
    }

    public abstract String toString();

    public abstract boolean isOperator();
}
