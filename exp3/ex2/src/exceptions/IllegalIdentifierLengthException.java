package exceptions;


/**
 * Use to indicate that the length of the identifier is too long.(> 24)
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last Update 2023/06/01)
 */
public class IllegalIdentifierLengthException extends LexicalException {
    /**
     * Used to show more information about the error.
     * 
     * @param message, the message to be shown.
     */
    public IllegalIdentifierLengthException(String message) {
        super(message);
    }
    
    /**
     * Default constructor.
     */
    public IllegalIdentifierLengthException() {
        this("Identifier Is Too Long");
    }
}
