package ast;

import exceptions.ExpressionException;
import exceptions.DividedByZeroException;
import token.TokenType;

/**
 * ArithExpr AST Node
 * 
 * @author Weichao Zeng
 * @version 3.00 (Last update: 2023/05/08)
 */
public class ArithExpr implements ast {
    private double num = 0;
    private ArithExpr left = null, right = null;
    private UnaryFunc unary = null;
    private VariablFunc var = null;
    private BoolExpr condition = null;
    private TokenType op = null, op1 = null;
    private int type = -1;

    public ArithExpr(double num) {
        this.num = num;
        this.type = 0;
    }

    public ArithExpr(ArithExpr unary, TokenType op) {
        this.left = unary;
        this.op = op;
        this.type = 1;
    }

    public ArithExpr(ArithExpr left, TokenType op, ArithExpr right) {
        this.left = left;
        this.op = op;
        this.right = right;
        this.type = 2;
    }

    public ArithExpr(BoolExpr condition, TokenType op, ArithExpr left, TokenType op1, ArithExpr right) {
        this.condition = condition;
        this.op = op;
        this.left = left;
        this.op1 = op1;
        this.right = right;
        this.type = 3;
    }

    public ArithExpr(UnaryFunc FuncCall) {
        this.unary = FuncCall;
        this.type = 4;
    }

    public ArithExpr(VariablFunc FuncCall) {
        this.var = FuncCall;
        this.type = 5;
    }

    @Override
    public double eval() throws ExpressionException {
        if (this.type == 0) {
            return this.num;
        }
        else if (this.type == 1) {
            if (this.op == TokenType.tok_unary_minus) {
                return -1 * this.left.eval();
            }
            return this.left.eval();
        }
        else if (this.type == 2) {
            if (this.op == TokenType.tok_plus) {
                return this.left.eval() + this.right.eval();
            }
            else if (this.op == TokenType.tok_minus) {
                return this.left.eval() - this.right.eval();
            }
            else if (this.op == TokenType.tok_star) {
                return this.left.eval() * this.right.eval();
            }
            else if (this.op == TokenType.tok_slash) {
                double right = this.right.eval();
                if (right == 0) {
                    throw new DividedByZeroException();
                }
                return this.left.eval() / right;
            }
            else if (this.op == TokenType.tok_caret) {
                return Math.pow(this.left.eval(), this.right.eval());
            }
            else {
                return 0;
            }
        }
        else if (this.type == 3) {
            // When Used in Boolean Expression, eval return 0 if false, 1 if true
            boolean flag = (this.condition.eval() == 1);
            assert(this.op1 == TokenType.tok_colon);

            if (flag) {
                return this.left.eval();
            }
            else {
                return this.right.eval();
            }
        }
        else if (this.type == 4) {
            return this.unary.eval();
        }
        else if (this.type == 5) {
            return this.var.eval();
        }
        return 0;
    }
}
