package exceptions;

/**
 * Use to indicate that a lexical error has occurred.
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last Update 2023/06/01)
 */
public class LexicalException extends OberonException {
    /**
     * Used to show more information about the error.
     * 
     * @param message, the message to be shown.
     */
    public LexicalException(String message) {
        super(message);
    }

    /**
     * Default constructor.
     */
    public LexicalException() {
        this("Lexical Exception");
    }
}
