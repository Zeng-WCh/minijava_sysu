package exceptions;

/**
 * Use to indicate that when a procedure is called, but the number of parameters is not matched.
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last Update 2023/06/01)
 */
public class ParameterMismatchedException extends SemanticException {
    /**
     * Used to show more information about the error.
     * 
     * @param message, the message to be shown.
     */
    public ParameterMismatchedException(String message) {
        super(message);
    }

    /**
     * Default constructor.
     */
    public ParameterMismatchedException() {
        super("Parameter mismatched");
    }
}
