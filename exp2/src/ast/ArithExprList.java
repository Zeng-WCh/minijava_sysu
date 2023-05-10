package ast;

import exceptions.ExpressionException;
import token.TokenType;

/**
 * ArithExprList AST Node
 * 
 * @author Weichao Zeng
 * @version 1.00 (Last update: 2023/05/09)
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
        } else if (this.type == TokenType.tok_max) {
            return now > next ? now : next;
        }
        return 0;
    }

    @Override
    public void print(int depth) {
        for (int i = 0; i < depth; ++i)
            System.out.print(' ');
        if (this.list == null) {
            System.out.println("`-- ArithExprList: ArithExpr");
            this.expr.print(depth + 1);
        } else {
            System.out.println("`-- ArithExprList: ArithExpr , ArithExprList");
            this.expr.print(depth + 1);
            this.list.print(depth + 1);
        }
    }

    public ArithExpr getExpr() {
        return this.expr;
    }

    public ArithExprList getExprList() {
        return this.list;
    }
}
