package exceptions;

/**
 * Use to indicate that we met a semantic error.
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last Update 2023/06/01)
 */
public class SemanticException extends Exception {
    /**
     * Used to show more information about the error.
     * 
     * @param message, the message to be shown.
     */
    public SemanticException(String message) {
        super(message);
    }

    /**
     * Default constructor.
     */
    public SemanticException() {
        super("Semantic error");
    }
}
