package exceptions;

/**
 * the base exception class for Oberon, all other exceptions should extend this class.
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last Update 2023/06/01)
 */
public class OberonException extends Exception {
    public OberonException(String message) {
        super(message);
    }

    public OberonException() {
        this("Oberon Exception");
    }
}
