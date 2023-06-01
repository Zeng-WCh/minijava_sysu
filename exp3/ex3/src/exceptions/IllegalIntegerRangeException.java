package exceptions;

/**
 * Use to indicate that the length of the integer is too long.(> 12)
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last Update 2023/06/01)
 */
public class IllegalIntegerRangeException extends LexicalException {
    /**
     * Used to show more information about the error.
     * 
     * @param message, the message to be shown.
     */
    public IllegalIntegerRangeException(String message) {
        super(message);
    }
    
    /**
     * Default constructor.
     */
    public IllegalIntegerRangeException() {
        this("Integer Is Out of Range");
    }
}
