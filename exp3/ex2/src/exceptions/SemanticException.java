package exceptions;

/**
 * Use to indicate that we met a semantic error.
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last Update 2023/06/01)
 */
public class SemanticException extends Exception {
    public SemanticException(String message) {
        super(message);
    }

    public SemanticException() {
        super("Semantic error");
    }
}
