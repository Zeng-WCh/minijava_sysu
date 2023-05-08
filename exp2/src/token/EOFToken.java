package token;

public class EOFToken extends Token {
    public EOFToken() {
        super(TokenType.tok_eof);
    }

    @Override
    public String toString() {
        return "EOF";
    }

    @Override
    public boolean isOperator() {
        return false;
    }
}
