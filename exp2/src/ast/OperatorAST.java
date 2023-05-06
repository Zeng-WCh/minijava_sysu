package ast;
import exceptions.DividedByZeroException;

public class OperatorAST implements ast {
    private ast left, right;
    private char op;

    public OperatorAST(ast left, ast right, char op) {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    @Override
    public double eval() throws DividedByZeroException {
        if (this.op == '+') {
            return this.left.eval() + this.right.eval();
        } else if (this.op == '-') {
            return this.left.eval() - this.right.eval();
        } else if (this.op == '*') {
            return this.left.eval() * this.right.eval();
        } else if (this.op == '/') {
            double right = this.right.eval();
            if (right == 0) {
                throw new DividedByZeroException();
            }
            return this.left.eval() / right;
        } else {
            return 0;
        }
    }
}
