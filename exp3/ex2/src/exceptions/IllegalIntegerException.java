package exceptions;


/**
 * Use to indicate that the Integer format is illegal.
 * like 0b
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last Update 2023/06/01)
 */
public class IllegalIntegerException extends LexicalException {
    /**
     * Used to show more information about the error.
     * 
     * @param message, the message to be shown.
     */
    public IllegalIntegerException(String message) {
        super(message);
    }
    
    /**
     * Default constructor.
     */
    public IllegalIntegerException() {
        this("Illegal Integer Detected");
    }
}
