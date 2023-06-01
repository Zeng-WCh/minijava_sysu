package exceptions;

/**
 * Use to indicate that we met a expression without left parenthesis, but with right parenthesis.
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last Update 2023/06/01)
 */
public class MissingLeftParenthesisException extends SyntacticException {
    /**
     * Used to show more information about the error.
     * 
     * @param message, the message to be shown.
     */
    public MissingLeftParenthesisException(String message) {
        super(message);
    }
    
    /**
     * Default constructor.
     */
    public MissingLeftParenthesisException() {
        this("Missing left parenthesis");
    }
}