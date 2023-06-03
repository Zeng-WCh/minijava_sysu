package exceptions;

/**
 * the base exception class for Oberon, all other exceptions should extend this class.
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last Update 2023/06/01)
 */
public class OberonException extends Exception {
    /**
     * Used to show more information about the error.
     * 
     * @param message, the message to be shown.
     */
    public OberonException(String message) {
        super(message);
    }

    /**
     * Default constructor.
     */
    public OberonException() {
        this("Oberon Exception");
    }
}
