package ast;

import exceptions.ExpressionException;
import exceptions.TypeMismatchedException;
import token.TokenType;

/**
 * BoolExpr AST Node
 * 
 * @author Weichao Zeng
 * @version 1.00 (Last update: 2023/05/04)
 */
public class BoolExpr implements ast {
    private boolean value = false;
    private ast left = null, right = null;
    private TokenType op = null;
    private int type = -1;

    public BoolExpr(boolean value) {
        this.value = value;
        this.type = 0;
    }

    public BoolExpr(ast BoolExpr) {
        this.left = BoolExpr;
        this.type = 1;
    }

    public BoolExpr(ast left, TokenType op, ast right) {
        this.left = left;
        this.op = op;
        this.right = right;
        this.type = 2;
    }

    public BoolExpr(ast left, TokenType op) {
        this.left = left;
        this.op = op;
        this.type = 3;
    }

    private void checkType() throws TypeMismatchedException {
        if (this.op == TokenType.tok_greater || this.op == TokenType.tok_less || this.op == TokenType.tok_greater_equal || this.op == TokenType.tok_less_equal || this.op == TokenType.tok_not_equal) {
            if (this.left instanceof ArithExpr && this.right instanceof ArithExpr) {
                return;
            }
            throw new TypeMismatchedException();
        }
        if (this.op == TokenType.tok_not || this.op == TokenType.tok_or || this.op == TokenType.tok_and) {
            if (this.left instanceof BoolExpr && this.right instanceof BoolExpr) {
                return;
            }
            throw new TypeMismatchedException();
        }
    }

    @Override
    public double eval() throws ExpressionException {
        checkType();
        if (this.type == 0) {
            return this.value ? 1 : 0;
        }
        else if (this.type == 1) {
            return this.left.eval();
        }
        else if (this.type == 2) {
            double left = this.left.eval();
            double right = this.right.eval();

            if (this.op == TokenType.tok_greater) {

                return left > right ? 1 : 0;
            }
            else if (this.op == TokenType.tok_greater_equal) {
                return left >= right ? 1 : 0;
            }
            else if (this.op == TokenType.tok_less) {
                return left < right ? 1 : 0;
            }
            else if (this.op == TokenType.tok_less_equal) {
                return left <= right ? 1 : 0;
            }
            else if (this.op == TokenType.tok_equal) {
                return left == right ? 1 : 0;
            }
            else if (this.op == TokenType.tok_not_equal) {
                return left != right ? 1 : 0;
            }
            else if (this.op == TokenType.tok_and) {
                boolean l = left == 1 ? true : false;
                boolean r = right == 1 ? true : false;
                return l && r ? 1 : 0;
            }
            else if (this.op == TokenType.tok_or) {
                boolean l = left == 1 ? true : false;
                boolean r = right == 1 ? true : false;
                return l || r ? 1 : 0;
            }
        }
        else if (this.type == 3) {
            if (this.op == TokenType.tok_not) {
                boolean v = (this.left.eval() == 1 ? true : false);
                return !v ? 1 : 0;
            }
        }
        return 0;
    }
}
