package ast;

import exceptions.ExpressionException;
import token.TokenType;

/**
 * ArithExprList AST Node
 * 
 * @author Weichao Zeng
 * @version 2.00 (Last update: 2023/05/05)
 */
public class ArithExprList implements ast {
    private ArithExpr expr;
    private ArithExprList list;
    private TokenType type;

    public ArithExprList(ArithExpr expr, TokenType type) {
        this.expr = expr;
        this.type = type;
        this.list = null;
    }

    public ArithExprList(ArithExpr expr, ArithExprList list, TokenType type) {
        this.expr = expr;
        this.type = type;
        this.list = list;
    }

    public void setType(TokenType type) {
        this.type = type;
        if (this.list != null)
            this.list.setType(type);
    }

    @Override
    public double eval() throws ExpressionException {
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
