package ast;

import exceptions.ExpressionException;
import token.TokenType;

/**
 * UnaryFunc AST Node
 * 
 * @author Weichao Zeng
 * @version 1.00 (Last update: 2023/05/04)
 */
public class UnaryFunc implements ast {
    private TokenType funcType;
    private ArithExpr expr;

    public UnaryFunc(TokenType funcType, ArithExpr expr) {
        this.funcType = funcType;
        this.expr = expr;
    }

    @Override
    public double eval() throws ExpressionException {
        if (this.funcType == TokenType.tok_sin) {
            return Math.sin(this.expr.eval());
        }
        else if (this.funcType == TokenType.tok_cos) {
            return Math.cos(this.expr.eval());
        }

        return 0;
    }
}
