package ast;
import exceptions.ExpressionException;
/**
 * Use to represent the basic type of AST
 * 
 * @author Weichao Zeng
 * @version 1.00 (Last update: 2023/05/04)
 */
public interface ast {
    public double eval() throws ExpressionException;
}
