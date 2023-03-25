/**
 * Interface for the AST
 * all the AST node should implement this interface
 */
public interface ast {
    /**
     * eval the AST
     * @return the result of the AST
     */
    public double eval();

    /**
     * @return the postfix expression of the AST
     */
    public String postFix();
}
