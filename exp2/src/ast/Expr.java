package ast;

import exceptions.ExpressionException;

public class Expr implements ast {
    private ArithExpr arithexpr = null;

    public Expr(ArithExpr expr) {
        this.arithexpr = expr;
    }
    
    @Override
    public double eval() throws ExpressionException {
        return this.arithexpr.eval();
    }
}
