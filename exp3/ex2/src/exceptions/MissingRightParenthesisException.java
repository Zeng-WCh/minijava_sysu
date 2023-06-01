package exceptions;

/**
 * Use to indicate that we met a expression without right parenthesis, but with left parenthesis.
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last Update 2023/06/01)
 */
public class MissingRightParenthesisException extends SyntacticException {
    public MissingRightParenthesisException(String message) {
        super(message);
    }
    
    public MissingRightParenthesisException() {
        this("Missing right parenthesis");
    }
}