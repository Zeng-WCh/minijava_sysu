package exceptions;

/**
 * Use to indicate that the length of the integer is too long.(> 12)
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last Update 2023/06/01)
 */
public class IllegalIntegerRangeException extends LexicalException {
    public IllegalIntegerRangeException(String message) {
        super(message);
    }
    
    public IllegalIntegerRangeException() {
        this("Integer Is Out of Range");
    }
}
