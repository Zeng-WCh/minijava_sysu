package exceptions;

/**
 * Use to indicate that we met a expression without left parenthesis, but with right parenthesis.
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last Update 2023/06/01)
 */
public class MissingLeftParenthesisException extends SyntacticException {
    public MissingLeftParenthesisException(String message) {
        super(message);
    }
    
    public MissingLeftParenthesisException() {
        this("Missing left parenthesis");
    }
}