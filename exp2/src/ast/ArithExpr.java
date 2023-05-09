package ast;

import exceptions.ExpressionException;
import exceptions.DividedByZeroException;
import token.TokenType;

/**
 * ArithExpr AST Node
 * 
 * @author Weichao Zeng
 * @version 1.00 (Last update: 2023/05/09)
 */
public class ArithExpr implements ast {
    /**
     * type 0, ArithExpr ::= decimal
     */
    private double num = 0;
    /**
     * type 1 ArithExpr ::= - ArithExpr (op left)
     * type 2 ArithExpr ::= ArithExpr op ArithExpr (left op right)
     * type 6 ArithExpr ::= ( ArithExpr )
     */
    private ArithExpr left = null, right = null;
    /**
     * type 3 ArithExpr ::= UnaryFunc
     */
    private UnaryFunc unary = null;
    /**
     * type 4 ArithExpr ::= VariableFunc
     */
    private VariablFunc var = null;
    /**
     * type 5 ArithExpr ::= BoolExpr ? ArithExpr : ArithExpr (condition ? left : right)
     */
    private BoolExpr condition = null;
    /**
     * just used in type 2
     */
    private TokenType op = null, op1 = null;
    /**
     * to decide use which way to eval and print
     */
    private int type = -1;

    /**
     * type 0 constructor
     * @param num, the decimal
     */
    public ArithExpr(double num) {
        this.num = num;
        this.type = 0;
    }

    /**
     * type 1 constructor
     * @param arithExpr, the ArithExpr
     * @param op, for now, only unary minus, so it is not quite necessary, but to make it more general
     */
    public ArithExpr(ArithExpr arithExpr, TokenType op) {
        this.left = arithExpr;
        this.op = op;
        this.type = 1;
    }

    /**
     * type 2 constructor
     * @param left, left ArithExpr
     * @param op, operator
     * @param right, right ArithExpr
     */
    public ArithExpr(ArithExpr left, TokenType op, ArithExpr right) {
        this.left = left;
        this.op = op;
        this.right = right;
        this.type = 2;
    }

    /**
     * type 3 constructor
     * 
     * @param condition
     * @param op, same as type 1, not that necessary
     * @param left, left ArithExpr
     * @param op1, operator, same as above
     * @param right, right ArithExpr
     */
    public ArithExpr(BoolExpr condition, TokenType op, ArithExpr left, TokenType op1, ArithExpr right) {
        this.condition = condition;
        this.op = op;
        this.left = left;
        this.op1 = op1;
        this.right = right;
        this.type = 3;
    }

    /**
     * type 4 constructor
     * @param FuncCall, unary function must
     */
    public ArithExpr(UnaryFunc FuncCall) {
        this.unary = FuncCall;
        this.type = 4;
    }

    /**
     * type 5 constructor
     * @param FuncCall, variable function must
     */
    public ArithExpr(VariablFunc FuncCall) {
        this.var = FuncCall;
        this.type = 5;
    }

    /**
     * type 6 constructor
     * @param arithExpr, the ArithExpr
     */
    public ArithExpr(ArithExpr arithExpr) {
        this.left = arithExpr;
        this.type = 6;
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
        else if (this.type == 6) {
            return this.left.eval();
        }
        return 0;
    }

    @Override
    public void print(int depth) {
        for (int i = 0; i < depth; ++i) {
            System.out.print(" ");
        }
        if (this.type == 0) {
            System.out.println("`-- ArithExpr: " + this.num);
        }
        else if (this.type == 1) {
            char operator = (this.op == TokenType.tok_unary_minus? '-' : 0);
            System.out.printf("`-- ArithExpr: %c ArithExpr\n", operator);
            this.left.print(depth + 1);
        }
        else if (this.type == 2) {
            char operator = 0;
            if (this.op == TokenType.tok_plus) {
                operator = '+';
            }
            else if (this.op == TokenType.tok_minus) {
                operator = '-';
            }
            else if (this.op == TokenType.tok_star) {
                operator = '*';
            }
            else if (this.op == TokenType.tok_slash) {
                operator = '/';
            }
            else if (this.op == TokenType.tok_caret) {
                operator = '^';
            }
            System.out.printf("`-- ArithExpr: ArithExpr %c ArithExpr\n", operator);
            // System.out.println("`-- ArithExpr: " + this.op);
            this.left.print(depth + 1);
            this.right.print(depth + 1);
        }
        else if (this.type == 3) {
            System.out.println("`-- ArithExpr: BoolExpr ? ArithExpr : ArithExpr");
            this.condition.print(depth + 1);
            this.left.print(depth + 1);
            this.right.print(depth + 1);
        }
        else if (this.type == 4) {
            System.out.println("`-- ArithExpr: UnaryFunc");
            this.unary.print(depth + 1);
        }
        else if (this.type == 5) {
            System.out.println("`-- ArithExpr: VariableFunc");
            this.var.print(depth + 1);
        }
        else if (this.type == 6) {
            System.out.println("`-- ArithExpr: ( ArithExpr )");
            this.left.print(depth + 1);
        }
    }
}
