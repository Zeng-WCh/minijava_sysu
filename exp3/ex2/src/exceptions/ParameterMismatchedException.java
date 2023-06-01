package exceptions;

/**
 * Use to indicate that when a procedure is called, but the number of parameters is not matched.
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last Update 2023/06/01)
 */
public class ParameterMismatchedException extends SemanticException {
    public ParameterMismatchedException(String message) {
        super(message);
    }

    public ParameterMismatchedException() {
        super("Parameter mismatched");
    }
}
