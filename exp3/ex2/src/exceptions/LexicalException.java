package exceptions;

public class LexicalException extends OberonException {
    public LexicalException(String message) {
        super(message);
    }

    public LexicalException() {
        this("Lexical Exception");
    }
}
