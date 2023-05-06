package token;

public class Operator extends Token {
    private String value;

    public Operator(TokenType type, String value) {
        super(type);
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean isOperator() {
        return true;
    }
}
