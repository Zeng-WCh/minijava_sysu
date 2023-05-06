package ast;
import exceptions.DividedByZeroException;

/**
 * UnaryOperatorAST, used to represent the unary operator, like "-1"
 */
public class UnaryOperatorAST implements ast {
    private char operator;
    private ast operand;

    public UnaryOperatorAST(char operator, ast operand) {
        this.operator = operator;
        this.operand = operand;
    }

    @Override
    public double eval() throws DividedByZeroException {
        if (this.operator == '-') {
            return -1 * this.operand.eval();
        }
        return this.operand.eval();
    }
}
