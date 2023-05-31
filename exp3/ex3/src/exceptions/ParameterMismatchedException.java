package exceptions;

public class ParameterMismatchedException extends SemanticException {
    public ParameterMismatchedException(String message) {
        super(message);
    }

    public ParameterMismatchedException() {
        super("Parameter mismatched");
    }
}
