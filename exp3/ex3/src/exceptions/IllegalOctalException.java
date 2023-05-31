package exceptions;

public class IllegalOctalException extends LexicalException {
    public IllegalOctalException(String message) {
        super(message);
    }

    public IllegalOctalException() {
        this("Illegal Octal Detected");
    }
}
