package exceptions;

/**
 * Used when meet an unknown character.
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last Update 2023/06/01)
 */
public class IllegalSymbolException extends LexicalException {
    /**
     * Used to show more information about the error.
     * 
     * @param message, the message to be shown.
     */
    public IllegalSymbolException(String message) {
        super(message);
    }

    /**
     * Default constructor.
     */
    public IllegalSymbolException() {
        this("Unknown Character Detected");
    }
}
