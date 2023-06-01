package exceptions;

/**
 * Use to indicate that a lexical error has occurred.
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last Update 2023/06/01)
 */
public class LexicalException extends OberonException {
    public LexicalException(String message) {
        super(message);
    }

    public LexicalException() {
        this("Lexical Exception");
    }
}
