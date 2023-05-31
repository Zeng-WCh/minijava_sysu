package exceptions;

public class IllegalIdentifierLengthException extends LexicalException {
    public IllegalIdentifierLengthException(String message) {
        super(message);
    }
    
    public IllegalIdentifierLengthException() {
        this("Identifier Is Too Long");
    }
}
