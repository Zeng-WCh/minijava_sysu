package ast;

import exceptions.ExpressionException;
import token.TokenType;

/**
 * UnaryFunc AST Node
 * 
 * @author Weichao Zeng
 * @version 1.00 (Last update: 2023/05/09)
 */
public class UnaryFunc implements ast {
    /**
     * To decide which function to use
     */
    private TokenType funcType;
    /**
     * The expression to be calculated before function call
     */
    private ArithExpr expr;

    /**
     * Unary Function Call Constructor
     * 
     * @param funcType, the function type
     * @param expr, the expression to be calculated before function call
     */
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

    @Override
    public void print(int depth) {
        for (int i = 0; i < depth; ++i) {
            System.out.print(' ');
        }
        String func = (this.funcType == TokenType.tok_sin) ? "sin" : "cos";
        System.out.printf("`-- UnaryFunc: %s ( ArithExpr )\n", func);
        this.expr.print(depth + 1);
    }
}
