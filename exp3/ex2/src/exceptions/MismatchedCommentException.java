package exceptions;

public class MismatchedCommentException extends LexicalException {
    public MismatchedCommentException(String message) {
        super(message);
    }
    
    public MismatchedCommentException() {
        this("Mismatched Comment Detected");
    }
}
