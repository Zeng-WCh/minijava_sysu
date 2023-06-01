package exceptions;

/**
 * Use to indicate that we met a expression without an operator.
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last Update 2023/06/01)
 */
public class MissingOperatorException extends SyntacticException {
    /**
     * Used to show more information about the error.
     * 
     * @param message, the message to be shown.
     */
    public MissingOperatorException(String message) {
        super(message);
    }
    
    /**
     * Default constructor.
     */
    public MissingOperatorException() {
        this("Missing operator");
    }
}
