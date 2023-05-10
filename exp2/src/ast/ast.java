package ast;

import exceptions.ExpressionException;

/**
 * Use to represent the basic type of AST
 * 
 * Update: Add Print Function to display the ast in a tree-like structure
 * 
 * @author Weichao Zeng
 * @version 1.00 (Last update: 2023/05/09)
 */
public interface ast {
    /**
     * Evaluate the AST, return the result
     * 
     * @return the result of the AST, specially, if the AST is a BoolExpr, return 1
     *         if true, 0 if false
     * @throws ExpressionException if the expression is invalid
     */
    public double eval() throws ExpressionException;

    /**
     * Print the AST in a tree-like structure
     * 
     * @param depth, current recursion depth
     */
    public void print(int depth);
}
