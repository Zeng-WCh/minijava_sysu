package ast;

import exceptions.DividedByZeroException;
import token.TokenType;

public class ArithExprList implements ast {
    private ArithExpr expr;
    private ArithExprList list;
    private TokenType type;

    public ArithExprList(ArithExpr expr) {
        this.expr = expr;
        this.list = null;
        this.type = null;
    }

    public ArithExprList(ArithExpr expr, ArithExprList list) {
        this.expr = expr;
        this.list = null;
        this.type = null;
    }

    public void setType(TokenType type) {
        this.type = type;
        if (this.list != null)
            this.list.setType(type);
    }

    @Override
    public double eval() throws DividedByZeroException {
        double now = this.expr.eval();
        if (this.list == null) {
            return now;
        }
        double next = this.list.eval();
        if (this.type == TokenType.tok_min) {
            return now < next ? now : next;
        }
        else if (this.type == TokenType.tok_max) {
            return now > next ? now : next;
        }
        return 0;
    }
}
