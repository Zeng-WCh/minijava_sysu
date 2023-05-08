package ast;

import exceptions.ExpressionException;
import token.TokenType;

/**
 * VariableFunction AST Node
 * 
 * @author Weichao Zeng
 * @version 1.00 (Last update: 2023/05/04)
 */
public class VariablFunc implements ast {
    private TokenType type;
    private ArithExpr expr;
    private ArithExprList arithExprList;

    public VariablFunc(TokenType type, ArithExpr expr, ArithExprList arithExprList) {
        this.type = type;
        this.expr = expr;
        this.arithExprList = arithExprList;
        this.arithExprList.setType(type);
    }

    @Override
    public double eval() throws ExpressionException {
        if (this.type == TokenType.tok_min) {
            double now = this.expr.eval();
            double min = this.arithExprList.eval();
            return now < min ? now : min;
        }
        else if (this.type == TokenType.tok_max) {
            double now = this.expr.eval();
            double max = this.arithExprList.eval();
            return now > max ? now : max;
        }
        return 0;
    }
    
}
