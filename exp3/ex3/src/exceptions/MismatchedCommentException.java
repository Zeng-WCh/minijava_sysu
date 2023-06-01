package exceptions;

/**
 * Use to indicate that the comment is unmatched.
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last Update 2023/06/01)
 */
public class MismatchedCommentException extends LexicalException {
    /**
     * Used to show more information about the error.
     * 
     * @param message, the message to be shown.
     */
    public MismatchedCommentException(String message) {
        super(message);
    }
    
    /**
     * Default constructor.
     */
    public MismatchedCommentException() {
        this("Mismatched Comment Detected");
    }
}
