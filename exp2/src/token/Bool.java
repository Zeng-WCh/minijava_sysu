package token;

public class Bool extends Token {
    private String value;

    public Bool(TokenType type, String value) {
        super(type);
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean isOperator() {
        return false;
    }
}
