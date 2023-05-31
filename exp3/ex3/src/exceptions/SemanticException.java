package exceptions;

public class SemanticException extends Exception {
    public SemanticException(String message) {
        super(message);
    }

    public SemanticException() {
        super("Semantic error");
    }
}
