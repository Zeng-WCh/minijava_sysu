package exceptions;

public class IllegalIntegerRangeException extends LexicalException {
    public IllegalIntegerRangeException(String message) {
        super(message);
    }
    
    public IllegalIntegerRangeException() {
        this("Integer Is Out of Range");
    }
}
