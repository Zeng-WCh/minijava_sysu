package exceptions;

/**
 * Used when meet an unknown character.
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last Update 2023/06/01)
 */
public class IllegalSymbolException extends LexicalException {
    public IllegalSymbolException(String message) {
        super(message);
    }

    public IllegalSymbolException() {
        this("Unknown Character Detected");
    }
}
