package token;

/**
 * This class is the base class of all tokens.
 * @author Weichao Zeng
 * @version 1.00 (Last Updated: 2023/4/28)
 */
public abstract class Token {
    /**
     * The type of the token.
     */
    private final TokenType type;

    public Token(TokenType type) {
        this.type = type;
    }

    public TokenType getType() {
        return this.type;
    }

    public abstract String toString();

    public abstract boolean isOperator();
}
