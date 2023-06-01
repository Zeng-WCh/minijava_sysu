package exceptions;

/**
 * Use to indicate that the Octal format is illegal.
 * like 09
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last Update 2023/06/01)
 */
public class IllegalOctalException extends LexicalException {
    public IllegalOctalException(String message) {
        super(message);
    }

    public IllegalOctalException() {
        this("Illegal Octal Detected");
    }
}
