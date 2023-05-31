package exceptions;

public class TypeMismatchedException extends SemanticException {
    public TypeMismatchedException(String message) {
        super(message);
    }

    public TypeMismatchedException() {
        super("Type mismatched");
    }
}