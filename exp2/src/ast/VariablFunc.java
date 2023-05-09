package ast;

import exceptions.ExpressionException;
import token.TokenType;

/**
 * VariableFunction AST Node
 * 
 * @author Weichao Zeng
 * @version 1.00 (Last update: 2023/05/09)
 */
public class VariablFunc implements ast {
    /**
     * Variable ::= min ( ArithExpr , ArithExprList )
     * Variable ::= max ( ArithExpr , ArithExprList )
     * 
     * type must be min/max
     */
    private TokenType type;
    /**
     * expr is the first parameter
     */
    private ArithExpr expr;
    /**
     * arithExprList is the rest parameter
     */
    private ArithExprList arithExprList;

    /**
     * Variable Function Call Constructor
     * 
     * @param type, function type
     * @param expr, the first parameter
     * @param arithExprList, the rest parameter
     */
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

    @Override
    public void print(int depth) {
        for (int i = 0; i < depth; ++i) {
            System.out.print(' ');
        }
        String func = (this.type == TokenType.tok_min) ? "min" : "max";
        System.out.printf("`-- VariablFunc: %s ( ArithExpr , ArithExprList )\n", func);
        this.expr.print(depth + 1);
        this.arithExprList.print(depth + 1);
    }
}
