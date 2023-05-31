package exceptions;

public class MissingOperatorException extends SyntacticException {
    public MissingOperatorException(String message) {
        super(message);
    }
    
    public MissingOperatorException() {
        this("Missing operator");
    }
}
