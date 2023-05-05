package ast;
import exceptions.ExpressionException;
/**
 * @author Weichao Zeng
 * @version 1.00 (Last update: 2023/04/28)
 * Use to represent the basic type of AST
 */
public interface ast {
    public double eval() throws ExpressionException;
}
