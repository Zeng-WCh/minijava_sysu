package exceptions;

/**
 * Use to indicate that we met a expression without right parenthesis, but with left parenthesis.
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last Update 2023/06/01)
 */
public class MissingRightParenthesisException extends SyntacticException {
    /**
     * Used to show more information about the error.
     * 
     * @param message, the message to be shown.
     */
    public MissingRightParenthesisException(String message) {
        super(message);
    }
    
    /**
     * Default constructor.
     */
    public MissingRightParenthesisException() {
        this("Missing right parenthesis");
    }
}