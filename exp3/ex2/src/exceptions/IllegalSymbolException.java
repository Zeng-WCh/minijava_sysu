package exceptions;

public class IllegalSymbolException extends LexicalException {
    public IllegalSymbolException(String message) {
        super(message);
    }

    public IllegalSymbolException() {
        this("Unknown Character Detected");
    }
}
