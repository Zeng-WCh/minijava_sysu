package ast;

import exceptions.ExpressionException;

/**
 * Expr AST Node
 * 
 * @author Weichao Zeng
 * @version 1.00 (Last update: 2023/05/09)
 */
public class Expr implements ast {
    /**
     * Expr ::= ArithExpr
     */
    private ArithExpr arithexpr = null;

    public Expr(ArithExpr expr) {
        this.arithexpr = expr;
    }
    
    @Override
    public double eval() throws ExpressionException {
        return this.arithexpr.eval();
    }

    @Override
    public void print(int depth) {
        System.out.println("|-- Expr: ArithExpr");
        this.arithexpr.print(depth + 1);
    }
}
