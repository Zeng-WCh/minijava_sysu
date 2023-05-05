package token;

public abstract class TokenBase {
    private final TokenType type;

    public TokenBase(TokenType type) {
        this.type = type;
    }

    public TokenType getType() {
        return this.type;
    }

    public abstract String toString();
}
