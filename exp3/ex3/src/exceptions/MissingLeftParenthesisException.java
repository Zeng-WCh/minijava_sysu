package exceptions;

public class MissingLeftParenthesisException extends SyntacticException {
    public MissingLeftParenthesisException(String message) {
        super(message);
    }
    
    public MissingLeftParenthesisException() {
        this("Missing left parenthesis");
    }
}