package ast;

import exceptions.DividedByZeroException;
import token.TokenType;

public class UnaryFunc implements ast {
    private TokenType funcType;
    private ast expr;

    public UnaryFunc(TokenType funcType, ast expr) {
        this.funcType = funcType;
        this.expr = expr;
    }

    @Override
    public double eval() throws DividedByZeroException {
        if (this.funcType == TokenType.tok_sin) {
            return Math.sin(this.expr.eval());
        }
        else if (this.funcType == TokenType.tok_cos) {
            return Math.cos(this.expr.eval());
        }

        return 0;
    }
}
