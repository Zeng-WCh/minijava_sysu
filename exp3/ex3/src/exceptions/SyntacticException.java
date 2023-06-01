package exceptions;

/**
 * Use to indicate that we met a syntactic error.
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last Update 2023/06/01)
 */
public class SyntacticException extends OberonException {
    /**
     * Used to show more information about the error.
     * 
     * @param message, the message to be shown.
     */
    public SyntacticException(String message) {
        super(message);
    }

    /**
     * Default constructor.
     */
    public SyntacticException() {
        this("Syntactic Exception");
    }
}
