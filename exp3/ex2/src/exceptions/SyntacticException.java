package exceptions;

/**
 * Use to indicate that we met a syntactic error.
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last Update 2023/06/01)
 */
public class SyntacticException extends OberonException {
    public SyntacticException(String message) {
        super(message);
    }

    public SyntacticException() {
        this("Syntactic Exception");
    }
}
