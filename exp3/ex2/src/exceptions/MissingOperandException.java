package exceptions;

/**
 * Use to indicate that we met a expression without an operand.
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last Update 2023/06/01)
 */
public class MissingOperandException extends SyntacticException {
    public MissingOperandException(String message) {
        super(message);
    }
    
    public MissingOperandException() {
        this("Missing operand");
    }
}
