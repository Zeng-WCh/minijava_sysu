package exceptions;

/**
 * Use to indicate that the comment is unmatched.
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last Update 2023/06/01)
 */
public class MismatchedCommentException extends LexicalException {
    public MismatchedCommentException(String message) {
        super(message);
    }
    
    public MismatchedCommentException() {
        this("Mismatched Comment Detected");
    }
}
