package exceptions;

/**
 * Use to indicate that the type of two operands are not matched, or in procedure call, the actually parameter type is not matched with the formal parameter type.
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last Update 2023/06/01)
 */
public class TypeMismatchedException extends SemanticException {
    public TypeMismatchedException(String message) {
        super(message);
    }

    public TypeMismatchedException() {
        super("Type mismatched");
    }
}