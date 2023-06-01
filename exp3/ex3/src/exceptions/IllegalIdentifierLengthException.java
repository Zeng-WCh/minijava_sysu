package exceptions;


/**
 * Use to indicate that the length of the identifier is too long.(> 24)
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last Update 2023/06/01)
 */
public class IllegalIdentifierLengthException extends LexicalException {
    public IllegalIdentifierLengthException(String message) {
        super(message);
    }
    
    public IllegalIdentifierLengthException() {
        this("Identifier Is Too Long");
    }
}
